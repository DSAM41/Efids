package efids.efids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contingency_arr {
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name = "ca_id")
		private String id;
		
		@Column(name = "ca_time")
		private String time;
		
		@Column(name = "ca_from")
		private String from;
		
		@Column(name = "ca_flight")
		private String flight;
		
		@Column(name = "ca_belt")
		private String belt;
		
		@Column(name = "ca_nature")
		private String nature;
		
		@Column(name = "ca_type")
		private String type;
		
		@Column(name = "ca_remark")
		private String remark;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getFrom() {
			return from;
		}

		public void setFrom(String from) {
			this.from = from;
		}

		public String getFlight() {
			return flight;
		}

		public void setFlight(String flight) {
			this.flight = flight;
		}

		public String getBelt() {
			return belt;
		}

		public void setBelt(String belt) {
			this.belt = belt;
		}

		public String getNature() {
			return nature;
		}

		public void setNature(String nature) {
			this.nature = nature;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}
		
}
