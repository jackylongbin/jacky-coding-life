package com.securenetmonitoring.alder_camera.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2018/2/28 0028.
 */

public class VolleySingleton {

    private static volatile VolleySingleton mInstance = null;
    private static Context mContext;
    private RequestQueue requestQueue = null;

    public VolleySingleton(Context context)
    {
        this.mContext = context;
    }

    public static VolleySingleton getInstance(Context context)
    {
        if(mInstance == null)
        {
            synchronized (VolleySingleton.class) {

                if(mInstance == null)
                    mInstance = new VolleySingleton(context);
            }
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue()
    {
        if(null == requestQueue)
        {
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
