package com.example.food;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddData extends AppCompatActivity {
    private EditText edit_name,edit_phone;
    private Button but;
    private UtilDao dao;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        //
        initWidget();
        DbUtil();

        intent = getIntent();
        String user_name = intent.getStringExtra("edit_name");
        String user_phone = intent.getStringExtra("edit_phone");
        edit_name.setText(user_name);
        edit_phone.setText(user_phone);

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edit_name.getText().toString();
                String phone = edit_phone.getText().toString();
                if(!name.equals("") && !phone.equals("")){
                    String[] key = {"userName","userPhone"};
                    String[] values = {name,phone};
                    intent = new Intent();
                    //
                    intent.putExtra("key",key);
                    intent.putExtra("values",values);
                    //
                    intent.putExtra("name",name);
                    intent.putExtra("phone",phone);

                    setResult(RESULT_OK,intent);
                    finish();
                } else if(name.equals("") || phone.equals("")){
                    finish();
                }
            }
        });
    }

    private void initWidget(){
        edit_name = findViewById(R.id.add_edit_name);
        edit_phone = findViewById(R.id.add_edit_phone);
        but = findViewById(R.id.add_but);
    }

    public void DbUtil(){
        dao = ((MyApplication)this.getApplication()).getDao();
    }
}
