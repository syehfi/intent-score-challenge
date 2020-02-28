package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView hasil = findViewById(R.id.result);

        Bundle extras = getIntent().getExtras();
        String result = getIntent().getExtras().getString("winner");
        if (extras != null){
            hasil.setText(result);
        }
    }
}
