package cl.samueltoloza.farmaciascl.data.repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cl.samueltoloza.farmaciascl.data.db.EmergencyContactDAO;
import cl.samueltoloza.farmaciascl.data.model.EmergencyContact;
import cl.samueltoloza.farmaciascl.utils.Result;

public class EmergencyContactRepository {

    private EmergencyContactDAO dao;

    @Inject
    public EmergencyContactRepository(EmergencyContactDAO dao) {
        this.dao = dao;
    }

    public Result<List<EmergencyContact>> getAll(){

        try{
            return Result.success( dao.getAll());
        }catch (Exception e){
            return Result.error(e);
        }

    }

    public void insertDefaultData(){

        List<EmergencyContact> list = new ArrayList<>();
        list.add(new EmergencyContact(1, "CONAF","130",""));
        list.add(new EmergencyContact(2, "Servicio de Atención Médico de Urgencias (SAMU)","131",""));
        list.add(new EmergencyContact(3, "Bomberos","132",""));
        list.add(new EmergencyContact(4, "Carabineros","133",""));
        list.add(new EmergencyContact(5, "PDI","134",""));
        list.add(new EmergencyContact(6, "Fono Drogas","135",""));
        list.add(new EmergencyContact(7, "Cuerpo de Socorro Andino","136",""));
        list.add(new EmergencyContact(8, "Búsqueda y salvamento marítimo","137",""));
        list.add(new EmergencyContact(9, "Servicio de Búsqueda y Salvamento Aéreo","138",""));
        list.add(new EmergencyContact(10, "Informaciones policiales","139",""));
        list.add(new EmergencyContact(11, "Fono niños","147",""));
        list.add(new EmergencyContact(12, "Fono familia","149",""));
        list.add(new EmergencyContact(13, "ACHS","1404",""));
        list.add(new EmergencyContact(14, "Mutual Seguridad","1407",""));
        list.add(new EmergencyContact(15, "Emergencia Metro","1411",""));
        list.add(new EmergencyContact(16, "Emergencia MetroGas","1442",""));
        list.add(new EmergencyContact(17, "Fono Mujer","1455",""));
        list.add(new EmergencyContact(18, "Prevención del suicidio","4141",""));
        dao.insertAll(list);

    }


}
