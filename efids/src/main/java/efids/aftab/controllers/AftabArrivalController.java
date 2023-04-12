package efids.aftab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import efids.aftab.model.AftabArrival;
import efids.aftab.service.AftabArrivalService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class AftabArrivalController {

    @Autowired
    private AftabArrivalService aftabArrivalService;
    
    @GetMapping("aftab/arrival")
	public List<AftabArrival> showArrival() {
		return aftabArrivalService.showArrival();
	}
    
    @GetMapping("aftab/arrival/excel")
    public String createExcelArrival() throws IOException {
    	String fileUrl = "http://10.121.0.19:8099/efids/getArrivalEfids";
    	String saveDir = "D:\\";
    	String fileName = "ARR.xlsx";

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
    
//    @PostMapping("aftab/arrival/excel")
//    public String createExcelArrival(@RequestBody List<AftabArrival> excelArrival) throws IOException {
//        // set file path
//        String filePath = "D:\\ARR.xlsx";
//
//        // create new workbook and sheet
//        Workbook workbook = new XSSFWorkbook();
//        Sheet sheet = workbook.createSheet("Data");
//
//        // create header row
//        Row headerRow = sheet.createRow(0);
//        headerRow.createCell(0).setCellValue("A. ADID");
//        headerRow.createCell(1).setCellValue("A. ORG(IATA)");
//        headerRow.createCell(2).setCellValue("A. FLIGHT");
//        headerRow.createCell(3).setCellValue("A. FLTI");
//        headerRow.createCell(4).setCellValue("A. Ops Stat");
//        headerRow.createCell(5).setCellValue("A. Belt");
//        headerRow.createCell(6).setCellValue("A. Belt2");
//        headerRow.createCell(7).setCellValue("A. Gate");
//        headerRow.createCell(8).setCellValue("A. Gate2");
//        headerRow.createCell(9).setCellValue("A. HOPO");
//        headerRow.createCell(10).setCellValue("A. REMP");
//        headerRow.createCell(11).setCellValue("A. Nature");
//        headerRow.createCell(12).setCellValue("A. VIA");
//        headerRow.createCell(13).setCellValue("A. SIBT");
//
//        // loop through data and add to sheet
////        System.out.println(excelpo.get(0).getAdid());
//        int rowNum = 1;
//        for (AftabArrival data : excelArrival) {
//            Row row = sheet.createRow(rowNum++);
//            
//            row.createCell(0).setCellValue(data.getAdid());
//            row.createCell(1).setCellValue(data.getOrg3());
//            row.createCell(2).setCellValue(data.getFlno());
//            row.createCell(3).setCellValue(data.getFlti());
//            row.createCell(4).setCellValue(data.getFtyp());
//            row.createCell(5).setCellValue(data.getBlt1());
//            row.createCell(6).setCellValue(data.getBlt2());
//            row.createCell(7).setCellValue(data.getGta1());
//            row.createCell(8).setCellValue(data.getGta2());
//            row.createCell(9).setCellValue(data.getHopo());
//            row.createCell(10).setCellValue(data.getRemp());
//            row.createCell(11).setCellValue(data.getTtyp());
//            row.createCell(12).setCellValue(data.getVial());
//            row.createCell(13).setCellValue(data.getStoa());
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

