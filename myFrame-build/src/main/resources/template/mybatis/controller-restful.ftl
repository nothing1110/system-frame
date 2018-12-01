package ${baseProjectPackage}.controller;

import ${baseFrmPackage}.base.model.Result;
import ${baseFrmPackage}.base.controller.BaseController;
import ${baseProjectPackage}.model.${modelNameUpperCamel};
import ${baseProjectPackage}.service.${modelNameUpperCamel}Service;
import com.github.pagehelper.Page;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

/**
* Created by ${author} on ${date}.
*/
@RestController
@RequestMapping("${baseRequestMapping}")
@Api(value = "${modelNameLowerCamel}" ,tags = {"${modelNameLowerCamel}"})
public class ${modelNameUpperCamel}Controller extends BaseController {
    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @PostMapping("/insert")
	@ApiOperation(value = "insert",notes = "插入数据")
    public Result save(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        return Result.successWithCode().setData(${modelNameLowerCamel}Service.insert(${modelNameLowerCamel}));
    }

    @PostMapping("/delete/{id}")
	@ApiOperation(value = "delete",notes = "根据id删除数据")
    public Result delete(@PathVariable("id") String id) {
        return Result.successWithCode().setData(${modelNameLowerCamel}Service.deleteById(id));
    }

    @PostMapping("/update")
	@ApiOperation(value = "update",notes = "更新数据")
    public Result update(${modelNameUpperCamel} ${modelNameLowerCamel}) {
        return Result.successWithCode().setData(${modelNameLowerCamel}Service.updateById(${modelNameLowerCamel}));
    }

    @PostMapping("/detail/{id}")
	@ApiOperation(value = "detail",notes = "获得详情")
    public Result detail(@PathVariable("id") String id) {
        return Result.successWithCode().setData(${modelNameLowerCamel}Service.queryById(id));
    }

    @GetMapping("/queryAllList")
	@ApiOperation(value = "queryAllList",notes = "获得全部列表")
    public Result queryAllList() {
        return Result.successWithCode().setData(${modelNameLowerCamel}Service.queryList());
    }

    @GetMapping("/queryList")
	@ApiOperation(value = "queryList",notes = "获得列表")
    public Result queryList(${modelNameUpperCamel} ${modelNameLowerCamel},String... likeFields) {
        return Result.successWithCode().setData(${modelNameLowerCamel}Service.queryList(${modelNameLowerCamel},likeFields));
    }

    @GetMapping("/queryPage")
	@ApiOperation(value = "queryPage",notes = "分页查询")
    public Result queryPage(${modelNameUpperCamel} ${modelNameLowerCamel}, Page page,String... likeFields) {
        return Result.successWithCode().setData(${modelNameLowerCamel}Service.queryPage(${modelNameLowerCamel},page,likeFields));
    }
}
