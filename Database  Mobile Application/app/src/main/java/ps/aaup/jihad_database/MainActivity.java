package ps.aaup.jihad_database;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBAdapter db;
    EditText Name,Email;
    TextView t_view;
    Button Add, Remove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name=findViewById(R.id.name);
        Email=findViewById(R.id.email);
        Add=findViewById(R.id.add);
        Remove =findViewById(R.id.remove);
        t_view =findViewById(R.id.t_view);

        db=new DBAdapter(this);
        ShowContact();

    }



    public void ShowContact(){
        db.open();
        String AllContact="";

        Cursor C =db.getAllContacts();
        C.moveToFirst();
        while(C.moveToNext()) {
            String id=C.getString(0);
            String name=C.getString(1);
            String email=C.getString(2);

            AllContact+="ID: "+id +" - "+"Name: "+name+" - "+"Email: "+email +"\n";

        }

        db.close();

        t_view.setText(AllContact);

    }


    public void add (View view){

        String S_Name=Name.getText().toString();
        String S_email=Email.getText().toString();

        if (S_email.equals("")&&S_Name.equals(""))
            Toast.makeText(this,"Please make sure that you have filled out fields",Toast.LENGTH_LONG).show();

        else {
            db.open();

            long i=db.insterConact(S_Name, S_email);

            db.close();
            Toast.makeText(this,"added row successfully",Toast.LENGTH_LONG).show();
            Name.setText("");
            Email.setText("");
            ShowContact();

        }





    }



    public void remove (View view){
        String S_Name=Name.getText().toString();
        String S_email=Email.getText().toString();

        if (S_email.equals("")&&S_Name.equals(""))
            Toast.makeText(this,"Please make sure that you have filled out fields",Toast.LENGTH_LONG).show();

        else {
            db.open();

            int  i=db.deleteContact_by_N_E(S_Name, S_email);

            db.close();
            Toast.makeText(this,"delete "+i+" rows successfully",Toast.LENGTH_LONG).show();
            Name.setText("");
            Email.setText("");
            ShowContact();

        }


    }


    public void update(View view) {

        String S_Name=Name.getText().toString();
        String S_email=Email.getText().toString();

        if (S_email.equals("")&&S_Name.equals(""))
            Toast.makeText(this,"Please make sure that you have filled out fields",Toast.LENGTH_LONG).show();

        else {
            db.open();

            int  i=db.updateContact_by_N_E(S_Name, S_email);

            db.close();
            Toast.makeText(this,"update "+i+" rows successfully",Toast.LENGTH_LONG).show();
            Name.setText("");
            Email.setText("");
            ShowContact();

        }

    }
}