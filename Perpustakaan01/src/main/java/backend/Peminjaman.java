/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author Lenovo
 */
public class Peminjaman {
     private int idPeminjaman;
    private Buku buku = new Buku();
    private Anggota anggota = new Anggota();
    private String tanggalPinjam, tanggalKembali;

    public Peminjaman() {
    }

    public Peminjaman(Anggota anggota, Buku buku, String tanggalPinjam, String tanggalKembali) {
        this.anggota = anggota;
        this.buku = buku;
        this.tanggalPinjam = tanggalPinjam;
        this.tanggalKembali = tanggalKembali;
    }

    public int getIdPeminjaman() {
        return idPeminjaman;
    }

    public void setIdPeminjaman(int idPeminjaman) {
        this.idPeminjaman = idPeminjaman;
    }

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public void setAnggota(Anggota anggota) {
        this.anggota = anggota;
    }

    public String getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(String tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public String getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(String tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }
    
    public Peminjaman getById(int id) {
        Peminjaman peminjaman = new Peminjaman();
        ResultSet rs = DBHelper.selectQuery("SELECT" 
                + " p.idpeminjaman AS idpeminjaman,"
                + " p.idanggota AS idanggota,"
                + " p.idbuku AS idbuku,"
                + " p.tanggalpinjam AS tanggalpinjam,"
                + " p.tanggalkembali AS tanggalkembali,"
                + " a.nama AS nama,"
                + " b.judul AS judul"
                + " FROM peminjaman p "
                + " LEFT JOIN anggota a "
                + " ON p.idanggota = a.idanggota"
                + " LEFT JOIN buku b "
                + " ON p.idbuku = b.idbuku"
                + " WHERE p.idpeminjaman = '" + id + "'");
        try {
            while (rs.next()) {
                peminjaman = new Peminjaman();
                peminjaman.setIdPeminjaman(rs.getInt("idpeminjaman"));
                peminjaman.getBuku().setIdbuku(rs.getInt("idbuku"));
                peminjaman.getBuku().setJudul(rs.getString("judul"));
                peminjaman.getAnggota().setIdanggota(rs.getInt("idanggota"));
                peminjaman.getAnggota().setNama(rs.getString("nama"));
                peminjaman.setTanggalPinjam(rs.getString("tanggalpinjam"));
                peminjaman.setTanggalKembali(rs.getString("tanggalkembali"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return peminjaman;
    }
    
    public ArrayList<Peminjaman> getAll() {
        ArrayList<Peminjaman> ListPeminjaman = new ArrayList();
        ResultSet rs = DBHelper.selectQuery("SELECT" 
                + " p.idpeminjaman AS idpeminjaman,"
                + " p.idanggota AS idanggota,"
                + " p.idbuku AS idbuku,"
                + " b.judul AS judul,"
                + " a.nama AS nama,"
                + " p.tanggalpinjam AS tanggalpinjam,"
                + " p.tanggalkembali AS tanggalkembali"
                + " FROM peminjaman p "
                + " LEFT JOIN anggota a "
                + " ON p.idanggota = a.idanggota"
                + " LEFT JOIN buku b "
                + " ON p.idbuku = b.idbuku");
        try {
              while (rs.next()) {
                Peminjaman peminjaman = new Peminjaman();
                peminjaman.setIdPeminjaman(rs.getInt("idpeminjaman"));
                peminjaman.getBuku().setIdbuku(rs.getInt("idbuku"));
                peminjaman.getBuku().setJudul(rs.getString("judul"));
                peminjaman.getAnggota().setIdanggota(rs.getInt("idanggota"));
                peminjaman.getAnggota().setNama(rs.getString("nama"));
                peminjaman.setTanggalPinjam(rs.getString("tanggalpinjam"));
                peminjaman.setTanggalKembali(rs.getString("tanggalkembali"));
                ListPeminjaman.add(peminjaman);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListPeminjaman;
    }
    
    public ArrayList<Peminjaman> search(String keyword) {
        ArrayList<Peminjaman> ListPeminjaman = new ArrayList();
        String sql = "SELECT" 
                + " p.idpeminjaman AS idpeminjaman,"
                + " p.idanggota AS idanggota,"
                + " p.idbuku AS idbuku,"
                + " b.judul AS judul,"
                + " a.nama AS nama,"
                + " p.tanggalpinjam AS tanggalpinjam,"
                + " p.tanggalkembali AS tanggalkembali"
                + " FROM peminjaman p "
                + " LEFT JOIN anggota a "
                + " ON p.idanggota = a.idanggota"
                + " LEFT JOIN buku b "
                + " ON p.idbuku = b.idbuku"
                + " WHERE b.judul LIKE '%" + keyword + "%' "
                + " OR p.idpeminjaman LIKE '%" + keyword + "%' "
                + " OR p.idanggota LIKE '%" + keyword + "%' "
                + " OR a.nama LIKE '%" + keyword + "%' "
                + " OR p.tanggalpinjam LIKE '%" + keyword + "%' "
                + " OR p.tanggalkembali LIKE '%" + keyword + "%' "
                + " OR p.idbuku LIKE '%" + keyword + "%' ";
        ResultSet rs = DBHelper.selectQuery(sql);
        try {
            while (rs.next()) {
                Peminjaman peminjaman = new Peminjaman();
                peminjaman.setIdPeminjaman(rs.getInt("idpeminjaman"));
                peminjaman.getBuku().setIdbuku(rs.getInt("idbuku"));
                peminjaman.getBuku().setJudul(rs.getString("judul"));
                peminjaman.getAnggota().setIdanggota(rs.getInt("idanggota"));
                peminjaman.getAnggota().setNama(rs.getString("nama"));
                peminjaman.setTanggalPinjam(rs.getString("tanggalpinjam"));
                peminjaman.setTanggalKembali(rs.getString("tanggalkembali"));
                ListPeminjaman.add(peminjaman);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListPeminjaman;
    }
    
    public void save(){
        if (getById(idPeminjaman).getIdPeminjaman()==0) {
            String SQL = "INSERT INTO peminjaman (idanggota,idbuku,tanggalpinjam,tanggalkembali) VALUES("
                       + " '"+this.getAnggota().getIdanggota()+"', "
                       + " '"+this.getBuku().getIdbuku()+"', "
                       + " '"+this.tanggalPinjam+"', "
                       + " '"+this.tanggalKembali+"' )";
            this.idPeminjaman = DBHelper.insertQueryGetId(SQL);
        }else{
              String SQL = "UPDATE peminjaman SET "
                       + "idanggota = '"+this.getAnggota().getIdanggota()+"', "
                       + "idbuku = '"+this.getBuku().getIdbuku()+"', "
                       + "tanggalpinjam = '"+this.tanggalPinjam+"', "
                       + "tanggalkembali = '"+this.tanggalKembali+"' "
                       + "WHERE idpeminjaman = '"+this.idPeminjaman+"'";
            DBHelper.executeQuery(SQL);
        }
    }
    public void delete(){
        String SQL = "DELETE FROM peminjaman WHERE idpeminjaman = '"+this.idPeminjaman+"'";
        DBHelper.executeQuery(SQL);
    }
}
