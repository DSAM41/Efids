package efids.efids.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User_login {
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name = "user_id")
		private String id;
		
		@Column(name = "user_name")
		private String username;
		
		@Column(name = "user_password")
		private String password;
		
		@Column(name = "user_rule")
		private String rule;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getRule() {
			return rule;
		}

		public void setRule(String rule) {
			this.rule = rule;
		}

		
}
