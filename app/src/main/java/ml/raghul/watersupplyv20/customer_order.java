package ml.raghul.watersupplyv20;

import android.content.Intent;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.Locale;

public class customer_order extends AppCompatActivity {
    DataBaseHelper mydb;
    TextView welcomeuser, phone, cost;
    EditText quantity;
    Button orderbutton, amountbutt;
    Integer costperlitre = 20;
    String Cno ;
    String name;
   String actualcost;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order);
        mydb = new DataBaseHelper(this);
        welcomeuser = (TextView) findViewById(R.id.welcome_user);
        String oldtype = getIntent().getExtras().getString("Oldcontact");
        String newtype = getIntent().getExtras().getString("Newcontact");


        if (oldtype != null)
        {

            Cno = oldtype;

        }
        else if ( newtype != null)
        {

            Cno = newtype ;
        }

        Cursor res = mydb.verify_customer(Cno);

        if (res != null && res.getCount() > 0)
        {
            while (res.moveToNext())
            {
                welcomeuser.setText("Welcome "+res.getString(1));
                name = res.getString(1);


            }
        }
        phone = (TextView) findViewById(R.id.display_user_contact);
        phone.setText(Cno);
        getDateTime();
        order();

    }
        @RequiresApi(api = Build.VERSION_CODES.N)
        public void order ()
        {

            mydb = new DataBaseHelper(this);
            quantity = (EditText)findViewById(R.id.get_water_quantity);


            Integer cost = Integer.parseInt(quantity.getText().toString())*costperlitre;
            actualcost = cost.toString();
            customer_order obj = new customer_order();
            orderbutton=(Button)findViewById(R.id.book_order_button);
            orderbutton.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            boolean result = mydb.insertcustorderData(getDateTime(),Cno,quantity.getText().toString(),actualcost);
                            if(result)
                            {
                                Toast.makeText(customer_order.this, "Your order placed", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(customer_order.this,invoiceactivity.class);
                                i.putExtra("ordercontact",Cno);
                                startActivity(i);
                            }
                            else
                            {
                                Toast.makeText(customer_order.this, "Your order not placed", Toast.LENGTH_SHORT).show();
                            }




                        }
                    }
            );









        }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        Toast.makeText(this, dateFormat.format(date), Toast.LENGTH_SHORT).show();
        return dateFormat.format(date);
    }




    }

