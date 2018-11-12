package gameofphones.gatech.stumble;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class EmergencyContact {

    @SerializedName("firstName")
    private String mFirstName;

    @SerializedName("lastName")
    private String mLastName;

    @SerializedName("phoneNumber")
    @Nullable private String mPhoneNumber;

    /**
     * The phone numbers are nullable because this gives the user the most flexibility. They can
     * indicate a voice phone number and leave the sms phone number blank to say "only call this
     * number and don't text it" and vice versa.
     */
    public EmergencyContact(String firstName, String lastName, @Nullable String PhoneNumber) {
        mFirstName = firstName;
        mLastName = lastName;
        mPhoneNumber = PhoneNumber;
    }

    /**
     * This constructor could be helpful in the case that last names aren't applicable.
     * For example, if the emergency contact is an organization or hospital.
     */
    public EmergencyContact(String firstName, @Nullable String PhoneNumber) {
        mFirstName = firstName;
        mPhoneNumber = PhoneNumber;
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

    @Nullable
    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(@Nullable String PhoneNumber) {
        mPhoneNumber = PhoneNumber;
    }
}