package com.ramonaharrison.busnyc;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by Ramona Harrison
 * on 2/18/16.
 */

public class BusApplication extends Application
{
    public static final String TAG = "";
    public static String API_KEY = "";


    public static BusApplication get(Context context) {
        return (BusApplication) context.getApplicationContext();
    }

    public String getKey()
    {
        try
        {
            InputStream inputStream = getAssets().open("key.txt");
            Scanner scanner = new Scanner(inputStream);
            return scanner.hasNext() ? scanner.next() : "";
        }
        catch(IOException e)
        {
            Log.d(TAG, "No keyfile found.");
        }
        return "";
    }

}
