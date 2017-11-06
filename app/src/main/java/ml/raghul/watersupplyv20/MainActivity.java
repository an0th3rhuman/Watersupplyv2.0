package ml.raghul.watersupplyv20;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper mydb;
    RadioButton indi,comp;
    EditText cust_contact,cust_name,cust_city;
    Button cust_reg,old_cust;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register_customer();
    }

    public void register_customer()
    {




        mydb = new DataBaseHelper(this);

        cust_contact = (EditText)findViewById(R.id.get_cust_contact);
        cust_name = (EditText)findViewById(R.id.get_cust_name);
        cust_city = (EditText)findViewById(R.id.get_cust_city);
        cust_reg = (Button)findViewById(R.id.cust_register_button);

        cust_reg.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String contact = cust_contact.getText().toString();
                        String name = cust_name.getText().toString();
                        String city = cust_city.getText().toString();
                        Boolean result = mydb.insertcustData(contact,name,city);


                        if (result == true)
                        {
                            Toast.makeText(MainActivity.this, "You are registered", Toast.LENGTH_SHORT).show();

                            Intent i = new Intent(MainActivity.this,customer_order.class);

                            i.putExtra("Newcontact",contact);
                            startActivity(i);
                        }

                        else
                        {
                            Toast.makeText(MainActivity.this, "Registration failed. Try again", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );
        old_cust = (Button)findViewById(R.id.already_reg_button);
        old_cust.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this,registered_cust.class);
                        startActivity(i);
                    }
                }
        );

    }
}
