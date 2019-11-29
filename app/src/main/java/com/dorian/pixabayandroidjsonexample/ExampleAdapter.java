package com.dorian.pixabayandroidjsonexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>{
    private Context mContext;
    private ArrayList<ExampleItem> mExampleItemArrayList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }


    public ExampleAdapter(Context context, ArrayList<ExampleItem> exampleItems) {
        mContext = context;
        mExampleItemArrayList = exampleItems;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item,parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        ExampleItem currentItem = mExampleItemArrayList.get(position);

        String imageurl = currentItem.getmImageUrl();
        String creatorName = currentItem.getmCreator();
        int likeCount = currentItem.getmLikesCount();

        holder.mTextViewCreator.setText(creatorName);
        holder.mTextViewLikes.setText("좋아요: " + likeCount);

        Picasso.get().load(imageurl).fit().centerInside().into(holder.mimageView);

    }

    @Override
    public int getItemCount() {
        return mExampleItemArrayList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder {

        public ImageView mimageView;
        public TextView mTextViewCreator;
        public TextView mTextViewLikes;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mimageView = itemView.findViewById(R.id.image_view);
            mTextViewCreator = itemView.findViewById(R.id.text_view_creator);
            mTextViewLikes = itemView.findViewById(R.id.text_view_likes);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }

                }
            });
        }
    }
}