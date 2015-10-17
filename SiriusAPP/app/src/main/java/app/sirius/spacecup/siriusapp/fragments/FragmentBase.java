package app.sirius.spacecup.siriusapp.fragments;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

import app.sirius.spacecup.siriusapp.R;

/**
 * Created by Gabriel on 17/10/2015.
 */
public class FragmentBase extends Fragment{

    /**
     * Define o resid para o título. Deve ser feito Override.
     *
     * @return Resource id para o titulo da pagina
     */
    public @StringRes int getResID_ToolbarDescricao(){
        return R.string.app_name;
    }
}
