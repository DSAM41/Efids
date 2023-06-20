package efids.efids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Contingency_dep {
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name = "cd_id")
		private String id;
		
		@Column(name = "cd_time")
		private String time;
		
		@Column(name = "cd_via")
		private String via;
		
		@Column(name = "cd_to")
		private String to;
		
		@Column(name = "cd_flight")
		private String flight;
		
		@Column(name = "cd_counter")
		private String counter;
		
		@Column(name = "cd_gate")
		private String gate;

		@Column(name = "cd_nature")
		private String nature;
		
		@Column(name = "cd_type")
		private String type;
		
		@Column(name = "cd_remark")
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

		public String getVia() {
			return via;
		}

		public void setVia(String via) {
			this.via = via;
		}

		public String getTo() {
			return to;
		}

		public void setTo(String to) {
			this.to = to;
		}

		public String getFlight() {
			return flight;
		}

		public void setFlight(String flight) {
			this.flight = flight;
		}

		public String getCounter() {
			return counter;
		}

		public void setCounter(String counter) {
			this.counter = counter;
		}

		public String getGate() {
			return gate;
		}

		public void setGate(String gate) {
			this.gate = gate;
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
