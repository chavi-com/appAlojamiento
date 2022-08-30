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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class registro extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    EditText  correo_reg, password_reg;

    Button btnregistro,btningresar;
    String idcust;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_registro);
        correo_reg=findViewById(R.id.correoreg);
        password_reg=findViewById(R.id.passwordreg);
        btnregistro=findViewById(R.id.btnregistro);
        btningresar=findViewById(R.id.btnyatengocu);


        btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerSave(correo_reg.getText().toString(),password_reg.getText().toString());
            }

            private void CustomerSave(String xcorreo, String xpassword) {
                Map<String, Object> ccustomer = new HashMap<>();
                ccustomer.put("email", xcorreo);
                ccustomer.put("password", xpassword);
                db.collection("customer").add(ccustomer).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"Cliente agregado correctamente...",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Cliente NO agregado...",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),customer.class));
            }
        });

    }
}