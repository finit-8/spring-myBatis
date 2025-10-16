package com.app.mybatis.mapper;

import com.app.mybatis.domain.MemberVO;
import com.app.mybatis.domain.PostDTO;
import com.app.mybatis.domain.PostVO;
import com.app.mybatis.enums.PostStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@SpringBootTest
@Slf4j
public class PostTests {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void insertTest() {
//        PostVO postVO = new PostVO();
//        postVO.setPostTitle("오늘 뭐먹지?");
//        postVO.setPostContent("점메추 받음");
//        postVO.setPostStatus("PUBLIC".equals("PUBLIC") ? PostStatus.PUBLIC : PostStatus.PRIVATE);
//        postVO.setMemberId(1L);

//        PostVO postVO = new PostVO();
//        postVO.setPostTitle("나만의 비밀");
//        postVO.setPostContent("오늘 사실 안아픔");
//        postVO.setPostStatus("PRIVATE".equals("PUBLIC") ? PostStatus.PUBLIC : PostStatus.PRIVATE);
//        postVO.setMemberId(1L);

//        postMapper.insert(postVO);

        // 실제 데이터 들어오는 순서
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("test123@gmail.com");
        memberVO.setMemberPassword("1234");
        memberMapper.select(memberVO)
                .map(MemberVO::getId)
                .ifPresent(memberId -> {
                    PostDTO postDTO = new PostDTO();
                    postDTO.setPostTitle("이제 되나");
                    postDTO.setPostContent("???");
                    postDTO.setPostStatus("PRIVATE".equals("PUBLIC") ? PostStatus.PUBLIC : PostStatus.PRIVATE);
                    postDTO.setMemberId(memberId);
                    postMapper.insert(postDTO);
                });
    }

    @Test
    public void selectAllTest() {
        List<PostDTO> posts = postMapper.selectAll();
        log.info("게시물 전체 조회: {}", posts);
    }

    @Test
    public void selectTest() {
        postMapper.updateReadCount(1L);                       // 게시글 조회 누르면 조회수 올라가고, 올라간게 조회되어야 해서 먼저 실행되게 함
        Optional<PostDTO> post = postMapper.select(1L);
//        log.info("게시물 상세 조회: {}", post);
        post.map(PostDTO::toString).ifPresent(log::info);
    }

    @Test
    public void updateTest() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("test123@gmail.com");
        memberVO.setMemberPassword("1234");                                                         // 로그인
        memberMapper.select(memberVO).map(MemberVO::toString).ifPresent(memberId -> {         // 게시판 중 현재 유저의
            postMapper.select(2L).ifPresent(postDTO -> {                                // 게시글 중에서 하나 선택해서
                PostDTO post = new PostDTO();                                                       // 수정 (select메서드의 리턴타입이 Optional<PostDTO>이라서, 리턴으로 PostDTO이 들어오면 그 값을 람다식 매개변수로 전달)
                post.setId(postDTO.getId());                                                        // ]--> ifPresent의 postDTO의 id이고,
                post.setPostTitle("update 제목");                                                    // ]
                post.setPostContent("update 내용");                                                  // ]--> 화면에서 받은 값
                post.setPostStatus(postDTO.getPostStatus());                                        // ]
                postMapper.update(post);                                                            // 업데이트 처리
            });
        });
    }

    @Test
    public void deleteTest() {
        postMapper.delete(50L);
    }

    // 게시글 목록(정렬) - 동적 쿼리
        // 게시글 정렬 위해 데이터 insert
    @Test
    public void insertTests() {
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberEmail("test123@gmail.com");
        memberVO.setMemberPassword("1234");
        memberMapper.select(memberVO).map(MemberVO::getId).ifPresent(memberId -> {
            for(int i = 0; i < 50; i++) {
                PostDTO postDTO = new PostDTO();
                postDTO.setPostTitle("테스트 작성글" + (i + 1));
                postDTO.setPostContent("테스트 작성 내용" + (i + 1));
                postDTO.setMemberId(memberId);
                postMapper.insert(postDTO);
            }
        });
    }
        // 조회수에 따른 게시글 정렬 위해 Random으로 ReadCount 증가
    @Test
    public void updateReadCountTest() {
        Random random = new Random();
        for(int i = 0; i < 10000; i++) {
            Long id = Long.valueOf(random.nextLong(1, 50));
            postMapper.select(id).map(PostDTO::getId).ifPresent(postMapper::updateReadCount);
        }
    }

        // 게시글 정렬
    @Test
    public void selectAllWithOrderTest(){
        String order = null;
        order = "popular";                          // mapper.xml 쿼리문에서의 <when>코드 실행
//      if(order == null) { order = "";}           mapper.xml 쿼리문에서의 <oterwise>코드 실행
        postMapper.selectAllWithOrder(order).stream().map(PostDTO::toString).forEach(log::info);
    }
}
