package sg.edu.np.mad.ex2;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class cyclerViewAdapter extends RecyclerView.Adapter<cyclerViewHolder>{

    ArrayList<user> data;
    public cyclerViewAdapter(ArrayList<user> input) {data = input;}

    @Override
    @NonNull
    public cyclerViewHolder onCreateViewHolder(ViewGroup parent, int view){

        View item;
        if(getItemViewType(view) == 1){
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.cyclerviewholder, parent, false);
        }
        else{
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.cyclerviewholder2, parent, false);
        }
        return new cyclerViewHolder(item);
    }

    public void onBindViewHolder(cyclerViewHolder holder, @SuppressLint("RecyclerView") int position){
        String name = data.get(position).getUserName();
        String desc = data.get(position).getDescription();
        boolean follow = data.get(position).isFollowed();

        holder.descText.setText(desc);
        holder.nameText.setText(name);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.img.getContext());
                builder.setMessage(name);
                builder.setTitle("Profile");
                builder.setCancelable(true);
                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Random ran = new Random();
                        //String value = String.valueOf(ran.nextInt(999999999));
                        long val = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
                        String value = String.valueOf(val);
                        Log.v(TAG, "Random interger: " + value);
                        Intent myIntent = new Intent(holder.img.getContext(), MainActivity.class);
                        myIntent.putExtra("MyRandomInt", value);
                        myIntent.putExtra("user", data.get(position));
                        holder.img.getContext().startActivity(myIntent);
                    }
                });
                builder.setNegativeButton("close",null);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    public int getItemViewType(int position){
        user obj = data.get(position);
        if(obj.getUserName().charAt(obj.getUserName().length() - 1)=='7'){
            return  0;
        }
        else {
            return 1;
        }
    }

    public int getItemCount(){return data.size();}

}
