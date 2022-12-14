package util;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
public static Object[][] fetchDataFromExcel(String filePath, String sheetName, String methodname){
		
		Map<Object, Object> datamap= new HashMap<Object, Object>();
		Object[][] obj = null;
		try{
			
			FileInputStream file = new FileInputStream(filePath);
			XSSFWorkbook wb = new XSSFWorkbook(file);
			XSSFSheet sh = wb.getSheet(sheetName);
			XSSFRow rw = sh.getRow(0);
			
			int totalRows = sh.getLastRowNum();
			//System.out.println(totalRows);
			//int cellNum = rw.getLastCellNum();
			//System.out.println(cellNum);
			
			//obj = new Object[totalRows][1];
			obj = new Object[1][1];
				
				for(int i=0;i<=totalRows;i++) {								
												
					XSSFRow tstrw = sh.getRow(i);
					String txt = tstrw.getCell(0).getStringCellValue();
					
					if(txt.trim().equalsIgnoreCase(methodname.trim())) {
						int cellNum = tstrw.getLastCellNum();
						datamap = new HashMap<Object, Object>();
						
						for(int j=0;j<cellNum-1;j++) {
														
							datamap.put(rw.getCell(j+1).toString(), sh.getRow(i).getCell(j+1).toString());
							System.out.println("Map values:" + datamap);
						}	
						
						obj[0][0] = datamap;
						//System.out.println("obj[0][0] = " + obj[0][0]);

					}
					
				}
			
			wb.close();
			
		}catch(Exception fetchDataFromExcel) {
			
			fetchDataFromExcel.printStackTrace();
			
		}
		
		return obj;
		
	}

public static Object[][] singleTestCaseMultipleTimes(String filePath, String sheetName, String methodname){
	
	Map<Object, Object> datamap= new HashMap<Object, Object>();
	Object[][] obj = null;
	try{
		
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sh = wb.getSheet(sheetName);
		XSSFRow rw = sh.getRow(0);
		
		int totalRows = sh.getLastRowNum();
		//System.out.println(totalRows);
		//int cellNum = rw.getLastCellNum();
		//System.out.println(cellNum);
		
		obj = new Object[totalRows][1];
		//obj = new Object[1][1];
			
			for(int i=0;i<=totalRows;i++) {								
											
				XSSFRow tstrw = sh.getRow(i);
				String txt = tstrw.getCell(0).getStringCellValue();
				
				if(txt.trim().equalsIgnoreCase(methodname.trim())) {
					
					datamap = new HashMap<Object, Object>();
					int cellNum = tstrw.getLastCellNum();
					for(int j=0;j<cellNum-1;j++) {
													
						datamap.put(rw.getCell(j+1).toString(), sh.getRow(i).getCell(j+1).toString());
						System.out.println("Map values:" + datamap);
					}	
					
					obj[i-1][0] = datamap;
					//System.out.println("obj["+i+"-1][0] = " + obj[i-1][0]);

				}
				
			}
		
		wb.close();
		
	}catch(Exception fetchDataFromExcel) {
		
		fetchDataFromExcel.printStackTrace();
		
	}
	
	return obj;
	
}

}
