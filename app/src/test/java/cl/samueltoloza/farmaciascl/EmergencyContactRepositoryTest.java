package cl.samueltoloza.farmaciascl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

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
import cl.samueltoloza.farmaciascl.utils.Result;

@RunWith(MockitoJUnitRunner.class)
public class EmergencyContactRepositoryTest {

    @Mock
    private EmergencyContactDAO emergencyContactDAO;

    @InjectMocks
    private EmergencyContactRepository emergencyContactRepository;

    @Test
    public void testGetAll(){

        List<EmergencyContact> list = Arrays.asList(
                new EmergencyContact(1, "Contact 1","111",""),
                new EmergencyContact(2, "Contact 2","222",""),
                new EmergencyContact(3, "Contact 3","333",""),
                new EmergencyContact(4, "Contact 4","444","")
        );

        when(emergencyContactDAO.getAll()).thenReturn(list);

        Result<List<EmergencyContact>> result = emergencyContactRepository.getAll();
        assertTrue(result.isSuccess());
        assertEquals(result.getData().size() , 4);

    }





}
