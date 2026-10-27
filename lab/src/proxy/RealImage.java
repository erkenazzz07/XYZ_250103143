package proxy;

public class RealImage implements Image {
    private final String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        System.out.println("--> [SLOW] Reading " + fileName + " from hard drive...");
    }

    @Override
    public void show() {
        System.out.println("Displaying " + fileName + " on screen!");
    }
}
