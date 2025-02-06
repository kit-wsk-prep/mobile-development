package com.example.moduleb1.Tickets;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moduleb1.Models.Ticket;
import com.honley.wsc_2023.R;

import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {

    private List<Ticket> tickets;

    public TicketAdapter(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public TicketViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ticket_item, parent, false); // Используем layout для одного тикета
        return new TicketViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TicketViewHolder holder, int position) {
        Ticket ticket = tickets.get(position);
        holder.ticketName.setText(ticket.getName());
        holder.eventTitle.setText(ticket.getEventTitle());

        String imageUriString = ticket.getImageUri();
        if (imageUriString != null) {
            Uri imageUri = Uri.parse(imageUriString);

            Glide.with(holder.itemView.getContext())
                    .load(imageUri)
                    .into(holder.ticketImage);
        }
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    public static class TicketViewHolder extends RecyclerView.ViewHolder {

        public TextView ticketName;
        public TextView eventTitle;  // Added the eventTitle TextView
        public ImageView ticketImage;

        public TicketViewHolder(View view) {
            super(view);
            ticketName = view.findViewById(R.id.ticket_name);
            eventTitle = view.findViewById(R.id.event_title);  // Initialize eventTitle
            ticketImage = view.findViewById(R.id.ticket_image);
        }
    }
}
