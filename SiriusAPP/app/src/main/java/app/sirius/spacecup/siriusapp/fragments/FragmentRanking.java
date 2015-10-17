package app.sirius.spacecup.siriusapp.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.sirius.spacecup.siriusapp.R;


public class FragmentRanking extends Fragment {

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

        return inflater.inflate(R.layout.fragment_ranking,container,false);
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
