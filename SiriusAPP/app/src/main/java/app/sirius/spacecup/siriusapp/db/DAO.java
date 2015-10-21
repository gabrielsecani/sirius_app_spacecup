package app.sirius.spacecup.siriusapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.text.DateFormat;
import java.util.Locale;

/**
 * Created by Gabriel Lucas de Toledo Ribeiro on 17/10/2015.
 */
public abstract class DAO<T extends DAO.ObjetoDao> {

    /**
     * Formatador de data utilizado em todas as classes DAO.
     * <code>DateFormat.getDateInstance(DateFormat.DATE_FIELD, Locale.ENGLISH);</code>
     */
    final public static DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.DATE_FIELD, Locale.ENGLISH);
    private final Context context;
    protected T object;
    private Exception lastException;

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
    protected abstract ContentValues getContentValues();

    /**
     * @return Noma de tabela
     */
    public abstract String getTableName();

    /**
     * String de base para identifcação do objeto
     *
     * @return clausula where com parametros na sequencia (usando "?")
     */
    public String getWhereClause() {
        return "_id = ?";
    }

    /**
     * Arqgumentos de parametro de identificação
     *
     * @return listagem de parametros seguenciais
     */

    public String[] getWhereArgs() {
        return new String[]{String.valueOf(getObject().get_id())};
    }

    /**
     * Define o nome de todas as colunas
     */
    public abstract String[] getAllColumns();

    /**
     * @return retorna o objeto aldo de manipulação
     */
    public T getObject() {
        if (this.object == null)
            this.object = (T) new ObjetoDao();
//            throw new Exception(this.getClass().getSimpleName() + " do something wrong! You need to pass an Object Model to DAO!");
        return this.object;
    }

    /**
     * @return retorna o objeto aldo de manipulação
     */
    public void setObject(T object) {
        this.object = object;
    }

    public abstract T doSelectOne(long ID);


    /**
     * Convenience method for inserting a row into the database.
     *
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    public long doInsert() {
        long id = 0;
        try {
            id = getDB().insertOrThrow(getTableName(), null, getContentValues());
            getObject().set_id(id);
        }catch (Exception e){
            lastException = e;
        }
        return id;
    }

    /**
     * Convenience method for updating rows in the database.
     *
     * @return the number of rows affected
     */
    public int doUpdate() {
        int rowsUpdated = 0;
        try {
            rowsUpdated = getDB().update(getTableName(), getContentValues(), getWhereClause(), getWhereArgs());
        }catch (SQLException e){
            lastException = e;
        }
        return rowsUpdated;
    }

    /**
     * Convenience method for deleting rows in the database.
     *
     * @return the number of rows affected if a whereClause is passed in, 0
     * otherwise. To remove all rows and get a count pass "1" as the
     * whereClause.
     */
    public int doDelete() {
        int rowsInserted = 0;
        try {
            rowsInserted = getDB().delete(getTableName(), getWhereClause(), getWhereArgs());
        }catch (SQLException e){
            lastException = e;
        }
        return rowsInserted;

    }

    /**
     * Convientemente faz o Insert ou Update do objeto carregado neste objeto DAO
     *
     * @return boolean
     * <code><strong>true</strong></code> se foi realizado com sucesso.<br>
     * <code><strong>false</strong></code>  se ocorreu algum erro.
     */
    public boolean doPersist() {
        if (getObject().get_id() > 0) {
            return doUpdate() > 0;
        } else {
            return doInsert() > 0;
        }
    }

    public Exception getLastException() {
        return lastException;
    }

    /**
     * Classe básica para todos os objetos internos utilizados pela classe <code>DAO</code> correspondente.
     */
    public class ObjetoDao {

        private long _id;

        public long get_id() {
            return _id;
        }

        public void set_id(long _id) {
            this._id = _id;
        }
    }
}
