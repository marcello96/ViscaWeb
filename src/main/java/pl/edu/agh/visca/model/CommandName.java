package pl.edu.agh.visca.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.edu.agh.visca.cmd.*;

@AllArgsConstructor
@Getter
public enum CommandName {
    ADDRESS(new AddressCmd()),
    CLEAR_ALL(new ClearAllCmd()),
    GET_MAX_SPEED(new GetPanTiltMaxSpeedCmd()),
    DOWN(new PanTiltDownCmd()),
    HOME(new PanTiltHomeCmd()),
    LEFT(new PanTiltLeftCmd()),
    RIGHT(new PanTiltRightCmd()),
    UP(new PanTiltUpCmd()),
    ZOOM_TELE(new ZoomTeleStdCmd()),
    ZOOM_WIDE(new ZoomWideStdCmd()),
    WAIT(new WaitCmd()),
    SET_DEST(new SetDestCmd()),
    CHANGE_ADDRESS(new ChangeAddressCmd());

    private Cmd command;
}
