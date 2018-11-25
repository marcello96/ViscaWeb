package pl.edu.agh.visca.cmd;

public final class ChangeAddressCmd extends Cmd {
    private static final byte[] changeAddressData = new byte[] {48, 1};
    private byte newAddress;


    public ChangeAddressCmd() {
        super(false, true);
    }

    @Override
    public byte[] prepareContent() {
        return duplicateArray(changeAddressData);
    }

    public void setNewAddress(byte newAddress) {
        this.newAddress = newAddress;
        changeAddressData[1] = newAddress;
    }

    @Override
    public String toString() {
        return "CHANGE_ADDRESS_" + newAddress;
    }
}
