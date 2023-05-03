package efids.efids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Airline {
	
		@Id
		@Column(name = "ALC3")
		private String alc3;
		@Column(name = "ALC2")
		private String alc2;
		@Column(name = "ALNAME")
		private String alname;
		
		public Airline() {
			super();
		}

		public Airline(String alc3, String alc2, String alname) {
			super();
			this.alc3 = alc3;
			this.alc2 = alc2;
			this.alname = alname;
		}

		public String getAlc3() {
			return alc3;
		}

		public void setAlc3(String alc3) {
			this.alc3 = alc3;
		}

		public String getAlc() {
			return alc2;
		}

		public void setAlc(String alc2) {
			this.alc2 = alc2;
		}

		public String getAlname() {
			return alname;
		}

		public void setAlname(String alname) {
			this.alname = alname;
		}
		
		
	
}
