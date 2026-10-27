package bridge;

public class BridgeDemo {
    public static void main(String[] args) {
        System.out.println("===== Task 4: The New Machine =====");
        Device light = new SmartLight();
        AdvancedRemote remote = new AdvancedRemote(light);
        remote.powerOn();
        remote.mute();

        System.out.println("\n===== Task 5: The New Button =====");
        Device radio = new Radio();
        PartyRemote partyRemote = new PartyRemote(radio);
        partyRemote.partyMode();
    }
}
