package com.example.joseluissacanamboy.qrcodereader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import com.appspot.echo_backend.echo.Echo;

import java.io.IOException;




public class ReaderActivity extends Activity implements AsyncTaskUI {


    private Button scan_btn;

    private Echo tickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);

        scan_btn = (Button) findViewById(R.id.scan_btn);
        final Activity activity = this;




        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(activity);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                intentIntegrator.setPrompt("Scan");
                intentIntegrator.setCameraId(0);
                intentIntegrator.setBeepEnabled(false);
                intentIntegrator.setBarcodeImageEnabled(false);
                intentIntegrator.initiateScan();
            }


        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);


        if(intentResult != null){
            if(intentResult.getContents()==null){

                Toast.makeText(this,"Cancelacion del escaner", Toast.LENGTH_LONG).show();

            }else {
                String parameter = intentResult.getContents();
                parameter = parameter.replace(" ","%");


                TicketAsyncTask ticket = new TicketAsyncTask(ReaderActivity.this);
                ticket.execute(parameter);




            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);

        }

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }
}
