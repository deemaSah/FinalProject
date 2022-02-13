package com.example.project2;

public class Rooms {
    private String id;
    private String imgUrl;
    private String price;
    private String pNum;
    private String description;

    public Rooms(String id, String imgUrl, String price, String pNum, String description) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.price = price;
        this.pNum = pNum;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getPrice() {
        return price;
    }

    public String getpNum() {
        return pNum;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setpNum(String pNum) {
        this.pNum = pNum;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
