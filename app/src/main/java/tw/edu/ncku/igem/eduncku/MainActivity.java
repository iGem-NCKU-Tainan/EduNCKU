package tw.edu.ncku.igem.eduncku;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    static ArrayList<Boolean> Achievement_array;
    private Button snake_btn;
    private Button qa_btn;
    private Button achievement_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent intent = new Intent();

        snake_btn = (Button) findViewById(R.id.snake_btn);
        snake_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.setClass(MainActivity.this, GameSnake.class);
                startActivityForResult(intent, 1);
                //startActivity(intent);
            }
        });

        qa_btn = (Button) findViewById(R.id.qa_btn);
        qa_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, QA.class);
                startActivity(intent);
            }
        });

        achievement_btn = (Button) findViewById(R.id.achievement_btn);
        achievement_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Achievement.class);
                startActivity(intent);
            }
        });

    }
}
