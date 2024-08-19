package cl.samueltoloza.farmaciascl;

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

import cl.samueltoloza.farmaciascl.data.db.EmergencyContactDAO;
import cl.samueltoloza.farmaciascl.data.model.EmergencyContact;
import cl.samueltoloza.farmaciascl.data.repository.EmergencyContactRepository;
import cl.samueltoloza.farmaciascl.ui.EmergencyContacts.EmergencyContactUiState;
import cl.samueltoloza.farmaciascl.ui.EmergencyContacts.EmergencyContactViewModel;
import cl.samueltoloza.farmaciascl.utils.Result;

@RunWith(MockitoJUnitRunner.class)
public class EmergencyContactViewModelTest {

    @InjectMocks
    private EmergencyContactRepository emergencyContactRepository;

    @Mock
    private EmergencyContactDAO emergencyContactDAO;

    private EmergencyContactViewModel emergencyContactViewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    @Test
    public void getEmergencyContacts_success(){

        List<EmergencyContact> emergencyContactList = Arrays.asList(
                new EmergencyContact(1, "Contact 1","111",""),
                new EmergencyContact(2, "Contact 2","222",""),
                new EmergencyContact(3, "Contact 3","333",""),
                new EmergencyContact(4, "Contact 4","444","")
        );

        when(emergencyContactDAO.getAll()).thenReturn(emergencyContactList);

        emergencyContactViewModel = new EmergencyContactViewModel(emergencyContactRepository);
        emergencyContactViewModel.loadEmergencyContacts();

        LiveData<Result<List<EmergencyContactUiState>>> liveData = emergencyContactViewModel.getEmergencyContactsData();
        Observer<Result<List<EmergencyContactUiState>>> observer = mock(Observer.class);
        emergencyContactViewModel.getEmergencyContactsData().observeForever(observer);
        assertTrue(liveData.getValue().isSuccess());



    }


}
