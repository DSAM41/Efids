package efids.efids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Fidtab {
	
		@Id
		@Column(name = "URNO")
		private String urno;
		@Column(name = "BEME")
		private String beme;
		@Column(name = "CODE")
		private String code;
		
		public Fidtab() {
			super();
		}

		public Fidtab(String urno, String beme, String code) {
			super();
			this.urno = urno;
			this.beme = beme;
			this.code = code;
		}

		public String getUrno() {
			return urno;
		}

		public void setUrno(String urno) {
			this.urno = urno;
		}

		public String getBeme() {
			return beme;
		}

		public void setBeme(String beme) {
			this.beme = beme;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
		
}
