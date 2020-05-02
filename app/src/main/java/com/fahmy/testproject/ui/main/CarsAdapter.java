package com.fahmy.testproject.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.fahmy.testproject.R;
import com.fahmy.testproject.data.network.model.CarsResponse;
import java.util.List;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.ViewHolder> {

    private List<CarsResponse.Data> carsList;
    private Context context;

    CarsAdapter(List<CarsResponse.Data> carsList, Context context) {
        // generate constructors to initialise the List and Context objects
        this.carsList = carsList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // this method will be called whenever our ViewHolder is created
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        // this method will bind the data to the ViewHolder from whence it'll be shown to other Views

        final CarsResponse.Data carItem = carsList.get(position);
        holder.carBrand.setText(carItem.getBrand());
        holder.consYear.setText(carItem.getConstructionYear());
        String isUsed = "IsUsed: ";
        if (carItem.getIsUsed()) {
            isUsed += "Used";
        } else {
            isUsed += "No";
        }
        holder.isUsed.setText(isUsed);
        Glide.with(context).load(carItem.getImageUrl()).centerCrop().into(holder.carImage);
    }

    @Override
    public int getItemCount() {
        return carsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        // define the View objects

        TextView carBrand;
        ImageView carImage;
        TextView consYear;
        TextView isUsed;

        ViewHolder(View itemView) {
            super(itemView);

            // initialize the View objects
            carBrand = (TextView) itemView.findViewById(R.id.tv_car_brand);
            carImage = (ImageView) itemView.findViewById(R.id.iv_car_image);
            consYear = (TextView) itemView.findViewById(R.id.tv_cons_year);
            isUsed = (TextView) itemView.findViewById(R.id.tv_is_used);
        }

    }
}
