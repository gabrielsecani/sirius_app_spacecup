package app.sirius.spacecup.siriusapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.sirius.spacecup.siriusapp.R;
import app.sirius.spacecup.siriusapp.db.GrupoDAO;
import app.sirius.spacecup.siriusapp.db.RankingDAO;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCadNovoGrupo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCadNovoGrupo extends FragmentBase {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView listView;
    private String mParam1;
    private String mParam2;

    public FragmentCadNovoGrupo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentCadNovoGrupo.
     */

    public static FragmentCadNovoGrupo newInstance(String param1, String param2) {
        FragmentCadNovoGrupo fragment = new FragmentCadNovoGrupo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        GrupoDAO grupoDAO = new GrupoDAO(getContext());
//
//        try {
//            grupoDAO.getObject().setNome_turma("SIS");
//            grupoDAO.getObject().setNome_grupo("turma");
//            grupoDAO.doInsert();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        View view = inflater.inflate(R.layout.fragment_cad_novo_grupo, container, false);

        String[] chaves = {"nome", "rm"};
        int[] identificadores = {R.id.txtView_nome_grupo, R.id.txtView_rm};

        listView = (ListView) view.findViewById(R.id.listView_participantes);

        final List<Map<String, Object>> participantes = new ArrayList<>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("nome", "Teste de um");
        map.put("rm", "77788");
        participantes.add(map);
        map = new HashMap<String, Object>();
        map.put("nome", "Teste de 2");
        map.put("rm", "74330");
        participantes.add(map);

        if (participantes.size() == 0) {
            ((TextView) view.findViewById(R.id.txtView_sem_grupos)).setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        } else {

            SimpleAdapter adapter =
                    new SimpleAdapter(getContext(), participantes,
                            R.layout.layout_fragment_participantes, chaves, identificadores);

            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Map<String, Object> map = participantes.get(position);
                    String msg = "Grupo selecionado: ";
                    msg += (String) map.get("grupo");

                    Toast.makeText(getContext(), msg,
                            Toast.LENGTH_SHORT).show();
                }
            });

        }
        ((Button) view.findViewById(R.id.btn1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<GrupoDAO.Grupo> grupos = new GrupoDAO(getContext()).doSelectAll();
                Toast.makeText(getContext(), "Foram encontrados: " + grupos.size() + "participantes", Toast.LENGTH_LONG).show();
            }
        });

        ((Button) view.findViewById(R.id.btn2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<RankingDAO.Ranking> grupos = new RankingDAO(getActivity()).doSelectAll();
                Toast.makeText(getActivity(), "Foram !!!! encontrados: " + grupos.size() + " ranks", Toast.LENGTH_LONG).show();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
