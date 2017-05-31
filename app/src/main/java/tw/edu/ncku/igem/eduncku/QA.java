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

    // the answers of the questions
    public static int answer_array[] =
            {1, 4, 2, 2, 1, 4, 3,
            3, 2, 2, 3, 4,
            1, 3, 4, 2, 3};
    public static int correct_question_number;
    public static String question_content[][] = {
            // week2
            {
                    "請問什麼樣的液體可以讓燈泡最亮？", // question1, answer = 1
                    "鹽水",
                    "糖水",
                    "自來水",
                    "以上皆是"

            },
            {
                    "請問一個水溶液中性代表ph值等於多少？", // question2, answer = 4
                    "5",
                    "0",
                    "3.14",
                    "7"
            },
            {
                    "請問一杯150公克的水，溶入30公克的鹽，溶解度是多少？", // question3, answer = 2
                    "30",
                    "0.2",
                    "5",
                    "150"
            },
            {
                    "下列何者為酸性液體?", // question4, answer = 2
                    "自來水",
                    "醋",
                    "石灰水",
                    "漱口水"
            },
            {
                    "下列何者為鹼性液體?", // question5, answer = 1
                    "肥皂水",
                    "鳳梨汁",
                    "鹽水",
                    "糖水"
            },
            {
                    "下列何者為中性溶液?", // question6, answer = 4
                    "漂白水",
                    "可樂",
                    "檸檬汁",
                    "蒸餾水"
            },
            {
                    "請問紫色高麗菜汁在加入肥皂水、鹽水、蘋果醋會分別變成什麼顏色？", // question7, answer = 3
                    "藍色、透明無色、紅色",
                    "紅色、紫色、藍色",
                    "藍色、紫色、紅色",
                    "紫色、藍色、紅色"
            },
            // week3
            {
                    "為什麼在月台的時候要站在黃線後面呢？", // question1, answer = 3
                    "火車經過時會產生高氣壓，站附近容易因氣壓流動而被吸過去",
                    "這樣離賣鐵路便當的小店比較近，方便買便當",
                    "因為白努力定律，火車經過時可能會被吸過去而發生危險",
                    "火車經過時會有電磁波，對身體不好"
            },
            {
                    "風是怎麼吹的？", // question2, answer = 2
                    "從氣度高吹到氣溫低",
                    "從氣壓高吹到氣壓低",
                    "從溼度高吹到濕度低",
                    "從氣壓低吹到氣壓高"
            },
            {
                    "颱風是高氣壓還是低氣壓？", // question3, answer = 2
                    "高氣壓",
                    "低氣壓",
                    "都不是",
                    "都有可能"
            },
            {
                    "颱風在北半球是怎麼旋轉的？", // question4, answer = 3
                    "不會旋轉",
                    "順時針旋轉",
                    "逆時針旋轉",
                    "先順時針再逆時針轉"
            },
            {
                    "颱風的旋轉方向是因為哪一種力造成", // question5, answer = 4
                    "白努力",
                    "電磁力",
                    "萬有引力",
                    "柯氏力"
            },
            // week4
            {
                    "遺傳物質位在細胞的那一個部分？", // question1, answer = 1
                    "細胞核",
                    "細胞質",
                    "細胞膜",
                    "細胞壁"
            },
            {
                    "在DNA萃取的實驗中，奇異果功能是什麼？", // question2, answer = 3
                    "提供分解酵素",
                    "中和口中酸性保護牙齒健康",
                    "萃取DNA",
                    "溶解細胞膜"
            },
            {
                    "下列何者不是上課提到的遺傳性狀?", // question3, answer = 4
                    "美人尖",
                    "酒窩",
                    "單雙眼皮",
                    "內臟位置"
            },
            {
                    "基因、DNA、染色體的大小依序為何(由大到小)？", // question4, answer = 2
                    "DNA、染色體、基因",
                    "染色體、DNA、基因",
                    "基因、DNA、染色體",
                    "染色體、基因、DNA"
            },
            {
                    "人體每個細胞裡面總共有幾對染色體呢？", // question5, answer = 3
                    "10對",
                    "46對",
                    "23對",
                    "50對"
            }
    };
    private TextView m_tv_no;
    private TextView m_tv_question;

    private Button m_radio_a;        // option A
    private TextView m_tv_check_a0;
    private TextView m_tv_check_a1;

    private Button m_radio_b;        // option B
    private TextView m_tv_check_b0;
    private TextView m_tv_check_b1;

    private Button m_radio_c;        // option C
    private TextView m_tv_check_c0;
    private TextView m_tv_check_c1;

    private Button m_radio_d;        // option D
    private TextView m_tv_check_d0;
    private TextView m_tv_check_d1;

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
        Intent intent = getIntent();//This activity is a self-cycling activity. It will call itself
        if(question_number == 1){//by calling intent
            answer = answer_array[0];//Because there's no intent at first, We
            correct_question_number = 0;// set the answer of the first question manually.
        }
        else if(intent != null){//If there's an intent coming, we capture it by getIntExtra.
            answer = intent.getIntExtra("answer",0);

        }
    }

    private void init()
    {
        m_tv_no = (TextView) findViewById(R.id.tv_no);
        m_tv_question = (TextView) findViewById(R.id.tv_question);

        m_radio_a = (Button) findViewById(R.id.radio_a);
        m_tv_check_a0 = (TextView) findViewById(R.id.tv_check_a0);
        m_tv_check_a1 = (TextView) findViewById(R.id.tv_check_a1);

        m_radio_b = (Button) findViewById(R.id.radio_b);
        m_tv_check_b0 = (TextView) findViewById(R.id.tv_check_b0);
        m_tv_check_b1 = (TextView) findViewById(R.id.tv_check_b1);

        m_radio_c = (Button) findViewById(R.id.radio_c);
        m_tv_check_c0 = (TextView) findViewById(R.id.tv_check_c0);
        m_tv_check_c1 = (TextView) findViewById(R.id.tv_check_c1);

        m_radio_d = (Button) findViewById(R.id.radio_d);
        m_tv_check_d0 = (TextView) findViewById(R.id.tv_check_d0);
        m_tv_check_d1 = (TextView) findViewById(R.id.tv_check_d1);

        m_tv_no.setText(Integer.toString(question_number));
        m_tv_question.setText(question_content[question_number - 1][0]);
        m_radio_a.setText(question_content[question_number - 1][1]);
        m_radio_b.setText(question_content[question_number - 1][2]);
        m_radio_c.setText(question_content[question_number - 1][3]);
        m_radio_d.setText(question_content[question_number -1 ][4]);
    }

    public void click_a(View view)
    {

        if (count == 0)
            count++; //Every time clicking a button the count will increase by 1
        if(answer == 1)
        {

            m_tv_check_a0.setVisibility(View.VISIBLE);
            m_tv_check_a1.setVisibility(View.GONE);
            m_radio_a.setBackgroundColor(Color.GREEN);
            if(count == 1) // true
                correct_question_number++;
        }
        else // false
        {
            m_tv_check_a0.setVisibility(View.GONE);
            m_tv_check_a1.setVisibility(View.VISIBLE);
            m_radio_a.setBackgroundColor(Color.RED);
        }
    }
    public void click_b(View view)
    {

        if (count == 1)
            count++;
        if (answer == 2)
        {

            count++;

            m_tv_check_b0.setVisibility(View.VISIBLE);
            m_tv_check_b1.setVisibility(View.GONE);
            m_radio_b.setBackgroundColor(Color.GREEN);
            if (count == 1)
                correct_question_number++;
        }
        else
        {
            m_tv_check_b0.setVisibility(View.GONE);
            m_tv_check_b1.setVisibility(View.VISIBLE);
            m_radio_b.setBackgroundColor(Color.RED);
        }
    }
    public void click_c(View view)
    {

        if (count == 2)
            count++;
        if (answer == 3)
        {

            m_tv_check_c0.setVisibility(View.VISIBLE);
            m_tv_check_c1.setVisibility(View.GONE);
            m_radio_c.setBackgroundColor(Color.GREEN);
            if (count == 1)
                correct_question_number++;
        }
        else
        {
            m_tv_check_c0.setVisibility(View.GONE);
            m_tv_check_c1.setVisibility(View.VISIBLE);
            m_radio_c.setBackgroundColor(Color.RED);
        }
    }
    public void click_d(View view)
    {
        if (count == 3)
            count++;
        if (answer == 4)
        {
            m_tv_check_d0.setVisibility(View.VISIBLE);
            m_tv_check_d1.setVisibility(View.GONE);
            m_radio_d.setBackgroundColor(Color.GREEN);
            if(count == 1)
                correct_question_number++;
        }
        else
        {
            m_tv_check_d0.setVisibility(View.GONE);
            m_tv_check_d1.setVisibility(View.VISIBLE);
            m_radio_d.setBackgroundColor(Color.RED);
        }
    }

    //返回上一頁
    public void back(View view) {
        question_number = 1;
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
            question_number = 1;//reset the pointer of the question.
            finish();
        }
    }
}
