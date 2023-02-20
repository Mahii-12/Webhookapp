/**
 * 
 */
package entity;

/**
 * @author Lenovo
 *
 */


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "webhook")
public class WebHook {

		public final boolean webHook = false;

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		
		@Column(name="url")
		private String url;
		
		@Column(name="dept_name")
		private String departmentName;
		private String type;

		
		public WebHook(Long id, String url, String companyName,String type) {
			super();
			this.id = id;
			this.url = url;
			this.departmentName = companyName;
			this.setType(type);
		}


		/**
		 * @return the id
		 */
		public Long getId() {
			return id;
		}


		/**
		 * @param id the id to set
		 */
		public void setId(Long id) {
			this.id = id;
		}


		/**
		 * @return the url
		 */
		public String getUrl() {
			return url;
		}


		/**
		 * @param url the url to set
		 */
		public void setUrl(String url) {
			this.url = url;
		}


		/**
		 * @return the departmentName
		 */
		public String getDepartmentName() {
			return departmentName;
		}


		/**
		 * @param departmentName the departmentName to set
		 */
		public void setDepartmentName(String departmentName) {
			this.departmentName = departmentName;
		}


		public String getType() {
			return type;
		}


		public void setType(String type) {
			this.type = type;
		}

	
	
}
