package sg.edu.np.mad.ex2;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class cyclerViewHolder extends RecyclerView.ViewHolder {

    public View image;
    TextView nameText;
    TextView descText;
    ImageView img;
    Button followButton;
    public cyclerViewHolder(@NonNull View itemView){
        super(itemView);

        nameText = itemView.findViewById(R.id.nameText);
        descText = itemView.findViewById(R.id.textDesc);
        img = itemView.findViewById(R.id.imageView2);
        followButton = itemView.findViewById(R.id.recyclerFollowButton);
    }
}
