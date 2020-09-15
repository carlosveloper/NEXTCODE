package com.scorpion.nextcode.View.Fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.scorpion.nextcode.Adapter.VistasPlanes;
import com.scorpion.nextcode.Common.Global;
import com.scorpion.nextcode.MainActivity;
import com.scorpion.nextcode.Model.Response.DatosPlanUsuario;
import com.scorpion.nextcode.Model.Response.DatosPlanes;
import com.scorpion.nextcode.R;
import com.scorpion.nextcode.ViewModel.ViewModelLogin;
import com.scorpion.nextcode.ViewModel.ViewModelMisPlanes;
import com.scorpion.nextcode.ViewModel.ViewModelPlanes;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;


public class MisPlanes extends Fragment {

    SweetAlertDialog pDialog;

    RecyclerView recyclerView;
    View vista;
    VistasPlanes adapter;
    LinearLayout contenedorFactura;
    private ViewModelMisPlanes viewModel;
    List<DatosPlanes> ls_listado= new ArrayList<>();
    CircleImageView Factura;
    String imagenFactura="https://www.netclipart.com/pp/m/82-825302_factura-en-icono-clipart-png-download-invoice-symbol.png";

    public MisPlanes() {
        // Required empty public constructor
    }


    @Override
    public void onResume() {
        super.onResume();
        viewModel.peticionMisPlanes();

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        vista=inflater.inflate(R.layout.fragment_mis_planes, container, false);
        Log.e("fragment","mis planes");
        UI();
        iniciar_recycler();
        observer();
        return vista;
    }



    private void UI(){
        contenedorFactura=vista.findViewById(R.id.contenedorFactura);
        Factura=vista.findViewById(R.id.agregarFactura);
        recyclerView= vista.findViewById(R.id.Recycler_misplanes);
        viewModel = ViewModelProviders.of(this).get(ViewModelMisPlanes.class);
        if(Global.Misplanes.size()<=0)
            contenedorFactura.setVisibility(View.GONE);
        Glide
                .with(getActivity())
                .load(imagenFactura)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(Factura);

        Factura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.AgregarFactura();
            }
        });
        animacion_cargando();

    }

    private void  iniciar_recycler(){
        adapter=new VistasPlanes(ls_listado,getFragmentManager(),getActivity(),2);
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

        final Observer<Boolean> observerLoadingFactura = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean result) {
                if(result){
                    pDialog.show();
                }else{
                    pDialog.dismiss();
                }

            }
        };


        final Observer<List<DatosPlanUsuario>> observerPlanesConsulta = new Observer<List<DatosPlanUsuario>>() {
            @Override
            public void onChanged(List<DatosPlanUsuario> result) {

                List<DatosPlanes> nuevo=new ArrayList<>();

                for (DatosPlanUsuario datosPlan:result){


                    for (DatosPlanes planes: Global.planes){

                        if(datosPlan.getPlanId()==planes.getId()){
                            nuevo.add(planes);

                        }
                    }
                }
                Global.Misplanes.clear();
                Global.Misplanes=nuevo;
                adapter.updateList(nuevo);
                if(Global.Misplanes.size()>0)
                    contenedorFactura.setVisibility(View.VISIBLE);
               /* ls_listado.clear();
                ls_listado.addAll(result);
                adapter.notifyDataSetChanged();*/

            }
        };

        final Observer<Boolean> observerFactura = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean result) {
                if(result){
                    ((MainActivity) getActivity()).clearFragmentBackStack();
                    ((MainActivity) getActivity()).cambiar_tab(2);
                }else{

                }

            }
        };

        viewModel.getIsViewLoadingFactura().observe(this, observerLoadingFactura);
        viewModel.getIntentFactura().observe(this, observerFactura);

        viewModel.getIsViewLoading().observe(this, observerLoading);

        viewModel.getplanesConsulta().observe(this, observerPlanesConsulta);

    }

    private void animacion_cargando(){
        pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(getActivity(), R.color.colorAzulDark))));
        pDialog.setTitleText("Agregando Factura");
        pDialog.setCancelable(false);


    }
}