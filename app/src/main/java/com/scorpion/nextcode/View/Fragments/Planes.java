package com.scorpion.nextcode.View.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scorpion.nextcode.Adapter.VistasPlanes;
import com.scorpion.nextcode.Common.Global;
import com.scorpion.nextcode.Common.Validation;
import com.scorpion.nextcode.Model.Response.DatosPlanes;
import com.scorpion.nextcode.R;
import com.scorpion.nextcode.ViewModel.ViewModelLogin;
import com.scorpion.nextcode.ViewModel.ViewModelPlanes;

import java.util.ArrayList;
import java.util.List;


public class Planes extends Fragment {

    RecyclerView recyclerView;
    View vista;
    VistasPlanes adapter;
    private ViewModelPlanes viewModel;
    List<DatosPlanes> ls_listado;
    public Planes() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.peticionPlanes();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista= inflater.inflate(R.layout.fragment_planes, container, false);
        Log.e("fragment","planes");
        UI();
        iniciar_recycler();
        observer();
        return vista;
    }

    private void UI(){
        ls_listado= new ArrayList<>();
        recyclerView= vista.findViewById(R.id.Recycler_planes);
        viewModel = ViewModelProviders.of(this).get(ViewModelPlanes.class);

    }

    private void  iniciar_recycler(){
        adapter=new VistasPlanes(ls_listado,getFragmentManager(),getActivity(),1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private  void observer(){
        final Observer<Boolean> observerLoading = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean result) {
                if(result){

                }else{


                }

            }
        };


        final Observer<List<DatosPlanes>> observerPlanesConsulta = new Observer<List<DatosPlanes>>() {
            @Override
            public void onChanged(List<DatosPlanes> result) {
                Log.e("traje","planes");
                Log.e("datin", Validation.convertObjToString(ls_listado));
                adapter.updateList(result);
                Global.planes=result;

            }
        };


        viewModel.getIsViewLoading().observe(this, observerLoading);
        viewModel.getplanesConsulta().observe(this, observerPlanesConsulta);

    }
}