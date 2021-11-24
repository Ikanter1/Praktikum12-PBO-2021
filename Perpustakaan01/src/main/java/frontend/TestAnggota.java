/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend;

/**
 *
 * @author Lenovo
 */
import backend.*;
public class TestAnggota {
    public static void main(String[] args) {
        Anggota ang1 = new Anggota("Putri", "Jl kolonel", "6287645376547");
        Anggota ang2 = new Anggota("Nurifa", "Jl Baru", "6287645656547");
        Anggota ang3 = new Anggota("Firdausia", "Jl Sentul", "6287654376547");

        // test insert
        ang1.save();
        ang2.save();
        ang3.save();

        // test update
        ang2.setTelepon("0987654635276");
        ang2.save();

        // test delete
        ang3.delete();

        // test select all
        for (Anggota a : new Anggota().getAll()) {
            System.out.println("Nama: " + a.getNama() + ", Alamat: " 
                              + a.getAlamat() + ", Telepon: " + a.getTelepon());
        }

        // test search
        for (Anggota a : new Anggota().search("Jl")) {
            System.out.println("Nama: " + a.getNama() + ", Alamat: " 
                              + a.getAlamat() + ", Telepon: " + a.getTelepon());
        }
    }
}
