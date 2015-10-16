package app.sirius.spacecup.siriusapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nando on 15/10/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "SiruisDatabase";
    private static final int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //construção da tabela grupos
        db.execSQL("CREATE TABLE grupo (_id INTEGER PRIMARY KEY, nome_grupo TEXT, nome_turma TEXT)");

        //construção da tabela pessoa
        db.execSQL("CREATE TABLE pessoa (_id INTEGER PRIMARY KEY, nome_pessoa TEXT, rm_pessoa INTEGER, flg_ativa INTEGER, grupo_id INTEGER)" +
                "FOREIGN KEY (grupo_id) REFERENCES grupo(_id)");

        //Construção da tabela lancamento
        db.execSQL("CREATE TABLE lancamento (_id INTEGER PRIMARY KEY, local TEXT, data TEXT, distancia_alvo REAL" +
                "angulo_lancamento REAL, velocidade_vento REAL, peso_foguete REAL, maxima_altitude REAL, maxima_velocidade REAL" +
                "tempo_propoulsao REAL, aceleracao_pico REAL, aceleracao_media REAL, tempo_apogeo_distancia REAL," +
                "tempo_ejecao REAL, taxa_decida REAL, duracao_voo REAL, grupo_id INTEGER" +
                "FOREIGN KEY (grupo_id) REFERENCES grupo(_id)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Utilizar somente quando for atualizar a versão do banco de dados.
        //Não será necessário o uso neste projeto.
        /*db.execSQL("");*/
    }
}
