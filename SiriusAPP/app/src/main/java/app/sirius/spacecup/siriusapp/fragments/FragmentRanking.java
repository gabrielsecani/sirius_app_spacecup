package app.sirius.spacecup.siriusapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import app.sirius.spacecup.siriusapp.R;
import app.sirius.spacecup.siriusapp.db.RankingDAO;


public class FragmentRanking extends FragmentBase {


    /*private List<Map<String, Object>> grupos;*/
    private ListView listView;

    public FragmentRanking(){

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }

    /*@Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SaveInstanceState){

        View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        String[] chaves = {"grupo", "distancia", "posicao", "img"};
        int[] identificadores = {R.id.txtView_nome_grupo, R.id.txtView_distancia_alvo, R.id.txtView_rank_position, R.id.icon_rankin_position};

        listView = (ListView) view.findViewById(R.id.listView_ranking);

        final List<Map<String, Object>> grupos = ListarGrupos();

        SimpleAdapter adapter =
                new SimpleAdapter(getContext(), grupos,
                        R.layout.layout_fragment_ranking, chaves, identificadores) {
                };

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Map<String, Object> map = grupos.get(position);
                String msg = "Grupo selecionado: ";
                msg += (String) map.get("grupo");

                Toast.makeText(getContext(), msg,
                        Toast.LENGTH_SHORT).show();
            }
        });

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        return view;
    }

    private List<Map<String, Object>> ListarGrupos() {
        RankingDAO grupos = new RankingDAO(getContext());

        return grupos.doSelectAllMap();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
