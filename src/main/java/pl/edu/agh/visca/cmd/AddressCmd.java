//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.visca.cmd;

import pl.edu.agh.visca.model.Constants;

public final class AddressCmd extends Cmd {
    private static final byte[] adrCommmandData = new byte[]{48, 1};

    public AddressCmd() {
        super(Constants.getServerAddress());
    }

    public byte[] createCommandData() {
        byte[] cmdData = duplicatArray(adrCommmandData);
        return cmdData;
    }

    private static byte[] duplicatArray(byte[] src) {
        byte[] dest = new byte[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }
}
