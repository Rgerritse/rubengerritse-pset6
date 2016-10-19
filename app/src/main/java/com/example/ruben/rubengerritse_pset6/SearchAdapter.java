package com.example.ruben.rubengerritse_pset6;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by ruben on 18-10-16.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private JSONArray resultsArray;
    private View.OnClickListener listener;
    private Context context;

    public SearchAdapter(JSONArray array) {
        this.resultsArray = array;
        this.listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int position = ((ViewGroup) v.getParent()).indexOfChild(v);
                try {
                    JSONObject item = resultsArray.getJSONObject(position);
                    Intent intent =  new Intent(context, GameInfoActivity.class);
                    intent.putExtra("id", item.getInt("id"));
                    context.startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,
                parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchAdapter.ViewHolder holder, int position) {
        holder.itemView.setOnClickListener(listener);
        try {
            holder.itemTitle.setText(resultsArray.getJSONObject(position).getString("name"));
            String imageString = resultsArray.getJSONObject(position).getJSONObject("image")
                    .getString("icon_url");
            URL imageUrl = new URL(imageString.replaceAll("\\/", "/"));
            Bitmap bmp = new ImageDownload().execute(imageUrl).get();
            holder.itemImage.setImageBitmap(bmp);
        } catch (JSONException | InterruptedException | ExecutionException |
                MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return resultsArray.length();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemTitle;
        ImageView itemImage;

        public ViewHolder(View itemView){
            super(itemView);
            itemTitle = (TextView) itemView.findViewById(R.id.item_name_tv);
            itemImage = (ImageView) itemView.findViewById(R.id.item_image_iv);
        }
    }
}
