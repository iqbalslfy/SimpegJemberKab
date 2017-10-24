package net.jemberkab.simpeg.simpegjemberkab;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private WebView mWebView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = (WebView)findViewById(R.id.mywebview);
        mWebView.loadUrl("http://simpeg.jemberkab.net/login?redirect=http://simpeg.jemberkab.net");

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if (progressDialog!= null && progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
                progressDialog = ProgressDialog.show(MainActivity.this, "Simpeg Kab", "Loading...");
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (mWebView.canGoBack()){
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
