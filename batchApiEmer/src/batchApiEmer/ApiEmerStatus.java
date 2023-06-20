package batchApiEmer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Properties;

import org.json.JSONObject;

public class ApiEmerStatus {

	public static void main(String[] args) {
		
		String configFilePath = "config.ini";
		String urlhost = null;
		String username = null;
		String password = null;
		String apiUrl_emergency_status = null; 
		String x_api_key = null;
		
		try {
			FileInputStream propsInput = new FileInputStream(configFilePath);
			Properties prop = new Properties();
			prop.load(propsInput);

			// 1. Connect DB
			urlhost = prop.getProperty("urlhost");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			apiUrl_emergency_status = prop.getProperty("apiUrl_emergency_status");
			x_api_key = prop.getProperty("x_api_key");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		try {
			URL url = new URL(apiUrl_emergency_status);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("x-api-key", x_api_key);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			// Get the value of "efid_enforce" from the response JSON
			JSONObject jsonResponse = new JSONObject(response.toString());
			boolean efidEnforce = jsonResponse.getBoolean("efid_enforce");
			// Print the value of "efid_enforce"
			System.out.println("efid_enforce value: " + efidEnforce);


			
			Connection connection = null;
			String status_emer_old = null;
			String status_emer_new = null;
			boolean status_emer_check;
			
			System.out.println(urlhost);
			System.out.println(username);
			System.out.println(password);
			

			
			try {
				connection = DriverManager.getConnection(urlhost, username, password);
				System.out.println("Database connected!");

				// 2. Execute SQL
				String SQL1 = "SELECT status_emer FROM efid.sync";
				System.out.println(SQL1);
				
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(SQL1);

				while (resultSet.next()) {
					status_emer_old = resultSet.getString("status_emer");
				}
				System.out.println("status_emer old : " + status_emer_old);
				
				// แปลงค่า true เป็น  1 เพื่อทำไปอัพเดท
				if (efidEnforce) {
					status_emer_new = "1";
				} else {
					status_emer_new = "0";
				}
				
				// แปลงค่า 1  เป็น  true เพื่อนำไปเทียบ
				if (status_emer_old.equals("1")) {
					status_emer_check = true;
				} else {
					status_emer_check = false;
				}

				if (status_emer_check != efidEnforce) {
					System.out.println("status_emer change");
					String SQL2 = "UPDATE efid.sync SET status_emer = '" + status_emer_new + "' WHERE id = 1";
					System.out.println(SQL2);
					statement.executeUpdate(SQL2);
					if (efidEnforce == true) {
						String SQL3 = "UPDATE efid.sync SET emer_offlast = '" + LocalDateTime.now() + "' WHERE id = 1";
						System.out.println(SQL3);
						statement.executeUpdate(SQL3);
					} else {
						String SQL3 = "UPDATE efid.sync SET emer_onlast = '" + LocalDateTime.now() + "' WHERE id = 1";
						System.out.println(SQL3);
						statement.executeUpdate(SQL3);
					}
				} else {
					System.out.println("status_emer not change");
				}
			} catch (SQLException e) {
				throw new RuntimeException("Cannot connect the database!", e);
			}

		} catch (Exception e) {
			System.out.println("Error!: " + e.getMessage());
		}

	}

}
