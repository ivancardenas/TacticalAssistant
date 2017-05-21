package eafit.edu.co.tacticalassistant.data;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.UUID;

import eafit.edu.co.tacticalassistant.data.UsersContract.UserEntry;

public class User{
    private String id;
    private String password;
    private String user;
    private String name;
    private String position;
    private String phoneNumber;
    private String bio;
    private String avatar;

    public User(String users, String user, String password, String name,
                String position, String phoneNumber) {
        this.id = UUID.randomUUID().toString();
        this.password = password;
        this.user= user;
        this.name = name;
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.bio = bio;
    }

    public User(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex(UserEntry.ID));
        password = cursor.getString(cursor.getColumnIndex(UserEntry.PASSWORD));
        user = cursor.getString(cursor.getColumnIndex(UserEntry.USER));
        name = cursor.getString(cursor.getColumnIndex(UserEntry.NAME));
        position = cursor.getString(cursor.getColumnIndex(UserEntry.POSITION));
        phoneNumber = cursor.getString(cursor.getColumnIndex(UserEntry.PHONE_NUMBER));
        bio = cursor.getString(cursor.getColumnIndex(UserEntry.BIO));
        avatar = cursor.getString(cursor.getColumnIndex(UserEntry.AVATAR));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(UserEntry.ID, id);
        values.put(UserEntry.PASSWORD, password);
        values.put(UserEntry.USER, user);
        values.put(UserEntry.NAME, name);
        values.put(UserEntry.POSITION, position);
        values.put(UserEntry.PHONE_NUMBER, phoneNumber);
        values.put(UserEntry.BIO, bio);
        values.put(UserEntry.AVATAR, avatar);
        return values;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {return password; }

    public String getUser() {return user; }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBio() {
        return bio;
    }

    public String getAvatar() {
        return avatar;
    }
}
