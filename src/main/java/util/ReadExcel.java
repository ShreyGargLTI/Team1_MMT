/**
 * 
 */
package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Shrey
 *
 */
public class ReadExcel
{
	private static File src;
	private static XSSFWorkbook wb;
	private static XSSFSheet sheet;
	private static DataFormatter df;

	public static String[][] readCred(String location)
			throws FileNotFoundException, IOException
	{
		String[][] credTable = null;
		src = new File(location);
		wb = new XSSFWorkbook(new FileInputStream(src));
		sheet = wb.getSheetAt(0);

		int
		// startRow = 0, startCol = 0,
		totalCols = 2, totalRows = sheet.getLastRowNum() + 1;

		credTable = new String[totalRows][totalCols];

		for (int i = 0; i < totalRows; i++)
		{
			for (int j = 0; j < totalCols; j++)
			{
				credTable[i][j] = getCellData(i, j);

				System.out.println(credTable[i][j]);
			}
		}

		return credTable;
	}

	public static String[][] readRegValues(String location)
			throws FileNotFoundException, IOException
	{
		String[][] table = null;
		src = new File(location);
		wb = new XSSFWorkbook(new FileInputStream(src));
		sheet = wb.getSheetAt(0);

		int startRow = 0, startCol = 0, totalCols = 3,
				totalRows = sheet.getLastRowNum() + 1;

		table = new String[totalRows][totalCols];

		for (int i = startRow; i < totalRows; i++)
		{
			for (int j = startCol; j < totalCols; j++)
			{
				table[i][j] = getCellData(i, j);
			}
		}

		return table;
	}

	public static String[][] readfrtpwd(String location)
			throws FileNotFoundException, IOException
	{
		String[][] credTable3 = null;
		src = new File(location);
		wb = new XSSFWorkbook(new FileInputStream(src));
		sheet = wb.getSheetAt(0);

		int startRow = 0, startCol = 0, totalCols = 1,
				totalRows = sheet.getLastRowNum() + 1;

		credTable3 = new String[totalRows][totalCols];

		for (int i = startRow; i < totalRows; i++)
		{
			for (int j = startCol; j < totalCols; j++)
			{
				credTable3[i][j] = getCellData(i, j);
			}
		}

		return credTable3;
	}

	public static String[][] readoneWay(String location)
			throws FileNotFoundException, IOException
	{
		String[][] credTable3 = null;
		src = new File(location);
		wb = new XSSFWorkbook(new FileInputStream(src));
		sheet = wb.getSheetAt(0);

		int startRow = 0, startCol = 0, totalCols = 5,
				totalRows = sheet.getLastRowNum() + 1;

		credTable3 = new String[totalRows][totalCols];

		for (int i = startRow; i < totalRows; i++)
		{
			for (int j = startCol; j < totalCols; j++)
			{
				credTable3[i][j] = getCellData(i, j);
			}
		}

		return credTable3;
	}

	public static String[][] readTwoWay(String path)
			throws FileNotFoundException, IOException
	{
		String[][] credTable3 = null;
		src = new File(path);
		wb = new XSSFWorkbook(new FileInputStream(src));
		sheet = wb.getSheetAt(0);

		int startRow = 0, startCol = 0, totalCols = 6,
				totalRows = sheet.getLastRowNum() + 1;

		credTable3 = new String[totalRows][totalCols];

		for (int i = startRow; i < totalRows; i++)
		{
			for (int j = startCol; j < totalCols; j++)
			{
				credTable3[i][j] = getCellData(i, j);
			}
		}

		return credTable3;

	}

	private static String getCellData(int rowNum, int colNum)
	{

		df = new DataFormatter();
		String cellData = df
				.formatCellValue(sheet.getRow(rowNum).getCell(colNum));

		return cellData;
	}

}