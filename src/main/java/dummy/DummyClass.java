package dummy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.checker.units.qual.C;
import utilities.ExcelUtility;
import utilities.LogMessage;
import utilities.Props;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class DummyClass {

    public static void main(String[] args) {
//        String location = "assets/Runner.xlsx";
//        File rnLocation = new File(location);
//
//        ExcelUtility exc1 = new ExcelUtility(location, "Sheet1");
//        System.out.println(exc1.getHeaderRow());
//        exc1.setHeaderRow(3);
//        System.out.println(exc1.getHeaderRow());
//        ExcelUtility exc2 = new ExcelUtility(rnLocation, 0);

//        System.out.println(exc1.getData(0, 1));

//        Map<String, String> data = new LinkedHashMap<>();
//        data.put("name", "sadia");
//        data.put("add", "hwh");
//        System.out.println(data);
//        System.out.println(data.get("name") + "--" + data.get("add"));

//        List<Integer> numsA = Arrays.asList(1,2,1,2,3,3,4);
//        System.out.println(numsA);
//
//        Set<Integer> numsB = new HashSet<>(numsA);
//        System.out.println(numsB);

//        System.out.println(exc1.getRowData(1));

//        if(!false){
//            System.out.println("Inside if");
//        }else{
//            System.out.println("Inside else");
//        }

//        String text = "     ";
//        System.out.println(text.length());

//        System.out.println(exc1.isRowEmpty(7));

//        ExcelUtility exc2 = new ExcelUtility("assets/data.xlsx", 0);
//        System.out.println(exc2.getRowNumbersInColumnHavingText("pwd1234", 2));
//        System.out.println(exc2.getColNumbersInRowHavingText("Username", 5));

//        String[][] data = new String[10][1];
//        data[0][0] = "something";
//        data[0][1] = "new";
//
//        List<Integer> nums = Arrays.asList(2,3);

//        //Declaring an array (type 01)
//        int[] numbers = new int[]{90,89,111,110}; //1 Row and 4 columns --> linear array
//        int[][] twoDnumbers = new int[][]{
//                {90,89,111,110},
//                {90,89,101,711}
//        }; //multiple rows and columns --> 2D array
//
//        //Declaring an array (type 02)
//        int[] n1 = new int[3];
//        n1[1] = 90;
//        n1[2] = 89;
//        for(int i=0; i<n1.length; i++){
//            System.out.println(n1[i]);
//        }
//
//        String[] s1 = new String[3];
//        s1[1] = "anything";
//        s1[2] = "demo";
//        for(int i=0; i<n1.length; i++){
//            System.out.println(s1[i]);
//        }
//
//        int[][] n2 = new int[3][2];
//        n2[0][1] = 10;
//        n2[2][1] = 22;
//        System.out.println(n2.length);
//
//        for (int i=0; i<n2.length; i++){
//            for(int j = 0; j< n2[i].length; j++){
//                System.out.println(n2[i][j]);
//            }
//        }


//        Map<String, String> testMap = new HashMap<>();
//        testMap.put("Name", "Sadia");
//        if(!testMap.containsKey("Name"))
//            testMap.put("Name", "Nasim");
//        System.out.println(testMap.containsKey("Name"));
//        System.out.println(testMap.get("Name"));

//        File file = new File("assets/config.properties");
//        File file2 = new File("assets/report.properties");

//        Properties prop = new Properties();
//        try {
//            prop.load(new FileInputStream(file));
//            System.out.println(prop.get("browser"));
//            System.out.println(prop.get("something"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        Properties prop1 = new Properties();
//        try {
//            prop.load(new FileInputStream(file));
//            System.out.println(prop.get("browser"));
//            System.out.println(prop.get("something"));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        Props props = new Props(file2);
//        System.out.println(props.getKey("address"));

//        String text = "You know Howrah is a very beautiful city. I live and study in HoWrah . I study at HowraH Public School.";
//        String[] words = text.split(" ");
//        String wordToCheck = "HoWrah";
//        System.out.println(Arrays.toString(words));
//        Map<String, Integer> wordsCounter = new LinkedHashMap<>();
//
//        for(int i=0; i< words.length; i++){
//            if(wordToCheck.equalsIgnoreCase(words[i])){
//                if(wordsCounter.containsKey(words[i].toLowerCase())){
//                    wordsCounter.put(words[i].toLowerCase(), wordsCounter.get(words[i].toLowerCase()) + 1);
//                }else
//                    wordsCounter.put(words[i].toLowerCase(), 1);
//            }
//        }
//
//        System.out.println(wordsCounter);

//        Logger log = LogManager.getLogger(DummyClass.class);
//        log.info("This is a log");

        LogMessage.info("I am from Sparta");
        LogMessage.warn("I am a warning");
        LogMessage.error("I am an error");
    }
}
