package com.hrbuedu.cn.dao.mapper;

import com.hrbuedu.cn.model.sysUser.Theme;
import com.hrbuedu.cn.model.sysUser.ThemeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ThemeMapper {
    long countByExample(ThemeExample example);

    int deleteByExample(ThemeExample example);

    int deleteByPrimaryKey(String id);

    int insert(Theme record);

    int insertSelective(Theme record);

    List<Theme> selectByExample(ThemeExample example);

    Theme selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Theme record, @Param("example") ThemeExample example);

    int updateByExample(@Param("record") Theme record, @Param("example") ThemeExample example);

    int updateByPrimaryKeySelective(Theme record);

    int updateByPrimaryKey(Theme record);
}