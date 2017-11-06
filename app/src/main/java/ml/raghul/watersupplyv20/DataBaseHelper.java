package ml.raghul.watersupplyv20;

/**
 * Created by raghul on 05-11-2017.
 */



import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by raghul on 23-09-2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "watersuply2.db";


    public static final String CUST_TABLE_NAME = "Customer_table";
    public static final String cust_col_1="CONTACT";
    public static final String cust_col_2="NAME";
    public static final String cust_col_3="CITY";
    public static final String cust_col_4="TYPE";

    public static final String ORDER_TABLE_NAME="Order";
    public static final String order_col_1= "ORDER_ID";
    public static final String order_col_2= "ODATETIME";
    public static final String order_col_3="CONTACT";
    public static final String order_col_4="QUANTITY";
    public static final String order_col_5= "PAYMENT";

    public static final String FEEDBACK_TABLE_NAME="Feedback";
    public static final String feed_col_1="FEEDBACK_ID";
    public static final String feed_col_2="DESCRIPTION";
    public static final String feed_col_3="CONTACT";
    public static final String feed_col_4="FEEDBACK_STATUS";
    public static final String feed_col_5="DELIVERY_ID";







    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE "+ CUST_TABLE_NAME + "(CONTACT INTEGER PRIMARY KEY,NAME TEXT,CITY TEXT)");
        db.execSQL("CREATE TABLE "+ ORDER_TABLE_NAME + "(ORDER_ID INTEGER PRIMARY KEY AUTOINCREMENT,ODATETIME TEXT,CONTACT INTEGER,QUANTITY INTEGER,PAYMENT TEXT)");
        db.execSQL("CREATE TABLE "+ FEEDBACK_TABLE_NAME + "(FEEDBACK_ID INTEGER PRIMARY KEY AUTOINCREMENT,DESCRIPTION TEXT,CONTACT INTEGER,FEEDBACK_STATUS TEXT)");



    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion,int newversion)
    {
        db.execSQL("DROP TABLE IF EXIST "+CUST_TABLE_NAME);
    }
    public DataBaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,1);
        SQLiteDatabase db = this.getWritableDatabase();
    }
    public boolean insertcustData(String Contact, String Name, String City)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
       contentValues.put(cust_col_1,Contact);
        contentValues.put(cust_col_2,Name);
        contentValues.put(cust_col_3,City);

        long result = db.insert(CUST_TABLE_NAME,null,contentValues);
        db.close();
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public Cursor readcustdata()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor custdata = db.rawQuery("SELECT * FROM "+CUST_TABLE_NAME,null);
        return custdata;
    }
   public boolean updatecustdata(String Contact,String Name,String City)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(cust_col_1,Contact);
        contentValues.put(cust_col_2,Name);
        contentValues.put(cust_col_3,City);
        int result=db.update(CUST_TABLE_NAME,contentValues,"CONTACT=?",new String[]{Contact});
        if(result>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public Integer deletecustdata(String Contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(CUST_TABLE_NAME,"CONTACT=?",new String[]{Contact});
        return i;
    }

    public Cursor verify_customer(String Contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor custdata = db.rawQuery("SELECT * FROM " + CUST_TABLE_NAME + " WHERE " + "CONTACT=" + Contact, null);
        return custdata;
    }
    public boolean insertcustorderData(String odatetime, String contact, String quantity,String payment)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(order_col_2,odatetime);
        contentValues.put(order_col_3,contact);
        contentValues.put(order_col_4,quantity);
        contentValues.put(order_col_5,payment);

        long result = db.insert(ORDER_TABLE_NAME,null,contentValues);
        db.close();
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public Cursor readcustorderdata(String Contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor custdata = db.rawQuery("SELECT * FROM "+ORDER_TABLE_NAME+" WHERE CONTACT="+Contact,null);
        return custdata;
    }

    public Integer deletecustorderdata(String orderid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(ORDER_TABLE_NAME,"ORDER_ID=?",new String[]{orderid});
        return i;
    }

    public boolean insertcustfeedData(String Description, String Contact, String Feedback_status)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(feed_col_2,Description);
        contentValues.put(feed_col_3,Contact);
        contentValues.put(feed_col_4,Feedback_status);

        long result = db.insert(FEEDBACK_TABLE_NAME,null,contentValues);
        db.close();
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }










}
