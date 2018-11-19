//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.visca.cmd;

public final class ClearAllCmd extends Cmd {
    private static final byte[] clearAllCommandData = new byte[]{1, 0, 1};

    public ClearAllCmd() {
        super(true, true);
    }

    @Override
    public byte[] prepareContent() {
        return duplicateArray(clearAllCommandData);
    }
}
