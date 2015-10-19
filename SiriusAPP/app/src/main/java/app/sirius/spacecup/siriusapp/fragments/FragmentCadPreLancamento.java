package app.sirius.spacecup.siriusapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import app.sirius.spacecup.siriusapp.R;
import app.sirius.spacecup.siriusapp.db.GrupoDAO;
import app.sirius.spacecup.siriusapp.db.LancamentoDAO;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCadPreLancamento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCadPreLancamento extends FragmentBase implements FragmentFooterBar.OnFragmentFooterBarInteractionListener {
    private static final String ARG_GRUPO = "grupo_id";

    private GrupoDAO.Grupo mGrupo;
    private EditText prelancto_angulo_lancto;
    private EditText prelancto_distanciaAlvo;
    private DatePicker prelancto_dtLancamento;
    private EditText prelancto_velocidade_vento;
    private EditText prelancto_peso_foguete;

    public FragmentCadPreLancamento() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param grupo é um objeto <code>GrupoDAO.Grupo</code> grupo que vai abrir os dados de lancamento
     * @return A new instance of fragment FragmentCadPreLancamento.
     */
    public static FragmentCadPreLancamento newInstance(GrupoDAO.Grupo grupo) {
        FragmentCadPreLancamento fragment = new FragmentCadPreLancamento();
        Bundle args = new Bundle();
        args.putSerializable(ARG_GRUPO, grupo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            try {
                mGrupo = (GrupoDAO.Grupo) getArguments().getSerializable(ARG_GRUPO);
            } catch (ClassCastException e) {
                throw new ClassCastException("parameter " + ARG_GRUPO
                        + " must be a GrupoDAO.Grupo class");
            }
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_cad_pre_lancamento, container, false);

        prelancto_angulo_lancto = (EditText) view.findViewById(R.id.prelancto_angulo_lancto);
        prelancto_distanciaAlvo = (EditText) view.findViewById(R.id.prelancto_distanciaAlvo);
        prelancto_dtLancamento = (DatePicker) view.findViewById(R.id.prelancto_dtLancamento);
        prelancto_velocidade_vento = (EditText) view.findViewById(R.id.prelancto_velocidade_vento);
        prelancto_peso_foguete = (EditText) view.findViewById(R.id.prelancto_peso_foguete);
        return view;
    }


    @Override
    public void onFragmentFooterBarSalvarClick(View view) {
        Toast.makeText(getContext(), "botao salvar do footer", Toast.LENGTH_LONG).show();
        LancamentoDAO dao = new LancamentoDAO(getContext());
        LancamentoDAO.Lancamento lan = dao.getObject();
        lan.setAngulo_lancamento(Double.parseDouble(String.valueOf(prelancto_angulo_lancto.getText())));
        lan.setAngulo_lancamento(Double.parseDouble(String.valueOf(prelancto_distanciaAlvo.getText())));

        lan.setData(prelancto_dtLancamento.getDayOfMonth() + "/" + prelancto_dtLancamento.getMonth() + "/" + prelancto_dtLancamento.getYear());

        lan.setVelocidade_vento(Double.parseDouble(String.valueOf(prelancto_velocidade_vento.getText())));
        lan.setPeso_foguete(Double.parseDouble(String.valueOf(prelancto_peso_foguete.getText())));

        dao.doInsert();
    }
}
