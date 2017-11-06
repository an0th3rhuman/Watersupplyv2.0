package ml.raghul.watersupplyv20;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class invoiceactivity extends AppCompatActivity {

    TextView invoiph,invoiquanti,invoitimedate,invoicost,invoorderid;
    DataBaseHelper mydb;
    Button cancelorder;
    String orderphone;
    Cursor res1;
    Button gofeedback;

    customer_order custorderobj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoiceactivity);
         orderphone = getIntent().getExtras().getString("ordercontact");

        invoiph = (TextView)findViewById(R.id.invoicephone);
        invoiquanti=(TextView)findViewById(R.id.invoicequantity);
        invoitimedate=(TextView)findViewById(R.id.invoicetimedate);
        invoicost = (TextView)findViewById(R.id.costestimated);

        invoorderid = (TextView)findViewById(R.id.invoiorderid);

        invoiph.setText(orderphone);
        mydb = new DataBaseHelper(this);
        gofeedback = (Button)findViewById(R.id.feedback_invoice_button);
        gofeedback.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(invoiceactivity.this,Feedbackactivity.class);
                        i.putExtra("feedbackcontact",orderphone);
                        startActivity(i);
                    }
                }
        );

/*        res1 = mydb.readcustorderdata(orderphone);

        if (res1 != null && res1.getCount() > 0)
        {
            while (res1.moveToNext())
            {


                invoiph.setText(res1.getColumnIndex("CONTACT"));
                invoiquanti.setText(res1.getColumnIndex("QUANTITY"));
                invoitimedate.setText(res1.getColumnIndex("ODATETIME"));





            }
        }*/
    }


}
