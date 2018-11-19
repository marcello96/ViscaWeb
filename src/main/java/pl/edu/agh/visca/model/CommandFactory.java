package pl.edu.agh.visca.model;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import pl.edu.agh.visca.cmd.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CommandFactory {

    @SneakyThrows
    public List<CommandName> createCommandList(String[] inputCommands) {
        return Stream.of(inputCommands)
                .map(this::createCommand)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private CommandName createCommand(String inputCommand) {
        if (inputCommand.startsWith(CommandName.WAIT.name())) {
            return getWaitCommand(inputCommand);
        }

        if (inputCommand.startsWith(CommandName.CHANGE_ADDRESS.name())) {
            return getChangeAddress(inputCommand);
        }

        if (inputCommand.startsWith(CommandName.SET_DEST.name())) {
            return getSetDest(inputCommand);
        }

        if (inputCommand.startsWith(CommandName.ZOOM_TELE.name())) {
            return getZoomTeleCommand(inputCommand);
        }

        if (inputCommand.startsWith(CommandName.ZOOM_WIDE.name())) {
            return getZoomWideCommand(inputCommand);
        }

        if (inputCommand.startsWith(CommandName.UP.name())) {
            return getPanTiltUpCommand(inputCommand);
        }

        if (inputCommand.startsWith(CommandName.DOWN.name())) {
            return getPanTiltDownCommand(inputCommand);
        }

        if (inputCommand.startsWith(CommandName.LEFT.name())) {
            return getPanTiltLeftCommand(inputCommand);
        }

        if (inputCommand.startsWith(CommandName.RIGHT.name())) {
            return getPanTiltRightCommand(inputCommand);
        }

        return CommandName.valueOf(inputCommand);
    }

    @SneakyThrows
    private CommandName getWaitCommand(String inputCommand) {
        int pos = inputCommand.indexOf("_");
        WaitCmd cmd = (WaitCmd) CommandName.WAIT.getCommand();

        if(pos != -1) {
            String time = inputCommand.substring(pos + 1);
            cmd.setTime(Integer.parseInt(time));
        }

        return CommandName.WAIT;
    }

    @SneakyThrows
    private CommandName getSetDest(String inputCommand) {
        int pos = inputCommand.indexOf("_", inputCommand.indexOf("_") + 1);

        SetDestCmd cmd = (SetDestCmd) CommandName.SET_DEST.getCommand();
        if (pos != -1) {
            String address = inputCommand.substring(pos + 1);
            cmd.setAddress(Byte.parseByte(address));
        }

        return CommandName.SET_DEST;
    }

    @SneakyThrows
    private CommandName getChangeAddress(String inputCommand) {
        int pos = inputCommand.indexOf("_", inputCommand.indexOf("_") + 1);

        ChangeAddressCmd cmd = (ChangeAddressCmd) CommandName.CHANGE_ADDRESS.getCommand();
        if (pos != -1) {
            String address = inputCommand.substring(pos + 1);
            cmd.setNewAddress(Byte.parseByte(address));
        }

        return CommandName.CHANGE_ADDRESS;
    }

    @SneakyThrows
    private CommandName getZoomTeleCommand(String inputCommand) {
        int pos = inputCommand.indexOf("_", inputCommand.indexOf("_") + 1);

        ZoomTeleStdCmd cmd = (ZoomTeleStdCmd) CommandName.ZOOM_TELE.getCommand();

        if (pos == -1) {
            return CommandName.ZOOM_TELE;
        }

        String speed = inputCommand.substring(pos + 1);
        cmd.setSpeed(ZoomTeleStdCmd.CONSTANT_SPEED.valueOf(speed));

        return CommandName.ZOOM_TELE;
    }

    @SneakyThrows
    private CommandName getZoomWideCommand(String inputCommand) {
        int pos = inputCommand.indexOf("_", inputCommand.indexOf("_") + 1);

        ZoomWideStdCmd cmd = (ZoomWideStdCmd) CommandName.ZOOM_WIDE.getCommand();

        if (pos == -1) {
            return CommandName.ZOOM_WIDE;
        }

        String speed = inputCommand.substring(pos + 1);
        cmd.setSpeed(ZoomWideStdCmd.CONSTANT_SPEED.valueOf(speed));

        return CommandName.ZOOM_WIDE;
    }

    @SneakyThrows
    private CommandName getPanTiltUpCommand(String inputCommand) {
        int pos = inputCommand.indexOf("_");

        PanTiltUpCmd cmd = (PanTiltUpCmd) CommandName.UP.getCommand();
        if (pos == -1) {
            return CommandName.UP;
        }

        String tiltSpeed = inputCommand.substring(pos + 1);
        cmd.setSpeed(ConstantTiltSpeed.valueOf(tiltSpeed));

        return CommandName.UP;
    }

    @SneakyThrows
    private CommandName getPanTiltDownCommand(String inputCommand) {
        int pos = inputCommand.indexOf("_");

        PanTiltDownCmd cmd = (PanTiltDownCmd) CommandName.DOWN.getCommand();

        if (pos == -1) {
            return CommandName.DOWN;
        }

        String tiltSpeed = inputCommand.substring(pos + 1);
        cmd.setSpeed(ConstantTiltSpeed.valueOf(tiltSpeed));

        return CommandName.DOWN;
    }

    @SneakyThrows
    private CommandName getPanTiltLeftCommand(String inputCommand) {
        int pos = inputCommand.indexOf("_");

        PanTiltLeftCmd cmd = (PanTiltLeftCmd) CommandName.LEFT.getCommand();

        if (pos == -1) {
            return CommandName.LEFT;
        }

        String panSpeed = inputCommand.substring(pos + 1);
        cmd.setSpeed(ConstantPanSpeed.valueOf(panSpeed));

        return CommandName.LEFT;
    }

    @SneakyThrows
    private CommandName getPanTiltRightCommand(String inputCommand) {
        int pos = inputCommand.indexOf("_");

        PanTiltRightCmd cmd = (PanTiltRightCmd) CommandName.RIGHT.getCommand();

        if (pos == -1) {
            return CommandName.RIGHT;
        }

        String panSpeed = inputCommand.substring(pos + 1);
        cmd.setSpeed(ConstantPanSpeed.valueOf(panSpeed));

        return CommandName.RIGHT;
    }
}
