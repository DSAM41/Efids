package batchExportEFIDS;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.sql.*;

public class ExportArr {

	public static void main(String[] args) throws IOException {
		String OutputPath = "D:/";
		String OutputFileName = "ARR.xlsx";
		String backperiod = "1";
		String frontperiod = "2";
		String configFilePath = "configArr.ini";
		try {
			FileInputStream propsInput = new FileInputStream(configFilePath);
			Properties prop = new Properties();
			prop.load(propsInput);
			OutputPath = prop.getProperty("outputpath");
			OutputFileName = prop.getProperty("outputfilename");
			backperiod = prop.getProperty("backperiod");
			frontperiod = prop.getProperty("frontperiod");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		String SQL = "SELECT URNO\r\n" 
				+ "    , ADID\r\n" 
				+ "    , ORG3\r\n" 
				+ "    , FLNO\r\n" 
				+ "    , FLTI\r\n"
				+ "    , FTYP\r\n" 
				+ "    , BLT1\r\n" 
				+ "    , BLT2\r\n" 
				+ "    , GTA1\r\n" 
				+ "    , GTA2\r\n"
				+ "    , HOPO\r\n" 
				+ "     , REMP\r\n"
				+ "    , CASE WHEN UPPER(TTYP) = 'NULL' THEN '' ELSE TTYP END AS TTYP\r\n"
				+ "    , REGEXP_REPLACE(SUBSTR(VIAL, 1, 4) , '[^0-9A-Za-z()./%,:;#&-/+ ]', ' ') AS VIAL\r\n"
				+ "    , STOA, to_date(STOA,'yyyymmddhh24miss')+(7/24) as STOA_LOCAL \r\n" + "\r\n"
				+ "FROM CEDAAODB.AFTTAB\r\n"
				+ "WHERE HOPO = 'BKK' AND ADID = 'A' AND STOA BETWEEN  to_char(trunc(sysdate-" + backperiod
				+ "),'yyyymmddhh24miss') AND to_char(trunc(sysdate+" + frontperiod + "),'yyyymmddhh24miss')";

		System.out.println(SQL);

		// 1. Connect DB
		Connection connection = null;
		String url = "jdbc:oracle:thin:@10.74.26.128:1521/ufisuat";
		String username = "cedarepdb";
		String password = "CEDA";

		try {
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Database connected!");

			// 2. execut SQL
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL);
			// ResultSetMetaData rsmd = resultSet.getMetaData();
			// int columnCount = rsmd.getColumnCount();
			// for (int i = 1; i <= columnCount; i++) {
			// String columnName = rsmd.getColumnName(i);
			// System.out.println(columnName);
			// }

			// 3. generate Excel
			// create new workbook and sheet
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("Data");

			// create header row
			Row headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("A. ADID");
			headerRow.createCell(1).setCellValue("A. ORG(IATA)");
			headerRow.createCell(2).setCellValue("A. FLIGHT");
			headerRow.createCell(3).setCellValue("A. FLTI");
			headerRow.createCell(4).setCellValue("A. Ops Stat");
			headerRow.createCell(5).setCellValue("A. Belt");
			headerRow.createCell(6).setCellValue("A. Belt2");
			headerRow.createCell(7).setCellValue("A. Gate");
			headerRow.createCell(8).setCellValue("A. Gate2");
			headerRow.createCell(9).setCellValue("A. HOPO");
			headerRow.createCell(10).setCellValue("A. REMP");
			headerRow.createCell(11).setCellValue("A. Nature");
			headerRow.createCell(12).setCellValue("A. VIA");
			headerRow.createCell(13).setCellValue("A. SIBT");
			headerRow.createCell(14).setCellValue("A. SIBT_LOCAL");

			// loop through data and add to sheet
			int rowNum = 1;
			while (resultSet.next()) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(resultSet.getString("ADID"));
				row.createCell(1).setCellValue(resultSet.getString("ORG3"));
				row.createCell(2).setCellValue(resultSet.getString("FLNO"));
				row.createCell(3).setCellValue(resultSet.getString("FLTI"));
				row.createCell(4).setCellValue(resultSet.getString("FTYP"));
				row.createCell(5).setCellValue(resultSet.getString("BLT1"));
				row.createCell(6).setCellValue(resultSet.getString("BLT2"));
				row.createCell(7).setCellValue(resultSet.getString("GTA1"));
				row.createCell(8).setCellValue(resultSet.getString("GTA2"));
				row.createCell(9).setCellValue(resultSet.getString("HOPO"));
				row.createCell(10).setCellValue(resultSet.getString("REMP"));
				row.createCell(11).setCellValue(resultSet.getString("TTYP"));
				row.createCell(12).setCellValue(resultSet.getString("VIAL"));
				row.createCell(13).setCellValue(resultSet.getString("STOA"));
				row.createCell(14).setCellValue(resultSet.getString("STOA_LOCAL"));
			}

			// save workbook to file
			FileOutputStream fileOut = new FileOutputStream(OutputPath + OutputFileName);
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();

		} catch (SQLException e) {
			throw new RuntimeException("Cannot connect the database!", e);
		}

	}

}
