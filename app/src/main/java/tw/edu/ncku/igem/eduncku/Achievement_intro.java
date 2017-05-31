package tw.edu.ncku.igem.eduncku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Achievement_intro extends AppCompatActivity {

    private int position = 0;
    private String intro[][] = {
            {"什麼是DNA？", "DNA就像是一張說明書，裡面記載著人體所有的秘密，當我們還在媽媽肚子裡的時候，身體裡的DNA會決定我們的長相、身高，甚至是我們的健康。神奇的是，並不是人類才有DNA。事實上，所有生物的身體裡面都有DNA，所有生物都會按照自己身體裡的DNA來改變自己的樣子，我們之所以和其他生物長得不一樣，就是因為不同生物擁有不同的DNA喔！"},
            {"什麼是基因？", "一本說明書可能有好幾百頁，每一頁都有不同的內容。舉例來說，一本手機說明書會告訴我們這隻手機有哪些構造？我們該如何正確使用這支手機？手機故障了我們該怎麼維修？這些內容都會詳細記載在不同的段落裡。同樣的。DNA是由許許多多的基因所組成，不同的基因代表不同的功能，有的記載著血型，有的記載著髮色，甚至是很多基因共同決定一個特徵。舉例來說，我們的膚色就是由很多段基因一起決定的喔！"},
            {"什麼是基因改造？", "聽過基因改造作物嗎？有些作物天生就怕蟲子，有些農作物很容易就腐爛，有些則沒辦法在潮濕的環境裡生長，這些問題都讓農民非常傷腦筋。科學家們能利用很先進的科學技術改變這些農作物的基因，讓它們在任何狀況下都能長得很好，這樣農夫就能在有限的土地上種出更多高品質的作物了！但是基因改造需要特殊技術，基因改造也可能有我們不知道的缺點，所以一定要由專業的科學家評估後才能進行喔！"},
            {"什麼是基因轉殖？", "我們能不能讓水族箱裡的魚和水母一樣發出美麗的螢光呢？答案是可以！在沒有基因轉殖技術的年代，只有水母和深海魚能發出美麗的螢光，水族箱裡的魚雖然有很多顏色，但就是不能發光。現在的科學家非常厲害，他們能將專屬於水母的發光能力利用基因轉殖放入魚的身體裡，讓這樣的魚也能像水母一樣發出螢光。透過基因轉殖技術，人們甚至能讓稻米產生胡蘿蔔素喔！是不是很厲害呢？"},
            {"什麼是合成生物學？", "傳統的基因轉殖只能在一個生物裡放入將少數幾種基因，這樣的做法效率低，而且能做到的事情也有限。隨著人們對DNA、基因的了解愈來愈透徹，人類開始能利用合成生物學技術能將微小的細菌改造成小型工廠，而這個細菌工廠能替人類做好多好多事情，像是製造可以用來治療病人的藥、可以幫人類清除重金屬、甚至可以製造出好多好多不同顏色的染料，這些都是合成生物學技術能做到的事情喔！是不是很神奇呢？"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement_intro);
        Intent intent = getIntent();
        position = (intent.getIntExtra("position",0) - 1);
        TextView textView = (TextView) findViewById(R.id.intro_title);
        textView.setText(intro[position][0]);
        textView = (TextView) findViewById(R.id.intro_body);
        textView.setText(intro[position][1]);
    }
}
