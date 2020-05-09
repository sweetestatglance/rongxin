package ${bussiPackage}.dao;

import java.util.List;
import java.util.Map;

import com.fourfaith.model.${entityName};

public interface ${entityName}Mapper {

   	int deleteByPrimaryKey(String id);

	int insert(${entityName} record);

	int insertSelective(${entityName} record);

	${entityName} selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(${entityName} record);

	int updateByPrimaryKey(${entityName} record);

	Integer getCount(Map<String, Object> params);

	List<${entityName}> getList(Map<String, Object> params);

	${entityName} findById(String Id);
}
