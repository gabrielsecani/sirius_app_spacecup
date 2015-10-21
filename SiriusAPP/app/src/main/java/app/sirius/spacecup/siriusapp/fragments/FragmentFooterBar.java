package app.sirius.spacecup.siriusapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import app.sirius.spacecup.siriusapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentFooterBarInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentFooterBar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentFooterBar extends Fragment {

    private OnFragmentFooterBarInteractionListener mListener;

    public FragmentFooterBar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentFooterBar.
     */

    public static FragmentFooterBar newInstance(String param1, String param2) {
        FragmentFooterBar fragment = new FragmentFooterBar();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.layout_footer_bar, container, false);
        Button btn = (Button) view.findViewById(R.id.btnSalvar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentBase.escondeTeclado(v, getContext());
                mListener.onFragmentFooterBarSalvarClick(v);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (OnFragmentFooterBarInteractionListener) this.getParentFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(this.getParentFragment().toString()
                    + " must implement OnFragmentFooterBarInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentFooterBarInteractionListener {
        public void onFragmentFooterBarSalvarClick(View view);
    }

}
