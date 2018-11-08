package pl.edu.agh.visca.service;

public class SleepUtility {
    public static void sleep(int timeSec) {
        try {
            Thread.sleep((long) (timeSec * 1000));
        } catch (InterruptedException var2) {
            var2.printStackTrace();
        }
    }
}
