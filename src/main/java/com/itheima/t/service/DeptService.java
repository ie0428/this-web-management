package com.itheima.t.service;

import com.itheima.t.pojo.Dept;
import com.itheima.t.pojo.Emp;
import com.itheima.t.pojo.PageBean;
import org.springframework.stereotype.Service;

import java.util.List;


//部门业务规则
public interface DeptService {
    /**
     * 查询所有的部门数据
     * @return   存储Dept对象的集合
     */
    List<Dept> list();
    /**
     * 根据id删除部门
     * @param id    部门id
     */
    void delete(Integer id);
    /**
     * 新增部门
     * @param dept  部门对象
     */
    void add(Dept dept);





}
