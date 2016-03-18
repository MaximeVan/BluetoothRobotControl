package fr.iut.vanbossm.exceptions;


/**
 * Created by raievskc on 2/14/16.
 */
public class NoAdapterBTHException extends BTHandlingException {
   public NoAdapterBTHException() {
      super("No Bluetooth adapter present on Android device.");
   }
}
