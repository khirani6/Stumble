package gameofphones.gatech.stumble;

public class UserTracker {
    private static final UserTracker ourInstance = new UserTracker();
    private User mUser;

    public static UserTracker getInstance() {
        return ourInstance;
    }

    private UserTracker() {
    }

    public User getCurrentUser() {
        return mUser;
    }

    public void setCurrentUser(User user) {
        mUser = user;
    }
}
