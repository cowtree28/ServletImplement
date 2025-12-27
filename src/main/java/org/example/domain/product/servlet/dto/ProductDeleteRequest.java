package org.example.domain.product.servlet.dto;

public class ProductDeleteRequest {
    private Long id;

    public ProductDeleteRequest() {
    }

    public ProductDeleteRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
