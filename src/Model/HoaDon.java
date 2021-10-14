/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author Hello Kiên
 */
public class HoaDon implements Serializable{
    protected String maHD;
    //kh
    protected String tenKH;
    protected int maHang;
    protected String tenHang;
    protected int soLuong;
    protected double tongGia, tongTien;

    public HoaDon() {
    }

    public HoaDon(String maHD, String tenKH, int maHang, String tenHang, int soLuong, double tongGia, double tongTien) {
        this.maHD = maHD;
        this.tenKH = tenKH;
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.soLuong = soLuong;
        this.tongGia = tongGia;
        this.tongTien = tongTien;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public int getMaHang() {
        return maHang;
    }

    public void setMaHang(int maHang) {
        this.maHang = maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getTongGia() {
        return tongGia;
    }

    public void setTongGia(double tongGia) {
        this.tongGia = tongGia;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "| Mã hàng = " + maHang + ", Tên hàng = " + tenHang + ", Số lượng = " + soLuong + ", Tổng giá = " + tongGia;
    }
    
    
}
