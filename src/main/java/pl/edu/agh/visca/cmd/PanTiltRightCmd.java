//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.visca.cmd;

public final class PanTiltRightCmd extends Cmd {
    private static final byte[] ptRightCommandData = new byte[]{1, 6, 1, 0x00, 0x00, 2, 3};
    private ConstantPanSpeed panSpeed;

    public PanTiltRightCmd() {
        super(false, true);
        setSpeed(ConstantPanSpeed.L04);
    }

    @Override
    public byte[] prepareContent() {
        /*cmdData[3] = 4;
        cmdData[4] = 1;*/
        return duplicateArray(ptRightCommandData);
    }

    public void setSpeed(ConstantPanSpeed panSpeed) {
        this.panSpeed = panSpeed;
        ptRightCommandData[3] = panSpeed.getValue();
    }

    @Override
    public String toString() {
        return panSpeed != null ? "RIGHT_" + panSpeed.name() : "RIGHT";
    }
}
