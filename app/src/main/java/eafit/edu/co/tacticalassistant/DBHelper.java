package eafit.edu.co.tacticalassistant;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import eafit.edu.co.tacticalassistant.data.User;
import eafit.edu.co.tacticalassistant.data.UsersContract.UserEntry;


public class DBHelper  extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Users.db";


    public DBHelper(Context context, String institute, SQLiteDatabase.CursorFactory factory, int i) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("CREATE TABLE Users(code INTEGER PRIMARY KEY AUTOINCREMENT,user TEXT,password TEXT)");
        db.execSQL("CREATE TABLE " + UserEntry.TABLE_NAME + " ("
                + UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UserEntry.ID + " INTEGER NOT NULL,"
                + UserEntry.PASSWORD + " TEXT NOT NULL,"
                + UserEntry.USER + " TEXT NOT NULL,"
                + UserEntry.NAME + " TEXT NOT NULL,"
                + UserEntry.POSITION + " TEXT NOT NULL,"
                + UserEntry.PHONE_NUMBER + " TEXT NOT NULL,"
                + UserEntry.BIO + " TEXT NOT NULL,"
                + UserEntry.AVATAR + " TEXT,"
                + "UNIQUE (" + UserEntry.ID + "))");
        db.execSQL("INSERT INTO User VALUES (01, 'admin123*', 'admin', 'lina', 'administrator', '1234567', 'khjggu', '')");
        mockData(db);
    }

    private void mockData(SQLiteDatabase sqLiteDatabase) {
        mockUser(sqLiteDatabase, new User("Bangulo", "Brayan Angulo", "Defensa",
                "300 200 1111", "Brayan Angulo es un futbolista colombiano nacionalizado español. Juega de Lateral izquierdo y su equipo actual es el Jaguares de Chiapas de la Liga Mexicana.",
                "carlos_perez.jpg"));

    }

    public long mockUser(SQLiteDatabase db, User user) {
        return db.insert(UserEntry.TABLE_NAME, null, user.toContentValues());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("CREATE TABLE User(code INTEGER PRIMARY KEY AUTOINCREMENT,user TEXT,password TEXT)");
        //db.execSQL("INSERT INTO Users VALUES(01,'admin','admin123*')");
    }

    public long saveUser(User user) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                UserEntry.TABLE_NAME,
                null,
                user.toContentValues());

    }

    public Cursor getAllUsers() {
        return getReadableDatabase()
                .query(
                        UserEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getUserById(String userId) {
        Cursor c = getReadableDatabase().query(
                UserEntry.TABLE_NAME,
                null,
                UserEntry.ID + " LIKE ?",
                new String[]{userId},
                null,
                null,
                null);
        return c;
    }

    public int deleteUser(String userId) {
        return getWritableDatabase().delete(
                UserEntry.TABLE_NAME,
                UserEntry.ID + " LIKE ?",
                new String[]{userId});
    }

    public int updateUser(User user, String userId) {
        return getWritableDatabase().update(
                UserEntry.TABLE_NAME,
                user.toContentValues(),
                UserEntry.ID + " LIKE ?",
                new String[]{userId}
        );
    }

}
