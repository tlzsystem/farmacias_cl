package cl.samueltoloza.farmaciascl.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import cl.samueltoloza.farmaciascl.data.model.EmergencyContact;
import cl.samueltoloza.farmaciascl.data.model.Pharmacy;

@Database(entities = {Pharmacy.class, EmergencyContact.class}, version = 5, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class AppDataBase extends RoomDatabase {

    public abstract PharmacyDAO pharmacyDAO();

    public abstract EmergencyContactDAO emergencyContactDAO();

}
