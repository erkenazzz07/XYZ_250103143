package proxy;

public class ProxyDemo {
    public static void main(String[] args) {
        System.out.println("===== Task 1: The Bouncer =====");
        Image guestImage = new ProxyImage("salary_sheet.png", "GUEST");
        Image adminImage = new ProxyImage("salary_sheet.png", "ADMIN");
        guestImage.show();
        adminImage.show();

        System.out.println("\n===== Task 2: The View Counter =====");
        Video video = new ProxyVideo("Cat_Memes.mp4");
        video.play();
        video.play();
        video.play();

        System.out.println("\n===== Task 3: School Wi-Fi Firewall =====");
        Internet internet = new SchoolProxyInternet();
        internet.connectTo("wikipedia.org");
        internet.connectTo("gaming.com");
    }
}
