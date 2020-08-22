package com.example.reportcard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class IntroActivity extends AppCompatActivity {

    // Widgets
    private ViewPager iSlidePager;
    private LinearLayout iLinearLayoutDotIndicator;
    private TextView[] iDots;
    private Button iSlideButtonBack, iSlideButtonNext;

    // Variables
    private int iCurrentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        iSlidePager = findViewById(R.id.vpIntroPager);
        iLinearLayoutDotIndicator = findViewById(R.id.llPageIndicators);

        SliderAdapter sliderAdapter = new SliderAdapter(this);
        iSlidePager.setAdapter(sliderAdapter);

        showDotIndicators(0);

        iSlidePager.addOnPageChangeListener(slideChangeListener);

        iSlideButtonBack = findViewById(R.id.btnSlideBack);
        iSlideButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iCurrentPage != 0) {
                    iSlidePager.setCurrentItem(iCurrentPage - 1);
                }
            }
        });

        iSlideButtonNext = findViewById(R.id.btnSlideNext);
        iSlideButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (iCurrentPage < iDots.length){
                    iSlidePager.setCurrentItem(iCurrentPage + 1);
                }
            }
        });
    }

    public void showDotIndicators(int position){
        iDots = new TextView[3];
        iLinearLayoutDotIndicator.removeAllViews();

        for (int i = 0; i < iDots.length; i++){
            iDots[i] = new TextView(this);
            iDots[i].setText(Html.fromHtml("&#826;"));
            iDots[i].setTextSize(32);
            iDots[i].setTextColor(getResources().getColor(R.color.colorGrey));

            iLinearLayoutDotIndicator.addView(iDots[i]);
        }

        if (iDots.length > 0){
            iDots[position].setTextColor(Color.WHITE);
        }
    }

    ViewPager.OnPageChangeListener slideChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            showDotIndicators(position);
            iCurrentPage = position;

            if (position == 0){
                iSlideButtonBack.setEnabled(false);
                iSlideButtonBack.setVisibility(View.INVISIBLE);

                iSlideButtonNext.setEnabled(true);
            } else if (position == iDots.length - 1){
                iSlideButtonBack.setEnabled(true);
                iSlideButtonBack.setVisibility(View.VISIBLE);

                iSlideButtonNext.setEnabled(true);

                iSlideButtonNext.setText(R.string.introFinishButton);
            } else {
                iSlideButtonBack.setEnabled(true);
                iSlideButtonBack.setVisibility(View.VISIBLE);

                iSlideButtonNext.setEnabled(true);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}