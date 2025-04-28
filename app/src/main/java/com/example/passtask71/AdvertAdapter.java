package com.example.passtask71;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passtask71.model.Advert;

import java.util.List;

// extend to use Recycler View that will take a list of Advert objects
// We are doing this to display the data in recycler view
public class AdvertAdapter extends RecyclerView.Adapter<AdvertAdapter.AdvertViewHolder> {
    private final List<Advert> advertList;
    private final Context context;
    // Initialise advert list
    // Utilise context so when we press the item, it will start and intent
    public AdvertAdapter(Context context, List<Advert> advertList) {
        this.context = context;
        this.advertList = advertList;
    }

    // Create a class to hold the references to the TextView widgets within the item_advert
    // utilise findViewById() to link each Text View to the corresponding views within the layout
    public static class AdvertViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemDescription, itemDate, itemLocation, itemPhone;

        public AdvertViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            itemDescription = itemView.findViewById(R.id.item_description);
            itemDate = itemView.findViewById(R.id.item_date);
            itemLocation = itemView.findViewById(R.id.item_location);
            itemPhone = itemView.findViewById(R.id.item_phone);
        }
    }

    // New layout for each item in the list
    // inflating means to load the resource from item_advert and have it represent one item in the list
    @NonNull
    @Override
    public AdvertAdapter.AdvertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_advert, parent, false);
        return new AdvertViewHolder(itemView);
    }

    // Bind the data from each advert object to the views found within AdvertViewHolder
    @Override
    public void onBindViewHolder(@NonNull AdvertAdapter.AdvertViewHolder holder, int position) {
        Advert advert = advertList.get(position); // get advert object for the current position
        // Set the values of the item views
        holder.itemName.setText(advert.getItem_name());
        holder.itemDescription.setText(advert.getItem_description());
        holder.itemDate.setText("Date: " + advert.getItem_date());
        holder.itemLocation.setText("Location: " + advert.getItem_location());
        holder.itemPhone.setText("Phone: " + advert.getItem_phone());

        // whenwe click an item we want to move to remove the item
        // get name, date and location to put into the file
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, RemoveTheItem.class);
            intent.putExtra("name", advert.getItem_name());
            intent.putExtra("date", advert.getItem_date());
            intent.putExtra("location", advert.getItem_location());
            context.startActivity(intent);
        });
    }

    // return number of items in the list
    // this tells the recycler view h ow many items to display
    @Override
    public int getItemCount() {
        return advertList.size();
    }



}
