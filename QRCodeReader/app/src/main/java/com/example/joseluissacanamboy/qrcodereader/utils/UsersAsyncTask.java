package com.example.joseluissacanamboy.qrcodereader.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.appspot.echo_backend.echo.model.Entrada;
import com.appspot.echo_backend.echo.model.Usuario;
import com.google.api.client.http.HttpResponseException;

import java.io.IOException;
import java.util.List;

/**
 * Created by joseluissacanamboy on 4/05/17.
 */
public class UsersAsyncTask extends AsyncTask<Void, Void, List<Usuario>> {

    private AsyncTaskUI asyncTaskUI;

    public UsersAsyncTask(AsyncTaskUI asyncTaskUI) {
        this.asyncTaskUI = asyncTaskUI;
    }

    @Override
    protected List<Usuario> doInBackground(Void... params) {

        Log.d("csacanam", "Before request");
        try {
            return TicketSingleton.getInstance().echo().darTodosUsuarios().execute().getItems();

        } catch (HttpResponseException e) {
            Log.e("csacanam", "HttpResponseException");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("csacanam", "IOException");
            e.printStackTrace();
        }

        Log.d("csacanam", "Return null");

        return null;
    }

    @Override
    protected void onPostExecute(List<Usuario> usuarios) {
        if (usuarios == null ) {
            Log.d("csacanam", "No hay usuarios");
            asyncTaskUI.showMessage("No hay usuarios");

        } else {
            Log.d("csacanam", "Hay " + usuarios.size() + " usuarios");
            asyncTaskUI.showMessage("Hay " + usuarios.size() + " usuarios");
        }
    }
}
