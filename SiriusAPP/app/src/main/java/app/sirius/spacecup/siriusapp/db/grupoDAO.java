package app.sirius.spacecup.siriusapp.db;

import android.content.ContentValues;

/**
 * Created by Gabriel on 17/10/2015.
 */
public class GrupoDAO extends DAOObject<GrupoDAO.Grupo> {

    @Override
    public ContentValues getContentValues() {

        ContentValues cv = new ContentValues();
        if (getObject().get_id() > 0) {
            cv.put("nome_grupo", getObject().get_id());
        }
        cv.put("nome_grupo", getObject().getNome_grupo());
        cv.put("nome_turma", getObject().getNome_turma());
        return cv;
    }

    @Override
    public String getTableName() {
        return "grupo";
    }

    @Override
    public String getDeleteClause() {
        return "_id = " + (getObject().get_id());
    }


    /**
     * Classe de objeto para acesso aos dados
     */
    public static class Grupo {
        private int _id;
        private String nome_grupo;
        private String nome_turma;

        public Grupo() {
        }

        public Grupo(int _id, String nome_grupo, String nome_turma) {
            this._id = _id;
            this.nome_grupo = nome_grupo;
            this.nome_turma = nome_turma;
        }

        public int get_id() {
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
