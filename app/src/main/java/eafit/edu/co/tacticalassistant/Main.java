package eafit.edu.co.tacticalassistant;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main extends AppCompatActivity {

    EditText et1,et2;
    private Cursor file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        et1= (EditText) findViewById(R.id.etUser);
        et2= (EditText) findViewById(R.id.etPassword);
    }
    public void ingress(View v){
        DBHelper admin= new DBHelper(this,"institute",null,1);
        SQLiteDatabase db=admin.getWritableDatabase();
        String user1=et1.getText().toString();
        String password1=et2.getText().toString();
        file = db.rawQuery("SELECT user,password from User where user='"+user1+"' and password='"+password1+"'", null);
        if (file.moveToFirst()){
            String user= file.getString(0);
            String pass= file.getString(1);
            if (user.equals(user)&&pass.equals(pass)){
                Intent ven=new Intent(this,Menu.class);
                startActivity(ven);
                et1.setText("");
                et2.setText("");
            }else{
                Toast.makeText(Main.this, "Incorrect user or password", Toast.LENGTH_LONG).show();
                et1.setText("");
                et2.setText("");
            }
        }
    }
    public void registry(View v){
        Intent ven=new Intent(this,Register.class);
        startActivity(ven);
    }
}
