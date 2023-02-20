/**
 * 
 */
package repository;

/**
 * @author Lenovo
 *
 */
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import entity.WebHook;

public interface WebhookRepo extends CrudRepository<WebHook,Long> {
	
        public List<WebHook> findByDepartmentNameAndType(String DepartmentName, String type);
		
		public List<WebHook> findByDepartmentName(String DepartmentName);

		public List<WebHook> DepartmentName(String departmentName, boolean b);

	    public static WebHook findOne(Long id) {
			
			return null;
		}
	
}
