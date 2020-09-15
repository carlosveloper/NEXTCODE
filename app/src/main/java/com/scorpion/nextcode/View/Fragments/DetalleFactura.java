package com.scorpion.nextcode.View.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.scorpion.nextcode.Common.Validation;
import com.scorpion.nextcode.Model.Response.DatosFactura;
import com.scorpion.nextcode.R;
import com.scorpion.nextcode.ViewModel.ViewModelDetallePlanes;

import de.hdodenhof.circleimageview.CircleImageView;


public class DetalleFactura extends Fragment {

    public DatosFactura factura = new DatosFactura();
    View vista;
    CircleImageView imagenFactura;
    TextView numeroFactura, valorFactura, subtotalFactura, ivaFactura, tituloFactura, fechaFactura;

    public DetalleFactura() {
        // Required empty public constructor
    }

    String imagen = "https://static.vecteezy.com/system/resources/previews/000/350/512/non_2x/invoice-vector-icon.jpg";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_detalle_factura, container, false);
        UI();
        LlenarDatos();
        return vista;
    }


    private void UI() {
        imagenFactura = vista.findViewById(R.id.imagenFactura);

        tituloFactura = vista.findViewById(R.id.TvTituloFactura);
        numeroFactura = vista.findViewById(R.id.TVNumeroFactura);
        fechaFactura = vista.findViewById(R.id.TVFechaFactura);
        subtotalFactura = vista.findViewById(R.id.TVSubTotalFactura);
        ivaFactura = vista.findViewById(R.id.TVIvaFactura);
        valorFactura = vista.findViewById(R.id.TVTotalFactura);
    }

    private void LlenarDatos(){

        tituloFactura.setText("Detalle Factura #"+factura.getId());
        numeroFactura.setText(factura.getId().toString());
        fechaFactura.setText(factura.getFechaEmision());
        subtotalFactura.setText(""+Validation.formatearDecimales(Double.parseDouble(""+factura.getSubtotal()),2));
        ivaFactura.setText(""+Validation.formatearDecimales(Double.parseDouble(""+factura.getIva()),2));
        valorFactura.setText(""+Validation.formatearDecimales(Double.parseDouble(""+factura.getTotal()),2));
      Glide
                .with(getActivity())
                .load(imagen)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imagenFactura);

    }
}