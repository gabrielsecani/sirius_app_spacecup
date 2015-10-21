package app.sirius.spacecup.siriusapp.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.util.List;

import app.sirius.spacecup.siriusapp.R;
import app.sirius.spacecup.siriusapp.adapter.ListPessoaAdapter;
import app.sirius.spacecup.siriusapp.db.GrupoDAO;
import app.sirius.spacecup.siriusapp.db.PessoaDAO;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCadNovoGrupo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCadNovoGrupo extends FragmentBase implements FragmentFooterBar.OnFragmentFooterBarInteractionListener {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_GRUPO = "grupo_id";
    private static final String ARG_READONLY = "readonly";

    private GrupoDAO.Grupo mGrupo;
    private boolean mReadOnly;


    private EditText edtNomeGrupo;
    private EditText edtTurmaGrupo;
    private TextView textViewIntegrantes;
    private TextView textViewMsgSemIntegrantes;
    private FloatingActionButton fab;
    private ListView listView;


    private List<PessoaDAO.Pessoa> integrantres;
    private ListPessoaAdapter adapter;


    public FragmentCadNovoGrupo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param grupo        Parameter 1.
     * @param openReadOnly Parameter 2.
     * @return A new instance of fragment FragmentCadNovoGrupo.
     */

    public static FragmentCadNovoGrupo newInstance(GrupoDAO.Grupo grupo, boolean openReadOnly) {
        FragmentCadNovoGrupo fragment = new FragmentCadNovoGrupo();
        /*Bundle args = new Bundle();
        args.putSerializable(ARG_GRUPO, grupo);
        args.putSerializable(ARG_READONLY, openReadOnly);*/
        Bundle args = newBundleArguments(grupo, openReadOnly);
        fragment.setArguments(args);
        return fragment;
    }

    public static Bundle newBundleArguments(GrupoDAO.Grupo grupo, boolean openReadOnly) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_GRUPO, grupo);
        args.putSerializable(ARG_READONLY, openReadOnly);
        return args;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ARG_GRUPO, mGrupo);
        outState.putSerializable(ARG_READONLY, configuraObjetos());
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
            mReadOnly = args.getBoolean(ARG_READONLY, false);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cad_novo_grupo, container, false);

        edtNomeGrupo = (EditText) view.findViewById(R.id.edt_nome_grupo);
        edtTurmaGrupo = (EditText) view.findViewById(R.id.edt_turma_grupo);
        textViewIntegrantes = (TextView) view.findViewById(R.id.txtView_membros_grupo);
        textViewMsgSemIntegrantes = (TextView) view.findViewById(R.id.txtView_sem_grupos);
        listView = (ListView) view.findViewById(R.id.list_membros_grupos);
        fab = (FloatingActionButton) view.findViewById(R.id.fab_add);

        listarMembros(mGrupo);
        if (mGrupo != null) {
            edtNomeGrupo.setText(mGrupo.getNome_grupo());
            edtTurmaGrupo.setText(mGrupo.getNome_turma());
            configuraObjetos();
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registraNovoIntegrante(view);
            }

        });

        if (mReadOnly) {
            configuraObjetos();
        }

        return view;
    }

    private void listarMembros(GrupoDAO.Grupo _mGrupo) {
        listView.setAdapter(null);
        integrantres = new PessoaDAO(getContext()).doSelectAllMembersGroup(_mGrupo);
        adapter = new ListPessoaAdapter(getContext(), integrantres);
        listView.setAdapter(adapter);
        listView.invalidateViews();
    }

    @Override
    public void onFragmentFooterBarSalvarClick(View view) {


        try {

            GrupoDAO grupoDAO = new GrupoDAO(getContext());
            GrupoDAO.Grupo grupo = grupoDAO.getObject();
            grupo.setNome_grupo(String.valueOf(edtNomeGrupo.getText()));
            grupo.setNome_turma(String.valueOf(edtTurmaGrupo.getText()));
            mGrupo = grupo;
            if (grupoDAO.doPersist()) {
                configuraObjetos();
                Toast.makeText(getContext(), R.string.msg_grupo_salvo_sucesso, Toast.LENGTH_SHORT).show();
            } else {
                Log.e(getClass().getSimpleName(), grupoDAO.getLastException().getMessage(), grupoDAO.getLastException());
                Toast.makeText(getContext(), R.string.erro_salvor_grupo, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.getStackTraceString(e);
        }
    }

    public boolean configuraObjetos() {

        textViewIntegrantes.setVisibility(View.VISIBLE);
        fab.setVisibility(View.VISIBLE);
        if (listView != null) {
            listView.setVisibility(View.VISIBLE);
        }

        return true;
    }

    public void registraNovoIntegrante(View v) {
        FragmentBase.escondeTeclado(v, getContext());

        final View view = getLayoutInflater(null).inflate(R.layout.layout_cad_membro, null);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());

        alert.setView(view).setIcon(R.drawable.ic_novo_grupo).setTitle(R.string.novo_integrante).
                setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton(R.string.cadastrar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //salvar
                        EditText nome = (EditText) view.findViewById(R.id.edt_nome_membro);
                        EditText rm = (EditText) view.findViewById(R.id.edt_rm_membro);
                        try {
                            PessoaDAO pessoaDAO = new PessoaDAO(getContext());
                            pessoaDAO.getObject().setNome_pessoa(String.valueOf(nome.getText()));
                            pessoaDAO.getObject().setRm_pessoa(Integer.valueOf(String.valueOf(rm.getText())));
                            pessoaDAO.getObject().setGrupo(mGrupo);
                            if (pessoaDAO.doPersist()) {

                                Toast.makeText(getContext(), R.string.adicionado_sucesso, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), R.string.erro_add_integrante, Toast.LENGTH_SHORT).show();
                            }

                        } catch (Exception e) {
                            Log.getStackTraceString(e);

                        }
                        listarMembros(mGrupo);
                        dialog.dismiss();

                    }
                }).create().show();
    }

}
