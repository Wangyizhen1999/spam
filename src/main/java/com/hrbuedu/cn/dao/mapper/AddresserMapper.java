package com.hrbuedu.cn.dao.mapper;

import com.hrbuedu.cn.model.sysUser.Addresser;
import com.hrbuedu.cn.model.sysUser.AddresserExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AddresserMapper {
    long countByExample(AddresserExample example);

    int deleteByExample(AddresserExample example);

    int deleteByPrimaryKey(String id);

    int insert(Addresser record);

    int insertSelective(Addresser record);

    List<Addresser> selectByExample(AddresserExample example);

    Addresser selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Addresser record, @Param("example") AddresserExample example);

    int updateByExample(@Param("record") Addresser record, @Param("example") AddresserExample example);

    int updateByPrimaryKeySelective(Addresser record);

    int updateByPrimaryKey(Addresser record);

	List<Addresser> selectByAddressderCount(AddresserExample addresserExample);
}