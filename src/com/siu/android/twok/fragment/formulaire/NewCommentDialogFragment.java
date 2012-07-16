package com.siu.android.twok.fragment.formulaire;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockDialogFragment;
import com.siu.android.andutils.Application;
import com.siu.android.twok.DataAccessLayer;
import com.siu.android.twok.R;
import com.siu.android.twok.model.Comment;
import com.siu.android.twok.model.User;
import com.siu.android.twok.task.CreateCommentTask;
import com.siu.android.twok.task.CreateUserTask;
import com.siu.android.twok.task.LoginUserTask;
import com.siu.android.twok.task.mother.LoginTaskCallback;
import com.siu.android.twok.task.mother.NewIdeaTaskCallback;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 15/07/12
 * Time: 21:51
 * To change this template use File | Settings | File Templates.
 */
public class NewCommentDialogFragment extends SherlockDialogFragment implements LoginTaskCallback, NewIdeaTaskCallback {


    private TextView editViewContent;
    private TextView editViewPseudo;
    private TextView editViewPassword;
    private TextView editViewMail;

    private Button buttonNoAccount;
    private Button buttonCancel;
    private Button buttonValidate;

    private String id;

    private CreateCommentTask taskCreateComment;
    private CreateUserTask taskCreateUser;
    private LoginUserTask taskLoginUser;

    public NewCommentDialogFragment(String id) {
        this.id = id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_comment_dialog, container, false);

        editViewContent = (TextView) view.findViewById(R.id.editContent);

        editViewPassword = (TextView) view.findViewById(R.id.editPassword);
        editViewPseudo = (TextView) view.findViewById(R.id.editPseudo);
        editViewMail = (TextView) view.findViewById(R.id.editMail);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.

        getDialog().setTitle(Application.getContext().getString(R.string.formulaire_comment_title));

        buttonValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             }
        });

        buttonNoAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFieldFillup();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
    }

    private void checkFieldFillup() {
        if(null != DataAccessLayer.getInstance().getUser()){
            Log.d(getClass().getName(), "TRY TO: Post Idée direct");
            createComment();
        }
        else if(editViewMail.getText().toString().length() > 0){
            Log.d(getClass().getName(), "TRY TO :CREAT USER");
            createUser();
        }
        else if(editViewPseudo.length() > 0 && editViewPassword.length() > 0){
            Log.d(getClass().getName(), "TRY to loggin");
            loginUser();
        }else{
            Toast.makeText(getSherlockActivity(), "Remplissez tous les champs", 2000).show();
        }
    }


    private void createUser(){
        String name = editViewPseudo.getText().toString();
        String mail = editViewMail.getText().toString();
        String password = editViewPassword.getText().toString();

        taskCreateUser = new CreateUserTask(this, name, password, mail);
        taskCreateUser.execute();
    }

    private void loginUser(){
        String name = editViewPseudo.getText().toString();
        String password = editViewPassword.getText().toString();

        taskLoginUser = new LoginUserTask(this, name, password);
        taskLoginUser.execute();
    }


    private void createComment(){

        taskCreateComment = new CreateCommentTask(this, editViewContent.getText().toString(), "201334725211305");
        taskCreateComment.execute();
    }

    @Override
    public void onLoginTaskFinished(User user) {

        Log.d("siu", " Lancement du post de commentaire'");
        Toast.makeText(getSherlockActivity(), "Conexion/création de compte", Toast.LENGTH_LONG).show();

        Log.d("siu--------------siu-----------", "token : " + user.getToken());
        if (null == user) {
            Toast.makeText(getSherlockActivity(), "Invalid connexion", Toast.LENGTH_SHORT).show();
            return;
        }
        createComment();
    }
    @Override
    public void onIdeaTaskFinished(){
        Toast.makeText(getSherlockActivity(), R.string.toast_comment_created, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDetach() {
        super.onDetach();    //To change body of overridden methods use File | Settings | File Templates.

        if (null != taskCreateComment)
            taskCreateComment.setFragment(null);
        if (null != taskCreateUser)
            taskCreateUser.setCallback(null);
        if (null != taskLoginUser)
            taskLoginUser.setCallbackFormulaire(null);
    }

    private void creatNewComment() {

        taskCreateComment = new CreateCommentTask(this, "description lambdaba", id );
        taskCreateComment.execute();


    }


}