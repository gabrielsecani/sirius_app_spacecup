package app.sirius.spacecup.siriusapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gabriel Lucas de Toledo Ribeiro on 17/10/2015.
 */
public class GrupoDAO extends DAO<GrupoDAO.Grupo> {

    static String[] AllColumns = new String[]{"_id", "nome_grupo", "nome_turma"};

    public GrupoDAO(Context context) {
        super(context);
        object = new GrupoDAO.Grupo();
    }

    @Override
    public ContentValues getContentValues() {
        return getObject().getContentValues();
    }

    @Override
    public String getTableName() {
        return "grupo";
    }

    @Override
    public String[] getAllColumns() {
        return AllColumns;
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

    public List<Map<String, Object>> doSelectAllMap() {
        List<Grupo> lista = doSelectAll();
        List<Map<String, Object>> list = new ArrayList<>();
        for (Grupo g : lista) {

            Map<String,Object> map=new HashMap<>();
            for (Map.Entry<String, Object> ent:g.getContentValues().valueSet()) {
                map.put(ent.getKey(),ent.getValue());
            }
//            map.putAll((Map<? extends String, ?>) g.getContentValues().valueSet());
            map.put("self", g);
            list.add(map);

        }
        return list;
    }
    /**
     * Classe de objeto para acesso aos dados
     */
    public class Grupo extends DAO.ObjetoDao implements Serializable {
        private String nome_grupo;
        private String nome_turma;

        public Grupo() {
        }

        public Grupo(int _id, String nome_grupo, String nome_turma) {
            super.set_id(_id);
            this.nome_grupo = nome_grupo;
            this.nome_turma = nome_turma;
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

        public ContentValues getContentValues() {

            ContentValues cv = new ContentValues();
            cv.put("nome_grupo", getNome_grupo());
            cv.put("nome_turma", getNome_turma());

            return cv;
        }
    }
}
