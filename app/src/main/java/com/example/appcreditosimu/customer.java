package com.example.appcreditosimu;

import static com.example.appcreditosimu.R.layout.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class customer extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    EditText correoin, passwordin;
    Button btnin;

    public String iddoc, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_customer);
        correoin=findViewById(R.id.correo);
        passwordin=findViewById(R.id.password);
        btnin=findViewById(R.id.btnregistro);
        btnin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarsecion(correoin.getText().toString(),passwordin.getText().toString());
            }

            private void iniciarsecion(String correo, String contraseña) {
                db.collection("customer").whereEqualTo("email",correo).whereEqualTo("password",contraseña).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (!task.getResult().isEmpty()) {

                                for (QueryDocumentSnapshot document : task.getResult()){
                                    iddoc=document.getId();
                                    email=document.getString("email");
                                }
                                Toast.makeText(getApplicationContext(), "bienvenido", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(customer.this,MainActivity.class);
                                i.putExtra("email",correoin.getText().toString());
                                startActivity(i);
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "no exite el cliente", Toast.LENGTH_SHORT).show();
                            }
                        }


                    }
                });
            }
        });

    }
}