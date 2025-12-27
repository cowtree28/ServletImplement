package org.example.domain.product.repository;

import org.example.domain.product.vo.ProductVO;

import java.util.List;

public interface ProductRepository {
    List<ProductVO> findAll();
    ProductVO findById(Long id);
    ProductVO save(ProductVO product);
    ProductVO update(ProductVO product);
    void delete(Long id);
}
