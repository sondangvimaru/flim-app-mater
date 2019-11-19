package com.cnt57cl.linhhuong;

import java.io.Serializable;

public class phim implements Serializable {


    int id;
    String name;
    String banner;
    String daodien;
    String ngayphathanh;
    String linkphim;
    int thoiluong;
    String mota;

    public int getThoiluong() {
        return thoiluong;
    }

    public void setThoiluong(int thoiluong) {
        this.thoiluong = thoiluong;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getDaodien() {
        return daodien;
    }

    public void setDaodien(String daodien) {
        this.daodien = daodien;
    }

    public String getNgayphathanh() {
        return ngayphathanh;
    }

    public void setNgayphathanh(String ngayphathanh) {
        this.ngayphathanh = ngayphathanh;
    }

    public String getLinkphim() {
        return linkphim;
    }

    public void setLinkphim(String linkphim) {
        this.linkphim = linkphim;
    }
}
