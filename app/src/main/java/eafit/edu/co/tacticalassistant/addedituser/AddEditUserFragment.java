package eafit.edu.co.tacticalassistant.addedituser;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import eafit.edu.co.tacticalassistant.DBHelper;
import eafit.edu.co.tacticalassistant.data.User;
import eafit.edu.co.tacticalassistant.R;

import static eafit.edu.co.tacticalassistant.DBHelper.DATABASE_NAME;

public class AddEditUserFragment extends Fragment {
    private static final String ARG_USER_ID = "arg_user_id";

    private String mUserId;
    private DBHelper mUserDbHelper;
    private TextInputEditText mPasswordField;
    private TextInputEditText mUserField;
    private TextInputEditText mNameField;
    private TextInputEditText mPhoneNumberField;
    private TextInputEditText mPositionField;
    private TextInputEditText mBioField;
    private TextInputLayout mPasswordLabel;
    private TextInputLayout mUserLabel;
    private TextInputLayout mNameLabel;
    private TextInputLayout mPhoneNumberLabel;
    private TextInputLayout mPositionLabel;
    private TextInputLayout mBioLabel;


    public AddEditUserFragment() {
        // Required empty public constructor
    }

    public static AddEditUserFragment newInstance(String userId) {
        AddEditUserFragment fragment = new AddEditUserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USER_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUserId = getArguments().getString(ARG_USER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_edit_user, container, false);

        // Referencias UI
        FloatingActionButton mSaveButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        mUserField = (TextInputEditText) root.findViewById(R.id.et_user);
        mPasswordField = (TextInputEditText) root.findViewById(R.id.et_password);
        mNameField = (TextInputEditText) root.findViewById(R.id.et_name);
        mPhoneNumberField = (TextInputEditText) root.findViewById(R.id.et_phone_number);
        mPositionField = (TextInputEditText) root.findViewById(R.id.et_position);
        mBioField = (TextInputEditText) root.findViewById(R.id.et_bio);
        mUserLabel = (TextInputLayout) root.findViewById(R.id.et_user);
        mNameLabel = (TextInputLayout) root.findViewById(R.id.til_name);
        mPositionLabel = (TextInputLayout) root.findViewById(R.id.et_position);
        mPhoneNumberLabel = (TextInputLayout) root.findViewById(R.id.til_phone_number);
        //mSpecialtyLabel = (TextInputLayout) root.findViewById(R.id.til_specialty);
        mBioLabel = (TextInputLayout) root.findViewById(R.id.til_bio);

        // Eventos
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEditUser();
            }
        });

        mUserDbHelper = new DBHelper(getActivity(),DATABASE_NAME, null, 1);

        // Carga de datos
        if (mUserId != null) {
            loadUser();
        }

        return root;
    }

    private void loadUser() {
        new GetUserByIdTask().execute();
    }

    private void addEditUser() {
        boolean error = false;
        String id = mUserId;
        String password = mPasswordField.getText().toString();
        String users = mUserField.getText().toString();
        String name = mNameField.getText().toString();
        String phoneNumber = mPhoneNumberField.getText().toString();
        String position = mPositionField.getText().toString();
        String bio = mBioField.getText().toString();

        if (TextUtils.isEmpty(password)) {
            mPasswordLabel.setError(getString(R.string.field_error));
            error = true;
        }

        if (TextUtils.isEmpty(users)) {
            mUserLabel.setError(getString(R.string.field_error));
            error = true;
        }

        if (TextUtils.isEmpty(name)) {
            mNameLabel.setError(getString(R.string.field_error));
            error = true;
        }

        if (TextUtils.isEmpty(position)) {
            mPositionLabel.setError(getString(R.string.field_error));
            error = true;
        }

        if (TextUtils.isEmpty(phoneNumber)) {
            mPhoneNumberLabel.setError(getString(R.string.field_error));
            error = true;
        }

        if (TextUtils.isEmpty(bio)) {
            mBioLabel.setError(getString(R.string.field_error));
            error = true;
        }

        if (error) {
            return;
        }

        User user = new User(id, users, name, position, phoneNumber, bio, "");

        new AddEditUserTask().execute(user);

    }

    private void showUsersScreen(Boolean requery) {
        if (!requery) {
            showAddEditError();
            getActivity().setResult(Activity.RESULT_CANCELED);
        } else {
            getActivity().setResult(Activity.RESULT_OK);
        }

        getActivity().finish();
    }

    private void showAddEditError() {
        Toast.makeText(getActivity(),
                "Error adding new information", Toast.LENGTH_SHORT).show();
    }

    private void showUser(User user) {
        mUserField.setText(user.getUser());
        mNameField.setText(user.getName());
        mPhoneNumberField.setText(user.getPhoneNumber());
        mPositionField.setText(user.getPosition());
        mBioField.setText(user.getBio());
    }

    private void showLoadError() {
        Toast.makeText(getActivity(),
                "Error editing player", Toast.LENGTH_SHORT).show();
    }

    private class GetUserByIdTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mUserDbHelper.getUserById(mUserId);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.moveToLast()) {
                showUser(new User(cursor));
            } else {
                showLoadError();
                getActivity().setResult(Activity.RESULT_CANCELED);
                getActivity().finish();
            }
        }

    }

    private class AddEditUserTask extends AsyncTask<User, Void, Boolean> {

        @Override
        protected Boolean doInBackground(User... users) {
            if (mUserId != null) {
                return mUserDbHelper.updateUser(users[0], mUserId) > 0;

            } else {
                return mUserDbHelper.saveUser(users[0]) > 0;
            }

        }

        @Override
        protected void onPostExecute(Boolean result) {
            showUsersScreen(result);
        }

    }

}
