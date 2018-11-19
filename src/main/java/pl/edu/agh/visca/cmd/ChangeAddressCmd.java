package pl.edu.agh.visca.cmd;

public final class ChangeAddressCmd extends Cmd {
    private static final byte[] changeAddressData = new byte[] {48, 1};

    public ChangeAddressCmd() {
        super(false, true);
    }

    @Override
    public byte[] prepareContent() {
        return duplicateArray(changeAddressData);
    }

    public void setNewAddress(byte newAddress) {
        changeAddressData[1] = newAddress;
    }
}
