package cl.samueltoloza.farmaciascl.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

import cl.samueltoloza.farmaciascl.data.model.Pharmacy;

@Dao
public interface PharmacyDAO {

    @Query("SELECT * FROM pharmacy")
    List<Pharmacy> getAll();

    @Query("SELECT * FROM pharmacy WHERE created=:dateCreated")
    List<Pharmacy> getByCreatedDate(Date dateCreated);

    @Query("SELECT * FROM pharmacy WHERE comuna_nombre like :name || '%'")
    List<Pharmacy> getByLikeCityName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Pharmacy> pharmacies);

    @Query("DELETE FROM pharmacy")
    void deleteAll();


}
