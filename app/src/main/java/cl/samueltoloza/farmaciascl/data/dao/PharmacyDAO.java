package cl.samueltoloza.farmaciascl.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import cl.samueltoloza.farmaciascl.data.model.Pharmacy;

@Dao
public interface PharmacyDAO {

    @Query("SELECT * FROM pharmacy")
    LiveData<List<Pharmacy>> getAll();


}
