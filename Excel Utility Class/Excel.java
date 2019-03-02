package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel  {
	
	public static String[][] xcelData;
	public static XSSFSheet Sheet;
	public static XSSFWorkbook WBook = null;
	public static XSSFSheet WSheet= null;
	public static XSSFRow Row;
	public static XSSFCell cell;
	public static String fileName;
	public static String excelPath = System.getProperty("user.dir")+"\\src\\TestData\\"; //Here you need to specify the Excel Test Data File path
	
	public static XSSFSheet getXcelSheet(String fileName,String sheetName) {
	    String fpath = excelPath+fileName;
	    //System.out.println("path = "+fpath);
	    File file = new File(fpath);
	    
	    try {
	    	FileInputStream inputStream = new FileInputStream(file);
            WBook = new XSSFWorkbook(inputStream);
            WSheet = WBook.getSheet(sheetName);         
        } catch (Exception e) {         
            e.printStackTrace();
        }
	    
        return WSheet;
    } 
	
	public static String[][] getXcelData(String sheetName) throws Exception {

		Sheet = getXcelSheet(fileName, sheetName);
		//System.out.println("Sheet Name = "+sheetName);
		int rowCount = (Sheet.getLastRowNum()-Sheet.getFirstRowNum())+1;
		//System.out.println("Rows = " + rowCount);
		int colCount = Sheet.getRow(0).getLastCellNum();
		//System.out.println("Columns = " + colCount);
		
		xcelData = new String[rowCount][colCount];

		for (int rCnt = 0; rCnt < rowCount; rCnt++) {
			for (int cCnt = 0; cCnt < colCount; cCnt++) {

				xcelData[rCnt][cCnt] = getCellData(sheetName, rCnt, cCnt);
			}
		}

		return  xcelData;
	}

	public static String getCellData(String Sheet, int row, int col) {

		try {

			int index = WBook.getSheetIndex(Sheet);

			WSheet = WBook.getSheetAt(index);

			Row = WSheet.getRow(row);

			if (Row == null)
				return "";

			cell = Row.getCell(col);
			if (cell == null)
				return "";

			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				return cell.getStringCellValue();

			case Cell.CELL_TYPE_BOOLEAN:
				return String.valueOf(cell.getBooleanCellValue());

			case Cell.CELL_TYPE_BLANK:
				return "";

			case Cell.CELL_TYPE_ERROR:
				return cell.getStringCellValue();

			case Cell.CELL_TYPE_NUMERIC:
				// return String.valueOf(cell.getNumericCellValue());
				if (DateUtil.isCellDateFormatted(cell)) {
					// return String.valueOf(cell.getDateCellValue());
					return String.valueOf(cell.getDateCellValue());
				} else {

					return String.valueOf(cell.getNumericCellValue());
				}
			default:
				return "Cell not found";

			}
		} catch (Exception e) {
			e.printStackTrace();
			return "row " + row + " or column " + col + " does not exist in xls";
		}

	}

	
	public int rowCount(String SheetName)
	{
		 XSSFSheet Sheet;
		 //System.out.println("before getXcelSheet"+excelPath);
		 Sheet = getXcelSheet(fileName, SheetName);
		 //System.out.println("before getXcelSheet"+excelPath);
		int rowCount = (Sheet.getLastRowNum()-Sheet.getFirstRowNum())+1;
		//System.out.println("Row Count : "+rowCount);
		return rowCount;
		
		
	}
	
	public int colCount(String SheetName)
	{
		 XSSFSheet Sheet;
		 Sheet = getXcelSheet(fileName, SheetName);
		 Iterator rowIterator = Sheet.rowIterator();
		 int columnCount=0;
		 
	        if (rowIterator.hasNext())
	        {
	            Row headerRow = (Row) rowIterator.next();
	            //get the number of cells in the header row
	            columnCount = headerRow.getPhysicalNumberOfCells();
	        }
	       // System.out.println("Column Count :  "+columnCount);
			
		return columnCount;
		
		
	}
	
	
	public static void writeToCell(String executionStatus, String sheetName,int rownum, int colnum) throws InvalidFormatException, IOException{

		System.out.println("###"+executionStatus+"; "+sheetName+"; "+rownum+"; "+colnum+"###");
		FileInputStream fis=new FileInputStream(excelPath+fileName);
		System.out.println(excelPath);
		System.out.println(fileName);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh=wb.getSheet(sheetName); 
		
		Row row=sh.getRow(rownum);
		Cell cell=row.createCell(colnum);
		cell.setCellType(Cell.CELL_TYPE_STRING);
		cell.setCellValue(executionStatus);
		FileOutputStream fos=new FileOutputStream(excelPath+fileName);
		wb.write(fos);
		fos.close(); //After writing the test execution results to excel you can choose to close the file if you want OR according to your need
		
	}

}


