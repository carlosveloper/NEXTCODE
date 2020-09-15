package com.scorpion.nextcode.View.Fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.scorpion.nextcode.Common.Global;
import com.scorpion.nextcode.MainActivity;
import com.scorpion.nextcode.Model.Response.DatosPlanUsuario;
import com.scorpion.nextcode.Model.Response.DatosPlanes;
import com.scorpion.nextcode.R;
import com.scorpion.nextcode.ViewModel.ViewModelDetallePlanes;
import com.scorpion.nextcode.ViewModel.ViewModelMisPlanes;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;


public class DetallePlan extends Fragment {

     public  DatosPlanes plan= new DatosPlanes();
    DatosPlanUsuario envioPlan=new DatosPlanUsuario();
    public String imagen="";
    public int  tipo=1;
    SweetAlertDialog pDialog;
    private ViewModelDetallePlanes viewModel;

    View vista;
    CircleImageView imagenPlan,agregarPlan;
    TextView nombrePlan, tipoPlan, valorPlan,subtotalPlan,ivaPlan,tituloPlan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista= inflater.inflate(R.layout.fragment_detalle_plan, container, false);
        UI();
        LlenarDatos();
        animacion_cargando();
        observer();
        return vista;
    }


    private  void UI(){
        agregarPlan=vista.findViewById(R.id.agregarPlan);


        tituloPlan=vista.findViewById(R.id.TvTituloPLan);
        imagenPlan=vista.findViewById(R.id.CIFotoPlan);
        nombrePlan=vista.findViewById(R.id.TVNombrePLan);
        tipoPlan=vista.findViewById(R.id.TVTipoPlan);
        subtotalPlan=vista.findViewById(R.id.TVSubTotalPlan);
        ivaPlan=vista.findViewById(R.id.TVIvaPlan);
        valorPlan=vista.findViewById(R.id.TVTotalPlan);
        viewModel = ViewModelProviders.of(this).get(ViewModelDetallePlanes.class);

        agregarPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.peticionMisPlanes(envioPlan);
            }
        });

        if(tipo==2){
            agregarPlan.setVisibility(View.GONE);
        }
    }

    private  void LlenarDatos(){
        tituloPlan.setText("Detalle del Plan "+plan.getNombre());
        nombrePlan.setText(plan.getNombre());
        tipoPlan.setText(plan.getTipo());
        ivaPlan.setText(plan.getIva());
        subtotalPlan.setText(plan.getSubtotal());
        valorPlan.setText(plan.getTotal());
        Glide
                .with(getActivity())
                .load(imagen)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imagenPlan);


        envioPlan.setPlanId(plan.getId());
        envioPlan.setStatus("A");
        envioPlan.setUsuarioId(Global.miPerfil.getId());

    }

    private void animacion_cargando(){
        pDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#"+Integer.toHexString(ContextCompat.getColor(getActivity(), R.color.colorAzulDark))));
        pDialog.setTitleText("Agregando Plan");
        pDialog.setCancelable(false);


    }
    private  void observer(){
        final Observer<Boolean> observerLoading = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean result) {
                if(result){
                    pDialog.show();
                }else{

                    pDialog.dismiss();

                }

            }
        };

        final Observer<Boolean> observerHome = new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean result) {
                if(result){
                    ((MainActivity) getActivity()).clearFragmentBackStack();
                    ((MainActivity) getActivity()).cambiar_tab(1);
                   // getFragmentManager().popBackStack("planes",0);
                }else{

                }

            }
        };

        viewModel.getintentHome().observe(this, observerHome);
        viewModel.getIsViewLoading().observe(this, observerLoading);

    }
}