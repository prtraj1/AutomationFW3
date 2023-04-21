package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogMessage {

    private static Logger log = LogManager.getLogger(LogMessage.class);

    public static void info(String message){
        log.info(message);
    }

    public static void warn(String message){
        log.warn(message);
    }

    public static void error(String message){
        log.error(message);
    }
}
