//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.visca.cmd;

import pl.edu.agh.visca.model.Constants;

public final class PanTiltLeftCmd extends Cmd {
    private static final byte[] ptLeftCommandData = new byte[]{1, 6, 1, 0x00, 0x00, 1, 3};

    public PanTiltLeftCmd() {
        super(Constants.DESTINATION_ADDRESS);
        setSpeed(ConstantPanSpeed.LEVEL08, ConstantTiltSpeed.LEVEL01);
    }

    @Override
    public byte[] createCommandData() {
        /*cmdData[3] = 8;
        cmdData[4] = 1;*/
        return duplicateArray(ptLeftCommandData);
    }

    public void setSpeed(ConstantPanSpeed panSpeed, ConstantTiltSpeed tiltSpeed) {
        ptLeftCommandData[3] = panSpeed.getValue();
        ptLeftCommandData[4] = tiltSpeed.getValue();
    }
}
