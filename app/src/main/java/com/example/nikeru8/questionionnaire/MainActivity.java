package com.example.nikeru8.questionionnaire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {




        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
        }




        // 按下 NEXT (切下一個畫面 Activit1)
        public void next(View view) {
            Intent intent = new Intent(this, Activity1.class);

            startActivity(intent);

        }


    }

