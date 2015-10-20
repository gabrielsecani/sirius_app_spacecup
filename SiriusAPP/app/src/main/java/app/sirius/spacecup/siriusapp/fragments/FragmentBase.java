package app.sirius.spacecup.siriusapp.fragments;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

/**
 * Created by Gabriel Lucas de Toledo Ribeiro on 17/10/2015.
 */
public class FragmentBase extends Fragment {

    private int toolbarTitle;

    /**
     * Resource id para o titulo da pagina
     */
//    protected String title;

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
