package com.example.nikeru8.questionionnaire;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity1 extends AppCompatActivity
{
    public static final String Q1_ANSWER_KEY = "Q1";

    private TextView m_tv_no;
    private TextView m_tv_question;
    private Button m_radio_a;
    private TextView m_tv_check_a0;
    private TextView m_tv_check_a1;
    private Button m_radio_b;
    private TextView m_tv_check_b0;
    private TextView m_tv_check_b1;
    private Button m_radio_c;
    private TextView m_tv_check_c0;
    private TextView m_tv_check_c1;
    private int answer = 1;

    private CharSequence m_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        init();
    }

    private void init()
    {
        m_tv_no = (TextView)findViewById(R.id.tv_no);
        m_tv_question = (TextView)findViewById(R.id.tv_question);
        m_radio_a = (Button)findViewById(R.id.radio_a);
        m_tv_check_a0 = (TextView)findViewById(R.id.tv_check_a0);
        m_tv_check_a1 = (TextView)findViewById(R.id.tv_check_a1);
        m_radio_b = (Button)findViewById(R.id.radio_b);
        m_tv_check_b0 = (TextView)findViewById(R.id.tv_check_b0);
        m_tv_check_b1 = (TextView)findViewById(R.id.tv_check_b1);
        m_radio_c = (Button)findViewById(R.id.radio_c);
        m_tv_check_c0 = (TextView)findViewById(R.id.tv_check_c0);
        m_tv_check_c1 = (TextView)findViewById(R.id.tv_check_c1);

        m_tv_no.setText("1");
        m_tv_question.setText(Html.fromHtml(getString(R.string.question_1)));
        m_radio_a.setText(Html.fromHtml(getString(R.string.question_1_radio_a)));
        m_radio_b.setText(Html.fromHtml(getString(R.string.question_1_radio_b)));
        m_radio_c.setText(Html.fromHtml(getString(R.string.question_1_radio_c)));
    }

    public void click_a(View view)
    {
        m_tv_check_a0.setVisibility(View.VISIBLE);
        m_tv_check_a1.setVisibility(View.GONE);
    }
    public void click_b(View view)
    {
        m_tv_check_b0.setVisibility(View.GONE);
        m_tv_check_b1.setVisibility(View.VISIBLE);
    }
    public void click_c(View view)
    {
        m_tv_check_c0.setVisibility(View.GONE);
        m_tv_check_c1.setVisibility(View.VISIBLE);
    }

    //返回上一頁
    public void back(View view) {
        finish();
    }
    // 按下 NEXT
    public void next(View view)
    {
        // 建立新 Intent: new Intent( 來源 , 目的)
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }

}