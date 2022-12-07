package utilities;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

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

    public String getData(int rowNo, int cellNo){
        Row rw = this.sh.getRow(rowNo);
        Cell cl = rw.getCell(cellNo);
        FormulaEvaluator fe = this.wb.getCreationHelper().createFormulaEvaluator();
        return new DataFormatter().formatCellValue(cl, fe);
    }

    public void getRowData(int rowNum){
        if(isRowEmpty(rowNum)){
            System.out.println("Invalid row sent");
            return;
        }
        Row hRow = this.sh.getRow(this.headerRow);
        int lastCellNum = hRow.getLastCellNum();
        List<String> hRowDataList = new ArrayList<>();
        List<String> cRowDataList = new ArrayList<>();
        for(int i = 0; i<lastCellNum; i++){
            hRowDataList.add(getData(this.headerRow, i));
            cRowDataList.add(getData(rowNum, i));
        }
        Map<String, String> dataMap = new LinkedHashMap<>();
        for(int i = 0; i<lastCellNum; i++){
            dataMap.put(hRowDataList.get(i), cRowDataList.get(i));
        }
        System.out.println(dataMap);
    }

    public boolean isRowEmpty(int rowNum){
        Row rw = this.sh.getRow(rowNum);
        return rw == null ? true : false;
    }
}
