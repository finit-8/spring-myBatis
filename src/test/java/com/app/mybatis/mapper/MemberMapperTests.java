package com.app.mybatis.mapper;

import com.app.mybatis.domain.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MemberMapperTests {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void insertTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("test123@gmail.com");
        memberVO.setMemberPassword("1234");
        memberVO.setMemberName("홍길동");
        memberMapper.insert(memberVO);
    }

    @Test
    public void selectTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("test456@gmail.com");
        memberVO.setMemberPassword("1234");
        memberMapper.select(memberVO).map(MemberVO::toString).ifPresent(log::info);
    }

    @Test
    public void select2Test() {
        MemberVO memberVO = new MemberVO();
        memberVO.setId(Long.parseLong("8"));
        memberMapper.select2(memberVO.getId());
    }

    @Test
    public void updateTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setId(8L);
        memberVO.setMemberEmail("updatetest123@gmail.com");
        memberVO.setMemberPassword("1234");
        memberVO.setMemberName("킹뚜껑");
        memberMapper.update(memberVO);
    }

    @Test
    public void deleteTest() {
        memberMapper.delete(8L);
    }
}
