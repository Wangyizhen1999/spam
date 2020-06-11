package com.hrbuedu.cn.dao.mapper;

import com.hrbuedu.cn.model.sysUser.Mail;
import com.hrbuedu.cn.model.sysUser.MailExample;
import com.hrbuedu.cn.model.sysUser.MailWithBLOBs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MailMapper {
    long countByExample(MailExample example);

    int deleteByExample(MailExample example);

    int deleteByPrimaryKey(String id);

    int insert(MailWithBLOBs record);

    int insertSelective(MailWithBLOBs record);

    List<MailWithBLOBs> selectByExampleWithBLOBs(MailExample example);

    List<Mail> selectByExample(MailExample example);

    MailWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") MailWithBLOBs record, @Param("example") MailExample example);

    int updateByExampleWithBLOBs(@Param("record") MailWithBLOBs record, @Param("example") MailExample example);

    int updateByExample(@Param("record") Mail record, @Param("example") MailExample example);

    int updateByPrimaryKeySelective(MailWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MailWithBLOBs record);

    int updateByPrimaryKey(Mail record);

	List<Mail> selectByMember(String memberId);

	int selectByMailNum(String memberId);

	List<Mail> selectByToday(MailExample mailExample);

	List<Mail> selectByYesterday(MailExample mailExample);

	List<Mail> selectByThisWeek(MailExample mailExample);

	List<Mail> selectByLastWeek(MailExample mailExample);

	int deleteByAll(Map<String, List<String>> maps);

	int selectByNormalNum(String memberId);

	int selectBySpamNum(String memberId);

}