//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.visca.cmd;

public final class GetPanTiltMaxSpeedCmd extends Cmd {
    private static final byte[] maxSpeedCommandData = new byte[]{9, 6, 17};

    public GetPanTiltMaxSpeedCmd() {
        super(false, true);
    }

    @Override
    public byte[] prepareContent() {
        return duplicateArray(maxSpeedCommandData);
    }

    @Override
    public String toString() {
        return "GET_MAX_SPEED";
    }

}
