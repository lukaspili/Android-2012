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
import com.siu.android.twok.DataAccessLayer;
import com.siu.android.twok.R;
import com.siu.android.twok.model.User;
import com.siu.android.twok.task.CreateIdeaTask;
import com.siu.android.twok.task.CreateUserTask;
import com.siu.android.twok.task.mother.LoginTaskCallback;
import com.siu.android.twok.task.LoginUserTask;
import com.siu.android.twok.task.mother.NewIdeaTaskCallback;

/**
 * Created with IntelliJ IDEA.
 * User: dieux
 * Date: 06/07/12
 * Time: 17:37
 * To change this template use File | Settings | File Templates.
 */
public class NewIdeaDialogFragment extends SherlockDialogFragment implements LoginTaskCallback, NewIdeaTaskCallback {

    private TextView editViewDescription;
    private TextView editViewTitle;
    private TextView editViewCategory;
    private TextView editViewPseudo;
    private TextView editViewPassword;
    private TextView editViewMail;
    private TextView editViewLieu;

    private Button buttonNoAccount;
    private Button buttonCancel;
    private Button buttonValidate;

    private CreateIdeaTask taskCreateIdea;
    private CreateUserTask taskCreateUser;
    private LoginUserTask taskLoginUser;


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_idea_dialog, container, false);

        editViewTitle = (TextView) view.findViewById(R.id.editTitle);
        editViewDescription = (TextView) view.findViewById(R.id.editDescription);
        editViewLieu = (TextView) view.findViewById(R.id.editLieu);
        editViewCategory = (TextView) view.findViewById(R.id.editCategory);
        editViewPassword = (TextView) view.findViewById(R.id.editPassword);
        editViewPseudo = (TextView) view.findViewById(R.id.editPseudo);
        editViewMail = (TextView) view.findViewById(R.id.editMail);

        buttonNoAccount = (Button) view.findViewById(R.id.buttonNoAccount);
        buttonCancel = (Button) view.findViewById(R.id.buttonCancel);
        buttonValidate = (Button) view.findViewById(R.id.buttonAdd);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getDialog().setTitle("Nouvelle idée");

        buttonValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFieldAccount(String.valueOf(editViewTitle.getText()), String.valueOf(editViewCategory.getText()),
                         1, String.valueOf(editViewDescription.getText()),
                        String.valueOf(editViewPseudo.getText()), String.valueOf(editViewMail.getText()));
            }
        });

        buttonNoAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFieldAccount(String.valueOf(editViewTitle.getText()), String.valueOf(editViewCategory.getText()),
                         2, String.valueOf(editViewDescription.getText()),
                        String.valueOf(editViewPseudo.getText()), String.valueOf(editViewMail.getText()));
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
    }

    private void checkFieldAccount (String titre, String category, Integer lieu,
                                    String description, String pseudo, String mail){
        if(null != DataAccessLayer.getInstance().getUser()){
            Log.d("siu", "TRY TO: Post Idée direct");
            createIdea();
        }
        else if(editViewMail.getText().toString().length() > 0){
            Log.d("siu", "TRY TO :CREAT USER");
            createUser();
        }
        else if(editViewPseudo.length() > 0 && editViewPassword.length() > 0){
            Log.d("siu", "TRY to loggin");
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

    private void createIdea(){

        taskCreateIdea = new CreateIdeaTask(this, editViewTitle.getText().toString(), editViewDescription.getText().toString());
        taskCreateIdea.execute();
    }

    @Override
    public void onLoginTaskFinished(User user) {
        Log.d("siu", " Lancement de la création d' 'idée'");
        Toast.makeText(getSherlockActivity(), "Conexion/création de compte", Toast.LENGTH_LONG).show();

        Log.d("siu--------------siu-----------", "token : "+ user.getToken());
        if(null == user) {
            Toast.makeText(getSherlockActivity(), "Invalid connexion", Toast.LENGTH_SHORT).show();
            return;
        }

        createIdea();
    }
    @Override
    public void onIdeaTaskFinished(){
        Toast.makeText(getSherlockActivity(), R.string.toast_idea_created, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDetach() {
        super.onDetach();    //To change body of overridden methods use File | Settings | File Templates.

        if (null != taskCreateIdea)
            taskCreateIdea.setFragment(null);
        if (null != taskCreateUser)
            taskCreateUser.setCallback(null);
        if (null != taskLoginUser)
            taskLoginUser.setCallbackFormulaire(null);
    }
}
