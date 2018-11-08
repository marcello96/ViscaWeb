package pl.edu.agh.visca.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.edu.agh.visca.cmd.*;

@AllArgsConstructor
@Getter
public enum CommandName {
    ADDRESS(new AddressCmd()),
    CLEAR_ALL(new ClearAllCmd()),
    GET_PAN_TILT_MAX_SPEED(new GetPanTiltMaxSpeedCmd()),
    PAN_TILT_DOWN(new PanTiltDownCmd()),
    PAN_TILT_HOME(new PanTiltHomeCmd()),
    PAN_TILT_LEFT(new PanTiltLeftCmd()),
    PAN_TILT_RIGHT(new PanTiltRightCmd()),
    PAN_TILT_UP(new PanTiltUpCmd()),
    ZOOM_TELE(new ZoomTeleStdCmd()),
    ZOOM_WIDE(new ZoomWideStdCmd()),
    WAIT(new WaitCmd());

    private Cmd command;
}
