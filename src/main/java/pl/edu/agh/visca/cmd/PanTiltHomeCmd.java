//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.visca.cmd;

import pl.edu.agh.visca.model.Constants;

public final class PanTiltHomeCmd extends Cmd {
    private static final byte[] ptHomeCommandData = new byte[]{1, 6, 4};

    public PanTiltHomeCmd() {
        super(Constants.DESTINATION_ADDRESS);
    }

    @Override
    public byte[] createCommandData() {
        return duplicateArray(ptHomeCommandData);
    }
}
