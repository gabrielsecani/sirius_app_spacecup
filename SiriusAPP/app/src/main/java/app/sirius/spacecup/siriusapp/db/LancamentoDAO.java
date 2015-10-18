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
public class LancamentoDAO extends DAO<LancamentoDAO.Lancamento> {

    public LancamentoDAO(Context context) {
        super(context);
        object = new Lancamento();
    }

    @Override
    protected ContentValues getContentValues() throws Exception {

        ContentValues cv = new ContentValues();
        cv.put("local", getObject().getLocal());
        cv.put("data", getObject().getData());
        cv.put("distancia_alvo", getObject().getDistancia_alvo());
        cv.put("distancia_alcancada", getObject().getDistancia_alcancada());
        cv.put("angulo_lancamento", getObject().getAngulo_lancamento());
        cv.put("velocidade_vento", getObject().getVelocidade_vento());
        cv.put("peso_foguete", getObject().getPeso_foguete());
        cv.put("maxima_altitude", getObject().getMaxima_altitude());
        cv.put("maxima_velocidade", getObject().getMaxima_velocidade());
        cv.put("tempo_propoulsao", getObject().getTempo_propoulsao());
        cv.put("aceleracao_pico", getObject().getAceleracao_pico());
        cv.put("aceleracao_media", getObject().getAceleracao_media());
        cv.put("tempo_apogeo_distancia", getObject().getTempo_apogeo_distancia());
        cv.put("tempo_ejecao", getObject().getTempo_ejecao());
        cv.put("taxa_decida", getObject().getTaxa_decida());
        cv.put("duracao_voo", getObject().getDuracao_voo());
        cv.put("grupo_id", getObject().getGrupo_id());
        return cv;
    }

    @Override
    public String getTableName() {
        return "lancamento";
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
        return new String[]{"_id",
                "local",
                "data",
                "distancia_alvo",
                "distancia_alcancada",
                "angulo_lancamento",
                "velocidade_vento",
                "peso_foguete",
                "maxima_altitude",
                "maxima_velocidade",
                "tempo_propoulsao",
                "aceleracao_pico",
                "aceleracao_media",
                "tempo_apogeo_distancia",
                "tempo_ejecao",
                "taxa_decida",
                "duracao_voo",
                "grupo_id"};
    }

    @Override
    public Lancamento doSelectOne(long ID) {
        String[] args = new String[]{String.valueOf(ID)};
        Cursor cursor = getDB().query(getTableName(), getAllColumns(), getWhereClause(), args, "", "", "");
        this.object = new Lancamento();
        if (cursor.moveToFirst()) {
            this.object.set_id(cursor.getInt(0));
            this.object.setLocal(cursor.getString(1));
            this.object.setData(cursor.getString(2));
            this.object.setDistancia_alvo(cursor.getDouble(3));
            this.object.setDistancia_alcancada(cursor.getDouble(4));
            this.object.setAngulo_lancamento(cursor.getDouble(5));
            this.object.setVelocidade_vento(cursor.getDouble(6));
            this.object.setPeso_foguete(cursor.getDouble(7));
            this.object.setMaxima_altitude(cursor.getDouble(8));
            this.object.setMaxima_velocidade(cursor.getDouble(9));
            this.object.setTempo_propoulsao(cursor.getDouble(10));
            this.object.setAceleracao_pico(cursor.getDouble(11));
            this.object.setAceleracao_media(cursor.getDouble(12));
            this.object.setTempo_apogeo_distancia(cursor.getDouble(13));
            this.object.setTempo_ejecao(cursor.getDouble(14));
            this.object.setTaxa_decida(cursor.getDouble(15));
            this.object.setDuracao_voo(cursor.getDouble(16));
            this.object.setGrupo_id(cursor.getInt(17));

        }
        return object;

    }

