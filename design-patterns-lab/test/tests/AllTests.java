package tests;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Base64;

import task1.*;
import task2.*;
import task3.*;
import task4.*;
import task5.*;

/** Dependency-free verification tests. Run: java -cp out tests.AllTests */
public class AllTests {
    private static int passed = 0, failed = 0;

    private static void check(String name, boolean cond) {
        if (cond) { passed++; System.out.println("  PASS " + name); }
        else      { failed++; System.out.println("  FAIL " + name); }
    }

    private static String capture(Runnable r) {
        PrintStream orig = System.out;
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buf));
        try { r.run(); } finally { System.setOut(orig); }
        return buf.toString().replace("\r\n", "\n");
    }

    public static void main(String[] args) {
        task1(); task2(); task3(); task4(); task5();
        System.out.printf("%nResults: %d passed, %d failed%n", passed, failed);
        if (failed > 0) System.exit(1);
    }

    static void task1() {
        System.out.println("Task 1: Strategy + Decorator");
        String[] h = {"ID", "User"};
        String[][] r = {{"1", "a"}, {"2", "b"}};
        String json = new JsonFormatter().format("T", h, r);
        check("JSON contains title and records", json.contains("\"report\": \"T\"") && json.contains("\"User\": \"b\""));
        String csv = new CsvFormatter().format("T", h, r);
        check("CSV has header row and data rows", csv.contains("ID,User\n") && csv.contains("1,a\n"));

        String out = capture(() -> new Base64EncryptDecorator(new HeaderFooterDecorator(new BaseFileExporter(), "TS"))
                .export("/x", "hello"));
        check("Base64 payload wrapper present", out.contains("[BASE64_CIPHERTEXT:"));
        String hf = capture(() -> new HeaderFooterDecorator(new BaseFileExporter(), "TS").export("/x", "hello"));
        check("HeaderFooter adds header and footer", hf.contains("=== METADATA HEADER: TS ===") && hf.contains("END RECORD ==="));
        String b64 = Base64.getEncoder().encodeToString("hello".getBytes());
        check("Base64 decorator encodes data", out.contains(b64));
        boolean threw = false;
        try { new HeaderFooterDecorator(null, "x"); } catch (IllegalArgumentException e) { threw = true; }
        check("Decorator rejects null wrapped exporter", threw);
    }

    static void task2() {
        System.out.println("Task 2: Adapter + Proxy");
        StockProvider adapter = new StockFeedAdapter(new LegacyStockFeed());
        check("Adapter midpoint AAPL = 183.00", Math.abs(adapter.getMarketPrice("AAPL") - 183.00) < 1e-9);
        check("Adapter midpoint GOOGL = 164.50", Math.abs(adapter.getMarketPrice("GOOGL") - 164.50) < 1e-9);
        check("Adapter status OPEN", "OPEN".equals(adapter.getStatus("AAPL")));
        check("Adapter unknown ticker status", "UNKNOWN".equals(adapter.getStatus("ZZZZ")));

        final int[] calls = {0};
        StockProvider counting = new StockProvider() {
            public double getMarketPrice(String t) { calls[0]++; return 10.0; }
            public String getStatus(String t) { return "OPEN"; }
        };
        CachedStockProxy proxy = new CachedStockProxy(counting, 150);
        String o = capture(() -> { proxy.getMarketPrice("X"); proxy.getMarketPrice("X"); });
        check("Second call is a cache hit (1 upstream call)", calls[0] == 1 && o.contains("[CACHE HIT]"));
        try { Thread.sleep(200); } catch (InterruptedException ignored) {}
        capture(() -> proxy.getMarketPrice("X"));
        check("Expired entry triggers new upstream call", calls[0] == 2);
    }

    static void task3() {
        System.out.println("Task 3: Bridge + Strategy");
        DispatchStrategy bh = new BusinessHoursStrategy();
        check("Business hours: severity 1 at 23 blocked", !bh.shouldDispatch(1, 23));
        check("Business hours: severity 1 at 11 allowed", bh.shouldDispatch(1, 11));
        check("Business hours: boundaries 9 and 17 allowed", bh.shouldDispatch(1, 9) && bh.shouldDispatch(1, 17));
        check("Business hours: 8 and 18 blocked", !bh.shouldDispatch(1, 8) && !bh.shouldDispatch(1, 18));
        check("Emergency bypass at 02:00", bh.shouldDispatch(5, 2));
        check("Immediate always dispatches", new ImmediateStrategy().shouldDispatch(1, 3));

        String sms = capture(() -> new CriticalSystemAlert(new SmsChannel(), bh).processAlert("+1", "boom", 2));
        check("Critical via SMS is single-line and delivered", sms.startsWith("[SMS PROTOCOL -> +1]") && !sms.trim().contains("\n"));
        String sup = capture(() -> new DailySummaryAlert(new EmailChannel(), bh).processAlert("a@b", "x", 23));
        check("Suppressed message logged", sup.contains("[DISPATCH SUPPRESSED]") && sup.contains("23:00"));
        String em = capture(() -> new DailySummaryAlert(new EmailChannel(), bh).processAlert("a@b", "x", 11));
        check("Email delivered with digest body", em.contains("[EMAIL PROTOCOL -> a@b]") && em.contains("Daily Digest:"));
    }

    static void task4() {
        System.out.println("Task 4: Adapter + Decorator");
        PricingService base = new CurrencyOrderAdapter(new LegacyForeignOrder("O1", 100.0), new LegacyCurrencyConverter(), "USD");
        check("Adapter converts 100 GBP -> 125 USD", Math.abs(base.calculateTotal() - 125.0) < 1e-9);
        check("Gift wrap +5", Math.abs(new GiftWrapDecorator(base).calculateTotal() - 130.0) < 1e-9);
        check("Express +15", Math.abs(new ExpressCourierDecorator(base).calculateTotal() - 140.0) < 1e-9);
        PricingService full = new CustomsDutyDecorator(new ExpressCourierDecorator(new GiftWrapDecorator(base)));
        check("Full stack = 157.33", Math.abs(full.calculateTotal() - 157.33) < 1e-9);
        check("Receipt lists all surcharges", full.getReceiptBreakdown().endsWith("[Gift Wrap: $5.00] + [Express Courier: $15.00] + [Customs Duty: 8.5%]"));
        PricingService dutyFirst = new ExpressCourierDecorator(new CustomsDutyDecorator(base));
        check("Decorator order matters (duty then +15 = 150.63)", Math.abs(dutyFirst.calculateTotal() - 150.63) < 1e-9);
    }

    static void task5() {
        System.out.println("Task 5: Bridge + Proxy");
        UserSession admin = new UserSession("a", "ADMIN"), member = new UserSession("m", "MEMBER"), guest = new UserSession("g", "GUEST");
        String o1 = capture(() -> new VideoAsset(new StorageDriverProxy(new S3StorageDriver(), admin), "v.mp4").render());
        check("Admin video renders via S3", o1.contains("SUCCESS: Video playback buffered 4 bytes via PROXY_GUARDED(AMAZON_S3_CLOUD)"));
        String o2 = capture(() -> new AudioAsset(new StorageDriverProxy(new NfsStorageDriver(), member), "a.flac").render());
        check("Member audio renders via NFS", o2.contains("SUCCESS: Audio bitstream") && o2.contains("[PROXY AUDIT]"));
        String o3 = capture(() -> new AudioAsset(new StorageDriverProxy(new NfsStorageDriver(), guest), "a.flac").render());
        check("Guest blocked, backend never touched", o3.contains("[PROXY BLOCKED]") && !o3.contains("[NFS POSIX CLIENT]") && o3.contains("FAILURE"));
        String guestLower = capture(() -> new VideoAsset(new StorageDriverProxy(new S3StorageDriver(), new UserSession("g", "guest")), "v").render());
        check("Role check is case-insensitive", guestLower.contains("[PROXY BLOCKED]"));
    }
}
