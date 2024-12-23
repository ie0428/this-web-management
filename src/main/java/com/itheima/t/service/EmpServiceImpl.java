package com.itheima.t.service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.t.mapper.EmpMapper;
import com.itheima.t.pojo.Emp;
import com.itheima.t.pojo.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

//员工业务实现类
@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    /**
    @Override
    public PageBean page (Integer page, Integer pageSize) {
        //1.获取总记录数
        Long cout=empMapper.count();

        //2.获取分页查询结果列表
        Integer start=(page-1)*pageSize;//计算起始索引值,公式：(当前页码-1)*每页记录数
        List<Emp> empList=empMapper.list(start,pageSize);

        //3.封装到PageBean对象中并返回
        PageBean pageBean=new PageBean(cout,empList);
        return pageBean;
    }
    */
    @Override
    public PageBean page(Integer page, Integer pageSize,String name, Short gender, LocalDate begin, LocalDate end) {

        // 设置分页参数
        PageHelper.startPage(page, pageSize);
        // 执行分页查询
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        // 获取分页结果
        Page<Emp> p = (Page<Emp>) empList;
        //封装PageBean
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
        }

        @Override
        public void delete(List<Integer>ids){
        empMapper.delete(ids);
        }

        @Override
        public  void save(Emp emp) {
            //补全数据
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            //调用添加方法
            empMapper.insert(emp);
        }
        @Override
         public Emp getById(Integer id){
            return empMapper.findById(id);
            }

         @Override
         public void update(Emp emp){
        emp.setUpdateTime(LocalDateTime.now());//更新修改时间
        empMapper.update(emp);
         }

        @Override
        public Emp login(Emp emp) {
            //调用dao层功能：登录
            Emp loginEmp=empMapper.getByUsernameAndPassword(emp);
            //返回查询结果给Controller
            return loginEmp;
        }
}