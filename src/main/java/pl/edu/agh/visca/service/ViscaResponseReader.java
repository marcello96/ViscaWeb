//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.visca.service;

import jssc.SerialPort;
import jssc.SerialPortException;
import org.springframework.stereotype.Service;
import pl.edu.agh.visca.service.exception.TimeoutException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ViscaResponseReader {
    private static final long TIMEOUT_MS = 5000L;

    public byte[] readResponse(SerialPort serialPort) throws SerialPortException, TimeoutException {
        List<Byte> data = new ArrayList<>();
        long startTime = System.currentTimeMillis();

        long timeDiff;
        do {
            while (serialPort.getInputBufferBytesCount() != 0) {
                byte[] responseData = serialPort.readBytes(1);
                Byte b = responseData[0];
                data.add(b);
                if (b == -1) {
                    responseData = new byte[data.size()];
                    int idx = 0;

                    for (Iterator var7 = data.iterator(); var7.hasNext(); responseData[idx++] = b) {
                        b = (Byte) var7.next();
                    }

                    return responseData;
                }
            }

            long currentTime = System.currentTimeMillis();
            timeDiff = currentTime - startTime;
        } while (timeDiff <= TIMEOUT_MS);

        throw new TimeoutException("Waiting too loong");
    }
}
