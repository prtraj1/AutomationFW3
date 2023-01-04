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

    public ExcelUtility(String location, int sheetNo){
        setWorkbook(location);
        setSheet(sheetNo);
    }

    public ExcelUtility(File location, int sheetNo){
        setWorkbook(location);
        setSheet(sheetNo);
    }

    public ExcelUtility(File location, String sheetName){
        setWorkbook(location);
        setSheet(sheetName);
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

    public Map<String, String> getRowData(int rowNum){
        Map<String, String> dataMap = new LinkedHashMap<>();
        if(isRowEmpty(rowNum)){
            System.out.println("Invalid row sent");
            return dataMap;
        }
        Row hRow = this.sh.getRow(this.headerRow);
        int lastCellNum = hRow.getLastCellNum();
//        List<String> hRowDataList = new ArrayList<>();
//        List<String> cRowDataList = new ArrayList<>();
//        for(int i = 0; i<lastCellNum; i++){
//            hRowDataList.add(getData(this.headerRow, i));
//            cRowDataList.add(getData(rowNum, i));
//        }
        for(int i = 0; i<lastCellNum; i++){
            if(dataMap.containsKey(getData(this.headerRow, i)))
                continue;
            dataMap.put(getData(this.headerRow, i), getData(rowNum, i));
        }
        return dataMap;
    }

    public boolean isRowEmpty(int rowNum){
        Row rw = this.sh.getRow(rowNum);
        if(rw == null)
            return true;
        if(rw.getLastCellNum()<=0)
            return true;
        for(Cell c: rw){
            String data = getData(rowNum, c.getColumnIndex());
            if(data.length()>0)
                return false;
        }
        return true;
    }

    public List<Integer> getRowNumbersInColumnHavingText(String txt, int colNo){
        int lastRowNum = this.sh.getLastRowNum();
        List<Integer> rowNums = new ArrayList<>();
        for(int i=1; i<=lastRowNum; i++){
            String data = getData(i, colNo);
            if(txt.equalsIgnoreCase(data))
                rowNums.add(i);
        }
        return rowNums;
    }

    public List<Integer> getColNumbersInRowHavingText(String txt, int rowNum){
        List<Integer> colNums = new ArrayList<>();
        if(isRowEmpty(rowNum))
            return colNums;
        Row rw = this.sh.getRow(rowNum);
        for(int i=0; i<rw.getLastCellNum(); i++){
            String data = getData(rowNum, i);
            if(txt.equalsIgnoreCase(data))
                colNums.add(i);
        }
        return colNums;
    }
}
