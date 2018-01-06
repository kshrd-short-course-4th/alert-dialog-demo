package com.example.rathana.alertdialogdemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rathana.alertdialogdemo.entity.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //construct alert Dialog
    private void createAlertMessageDialog() {
        //step 1st
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        //step 2nd
        //build alert dialog view
        //header
        builder.setIcon(R.drawable.instagram);
        builder.setTitle("Message Dialog");

        //context part
        builder.setMessage("this is alert Diglog message.Do you want to continue?");
        //footer
        builder.setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showMessage("you accepted");
                dialog.dismiss();
            }
        }).setNegativeButton("No!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showMessage("you cancel the event");
                dialog.dismiss();
            }
        }).setNeutralButton("remind me later", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showMessage("remind later");
                dialog.dismiss();
            }
        });

        //create alter Dialog object
        AlertDialog alertDialog = builder.create();
                alertDialog.show();

    }
    private void createSignalChoiceListDialog() {
        //step 1st
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        //step 2nd
        //build alert dialog view
        //header
        builder.setIcon(R.drawable.instagram);
        builder.setTitle("signal choice list Dialog");

        //context part
        //builder.setMessage("this is alert Diglog message.Do you want to continue?");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showMessage("click on " +colors[which] +" item");
            }
        });
        //create alter Dialog object
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private String[] colors={"Red","Yellow","Blue","Black"};


    private void createRadioButtonListDialog() {
        //step 1st
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        //step 2nd
        //build alert dialog view
        //header
        builder.setIcon(R.drawable.instagram);
        builder.setTitle("signal choice list Dialog");
        //context part
        //builder.setMessage("this is alert Diglog message.Do you want to continue?");
       builder.setSingleChoiceItems(companies, 1, new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
                showMessage("I choose "+companies[which]+" company");
           }
       });
       builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
                showMessage("you accept");
           }
       }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               dialog.dismiss();
           }
       });
        //create alter Dialog object
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    String[] companies={"Samsung","Apple","Microsoft","Oracle","JetBrain"};

    private void createCheckBoxListDialog() {
        //step 1st
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        //step 2nd
        //build alert dialog view
        //header
        builder.setIcon(R.drawable.instagram);
        builder.setTitle("signal choice list Dialog");
        //context part
        //builder.setMessage("this is alert Diglog message.Do you want to continue?");
        builder.setMultiChoiceItems(companies,itemChecked, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    if(isChecked) {
                        itemChecked[which]=true;
                        for(int i=0;i<itemChecked.length;i++){
                            if(itemChecked[i]) companyChoosed.add(companies[which]);
                        }
                    }
                    else {
                        companyChoosed.remove(companies[which]);
                        itemChecked[which]=false;
                    }
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String com="";
                for (String s:companyChoosed){
                    com=com+ " "+s;
                }
                showMessage(com);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        //create alter Dialog object
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    List<String> companyChoosed=new ArrayList<>();
    static  boolean[] itemChecked={false,true,false,false,false};
    //button event handler
    public void onShowDialogMessage(View view) {
        createAlertMessageDialog();
    }

    private  void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void onShowSignalChoiceListDialog(View view) {
        createSignalChoiceListDialog();
    }

    public void onShowRadioButtonListDialog(View view) {
        createRadioButtonListDialog();
    }



    public void onShowCheckBoxListDialog(View view) {
        createCheckBoxListDialog();
    }


    public void onShowCustomDialog(View view) {
        createCustomDialog();
    }

    private void createCustomDialog() {

        AlertDialog.Builder builder =new AlertDialog.Builder(this);

        builder.setTitle("Sign In");

        ///custom layout for alertDialog
        View view= LayoutInflater.from(this).inflate(
                R.layout.custom_dialog_layout,
                null
        );
        final EditText mUsername,mPassword;
        mUsername=view.findViewById(R.id.username);
        mPassword=view.findViewById(R.id.password);

        builder.setView(view);

        builder.setPositiveButton("sign in", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO LOGIN
                User user=new User(mUsername.getText().toString(),
                        mPassword.getText().toString());
                loginSuccess(user);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Cancel
            }
        });
        builder.create().show();
    }

    private void loginSuccess(User user) {
        if(userMemory.getUsername().equals(user.getUsername()) &&
                userMemory.getPassword().equals(user.getPassword()))
        {
            startActivity(new Intent(this,
                    HomeActivity.class));
        }
    }

    private static User userMemory = new User("admin","admin");
}
