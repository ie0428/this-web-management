package com.itheima.t.mapper;
import com.itheima.t.pojo.Emp;
import lombok.Data;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;

import java.util.List;

@Mapper
public interface EmpMapper {
    /**
    //获取总记录数
    @Select("select count(*) from emp")
     Long count();

    //获取当前页的查询结果
    @Select("SELECT * from emp limit #{start},#{pageSize}")
     List<Emp> list(Integer start,Integer pageSize);
    */
//获取当前页的结果列表


    List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);
    //批量删除
    void delete(List<Integer> ids);

    //新增员工
    @Insert("insert into emp (username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime});")
    void insert(Emp emp);


    //根据部门id删除部门下所有员工
    @Delete("delete from emp where dept_id=#{deptId}")
    public int deleteByDeptId(Integer deptId);

    //根据ID查询员工信息
    @Select("select id, username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time " +
            "from emp " +
            "where id = #{id}")
    public Emp findById(Integer id);

    //修改员工信息
    void update(Emp emp);

    //登录操作
    @Select("select id, username, password, name, gender, image, job, entrydate, dept_id, create_time, update_time " +
            "from emp " +
            "where username=#{username} and password =#{password}")
    public Emp getByUsernameAndPassword(Emp emp);
    }




