package app.sirius.spacecup.siriusapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Gabriel on 17/10/2015.
 */
public abstract class DAO<T> {

    private final Context context;
    protected T object;

    protected DAO(Context context) {
        super();
        this.context = context;
    }

    protected SQLiteDatabase getDB() {
        return new Database(context).getDB();
    }

    /**
     * Valores usados como parametro
     * <p/>
     * Não passar o _id aqui nesta lista
     *
     * @return ContentValues para realizar INSERT e UPDATE
     */
    protected abstract ContentValues getContentValues() throws Exception;

    /**
     * @return Noma de tabela
     */
    public abstract String getTableName();

    /**
     * String de base para identifcação do objeto
     *
     * @return clausula where com parametros na sequencia (usando "?")
     */
    public abstract String getWhereClause();

    /**
     * Arqgumentos de parametro de identificação
     *
     * @return listagem de parametros seguenciais
     */
    public abstract String[] getWhereArgs() throws Exception;

    /**
     * Define o nome de todas as colunas
     */
    public abstract String[] getAllColumns();

    /**
     * @return retorna o objeto aldo de manipulação
     */
    public T getObject() throws Exception {
        if (this.object == null)
            throw new Exception(this.getClass().getSimpleName() + " do something wrong! You need to pass an Object Model to DAO!");
        return this.object;
    }

    /**
     * @return retorna o objeto aldo de manipulação
     */
    public void setObject(T object) {
        this.object = object;
    }

    public abstract T doSelectOne(long ID) throws Exception;

    public long doInsert() throws Exception {
        return getDB().insert(getTableName(), null, getContentValues());

    }

    public int doUpdate() throws Exception {
        return getDB().update(getTableName(), getContentValues(), getWhereClause(), getWhereArgs());
    }

    public int doDelete() throws Exception {
        return getDB().delete(getTableName(), getWhereClause(), getWhereArgs());
    }
}
