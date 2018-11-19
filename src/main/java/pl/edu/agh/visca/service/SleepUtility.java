package pl.edu.agh.visca.service;

import lombok.SneakyThrows;

public class SleepUtility {
    @SneakyThrows
    public static void sleep(int timeSec) {
        Thread.sleep((long) (timeSec * 1000));
    }
}
