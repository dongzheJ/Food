package com.example.food;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private RecyclerView recyclerView;
    private List<String> mDatas;
    private Fragment mainFragment = new MainFragment();
    private Fragment secondFragment = new SecondFragment();
    private Fragment thirdFragment = new ThirdFragment();
    private Fragment fourthFragment = new FourthFragment();
    private Fragment fifthFragment = new FifthFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final MyAdapter myAdapter = new MyAdapter(this,mDatas);
        recyclerView.setAdapter(myAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                switch (position ){
                    case 0:
                        ft.replace(R.id.mainContainer, mainFragment).commit();
                        break;
                    case 1:
                        ft.replace(R.id.secondContainer, secondFragment).commit();
                        break;
                    case 2:
                        ft.replace(R.id.thirdContainer, thirdFragment).commit();
                        break;
                    case 3:
                        ft.replace(R.id.fourthContainer, fourthFragment).commit();
                        break;
                    case 4:
                        ft.replace(R.id.fifthContainer, fifthFragment).commit();
                        break;
                }

//                Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        mDatas = new ArrayList<String>();

        mDatas.add("Huaiyang cuisine");
        mDatas.add("Sichuan cuisine");
        mDatas.add("Cantonese cuisine");
        mDatas.add("Shandong cuisine");
        mDatas.add("Hunan cuisine");

//        for ( int i=0; i < 19; i++) {
//            mDatas.add( "item"+i);
//        }
    }
}
