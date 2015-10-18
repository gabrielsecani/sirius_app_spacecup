package app.sirius.spacecup.siriusapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import app.sirius.spacecup.siriusapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCadPreLancamento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCadPreLancamento extends FragmentBase implements FragmentFooterBar.OnFragmentFooterBarInteractionListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_IDGRUPO = "grupo_id";

    // TODO: Rename and change types of parameters
    private String mIDGRUPO;


    public FragmentCadPreLancamento() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param idGrupo ID do grupo que vai abrir os dados de lancamento
     * @return A new instance of fragment FragmentCadPreLancamento.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentCadPreLancamento newInstance(String idGrupo) {
        FragmentCadPreLancamento fragment = new FragmentCadPreLancamento();
        Bundle args = new Bundle();
        args.putString(ARG_IDGRUPO, idGrupo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mIDGRUPO = getArguments().getString(ARG_IDGRUPO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_cad_pre_lancamento, container, false);
    }


    @Override
    public void onFragmentFooterBarSalvarClick(View view) {
        Toast.makeText(getContext(), "botao salvar do footer", Toast.LENGTH_LONG).show();
    }
}
