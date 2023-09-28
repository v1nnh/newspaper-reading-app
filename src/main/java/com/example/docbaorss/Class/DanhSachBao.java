package com.example.docbaorss.Class;

import com.example.docbaorss.R;



import java.util.ArrayList;


// Dùng để hiển thị danh sách đầu báo

public class DanhSachBao {

    public ArrayList<Bao> LayDanhSachBao(){
        ArrayList<Bao> DanhSachBao = new ArrayList<>();
        DanhSachBao.add(new Bao(R.drawable.vnexpresss,"VNExpress"));
        DanhSachBao.add(new Bao(R.drawable.haitugio,"24H"));
        DanhSachBao.add(new Bao(R.drawable.thanh_nien,"Thanh Niên"));
        return DanhSachBao;
    }

}
