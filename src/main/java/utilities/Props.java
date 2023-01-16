package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Props {

    private Properties props = new Properties();

    public Props(String filePath){
        try {
            props.load(new FileInputStream(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Props(File file){
        try {
            props.load(new FileInputStream(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getKey(String property){
        return props.getProperty(property);
    }
}
