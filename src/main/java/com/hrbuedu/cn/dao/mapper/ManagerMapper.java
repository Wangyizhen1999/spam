package com.hrbuedu.cn.dao.mapper;

import com.hrbuedu.cn.model.sysUser.Manager;
import com.hrbuedu.cn.model.sysUser.ManagerExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ManagerMapper {
    long countByExample(ManagerExample example);

    int deleteByExample(ManagerExample example);

    int deleteByPrimaryKey(String id);

    int insert(Manager record);

    int insertSelective(Manager record);

    List<Manager> selectByExampleWithBLOBs(ManagerExample example);

    List<Manager> selectByExample(ManagerExample example);

    Manager selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Manager record, @Param("example") ManagerExample example);

    int updateByExampleWithBLOBs(@Param("record") Manager record, @Param("example") ManagerExample example);

    int updateByExample(@Param("record") Manager record, @Param("example") ManagerExample example);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKeyWithBLOBs(Manager record);

    int updateByPrimaryKey(Manager record);

	int updateByProblem(Manager manager);

	int updateByManager(Manager manager);
}