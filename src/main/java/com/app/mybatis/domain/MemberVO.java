package com.app.mybatis.domain;

import lombok.*;

@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
@NoArgsConstructor @AllArgsConstructor
public class MemberVO {
    private Long id;
    private String memberEmail;
    private String memberName;
    private String memberPassword;
}
