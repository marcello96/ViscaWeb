package pl.edu.agh.visca.cmd;


import lombok.Getter;
import lombok.Setter;
import pl.edu.agh.visca.model.Constants;

@Getter
@Setter
public class SetDestCmd extends Cmd {
    private byte address;

    public SetDestCmd() {
        super(false, false);
    }

    @Override
    public byte[] prepareContent() {
        Constants.DESTINATION_ADDRESS = address;
        return new byte[0];
    }

    @Override
    public String toString() {
        return "SET_DEST_" + address;
    }
}
