package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogMessage {

    private static Logger log = LogManager.getLogger();

    public static void info(String message){
        log.info(Thread.currentThread().getStackTrace()[2].getClassName()+ " | "+ message);
    }

    public static void warn(String message){
        log.warn(message);
    }

    public static void error(String message){
        log.error(message);
    }
}
