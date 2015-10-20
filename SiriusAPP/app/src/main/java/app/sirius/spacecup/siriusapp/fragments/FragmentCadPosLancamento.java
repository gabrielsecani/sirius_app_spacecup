package app.sirius.spacecup.siriusapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import app.sirius.spacecup.siriusapp.R;
import app.sirius.spacecup.siriusapp.db.GrupoDAO;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCadPosLancamento#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class FragmentCadPosLancamento extends FragmentBase implements FragmentFooterBar.OnFragmentFooterBarInteractionListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FragmentCadPosLancamento() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentCadPosLancamento.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCadPosLancamento newInstance(String param1, String param2) {
        FragmentCadPosLancamento fragment = new FragmentCadPosLancamento();
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

        GrupoDAO.Grupo grupo = new GrupoDAO(getContext()).getObject();
        //TODO: buscar o id do botao salvar da pre lancamento e exclui-lo
        //TODO: fazer o fragment abrir os dados de pre lancamento abrirem somente leitura
        //TODO: fazer os dados da fragment pré lancamento persistir no Bundle
//        FragmentCadPreLancamento fragment = FragmentCadPreLancamento.newInstance(grupo, true);
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container_layout_posLancto, fragment);
//        fragmentTransaction.commit();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cad_pos_lancamento, container, false);

        return view;
    }


    @Override
    public void onFragmentFooterBarSalvarClick(View view) {
        Toast.makeText(getContext(), "salvar do POS lancamento", Toast.LENGTH_LONG).show();
    }
}
