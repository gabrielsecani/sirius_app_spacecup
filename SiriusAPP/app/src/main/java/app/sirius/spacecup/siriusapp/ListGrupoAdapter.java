package app.sirius.spacecup.siriusapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import app.sirius.spacecup.siriusapp.db.GrupoDAO;

/**
 * Created by gabriel.ribeiro on 20/10/2015.
 */
public class ListGrupoAdapter extends BaseAdapter {
    private List<GrupoDAO.Grupo> grupos;
    private LayoutInflater inflater = null;

    ListGrupoAdapter() {
    }

    public ListGrupoAdapter(Context context, List<GrupoDAO.Grupo> grupoList) {
        this.grupos = grupoList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return grupos.size();
    }

    @Override
    public Object getItem(int position) {
        return grupos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return grupos.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.layout_lista_grupo, null);
        TextView textView;
        textView = (TextView) view.findViewById(R.id.layout_lista_grupo_nome_grupo);
        textView.setText(grupos.get(position).getNome_grupo());
        textView = (TextView) view.findViewById(R.id.layout_lista_grupo_turma);
        textView.setText(grupos.get(position).getNome_turma());
        return view;
    }
}
