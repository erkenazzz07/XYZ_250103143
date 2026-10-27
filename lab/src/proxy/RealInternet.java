package proxy;

public class RealInternet implements Internet {
    @Override
    public void connectTo(String site) {
        System.out.println("Connected to site: " + site);
    }
}
