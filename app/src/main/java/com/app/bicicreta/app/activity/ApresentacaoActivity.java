package com.app.bicicreta.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.bicicreta.R;
import com.app.bicicreta.app.model.User;
import com.app.bicicreta.app.repository.UserRepository;

public class ApresentacaoActivity extends AppCompatActivity {
    private EditText nomeEditText;
    private ImageView imageViewNext;
    private User userEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao);
        inicializarComponentes();
        userEdit = (User) getIntent().getSerializableExtra("user");
        if(userEdit != null){
            nomeEditText.setText(userEdit.getNome());
        }
    }

    private void inicializarComponentes(){
        nomeEditText = findViewById(R.id.nomeEditText);
        imageViewNext = findViewById(R.id.imageViewNext);
        imageViewNext.setOnClickListener(v -> createUser());
    }

    private void createUser(){
        UserRepository repository = new UserRepository(this);
        String name = String.valueOf(nomeEditText.getText());
        if(name.trim().isEmpty()){
            Toast.makeText(this, "Por favor, Preencha um nome.", Toast.LENGTH_LONG).show();
            return;
        }
        if(userEdit != null){
            User userUpdate = new User(userEdit.getId(), name);
            repository.update(userUpdate);
            finish();
            return;
        }
        repository.add(name);
        handleClickNextPage();
    }

    private void handleClickNextPage(){
        Intent mainIntent = new Intent(ApresentacaoActivity.this, CadastroBicicletaActivity.class);
        startActivity(mainIntent);
        finish();
    }
}