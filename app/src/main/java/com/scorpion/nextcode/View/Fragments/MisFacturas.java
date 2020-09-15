package com.scorpion.nextcode.View.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scorpion.nextcode.Adapter.VistaFacturas;
import com.scorpion.nextcode.Adapter.VistasPlanes;
import com.scorpion.nextcode.Common.Global;
import com.scorpion.nextcode.Model.Response.DatosFactura;
import com.scorpion.nextcode.Model.Response.DatosPlanUsuario;
import com.scorpion.nextcode.Model.Response.DatosPlanes;
import com.scorpion.nextcode.R;
import com.scorpion.nextcode.ViewModel.ViewModelMisFacturas;
import com.scorpion.nextcode.ViewModel.ViewModelMisPlanes;

import java.util.ArrayList;
import java.util.List;


public class MisFacturas extends Fragment {

    RecyclerView recyclerView;
    View vista;
    VistaFacturas adapter;
    private ViewModelMisFacturas viewModel;
    List<DatosFactura> ls_listado = new ArrayList<>();

    public MisFacturas() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        super.onResume();
        viewModel.peticionMisFactura();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_mis_facturas, container, false);

        UI();
        iniciar_recycler();
        observer();
        return vista;
    }

    private void UI() {
        recyclerView = vista.findViewById(R.id.Recycler_misfacturas);
        viewModel = ViewModelProviders.of(this).get(ViewModelMisFacturas.class);

    }

    private void iniciar_recycler() {
        adapter = new VistaFacturas(ls_listado, getFragmentManager(), getActivity());
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


        final Observer<List<DatosFactura>> observerPlanesConsulta = new Observer<List<DatosFactura>>() {
            @Override
            public void onChanged(List<DatosFactura> result) {

                adapter.updateList(result);
               /* ls_listado.clear();
                ls_listado.addAll(result);
                adapter.notifyDataSetChanged();*/

            }
        };
        viewModel.getIsViewLoading().observe(this, observerLoading);

        viewModel.getplanesConsulta().observe(this, observerPlanesConsulta);

    }
}