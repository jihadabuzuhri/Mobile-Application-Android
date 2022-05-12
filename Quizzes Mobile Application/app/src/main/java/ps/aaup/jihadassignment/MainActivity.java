package ps.aaup.jihadassignment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView num,question;
    RadioButton Button1,Button2,Button3;
    RadioGroup RG;
    int count;
    String [] question_arr,num_arr,Button1_arr,Button2_arr,Button3_arr,answers_arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count=0;

        num= (TextView) findViewById(R.id.num);
        question= (TextView) findViewById(R.id.question);
        Button1= (RadioButton) findViewById(R.id.Button1);
        Button2= (RadioButton) findViewById(R.id.Button2);
        Button3= (RadioButton) findViewById(R.id.Button3);
        RG= (RadioGroup) findViewById(R.id.RadioGroup);
        count=1;

        num_arr = new String[]{"", " Question No. 1 :", " Question No. 2 :"," Question No. 3 :"," Question No. 4 :"," Question No. 5 :"};
        question_arr = new String[]{"", " 5*5 =", " 12/6 ="," 3+4 ="," 9-3 ="," 2*1 ="};
        Button1_arr= new String[]{"","25","3","6","3","2"};
        Button2_arr= new String[]{"","15","2","7","8","1"};
        Button3_arr = new String[]{"","53","5","12","6","5"};
        answers_arr = new String[]{"","!","!","!","!","!"};

    }



    public void next (View view){

        if (count==5)
            count=1;
        else count++;

        num.setText(num_arr[count]);
        question.setText(question_arr[count]);
        Button1.setText(Button1_arr[count]);
        Button2.setText(Button2_arr[count]);
        Button3.setText(Button3_arr[count]);



        RG.clearCheck();


        if(answers_arr[count].equals(Button1.getText().toString()))
            Button1.setChecked(true);
        else if(answers_arr[count].equals(Button2.getText().toString()))
            Button2.setChecked(true);
        else if(answers_arr[count].equals(Button3.getText().toString()))
            Button3.setChecked(true);



    }

    public void Previous (View view){

        if (count==1)
            count=5;
        else count--;

        num.setText(num_arr[count]);
        question.setText(question_arr[count]);
        Button1.setText(Button1_arr[count]);
        Button2.setText(Button2_arr[count]);
        Button3.setText(Button3_arr[count]);

        RG.clearCheck();



        if(answers_arr[count].equals(Button1.getText().toString()))
            Button1.setChecked(true);
       else if(answers_arr[count].equals(Button2.getText().toString()))
            Button2.setChecked(true);
       else if(answers_arr[count].equals(Button3.getText().toString()))
            Button3.setChecked(true);


    }


    public void answer (View view){
        if(Button1.isChecked())
            answers_arr[count] = Button1.getText().toString();

        else if(Button2.isChecked())
            answers_arr[count] = Button2.getText().toString();

        else if(Button3.isChecked())
            answers_arr[count] = Button3.getText().toString();
    }

    public void show_result (View View){
        boolean flag=true;
        for(int i=1;i<=5;i++){
            if (answers_arr[i].equals("!")){
             flag=false;
             break;
            }
        }

        if (flag){

            Intent i = new Intent(this, QuizResult.class);
            i.putExtra("answers_arr",answers_arr);
            startActivity(i);
        }
        else
        Toast.makeText(this,"please make sure that you are answered all question",Toast.LENGTH_SHORT).show();

    }




}



