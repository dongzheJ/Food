package com.example.food;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SecondFragment extends Fragment{
    TextView tv;
    String myStr;
    String myStr1;
    String format;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
//        ImageView iv = view.findViewById(R.id.imageView);
//        iv.setImageResource(R.drawable.chuan);

        Button b = view.findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity  mainActivity = (MainActivity) getActivity();
                mainActivity.getMaster();
            }
        });

        tv = view.findViewById(R.id.textView);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            myStr = bundle.getString("key");
            myStr1 = bundle.getString("key1");
            format = myStr + ":\n" + myStr1;
            tv.setText(format);
        }

        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && isResumed()) {
            Bundle bundle = this.getArguments();
            if (bundle != null) {
                myStr = bundle.getString("key");
                myStr1 = bundle.getString("key1");
                format = myStr + ":\n" + myStr1;
                tv.setText(format);
            }
        }
    }
}
