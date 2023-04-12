package efids.aftab.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FIDS_AIRPORT")
public class FidsAirport {
	
	//Arrivals
	@Id
	@Column(name = "APC3")
	private String apcthree;
	@Column(name = "APSN")
	private String apsn;
	@Column(name = "APSN2")
	private String apsn2;
	@Column(name = "WEATHER")
	private String weather;
	@Column(name = "APSN3")
	private String apsn3;
	@Column(name = "APC4")
	private String apc4;


	public FidsAirport() {
	}


	public FidsAirport(String apcthree, String apsn, String apsn2, String weather, String apsn3, String apc4) {
		super();
		this.apcthree = apcthree;
		this.apsn = apsn;
		this.apsn2 = apsn2;
		this.weather = weather;
		this.apsn3 = apsn3;
		this.apc4 = apc4;
	}


	public String getApcthree() {
		return apcthree;
	}


	public void setApcthree(String apcthree) {
		this.apcthree = apcthree;
	}


	public String getApsn() {
		return apsn;
	}


	public void setApsn(String apsn) {
		this.apsn = apsn;
	}


	public String getApsn2() {
		return apsn2;
	}


	public void setApsn2(String apsn2) {
		this.apsn2 = apsn2;
	}


	public String getWeather() {
		return weather;
	}


	public void setWeather(String weather) {
		this.weather = weather;
	}


	public String getApsn3() {
		return apsn3;
	}


	public void setApsn3(String apsn3) {
		this.apsn3 = apsn3;
	}


	public String getApc4() {
		return apc4;
	}


	public void setApc4(String apc4) {
		this.apc4 = apc4;
	}


	
	
}
