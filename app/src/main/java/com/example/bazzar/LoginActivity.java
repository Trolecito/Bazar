package com.example.bazzar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;
import android.view.Window;
import androidx.core.content.ContextCompat;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText username, password;
    Button login, register;
    DBHelper db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, android.R.color.black));
        }
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.txt_user);
        password = findViewById(R.id.txt_password);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_registrarse);
        db = new DBHelper(this);

        login.setOnClickListener(view -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            if (db.verificarUsuario(user, pass)) {
                Toast.makeText(this, "¡Bienvenido!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });

        register.setOnClickListener(view -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            if (db.insertarUsuario(user, pass)) {
                Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
