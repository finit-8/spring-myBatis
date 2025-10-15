package com.app.mybatis.mapper;

import com.app.mybatis.domain.PostVO;
import com.app.mybatis.domain.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {
    // 게시글 작성
    public void insert(PostVO postVO);

    // 게시글 목록
    // 게시글 조회
    // 게시글 수정
    // 게시글 삭제
    // 게시글 증가
    // 게시글 목록(정렬) - 동적 쿼리

}
