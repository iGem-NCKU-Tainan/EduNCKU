package tw.edu.ncku.igem.eduncku;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QA extends AppCompatActivity
{
    //public static final String Q1_ANSWER_KEY = "Q1";

    public static int answer_array[] = {1,2,3,2,1,2,3,3,1,2,3,2,1,1,1,3,2};//The answer to the question.
    public static int correct_question_number;
    public static String question_content[][] = {
            {//1.A
                    "請問什麼樣的液體可以讓燈泡最亮?",
                    "鹽水",
                    "糖水",
                    "自來水"
            },
            {//2.B
                    "請問一個水溶液中性代表ph值等於多少?",
                    "2",
                    "7",
                    "11"
            },
            {//3.C
                    "請問一杯150公克的水，溶入30公克的鹽，溶解度是多少?",
                    "0.5",
                    "0.002",
                    "0.2"
            },
            {//4.B
                    "請問下列哪個是鹼性液體?",
                    "醋",
                    "小蘇打水",
                    "白開水"
            },
            {//5.A
                    "請問紫色高麗菜汁在加入肥皂水會變成什麼顏色?",
                    "藍色",
                    "紫色",
                    "紅色"
            },
            {//6.B
                    "請問紫色高麗菜汁在加入鹽水會變成什麼顏色?",
                    "藍色",
                    "紫色",
                    "紅色"
            },
            {//7.C
                    "請問紫色高麗菜汁在加入蘋果醋會變成什麼顏色?",
                    "藍色",
                    "紫色",
                    "紅色"
            },
            {//8.C
                    "為什麼在月台的時候要站在黃線後面呢?",
                    "因為火車經過的時候會產生高氣壓，如果在附近容易因為氣壓的流動而被吸過去",
                    "因為媽媽說要站在後面",
                    "以上皆非"
            },
            {//9.A
                    "風是怎麼吹的?",
                    "高壓往低壓",
                    "高壓往高壓",
                    "低壓往高壓"
            },
            {//10.B
                    "颱風是高氣壓還是低氣壓?",
                    "高氣壓",
                    "低氣壓",
                    "以上皆是"
            },
            {//11.C
                    "颱風在北半球是怎麼旋轉的?",
                    "不會旋轉",
                    "順時針旋轉",
                    "逆時針旋轉"
            },
            {//12.B
                    "颱風的旋轉方向是因為哪一種力造成?",
                    "白努力",
                    "柯氏力",
                    "非常努力"
            },
            {//13.A
                    "遺傳物質位在細胞的那一個部分?",
                    "細胞核",
                    "細胞質",
                    "細胞壁"
            },
            {//14.A
                    "在DNA萃取的實驗中，奇異果功能是什麼?",
                    "萃取DNA",
                    "提供分解酵素",
                    "溶解細胞膜"
            },
            {//15.A
                    "請問哪個是上課提到的遺傳性狀?",
                    "酒窩",
                    "酒瓶",
                    "酒窖"
            },
            {//16.C
                    "基因、DNA、染色體的大小哪個最小?",
                    "染色體",
                    "DNA",
                    "基因"
            },
            {//17.B
                    "人體每個細胞裡面總共有幾對染色體呢?",
                    "8對染色體",
                    "23對染色體",
                    "29對染色體"
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
    final int the_number_of_question = 17;

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
            m_radio_a.setBackgroundColor(Color.GREEN);
            if(count == 1){//Answer the correct answer
                correct_question_number++;
            }
        }
        else{
            m_tv_check_a0.setVisibility(View.GONE);
            m_tv_check_a1.setVisibility(View.VISIBLE);
            m_radio_a.setBackgroundColor(Color.RED);
        }
    }
    public void click_b(View view)
    {
        count++;
        if(answer == 2){
            m_tv_check_b0.setVisibility(View.VISIBLE);
            m_tv_check_b1.setVisibility(View.GONE);
            m_radio_b.setBackgroundColor(Color.GREEN);
            if(count == 1){
                correct_question_number++;
            }
        }
        else{
            m_tv_check_b0.setVisibility(View.GONE);
            m_tv_check_b1.setVisibility(View.VISIBLE);
            m_radio_b.setBackgroundColor(Color.RED);
        }
    }
    public void click_c(View view)
    {
        count++;
        if(answer == 3){
            m_tv_check_c0.setVisibility(View.VISIBLE);
            m_tv_check_c1.setVisibility(View.GONE);
            m_radio_c.setBackgroundColor(Color.GREEN);
            if(count == 1){
                correct_question_number++;
            }
        }
        else{
            m_tv_check_c0.setVisibility(View.GONE);
            m_tv_check_c1.setVisibility(View.VISIBLE);
            m_radio_c.setBackgroundColor(Color.RED);
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
