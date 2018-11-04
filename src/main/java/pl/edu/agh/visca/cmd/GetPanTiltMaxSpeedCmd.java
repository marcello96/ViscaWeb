//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.visca.cmd;

import pl.edu.agh.visca.model.Constants;

public final class GetPanTiltMaxSpeedCmd extends Cmd {
    private static final byte[] maxSpeedCommandData = new byte[]{9, 6, 17};

    public GetPanTiltMaxSpeedCmd() {
        super(Constants.DESTINATION_ADDRESS);
    }

    @Override
    public byte[] createCommandData() {
        return duplicateArray(maxSpeedCommandData);
    }
}
