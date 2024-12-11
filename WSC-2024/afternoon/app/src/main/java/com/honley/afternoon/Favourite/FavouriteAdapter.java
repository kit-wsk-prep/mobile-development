package com.honley.afternoon.Favourite;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.honley.afternoon.R;

import java.util.ArrayList;
import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {

    private final List<FavouriteItem> favouriteList;
    private final Context context;

    public FavouriteAdapter(Context context, List<FavouriteItem> favouriteList) {
        this.context = context;
        this.favouriteList = new ArrayList<>(favouriteList);
    }

    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);
        return new FavouriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteViewHolder holder, int position) {
        FavouriteItem favouriteItem = favouriteList.get(position);
        bindData(holder.image, holder.description, favouriteItem.getName());

        holder.detailsButton.setOnClickListener(view ->
                openDialog(favouriteItem.getName()));
    }

    @Override
    public int getItemCount() {
        return favouriteList.size();
    }

    public static class FavouriteViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView description;
        ImageButton detailsButton;

        public FavouriteViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.placeImage);
            description = itemView.findViewById(R.id.placeDescription);
            detailsButton = itemView.findViewById(R.id.detailsButton);
        }
    }

    private void bindData(ImageView image, TextView description, String name) {
        switch (name) {
            case "toureiffel":
                image.setImageResource(R.drawable.scene_1);
                description.setText(R.string.toureiffel);
                break;

            case "socrecoeur":
                image.setImageResource(R.drawable.scene_5);
                description.setText(R.string.socrecoeur);
                break;

            case "arcdetriophe":
                image.setImageResource(R.drawable.scene_2);
                description.setText(R.string.arcdetriophe);
                break;

            case "palaisRoyal":
                image.setImageResource(R.drawable.scene_4);
                description.setText(R.string.palaisRoyal);
                break;

            default:
                image.setImageResource(R.drawable.icon_close);
                description.setText(R.string.content);
                break;
        }
    }

    private void openDialog(String name) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_place);

        ImageView placeImage = dialog.findViewById(R.id.placeImage);
        TextView placeDescription = dialog.findViewById(R.id.placeDescription);

        bindData(placeImage, placeDescription, name);

        dialog.show();
    }
}
