package gameofphones.gatech.stumble;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class User {

    @SerializedName("first_name")
    private String mFirstName;

    @SerializedName("last_name")
    private String mLastName;
    @SerializedName("email")
    private String mEmail;
    @SerializedName("emergency_contacts")
    private List<EmergencyContact> mEmergencyContacts;

    public User(String firstName, String lastName, String email) {
        mFirstName = firstName;
        mLastName = lastName;
        mEmail = email;
    }

    public User(String firstName, String lastName, String email,
                List<EmergencyContact> emergencyContacts) {
        mFirstName = firstName;
        mLastName = lastName;
        mEmail = email;
        mEmergencyContacts = emergencyContacts;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public List<EmergencyContact> getEmergencyContacts() {
        return mEmergencyContacts;
    }

    public void setEmergencyContacts(List<EmergencyContact> emergencyContacts) {
        mEmergencyContacts = emergencyContacts;
    }

    public void addEmergencyContact(EmergencyContact ec) {
        if (mEmergencyContacts == null) {
            mEmergencyContacts = new ArrayList<>();
        }
        mEmergencyContacts.add(ec);
    }

    public boolean removeEmergencyContact(EmergencyContact ec) {
        return mEmergencyContacts.remove(ec);
    }
}