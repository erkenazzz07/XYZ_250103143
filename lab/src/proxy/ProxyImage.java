package proxy;

// Task 1: Protection Proxy
public class ProxyImage implements Image {
    private final String fileName;
    private final String userRole;
    private RealImage realImage;

    public ProxyImage(String fileName, String userRole) {
        this.fileName = fileName;
        this.userRole = userRole;
    }

    @Override
    public void show() {
        if (!"ADMIN".equals(userRole)) {
            System.out.println("[ACCESS DENIED] You lack permission to view: " + fileName);
            return;
        }
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.show();
    }
}
