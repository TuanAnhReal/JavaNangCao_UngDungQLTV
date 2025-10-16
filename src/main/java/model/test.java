/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package model;

/**
 *
 * @author PC
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SachDAO sDAO = new SachDAO();
        var s = sDAO.getAll();
        System.out.println("sách:" + s);
        
    }
    
}
