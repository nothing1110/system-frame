package ${baseProjectPackage}.service.impl;

import ${baseFrmPackage}.base.service.impl.CommonBaseServiceImpl;
import ${baseProjectPackage}.dao.${modelNameUpperCamel}Mapper;
import ${baseProjectPackage}.model.${modelNameUpperCamel};
import ${baseProjectPackage}.service.${modelNameUpperCamel}Service;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * Created by ${author} on ${date}.
 */
@Service
public class ${modelNameUpperCamel}ServiceImpl extends CommonBaseServiceImpl<${modelNameUpperCamel},${modelNameUpperCamel}Mapper,String> implements ${modelNameUpperCamel}Service {

}
