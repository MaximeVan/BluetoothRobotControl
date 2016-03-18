package fr.iut.vanbossm.exceptions;

/**
 * Created by raievskc on 2/14/16.
 */
public class NoBTDeviceFoundException extends BTHandlingException {
   public NoBTDeviceFoundException() {
      super("No Bluetooth device found");
   }
}
