package com.material.tblagodarova.design.ui.fragments;



import com.material.tblagodarova.design.R;
import com.material.tblagodarova.design.ui.adapters.HomeAdapter;
import com.material.tblagodarova.design.ui.adapters.NavigationDrawerAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tblagodarova on 7/14/15.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        // Inflate the layout for this fragment
        String[] values = getActivity().getResources().getStringArray(R.array.home_adapter_values);
        RecyclerView recyclerView = (RecyclerView)rootView.findViewById(R.id.listview_home);
        HomeAdapter adapter = new HomeAdapter(getActivity(), values);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
