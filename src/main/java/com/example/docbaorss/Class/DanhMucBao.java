package com.example.docbaorss.Class;

import java.io.Serializable;


// Dùng để lưu trữ danh mục báo và rss theo danh mục

public class DanhMucBao implements Serializable{

    String tendanhmuc;
    String urlDanhMuc;

    public DanhMucBao(String tendanhmuc, String urlDanhMuc) {
        this.tendanhmuc = tendanhmuc;
        this.urlDanhMuc = urlDanhMuc;
    }

    public String getTendanhmuc() {
        return tendanhmuc;
    }

    public void setTendanhmuc(String tendanhmuc) {
        this.tendanhmuc = tendanhmuc;
    }

    public String getUrlDanhMuc() {
        return urlDanhMuc;
    }

    public void setUrlDanhMuc(String urlDanhMuc) {
        this.urlDanhMuc = urlDanhMuc;
    }
}
