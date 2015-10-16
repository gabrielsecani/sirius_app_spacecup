package app.sirius.spacecup.siriusapp.DrawerMenu;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.sirius.spacecup.siriusapp.R;

/**
 * Created by nando on 14/10/2015.
 */
public class DrawerMenuAdapter extends BaseAdapter{

    List<DrawerMenuItem> menuItens;
    Context context;

    public DrawerMenuAdapter(Context context,List<DrawerMenuItem> menuItens){
        this.context = context;
        this.menuItens = menuItens;
    }

    @Override
    public int getCount() {
        return this.menuItens.size();
    }

    @Override
    public Object getItem(int position) {
        return this.menuItens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.layout_menu_item, null);

        }

        ImageView icone = (ImageView) view.findViewById(R.id.imgView_menu_item);
        TextView titulo = (TextView) view.findViewById(R.id.txtView_menu_item);

        DrawerMenuItem item = menuItens.get(position);
        icone.setImageResource(item.getIcone());
        titulo.setText(item.getTexto());

        return view;
    }

}
