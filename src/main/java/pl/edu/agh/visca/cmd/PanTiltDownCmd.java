//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.visca.cmd;

import pl.edu.agh.visca.model.Constants;

public final class PanTiltDownCmd extends Cmd {
    private static final byte[] ptUpCommandData = new byte[]{1, 6, 1, 0x00, 0x00, 3, 2};

    public PanTiltDownCmd() {
        super(false, true);
        setSpeed(ConstantTiltSpeed.L02);
    }

    @Override
    public byte[] prepareContent() {
        /*cmdData[3] = 1;
        cmdData[4] = 2;*/
        return duplicateArray(ptUpCommandData);
    }

    public void setSpeed(ConstantTiltSpeed tiltSpeed) {
        ptUpCommandData[4] = tiltSpeed.getValue();
    }
}
