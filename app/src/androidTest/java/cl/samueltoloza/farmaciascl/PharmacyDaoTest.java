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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cl.samueltoloza.farmaciascl.data.db.AppDataBase;
import cl.samueltoloza.farmaciascl.data.db.PharmacyDAO;
import cl.samueltoloza.farmaciascl.data.model.Pharmacy;
import cl.samueltoloza.farmaciascl.utils.Util;

@RunWith(AndroidJUnit4.class)
public class PharmacyDaoTest {

    private PharmacyDAO dao;
    private AppDataBase dataBase;
    private Date currentDate;

    @Before
    public void setup(){
        Context context = ApplicationProvider.getApplicationContext();
        dataBase = Room.inMemoryDatabaseBuilder(context, AppDataBase.class).allowMainThreadQueries().build();
        dao = dataBase.pharmacyDAO();
        currentDate = Util.getCurrentDate();
    }

    @After
    public void close(){
        dataBase.close();
    }

    @Test
    public void testPharmacyCreation_success(){
        dao.insertAll(Arrays.asList(
                new Pharmacy(1, "Pharmacy 1", currentDate),
                new Pharmacy(2, "Pharmacy 2", currentDate),
                new Pharmacy(3, "Pharmacy 3", currentDate),
                new Pharmacy(4, "TestPharmacy 4", currentDate)
        ));

        List<Pharmacy> list = dao.getAll();
        assertEquals(list.size(), 4);
        assertEquals(list.get(0).getLocal_nombre(), "Pharmacy 1");

    }

    @Test
    public void testGetPharmacy_byCreatedDate() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
        Date date = dateFormat.parse("2024-01-01");
        dao.insertAll(Arrays.asList(
                new Pharmacy(5, "Pharmacy 5", currentDate),
                new Pharmacy(6, "Pharmacy 6", currentDate),
                new Pharmacy(7, "Pharmacy 7", date),
                new Pharmacy(8, "Pharmacy 8", date)
        ));
        List<Pharmacy> list = dao.getByCreatedDate(date);
        assertEquals(list.size(), 2);
    }

    @Test
    public void testGetPharmacy_getByLikeCityName(){
        dao.insertAll(Arrays.asList(
                new Pharmacy(9, "Pharmacy1" ,"AAA"),
                new Pharmacy(10, "Pharmacy2","BBB" ),
                new Pharmacy(11, "Pharmacy3","CCC" ),
                new Pharmacy(12, "Pharmacy4" ,"DDD")
        ));
        List<Pharmacy> list = dao.getByLikeCityName("AAA");
        assertEquals(list.size(), 1);
        assertEquals(list.get(0).getLocal_nombre(), "Pharmacy1");
    }

    @Test
    public void testDeleteAll(){
        dao.insertAll(Arrays.asList(
                new Pharmacy(1, "Pharmacy 1", currentDate),
                new Pharmacy(2, "Pharmacy 2", currentDate),
                new Pharmacy(3, "Pharmacy 3", currentDate),
                new Pharmacy(4, "TestPharmacy 4", currentDate)
        ));
        List<Pharmacy> list = dao.getAll();
        assertEquals(list.size(), 4);
        dao.deleteAll();
        assertEquals(dao.getAll().size(), 0);
    }


}
