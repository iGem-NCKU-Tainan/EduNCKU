package tw.edu.ncku.igem.eduncku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static tw.edu.ncku.igem.eduncku.MainActivity.Achievement_array;

public class Achievement extends AppCompatActivity {

    private Toast toast = Toast.makeText(this, "", Toast.LENGTH_LONG);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);
        ArrayList<String> myDataset = new ArrayList<>();
        myDataset.add("吃掉一個蘋果");
        myDataset.add("吃掉兩個蘋果");
        myDataset.add("吃掉三個蘋果");
        myDataset.add("吃掉四個蘋果");
        myDataset.add("吃掉五個蘋果");
        MyAdapter myAdapter = new MyAdapter(myDataset);
        RecyclerView mList = (RecyclerView) findViewById(R.id.list_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mList.setLayoutManager(layoutManager);
        mList.setAdapter(myAdapter);
    }
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<String> mData;

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public CardView mCardView;
            public ViewHolder(View v) {
                super(v);
                mTextView = (TextView) v.findViewById(R.id.info_text);
                mCardView = (CardView) v.findViewById(R.id.card_view);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCardView.setCardBackgroundColor(Color.parseColor("#E27386"));
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



            if (position != 0){
                holder.mTextView.setText(mData.get(position));
                if(MainActivity.Achievement_array.get(position) == true) {
                    holder.mCardView.setCardBackgroundColor(Color.parseColor("#B8F1CC"));
                }
            }

        }
        @Override
        public int getItemCount() {
            return mData.size();
        }

        private void showTip(final String str) {
            toast.setText(str);
            toast.show();
        }
    }
}
