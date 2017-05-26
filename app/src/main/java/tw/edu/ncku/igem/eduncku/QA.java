package tw.edu.ncku.igem.eduncku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QA extends AppCompatActivity
{
    //public static final String Q1_ANSWER_KEY = "Q1";

    public static int answer_array[] = {1,2,3};//The answer to the question.
    public static int correct_question_number;
    public static String question_content[][] = {
            {
                    "請問飛機可以飛上天運用到的原理是?",
                    "白努利定律",
                    "牛頓定律",
                    "愛因斯坦相對論"
            },
            {
                    "請問這幾次教你們的人是什麼團隊?",
                    "這群人",
                    "iGEM NCKU",
                    "學校老師"
            },
            {
                    "請問製作眼鏡的所用的材料是?",
                    "凸透鏡",
                    "凹透鏡",
                    "以上皆非"
            }
    };
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
    private int answer = 0;
    private int count = 0;
    private CharSequence m_answer;
    static int question_number = 1;//the current question
    final int the_number_of_question = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qa);
        init();//initialize
        Intent intent = getIntent();
        if(question_number == 1){
            answer = answer_array[0];//set the answer of the first question
            correct_question_number = 0;
        }
        else if(intent != null){
            answer = intent.getIntExtra("answer",0);
        }
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
        m_tv_no.setText(Integer.toString(question_number));
        m_tv_question.setText(question_content[question_number - 1][0]);
        m_radio_a.setText(question_content[question_number - 1][1]);
        m_radio_b.setText(question_content[question_number - 1][2]);
        m_radio_c.setText(question_content[question_number - 1][3]);
    }

    public void click_a(View view)
    {
        count++;//Every time clinking a button the count will increase by 1
        if(answer == 1){
            m_tv_check_a0.setVisibility(View.VISIBLE);
            m_tv_check_a1.setVisibility(View.GONE);
            if(count == 1){//Answer the correct answer
                correct_question_number++;
            }
        }
        else{
            m_tv_check_a0.setVisibility(View.GONE);
            m_tv_check_a1.setVisibility(View.VISIBLE);
        }
    }
    public void click_b(View view)
    {
        count++;
        if(answer == 2){
            m_tv_check_b0.setVisibility(View.VISIBLE);
            m_tv_check_b1.setVisibility(View.GONE);
            if(count == 1){
                correct_question_number++;
            }
        }
        else{
            m_tv_check_b0.setVisibility(View.GONE);
            m_tv_check_b1.setVisibility(View.VISIBLE);
        }
    }
    public void click_c(View view)
    {
        count++;
        if(answer == 3){
            m_tv_check_c0.setVisibility(View.VISIBLE);
            m_tv_check_c1.setVisibility(View.GONE);
            if(count == 1){
                correct_question_number++;
            }
        }
        else{
            m_tv_check_c0.setVisibility(View.GONE);
            m_tv_check_c1.setVisibility(View.VISIBLE);
        }
    }

    //返回上一頁
    public void back(View view) {
        finish();
    }
    // 按下 NEXT
    public void next(View view)
    {
        question_number++;
        // 建立新 Intent: new Intent( 來源 , 目的)
        if(question_number <= the_number_of_question) {
            Intent intent = new Intent(this, QA.class);
            intent.putExtra("answer", answer_array[question_number - 1]);
            startActivity(intent);
            finish();
        }
        else{
            question_number = 1;
            finish();
        }
    }
}
