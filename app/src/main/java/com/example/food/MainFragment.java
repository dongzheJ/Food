package com.example.food;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class MainFragment extends Fragment {
    private View view;
    private RecyclerView rv;
    private List<String> mDatas;
    private MyAdapter myAdapter;

    //
    private Button button;
    private UtilDao dao;
    private int listNum = 0;
    private List<User> list,newList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.main_fragment, container,false);

        final Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_add){
                    Intent intent = new Intent(getContext(),AddData.class);
                    startActivityForResult(intent,1);
                }else if(item.getItemId() == R.id.action_settings){
                    toolbar.setSubtitle("Long press to Edit or Delete");
                }
                return false;
            }
        });

        DbUtil();
        newList = new ArrayList<>();
        list = new ArrayList<>();
        list = dao.inquireData();

        rv = view.findViewById(R.id.my_recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        myAdapter = new MyAdapter(getContext(),list);
        rv.setAdapter(myAdapter);
        rv.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String name, String description) {
                MainActivity  mainActivity = (MainActivity) getActivity();
                mainActivity.getDetail(name, description);
            }
        });

        myAdapter.setOnItemLongClickListener(new MyAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int position) {
                dialogList();
                listNum = position;
            }
        });

//        button = view.findViewById(R.id.main_but);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(v.getId() == R.id.main_but){
//                    Intent intent = new Intent(getContext(),AddData.class);
//                    startActivityForResult(intent,1);
//                }
//            }
//        });
        return view;
    }

    public void dialogList(){
        final String[] items = {"Edit","Delete"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                User userNum = list.get(listNum);
                Intent intent;
                switch (i){
                    case 0: intent = new Intent(getContext(),AddData.class);
                        intent.putExtra("edit_name",userNum.getName());
                        intent.putExtra("edit_phone",userNum.getPhone());
                        startActivityForResult(intent,2);
                        break;
                    case 1: dialogNormal();
                        break;
                    default:
                        break;
                }
            }
        });
        builder.create().show();
    }

    public void dialogNormal(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        DialogInterface.OnClickListener dialogOnClick = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                User userDel = list.get(listNum);
                switch (i){
                    case DialogInterface.BUTTON_POSITIVE:
                        dao.delData("userName=?",new String[]{userDel.getName()});
                        refresh();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                    default:break;
                }
            }
        };
        builder.setTitle("Delete cuisine");
        builder.setMessage("Are you sure deleting?");
        builder.setPositiveButton("OK", dialogOnClick);
        builder.setNegativeButton("Cancel",dialogOnClick);
        builder.create().show();
    }

    public void DbUtil(){
        dao = ((MyApplication)getActivity().getApplication()).getDao();
    }

    @Override
    public void onResume() {
        super.onResume();
        getNotifyData();
    }

    public void getNotifyData(){
        newList = dao.inquireData();
        list.clear();
        list.addAll(newList);
        myAdapter.notifyDataSetChanged();
    }

    public void refresh(){
        getNotifyData();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    String[] key = data.getStringArrayExtra("key");
                    String[] values = data.getStringArrayExtra("values");
                    dao.addData("UserInfo",key,values);
                }
                break;
            case 2:
                if(resultCode == RESULT_OK){
                    User user = list.get(listNum);
                    String name = data.getStringExtra("name");
                    String phone = data.getStringExtra("phone");
                    String[] values = {name,phone,user.getName()};
                    dao.update(values);
                }
                break;
        }
    }
}
