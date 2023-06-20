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
import java.util.Properties;

public class ApiEmerScreen {
	public static void main(String[] args) {
		
		String configFilePath = "config.ini";
		String urlhost = null;
		String username = null;
		String password = null;
		String apiUrl_emergency_screen = null; 
		String x_api_key = null;
		Connection connection = null;
		String status_sync_old = null;
		
		try {
			FileInputStream propsInput = new FileInputStream(configFilePath);
			Properties prop = new Properties();
			prop.load(propsInput);

			// 1. Connect DB
			urlhost = prop.getProperty("urlhost");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			apiUrl_emergency_screen = prop.getProperty("apiUrl_emergency_screen");
			x_api_key = prop.getProperty("x_api_key");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
		try {
			
			System.out.println(urlhost);
			System.out.println(username);
			System.out.println(password);
			
			connection = DriverManager.getConnection(urlhost, username, password);
			System.out.println("Database connected!");

			
			// 2. Execute SQL
			String SQL1 = "SELECT status_sync FROM efid.sync";
			System.out.println(SQL1);
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SQL1);

			while (resultSet.next()) {
				status_sync_old = resultSet.getString("status_sync");
			}
			System.out.println("status_sync old : " + status_sync_old);

			if (status_sync_old.equals("1")) {
				try {
					System.out.println("call api");
					URL url = new URL(apiUrl_emergency_screen);
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
				} catch (Exception e) {
					System.out.println("Error!: " + e.getMessage());
				}
				System.out.println("status_sync change 1 to 0");
				String SQL2 = "UPDATE efid.sync SET status_sync = '0' WHERE id = 1";
				System.out.println(SQL2);
				statement.executeUpdate(SQL2);
			} else {
				System.out.println("not calling api");
				System.out.println("status_sync 0 not change");
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
