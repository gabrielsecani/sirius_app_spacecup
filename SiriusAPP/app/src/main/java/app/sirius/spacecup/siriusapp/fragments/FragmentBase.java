package app.sirius.spacecup.siriusapp.fragments;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Gabriel Lucas de Toledo Ribeiro on 17/10/2015.
 */
public class FragmentBase extends Fragment {

    private int toolbarTitle;

    /**
     * Resource id para o titulo da pagina
     */
//    protected String title;
    public static void escondeTeclado(View v, Context context) {
        // esconde o teclado
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    /**
     * Define o titulo para ser exibido no Toolbar
     *
     * @return Resource id para o titulo da pagina
     */
    @StringRes
    public int getToolbarTitle() {
        return toolbarTitle;
    }
}
