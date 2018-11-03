package pl.edu.agh.visca.cmd;

import pl.edu.agh.visca.model.Constants;

public class WaitCmd extends Cmd {
    @Override
    public byte[] createCommandData() {
        sleep(time);
        return new byte[0];
    }

    public WaitCmd() {
        super(Constants.getDestinationAddress());
        this.time = 5;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    private int time;

    private static void sleep(int timeSec) {
        try {
            Thread.sleep((long)(timeSec * 1000));
        } catch (InterruptedException var2) {
            var2.printStackTrace();
        }

    }
}
