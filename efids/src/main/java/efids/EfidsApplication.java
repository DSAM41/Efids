package efids;

import java.io.FileInputStream;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class EfidsApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		String configFilePath = "./efidsWar.ini";
		//String configFilePath = "efidsWar.ini";
		System.out.println("okl");
		try {
			FileInputStream propsInput = new FileInputStream(configFilePath);
			Properties prop = new Properties();
			prop.load(propsInput);

			String mysqlUrl = prop.getProperty("spring.datasource.mysql.url");
			String mysqlUsername = prop.getProperty("spring.datasource.mysql.username");
			String mysqlPassword = prop.getProperty("spring.datasource.mysql.password");
			System.setProperty("mysqlUrl", mysqlUrl);
			System.setProperty("mysqlUsername", mysqlUsername);
			System.setProperty("mysqlPassword", mysqlPassword);

			String oraclelUrl = prop.getProperty("spring.datasource.oracle.url");
			String oracleUsername = prop.getProperty("spring.datasource.oracle.username");
			String oraclePassword = prop.getProperty("spring.datasource.oracle.password");
			System.setProperty("oraclelUrl", oraclelUrl);
			System.setProperty("oracleUsername", oracleUsername);
			System.setProperty("oraclePassword", oraclePassword);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return application.sources(EfidsApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(EfidsApplication.class, args);
	}

}
