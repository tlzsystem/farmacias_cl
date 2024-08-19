package cl.samueltoloza.farmaciascl.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import cl.samueltoloza.farmaciascl.data.model.EmergencyContact;

@Dao
public interface EmergencyContactDAO {

    @Query("SELECT * FROM emergency_contact")
    List<EmergencyContact> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<EmergencyContact> entities);


}
