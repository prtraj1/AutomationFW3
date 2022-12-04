package utilities;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtility {

    private Workbook wb;

    private Sheet sh;

    private int headerRow = 0;

    public ExcelUtility(String location, String sheetName){
        setWorkbook(location);
        setSheet(sheetName);
    }

    public ExcelUtility(File location, int sheetNo){
        setWorkbook(location);
        setSheet(sheetNo);
    }

    private void setWorkbook(Object location){
        FileInputStream fis = null;
        try{
            if(location instanceof String){
                fis = new FileInputStream((String) location);
            }else if(location instanceof File){
                fis = new FileInputStream((File) location);
            }
            this.wb = WorkbookFactory.create(fis);
        }catch (Exception e){
            System.out.println("There was some problem setting excel workbook");
        }
    }

    private void setSheet(Object sheet){
        if(sheet instanceof String)
            this.sh = this.wb.getSheet((String)sheet);
        else if(sheet instanceof Integer)
            this.sh = this.wb.getSheetAt((int)sheet);
    }

    public void setHeaderRow(int rowNum){
        this.headerRow = rowNum;
    }

    public int getHeaderRow(){
        return this.headerRow;
    }
}
