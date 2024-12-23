package com.itheima.t.mapper;
import com.itheima.t.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper

public interface DeptMapper {
    //查询所有部门数据
    @Select("select id, name, create_time, update_time from dept ")
    List<Dept> list();

    //删除部门数据
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    //增加部门数据

    @Insert("insert into dept(name, create_time, update_time)values(#{name},#{createTime},#{updateTime})")
    void inser(Dept dept);
}

