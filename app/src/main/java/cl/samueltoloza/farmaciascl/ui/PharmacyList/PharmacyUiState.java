package cl.samueltoloza.farmaciascl.ui.PharmacyList;


public class PharmacyUiState {

    private String name;
    private String city;
    private String address;
    private String openTime;
    private String closeTime;
    private String phone;
    private String lat;
    private String lng;

    public PharmacyUiState() {
    }

    public PharmacyUiState(String name) {
        this.name = name;
    }

    public String getFullAddress(){
        return String.format("%s , %s", this.getAddress(), this.getCity());
    }
    public String getFullSchedule(){
        return String.format("%s - %s", this.getOpenTime(), this.getCloseTime());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
