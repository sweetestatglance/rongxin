package ${bussiPackage}.service.impl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

import ${bussiPackage}.model.${entityName};
import ${bussiPackage}.service.${entityName}Service;
import ${bussiPackage}.dao.${entityName}Mapper;


@Service("${entityName?uncap_first}Service")
public class ${entityName}ServiceImpl implements ${entityName}Service {
	protected Logger logger = Logger.getLogger(${entityName}ServiceImpl.class);
	@Autowired
	private ${entityName}Mapper ${entityName?uncap_first}Mapper;
  
    @Override
	public int deleteByPrimaryKey(String id) {

		int result = ${entityName?uncap_first}Mapper.deleteByPrimaryKey(id);
		return result;
	}

	@Override
	public int insert(${entityName} record) {

		int result = ${entityName?uncap_first}Mapper.insert(record);
		return result;
	}
	
	
	@Override
	public int insertSelective(${entityName} record) {

		int result = ${entityName?uncap_first}Mapper.insertSelective(record);
		return result;
	}

	@Override
	public ${entityName} selectByPrimaryKey(String id) {

		${entityName} entity = ${entityName?uncap_first}Mapper.selectByPrimaryKey(id);
		return entity;
	}
	
	@Override
	public int updateByPrimaryKeySelective(${entityName} record) {

		int result = ${entityName?uncap_first}Mapper.updateByPrimaryKeySelective(record);
		return result;
	}

	@Override
	public int updateByPrimaryKey(${entityName} record) {

		int result = ${entityName?uncap_first}Mapper.updateByPrimaryKey(record);
		return result;
	}
	
	@Override
	public Integer getCount(Map<String, Object> params) {

		int result = ${entityName?uncap_first}Mapper.getCount(params);
		return result;
	}

	@Override
	public List<${entityName}> getList(Map<String, Object> params) {

		return ${entityName?uncap_first}Mapper.getList(params);
	}

	@Override
	public ${entityName} findById(String Id) {
		return ${entityName?uncap_first}Mapper.findById(Id);
	}
}