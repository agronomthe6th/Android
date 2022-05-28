package ru.mirea.gribkova.mireaproject1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthWithFirebase extends AppCompatActivity implements View.OnClickListener{

    private EditText emailEditText;
    private EditText passwordTextEdit;
    private TextView statusTextView;
    private FirebaseAuth auth;
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_with_firebase);
        // Views
        emailEditText = findViewById(R.id.editEmail);
        passwordTextEdit = findViewById(R.id.editPassword);
        statusTextView = findViewById(R.id.signView);
        // Buttons
        findViewById(R.id.btnSign).setOnClickListener(this);
        findViewById(R.id.createAccountbtn).setOnClickListener(this);
        findViewById(R.id.sgnoutbtn).setOnClickListener(this);
        // [START initialize_auth]
        auth = FirebaseAuth.getInstance();
        // [END initialize_auth]
    }
    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly
        FirebaseUser currentUser = auth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            statusTextView.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(), user.isEmailVerified()));
            findViewById(R.id.editEmail).setVisibility(View.GONE);
            findViewById(R.id.editPassword).setVisibility(View.GONE);
            findViewById(R.id.btnSign).setVisibility(View.GONE);
            findViewById(R.id.createAccountbtn).setVisibility(View.GONE);
            findViewById(R.id.sgnoutbtn).setVisibility(View.VISIBLE);

        } else {
            statusTextView.setText(R.string.signed_out);
            findViewById(R.id.editPassword).setVisibility(View.VISIBLE);
            findViewById(R.id.editEmail).setVisibility(View.VISIBLE);
            findViewById(R.id.btnSign).setVisibility(View.VISIBLE);
            findViewById(R.id.createAccountbtn).setVisibility(View.VISIBLE);
            findViewById(R.id.sgnoutbtn).setVisibility(View.VISIBLE);
        }
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = emailEditText.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Required.");
            valid = false;
        } else {
            emailEditText.setError(null);
        }

        String password = passwordTextEdit.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordTextEdit.setError("Required.");
            valid = false;
        } else {
            passwordTextEdit.setError(null);
        }
        return valid;
    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }
        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success");
                FirebaseUser user = auth.getCurrentUser();
                updateUI(user);
                finish();
            } else {
                // If sign in fails, display a message to the user
                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                Toast.makeText(AuthWithFirebase.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
                updateUI(null);
            }
        });
        // [END create_user_with_email]
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            updateUI(user);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(AuthWithFirebase.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            statusTextView.setText(R.string.auth_failed);
                            finish();
                        }
                        // [END_EXCLUDE]
                    }
                    // [END sign_in_with_email]
                });
    }
    private void signOut() {
        auth.signOut();
        updateUI(null);
    }
    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.createAccountbtn) {
            createAccount(emailEditText.getText().toString(),
                    passwordTextEdit.getText().toString());
        } else if (i == R.id.btnSign) {
            signIn(emailEditText.getText().toString(),
                    passwordTextEdit.getText().toString());
        } else if (i == R.id.sgnoutbtn){
            signOut();
        }
    }
}