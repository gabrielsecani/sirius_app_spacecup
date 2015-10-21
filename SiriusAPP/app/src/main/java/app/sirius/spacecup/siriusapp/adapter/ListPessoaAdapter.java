package app.sirius.spacecup.siriusapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import app.sirius.spacecup.siriusapp.R;
import app.sirius.spacecup.siriusapp.db.PessoaDAO;

/**
 * Created by nando on 21/10/2015.
 */
public class ListPessoaAdapter extends BaseAdapter {

    private List<PessoaDAO.Pessoa> mIntegrante;
    private LayoutInflater mInflater = null;

    ListPessoaAdapter() {
    }

    public ListPessoaAdapter(Context context, List<PessoaDAO.Pessoa> pessoaList) {
        this.mIntegrante = pessoaList;
//        this.mInflater = LayoutInflater.from(context);
        this.mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mIntegrante.size();
    }

    @Override
    public Object getItem(int position) {
        return mIntegrante.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mIntegrante.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.layout_membros_grupo, null);
        }
        TextView textView;
        textView = (TextView) convertView.findViewById(R.id.txt_nome_membro);
        textView.setText(mIntegrante.get(position).getNome_pessoa());
        textView = (TextView) convertView.findViewById(R.id.txt_rm_membro);
        textView.setText(mIntegrante.get(position).getRm_pessoa());
        return convertView;
    }
}
