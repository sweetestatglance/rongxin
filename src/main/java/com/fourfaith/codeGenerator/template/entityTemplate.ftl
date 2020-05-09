package ${bussiPackage}.model;
import java.math.BigDecimal;


/**   
 * @Title: Entity
 * @Description: ${ftl_description}
 * @author  administrator
 * @date ${ftl_create_time}
 * @version V1.0   
 *
 */

public class ${entityName} implements java.io.Serializable {
	<#list originalColumns as po>
	/**${po.filedComment}*/
	private ${po.fieldType} ${po.fieldName};
	</#list>
	
	<#list originalColumns as po>
	
	public ${po.fieldType} get${po.fieldName?cap_first}(){
		return this.${po.fieldName};
	}

	/**
	 *方法: 设置${po.fieldType}
	 *@param: ${po.fieldType}  ${po.filedComment}
	 */
	public void set${po.fieldName?cap_first}(${po.fieldType} ${po.fieldName}){
		this.${po.fieldName} = ${po.fieldName};
	}
	</#list>
}
