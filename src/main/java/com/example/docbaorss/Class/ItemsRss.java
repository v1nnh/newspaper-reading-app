package com.example.docbaorss.Class;

import java.io.Serializable;


// Dùng để lưu trữ các tin tức lấy xuống sau khi phân tích RSS

public class ItemsRss implements Serializable{

        private String title;
        private String description;
        private String link;
        private String urlImg;



    public ItemsRss(String title, String description, String link, String urlImg ) {
            this.title = title;
            this.description = description;
            this.link = link;
            this.urlImg = urlImg;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }


}
