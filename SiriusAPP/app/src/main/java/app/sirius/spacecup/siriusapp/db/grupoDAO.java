package app.sirius.spacecup.siriusapp.db;

import android.content.ContentValues;

/**
 * Created by Gabriel on 17/10/2015.
 */
public class GrupoDAO extends DAOObject<Grupo> {

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


}
