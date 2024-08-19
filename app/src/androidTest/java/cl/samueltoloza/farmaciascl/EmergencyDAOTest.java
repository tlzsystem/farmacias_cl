package cl.samueltoloza.farmaciascl;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import cl.samueltoloza.farmaciascl.data.db.AppDataBase;
import cl.samueltoloza.farmaciascl.data.db.EmergencyContactDAO;
import cl.samueltoloza.farmaciascl.data.model.EmergencyContact;

@RunWith(AndroidJUnit4.class)
public class EmergencyDAOTest {

    private EmergencyContactDAO dao;
    private AppDataBase dataBase;

    @Before
    public void setup(){
        Context context = ApplicationProvider.getApplicationContext();
        dataBase = Room.inMemoryDatabaseBuilder(context, AppDataBase.class).allowMainThreadQueries().build();
        dao = dataBase.emergencyContactDAO();
    }

    @After
    public void close(){
        dataBase.close();
    }

    @Test
    public void testPharmacyCreation_success(){
        dao.insertAll(Arrays.asList(
                new EmergencyContact(1, "Contact 1","111",""),
                new EmergencyContact(2, "Contact 2","222",""),
                new EmergencyContact(3, "Contact 3","333",""),
                new EmergencyContact(4, "Contact 4","444","")
        ));

        List<EmergencyContact> list = dao.getAll();
        assertEquals(list.size(), 4);
        assertEquals(list.get(0).getName(), "Contact 1");

    }

}
