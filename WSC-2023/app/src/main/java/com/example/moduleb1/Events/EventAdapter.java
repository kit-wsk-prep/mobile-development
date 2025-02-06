package com.example.moduleb1.Events;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moduleb1.Models.Event;
import com.honley.wsc_2023.R;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private List<Event> events;

    public EventAdapter(List<Event> events) {
        this.events = events;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = events.get(position);
        holder.eventTitle.setText(event.getEventTitle());
        holder.eventText.setText(event.getEventText());

        if (!event.getEventPictures().isEmpty()) {
            String imagePath = event.getEventPictures().get(0);
            // Здесь нужно добавить загрузку изображения, например с помощью библиотеки Picasso или Glide
            // Glide.with(holder.itemView.getContext()).load(imagePath).into(holder.eventImage);
        }
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView eventTitle, eventText;
        ImageView eventImage;

        public EventViewHolder(View itemView) {
            super(itemView);
            eventTitle = itemView.findViewById(R.id.eventTitle);
            eventText = itemView.findViewById(R.id.eventText);
            eventImage = itemView.findViewById(R.id.eventImage);
        }
    }
}

