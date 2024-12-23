package com.itheima.t.controller;
import com.itheima.t.pojo.Dept;
import com.itheima.t.pojo.Result;
import com.itheima.t.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//部门管理控制器
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired//用于自动注入依赖。当在类的属性、方法或构造函数上使用
    private DeptService deptService;
    //@RequestMapping(value = "/depts" , method = RequestMethod.GET)
    @GetMapping
    public Result list(){
        log.info("查询所有部门数据");
        List<Dept> deptList = deptService.list();//用一个集合来接受数据
        return Result.success(deptList);

    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        //日志记录
        log.info("根据id删除部门:{}");
        //调用service执行删除
        deptService.delete(id);
        //响应
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Dept dept){
        //记录日志
        log.info("新增部门:{}",dept);
        //调用service执行添加
        deptService.add(dept);
        //响应
        return Result.success();
    }


}
