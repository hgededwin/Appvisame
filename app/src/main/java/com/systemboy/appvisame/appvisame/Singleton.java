package com.systemboy.appvisame.appvisame;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Generico on 02/06/2015.
 */
public class Singleton {

    private static Singleton mSingleton = null;

    private RequestQueue mRequesQueue;

    private Singleton(Context context) {
        mRequesQueue = Volley.newRequestQueue(context);
    }
    public static Singleton getInstance(Context context) {

        if (mSingleton == null) {
            mSingleton = new Singleton(context);
        }

        return mSingleton;
    }
        public RequestQueue getRequestQueue(){

        return mRequesQueue;
    }
}
