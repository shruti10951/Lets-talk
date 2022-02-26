package com.vidyalankar.letstalk.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.vidyalankar.letstalk.R;

public class AboutLetsTalkFragment extends Fragment {

    WebView webview;

    public AboutLetsTalkFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_about_lets_talk, container, false);

        webview=view.findViewById(R.id.webView1);

        WebView webView=(WebView)view.findViewById(R.id.webView1);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://amritapawar2802.wixsite.com/lifemedical/about");

        return view;
    }
}