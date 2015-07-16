package com.material.tblagodarova.design.ui.adapters;

import com.material.tblagodarova.design.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by tblagodarova on 7/14/15.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder2> {
    String[] data;
    private LayoutInflater inflater;
    private Context context;

    public HomeAdapter(Context context, String[] data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cardview_row, parent, false);
        MyViewHolder2 holder = new MyViewHolder2(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder2 holder, int position) {
        String current = data[position];
        holder.title.setText(current);

    }

    @Override
    public int getItemCount() {
        return data.length;
    }


    class MyViewHolder2 extends RecyclerView.ViewHolder {
        private final ImageView image;
        TextView title;

        public MyViewHolder2(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.person_name);
            image = (ImageView) itemView.findViewById(R.id.person_photo);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                //Default
                image.setBackgroundResource(R.drawable.images_google);
            } else {
                Resources res = itemView.getContext().getResources();
                Bitmap src = BitmapFactory.decodeResource(res, R.drawable.images_google);
                RoundedBitmapDrawable dr =
                        RoundedBitmapDrawableFactory.create(res, src);
                dr.setCornerRadius(4);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                    image.setBackground(dr);
                else
                    image.setBackgroundDrawable(dr);
            }

            CardView cv = (CardView) itemView.findViewById(R.id.card_view);
            cv.setPreventCornerOverlap(false);
        }
    }
}
