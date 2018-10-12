package gameofphones.gatech.stumble;

import android.support.annotation.Nullable;

public class EmergencyContact {

    private String mFirstName;
    private String mLastName;
    @Nullable private String mVoicePhoneNumber;
    @Nullable private String mSMSPhoneNumber;

    /**
     * The phone numbers are nullable because this gives the user the most flexibility. They can
     * indicate a voice phone number and leave the sms phone number blank to say "only call this
     * number and don't text it" and vice versa.
     */
    public EmergencyContact(String firstName, String lastName, @Nullable String voicePhoneNumber,
                            @Nullable String smsPhoneNumber) {
        mFirstName = firstName;
        mLastName = lastName;
        mVoicePhoneNumber = voicePhoneNumber;
        mSMSPhoneNumber = smsPhoneNumber;
    }

    /**
     * This constructor could be helpful in the case that last names aren't applicable.
     * For example, if the emergency contact is an organization or hospital.
     */
    public EmergencyContact(String firstName, @Nullable String voicePhoneNumber,
                            @Nullable String SMSPhoneNumber) {
        mFirstName = firstName;
        mVoicePhoneNumber = voicePhoneNumber;
        mSMSPhoneNumber = SMSPhoneNumber;
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
    public String getVoicePhoneNumber() {
        return mVoicePhoneNumber;
    }

    public void setVoicePhoneNumber(@Nullable String voicePhoneNumber) {
        mVoicePhoneNumber = voicePhoneNumber;
    }

    @Nullable
    public String getSMSPhoneNumber() {
        return mSMSPhoneNumber;
    }

    public void setSMSPhoneNumber(@Nullable String SMSPhoneNumber) {
        mSMSPhoneNumber = SMSPhoneNumber;
    }
}