package cl.samueltoloza.farmaciascl.ui.PharmacyList;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.progressindicator.LinearProgressIndicator;

import cl.samueltoloza.farmaciascl.R;
import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class PharmacyListFragment extends Fragment {


    private PharmacyListViewModel viewModel;
    private PharmacyListAdapter listAdapter;
    private RecyclerView recyclerView;
    protected RecyclerView.LayoutManager layoutManager;
    private LinearProgressIndicator linearProgressIndicator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView =  inflater.inflate(R.layout.fragment_pharmacy_list, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(PharmacyListViewModel.class);
        recyclerView = rootView.findViewById(R.id.pharmacyListRecyclerViewId);
        linearProgressIndicator = rootView.findViewById(R.id.pharmacyListProgressBarId);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getActivity(), LinearLayout.VERTICAL));
        listAdapter = new PharmacyListAdapter(getContext());
        viewModel.loadPharmacies();
        viewModel.getPharmaciesList().observe(getActivity(), result->{

            if(result.isSuccess()){
                listAdapter.setPharmacyUiStateList(result.getData());
                recyclerView.setAdapter(listAdapter);
            }else {
                Toast.makeText(getContext(), "Error al obtener información", Toast.LENGTH_SHORT).show();
            }

            linearProgressIndicator.hide();
        });


        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.top_appbar_menu, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {

                if(menuItem.getItemId() == R.id.app_bar_search){
                    SearchView searchView = (SearchView)menuItem.getActionView();
                    searchView.setQueryHint("Buscar por Comuna");

                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {

                            viewModel.findPharmaciesByCityName(newText);
                            return false;
                        }
                    });

                }


                return false;
            }
        }, getViewLifecycleOwner(), Lifecycle.State.RESUMED);



        return rootView;
    }


}