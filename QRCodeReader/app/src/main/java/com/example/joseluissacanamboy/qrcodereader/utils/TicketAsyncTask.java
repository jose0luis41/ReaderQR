package com.example.joseluissacanamboy.qrcodereader.utils;

import android.os.AsyncTask;

import com.appspot.echo_backend.echo.model.Entrada;
import com.google.api.client.http.HttpResponseException;


import java.io.IOException;

/**
 * Created by joseluissacanamboy on 4/05/17.
 */
public class TicketAsyncTask extends AsyncTask<String,Void,Entrada> {

    private AsyncTaskUI asyncTaskUI;

    public TicketAsyncTask( AsyncTaskUI asyncTaskUI)
    {
        this.asyncTaskUI = asyncTaskUI;
    }

    @Override
    protected Entrada doInBackground(String... params) {

        String code = params[0];

        try{
            return TicketSingleton.getInstance().echo().getTicekt(code).execute();

        }catch(HttpResponseException e){


        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Entrada entrada) {
        if(entrada != null){
            asyncTaskUI.showMessage(entrada.getCodigoQR());

        }else{
            asyncTaskUI.showMessage("Error");
        }
    }
}
