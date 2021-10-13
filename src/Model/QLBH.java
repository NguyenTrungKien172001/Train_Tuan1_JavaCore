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
public class QLBH implements Serializable{
    protected String maHD;
    protected int maKH;
    protected String tenKH;
    protected int maHang;
    protected String tenHang;
    protected int soLuong;
    protected double tongGia;

    public QLBH() {
    }

    public QLBH(String maHD, int maKH, String tenKH, int maHang, String tenHang, int soLuong, double tongGia) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.soLuong = soLuong;
        this.tongGia = tongGia;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
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

    @Override
    public String toString() {
        return "QLBH{" + "maHD=" + maHD + ", maKH=" + maKH + ", tenKH=" + tenKH + ", maHang=" + maHang + ", tenHang=" + tenHang + ", soLuong=" + soLuong + ", tongGia=" + tongGia + '}';
    }

    
    
}
