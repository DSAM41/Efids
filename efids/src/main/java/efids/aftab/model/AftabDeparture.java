package efids.aftab.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CEDAAODB.AFTTAB")
public class AftabDeparture {
	
	//Departure
	@Id
	@Column(name = "URNO")
	private String urno;
	@Column(name = "ADID")
	private String adid;
	@Column(name = "DES3")
	private String des3;
	@Column(name = "FLNO")
	private String flno;
	@Column(name = "FLTI")
	private String flti;
	@Column(name = "FTYP")
	private String ftyp;
	@Column(name = "CKIF")
	private String ckif;
	@Column(name = "CKIT")
	private String ckit;
	@Column(name = "GTD1")
	private String gtd1;
	@Column(name = "GTD2")
	private String gtd2;
	@Column(name = "HOPO")
	private String hopo;
	@Column(name = "REMP")
	private String remp;
	@Column(name = "TTYP")
	private String ttyp;
	@Column(name = "VIAL")
	private String vial;
	@Column(name = "STOD")
	private String stod;
	
	public AftabDeparture () {
	
	}

	public AftabDeparture(String urno, String adid, String des3, String flno, String flti, String ftyp, String ckif,
			String ckit, String gtd1, String gtd2, String hopo, String remp, String ttyp, String vial, String stod) {
		super();
		this.urno = urno;
		this.adid = adid;
		this.des3 = des3;
		this.flno = flno;
		this.flti = flti;
		this.ftyp = ftyp;
		this.ckif = ckif;
		this.ckit = ckit;
		this.gtd1 = gtd1;
		this.gtd2 = gtd2;
		this.hopo = hopo;
		this.remp = remp;
		this.ttyp = ttyp;
		this.vial = vial;
		this.stod = stod;
	}

	public String getUrno() {
		return urno;
	}

	public void setUrno(String urno) {
		this.urno = urno;
	}

	public String getAdid() {
		return adid;
	}

	public void setAdid(String adid) {
		this.adid = adid;
	}

	public String getDes3() {
		return des3;
	}

	public void setDes3(String des3) {
		this.des3 = des3;
	}

	public String getFlno() {
		return flno;
	}

	public void setFlno(String flno) {
		this.flno = flno;
	}

	public String getFlti() {
		return flti;
	}

	public void setFlti(String flti) {
		this.flti = flti;
	}

	public String getFtyp() {
		return ftyp;
	}

	public void setFtyp(String ftyp) {
		this.ftyp = ftyp;
	}

	public String getCkif() {
		return ckif;
	}

	public void setCkif(String ckif) {
		this.ckif = ckif;
	}

	public String getCkit() {
		return ckit;
	}

	public void setCkit(String ckit) {
		this.ckit = ckit;
	}

	public String getGtd1() {
		return gtd1;
	}

	public void setGtd1(String gtd1) {
		this.gtd1 = gtd1;
	}

	public String getGtd2() {
		return gtd2;
	}

	public void setGtd2(String gtd2) {
		this.gtd2 = gtd2;
	}

	public String getHopo() {
		return hopo;
	}

	public void setHopo(String hopo) {
		this.hopo = hopo;
	}

	public String getRemp() {
		return remp;
	}

	public void setRemp(String remp) {
		this.remp = remp;
	}

	public String getTtyp() {
		return ttyp;
	}

	public void setTtyp(String ttyp) {
		this.ttyp = ttyp;
	}

	public String getVial() {
		return vial;
	}

	public void setVial(String vial) {
		this.vial = vial;
	}

	public String getStod() {
		return stod;
	}

	public void setStod(String stod) {
		this.stod = stod;
	}

	

}
