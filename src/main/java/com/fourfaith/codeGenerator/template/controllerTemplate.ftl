package ${bussiPackage}.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ${bussiPackage}.service.${entityName}Service;
/**   
 * @Title: Controller
 * @Description: ${ftl_description}
 * @author administrator
 * @date ${ftl_create_time}
 * @version V1.0   
 *
 */
@Controller
@RequestMapping(value ="/${entityName?uncap_first}")
public class ${entityName}Controller {

	@Autowired
	private ${entityName}Service ${entityName?uncap_first}Service;


}
