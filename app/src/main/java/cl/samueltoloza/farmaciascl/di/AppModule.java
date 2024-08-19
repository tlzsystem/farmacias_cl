package cl.samueltoloza.farmaciascl.di;


import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import cl.samueltoloza.farmaciascl.BuildConfig;
import cl.samueltoloza.farmaciascl.data.api.MinsalAPI;
import cl.samueltoloza.farmaciascl.data.db.AppDataBase;
import cl.samueltoloza.farmaciascl.data.db.EmergencyContactDAO;
import cl.samueltoloza.farmaciascl.data.db.PharmacyDAO;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public static MinsalAPI provideMinsalAPI(){
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MinsalAPI.class);
    }

    @Provides
    @Singleton
    public static AppDataBase provideAppDatabase(@ApplicationContext Context context){
        return Room.databaseBuilder(context, AppDataBase.class, "farmaciascl-db").fallbackToDestructiveMigration().build();
    }

    @Provides
    @Singleton
    public static PharmacyDAO providePharmacyDAO(AppDataBase appDataBase){
        return appDataBase.pharmacyDAO();
    }

    @Provides
    @Singleton
    public static EmergencyContactDAO provideEmergencyContactDAO(AppDataBase appDataBase){
        return  appDataBase.emergencyContactDAO();
    }










}
