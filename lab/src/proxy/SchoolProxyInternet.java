package proxy;

// Task 3: School Wi-Fi Firewall
public class SchoolProxyInternet implements Internet {
    private RealInternet realInternet;

    @Override
    public void connectTo(String site) {
        if (site.equals("gaming.com") || site.equals("socialmedia.com")) {
            System.out.println("[BLOCKED BY FIREWALL] Cannot access: " + site);
            return;
        }
        if (realInternet == null) {
            realInternet = new RealInternet();
        }
        realInternet.connectTo(site);
    }
}
