package ml.raghul.watersupplyv20;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class invoiceactivity extends AppCompatActivity {

    TextView invoiph,invoiquanti,invoitimedate,invoicost,invoorderid;
    DataBaseHelper mydb;
    Button cancelorder;
    String orderphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoiceactivity);
         orderphone = getIntent().getExtras().getString("ordercontact");

        invoiph = (TextView)findViewById(R.id.invoicephone);
        invoiquanti=(TextView)findViewById(R.id.invoicequantity);
        invoitimedate=(TextView)findViewById(R.id.invoicetimedate);
        invoicost = (TextView)findViewById(R.id.costestimated);
        invoicost = (TextView)findViewById(R.id.invoiorderid);

        invoiph.setText(orderphone);
        mydb = new DataBaseHelper(this);
        Cursor res = mydb.readcustorderdata(orderphone);

        if (res != null && res.getCount() > 0)
        {
            while (res.moveToNext())
            {
                


            }
        }
    }


}
