package app.sirius.spacecup.siriusapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gabriel on 17/10/2015.
 */
public class PessoaDAO extends DAO<PessoaDAO.Pessoa> {

    public PessoaDAO(Context context) {
        super(context);
        object = new Pessoa();
    }

    @Override
    protected ContentValues getContentValues() throws Exception {

        ContentValues cv = new ContentValues();
        cv.put("nome_grupo", getObject().getNome_pessoa());
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
        return new String[]{"_id", "nome_pessoa", "rm_pessoa", "grupo_id"};
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

    public List<Pessoa> doSelectAllMembersGroup(GrupoDAO.Grupo grupo) {

        Cursor cursor = getDB().rawQuery(
                "select P._id, nome, rm" +
                        " from PESSOA P" +
                        " join GRUPO G (on G._id = P.grupo_id) where G._id = ?", new String[]{String.valueOf(grupo.get_id())});

        List<Pessoa> lista = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Pessoa membro = new Pessoa();
                membro.set_id(cursor.getInt(0));
                membro.setNome_pessoa(cursor.getString(1));
                membro.setRm_pessoa(cursor.getInt(2));
                membro.grupo = grupo;
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
        private int grupo_id;

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

        public int getGrupo_id() {
            return grupo_id;
        }

        public void setGrupo_id(int grupo_id) {
            this.grupo_id = grupo_id;
        }

        public GrupoDAO.Grupo getGrupo(Context context){
            if (grupo == null) {
                grupo = new GrupoDAO(context).doSelectOne(getGrupo_id());
            }
            return grupo;
        }

    }
}
