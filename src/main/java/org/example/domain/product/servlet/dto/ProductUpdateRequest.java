package org.example.domain.product.servlet.dto;

public class ProductUpdateRequest {
    private Long id;
    private String name;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ProductUpdateRequest() {
    }

    public ProductUpdateRequest(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
