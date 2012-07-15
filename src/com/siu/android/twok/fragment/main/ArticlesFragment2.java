package com.siu.android.twok.fragment.main;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockFragment;
import com.google.gson.reflect.TypeToken;
import com.siu.android.twok.R;
import com.siu.android.twok.activity.DetailIdeaActivity;
import com.siu.android.twok.adaptater.IdeaAdapter;
import com.siu.android.twok.model.Idea;
import com.siu.android.twok.parser.GsonFormatter;
import com.siu.android.twok.util.UrlUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 16/05/12
 * Time: 11:07
 * To change this template use File | Settings | File Templates.
 */
public class ArticlesFragment2 extends SherlockFragment {

    public static final String EXTRA_TYPE = "type_de_donnee";

    private AddStringTask task;
    private ListView listView;
    private IdeaAdapter arrayAdapter;
    private List<Idea> listeStrings;
    private Idea ideaSelected;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // -----------------------------------------ICI ON FAIT LES FIND VIEW BY ID------------------------------------------
        View layout1 = inflater.inflate(R.layout.fragment1, container, false);
        listView = (ListView) layout1.findViewById(R.id.listView1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                ideaSelected = (Idea) listView.getItemAtPosition(position);
                Bundle objetbunble = new Bundle();
                objetbunble.putSerializable("idea", ideaSelected);
                Intent intent = new Intent(getSherlockActivity(), DetailIdeaActivity.class);
                intent.putExtras(objetbunble);
                startActivity(intent);
            }
        });

        return layout1;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //---------------------------------------ICI ON TRAINTE LES DONNEES---------------------------------------------------
        super.onActivityCreated(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.

        Log.d("tag------------------------","REUSSI--------REUSSI--------------REUSSI---");
        listeStrings = new ArrayList<Idea>();
        arrayAdapter = new IdeaAdapter(getSherlockActivity(), listeStrings);
        listView.setAdapter(arrayAdapter);
        task = new AddStringTask();
        task.setFragment(this);
        task.execute();  }

    @Override
    public void onDetach() {
        super.onDetach();
        if(null != task){
            task.setFragment(null);
        }
    }

    public void onFinishAddStringTask(List<Idea> ideas){
        if(null != ideas){
            listeStrings.clear();
            listeStrings.addAll(ideas);
            arrayAdapter.notifyDataSetChanged();
        }else{
            Toast.makeText(getSherlockActivity(), "Impossible de récupérer les données", 2000).show();
        }
    }

    static class AddStringTask extends AsyncTask<Void, String, Void> {
        private List<Idea> ideas;
        private ArticlesFragment2 fragment;

        public void setFragment(ArticlesFragment2 articlesFragment){
            fragment = articlesFragment;
        }

        @Override
        protected Void doInBackground(Void... aVoid) {
            String data = UrlUtils.downloadData("http://twothousandtwelve.heroku.com/api/ideas?sort_by=popular");
            ideas = GsonFormatter.getGson().fromJson(data, new TypeToken<List<Idea>>() { }.getType());
            // https://mail.google.com/mailmldifgmdsofgdmfogsdgm?  value=key & value2=key2 & sdfg=zdefr & sdfg=sdfg

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if(null != fragment){
                fragment.onFinishAddStringTask(ideas);
            }
        }
    }
}
