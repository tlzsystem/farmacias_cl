package cl.samueltoloza.farmaciascl.ui.EmergencyContacts;

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

public class EmergencyContactListAdapter extends RecyclerView.Adapter<EmergencyContactListAdapter.ViewHolder> {

    private Context context;
    private List<EmergencyContactUiState> emergencyContactUiStateList;

    public EmergencyContactListAdapter(Context context) {
        this.context = context;
    }

    public void setEmergencyContactUiStateList(List<EmergencyContactUiState> emergencyContactUiStateList) {
        this.emergencyContactUiStateList = emergencyContactUiStateList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emergency_contact_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        EmergencyContactUiState uiState = emergencyContactUiStateList.get(position);
        holder.getEmergencyContactItemName().setText(uiState.getName());
        holder.getEmergencyContactItemNumber().setText(uiState.getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        return emergencyContactUiStateList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView emergencyContactItemName;
        private TextView emergencyContactItemNumber;
        private Button emergencyContactItemCallButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            emergencyContactItemName = itemView.findViewById(R.id.emercency_contact_item_name);
            emergencyContactItemNumber = itemView.findViewById(R.id.emercency_contact_item_number_data);
            emergencyContactItemCallButton = itemView.findViewById(R.id.emercency_contact_item_call_button);
            emergencyContactItemCallButton.setOnClickListener(view -> {
                Intent i = new Intent(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:"+ emergencyContactUiStateList.get(getAdapterPosition()).getPhoneNumber()));
                context.startActivity(i);
            });
        }

        public TextView getEmergencyContactItemName() {
            return emergencyContactItemName;
        }

        public TextView getEmergencyContactItemNumber() {
            return emergencyContactItemNumber;
        }

        public Button getEmergencyContactItemCallButton() {
            return emergencyContactItemCallButton;
        }
    }


}
