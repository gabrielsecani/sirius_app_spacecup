package app.sirius.spacecup.siriusapp.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import app.sirius.spacecup.siriusapp.R;
import app.sirius.spacecup.siriusapp.db.GrupoDAO;
import app.sirius.spacecup.siriusapp.db.PessoaDAO;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCadNovoGrupo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCadNovoGrupo extends FragmentBase implements FragmentFooterBar.OnFragmentFooterBarInteractionListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ListView listView;
    private String mParam1;
    private String mParam2;

    private EditText edtNomeGrupo;
    private EditText edtTurmaGrupo;
    private ImageButton alteraNomeGrupo;
    private ImageButton alteraTurmaGrupo;

    private long idGrupo;

    private GrupoDAO grupoDAO;

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

        View view = inflater.inflate(R.layout.fragment_cad_novo_grupo, container, false);

        edtNomeGrupo = (EditText) view.findViewById(R.id.edt_nome_grupo);
        edtTurmaGrupo = (EditText) view.findViewById(R.id.edt_turma_grupo);
        alteraNomeGrupo = (ImageButton) view.findViewById(R.id.img_btn_altera);
        alteraTurmaGrupo = (ImageButton) view.findViewById(R.id.img_btn_altera2);

        listView = (ListView) view.findViewById(R.id.list_membros_grupos);

        grupoDAO = new GrupoDAO(getContext());

        final List<Map<String, Object>> membros = ListarMembros();

        ImageButton addNovoMembro = (ImageButton) view.findViewById(R.id.img_btn_novo_membro);
        addNovoMembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updadeDataGrupo();
            }
        });

        /*if (membros.size() == 0) {

            ((TextView) view.findViewById(R.id.txtView_sem_grupos)).setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        } else {

            String[] chaves = {"nome", "rm"};
            int[] identificadores = {R.id.txt_nome_membro,R.id.txt_rm_membro};
//
            SimpleAdapter adapter =
                    new SimpleAdapter(getContext(), membros,
                            R.layout.layout_fragment_ranking, chaves, identificadores) {
                    };
            listView.setAdapter(adapter);

        }*/

        return view;
    }



    private List<Map<String, Object>> ListarMembros() {
        PessoaDAO grupos = new PessoaDAO(getContext());
        GrupoDAO grupoDAO = new GrupoDAO(getContext());

        return null;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void updadeDataGrupo() {

        final String nomeGrupo = String.valueOf(edtNomeGrupo.getText());
        final String turmaGrupo = String.valueOf(edtNomeGrupo.getText());

        if (nomeGrupo.isEmpty() || turmaGrupo.isEmpty()) {
            Toast.makeText(getContext(), R.string.valida_grupos, Toast.LENGTH_LONG).show();

        } else {

            AlertDialog.Builder b = new AlertDialog.Builder(getContext());
            final View view = getLayoutInflater(null).inflate(R.layout.layout_cad_membro, null);

            b.setView(view).setIcon(R.drawable.ic_novo_grupo).setTitle(R.string.novo_integrante).
                    setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setPositiveButton(R.string.adicionar, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //salvar

                            /*GrupoDAO grupoDAO = new GrupoDAO(getContext());*/
                            PessoaDAO pessoaDAO = new PessoaDAO(getContext());

                            try {
                                if (grupoDAO.getObject().get_id() == 0) {
                                    grupoDAO.getObject().setNome_turma(nomeGrupo);
                                    grupoDAO.getObject().setNome_grupo(turmaGrupo);
                                    grupoDAO.doInsert();

                                    pessoaDAO.getObject().setNome_pessoa(String.valueOf(view.findViewById(R.id.edt_nome_membro)));
                                    pessoaDAO.getObject().setRm_pessoa(Integer.parseInt(String.valueOf(view.findViewById(R.id.edt_rm_membro))));
                                    pessoaDAO.getObject().setGrupo_id((int) grupoDAO.getObject().get_id());
                                    pessoaDAO.doInsert();

                                    edtNomeGrupo.setEnabled(false);
                                    edtTurmaGrupo.setEnabled(false);
                                    alteraNomeGrupo.setVisibility(View.VISIBLE);
                                    alteraTurmaGrupo.setVisibility(View.VISIBLE);

                                } else {
                                    pessoaDAO.getObject().setNome_pessoa(String.valueOf(view.findViewById(R.id.edt_nome_membro)));
                                    pessoaDAO.getObject().setRm_pessoa(Integer.parseInt(String.valueOf(view.findViewById(R.id.edt_rm_membro))));
                                    pessoaDAO.getObject().setGrupo_id((int) grupoDAO.getObject().get_id());
                                    pessoaDAO.doInsert();

                                }

                            } catch (Exception e) {
                                Log.getStackTraceString(e);

                            }

                            Toast.makeText(getContext(), R.string.adicionado_sucesso, Toast.LENGTH_SHORT).show();

                            dialog.dismiss();
                        }
                    }).create().show();

        }
    }

    @Override
    public void onFragmentFooterBarSalvarClick(View view) {
        Toast.makeText(getContext(), "SALVOU!", Toast.LENGTH_LONG).show();
    }
}
