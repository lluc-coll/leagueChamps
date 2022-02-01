package com.example.leaguechamps.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.leaguechamps.R;
import com.example.leaguechamps.ui.models.Skin;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class SkinsAdapter extends PagerAdapter {

    // Context object
    Context context;

    // Array of images
    List<Skin> skins;

    // Layout Inflater
    LayoutInflater mLayoutInflater;

    Boolean landscape;


    // Viewpager Constructor
    public SkinsAdapter(Context context, List<Skin> skins, Boolean landscape) {
        this.context = context;
        this.skins = skins;
        this.landscape = landscape;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // return the number of images
        return skins.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((ConstraintLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.single_skin, container, false);

        ImageView skinImage = (ImageView) itemView.findViewById(R.id.skin);
        TextView skinText = (TextView) itemView.findViewById(R.id.skin_name);

        if(landscape){
            Picasso.get().load(skins.get(position).getLandphoto()).into(skinImage);
        }else {
            Picasso.get().load(skins.get(position).getPhoto()).into(skinImage);
        }
        skinText.setText(skins.get(position).getName());

        Objects.requireNonNull(container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((ConstraintLayout) object);
    }
}
