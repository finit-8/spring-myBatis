package com.app.mybatis.mapper;

import com.app.mybatis.domain.PostDTO;
import com.app.mybatis.domain.PostVO;
import com.app.mybatis.domain.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PostMapper {
    // 게시글 작성
    public void insert(PostDTO postDTO);

    // 게시글 목록
    public List<PostDTO> selectAll();

    // 게시글 상세조회
    public Optional<PostDTO> select(Long id);

    // 게시글 수정
    public void update(PostDTO postDTO);

    // 게시글 삭제
    public void delete(Long id);

    // 조회수 증가
    public void updateReadCount(Long id);

    // 게시글 목록(정렬) - 동적 쿼리
    public List<PostDTO> selectAllWithOrder(String order);
}
