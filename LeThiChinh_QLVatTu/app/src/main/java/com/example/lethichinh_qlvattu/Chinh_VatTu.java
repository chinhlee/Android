package com.example.lethichinh_qlvattu;

public class Chinh_VatTu {
    private String mavt;
    private String ten, nsx;
    private String namsx, soluong;
    private String dongia;

    public String getMavt() {
        return mavt;
    }

    public void setMavt(String mavt) {
        this.mavt = mavt;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNsx() {
        return nsx;
    }

    public void setNsx(String nsx) {
        this.nsx = nsx;
    }

    public String getNamsx() {
        return namsx;
    }

    public void setNamsx(String namsx) {
        this.namsx = namsx;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }

    public String getDongia() {
        return dongia;
    }

    public void setDongia(String dongia) {
        this.dongia = dongia;
    }

    public Chinh_VatTu(){

    }

    public Chinh_VatTu(String mavt, String ten, String nsx, String namsx, String soluong, String dongia) {
        this.mavt = mavt;
        this.ten = ten;
        this.nsx = nsx;
        this.namsx = namsx;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    public Chinh_VatTu(String ten, String nsx, String namsx, String soluong, String dongia) {
        this.ten = ten;
        this.nsx = nsx;
        this.namsx = namsx;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    @Override
    public String toString() {
        return "Chinh_VatTu{" +
                "mavt='" + mavt + '\'' +
                ", ten='" + ten + '\'' +
                ", nsx='" + nsx + '\'' +
                ", namsx=" + namsx +
                ", soluong=" + soluong +
                ", dongia=" + dongia +
                '}';
    }
}
