package com.example.nikeru8.questionionnaire;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity3 extends AppCompatActivity
{
    public static final String Q3_ANSWER_KEY = "Q3";

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
    private int answer = 2;

    private CharSequence m_answer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
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

        m_tv_no.setText("3");
        m_tv_question.setText(Html.fromHtml(getString(R.string.question_3)));
        m_radio_a.setText(Html.fromHtml(getString(R.string.question_3_radio_a)));
        m_radio_b.setText(Html.fromHtml(getString(R.string.question_3_radio_b)));
        m_radio_c.setText(Html.fromHtml(getString(R.string.question_3_radio_c)));
    }

    public void click_a(View view)
    {
        m_tv_check_a0.setVisibility(View.GONE);
        m_tv_check_a1.setVisibility(View.VISIBLE);
    }
    public void click_b(View view)
    {
        m_tv_check_b0.setVisibility(View.VISIBLE);
        m_tv_check_b1.setVisibility(View.GONE);
    }
    public void click_c(View view)
    {
        m_tv_check_c0.setVisibility(View.GONE);
        m_tv_check_c1.setVisibility(View.VISIBLE);
    }

    // 按下 MAIN
    public void main(View view)
    {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    // 按下 BACK
    public void back(View view) {
        finish();
    }

}