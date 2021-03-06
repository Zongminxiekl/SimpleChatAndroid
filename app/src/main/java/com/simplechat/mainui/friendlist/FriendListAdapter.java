package com.simplechat.mainui.friendlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.simplechat.R;
import com.simplechat.mainui.friendlist.domain.Friend;
import com.simplechat.webservices.RequestImage;

import java.util.List;

public class FriendListAdapter extends ArrayAdapter<Friend> {
    private List<Friend> friendList;

    private Integer flag = 0;

    public FriendListAdapter(@NonNull Context context, int resource, @NonNull List<Friend> objects) {
        super(context, resource, objects);
        friendList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.friendlist_item, parent, false);
        //用户头像
        ImageView userImage = (ImageView) convertView.findViewById(R.id.user_image);
        //用户名字或者昵称，如果有昵称，应当显示昵称
        TextView username = (TextView) convertView.findViewById(R.id.nickname);
        //用户个性签名
        TextView userSign = (TextView) convertView.findViewById(R.id.user_sign);
        Friend friend = friendList.get(position);
        username.setText(friend.getNickname());
        userSign.setText(friend.getSignature());
        String imageName = friend.getHead();
        try {
            RequestImage requestImage = new RequestImage();
            requestImage.sendRequestImage(userImage, imageName);
        } catch (Exception e){
            e.printStackTrace();
        }
        //DateFormat df = new SimpleDateFormat("M月d日");
        return convertView;
    }
}
