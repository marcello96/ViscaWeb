package pl.edu.agh.visca.service;

import jssc.SerialPort;
import jssc.SerialPortException;
import pl.edu.agh.visca.cmd.Cmd;
import pl.edu.agh.visca.cmd.ViscaCommand;
import pl.edu.agh.visca.cmd.WaitCmd;

public class ViscaCommandHelper {

    static void sendCommand(SerialPort serialPort, Cmd command) {

        if (command instanceof WaitCmd) {
            sleep(((WaitCmd) command).getTime());
            return;
        }
        byte[] cmdData = command.createCommandData();
        try {
            ViscaCommand vCmd = new ViscaCommand();
            vCmd.commandData = cmdData;
            vCmd.sourceAdr = 0;
            vCmd.destinationAdr = command.getDestination();
            cmdData = vCmd.getCommandData();
            System.out.println("@ " + byteArrayToString(cmdData));

            serialPort.writeBytes(cmdData);
        } catch (SerialPortException | RuntimeException e) {
            e.printStackTrace();
        }
    }

    static void readResponse(SerialPort serialPort) {
        byte[] response;
        try {
            response = ViscaResponseReader.readResponse(serialPort);
            System.out.println("> " + byteArrayToString(response));
        } catch (ViscaResponseReader.TimeoutException var15) {
            System.out.println("! TIMEOUT exception");
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    private static String byteArrayToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();

        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }

        return sb.toString();
    }

    public static void sleep(int timeSec) {
        try {
            Thread.sleep((long) (timeSec * 1000));
        } catch (InterruptedException var2) {
            var2.printStackTrace();
        }

    }

}
