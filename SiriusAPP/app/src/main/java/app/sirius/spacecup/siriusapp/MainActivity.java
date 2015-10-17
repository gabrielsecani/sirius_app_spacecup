package app.sirius.spacecup.siriusapp;


import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.sirius.spacecup.siriusapp.fragments.FragmentBase;
import app.sirius.spacecup.siriusapp.fragments.FragmentCadNovoGrupo;

/**
 * Created by nando on 14/10/2015.
 */
//public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, FragmentCadNovoGrupo.OnFragmentInteractionListener {

    android.support.v7.widget.Toolbar toolbar;
    DrawerLayout layout;
    ActionBarDrawerToggle toogle;
    ListView menu;
    DrawerMenuAdapter menuAdapter;

    Context contexto = this;

    public void onCreate (Bundle SaveInstanceState){
        super.onCreate(SaveInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar_menu);
        layout = (DrawerLayout) findViewById(R.id.drawerLayout_menu);
        menu = (ListView) findViewById(R.id.listView_menu);
        menu.setOnItemClickListener(this);

        List<DrawerMenuItem> menuItems = generateDrawerMenuItems();
        menuAdapter = new DrawerMenuAdapter(getApplicationContext(), menuItems);
        menu.setAdapter(menuAdapter);

        toogle = new ActionBarDrawerToggle(this,layout,toolbar,R.string.app_name,R.string.app_name) {
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }
        };
        layout.setDrawerListener(toogle);

        if(SaveInstanceState == null){
            onItemClick(null, null, 0, 0);
        }
    }

    private List<DrawerMenuItem> generateDrawerMenuItems() {

        String[] itens = getResources().getStringArray(R.array.drawer_itens);
        TypedArray icones = getResources().obtainTypedArray(R.array.drawer_icons);
        try {
            List<DrawerMenuItem> result = new ArrayList<DrawerMenuItem>();
            for (int i = 0; i < itens.length; i++) {
                DrawerMenuItem item = new DrawerMenuItem();
                item.setTexto(itens[i]);
                item.setIcone(icones.getResourceId(i, -1));
                result.add(item);
            }
            return result;
        } finally {
            icones.recycle();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                setFragment(0, FragmentCadNovoGrupo.class);
                break;
            case 1:
                setFragment(1, FragmentCadNovoGrupo.class);
                break;
            case 2:
                setFragment(2, FragmentCadNovoGrupo.class);
                break;
            case 3:
                setFragment(3, FragmentCadNovoGrupo.class);
                break;
        }

    }

    @Override
    public void onBackPressed() {
        if (layout.isDrawerOpen(menu)) {
            layout.closeDrawer(menu);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toogle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
// Pass any configuration change to the drawer toggls
        toogle.onConfigurationChanged(newConfig);
    }

    public void setFragment(int position, Class fragmentClass) {

        try {
            FragmentBase fragment = (FragmentBase) fragmentClass.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout_menu, fragment, fragmentClass.getSimpleName());
            fragmentTransaction.commit();
            ((TextView) toolbar.findViewById(R.id.txtToolbarDescricao)).setText(fragment.getResID_ToolbarDescricao());

            menu.setItemChecked(position, true);
            layout.closeDrawer(menu);
            menu.invalidateViews();

        }
        catch (Exception ex){
            Log.e("setFragment", ex.getMessage());
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //
    }
}
