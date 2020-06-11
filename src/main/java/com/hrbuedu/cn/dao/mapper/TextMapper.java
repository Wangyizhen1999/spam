package com.hrbuedu.cn.dao.mapper;

import com.hrbuedu.cn.model.sysUser.Text;
import com.hrbuedu.cn.model.sysUser.TextExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TextMapper {
    long countByExample(TextExample example);

    int deleteByExample(TextExample example);

    int deleteByPrimaryKey(String id);

    int insert(Text record);

    int insertSelective(Text record);

    List<Text> selectByExample(TextExample example);

    Text selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Text record, @Param("example") TextExample example);

    int updateByExample(@Param("record") Text record, @Param("example") TextExample example);

    int updateByPrimaryKeySelective(Text record);

    int updateByPrimaryKey(Text record);
}