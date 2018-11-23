package pl.edu.agh.visca.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.edu.agh.visca.cmd.*;

@AllArgsConstructor
@Getter
public enum CommandName {
    ADDRESS {
        @Override
        public Cmd getCommand() {
            return new AddressCmd();
        }
    },
    CLEAR_ALL {
        @Override
        public Cmd getCommand() {
            return new ClearAllCmd();
        }
    },
    GET_MAX_SPEED {
        @Override
        public Cmd getCommand() {
            return new GetPanTiltMaxSpeedCmd();
        }
    },
    DOWN {
        @Override
        public Cmd getCommand() {
            return new PanTiltDownCmd();
        }
    },
    HOME {
        @Override
        public Cmd getCommand() {
            return new PanTiltHomeCmd();
        }
    },
    LEFT {
        @Override
        public Cmd getCommand() {
            return new PanTiltLeftCmd();
        }
    },
    RIGHT {
        @Override
        public Cmd getCommand() {
            return new PanTiltRightCmd();
        }
    },
    UP {
        @Override
        public Cmd getCommand() {
            return new PanTiltUpCmd();
        }
    },
    ZOOM_TELE {
        @Override
        public Cmd getCommand() {
            return new ZoomTeleStdCmd();
        }
    },
    ZOOM_WIDE {
        @Override
        public Cmd getCommand() {
            return new ZoomWideStdCmd();
        }
    },
    WAIT {
        @Override
        public Cmd getCommand() {
            return new WaitCmd();
        }
    },
    SET_DEST {
        @Override
        public Cmd getCommand() {
            return new SetDestCmd();
        }
    },
    CHANGE_ADDRESS {
        @Override
        public Cmd getCommand() {
            return new ChangeAddressCmd();
        }
    };

    public abstract Cmd getCommand();
}
