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
    private List<GrupoDAO.Grupo> mGrupos;
    private LayoutInflater mInflater = null;

    ListGrupoAdapter() {
    }

    public ListGrupoAdapter(Context context, List<GrupoDAO.Grupo> grupoList) {
        this.mGrupos = grupoList;
//        this.mInflater = LayoutInflater.from(context);
        this.mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mGrupos.size();
    }

    @Override
    public Object getItem(int position) {
        return mGrupos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mGrupos.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.layout_lista_grupo, null);
        }
        TextView textView;
        textView = (TextView) convertView.findViewById(R.id.layout_lista_grupo_nome_grupo);
        textView.setText(mGrupos.get(position).getNome_grupo());
        textView = (TextView) convertView.findViewById(R.id.layout_lista_grupo_turma);
        textView.setText(mGrupos.get(position).getNome_turma());
        return convertView;
    }
}
