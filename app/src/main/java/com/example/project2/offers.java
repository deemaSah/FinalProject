package com.example.project2;

public class offers {
    private String id;
    private String imgUrl;
    private String caption;
    private String oldPrice;


    public offers(String id, String imgUrl, String caption, String oldPrice) {
        this.id = id;
        this.imgUrl = imgUrl;
        this.caption = caption;
        this.oldPrice = oldPrice;
    }

    public String getId() {
        return id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getCaption() {
        return caption;
    }

    public String getOldPrice() {
        return oldPrice;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

}
