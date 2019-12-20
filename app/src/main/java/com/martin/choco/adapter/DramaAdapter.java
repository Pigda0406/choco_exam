package com.martin.choco.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.martin.choco.DetailActivity;
import com.martin.choco.R;
import com.martin.choco.model.Drama;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DramaAdapter extends RecyclerView.Adapter<DramaAdapter.ExampleViewHolder> implements Filterable {
    private List<Drama> dramaListList;
    private List<Drama> dramaListListFull;
    private Context context;

    class ExampleViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView time;
        RatingBar ratingBar;

        ExampleViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            name = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
            ratingBar=itemView.findViewById(R.id.ratingBar);
            ratingBar.setMax(5);
            ratingBar.setNumStars(5);
            ratingBar.setStepSize((float) 0.1);
            ratingBar.setIsIndicator(true);
        }
    }

    public DramaAdapter(Context context,List<Drama> exampleList) {
        this.context = context;
        this.dramaListList = exampleList;
        dramaListListFull = new ArrayList<>(exampleList);
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.drama_item,
                parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        final Drama currentItem = dramaListList.get(position);
        Glide.with(context).load(currentItem.getThumb()
        ).into(holder.imageView);
        holder.name.setText(currentItem.getName());

        holder.ratingBar.setRating(currentItem.getRating());
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
        String displayformat="yyyy/MM/dd,hh:mm";
        SimpleDateFormat destFormat=new SimpleDateFormat(displayformat);

        try {
            Date myDate = myFormat.parse(currentItem.getCreatedAt());
            holder.time.setText(destFormat.format(myDate));
            currentItem.setCreatedAt(destFormat.format(myDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("Drama", currentItem);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dramaListList.size();
    }

    @Override
    public Filter getFilter() {
        return dramaFilter;
    }

    private Filter dramaFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Drama> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(dramaListListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Drama item : dramaListListFull) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dramaListList.clear();
            dramaListList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}