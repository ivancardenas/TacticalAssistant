package eafit.edu.co.tacticalassistant.data;

import android.provider.BaseColumns;

public class UsersContract {

    public static abstract class UserEntry implements BaseColumns {
        public static final String TABLE_NAME ="user";

        public static final String _ID = "_id";
        public static final String ID = "id";
        public static final String PASSWORD = "password";
        public static final String USER = "user";
        public static final String NAME = "name";
        public static final String POSITION = "position";
        public static final String PHONE_NUMBER = "phoneNumber";
        public static final String BIO = "bio";
        public static final String AVATAR = "avatar";
    }
}
