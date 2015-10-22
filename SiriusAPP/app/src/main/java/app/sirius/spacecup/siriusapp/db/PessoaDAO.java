package app.sirius.spacecup.siriusapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gabriel Lucas de Toledo Ribeiro on 17/10/2015.
 */
public class PessoaDAO extends DAO<PessoaDAO.Pessoa> {

    static String[] AllColumns = new String[]{"_id", "nome_pessoa", "rm_pessoa", "grupo_id"};

    public PessoaDAO(Context context) {
        super(context);
        object = new Pessoa();
    }

    @Override
    protected ContentValues getContentValues() {

        ContentValues cv = new ContentValues();
        cv.put("nome_pessoa", getObject().getNome_pessoa());
        cv.put("rm_pessoa", getObject().getRm_pessoa());
        cv.put("grupo_id", getObject().getGrupo_id());
        return cv;
    }

    @Override
    public String getTableName() {
        return "pessoa";
    }

    @Override
    public String[] getAllColumns() {
        return AllColumns;
    }

    @Override
    public Pessoa doSelectOne(long ID) {
        String[] args = new String[]{String.valueOf(ID)};
        Cursor cursor = getDB().query(getTableName(), getAllColumns(), getWhereClause(), args, "", "", "");
        this.object = new Pessoa();
        if (cursor.moveToFirst()) {
            this.object.set_id(cursor.getInt(0));
            this.object.setNome_pessoa(cursor.getString(1));
            this.object.setRm_pessoa(cursor.getInt(2));
            this.object.setGrupo_id(cursor.getInt(2));
        }
        return object;

    }

    /**
     * Busca Aluno por RM
     *
     * @param RM
     */
    public Pessoa doSelectByRM(int RM) {

        Cursor cursor = getDB().rawQuery(
                "select P._id, nome_pessoa, rm_pessoa, grupo_id" +
                        " from PESSOA P" +
                        " where G.rm_pessoa = ?", new String[]{String.valueOf(RM)});

        Pessoa membro = new Pessoa();
        if (cursor.moveToFirst()) {
            do {
                membro.set_id(cursor.getInt(0));
                membro.setNome_pessoa(cursor.getString(1));
                membro.setRm_pessoa(cursor.getInt(2));
                membro.setGrupo_id(cursor.getInt(3));

            } while (cursor.moveToNext());

        }

        return membro;
    }

    public List<Pessoa> doSelectAllMembersGroup(GrupoDAO.Grupo grupo) {

        List<Pessoa> lista = new ArrayList<>();
        if (grupo == null) {
            return lista;
        } else if (grupo.get_id() <= 0) {
            return lista;
        }

        Cursor cursor = getDB().rawQuery(
                "select P._id, nome_pessoa, rm_pessoa" +
                        " from PESSOA P" +
                        " join GRUPO G on G._id = P.grupo_id where G._id = ?", new String[]{String.valueOf(grupo.get_id())});

        if (cursor.moveToFirst()) {
            do {
                Pessoa membro = new Pessoa();
                membro.set_id(cursor.getInt(0));
                membro.setNome_pessoa(cursor.getString(1));
                membro.setRm_pessoa(cursor.getInt(2));
                membro.grupo = grupo;

                lista.add(membro);

            } while (cursor.moveToNext());

        }

        return lista;
    }

    public List<Map<String, Object>> doSelectAllMap(GrupoDAO.Grupo grupo) {

        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();

        for (Pessoa p : doSelectAllMembersGroup(grupo)) {
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("nome", p.getNome_pessoa());
            hashMap.put("rm", p.getRm_pessoa());
            mapList.add(hashMap);
        }
        return mapList;
    }

    /**
     * Classe de objeto para acesso aos dados
     */
    public class Pessoa extends DAO.ObjetoDao {

        GrupoDAO.Grupo grupo;

        private String nome_pessoa;
        private int rm_pessoa;
        private long grupo_id;

        public Pessoa() {
        }


        public String getNome_pessoa() {
            return nome_pessoa;
        }

        public void setNome_pessoa(String nome_pessoa) {
            this.nome_pessoa = nome_pessoa;
        }

        public int getRm_pessoa() {
            return rm_pessoa;
        }

        public void setRm_pessoa(int rm_pessoa) {
            this.rm_pessoa = rm_pessoa;
        }

        public long getGrupo_id() {
            return grupo_id;
        }

        public void setGrupo_id(long grupo_id) {
            this.grupo_id = grupo_id;
        }

        public void setGrupo(GrupoDAO.Grupo grupo) {
            this.grupo = grupo;
            setGrupo_id(grupo.get_id());
        }

        public GrupoDAO.Grupo getGrupo(Context context) {
            if (grupo == null)
                try {
                    GrupoDAO.Grupo newGrupo = new GrupoDAO(context).doSelectOne(getGrupo_id());
                    grupo = newGrupo;
                } catch (Exception e) {
                    Log.e(this.getClass().getSimpleName(), e.getMessage(), e);
                }
            return grupo;
        }

    }
}
