/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Sach {

    private int maSach;
    private String tenSach;
    private String tacGia;
    private int namxuatban;
    private String theLoai;
    private int soLuong;
    private String anhBia;
    
    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getNamxuatban() {
        return namxuatban;
    }

    public void setNamxuatban(int namxuatban) {
        this.namxuatban = namxuatban;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getAnhBia() {
        return anhBia;
    }

    public void setAnhBia(String anhBia) {
        this.anhBia = anhBia;
    }

    public Sach(int maSach, String tenSach, String tacGia, int namxuatban, String theLoai, int soLuong, String anhBia) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.namxuatban = namxuatban;
        this.theLoai = theLoai;
        this.soLuong = soLuong;
        this.anhBia = anhBia;
    }

    public Sach(int maSach, String tenSach, String tacGia, int namxuatban, String theLoai, int soLuong) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tacGia = tacGia;
        this.namxuatban = namxuatban;
        this.theLoai = theLoai;
        this.soLuong = soLuong;
    }

    public Sach() {
    }

    @Override
    public String toString() {
        return this.tenSach;
    }

}
