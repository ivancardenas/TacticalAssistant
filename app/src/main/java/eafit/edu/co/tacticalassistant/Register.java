package eafit.edu.co.tacticalassistant;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import eafit.edu.co.tacticalassistant.data.User;
import eafit.edu.co.tacticalassistant.data.UsersContract;

import static eafit.edu.co.tacticalassistant.DBHelper.DATABASE_NAME;


public class Register extends AppCompatActivity {

    EditText et1,et2,et3,et4,et5,et6,et7;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        et2= (EditText) findViewById(R.id.etuser);
        et3= (EditText) findViewById(R.id.etPass);
        et1= (EditText) findViewById(R.id.etName);
        et4= (EditText) findViewById(R.id.etPosition);
        et5= (EditText) findViewById(R.id.etphonenumber);
        et6= (EditText) findViewById(R.id.etBio);
        et7= (EditText) findViewById(R.id.etAvatar);
    }
    public void register(View view){
        DBHelper admin=new DBHelper(this,DATABASE_NAME,null,1);
        SQLiteDatabase db=admin.getWritableDatabase();
        String user=et2.getText().toString();
        String pass=et3.getText().toString();
        String name=et1.getText().toString();
        String position=et4.getText().toString();
        String phone=et5.getText().toString();
        String bio=et6.getText().toString();
        String avatar=et7.getText().toString();


        ContentValues values=new ContentValues();
        values.put("id",0);
        values.put("user",user);
        values.put("password",pass);
        values.put("name", name);
        values.put("position", position);
        values.put("phoneNumber", phone);
        values.put("bio", bio);
        values.put("avatar", avatar);

        db.insert("User",null,values);
        db.close();

        Intent ven=new Intent(this,Main.class);
        startActivity(ven);
    }
    public void cancel(View view){
        finish();
    }
}

