package efids.aftab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import efids.aftab.model.AftabDeparture;
import efids.aftab.service.AftabDepartureService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class AftabDepartureController {

	@Autowired
	private AftabDepartureService aftabDepartureService;

	@GetMapping("aftab/departure")
	public List<AftabDeparture> showDeparture() {
		return aftabDepartureService.showDeparture();
	}

	@GetMapping("aftab/departure/excel")
	public String createExcelArrival() throws IOException {
		String fileUrl = "http://10.121.0.19:8099/efids/getDepartureEfids";
		String saveDir = "D:\\";
		String fileName = "DEP.xlsx";

		URL url = new URL(fileUrl);
		InputStream inputStream = url.openStream();
		FileOutputStream outputStream = new FileOutputStream(saveDir + fileName);

		byte[] buffer = new byte[1024];
		int length;

		while ((length = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, length);
		}

		inputStream.close();
		outputStream.close();

		// return success message
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		return "Excel file saved successfully at " + saveDir + fileName + " on " + dtf.format(now);
	}

//    @PostMapping("aftab/departure/excel")
//    public String createExcelDeparture(@RequestBody List<AftabDeparture> excelDeparture) throws IOException {
//        // set file path
//        String filePath = "D:\\DEP.xlsx";
//
//        // create new workbook and sheet
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("Data");
//
//        // create header row
//        Row headerRow = sheet.createRow(0);
//        headerRow.createCell(0).setCellValue("D. ADID");
//        headerRow.createCell(1).setCellValue("D. DEST(IATA)");
//        headerRow.createCell(2).setCellValue("D. FLIGHT");
//        headerRow.createCell(3).setCellValue("D. FLTI");
//        headerRow.createCell(4).setCellValue("D. Ops Stat");
//        headerRow.createCell(5).setCellValue("D. CIC From");
//        headerRow.createCell(6).setCellValue("D. CIC To");
//        headerRow.createCell(7).setCellValue("D. Gate");
//        headerRow.createCell(8).setCellValue("D. Gate2");
//        headerRow.createCell(9).setCellValue("D. HOPO");
//        headerRow.createCell(10).setCellValue("D. REMP");
//        headerRow.createCell(11).setCellValue("D. Nature");
//        headerRow.createCell(12).setCellValue("D. VIA");
//        headerRow.createCell(13).setCellValue("D. SOBT");
//
//        // loop through data and add to sheet
////        System.out.println(excelpo.get(0).getAdid());
//        int rowNum = 1;
//        for (AftabDeparture data : excelDeparture) {
//            Row row = sheet.createRow(rowNum++);
//            
//            row.createCell(0).setCellValue(data.getAdid());
//            row.createCell(1).setCellValue(data.getDes3());
//            row.createCell(2).setCellValue(data.getFlno());
//            row.createCell(3).setCellValue(data.getFlti());
//            row.createCell(4).setCellValue(data.getFtyp());
//            row.createCell(5).setCellValue(data.getCkif());
//            row.createCell(6).setCellValue(data.getCkit());
//            row.createCell(7).setCellValue(data.getGtd1());
//            row.createCell(8).setCellValue(data.getGtd2());
//            row.createCell(9).setCellValue(data.getHopo());
//            row.createCell(10).setCellValue(data.getRemp());
//            row.createCell(11).setCellValue(data.getTtyp());
//            row.createCell(12).setCellValue(data.getVial());
//            row.createCell(13).setCellValue(data.getStod());
//            
//        }
//
//        // save workbook to file
//        FileOutputStream fileOut = new FileOutputStream(filePath);
//        workbook.write(fileOut);
//        fileOut.close();
//        workbook.close();
//
//        // return success message
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
//        return "Excel file saved successfully at " + filePath + " on " + dtf.format(now);
//    }

}
