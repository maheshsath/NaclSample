package com.master.Mahesh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

public class SecondActivity extends AppCompatActivity {

    Spinner sp_replication,sp_treatment;
    Button btn_go;
    SharedPreferences sp;
    SharedPreferences.Editor spe;
    String selectedReplication,selectedTreatment;
    int selectedReplicationPosition,selectedTreatmentPosition;
    private String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        this.setFinishOnTouchOutside(false);

        sp = getSharedPreferences("TreatmentReplicationValue",MODE_PRIVATE);
        spe = sp.edit();

        int selectedReplicationPosition = sp.getInt("replicationPosition",-1);
        int selectedTreatmentPosition = sp.getInt("treatmentPosition",-1);
        getView();
        setListener();

        //set previous selected value
        if(selectedReplicationPosition!=-1){
            sp_replication.setSelection(selectedReplicationPosition);
            sp_treatment.setSelection(selectedTreatmentPosition);
        }

    }
    private void getView() {
        sp_replication = findViewById(R.id.sp_replication);
        sp_treatment = findViewById(R.id.sp_treatment);
        btn_go = findViewById(R.id.btn_go);
    }

    private void setListener() {
        //set listener to spinner
        sp_treatment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTreatment = (String) parent.getSelectedItem();
                selectedTreatmentPosition = position;
                Log.e(TAG,"Selected treatment value => "+selectedTreatment);
                // fragmentCommunicator.passData(selectedTreatment);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_replication.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               selectedReplication = (String) parent.getSelectedItem();
               selectedReplicationPosition = position;
                Log.e(TAG,"Selected treatment value => "+selectedReplication);
                // fragmentCommunicator.passData(selectedTreatment);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spe.putString("treatment",selectedTreatment);
                spe.putInt("treatmentPosition",selectedTreatmentPosition);
                spe.putString("replication",selectedReplication);
                spe.putInt("replicationPosition",selectedReplicationPosition);
                spe.commit();
                Intent i = new Intent(SecondActivity.this,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();

            }
        });
    }


}
