package app.sirius.spacecup.siriusapp.db;

import android.content.ContentValues;

/**
 * Created by Carol on 17/10/2015.
 */
public interface DAO<T> {
    public ContentValues getContentValues();

    public String getTableName();

    public String getDeleteClause();

    public T getObject();
}
