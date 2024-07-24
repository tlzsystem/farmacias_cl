package cl.samueltoloza.farmaciascl.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cl.samueltoloza.farmaciascl.R;

public class PharmacyListAdapter extends RecyclerView.Adapter<PharmacyListAdapter.ViewHolder> {


    private List<PharmacyUiState> pharmacyUiStateList;

    public PharmacyListAdapter(List<PharmacyUiState> pharmacyUiStateList) {
        this.pharmacyUiStateList = pharmacyUiStateList;
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

        holder.getPharmacyItem_AddressData().setText(pharmacy.getFullAddress());
        holder.getPharmacyItem_ScheduleData().setText(pharmacy.getFullSchedule());
        holder.getPharmacyItem_NameLabel().setText(pharmacy.getName());


    }

    @Override
    public int getItemCount() {
        return pharmacyUiStateList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView pharmacyItem_NameLabel;
        private TextView pharmacyItem_AddressData;
        private TextView pharmacyItem_ScheduleData;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pharmacyItem_NameLabel = itemView.findViewById(R.id.pharmacyItem_NameLabel);
            pharmacyItem_AddressData = itemView.findViewById(R.id.pharmacyItem_AddressData);
            pharmacyItem_ScheduleData = itemView.findViewById(R.id.pharmacyItem_ScheduleData);
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
    }

}
