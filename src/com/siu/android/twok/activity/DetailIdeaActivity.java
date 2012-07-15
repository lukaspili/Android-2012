package com.siu.android.twok.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.siu.android.twok.R;
import com.siu.android.twok.adaptater.CommentAdapter;
import com.siu.android.twok.adaptater.IdeaAdapter;
import com.siu.android.twok.model.Comment;
import com.siu.android.twok.model.Idea;
import com.siu.android.twok.model.User;
import com.siu.android.twok.parser.GsonFormatter;
import com.siu.android.twok.task.*;
import com.siu.android.twok.task.mother.LoginTaskCallback;
import com.siu.android.twok.task.mother.NewIdeaTaskCallback;
import com.siu.android.twok.util.NetworkUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 27/06/12
 * Time: 14:27
 * To change this template use File | Settings | File Templates.
 */
public class DetailIdeaActivity extends SherlockFragmentActivity implements LoginTaskCallback, NewIdeaTaskCallback {

    private final String EXTRA_SELECTED_TAB_ID = "select_tab_id";

    private ListView listView;
    private CommentAdapter arrayAdapter;
    private List<Comment> listeStrings;
    private Comment commentSelected;

    private TextView textViewName;
    private TextView textViewNickName;
    private TextView textViewContent;
    private TextView textViewCategory;

    private Button buttonAlready;
    private Button buttonDontWant;
    private Button buttonWant;
    private Button buttonNewComment;

    private Idea idea;
    private SetIdeasTask task;
    private GetCommentTask commentTask;


    private CreateCommentTask taskCreateComment;
    private CreateUserTask taskCreateUser;
    private LoginUserTask taskLoginUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        // actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        textViewName = (TextView) findViewById(R.id.name);
        textViewNickName = (TextView) findViewById(R.id.date);
        textViewContent = (TextView) findViewById(R.id.description);
        textViewCategory = (TextView) findViewById(R.id.category);

        listView = (ListView) findViewById(R.id.comment);
        buttonWant = (Button) findViewById(R.id.button_want_to);
        buttonDontWant = (Button) findViewById(R.id.button_didnot_want);
        buttonAlready = (Button) findViewById(R.id.button_done);
        buttonNewComment = (Button) findViewById(R.id.button_new_comment);

        Idea idea = (Idea) getIntent().getExtras().getSerializable("idea");
        updateIdea(idea);

        //String[] listeStrings = {"Tata", " J ai dit a ma copine que je l aimais", " J ai accordée de l attention a un roux",
          //      " J'ai souris à une moche XD", "j achete un hotel rue de la paix", " Et moi a un noiche ROUX !!!",
            //    " wazaaaaa", "WAZAAAAAHH", "hellooo WAZAHHH", "WAZAHH HELLO WAZAAA HELLO WAZAAA", "WAZAAAAH HALLLLOOOOO"};
       // listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listeStrings));

        buttonWant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postMethod("want");
            }
        });
        buttonDontWant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postMethod("dont_want");
            }
        });
        buttonAlready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postMethod("already");
            }
        });
        buttonNewComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postMethod("already");
            }
        });


        Log.d("tag------------------------", "REUSSI--------REUSSI--------------REUSSI---");
        listeStrings = new ArrayList<Comment>();
        arrayAdapter = new CommentAdapter(this, listeStrings);
        listView.setAdapter(arrayAdapter);
        //arrayAdapter = new ArrayAdapter<Idea>(getSherlockActivity(), android.R.layout.simple_list_item_1, listeStrings);
        commentTask = new GetCommentTask(this.getString(R.string.url_base) + this.getString(R.string.url_ideas), this) ;
        commentTask.setFragment(this);
        commentTask.execute();
    }


    private void updateIdea(Idea idea) {
        this.idea = idea;
        textViewName.setText(idea.getName());
        textViewNickName.setText("Crée par: " + idea.getNickname() + ", le: " + idea.getDate());
        textViewContent.setText(idea.getContent());
        textViewCategory.setText("Categorie: " + idea.getCategory());

        buttonWant.setText("Je veux faire: " + idea.getWant());
        buttonDontWant.setText("Innintéressant: " + idea.getDontwant());
        buttonAlready.setText("J'ais déjà fait:" + idea.getAlready());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != task) {
            task.setActivity(null);
        }
        if (null != commentTask)
            commentTask.setFragment(null);
    }

    private void postMethod(String action) {
        task = new SetIdeasTask(this, idea, action);
        task.execute();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return false;
    }

    @Override
    public void onIdeaTaskFinished() {
        //To change body of implemented methods use File | Settings | File Templates.
    }


    private static class SetIdeasTask extends AsyncTask<Void, String, Idea> {
        private DetailIdeaActivity activity;
        private String action;
        private Idea idea;

        public SetIdeasTask(DetailIdeaActivity detailIdeaActivity, Idea idea, String action) {
            this.activity = detailIdeaActivity;
            this.idea = idea;
            this.action = action;
        }

        public void setActivity(DetailIdeaActivity detailIdeaActivity) {
            this.activity = detailIdeaActivity;
        }

        @Override
        protected Idea doInBackground(Void... aVoid) {
            if (!NetworkUtils.isOnline()) {
                return null;
            }

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("value", action));

            String data = post(String.format(activity.getString(R.string.url_base) + activity.getString(R.string.url_idea_opinion), idea.getId()), nameValuePairs);

            if (null != data) {
                return GsonFormatter.getGson().fromJson(data, Idea.class);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Idea idea) {
            if (null != activity && null != idea) {
                activity.updateIdea(idea);
            }
        }
    }

    private static String post(String url, List<NameValuePair> nameValuePairs) {
        HttpClient client = new DefaultHttpClient();
        HttpResponse response;

        HttpPost post = new HttpPost(url);

        try {
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = client.execute(post);
            InputStream in = response.getEntity().getContent();

            try {
                return IOUtils.toString(in);
            } finally {
                in.close();
            }
        } catch (Exception e) {
            Log.e(DetailIdeaActivity.class.getName(), "ERROR", e);
        }

        return null;
    }


    @Override
    public void onLoginTaskFinished(User user) {

        Log.d("siu", " Lancement du post de commentaire'");
        Toast.makeText(this, "Conexion/création de compte", Toast.LENGTH_LONG).show();

        Log.d("siu--------------siu-----------", "token : " + user.getToken());
        if (null == user) {
            Toast.makeText(this, "Invalid connexion", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private void creatNewComment() {

        taskCreateComment = new CreateCommentTask(this,"titre provisoire", "description lambdaba", idea.getId() );
        taskCreateComment.execute();


    }


}
