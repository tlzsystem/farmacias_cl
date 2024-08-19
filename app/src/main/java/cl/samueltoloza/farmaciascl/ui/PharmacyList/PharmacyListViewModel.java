package cl.samueltoloza.farmaciascl.ui.PharmacyList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cl.samueltoloza.farmaciascl.data.model.Pharmacy;
import cl.samueltoloza.farmaciascl.data.repository.PharmacyRepository;
import cl.samueltoloza.farmaciascl.utils.Result;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PharmacyListViewModel extends ViewModel {

    private PharmacyRepository pharmacyRepository;
    private MutableLiveData<Result<List<PharmacyUiState>>> pharmaciesList = new MutableLiveData<>();

    @Inject
    public PharmacyListViewModel(PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }



    public void loadPharmacies(){
        new Thread(()->{
            Result<List<Pharmacy>> pharmacies = pharmacyRepository.getPharmacys();

            if (pharmacies.isSuccess()){
                List<PharmacyUiState> pharmacyUiStates = this.transformPharmacyEntityToUiState(pharmacies.getData());
                pharmaciesList.postValue(Result.success(pharmacyUiStates));
            }else {
                pharmaciesList.postValue(Result.error(pharmacies.getError()));
            }

        }).start();

    }

    public LiveData<Result<List<PharmacyUiState>>> getPharmaciesList(){
        return this.pharmaciesList;
    }

    public void findPharmaciesByCityName(String name){

        new Thread(()->{

            List<Pharmacy> pharmacies = pharmacyRepository.getPharmacysByCityName(name);
            List<PharmacyUiState> pharmacyUiStates = this.transformPharmacyEntityToUiState(pharmacies);
            pharmaciesList.postValue(Result.success(pharmacyUiStates));
        }).start();

    }

    public List<PharmacyUiState> transformPharmacyEntityToUiState(List<Pharmacy> entities){
        List<PharmacyUiState> pharmacyUiStates = new ArrayList<>();
        for(Pharmacy pharmacy: entities){
            PharmacyUiState pharmacyUiState = new PharmacyUiState();
            pharmacyUiState.setName(pharmacy.getLocal_nombre());
            pharmacyUiState.setAddress(pharmacy.getLocal_direccion());
            pharmacyUiState.setCity(pharmacy.getComuna_nombre());
            pharmacyUiState.setOpenTime(pharmacy.getFuncionamiento_hora_apertura());
            pharmacyUiState.setCloseTime(pharmacy.getFuncionamiento_hora_cierre());
            pharmacyUiState.setPhone(pharmacy.getLocal_telefono());
            pharmacyUiState.setLat(pharmacy.getLocal_lat());
            pharmacyUiState.setLng(pharmacy.getLocal_lng());
            pharmacyUiStates.add(pharmacyUiState);
        }
        return pharmacyUiStates;
    }
}
