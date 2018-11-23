//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.visca.cmd;

public final class PanTiltHomeCmd extends Cmd {
    private static final byte[] ptHomeCommandData = new byte[]{1, 6, 4};

    public PanTiltHomeCmd() {
        super(false, true);
    }

    @Override
    public byte[] prepareContent() {
        return duplicateArray(ptHomeCommandData);
    }

    @Override
    public String toString() {
        return "HOME";
    }
}
