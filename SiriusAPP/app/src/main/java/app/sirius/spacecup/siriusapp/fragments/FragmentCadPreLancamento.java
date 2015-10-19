package app.sirius.spacecup.siriusapp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

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
    private static final String ARG_LANCTO = "lancamento";
    private static final String ARG_READONLY = "readonly";

    private GrupoDAO.Grupo mGrupo;
    private LancamentoDAO.Lancamento mLancamento;
    private boolean mReadOnly;
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ARG_GRUPO, mGrupo);
        outState.putSerializable(ARG_LANCTO, mLancamento);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = null;
        if (savedInstanceState != null)
            args = savedInstanceState;
        if (getArguments() != null) {
            if (args != null)
                args.putAll(getArguments());
            else
                args = getArguments();
        }
        if (args != null) {
            if (args.containsKey(ARG_GRUPO)) {
                try {
                    mGrupo = (GrupoDAO.Grupo) args.getSerializable(ARG_GRUPO);
                } catch (ClassCastException e) {
                    throw new ClassCastException("parameter " + ARG_GRUPO
                            + " must be a GrupoDAO.Grupo class");
                }
            }
            if (args.containsKey(ARG_LANCTO)) {
                try {
                    mLancamento = (LancamentoDAO.Lancamento) args.getSerializable(ARG_LANCTO);
                } catch (ClassCastException e) {
                    throw new ClassCastException("parameter " + ARG_LANCTO
                            + " must be a LancamentoDAO.Lancamento class");
                }
            }
            mReadOnly = args.getBoolean(ARG_READONLY, false);
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

        Spinner spinner = (Spinner) view.findViewById(R.id.prelancto_Grupos);
        final List<Map<String, Object>> listagrupos = new GrupoDAO(getContext()).doSelectAllMap();

        SimpleAdapter adap = new SimpleAdapter(getContext(), listagrupos, R.layout.layout_spinner, new String[]{"nome_grupo"}, new int[]{R.id.txt_spinner_item});
        spinner.setAdapter(adap);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> map = listagrupos.get(position);
                mGrupo = (GrupoDAO.Grupo) map.get("self");
                carregaDadosLancamento();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if (mReadOnly) {
            View v = view.findViewById(R.id.footerbar_prelancto);
            if (v != null)
                v.setVisibility(View.GONE);
            prelancto_angulo_lancto.setEnabled(false);
            prelancto_distanciaAlvo.setEnabled(false);
            prelancto_dtLancamento.setEnabled(false);
            prelancto_velocidade_vento.setEnabled(false);
            prelancto_peso_foguete.setEnabled(false);
        }
        return view;
    }

    private void carregaDadosLancamento() {
        if (mGrupo == null) {
            Toast.makeText(getContext(), R.string.valida_grupos, Toast.LENGTH_LONG).show();
        } else {
            LancamentoDAO dao = new LancamentoDAO(getContext());
            mLancamento = dao.doSelectOne(mGrupo);
            prelancto_angulo_lancto.setText(String.valueOf(mLancamento.getAngulo_lancamento()));
            prelancto_distanciaAlvo.setText(String.valueOf(mLancamento.getDistancia_alvo()));
            prelancto_velocidade_vento.setText(String.valueOf(mLancamento.getVelocidade_vento()));
            prelancto_peso_foguete.setText(String.valueOf(mLancamento.getPeso_foguete()));
            //TODO: Fazer colocar a data de lancamento no datepicker
//            prelancto_dtLancamento.setText(String.valueOf(mLancamento.getData()));
        }
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
        lan.setGrupo_id(mGrupo.get_id());

        dao.doInsert();
    }
}
