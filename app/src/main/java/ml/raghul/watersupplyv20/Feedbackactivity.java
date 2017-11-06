package ml.raghul.watersupplyv20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Feedbackactivity extends AppCompatActivity {

    EditText feedback;
    Button submit;
    String contact;
    DataBaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbackactivity);


        contact = getIntent().getExtras().getString("feedbackcontact");

        mydb = new DataBaseHelper(this);

        //TODO:Algo for flag status bit

        submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Boolean result = mydb.insertcustfeedData(getfeedback(),contact,"1");
                        if(result)
                        {
                            Toast.makeText(Feedbackactivity.this, "Thankyou for submitting feedback", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(Feedbackactivity.this, "Your feedback is not submitted", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );


    }

    public String getfeedback()
    {
        String description;
        feedback = (EditText)findViewById(R.id.get_feedback);
        description = feedback.getText().toString();
        return description;


    }


}
