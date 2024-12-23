package com.itheima.t.service;

import com.itheima.t.mapper.DeptMapper;
import com.itheima.t.mapper.EmpMapper;
import com.itheima.t.pojo.Dept;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


//部门业务实现类
@Slf4j
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> list(){
        List<Dept> deptList = deptMapper.list();
        return deptList;
    }
    //根据部门id，删除部门信息及部门下的所有员工
    @Transactional//当前方法添加了事务管理
    @Override
    public void delete(Integer id){
        //根据部门id删除部门信息
        deptMapper.deleteById(id);

        //模拟异常发生
        int i = 10/0;

        //删除部门下的所有员工信息
        empMapper.deleteByDeptId(id);
    }

    @Override
    public void add(Dept dept){
        //补全部门数据
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //调用持久层增加功能
        deptMapper.inser(dept);
    }



}
