package cl.samueltoloza.farmaciascl.ui.PharmacyList;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cl.samueltoloza.farmaciascl.R;

public class PharmacyListAdapter extends RecyclerView.Adapter<PharmacyListAdapter.ViewHolder> {

    private Context context;
    private List<PharmacyUiState> pharmacyUiStateList;

    public PharmacyListAdapter(List<PharmacyUiState> pharmacyUiStateList) {
        this.pharmacyUiStateList = pharmacyUiStateList;
    }

    public PharmacyListAdapter(Context context) {
        this.context = context;
    }

    public void setPharmacyUiStateList(List<PharmacyUiState> pharmacyList){
        this.pharmacyUiStateList = pharmacyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pharmacy_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PharmacyUiState pharmacy = pharmacyUiStateList.get(position);

        holder.getPharmacyItem_AddressData().setText(pharmacy.getAddress());
        holder.getPharmacyItem_ScheduleData().setText(pharmacy.getFullSchedule());
        holder.getPharmacyItem_NameLabel().setText(pharmacy.getName());
        holder.getPharmacyItem_ComunaData().setText(pharmacy.getCity());


    }

    @Override
    public int getItemCount() {
        return pharmacyUiStateList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView pharmacyItem_NameLabel;
        private TextView pharmacyItem_AddressData;
        private TextView pharmacyItem_ScheduleData;
        private TextView pharmacyItem_ComunaData;
        private Button pharmacyItem_CallActionButton;
        private Button pharmacyItem_MapActionButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pharmacyItem_NameLabel = itemView.findViewById(R.id.pharmacyItem_NameLabel);
            pharmacyItem_AddressData = itemView.findViewById(R.id.pharmacyItem_AddressData);
            pharmacyItem_ScheduleData = itemView.findViewById(R.id.pharmacyItem_ScheduleData);
            pharmacyItem_ComunaData = itemView.findViewById(R.id.pharmacyItem_ComunaData);
            pharmacyItem_CallActionButton = itemView.findViewById(R.id.pharmacyItem_CallActionButton);
            pharmacyItem_MapActionButton = itemView.findViewById(R.id.pharmacyItem_MapActionButton);
            pharmacyItem_CallActionButton.setOnClickListener(view -> {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+ pharmacyUiStateList.get(getAdapterPosition()).getPhone()  ));
                context.startActivity(i);
            });

            pharmacyItem_MapActionButton.setOnClickListener(view -> {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+ pharmacyUiStateList.get(getAdapterPosition()).getLat()  +","+ pharmacyUiStateList.get(getAdapterPosition()).getLng()+",farmacia");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                context.startActivity(mapIntent);
            });



        }

        public TextView getPharmacyItem_NameLabel(){
            return pharmacyItem_NameLabel;
        }
        public TextView getPharmacyItem_AddressData(){
            return pharmacyItem_AddressData;
        }
        public TextView getPharmacyItem_ScheduleData(){
            return pharmacyItem_ScheduleData;
        }
        public TextView getPharmacyItem_ComunaData() { return pharmacyItem_ComunaData; }


    }

}
