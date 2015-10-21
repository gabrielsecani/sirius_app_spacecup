package app.sirius.spacecup.siriusapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;

import java.util.List;
import java.util.Map;

import app.sirius.spacecup.siriusapp.MainActivity;
import app.sirius.spacecup.siriusapp.R;
import app.sirius.spacecup.siriusapp.db.GrupoDAO;
import app.sirius.spacecup.siriusapp.db.RankingDAO;

public class FragmentRanking extends FragmentBase {

    private ListView listView;

    public FragmentRanking(){

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle SaveInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        listView = (ListView) view.findViewById(R.id.listView_ranking);

        final List<Map<String, Object>> grupos = ListarGrupos();

        if (grupos.size() == 0) {

            ((TextView) view.findViewById(R.id.txtView_sem_grupos)).setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        } else {

            String[] chaves = {"grupo", "distancia", "posicao", "img"};
            int[] identificadores = {R.id.txtView_nome_grupo, R.id.txtView_distancia_alvo, R.id.txtView_rank_position, R.id.icon_rankin_position};

            SimpleAdapter adapter =
                    new SimpleAdapter(getContext(), grupos,
                            R.layout.layout_fragment_ranking, chaves, identificadores) {
                    };

            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Map<String, Object> map = grupos.get(position);
//                    String msg = "Grupo selecionado: ";
//                    msg += (String) map.get("grupo");
//                    Toast.makeText(getContext(), msg,
//                            Toast.LENGTH_SHORT).show();

                    long grupoID = Long.valueOf(String.valueOf(map.get("grupoID")));
                    GrupoDAO.Grupo grupo = new GrupoDAO(getContext()).doSelectOne(grupoID);
                    ((MainActivity) getActivity()).setFragment(1, FragmentCadNovoGrupo.newInstance(grupo, false));
                }
            });

        }

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainActivity) getContext()).onItemClick(null, null, 1, 0);
            }

        });

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
