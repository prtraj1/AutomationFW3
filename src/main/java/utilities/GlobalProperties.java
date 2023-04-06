package utilities;

import java.io.File;

public class GlobalProperties {

    private static File file = new File("assets/config.properties");
    private static Props props = new Props(file);

    public static String getProperty(String property){
        try{
            return props.getKey(property).trim();
        }catch (Exception e){
            return null;
        }
    }
}
