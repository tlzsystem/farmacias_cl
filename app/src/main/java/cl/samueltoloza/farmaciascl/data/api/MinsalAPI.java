package cl.samueltoloza.farmaciascl.data.api;

import java.util.List;

import cl.samueltoloza.farmaciascl.data.model.Pharmacy;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MinsalAPI {


    @GET("getLocalesTurnos.php")
    Call<List<Pharmacy>> getLocalesTurnos();



}
