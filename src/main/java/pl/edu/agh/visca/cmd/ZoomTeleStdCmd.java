//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.visca.cmd;

import lombok.AllArgsConstructor;
import lombok.Getter;

public final class ZoomTeleStdCmd extends Cmd {
    private static final byte[] ptTeleStdCommandData = new byte[]{1, 4, 7, 0x00};
    private CONSTANT_SPEED speed;


    public ZoomTeleStdCmd() {
        super(false, true);
        setSpeed(CONSTANT_SPEED.DEFAULT_SPEED);
    }

    public void setSpeed(final CONSTANT_SPEED speed) {
        this.speed = speed;
        ptTeleStdCommandData[3] = speed.value;
    }

    @Override
    public byte[] prepareContent() {
        return duplicateArray(ptTeleStdCommandData);
    }

    @AllArgsConstructor
    @Getter
    public enum CONSTANT_SPEED {
        DEFAULT_SPEED((byte) 0x02),
        SPEED0((byte) 0x20),
        SPEED1((byte) 0x21),
        SPEED2((byte) 0x22),
        SPEED3((byte) 0x23),
        SPEED4((byte) 0x24),
        SPEED5((byte) 0x25),
        SPEED6((byte) 0x26),
        SPEED7((byte) 0x27);

        private byte value;

        public String getName() {
            return name();
        }
    }

    @Override
    public String toString() {
        return "ZOOM_TELE_" + speed.name();
    }
}
