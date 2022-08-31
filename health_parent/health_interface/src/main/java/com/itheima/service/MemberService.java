package com.itheima.service;

import com.itheima.pojo.Member;

public interface MemberService {
    public void add(Member member);
    public Member findByTelephone(String telephone);
}
