package eafit.edu.co.tacticalassistant;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


public class Register extends AppCompatActivity {

    EditText et1,et2,et3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        //et1= (EditText) findViewById(R.id.etcod);
        et2= (EditText) findViewById(R.id.etuser);
        et3= (EditText) findViewById(R.id.etPass);
    }
    public void register(View view){
        DBHelper admin=new DBHelper(this,"institute",null,1);
        SQLiteDatabase db=admin.getWritableDatabase();
        //String code=et1.getText().toString();
        String user=et2.getText().toString();
        String pass=et3.getText().toString();

        ContentValues values=new ContentValues();
        //values.put("code",code);
        values.put("user",user);
        values.put("password",pass);

        db.insert("Users",null,values);
        db.close();

        Intent ven=new Intent(this,Main.class);
        startActivity(ven);
    }
    public void cancel(View view){
        finish();
    }

}

