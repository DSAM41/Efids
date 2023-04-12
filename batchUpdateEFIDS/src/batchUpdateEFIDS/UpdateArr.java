package batchUpdateEFIDS;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class UpdateArr {

	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, IOException {
		String filePath = null;
		String urlFidsairport = null;
		String urlCreateall = null;
		JSONArray fidsairport = null;
		
		String configFilePath = "configArr.ini";
		
		try {
			FileInputStream propsInput = new FileInputStream(configFilePath);
			Properties prop = new Properties();
			prop.load(propsInput);
			filePath = prop.getProperty("filePath");
			urlFidsairport = prop.getProperty("urlFidsairport");
			urlCreateall = prop.getProperty("urlCreateall");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		

		try {
			// เรียกใช้ API ด้วย HttpURLConnection
			// URL url = new URL("http://localhost:8080/fidsairport/all");
			URL url = new URL(urlFidsairport);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			// อ่าน response จาก API และเก็บไว้ใน StringBuilder
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// แปลง response เป็น JSONArray
			fidsairport = new JSONArray(response.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}


		// สร้าง JSONArray สำหรับเก็บ JSONObject ของแต่ละแถว
		JSONArray jsonArray = new JSONArray();

		// สร้างออบเจ็กต์ Workbook จากไฟล์ Excel
		try (Workbook workbook = WorkbookFactory.create(new File(filePath))) {

			// เลือก Sheet ที่ต้องการอ่านข้อมูล
			Sheet sheet = workbook.getSheetAt(0);

			// วนลูปอ่านข้อมูลจากเซลล์แต่ละเซลล์และเก็บไว้ใน JSONArray
			for (Row row : sheet) {
				if (row.getRowNum() == 0) {
					continue; // ข้ามหัวตาราง
				}

				String time = row.getCell(14).getStringCellValue();
				String from = row.getCell(1).getStringCellValue();
				String flight = row.getCell(2).getStringCellValue();
				String belt1 = row.getCell(5).getStringCellValue();
				String belt2 = row.getCell(6).getStringCellValue();
				String nature = row.getCell(11).getStringCellValue();
				String type = row.getCell(3).getStringCellValue();
				String remark = row.getCell(10).getStringCellValue();

				String target = from;
				for (int i = 0; i < fidsairport.length(); i++) {
					JSONObject obj = fidsairport.getJSONObject(i);
					String apcthree = obj.getString("apcthree");
					if (apcthree.equals(target)) {
						from = obj.getString("apsn");
					}
				}

				if (type == "D") {
					type = "DOM";
				} else {
					type = "INT";
				}

				if (!belt2.trim().isEmpty()) {
					belt1 = belt1.trim() + "," + belt2;
				}

				// สร้าง JSONObject และเพิ่มข้อมูลลงใน JSONObject
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("time", time);
				jsonObject.put("from", from);
				jsonObject.put("flight", flight);
				jsonObject.put("belt", belt1);
				jsonObject.put("nature", nature);
				jsonObject.put("type", type);
				jsonObject.put("remark", remark);
				jsonArray.put(jsonObject);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		// String urlCreateall = "http://localhost:8080/arr/createall";
		String requestBody = jsonArray.toString();

		// System.out.println(requestBody);

		URL url = new URL(urlCreateall);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");

		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(requestBody);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

        System.out.println(response.toString());

	}
}
