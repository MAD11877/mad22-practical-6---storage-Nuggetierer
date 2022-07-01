package sg.edu.np.mad.ex2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        dbHandler db = new dbHandler(this, null, null,1);
//        ImageView img = findViewById(R.id.centerImage);
//
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                alertBox();
//            }
//        });

        ArrayList<user> userList = new ArrayList<user>();

        userList = db.getUsers();

//        for(int i = 0; i < 20; i++ ){
//            user newuser = new user();
//
//            String temp_name = "Name" + randomOTP();
//            String temp_desc = "Description " + randomOTP();
//            boolean temp_bool;
//
//            Random rand = new Random();
//            int upper = 2;
//            int decide = rand.nextInt(upper);
//
//            if (decide == 0){
//                temp_bool = true;
//            }
//            else{
//                temp_bool = false;
//            }
//
//            newuser.setFollowed(temp_bool);
//            newuser.setUserName(temp_name);
//            newuser.setDescription(temp_desc);
//
//            userList.add(newuser);
//        }

        RecyclerView recyclerView = findViewById(R.id.cyclerView);
        cyclerViewAdapter mAdapter = new cyclerViewAdapter(userList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    private int randomOTP(){
        Random rand = new Random();
        int value = rand.nextInt(999999);
        return value;
    }

    private void alertBox(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setMessage("MADness").setCancelable(false);
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        builder.setNegativeButton("View", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String number = Integer.toString(randomOTP());
                Intent myNewIntent = new Intent(ListActivity.this, MainActivity.class);
                myNewIntent.putExtra("OTP", number);
                startActivity(myNewIntent);
            }
        });

        AlertDialog alert = builder.create();
        alert.setTitle("Profile");
        alert.show();
    }
}