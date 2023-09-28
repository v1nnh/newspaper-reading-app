package com.example.docbaorss.Class;


// Dùng để lưu trữ tên báo và url của báo đó

public class Bao {

    int icon;
    String tenBao;
    String urlBao;

    public Bao(int icon, String tenBao) {
        this.icon = icon;
        this.tenBao = tenBao;
    }

    public Bao(int icon, String tenBao, String urlBao) {
        this.icon = icon;
        this.tenBao = tenBao;
        this.urlBao = urlBao;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTenBao() {
        return tenBao;
    }

    public void setTenBao(String tenBao) {
        this.tenBao = tenBao;
    }
}
