package org.example.domain.product.vo;

public class ProductVO {
    private final Long id;
    private final String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    private final int price;

    public ProductVO(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
