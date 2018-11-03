package pl.edu.agh.visca.service;

import jssc.SerialPort;
import jssc.SerialPortException;
import pl.edu.agh.visca.cmd.Cmd;
import pl.edu.agh.visca.cmd.ViscaCommand;
import pl.edu.agh.visca.cmd.WaitCmd;

public class ViscaCommandHelper {

    public static void sendCommand(SerialPort serialPort, Cmd command) {

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
        } catch (SerialPortException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public static void readResponse(SerialPort serialPort){
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
        byte[] var5 = bytes;
        int var4 = bytes.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            byte b = var5[var3];
            sb.append(String.format("%02X ", b));
        }

        return sb.toString();
    }

    private static void sleep(int timeSec) {
        try {
            Thread.sleep((long)(timeSec * 1000));
        } catch (InterruptedException var2) {
            var2.printStackTrace();
        }

    }

}
