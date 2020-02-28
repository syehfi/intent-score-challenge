package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ScorerActivity extends AppCompatActivity {

    private EditText scorer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorer);
        scorer = findViewById(R.id.editText);
    }

    public void handleScorer(View view) {
        String stringToPassBack = scorer.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("score", stringToPassBack);
        setResult(RESULT_OK, intent);
        finish();
    }
}
