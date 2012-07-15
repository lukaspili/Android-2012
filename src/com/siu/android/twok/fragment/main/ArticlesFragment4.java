package com.siu.android.twok.fragment.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.actionbarsherlock.app.SherlockFragment;
import com.siu.android.twok.R;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 16/05/12
 * Time: 11:07
 * To change this template use File | Settings | File Templates.
 */
public class ArticlesFragment4 extends SherlockFragment {

    public static final String EXTRA_TYPE = "type_de_donnee";

    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // -----------------------------------------ICI ON FAIT LES FIND VIEW BY ID------------------------------------------
        View layout1 = inflater.inflate(R.layout.fragment1, container, false);
        listView = (ListView) layout1.findViewById(R.id.listView1);

        return layout1;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //---------------------------------------ICI ON TRAINTE LES DONNEES---------------------------------------------------
        super.onActivityCreated(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        String[] listeStrings = {"Tata"," J ai dit a ma copine que je l aimais"," J ai accordée de l attention a un roux",
                " J'ai souris à une moche XD", "  j achete un hotel rue de la paix"," Et moi a un noiche ROUX !!!"," wazaaaaa", "WAZAAAAAHH", "hellooo WAZAHHH", "WAZAHH HELLO WAZAAA HELLO WAZAAA", "WAZAAAAH HALLLLOOOOO"};
        String[] listeStrings2 = {"nailed it"};

        listView.setAdapter(new ArrayAdapter<String>(getSherlockActivity(), android.R.layout.simple_list_item_1, listeStrings));
    }

    @Override
    public void setTargetFragment(android.support.v4.app.Fragment fragment, int requestCode) {
        super.setTargetFragment(fragment, requestCode);    //To change body of overridden methods use File | Settings | File Templates.


    }
}
