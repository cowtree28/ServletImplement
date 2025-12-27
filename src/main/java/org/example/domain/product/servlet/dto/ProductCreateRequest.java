package org.example.domain.product.servlet.dto;

public class ProductCreateRequest {
    private String name;
    private int price;

    public ProductCreateRequest(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public ProductCreateRequest() { }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
