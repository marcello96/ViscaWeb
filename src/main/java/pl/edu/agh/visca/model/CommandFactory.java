package pl.edu.agh.visca.model;

import org.springframework.stereotype.Service;
import pl.edu.agh.visca.cmd.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CommandFactory {

    public List<Cmd> createCommandList(String[] inputCommands) {
        return Stream.of(inputCommands)
                .map(this::createCommand)
                .collect(Collectors.toList());
    }

    private Cmd createCommand(String inputCommand) {
        if (inputCommand.startsWith(CommandName.WAIT.name())) {
            return getWaitCommand(inputCommand);
        }

        if (inputCommand.startsWith(CommandName.ZOOM_TELE.name())) {
            return getZoomTeleCommand(inputCommand);
        }

        if (inputCommand.startsWith(CommandName.ZOOM_WIDE.name())) {
            return getZoomWideCommand(inputCommand);
        }

        if (inputCommand.startsWith(CommandName.PAN_TILT_UP.name())) {
            return getPanTiltUpCommand(inputCommand);
        }

        if (inputCommand.startsWith(CommandName.PAN_TILT_DOWN.name())) {
            return getPanTiltDownCommand(inputCommand);
        }

        if (inputCommand.startsWith(CommandName.PAN_TILT_LEFT.name())) {
            return getPanTiltLeftCommand(inputCommand);
        }

        if (inputCommand.startsWith(CommandName.PAN_TILT_RIGHT.name())) {
            return getPanTiltRightCommand(inputCommand);
        }

        return CommandName.valueOf(inputCommand).getCommand();
    }

    private Cmd getWaitCommand(String inputCommand) {
        int pos = inputCommand.indexOf("_");
        String time = inputCommand.substring(pos + 1);
        WaitCmd cmd = (WaitCmd) CommandName.WAIT.getCommand();

        try {
            cmd.setTime(Integer.parseInt(time));
        } catch (NumberFormatException e) {
            System.out.println("Wrong format: should be an integer" + e.getMessage());
            e.printStackTrace();
        }

        return cmd;
    }

    private Cmd getZoomTeleCommand(String inputCommand) {
        int pos = inputCommand.indexOf("_", inputCommand.indexOf("_") + 1);

        ZoomTeleStdCmd cmd = (ZoomTeleStdCmd) CommandName.ZOOM_TELE.getCommand();

        if (pos == -1) {
            return cmd;
        }

        String speed = inputCommand.substring(pos + 1);
        try {
            cmd.setSpeed(ZoomTeleStdCmd.CONSTANT_SPEED.valueOf(speed));
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong format should be a constant in ZoomTeleStdCmd.Constant_Speed" + e.getMessage());
            e.printStackTrace();
        }

        return cmd;
    }

    private Cmd getZoomWideCommand(String inputCommand) {
        int pos = inputCommand.indexOf("_", inputCommand.indexOf("_") + 1);

        ZoomWideStdCmd cmd = (ZoomWideStdCmd) CommandName.ZOOM_WIDE.getCommand();

        if (pos == -1) {
            return cmd;
        }

        String speed = inputCommand.substring(pos + 1);

        try {
            cmd.setSpeed(ZoomWideStdCmd.CONSTANT_SPEED.valueOf(speed));
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong format should be a constant in ZoomWideStdCmd.Constant_Speed" + e.getMessage());
            e.printStackTrace();
        }

        return cmd;
    }

    private Cmd getPanTiltUpCommand(String inputCommand) {
        int pos = inputCommand.indexOf("_",
                inputCommand.indexOf("_",
                        inputCommand.indexOf("_") + 1) + 1);

        int pos2 = inputCommand.indexOf("_",
                inputCommand.indexOf("_",
                        inputCommand.indexOf("_",
                                inputCommand.indexOf("_") + 1) + 1) + 1);

        PanTiltUpCmd cmd = (PanTiltUpCmd) CommandName.PAN_TILT_UP.getCommand();
        if (pos == -1 || pos2 == -1) {
            return cmd;
        }

        String panSpeed = inputCommand.substring(pos + 1, pos2);
        String tiltSpeed = inputCommand.substring(pos2 + 1);

        try {
            cmd.setSpeed(ConstantPanSpeed.valueOf(panSpeed), ConstantTiltSpeed.valueOf(tiltSpeed));
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong format should be a constant in ConstantPanSpeed and in ConstantTiltSpeed" + e.getMessage());
            e.printStackTrace();
        }

        return cmd;
    }

    private Cmd getPanTiltDownCommand(String inputCommand) {
        int pos = inputCommand.indexOf("_",
                inputCommand.indexOf("_",
                        inputCommand.indexOf("_") + 1) + 1);

        int pos2 = inputCommand.indexOf("_",
                inputCommand.indexOf("_",
                        inputCommand.indexOf("_",
                                inputCommand.indexOf("_") + 1) + 1) + 1);

        PanTiltDownCmd cmd = (PanTiltDownCmd) CommandName.PAN_TILT_DOWN.getCommand();

        if (pos == -1 || pos2 == -1) {
            return cmd;
        }

        String panSpeed = inputCommand.substring(pos + 1, pos2);
        String tiltSpeed = inputCommand.substring(pos2 + 1);

        try {
            cmd.setSpeed(ConstantPanSpeed.valueOf(panSpeed), ConstantTiltSpeed.valueOf(tiltSpeed));
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong format should be a constant in ConstantPanSpeed and in ConstantTiltSpeed" + e.getMessage());
            e.printStackTrace();
        }

        return cmd;
    }

    private Cmd getPanTiltLeftCommand(String inputCommand) {
        int pos = inputCommand.indexOf("_",
                inputCommand.indexOf("_",
                        inputCommand.indexOf("_") + 1) + 1);

        int pos2 = inputCommand.indexOf("_",
                inputCommand.indexOf("_",
                        inputCommand.indexOf("_",
                                inputCommand.indexOf("_") + 1) + 1) + 1);

        PanTiltLeftCmd cmd = (PanTiltLeftCmd) CommandName.PAN_TILT_LEFT.getCommand();

        if (pos == -1 || pos2 == -1) {
            return cmd;
        }

        String panSpeed = inputCommand.substring(pos + 1, pos2);
        String tiltSpeed = inputCommand.substring(pos2 + 1);

        try {
            cmd.setSpeed(ConstantPanSpeed.valueOf(panSpeed), ConstantTiltSpeed.valueOf(tiltSpeed));
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong format should be a constant in ConstantPanSpeed and in ConstantTiltSpeed" + e.getMessage());
            e.printStackTrace();
        }

        return cmd;
    }

    private Cmd getPanTiltRightCommand(String inputCommand) {
        int pos = inputCommand.indexOf("_",
                inputCommand.indexOf("_",
                        inputCommand.indexOf("_") + 1) + 1);

        int pos2 = inputCommand.indexOf("_",
                inputCommand.indexOf("_",
                        inputCommand.indexOf("_",
                                inputCommand.indexOf("_") + 1) + 1) + 1);

        PanTiltRightCmd cmd = (PanTiltRightCmd) CommandName.PAN_TILT_RIGHT.getCommand();

        if (pos == -1 || pos2 == -1) {
            return cmd;
        }

        String panSpeed = inputCommand.substring(pos + 1, pos2);
        String tiltSpeed = inputCommand.substring(pos2 + 1);

        try {
            cmd.setSpeed(ConstantPanSpeed.valueOf(panSpeed), ConstantTiltSpeed.valueOf(tiltSpeed));
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong format should be a constant in ConstantPanSpeed and in ConstantTiltSpeed" + e.getMessage());
            e.printStackTrace();
        }

        return cmd;
    }
}
