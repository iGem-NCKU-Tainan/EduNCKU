package tw.edu.ncku.igem.eduncku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Achievement_intro extends AppCompatActivity {

    private int position = 0;
    private String intro[] = {
            "A",
            "B",
            "C",
            "D",
            "E"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement_intro);
        Intent intent = getIntent();
        position = intent.getIntExtra("position",0);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(intro[position]);
    }
}
