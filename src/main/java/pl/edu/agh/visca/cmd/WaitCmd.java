package pl.edu.agh.visca.cmd;

import lombok.Getter;
import lombok.Setter;
import pl.edu.agh.visca.model.Constants;

import static pl.edu.agh.visca.service.SleepUtility.sleep;

@Getter
@Setter
public class WaitCmd extends Cmd {
    private static final int DEFAULT_TIME_WAITING = 5;
    private int time;

    public WaitCmd() {
        super(false, false);
        this.time = DEFAULT_TIME_WAITING;
    }

    @Override
    public byte[] prepareContent() {
        sleep(time);
        return new byte[0];
    }
}
