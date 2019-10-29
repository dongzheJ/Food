package com.example.food;

import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
//        WebView myWebView = view.findViewById(R.id.webview);
//        myWebView.loadUrl("https://en.wikipedia.org/wiki/Huaiyang_cuisine");
        ImageView iv = view.findViewById(R.id.imageView);
        iv.setImageResource(R.drawable.huai);
        return view;
    }

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        WebView myWebView = (WebView) getView().findViewById(R.id.webview);
//        myWebView.loadUrl("https://en.wikipedia.org/wiki/Huaiyang_cuisine");
//    }
}
