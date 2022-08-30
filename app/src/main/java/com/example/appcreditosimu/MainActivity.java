package com.example.appcreditosimu;

import static java.lang.Double.parseDouble;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class
MainActivity extends AppCompatActivity implements View.OnClickListener{

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    TextView valorcuota, valordeuda;
    EditText valorprestamo, nom;
    RadioButton vivienda, educacion, librein, c12, c24, c36;
    CheckBox cuotamanejo;
    Button simular,limpiar,guardar,cerrar_sec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Referenciar -del archivo activity_main.xml - los objetos instanciados
        valorcuota = findViewById(R.id.rvcuota);
        valordeuda = findViewById(R.id.rdeuda);
        valorprestamo = findViewById(R.id.edvalorprestamo);
        nom = findViewById(R.id.edinom);
        vivienda = findViewById(R.id.rbvivienda);
        educacion = findViewById(R.id.rbeducacion);
        librein = findViewById(R.id.rbinv);
        c12 = findViewById(R.id.rd12);
        c24 = findViewById(R.id.rd24);
        c36 = findViewById(R.id.rd36);
        cuotamanejo = findViewById(R.id.chcuota);
        simular = findViewById(R.id.btnsimular);
        limpiar = findViewById(R.id.btnlimpiar);
        guardar = findViewById(R.id.btnguardar);
        cerrar_sec = findViewById(R.id.btncerrar);
        //
        simular.setOnClickListener(this);
        limpiar.setOnClickListener(this);

        guardar.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreditoSave(nom.getText().toString(),valorprestamo.getText().toString(),valorcuota.getText().toString(),valordeuda.getText().toString());
            }

            private void CreditoSave(String xNombre, String xPrestamo, String xCuota, String xDeuda) {
                Map<String, Object> ccredito = new HashMap<>();
                ccredito.put("nombre",xNombre);
                ccredito.put("valorPrestamo",xPrestamo);
                ccredito.put("valorCuota",xCuota);
                ccredito.put("totalDeuta",xDeuda);

                Task<DocumentReference>  documentReferenceTask = db.collection("creditos").add(ccredito).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference){
                        Toast.makeText(getApplicationContext(), "el credito se guardo correctamente",Toast.LENGTH_SHORT).show();

                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"no se pudo guardar", Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        }));

        cerrar_sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),registro.class));
            }
        });
    }
            @Override
            public void onClick(View v) {
                //convertir los valores a tipo numerico
                String svalorprestamo = valorprestamo.getText().toString().trim();
                if (!svalorprestamo.equals("")) {
                    double mvalorprestamo = parseDouble(valorprestamo.getText().toString().trim());
                    double tcredito = 0;
                    double tvivienda;
                    double teducacion;
                    double tlibrein;
                    double pcredito =0;
                    double tpcredito = 0;
                    double intmens = 0;
                    if (mvalorprestamo >= 1000000 && mvalorprestamo <= 100000000) {
                        switch (v.getId()) {
                            case R.id.btnsimular:

                                if (vivienda.isChecked()) {
                                    tvivienda = mvalorprestamo * 0.010;
                                    if (c12.isChecked()) {
                                        intmens = tvivienda * 12;
                                        tcredito = mvalorprestamo + intmens;
                                        tpcredito = tcredito / 12;
                                    }
                                    if (c24.isChecked()) {
                                        intmens = tvivienda * 24;
                                        tcredito = mvalorprestamo + intmens;
                                        tpcredito = tcredito / 24;
                                    }
                                    if (c36.isChecked()) {
                                        intmens = tvivienda * 36;
                                        tcredito = mvalorprestamo + intmens;
                                        tpcredito = tcredito / 36;
                                    }

                                }
                                if (educacion.isChecked()) {
                                    teducacion = mvalorprestamo * 0.005;
                                    if (c12.isChecked()) {
                                        intmens = teducacion * 12;
                                        tcredito = mvalorprestamo + intmens;
                                        tpcredito = tcredito / 12;
                                    }
                                    if (c24.isChecked()) {
                                        intmens = teducacion * 24;
                                        tcredito = mvalorprestamo + intmens;
                                        tpcredito = tcredito / 24;
                                    }
                                    if (c36.isChecked()) {
                                        intmens = teducacion * 36;
                                        tcredito = mvalorprestamo + intmens;
                                        tpcredito = tcredito / 36;
                                    }
                                }
                                if (librein.isChecked()) {
                                    tlibrein = mvalorprestamo * 0.015;
                                    if (c12.isChecked()) {
                                        intmens = tlibrein * 12;
                                        tcredito = mvalorprestamo + intmens;
                                        tpcredito = tcredito / 12;
                                    }
                                    if (c24.isChecked()) {
                                        intmens = tlibrein * 24;
                                        tcredito = mvalorprestamo + intmens;
                                        tpcredito = tcredito / 24;
                                    }
                                    if (c36.isChecked()) {
                                        intmens = tlibrein * 36;
                                        tcredito = mvalorprestamo + intmens;
                                        tpcredito = tcredito / 36;
                                    }

                                }



                                if (cuotamanejo.isChecked()){
                                    tpcredito = tpcredito + 10000;
                                }
                                else {
                                    tpcredito = tpcredito + 0;
                                }
                                break;

                            case R.id.btnlimpiar:
                                valorprestamo.setText(" ");
                                vivienda.setChecked(false);
                                educacion.setChecked(false);
                                librein.setChecked(false);
                                c12.setChecked(false);
                                c24.setChecked(false);
                                c36.setChecked(false);
                                cuotamanejo.setChecked(false);
                                valorcuota.setText(" ");
                                valordeuda.setText(" ");
                                valorprestamo.requestFocus();
                                break;
                        }
                        //Asignar el contenido de nresultado al obj definitiva
                        DecimalFormat ornos = new DecimalFormat("###,###,###,###,###");
                        valorcuota.setText(ornos.format(tpcredito));
                        valordeuda.setText(ornos.format(tcredito));
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Solo se realiza presamos de $1000000 hasta  $100000000", Toast.LENGTH_SHORT).show();
                        valorprestamo.setText(" ");
                        vivienda.setChecked(false);
                        educacion.setChecked(false);
                        librein.setChecked(false);
                        c12.setChecked(false);
                        c24.setChecked(false);
                        c36.setChecked(false);
                        cuotamanejo.setChecked(false);
                        valorcuota.setText(" ");
                        valordeuda.setText(" ");
                        valorprestamo.requestFocus();
                    }
                }
            }


            
    }