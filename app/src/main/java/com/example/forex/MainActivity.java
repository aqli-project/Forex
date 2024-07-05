package com.example.forex;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.text.DecimalFormat;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private ProgressBar loadingProgressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView audTextView, bndTexrtView,btcTextView, eurTextView, gbpTextView, hkdTextView, inrTextView, jpyTextView, myrTextView, usdTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout1);
        audTextView = (TextView) findViewById(R.id.audTextView);
        bndTexrtView = (TextView) findViewById(R.id.bndTextView);
        btcTextView = (TextView) findViewById(R.id.btcTextView);
        eurTextView = (TextView) findViewById(R.id.euroTextView);
        gbpTextView = (TextView) findViewById(R.id.gbpTextView);
        hkdTextView = (TextView) findViewById(R.id.hkdTextView);
        inrTextView = (TextView) findViewById(R.id.inrTextView);
        jpyTextView = (TextView) findViewById(R.id.jpyTextView);
        myrTextView = (TextView) findViewById(R.id.myrTextView);
        usdTextView = (TextView) findViewById(R.id.usdTextView);
        loadingProgressBar = (ProgressBar) findViewById(R.id.loadingProgressBar);

        initSwipeRefreshLayout();
        initForex();
    }

    private void initSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(()->{
            initForex();
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    public String formatNumber (double number, String formant){
        DecimalFormat decimalFormat = new DecimalFormat(formant);
        return decimalFormat.format(number);
    }

    private void initForex() {
        loadingProgressBar.setVisibility(TextView.VISIBLE);
        String Url = "https://openexchangerates.org/api/latest.json?app_id=94decff3326c4b76b5a8731bb6371ba3";

        AsyncHttpClient asyncHttpClinet = new AsyncHttpClient();
        asyncHttpClinet.get(Url, new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gson = new Gson();
                RootModel rootModel = gson.fromJson(new String (responseBody), RootModel.class);
                RatesModel ratesModel = rootModel.getRatesModel();

                double aud = ratesModel.getIDR() / ratesModel.getAUB();
                double bnd = ratesModel.getIDR() / ratesModel.getBND();
                double btc = ratesModel.getIDR() / ratesModel.getBTC();
                double eur = ratesModel.getIDR() / ratesModel.getEUR();
                double gbp = ratesModel.getIDR() / ratesModel.getGBP();
                double hkd = ratesModel.getIDR() / ratesModel.getHKD();
                double inr = ratesModel.getIDR() / ratesModel.getINR();
                double jpy = ratesModel.getIDR() / ratesModel.getJPY();
                double myr = ratesModel.getIDR() / ratesModel.getMYR();
                double idr = ratesModel.getIDR();

                audTextView.setText(formatNumber(aud, "###,##0.##"));
                bndTexrtView.setText(formatNumber(bnd, "###,##0.##"));
                btcTextView.setText(formatNumber(btc, "###,##0.##"));
                eurTextView.setText(formatNumber(eur, "###,##0.##"));
                gbpTextView.setText(formatNumber(eur, "###,##0.##"));
                hkdTextView.setText(formatNumber(hkd, "###,##0.##"));
                inrTextView.setText(formatNumber(inr, "###,##0.##"));
                jpyTextView.setText(formatNumber(jpy, "###,##0.##"));
                myrTextView.setText(formatNumber(myr, "###,##0.##"));
                usdTextView.setText(formatNumber(idr, "###,##0.##"));

                loadingProgressBar.setVisibility(TextView.INVISIBLE);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                loadingProgressBar.setVisibility(TextView.INVISIBLE);
            }
        });
    }



}