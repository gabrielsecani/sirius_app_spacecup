package app.sirius.spacecup.siriusapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by nando on 15/10/2015.
 */
public class Database {

    private DatabaseHelper helper;
    private SQLiteDatabase db;
    private boolean opened = false;

    public Database(Context context) {
        helper = new DatabaseHelper(context);
    }

    protected SQLiteDatabase getDB() {

        if (db == null) {
            db = helper.getWritableDatabase();
            // Enable foreign key constraints
            if (!db.isReadOnly()) {
                db.execSQL("PRAGMA foreign_keys = ON;");
            }
            opened = true;
        }

        return db;
    }

    @Override
    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }

    public void close() {
        if (opened) {
            helper.close();
            opened = false;
        }
    }

}
