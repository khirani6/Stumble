package gameofphones.gatech.stumble;

public class UserTracker {
    public static final UserTracker ourInstance = new UserTracker();
    private User mUser;

    public static UserTracker getInstance() {
        return ourInstance;
    }

    public UserTracker() {
    }

    public User getCurrentUser() {
        return mUser;
    }

    public void setCurrentUser(User user) {
        mUser = user;
    }
}
