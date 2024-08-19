package cl.samueltoloza.farmaciascl.data.db;

import androidx.room.TypeConverter;

import java.util.Date;

public class Converter {

    @TypeConverter
    public static Date toDate(Long dateLong){
        return dateLong == null ? null: new Date(dateLong);
    }

    @TypeConverter
    public static Long fromDate(Date date){
        return date == null ? null : date.getTime();
    }

}
