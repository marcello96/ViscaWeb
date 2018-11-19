//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.visca.cmd;

import pl.edu.agh.visca.model.Constants;

public final class PanTiltRightCmd extends Cmd {
    private static final byte[] ptRightCommandData = new byte[]{1, 6, 1, 0x00, 0x00, 2, 3};

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
        ptRightCommandData[3] = panSpeed.getValue();
    }
}
