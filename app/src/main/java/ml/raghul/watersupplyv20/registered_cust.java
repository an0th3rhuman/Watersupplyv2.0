package ml.raghul.watersupplyv20;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registered_cust extends AppCompatActivity {

    DataBaseHelper mydb;
    EditText get_cust_contacts;
    Button cust_Verify;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_cust);
        verify_customer();
    }

    public void verify_customer()
    {
        mydb = new DataBaseHelper(this);
        get_cust_contacts = (EditText)findViewById(R.id.cust_verify_contact);
        cust_Verify = (Button)findViewById(R.id.goto_order_button);
        cust_Verify.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {

                        String Contact = get_cust_contacts.getText().toString();
                        Cursor res = mydb.verify_customer(Contact);
                        StringBuffer stringBuffer = new StringBuffer();
                        if (res==null)
                        {
                            Toast.makeText(registered_cust.this, "You are not registered", Toast.LENGTH_SHORT).show();
                        }
                        if(res!=null && res.getCount()>0)
                        {
                            while (res.moveToNext())
                            {
                                stringBuffer.append("Contact: "+res.getString(0)+"\n");
                                stringBuffer.append("Name: " +res.getString(1)+"\n");
                                stringBuffer.append("City "+res.getString(1)+"\n");
                                Toast.makeText(registered_cust.this, "Welcome " + res.getString(1), Toast.LENGTH_LONG).show();


                            }

                            Intent i = new Intent(registered_cust.this,customer_order.class);
                            String getcustcontact = get_cust_contacts.getText().toString();

                            i.putExtra("Oldcontact",getcustcontact);
                            startActivity(i);
                            finish();


                        }



                    }

                }
        );




    }
}
