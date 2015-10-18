package app.sirius.spacecup.siriusapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gabriel on 17/10/2015.
 */
public class GrupoDAO extends DAO<GrupoDAO.Grupo> {

    public GrupoDAO(Context context) {
        super(context);
        object = new GrupoDAO.Grupo();
    }

    @Override
    public ContentValues getContentValues() throws Exception {

        ContentValues cv = new ContentValues();
        cv.put("nome_grupo", getObject().getNome_grupo());
        cv.put("nome_turma", getObject().getNome_turma());
        return cv;
    }

    @Override
    public String getTableName() {
        return "grupo";
    }

    @Override
    public String getWhereClause() {
        return "_id = ?";
    }

    @Override
    public String[] getWhereArgs() throws Exception {
        return new String[]{String.valueOf(getObject().get_id())};
    }

    @Override
    public String[] getAllColumns() {
        return new String[]{"_id", "nome_grupo", "nome_turma"};
    }

    @Override
    public Grupo doSelectOne(long ID) {
        String[] args = new String[]{String.valueOf(ID)};
        Cursor cursor = getDB().query(getTableName(), getAllColumns(), getWhereClause(), args, "", "", "");
        this.object = new Grupo();
        if (cursor.moveToFirst()) {
            this.object.set_id(cursor.getInt(0));
            this.object.setNome_grupo(cursor.getString(1));
            this.object.setNome_turma(cursor.getString(2));
        }
        return object;

    }

    public List<Grupo> doSelectAll() {
        List<Grupo> lista = new ArrayList<>();
        try {
            Cursor cursor = getDB().query(getTableName(), getAllColumns(), null, null, null, null, "nome_grupo ASC");
            cursor.moveToFirst();

            do {
                Grupo object = new Grupo();
                object.set_id(cursor.getInt(0));
                object.setNome_grupo(cursor.getString(1));
                object.setNome_turma(cursor.getString(2));
                lista.add(object);
            } while (cursor.moveToNext());

        } catch (Exception e) {
            Log.e(this.getClass().getSimpleName(), "Erro abrindo dados: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    /**
     * Classe de objeto para acesso aos dados
     */
    public class Grupo {
        private long _id;
        private String nome_grupo;
        private String nome_turma;

        public Grupo() {
        }

        public Grupo(int _id, String nome_grupo, String nome_turma) {
            this._id = _id;
            this.nome_grupo = nome_grupo;
            this.nome_turma = nome_turma;
        }

        public long get_id() {
            return _id;
        }

        public void set_id(int _id) {
            this._id = _id;
        }

        public String getNome_grupo() {
            return nome_grupo;
        }

        public void setNome_grupo(String nome_grupo) {
            this.nome_grupo = nome_grupo;
        }

        public String getNome_turma() {
            return nome_turma;
        }

        public void setNome_turma(String nome_turma) {
            this.nome_turma = nome_turma;
        }

    }
}
