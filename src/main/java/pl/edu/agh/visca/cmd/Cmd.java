//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.visca.cmd;

public abstract class Cmd {
    public Cmd(byte destination) {
        this.destination = destination;
    }

    private byte destination;

    public abstract byte[] createCommandData();

    public byte getDestination() {
        return destination;
    }

    protected static byte[] duplicateArray(byte[] src) {
        byte[] dest = new byte[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }
}
