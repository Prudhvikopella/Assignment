package com.android.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    AppCompatButton commonBottomButton;
    RelativeLayout parentEmiLayout,secondInnerLoanLayout,secondInnerEmiLayout;
    LinearLayout selectbanklayout,firstInnerEmiLayout,firstInnerLoanLayout,emiplaninnerlayout;
    Animation animation,fadein,fadeout,slideinup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         commonBottomButton = findViewById(R.id.bt_commonbottombutton);
         firstInnerLoanLayout = findViewById(R.id.ll_innerloanfirstlayout);
         secondInnerLoanLayout = findViewById(R.id.rl_innerloansecondlayout);
         
         parentEmiLayout = findViewById(R.id.rl_emilayout);
         firstInnerEmiLayout = findViewById(R.id.ll_inneremifirstlayout);
         secondInnerEmiLayout = findViewById(R.id.rl_inneremisecondlayout);
         
         selectbanklayout = findViewById(R.id.rl_selectbankaccountlayout);
         
         animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.slide_out_up);
         slideinup = AnimationUtils.loadAnimation(MainActivity.this,R.anim.slide_in_up);
         fadein = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade_in);
         fadeout = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade_out);

         commonBottomButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(commonBottomButton.getText().toString().matches("Proceed to EMI selection")){
                     collapseLoanLayout();
                     return;
                 }if(commonBottomButton.getText().toString().matches("Select your bank account")){
                     expandselectbankaccountlayout();
                     return;
                 }
             }
         });

         secondInnerLoanLayout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 expandemilayout();
             }
         });

         secondInnerEmiLayout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 collapseSelectBankAccountLayout();
             }
         });


    }

    public void collapseLoanLayout(){
        parentEmiLayout.setVisibility(View.VISIBLE);
        firstInnerLoanLayout.setVisibility(View.GONE);
        secondInnerLoanLayout.setVisibility(View.VISIBLE);
        commonBottomButton.setText("Select your bank account");
        findViewById(R.id.iv_seekbar).setVisibility(View.GONE);

        parentEmiLayout.startAnimation(animation);
        firstInnerLoanLayout.startAnimation(fadeout);
        secondInnerLoanLayout.startAnimation(fadein);
        commonBottomButton.startAnimation(animation);
    }

    public void expandemilayout(){
        parentEmiLayout.setVisibility(View.GONE);
        parentEmiLayout.startAnimation(slideinup);
        firstInnerLoanLayout.setVisibility(View.VISIBLE);
        firstInnerLoanLayout.startAnimation(fadein);
        commonBottomButton.setText("Proceed to EMI selection");
        secondInnerLoanLayout.setVisibility(View.GONE);
        secondInnerLoanLayout.startAnimation(fadeout);
        commonBottomButton.startAnimation(animation);
        selectbanklayout.setVisibility(View.GONE);
        selectbanklayout.startAnimation(slideinup);
        findViewById(R.id.iv_seekbar).setVisibility(View.VISIBLE);


    }

    public void expandselectbankaccountlayout(){
        firstInnerEmiLayout.setVisibility(View.GONE);
        secondInnerEmiLayout.setVisibility(View.VISIBLE);
        selectbanklayout.setVisibility(View.VISIBLE);
        findViewById(R.id.iv_seekbar).setVisibility(View.GONE);
        commonBottomButton.setText("Tap for 1-click KYC");
        firstInnerEmiLayout.startAnimation(fadeout);
        secondInnerEmiLayout.startAnimation(fadein);
        selectbanklayout.startAnimation(animation);
        commonBottomButton.startAnimation(animation);

    }

    public void collapseSelectBankAccountLayout(){
        firstInnerEmiLayout.setVisibility(View.VISIBLE);
        secondInnerEmiLayout.setVisibility(View.GONE);
        selectbanklayout.setVisibility(View.GONE);
        commonBottomButton.setText("Select your bank account");
        findViewById(R.id.iv_seekbar).setVisibility(View.GONE);

        firstInnerEmiLayout.startAnimation(fadein);
        secondInnerEmiLayout.startAnimation(fadeout);
        selectbanklayout.startAnimation(slideinup);
        commonBottomButton.startAnimation(animation);
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if(commonBottomButton.getText().toString().matches("Proceed to EMI selection")){
            finishAffinity();
            return;
        }if(commonBottomButton.getText().toString().matches("Select your bank account")){
            expandemilayout();
            return;
        }if(commonBottomButton.getText().toString().matches("Tap for 1-click KYC")){
            collapseSelectBankAccountLayout();
            return;
        }else{
            Toast.makeText(this, "zero", Toast.LENGTH_SHORT).show();
        }
    }


}