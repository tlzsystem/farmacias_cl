package cl.samueltoloza.farmaciascl.data.repository;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import cl.samueltoloza.farmaciascl.data.api.MinsalAPI;
import cl.samueltoloza.farmaciascl.data.db.PharmacyDAO;
import cl.samueltoloza.farmaciascl.data.model.Pharmacy;
import cl.samueltoloza.farmaciascl.exception.MinsalAPIConnectionException;
import cl.samueltoloza.farmaciascl.exception.MinsalAPIContentException;
import cl.samueltoloza.farmaciascl.utils.Result;
import cl.samueltoloza.farmaciascl.utils.Util;
import retrofit2.Call;
import retrofit2.Response;

public class PharmacyRepository {

    private MinsalAPI minsalAPI;
    private PharmacyDAO pharmacyDAO;

    @Inject
    public PharmacyRepository(MinsalAPI minsalAPI, PharmacyDAO pharmacyDAO) {
        this.minsalAPI = minsalAPI;
        this.pharmacyDAO = pharmacyDAO;
    }

    public Result<List<Pharmacy>> getPharmacys()  {

        Date currentDate = Util.getCurrentDate();
        List<Pharmacy> pharmaciesFromLocal = pharmacyDAO.getByCreatedDate(Util.getCurrentDate());
        if (pharmaciesFromLocal != null && !pharmaciesFromLocal.isEmpty()){
            return Result.success(pharmaciesFromLocal);
        }else {
            Call<List<Pharmacy>> call = minsalAPI.getLocalesTurnos();
            try {
                Response<List<Pharmacy>> response = call.execute();
                if (response.isSuccessful()){
                    List<Pharmacy> pharmaciesFromRemote = response.body();
                    if(!pharmaciesFromRemote.isEmpty()){
                        pharmaciesFromRemote.stream().forEach(pharmacy -> pharmacy.setCreated(currentDate));
                        pharmacyDAO.deleteAll();
                        pharmacyDAO.insertAll(pharmaciesFromRemote);
                        return Result.success(pharmaciesFromRemote);
                    }else {
                        return Result.error(new MinsalAPIContentException("API Content error"));
                    }
                }else{
                    return Result.error(new MinsalAPIConnectionException("API error"));
                }
            } catch (IOException e) {
                return Result.error(e);
            }

        }
    }

    public List<Pharmacy> getPharmacysByCityName(String name){
        return pharmacyDAO.getByLikeCityName(name);
    }

}
