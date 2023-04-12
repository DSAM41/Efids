package efids.aftab.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CEDAAODB.AFTTAB")
public class AftabArrival {
	
	//Arrival
	@Id
	@Column(name = "URNO")
	private String urno;
	@Column(name = "ADID")
	private String adid;
	@Column(name = "ORG3")
	private String org3;
	@Column(name = "FLNO")
	private String flno;
	@Column(name = "FLTI")
	private String flti;
	@Column(name = "FTYP")
	private String ftyp;
	@Column(name = "BLT1")
	private String blt1;
	@Column(name = "BLT2")
	private String blt2;
	@Column(name = "GTA1")
	private String gta1;
	@Column(name = "GTA2")
	private String gta2;
	@Column(name = "HOPO")
	private String hopo;
	@Column(name = "REMP")
	private String remp;
	@Column(name = "TTYP")
	private String ttyp;
	@Column(name = "VIAL")
	private String vial;
	@Column(name = "STOA")
	private String stoa;

	public AftabArrival() {
	}

	public AftabArrival(String urno, String adid, String org3, String flno, String flti, String ftyp, String blt1,
			String blt2, String gta1, String gta2, String hopo, String remp, String ttyp, String vial, String stoa) {
		super();
		this.urno = urno;
		this.adid = adid;
		this.org3 = org3;
		this.flno = flno;
		this.flti = flti;
		this.ftyp = ftyp;
		this.blt1 = blt1;
		this.blt2 = blt2;
		this.gta1 = gta1;
		this.gta2 = gta2;
		this.hopo = hopo;
		this.remp = remp;
		this.ttyp = ttyp;
		this.vial = vial;
		this.stoa = stoa;
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

	public String getOrg3() {
		return org3;
	}

	public void setOrg3(String org3) {
		this.org3 = org3;
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

	public String getBlt1() {
		return blt1;
	}

	public void setBlt1(String blt1) {
		this.blt1 = blt1;
	}

	public String getBlt2() {
		return blt2;
	}

	public void setBlt2(String blt2) {
		this.blt2 = blt2;
	}

	public String getGta1() {
		return gta1;
	}

	public void setGta1(String gta1) {
		this.gta1 = gta1;
	}

	public String getGta2() {
		return gta2;
	}

	public void setGta2(String gta2) {
		this.gta2 = gta2;
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

	public String getStoa() {
		return stoa;
	}

	public void setStoa(String stoa) {
		this.stoa = stoa;
	}

	
	
	
	
}
