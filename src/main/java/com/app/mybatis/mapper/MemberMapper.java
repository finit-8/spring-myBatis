package com.app.mybatis.mapper;

import com.app.mybatis.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    // 회원가입
    public void insert(MemberVO memberVO);

    // 로그인
    public Optional<MemberVO> select(MemberVO memberVO);

    // 회원조회
    public Optional<MemberVO> select2(Long id);

    // 회원정보수정
    public void update(MemberVO memberVO);

    // 회원 탈퇴
    public void delete(Long id);



    public List<MemberVO> selectAll();
}
