package com.crave.bbq;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import com.crave.bbq.calc.BarbecueCalc;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private static final String DEFAULT_UNIT = " Kg";
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListeners();

        MobileAds.initialize(this, BuildConfig.AdsAPIKey);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void setListeners() {
        ((Button) findViewById(R.id.barbecueCalc)).setOnClickListener(this);
        ((SeekBar) findViewById(R.id.menSeek)).setOnSeekBarChangeListener(this);
        ((SeekBar) findViewById(R.id.womenSeek)).setOnSeekBarChangeListener(this);
        ((SeekBar) findViewById(R.id.childrenSeek)).setOnSeekBarChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        calculate();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        updateQuantityTextView(seekBar);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //Not implemented
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //Not implemented
    }

    private void calculate() {
        BarbecueCalc barbecue = new BarbecueCalc(getMenQuantity(), getWomenQuantity(), getChildreQuantity());

        setBarbecueResult(R.id.meatQuantity, barbecue.getMeatQuantity());
        setBarbecueResult(R.id.sausageQuantity, barbecue.getSausageQuantity());
    }

    private void updateQuantityTextView(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.menSeek:
                setQuantity(R.id.menQuantity, getMenQuantity());
                break;
            case R.id.womenSeek:
                setQuantity(R.id.womenQuantity, getWomenQuantity());
                break;
            case R.id.childrenSeek:
                setQuantity(R.id.childrenQuantity, getChildreQuantity());
                break;
        }
    }

    private double getMenQuantity() {
        SeekBar menSeek = (SeekBar) findViewById(R.id.menSeek);
        return menSeek.getProgress();
    }

    private double getWomenQuantity() {
        SeekBar womenSeek = (SeekBar) findViewById(R.id.womenSeek);
        return womenSeek.getProgress();
    }

    private double getChildreQuantity() {
        SeekBar childrenSeek = (SeekBar) findViewById(R.id.childrenSeek);
        return childrenSeek.getProgress();
    }

    private void setBarbecueResult(int id, double value) {
        TextView barbecue = (TextView) findViewById(id);
        barbecue.setText(value + DEFAULT_UNIT);
    }

    private void setQuantity(int id, Double value) {
        TextView quantity = (TextView) findViewById(id);
        quantity.setText(new Integer(value.intValue()).toString());
    }
}
