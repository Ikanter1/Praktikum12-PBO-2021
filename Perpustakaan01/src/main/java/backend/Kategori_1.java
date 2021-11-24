/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

/**
 *
 * @author Lenovo
 */
import java.util.ArrayList;
import java.sql.*;

public class Kategori_1 {
    private int idkategori;
    private String nama;
    private String keterangan;
   

    public int getIdkategori() {
        return idkategori;
    }

    public void setIdkategori(int idkategori) {
        this.idkategori = idkategori;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
    
    public Kategori_1(){
    
    }

    public Kategori_1(String nama, String keterangan) {
        this.nama = nama;
        this.keterangan = keterangan;
    }
    
    public Kategori_1 getById(int id){
        Kategori_1 kat = new Kategori_1();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM kategori "
                                            + " WHERE idkategori = '"+id+"'");
        try {
            while(rs.next()){
                kat = new Kategori_1();
                kat.setIdkategori(rs.getInt("idkategori"));
                kat.setNama(rs.getString("nama"));
                kat.setKeterangan(rs.getString("keterangan"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kat;
    }
    
    public ArrayList<Kategori_1> getAll(){
        ArrayList<Kategori_1> ListKategori = new ArrayList();
        
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM kategori");
        try {
            while(rs.next()){
                Kategori_1 kat = new Kategori_1();
                kat.setIdkategori(rs.getInt("idkategori"));
                kat.setNama(rs.getString("nama"));
                kat.setKeterangan(rs.getString("keterangan"));
                
                ListKategori.add(kat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListKategori;
    }
    
    public ArrayList<Kategori_1> search(String keyword){
        ArrayList<Kategori_1> ListKategori = new ArrayList();
        
        String sql = "SELECT * FROM kategori WHERE"
                    + " nama LIKE '%"+keyword+"%'"
                    + " OR keterangan LIKE '%"+keyword+"%'";
        
        ResultSet rs = DBHelper.selectQuery(sql);
        
        try {
            while(rs.next()){
                Kategori_1 kat = new Kategori_1();
                kat.setIdkategori(rs.getInt("idkategori"));
                kat.setNama(rs.getString("nama"));
                kat.setKeterangan(rs.getString("keterangan"));
                
                ListKategori.add(kat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListKategori;
    }
    
    public void save(){
        if(getById(idkategori).getIdkategori() == 0){
            String SQL = "INSERT INTO kategori (nama, keterangan) VALUES ("
                        + " '"+this.nama+"',"
                        + " '"+this.keterangan+"')";
            this.idkategori = DBHelper.insertQueryGetId(SQL);
        }else{
            String SQL = "UPDATE kategori SET "
                        + " nama = '"+this.nama+"',"
                        + " keterangan = '"+this.keterangan+"'"
                        + " WHERE idkategori = '"+this.idkategori+"'";
            DBHelper.executeQuery(SQL);
        }
    }
    
    public void delete(){
        String SQL = "DELETE FROM kategori WHERE "
                    + "idkategori = '"+this.idkategori+"'";
        DBHelper.executeQuery(SQL);
    }
}