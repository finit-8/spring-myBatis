package com.app.mybatis.domain;

import lombok.*;

@Getter @Setter @ToString @EqualsAndHashCode(of = "id")
@NoArgsConstructor @AllArgsConstructor
public class ProductVO {
    Long id;
    String productName;
    int productPrice;
    int productStock;
}
