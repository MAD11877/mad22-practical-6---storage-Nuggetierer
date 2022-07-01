package sg.edu.np.mad.ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler db = new dbHandler(this, null, null, 1);

        Intent recieve = getIntent();
        String OTP = recieve.getStringExtra("OTP");
        user userInfo = recieve.getParcelableExtra("user");

        TextView text = findViewById(R.id.HelloWorld);
        text.setText("MAD"+ OTP);

        //create new user object
        user myUser = userInfo;

        //button press -> check follow -> change button
        Button flw =findViewById(R.id.followButton);

        flw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text;
                if (myUser.isFollowed()){
                    myUser.setFollowed(false);
                    flw.setText("Follow");
                    text = "Unfollowed";
                }
                else{
                    myUser.setFollowed(true);
                    flw.setText("Unfollow");
                    text = "Followed";
                }
                Toast.makeText(MainActivity.this,text, Toast.LENGTH_SHORT).show();

                db.updateUser(myUser);
            }
        });
    }
}