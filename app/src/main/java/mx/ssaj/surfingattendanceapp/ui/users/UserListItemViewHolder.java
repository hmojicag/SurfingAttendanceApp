package mx.ssaj.surfingattendanceapp.ui.users;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.commons.lang3.StringUtils;

import mx.ssaj.surfingattendanceapp.R;
import mx.ssaj.surfingattendanceapp.data.model.Users;

public class UserListItemViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageViewUserPicture;
    private TextView textViewUserName;
    private TextView textViewBioData;

    public UserListItemViewHolder(@NonNull View itemView) {
        super(itemView);
        imageViewUserPicture = itemView.findViewById(R.id.imageview_user_picture);
        textViewUserName = itemView.findViewById(R.id.textView_user_name);
        textViewBioData = itemView.findViewById(R.id.textView_biodata);
    }

    public void bind(Users user) {
        String userTruncName = StringUtils.truncate(user.name, 25);
        String fullNameDesc = user.user + " " + userTruncName;
        textViewUserName.setText(fullNameDesc);
        textViewBioData.setText("1 FingerPrint, 1 BioPhoto");
        imageViewUserPicture.setImageResource(R.drawable.account_circle);
    }

    public static UserListItemViewHolder create(ViewGroup parent) {
        return new UserListItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_users_list_user_item, parent, false));
    }

}
