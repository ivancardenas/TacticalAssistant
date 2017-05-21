package eafit.edu.co.tacticalassistant.addedituser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import eafit.edu.co.tacticalassistant.R;
import eafit.edu.co.tacticalassistant.users.UsersActivity;

public class AddEditUserActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_USER = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String userId = getIntent().getStringExtra(UsersActivity.EXTRA_USER_ID);

        setTitle(userId == null ? "Add Player" : "Edit Player");

        AddEditUserFragment addEditUserFragment = (AddEditUserFragment)
                getSupportFragmentManager().findFragmentById(R.id.add_edit_user_container);
        if (addEditUserFragment == null) {
            addEditUserFragment = AddEditUserFragment.newInstance(userId);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.add_edit_user_container, addEditUserFragment)
                    .commit();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
