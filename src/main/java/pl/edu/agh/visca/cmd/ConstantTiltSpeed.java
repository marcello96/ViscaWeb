package pl.edu.agh.visca.cmd;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ConstantTiltSpeed {

    L01((byte) 0x01),
    L02((byte) 0x02),
    L03((byte) 0x03),
    L04((byte) 0x04),
    L05((byte) 0x05),
    L06((byte) 0x06),
    L07((byte) 0x07),
    L08((byte) 0x08),
    L09((byte) 0x09),
    L0A((byte) 0x0A),
    L0B((byte) 0x0B),
    L0C((byte) 0x0C),
    L0D((byte) 0x0D),
    L0E((byte) 0x0E),
    L0F((byte) 0x0F),
    L10((byte) 0x10),
    L11((byte) 0x11),
    L12((byte) 0x12),
    L13((byte) 0x13),
    L14((byte) 0x14);

    private byte value;

    public String getName() {
        return name();
    }
}
