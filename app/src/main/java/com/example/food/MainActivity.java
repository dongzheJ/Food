package com.example.food;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends FragmentActivity {
    private MainFragment mf;
    private FragmentTransaction ft;
    private FragmentManager fm;
    private SecondFragment sF;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getMaster();
    }

    public void getDetail(String name, String description){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        if(mf != null){
            ft.hide(mf);
        }
        if (sF == null) {
            sF = new SecondFragment();
            ft.add(R.id.secondContainer, sF);
        }else{
            ft.show(sF);
        }

        Bundle bundle = new Bundle();
        bundle.putString("key", name);
        bundle.putString("key1", description);
        sF.setArguments(bundle);

        ft.commit();
    }

    public void getMaster(){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        if(sF != null){
            ft.hide(sF);
        }

        if (mf == null) {
            mf = new MainFragment();
            ft.add(R.id.mainContainer, mf);
        }else{
            ft.show(mf);
        }
        ft.commit();
    }
}
