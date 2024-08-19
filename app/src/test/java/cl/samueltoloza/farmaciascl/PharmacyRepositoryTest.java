package cl.samueltoloza.farmaciascl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cl.samueltoloza.farmaciascl.data.api.MinsalAPI;
import cl.samueltoloza.farmaciascl.data.db.PharmacyDAO;
import cl.samueltoloza.farmaciascl.data.model.Pharmacy;
import cl.samueltoloza.farmaciascl.data.repository.PharmacyRepository;
import cl.samueltoloza.farmaciascl.utils.Result;
import cl.samueltoloza.farmaciascl.utils.Util;
import retrofit2.Call;
import retrofit2.Response;

@RunWith(MockitoJUnitRunner.class)
public class PharmacyRepositoryTest {

    @Mock
    private PharmacyDAO pharmacyDAO;

    @Mock
    private Call<List<Pharmacy>> mockCall;

    @InjectMocks
    private PharmacyRepository repository;

    @Mock
    private MinsalAPI minsalAPI;


    @Test
    public void testGetPharmacies_fromLocalData(){

        List<Pharmacy> localData = Arrays.asList(new Pharmacy("Pharmacy 1"),
                new Pharmacy("Pharmacy 2"));

        when(pharmacyDAO.getByCreatedDate(Util.getCurrentDate())).thenReturn(localData);
        Result<List<Pharmacy>> result = repository.getPharmacys();
        assertTrue(result.isSuccess());
        assertTrue(result.getError()==null);
        assertEquals(result.getData(), localData);

    }

    @Test
    public void testGetPharmacies_fromRemote() throws IOException {

        List<Pharmacy> remoteData = Arrays.asList(new Pharmacy("Pharmacy 1"),
                new Pharmacy("Pharmacy 2"));

        when(pharmacyDAO.getByCreatedDate(Util.getCurrentDate())).thenReturn(Collections.emptyList());
        when(minsalAPI.getLocalesTurnos()).thenReturn(mockCall);
        when(mockCall.execute()).thenReturn(Response.success(remoteData));

        Result<List<Pharmacy>> result = repository.getPharmacys();

        verify(pharmacyDAO).getByCreatedDate(Util.getCurrentDate());
        verify(minsalAPI).getLocalesTurnos();
        verify(mockCall).execute();

        assertTrue(result.isSuccess());
        assertEquals(result.getData(), remoteData);

        verify(pharmacyDAO).insertAll(remoteData);

    }

    @Test
    public void testGetPharmacies_fromRemoteFailure() throws IOException {

        when(pharmacyDAO.getByCreatedDate(Util.getCurrentDate())).thenReturn(Collections.emptyList());

        when(minsalAPI.getLocalesTurnos()).thenReturn(mockCall);
        when(mockCall.execute()).thenThrow(new IOException("Network error"));

        Result<List<Pharmacy>> result = repository.getPharmacys();

        verify(pharmacyDAO).getByCreatedDate(Util.getCurrentDate());
        verify(minsalAPI).getLocalesTurnos();
        verify(mockCall).execute();

        assertTrue(result.isError());

        verify(pharmacyDAO, never()).insertAll(anyList());




    }

}
