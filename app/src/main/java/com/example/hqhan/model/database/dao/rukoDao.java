package com.example.hqhan.model.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.hqhan.model.entity.Pengguna;
import com.example.hqhan.model.entity.ruko;

import java.util.List;

@Dao
public interface rukoDao {
    @Query("SELECT * FROM ruko")
    LiveData<List<ruko>> getAll();

    @Query("SELECT * FROM ruko where namaruko like '%' || :namaR || '%'")
    LiveData<List<ruko>> getAllsearch(String namaR);

    @Query("SELECT * FROM ruko WHERE idruko IN (:ID)")
    List<ruko> loadAllByIds(int[] ID);

    @Query("SELECT gmap FROM ruko WHERE idruko= :uid")
    String getGmap(int uid);

    @Insert
    void insertAll1(ruko... rukos);

    @Query("DELETE FROM ruko WHERE idruko= :uid")
    void deleteSingleData(int uid);

    @Query("SELECT * FROM ruko WHERE idruko= :uid LIMIT 1")
    ruko ambilSingleData(int uid);

    @Query("UPDATE ruko SET deskripsiruko= :fslts, namaruko= :nm, alamatruko= :almt, Kontakruko= :kntk, gmap= :gmp WHERE idruko= :uid")
    void ubahSingleData(String fslts, String nm, String almt, String kntk, String gmp, int uid);

    @Delete
    void delete(ruko ruko);
    @Query("SELECT * FROM Pengguna")
    List<Pengguna> getAll1();

    @Query("SELECT * FROM Pengguna WHERE id IN (:userID)")
    List<Pengguna> loadAllByIds1(int[] userID);

    @Query("SELECT * FROM Pengguna WHERE id= :userID AND password = :password LIMIT 1")
    Pengguna findByIDAndPassword(Integer userID, String password);

    //nanti diubah
    @Query("SELECT nama FROM Pengguna WHERE id= :userID LIMIT 1")
    String namaPengguna(String userID);

    @Query("SELECT id FROM Pengguna WHERE nama = :nama LIMIT 1")
    Integer findByID(String nama);

    @Query("SELECT tipe FROM Pengguna WHERE nama = :nama LIMIT 1")
    String findTipe(String nama);

    @Query("SELECT * FROM Pengguna WHERE nama = :nama LIMIT 1")
    Pengguna findByNama(String nama);

    @Insert
    void insertAll(Pengguna... users);

    @Delete
    void delete(Pengguna user);
}
