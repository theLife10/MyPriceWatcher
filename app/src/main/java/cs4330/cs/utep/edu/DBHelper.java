package cs4330.cs.utep.edu;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "pricewatcherDB";
    private static final String PRICE_TABLE = "items";

    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_STARTINGPRICE = "starting";
    private static final String KEY_CURRENTPRICE = "current";
    private static final String KEY_PERCENT = "percent";
    private static final String KEY_URL = "url";


    public DBHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + PRICE_TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT, "
                + KEY_STARTINGPRICE + " FLOAT,"
                + KEY_CURRENTPRICE + " FLOAT,"
                + KEY_PERCENT+ "FLOAT,"
                + KEY_URL+ " TEXT "+
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PRICE_TABLE);
        onCreate(db);

    }
}
