package app.sirius.spacecup.siriusapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.DrawableRes;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.sirius.spacecup.siriusapp.R;

/**
 * Created by Gabriel on 17/10/2015.
 */
public class RankingDAO extends DAO<RankingDAO.Ranking> {

    public RankingDAO(Context context) {
        super(context);
        object = new Ranking();
    }

    @Override
    protected ContentValues getContentValues() {
        return null;
    }

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public String[] getAllColumns() {
        return new String[0];
    }

    @Override
    public Ranking doSelectOne(long ID) {
        return null;
    }

    public List<Ranking> doSelectAll() {

        Cursor cursor = getDB().rawQuery(
                "select G.nome_grupo, G.nome_turma, L.distancia_alcancada from GRUPO G " +
                        "left join LANCAMENTO L on (grupo_id=L._ID) order by L.distancia_alcancada asc", null);

        List<Ranking> lista = new ArrayList<>();

        if (cursor.moveToFirst()) {

            int i = 1;
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMaximumFractionDigits(1);
            do {
                Ranking object = new Ranking();
                object.setNome_grupo(cursor.getString(1));
                object.setNome_turma(cursor.getString(2));
                if (!cursor.isNull(3)) {
                    object.setPosicaoRank(String.valueOf(i++));
                    object.setDistancia_alcancada(nf.format(cursor.getFloat(3)) + " m");
                } else {
                    object.setPosicaoRank("");
                    object.setDistancia_alcancada("");
                }

                lista.add(object);
            } while (cursor.moveToNext());
        }
        return lista;

    }

    public List<Map<String, Object>> doSelectAllMap() {
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        for (Ranking r : doSelectAll()) {
            HashMap<String, Object> hashMap = new HashMap<String, Object>();
            hashMap.put("grupo", r.getNome_grupo());
            hashMap.put("distancia", r.getDistancia_alcancada());
            hashMap.put("posicao", String.valueOf(r.getPosicaoRank()));
            hashMap.put("img", r.getResIdMedal());

            mapList.add(hashMap);
        }
        return mapList;
    }

    /**
     * Classe de objeto para acesso aos dados
     */
    public class Ranking extends DAO.ObjetoDao {

        private String nome_grupo;
        private String nome_turma;
        private String posicaoRank;
        private String distancia_alcancada;

        public Ranking() {
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

        public String getPosicaoRank() {
            return posicaoRank;
        }

        public void setPosicaoRank(String posicaoRank) {
            this.posicaoRank = posicaoRank;
        }

        public String getDistancia_alcancada() {
            return distancia_alcancada;
        }

        public void setDistancia_alcancada(String distancia_alcancada) {
            this.distancia_alcancada = distancia_alcancada;
        }

        @DrawableRes
        public int getResIdMedal() {

            switch (posicaoRank) {
                case "1":
                    return R.drawable.first_medal;
                case "2":
                    return R.drawable.second_medal;
                case "3":
                    return R.drawable.third_medal;
                default:
                    return R.drawable.ic_lanc_ok;
            }
        }
    }
}
