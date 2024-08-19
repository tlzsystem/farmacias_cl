package cl.samueltoloza.farmaciascl.ui.EmergencyContacts;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cl.samueltoloza.farmaciascl.data.model.EmergencyContact;
import cl.samueltoloza.farmaciascl.data.repository.EmergencyContactRepository;
import cl.samueltoloza.farmaciascl.utils.Result;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EmergencyContactViewModel extends ViewModel {

    private EmergencyContactRepository repository;
    private MutableLiveData<Result<List<EmergencyContactUiState>>> emergencyContactsList = new MutableLiveData<>();

    @Inject
    public EmergencyContactViewModel(EmergencyContactRepository repository) {
        this.repository = repository;
    }

    public void loadEmergencyContacts(){

        new Thread(()->{
            Result<List<EmergencyContact>> emergencyContactsResult = repository.getAll();

            if(emergencyContactsResult.isSuccess()){
                if(emergencyContactsResult.getData().size()==0){
                    repository.insertDefaultData();
                    emergencyContactsResult = repository.getAll();
                }
            }

            List<EmergencyContactUiState> emergencyContactUiStateList
                    = transformEmergencyContactEntityToUiState(emergencyContactsResult.getData());

            emergencyContactsList.postValue(Result.success(emergencyContactUiStateList));

        }).start();

    }

    public LiveData<Result<List<EmergencyContactUiState>>> getEmergencyContactsData(){
        return this.emergencyContactsList;
    }

    public List<EmergencyContactUiState> transformEmergencyContactEntityToUiState(List<EmergencyContact> entities){

        List<EmergencyContactUiState> emergencyContactUiStateList = new ArrayList<>();
        for(EmergencyContact emergencyContact: entities){
            EmergencyContactUiState uiState
                    = new EmergencyContactUiState(emergencyContact.getName(), emergencyContact.getPhone());
            emergencyContactUiStateList.add(uiState);
        }
        return emergencyContactUiStateList;

    }


}
