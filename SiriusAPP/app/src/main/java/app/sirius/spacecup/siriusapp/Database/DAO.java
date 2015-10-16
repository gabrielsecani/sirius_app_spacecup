package app.sirius.spacecup.siriusapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by nando on 15/10/2015.
 */
public class DAO {

    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public DAO(Context context){
        helper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDB(){
        if(db == null){
            db = helper.getWritableDatabase();
        }
        return db;
    }

    public void close(){
        helper.close();
    }
}
