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
public class PessoaDAO extends DAO<PessoaDAO.Pessoa> {

    public PessoaDAO(Context context) {
        super(context);
        object = new Pessoa();
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
    public Pessoa doSelectOne(long ID) {
        String[] args = new String[]{String.valueOf(ID)};
        Cursor cursor = getDB().query(getTableName(), getAllColumns(), getWhereClause(), args, "", "", "");
        cursor.moveToFirst();
        this.object = new Pessoa();
        this.object.set_id(cursor.getInt(0));
        this.object.setNome_grupo(cursor.getString(1));
        this.object.setNome_turma(cursor.getString(2));
        return object;

    }

    public List<Pessoa> doSelectAll() {
        List<Pessoa> lista = new ArrayList<>();
        try {
            Cursor cursor = getDB().query(getTableName(), getAllColumns(), null, null, null, null, "nome_grupo ASC");
            cursor.moveToFirst();

            do {
                Pessoa object = new Pessoa();
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

    public List<Pessoa> doSelectAllRanking() {

        Cursor cursor = getDB().rawQuery("select G._ID, nome_grupo, nome_turma, l._id as lanc from GRUPO G left join LANCAMENTO L on (grupo_id=L._ID) order by distancia_alcancada asc", null);
        cursor.moveToFirst();
        List<Pessoa> lista = new ArrayList<>();
        int i = 1;
        do {
            Pessoa object = new Pessoa();
            object.set_id(cursor.getInt(0));
            object.setNome_grupo(cursor.getString(1));
            object.setNome_turma(cursor.getString(2));
            if (!cursor.isNull(3))
                object.setPosicaoRank(i++);
            lista.add(object);
        } while (cursor.moveToNext());

        return lista;

    }

    /**
     * Classe de objeto para acesso aos dados
     */
    public class Pessoa {
        private long _id;
        private String nome_grupo;
        private String nome_turma;
        private int posicaoRank;

        public Pessoa() {
        }

        public Pessoa(int _id, String nome_grupo, String nome_turma) {
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

        public int getPosicaoRank() {
            return posicaoRank;
        }

        public void setPosicaoRank(int posicaoRank) {
            this.posicaoRank = posicaoRank;
        }
    }
}
