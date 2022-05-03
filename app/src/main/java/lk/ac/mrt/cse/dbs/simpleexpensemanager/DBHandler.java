package lk.ac.mrt.cse.dbs.simpleexpensemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "190055F";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME_ACCOUNTS = "accounts";

    // below variable is for our id column.
    private static final String ID_COL = "id_account";

    // below variable is for our id column.
    private static final String ACC_NO_COL = "account_no";

    // below variable is for our  name column
    private static final String ACC_NAME_COL = "account_name";
    // below variable is for our bank name column
    private static final String BANK_NAME_COL = "bank_name";

    // below variable id for our account balance column.
    private static final String BALANCE_COL = "balance";

    // below variable is for our table name.
    private static final String TABLE_NAME_TRANSACTIONS = "transactions";

    // below variable is for our id column.
    private static final String TRANSACTION_ID_COL = "transaction_id";

    // below variable is for our date column.
    private static final String DATE_COL = "date";

    // below variable is for our  expense type column
    private static final String EXPENSE_TYPE_COL = "expense_type";

    // below variable id for our amount column.
    private static final String AMOUNT_COL = "amount";

//    // below variable for our course description column.
//    private static final String DESCRIPTION_COL = "description";
//
//    // below variable is for our course tracks column.
//    private static final String TRACKS_COL = "tracks";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
//        SQLiteDatabase db=this.getWritableDatabase();
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query_account = "CREATE TABLE " + TABLE_NAME_ACCOUNTS + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ACC_NO_COL+" TEXT,"
                + ACC_NAME_COL + " TEXT,"
                + BANK_NAME_COL + " TEXT,"
                + BALANCE_COL + " REAL)";
        String query_transaction = "CREATE TABLE " + TABLE_NAME_TRANSACTIONS + " ("
                + TRANSACTION_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ACC_NO_COL + " TEXT,"
                + DATE_COL + " TEXT,"
                + EXPENSE_TYPE_COL + " TEXT,"
                + AMOUNT_COL + " TEXT)";
        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query_account);
        db.execSQL(query_transaction);

    }

    // this method is use to add new account to our sqlite database.
    public boolean addNewAccount(String accountNo,String accountName,String bankName, double balance) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(ACC_NO_COL, accountNo);
        values.put(ACC_NAME_COL, accountName);
        values.put(BANK_NAME_COL, bankName);
        values.put(BALANCE_COL, balance);


        // after adding all values we are passing
        // content values to our table.
        long result=db.insert(TABLE_NAME_ACCOUNTS, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
        if (result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getAllAccounts(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME_ACCOUNTS,null);
        return res;
    }
    public Cursor getAllAccountNumbers(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("select account_no from "+TABLE_NAME_ACCOUNTS,null);
        return res;
    }
    public Cursor getAllTransactions(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME_TRANSACTIONS,null);
        return res;
    }

    // this method is use to add new account to our sqlite database.
    public boolean addNewTransaction(String accountNo, String date,String expenseType, String amount) {
        SQLiteDatabase sem=this.getWritableDatabase();
        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(ACC_NO_COL, accountNo);
        values.put(DATE_COL, date);
        values.put(EXPENSE_TYPE_COL, expenseType);
        values.put(AMOUNT_COL, amount);


        // after adding all values we are passing
        // content values to our table.
        long result=db.insert(TABLE_NAME_TRANSACTIONS, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
        if (result==-1){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ACCOUNTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TRANSACTIONS);
        onCreate(db);
    }
}
