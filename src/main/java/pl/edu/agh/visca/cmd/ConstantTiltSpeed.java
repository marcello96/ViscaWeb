package pl.edu.agh.visca.cmd;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ConstantTiltSpeed {

    LEVEL01((byte) 0x01),
    LEVEL02((byte) 0x02),
    LEVEL03((byte) 0x03),
    LEVEL04((byte) 0x04),
    LEVEL05((byte) 0x05),
    LEVEL06((byte) 0x06),
    LEVEL07((byte) 0x07),
    LEVEL08((byte) 0x08),
    LEVEL09((byte) 0x09),
    LEVEL0A((byte) 0x0A),
    LEVEL0B((byte) 0x0B),
    LEVEL0C((byte) 0x0C),
    LEVEL0D((byte) 0x0D),
    LEVEL0E((byte) 0x0E),
    LEVEL0F((byte) 0x0F),
    LEVEL10((byte) 0x10),
    LEVEL11((byte) 0x11),
    LEVEL12((byte) 0x12),
    LEVEL13((byte) 0x13),
    LEVEL14((byte) 0x14);

    private byte value;
}
