package com.app.mybatis.mapper;

import com.app.mybatis.domain.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class ProductMapperTests {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void insertTest() {
        ProductVO productVO = new ProductVO();
        productVO.setProductName("자바 마스터리북");
        productVO.setProductPrice(9999999);
        productVO.setProductStock(1);

        productMapper.insert(productVO);
    }

    @Test
    public void selectTest() {
        productMapper.select(2L).map(ProductVO::toString).ifPresent(log::info);
    }

    @Test
    public void selectAllTest() {
        List<ProductVO> products = productMapper.selectAll();
        products.stream().map(ProductVO::toString).forEach(log::info);
    }

    @Test
    public void updateTest() {
        ProductVO productVO = new ProductVO();
        productVO.setId(2L);
        productVO.setProductName("배고플 때 생각나는 대 맛도리 치킨");
        productVO.setProductPrice(26000);
        productVO.setProductStock(20);

        productMapper.update(productVO);
    }

    @Test
    public void deleteTest() {
        productMapper.delete(3L);
    }
}
