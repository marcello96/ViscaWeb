//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.visca.cmd;

public final class PanTiltLeftCmd extends Cmd {
    private static final byte[] ptLeftCommandData = new byte[]{1, 6, 1, 0x00, 0x00, 1, 3};
    private ConstantPanSpeed panSpeed;

    public PanTiltLeftCmd() {
        super(false, true);
        setSpeed(ConstantPanSpeed.L08);
    }

    @Override
    public byte[] prepareContent() {
        /*cmdData[3] = 8;
        cmdData[4] = 1;*/
        return duplicateArray(ptLeftCommandData);
    }

    public void setSpeed(ConstantPanSpeed panSpeed) {
        this.panSpeed = panSpeed;
        ptLeftCommandData[3] = panSpeed.getValue();
    }

    @Override
    public String toString() {
        return panSpeed != null ? "LEFT_" + panSpeed.name() : "LEFT";
    }
}
