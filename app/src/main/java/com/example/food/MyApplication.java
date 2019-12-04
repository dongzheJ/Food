package com.example.food;
import android.app.Application;

public class MyApplication extends Application {
    private UtilDao dao;

    @Override
    public void onCreate() {
        super.onCreate();
        dao = new UtilDao(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        dao.getClose();
    }

    public UtilDao getDao() {
        return dao;
    }
}
