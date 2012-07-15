package com.siu.android.twok.activity;

import android.os.Bundle;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.siu.android.andutils.util.FragmentUtils;
import com.siu.android.twok.R;
import com.siu.android.twok.fragment.formulaire.NewIdeaDialogFragment;
import com.siu.android.twok.fragment.main.ArticlesFragment1;
import com.siu.android.twok.fragment.main.ArticlesFragment2;
import com.siu.android.twok.fragment.main.ArticlesFragment3;
import com.siu.android.twok.fragment.main.ArticlesFragment4;

public class MainActivity extends SherlockFragmentActivity
{
    //-http-proxy http://149784:ewPtrMK2@10.1.40.3:3128
    //-http-proxy http://149784:33i7ubV0@10.1.40.3:3128
    private final String EXTRA_SELECTED_TAB_ID = "select_tab_id";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        com.actionbarsherlock.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        Bundle bundle = new Bundle();
        bundle.putString(ArticlesFragment1.EXTRA_TYPE, "recent");
        com.actionbarsherlock.app.ActionBar.Tab tab;
        tab = actionBar.newTab()
                .setText("Newest")
                .setTabListener(new TabListener<ArticlesFragment1>(this, "tag_fragment_1", ArticlesFragment1.class, bundle));

        actionBar.addTab(tab);

        bundle = new Bundle();
        bundle.putString(ArticlesFragment1.EXTRA_TYPE, "populaire");
        tab = actionBar.newTab()
                .setText("Top")
                .setTabListener(new TabListener<ArticlesFragment2>(this, "tag_fragment_2", ArticlesFragment2.class, bundle));
        actionBar.addTab(tab);

        bundle = new Bundle();
        bundle.putString(ArticlesFragment1.EXTRA_TYPE, "flop");
        tab = actionBar.newTab()
                .setText("Flop")
                .setTabListener(new TabListener<ArticlesFragment3>(this, "tag_fragment_3", ArticlesFragment3.class, bundle));
        actionBar.addTab(tab);


        bundle = new Bundle();
        bundle.putString(ArticlesFragment1.EXTRA_TYPE, "random");
        tab = actionBar.newTab()
                .setText("Rand")
                .setTabListener(new TabListener<ArticlesFragment4>(this, "tag_fragment_4", ArticlesFragment4.class, bundle));
        actionBar.addTab(tab);

        if(savedInstanceState != null){
            actionBar.setSelectedNavigationItem(savedInstanceState.getInt(EXTRA_SELECTED_TAB_ID));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater2 = getSupportMenuInflater();
        inflater2.inflate(R.menu.main_menu,  menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.newIdea:
                FragmentUtils.showDialog(getSupportFragmentManager(), new NewIdeaDialogFragment());// LANCER LE DIALOGUE
                break;
            case R.id.categoryOption:
                FragmentUtils.showDialog(getSupportFragmentManager(), new NewIdeaDialogFragment());// LANCER LE DIALOGUE
                break;
            case R.id.parametre:
                FragmentUtils.showDialog(getSupportFragmentManager(), new NewIdeaDialogFragment());// LANCER LE DIALOGUE
                break;
        }
        return  true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(EXTRA_SELECTED_TAB_ID, getSupportActionBar().getSelectedNavigationIndex());
    }
}
