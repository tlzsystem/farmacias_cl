package cl.samueltoloza.farmaciascl;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import cl.samueltoloza.farmaciascl.data.db.PharmacyDAO;
import cl.samueltoloza.farmaciascl.data.model.Pharmacy;
import cl.samueltoloza.farmaciascl.data.repository.PharmacyRepository;
import cl.samueltoloza.farmaciascl.ui.PharmacyList.PharmacyListViewModel;
import cl.samueltoloza.farmaciascl.ui.PharmacyList.PharmacyUiState;
import cl.samueltoloza.farmaciascl.utils.Result;
import cl.samueltoloza.farmaciascl.utils.Util;

@RunWith(MockitoJUnitRunner.class)
public class PharmacyListViewModelTest {


    @InjectMocks
    private PharmacyRepository repository;

    @Mock
    private PharmacyDAO pharmacyDAO;

    private PharmacyListViewModel pharmacyListViewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();



    @Test
    public void testPharmacyList_LoadPharmacy_Success(){

        List<Pharmacy> localPharmacyList = Arrays.asList(new Pharmacy("Pharmacy 1"),
                new Pharmacy("Pharmacy 2"));


        when(pharmacyDAO.getByCreatedDate(Util.getCurrentDate())).thenReturn(localPharmacyList);

        pharmacyListViewModel = new PharmacyListViewModel(repository);
        pharmacyListViewModel.loadPharmacies();

        LiveData<Result<List<PharmacyUiState>>> liveData = pharmacyListViewModel.getPharmaciesList();

        Observer<Result<List<PharmacyUiState>>> observer = mock(Observer.class);
        pharmacyListViewModel.getPharmaciesList().observeForever(observer);


        assertTrue(liveData.getValue().isSuccess());
        assertTrue(liveData.getValue().getData().get(0).getName()=="Pharmacy 1");



    }

    @Test
    public void testMapPharmacyEntityToUiState(){

        List<Pharmacy> pharmacyList = Arrays.asList(new Pharmacy("Pharmacy 1"),
                new Pharmacy("Pharmacy 2"));

        pharmacyListViewModel = new PharmacyListViewModel(repository);

        List<PharmacyUiState> uiStates = pharmacyListViewModel.transformPharmacyEntityToUiState(pharmacyList);
        assertEquals(uiStates.size() , pharmacyList.size());
        assertEquals(uiStates.get(0).getName(), "Pharmacy 1");


    }


}
