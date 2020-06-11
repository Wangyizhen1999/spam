package com.hrbuedu.cn.dao.mapper;

import com.hrbuedu.cn.model.sysUser.Size;
import com.hrbuedu.cn.model.sysUser.SizeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SizeMapper {
    long countByExample(SizeExample example);

    int deleteByExample(SizeExample example);

    int deleteByPrimaryKey(String id);

    int insert(Size record);

    int insertSelective(Size record);

    List<Size> selectByExample(SizeExample example);

    Size selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Size record, @Param("example") SizeExample example);

    int updateByExample(@Param("record") Size record, @Param("example") SizeExample example);

    int updateByPrimaryKeySelective(Size record);

    int updateByPrimaryKey(Size record);
}