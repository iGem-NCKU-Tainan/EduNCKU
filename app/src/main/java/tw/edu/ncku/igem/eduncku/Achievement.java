package tw.edu.ncku.igem.eduncku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Achievement extends AppCompatActivity {
    private Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);
        ArrayList<String> myDataset = new ArrayList<>();
        myDataset.add("吃到一個蘋果");
        myDataset.add("PATH 1");
        myDataset.add("PATH 2");
        myDataset.add("PATH 3");
        myDataset.add("PATH 4");
        myDataset.add("PATH 5");
        MyAdapter myAdapter = new MyAdapter(myDataset);
        RecyclerView mList = (RecyclerView) findViewById(R.id.list_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mList.setLayoutManager(layoutManager);
        mList.setAdapter(myAdapter);

        toast = Toast.makeText(this, "", Toast.LENGTH_LONG);
    }
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<String> mData;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public int position;
            public TextView mTextView;
            public CardView mCardView;
            public ViewHolder(View v) {
                super(v);
                position = 0;
                mTextView = (TextView) v.findViewById(R.id.info_text);
                mCardView = (CardView) v.findViewById(R.id.card_view);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //mCardView.setCardBackgroundColor(Color.parseColor("#64a52c62"));
                        if(MainActivity.Achievement_array.get(position) == true && position != 0) {
                            Intent intent = new Intent(Achievement.this, Achievement_intro.class);
                            intent.putExtra("position", position);
                            startActivity(intent);
                        }
                    }
                });
            }
        }

        public MyAdapter(List<String> data) {
            mData = data;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.achievement, parent, false);
            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
                holder.position = position;
                holder.mTextView.setText(mData.get(position));
                if(MainActivity.Achievement_array.get(position) == true) {
                    holder.mCardView.setCardBackgroundColor(Color.parseColor("#642ca53a"));
                }
        }
        @Override
        public int getItemCount() {
            return mData.size();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_MENU:
                showTip("MENU");




                return Boolean.TRUE;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void showTip(final String str) {
        toast.setText(str);
        toast.show();
    }
}
