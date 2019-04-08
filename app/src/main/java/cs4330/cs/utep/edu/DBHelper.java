package cs4330.cs.utep.edu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.support.customtabs.CustomTabsIntent.KEY_DESCRIPTION;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "pricewatcherDB";
    private static final String PRICE_TABLE = "items";

    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_STARTINGPRICE = "starting";
    private static final String KEY_CURRENTPRICE = "current";
    private static final String KEY_URL = "url";
    private static final String KEY_DONE = "done";


    public DBHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + PRICE_TABLE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT, "
                + KEY_STARTINGPRICE + "REAL,"
                + KEY_CURRENTPRICE + "REAL,"
                + KEY_URL + "TEXT,"
                + KEY_DONE + " INTEGER" + ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PRICE_TABLE);
        onCreate(db);

    }

    public boolean addItem(String name,  String url,float start,float curr) {
        Item item = new Item();
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name); // task name
        values.put(KEY_STARTINGPRICE,start);
        values.put(KEY_CURRENTPRICE,curr);
        values.put(KEY_URL, url);
        long id = db.insert(PRICE_TABLE, null, values);
        item.setId((int) id);
        db.close();

        if(id == -1){
            return false;
        }
        else {
            return true;
        }
    }
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PRICE_TABLE, null, new String[]{});
        db.close();
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + PRICE_TABLE;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public boolean deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(PRICE_TABLE, KEY_ID +  "=" + id, null) > 0;
    }
    //get back to this tommorrow
    public List<Item> allItems() {
        List<Item> todoList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + PRICE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String description = cursor.getString(1);

                ToDoItem task = new ToDoItem(id, description, done);
                todoList.add(task);
            } while (cursor.moveToNext());
        }
        return todoList;
    }


}
