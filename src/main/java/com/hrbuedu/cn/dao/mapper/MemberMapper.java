package com.hrbuedu.cn.dao.mapper;

import com.hrbuedu.cn.model.sysUser.Member;
import com.hrbuedu.cn.model.sysUser.MemberExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    long countByExample(MemberExample example);

    int deleteByExample(MemberExample example);

    int deleteByPrimaryKey(String id);

    int insert(Member record);

    int insertSelective(Member record);

    List<Member> selectByExampleWithBLOBs(MemberExample example);

    List<Member> selectByExample(MemberExample example);

    Member selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Member record, @Param("example") MemberExample example);

    int updateByExampleWithBLOBs(@Param("record") Member record, @Param("example") MemberExample example);

    int updateByExample(@Param("record") Member record, @Param("example") MemberExample example);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKeyWithBLOBs(Member record);

    int updateByPrimaryKey(Member record);

	int updateByProblem(Member member);

	int updateByMember(Member member);

	void updateByNum(Member member);
}