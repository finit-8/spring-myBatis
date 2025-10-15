package com.app.mybatis.domain;

import com.app.mybatis.enums.PostStatus;
import lombok.Data;

@Data
public class PostVO {
    private Long id;
    private String postTitle;
    private String postContent;
    private PostStatus postStatus;
    private Integer postReadCount;
    private Long memberId;
}
