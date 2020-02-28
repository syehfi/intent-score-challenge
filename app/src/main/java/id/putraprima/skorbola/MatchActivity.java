package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;

import id.putraprima.skorbola.model.Data;

public class MatchActivity extends AppCompatActivity {

    public static final String DATA_KEY = "data";
    public static final String WINNER = "winner";
        private static final int HOME_ACTIVITY_REQUEST_CODE = 1;
    private static final int AWAY_ACTIVITY_REQUEST_CODE = 2;
    private TextView homeText;
    private TextView awayText;
    private TextView hscore;
    private TextView ascore;
    private Data data;
    private ImageView homeLogo;
    private ImageView awayLogo;
    private int homeScore = 0;
    private int awayScore = 0;
    private TextView tv_home;
    private TextView tv_away;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        homeText = findViewById(R.id.txt_home);
        awayText = findViewById(R.id.txt_away);
        hscore = findViewById(R.id.score_home);
        ascore = findViewById(R.id.score_away);
        homeLogo = findViewById(R.id.home_logo);
        awayLogo = findViewById(R.id.away_logo);
        tv_home =  findViewById(R.id.textView3);
        tv_away =  findViewById(R.id.textView4);

        Bundle extras = getIntent().getExtras();

        if (extras != null){
            data = getIntent().getParcelableExtra(DATA_KEY);
            awayText.setText(data.getAwayName());
            homeText.setText(data.getHomeName());

            Bundle extra = getIntent().getExtras();
            Bitmap home = extra.getParcelable("logoHome");
            Bitmap away = extra.getParcelable("logoAway");
            homeLogo.setImageBitmap(home);
            awayLogo.setImageBitmap(away);

        }
        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan memindah activity ke scorerActivity dimana pada scorer activity di isikan nama pencetak gol
        //3.Dari activity scorer akan mengirim kembali ke activity matchactivity otomatis nama pencetak gol dan skor bertambah +1
        //4.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang beserta nama pencetak gol ke ResultActivity, jika seri di kirim text "Draw",
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check that it is the SecondActivity with an OK result
        if (requestCode == HOME_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                homeScore++;
                hscore.setText(String.valueOf(homeScore));
                String temp = tv_home.getText().toString();
                String nama_home = data.getStringExtra("score");
                tv_home.setText(temp + "\n" +nama_home);
            }
        } else if (requestCode == AWAY_ACTIVITY_REQUEST_CODE){
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Away", Toast.LENGTH_SHORT).show();
                awayScore++;
                ascore.setText(String.valueOf(awayScore));
                String temp = tv_away.getText().toString();
                String nama_away = data.getStringExtra("score");
                tv_away.setText(temp + "\n" +nama_away);
            }
        }
    }

    public void addHomeScore(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivityForResult(intent, HOME_ACTIVITY_REQUEST_CODE);
    }

    public void addAwayScore(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivityForResult(intent, AWAY_ACTIVITY_REQUEST_CODE);
    }

    public void handleResult(View view) {

        String winner;

        if (homeScore > awayScore){
            winner = "Tim " + data.getHomeName() + " adalah pemenang"+ "\n \n \n" + "Nama Pencetak gol"  +tv_home.getText().toString();
        } else if (homeScore < awayScore){
            winner = "Tim " + data.getAwayName() + " adalah pemenang"+ "\n \n \n" + "Nama Pencetak gol"  +tv_away.getText().toString();
        } else{
            winner = "DRAW";
        }

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(WINNER, winner);
        startActivity(intent);

    }
}
