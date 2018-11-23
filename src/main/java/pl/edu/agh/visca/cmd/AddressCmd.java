//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.visca.cmd;

public final class AddressCmd extends Cmd {
    private static final byte[] adrCommmandData = new byte[]{48, 1};

    public AddressCmd() {
        super(true, true);
    }

    @Override
    public byte[] prepareContent() {
        return duplicateArray(adrCommmandData);
    }

    @Override
    public String toString() {
        return "ADDRESS";
    }
}
