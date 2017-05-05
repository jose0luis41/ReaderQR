package com.example.joseluissacanamboy.qrcodereader.utils;

import com.appspot.echo_backend.echo.Echo;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

/**
 * Created by joseluissacanamboy on 4/05/17.
 */
public class TicketSingleton {

    private static Echo myApiService = null;

    public static Echo getInstance(){
        if(myApiService ==null){
            Echo.Builder builder = new Echo.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(),null).setRootUrl("https://disco-web.appspot.com/_ah/api");

                myApiService = builder.build();
        }
        return myApiService;
    }

}
