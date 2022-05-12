package ps.aaup.jihadassignment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class QuizResult extends AppCompatActivity {

    String [] true_answers_arr,answers_arr;
    TextView result;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        true_answers_arr = new String[]{"","25","2","7","6","2"};
        result = (TextView) findViewById(R.id.result);

        Intent data = getIntent();
        answers_arr = data.getStringArrayExtra("answers_arr");

        count=0;
        for(int i=1;i<=5;i++){
            if (answers_arr[i].equals(true_answers_arr[i]))
                count++;
        }

        result.setText(count+"/5");

        if (count<3)
            result.setBackgroundColor(Color.RED);
        else
            result.setBackgroundColor(Color.GREEN);

    }
}