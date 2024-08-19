package cl.samueltoloza.farmaciascl.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "emergency_contact")
public class EmergencyContact {

    @NonNull
    @PrimaryKey
    @ColumnInfo(name="id")
    private int id;
    @ColumnInfo(name="name")
    private String name;
    @ColumnInfo(name="phone")
    private String phone;
    @ColumnInfo(name="web")
    private String webSite;

    public EmergencyContact() {
    }

    public EmergencyContact(int id, String name, String phone, String webSite) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.webSite = webSite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }
}
