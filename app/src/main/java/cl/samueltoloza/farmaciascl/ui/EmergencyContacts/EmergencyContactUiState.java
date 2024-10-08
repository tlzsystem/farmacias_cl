package cl.samueltoloza.farmaciascl.ui.EmergencyContacts;

public class EmergencyContactUiState {

    private String name;
    private String phoneNumber;

    public EmergencyContactUiState(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public EmergencyContactUiState() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
