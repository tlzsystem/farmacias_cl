package cl.samueltoloza.farmaciascl.ui.EmergencyContacts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import cl.samueltoloza.farmaciascl.R;
import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class EmergencyContactsFragment extends Fragment {

    private EmergencyContactViewModel viewModel;
    private EmergencyContactListAdapter listAdapter;
    private RecyclerView recyclerView;
    protected RecyclerView.LayoutManager layoutManager;


    public EmergencyContactsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_emergency_contacts, container, false);

        viewModel = new ViewModelProvider(getActivity()).get(EmergencyContactViewModel.class);
        recyclerView = rootView.findViewById(R.id.recycler_view_emergency_contacts_id);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayout.VERTICAL));
        listAdapter = new EmergencyContactListAdapter(getContext());
        viewModel.getEmergencyContactsData().observe(getActivity(), result ->{

            if(result.isSuccess()){
                listAdapter.setEmergencyContactUiStateList(result.getData());
                recyclerView.setAdapter(listAdapter);
            }else {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
            }


        });
        viewModel.loadEmergencyContacts();


        return rootView;
    }
}