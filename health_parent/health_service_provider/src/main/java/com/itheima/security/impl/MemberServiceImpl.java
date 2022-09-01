package com.itheima.security.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.dao.MemberDao;
import com.itheima.pojo.Member;
import com.itheima.security.MemberService;
import com.itheima.utils.MD5Utils;

import java.util.ArrayList;
import java.util.List;

public class MemberServiceImpl implements MemberService {

    @Reference
    private MemberDao memberDao;

    //根据手机号查询会员
    public Member findByTelephone(String telephone) {
        return memberDao.findByTelephone(telephone);
    }
    //新增会员
    public void add(Member member) {
        if(member.getPassword() != null){
            member.setPassword(MD5Utils.md5(member.getPassword()));
        }
        memberDao.add(member);
    }
    //根据月份统计会员数量
    public List<Integer> findMemberCountByMonth(List<String> month) {
        List<Integer> list = new ArrayList<>();
        for(String m : month){
            m = m + ".31";//格式：2019.04.31
            Integer count = memberDao.findMemberCountBeforeDate(m);
            list.add(count);
        }
        return list;
    }
}
