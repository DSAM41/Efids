package efids.aftab.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CEDAAODB.FIDTAB")
public class Fidtab {
	
		@Id
		@Column(name = "URNO")
		private String urno;
		@Column(name = "BEME")
		private String beme;
		
		public Fidtab() {
			super();
		}

		public Fidtab(String urno, String beme) {
			super();
			this.urno = urno;
			this.beme = beme;
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

	
	
			
		
}
