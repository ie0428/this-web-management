package com.itheima.t.controller;
import com.itheima.t.pojo.Emp;
import com.itheima.t.pojo.PageBean;
import com.itheima.t.pojo.Result;
import com.itheima.t.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//员工管理控制器
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    //条件分页查询
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1")Integer page,
                       @RequestParam(defaultValue = "10")Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        //记录日志
        log.info("分页查询，参数：{}，{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        //调用业务分页查询功能
        PageBean pageBean=empService.page(page,pageSize,name,gender,begin,end);
        //响应
        return Result.success(pageBean);
    }

    //删除员工
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        //记录日志
        log.info("根据id删除员工:{}",ids);
        //调用service执行删除
        empService.delete(ids);
        return Result.success();
    }

    //新增员工
    @PostMapping
    public Result save(@RequestBody Emp emp){
        //记录日志
        log.info("新增员工，员工信息：{}",emp);
        //调用service执行保存
        empService.save(emp);
        return Result.success();
    }

    //根据id查询
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Emp emp=empService.getById(id);
        return Result.success(emp);
    }

    //修改员工
    @PutMapping
    public Result update(@RequestBody Emp emp){
        empService.update(emp);
        return Result.success();
    }
}
