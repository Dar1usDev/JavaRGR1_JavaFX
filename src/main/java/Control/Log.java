package Control;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;

/**
 * Logging realisation
 */
public class Log {

    /**
     * false since first log session
     */
    private static boolean clean = false;

    /**
     * Adds a line to Log
     * @param msg Log message
     */
    public static void write(String msg){
        String logLine = LocalDateTime.now() + " MSG: " + msg;
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("src//main//resources//log.txt", clean));
            writer.write(logLine);
            writer.newLine();
            writer.close();
            clean = true;
        }catch(Exception ignored){
            // :)
        }
    }
}
