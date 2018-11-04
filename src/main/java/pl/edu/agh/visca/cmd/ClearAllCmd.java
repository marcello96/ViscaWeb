//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.visca.cmd;

import pl.edu.agh.visca.model.Constants;

public final class ClearAllCmd extends Cmd {
    private static final byte[] clearAllCommandData = new byte[]{1, 0, 1};

    public ClearAllCmd() {
        super(Constants.SERVER_ADDRESS);

    }

    @Override
    public byte[] createCommandData() {
        return duplicateArray(clearAllCommandData);
    }
}
