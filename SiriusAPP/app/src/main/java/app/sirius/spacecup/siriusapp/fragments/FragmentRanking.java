package app.sirius.spacecup.siriusapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.sirius.spacecup.siriusapp.R;


public class FragmentRanking extends FragmentBase {


    private List<Map<String, Object>> grupos;
    private ListView listView;

    public FragmentRanking(){

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
    }

    /*@Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SaveInstanceState){

        View view = inflater.inflate(R.layout.fragment_ranking, container, false);

        String[] chaves = {"grupo", "distancia", "posicao", "img"};
        int[] identificadores = {R.id.txtView_nome_grupo, R.id.txtView_distancia_alvo, R.id.txtView_rank_position, R.id.icon_rankin_position};

        listView = (ListView) view.findViewById(R.id.listView_ranking);

        SimpleAdapter adapter =
                new SimpleAdapter(getContext(), ListarGrupos(),
                        R.layout.layout_fragment_ranking, chaves, identificadores);

        listView.setAdapter(adapter);

        /*return inflater.inflate(R.layout.fragment_ranking,container,false);*/

        return view;
    }

    private List<Map<String, Object>> ListarGrupos() {
        grupos = new ArrayList<Map<String, Object>>();

        Map<String, Object> item;
        int arrayLenght = 10;

        for (int i = 0; i < arrayLenght; i++) {
            item = new HashMap<String, Object>();
            item.put("grupo", "Nome Grupo");
            item.put("distancia", "10m");
            item.put("posicao", Integer.valueOf(i + 1));

            if (i == 0)
                item.put("img", R.drawable.first_medal);
            else if (i == 1)
                item.put("img", R.drawable.second_medal);
            else if (i == 2)
                item.put("img", R.drawable.third_medal);
            else
                item.put("img", R.drawable.third_medal);

            grupos.add(item);
        }


        return grupos;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