    public List<Lancamento> doSelectAll() {
        List<Lancamento> lista = new ArrayList<>();
        try {
            Cursor cursor = getDB().query(getTableName(), getAllColumns(), null, null, null, null, getAllColumns()[1] + " ASC");
            cursor.moveToFirst();

            do {
                Lancamento lancamento = new Lancamento();
                this.object.set_id(cursor.getInt(0));
                this.object.setLocal(cursor.getString(1));
                this.object.setData(cursor.getString(2));
                this.object.setDistancia_alvo(cursor.getDouble(3));
                this.object.setDistancia_alcancada(cursor.getDouble(4));
                this.object.setAngulo_lancamento(cursor.getDouble(5));
                this.object.setVelocidade_vento(cursor.getDouble(6));
                this.object.setPeso_foguete(cursor.getDouble(7));
                this.object.setMaxima_altitude(cursor.getDouble(8));
                this.object.setMaxima_velocidade(cursor.getDouble(9));
                this.object.setTempo_propoulsao(cursor.getDouble(10));
                this.object.setAceleracao_pico(cursor.getDouble(11));
                this.object.setAceleracao_media(cursor.getDouble(12));
                this.object.setTempo_apogeo_distancia(cursor.getDouble(13));
                this.object.setTempo_ejecao(cursor.getDouble(14));
                this.object.setTaxa_decida(cursor.getDouble(15));
                this.object.setDuracao_voo(cursor.getDouble(16));
                this.object.setGrupo_id(cursor.getInt(17));
                lista.add(lancamento);
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
    public class Lancamento {

        GrupoDAO.Grupo grupo;
        private long _id;
        private String local;
        private String data;
        private double distancia_alcancada;
        private double distancia_alvo;
        private double angulo_lancamento;
        private double velocidade_vento;
        private double peso_foguete;
        private double maxima_velocidade;
        private double maxima_altitude;
        private double tempo_propoulsao;
        private double aceleracao_pico;
        private double aceleracao_media;
        private double tempo_apogeo_distancia;
        private double tempo_ejecao;
        private double taxa_decida;
        private double duracao_voo;
        private int grupo_id;

        public Lancamento() {
        }

        public long get_id() {
            return _id;
        }

        public void set_id(long _id) {
            this._id = _id;
        }

        public String getLocal() {
            return local;
        }

        public void setLocal(String local) {
            this.local = local;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public double getDistancia_alcancada() {
            return distancia_alcancada;
        }

        public void setDistancia_alcancada(double distancia_alcancada) {
            this.distancia_alcancada = distancia_alcancada;
        }

        public double getDistancia_alvo() {
            return distancia_alvo;
        }

        public void setDistancia_alvo(double distancia_alvo) {
            this.distancia_alvo = distancia_alvo;
        }

        public double getVelocidade_vento() {
            return velocidade_vento;
        }

        public void setVelocidade_vento(double velocidade_vento) {
            this.velocidade_vento = velocidade_vento;
        }

        public double getPeso_foguete() {
            return peso_foguete;
        }

        public void setPeso_foguete(double peso_foguete) {
            this.peso_foguete = peso_foguete;
        }

        public double getMaxima_velocidade() {
            return maxima_velocidade;
        }

        public void setMaxima_velocidade(double maxima_velocidade) {
            this.maxima_velocidade = maxima_velocidade;
        }

        public double getMaxima_altitude() {
            return maxima_altitude;
        }

        public void setMaxima_altitude(double maxima_altitude) {
            this.maxima_altitude = maxima_altitude;
        }

        public double getTempo_propoulsao() {
            return tempo_propoulsao;
        }

        public void setTempo_propoulsao(double tempo_propoulsao) {
            this.tempo_propoulsao = tempo_propoulsao;
        }

        public double getAceleracao_pico() {
            return aceleracao_pico;
        }

        public void setAceleracao_pico(double aceleracao_pico) {
            this.aceleracao_pico = aceleracao_pico;
        }

        public double getAceleracao_media() {
            return aceleracao_media;
        }

        public void setAceleracao_media(double aceleracao_media) {
            this.aceleracao_media = aceleracao_media;
        }

        public double getTempo_apogeo_distancia() {
            return tempo_apogeo_distancia;
        }

        public void setTempo_apogeo_distancia(double tempo_apogeo_distancia) {
            this.tempo_apogeo_distancia = tempo_apogeo_distancia;
        }

        public double getTempo_ejecao() {
            return tempo_ejecao;
        }

        public void setTempo_ejecao(double tempo_ejecao) {
            this.tempo_ejecao = tempo_ejecao;
        }

        public double getTaxa_decida() {
            return taxa_decida;
        }

        public void setTaxa_decida(double taxa_decida) {
            this.taxa_decida = taxa_decida;
        }

        public double getDuracao_voo() {
            return duracao_voo;
        }

        public void setDuracao_voo(double duracao_voo) {
            this.duracao_voo = duracao_voo;
        }

        public int getGrupo_id() {
            return grupo_id;
        }

        public void setGrupo_id(int grupo_id) {
            this.grupo_id = grupo_id;
        }

        public GrupoDAO.Grupo getGrupo(Context context) {
            return new GrupoDAO(context).doSelectOne(getGrupo_id());
        }

        public double getAngulo_lancamento() {
            return angulo_lancamento;
        }

        public void setAngulo_lancamento(double angulo_lancamento) {
            this.angulo_lancamento = angulo_lancamento;
        }
    }
}
