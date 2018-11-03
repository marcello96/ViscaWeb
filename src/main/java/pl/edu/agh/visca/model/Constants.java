package pl.edu.agh.visca.model;

public class Constants {
    public static byte getDestinationAddress() {
        return destinationAddress;
    }

    public static void setDestinationAddress(byte destinationAddress) {
        Constants.destinationAddress = destinationAddress;
    }

    private static byte destinationAddress = 1;

    public static byte getServerAddress() {
        return serverAddress;
    }

    public static void setServerAddress(byte serverAddress) {
        Constants.serverAddress = serverAddress;
    }

    private static byte serverAddress = 8;
}
