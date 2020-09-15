package com.scorpion.nextcode.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.scorpion.nextcode.Common.Validation;
import com.scorpion.nextcode.Model.Response.DatosFactura;
import com.scorpion.nextcode.Model.Response.DatosPlanes;
import com.scorpion.nextcode.R;
import com.scorpion.nextcode.View.Fragments.DetalleFactura;
import com.scorpion.nextcode.View.Fragments.DetallePlan;

import java.util.List;

public class VistaFacturas extends RecyclerView.Adapter<VistaFacturas.Holder>   {

    List<DatosFactura> lst_normal;
    List<DatosFactura> list_full;
    FragmentManager fragmentManager;
    Context context;
    String id_del_fragment;

    public VistaFacturas(List<DatosFactura> lst_normal, FragmentManager fragmentManager, Context context) {
        this.lst_normal = lst_normal;
        this.fragmentManager = fragmentManager;
        this.context = context;
    }




    @NonNull
    @Override
    public VistaFacturas.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_factura,
                parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VistaFacturas.Holder holder, final int position) {

       // holder.RellenoStatus.setBackgroundColor(R.drawable.border_estatus_purpura);

        holder.NumeroFactura.setText("Factura #"+lst_normal.get(position).getId());



        holder.FechaFactura.setText(lst_normal.get(position).getFechaEmision());

        holder.FacturaTotal.setText("$"+ Validation.formatearDecimales(Double.parseDouble(""+lst_normal.get(position).getTotal()),2));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetalleFactura detalle = new DetalleFactura();
                detalle.factura = lst_normal.get(position);
                FragmentTransaction fragmentTransaction;
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Contenedor_Fragments, detalle).addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return lst_normal.size();
    }



    public class Holder extends RecyclerView.ViewHolder {

        TextView NumeroFactura,Status,  FechaFactura,FacturaTotal;

        public Holder(@NonNull View itemView) {
            super(itemView);
            NumeroFactura=itemView.findViewById(R.id.NumeroFactura);
            Status=itemView.findViewById(R.id.Status);
            FechaFactura=itemView.findViewById(R.id.FechaFactura);
            FacturaTotal=itemView.findViewById(R.id.FacturaTotal);



        }
    }



    public void updateList(List<DatosFactura> newlist) {
        lst_normal.clear();
        lst_normal.addAll(newlist);
        this.notifyDataSetChanged();
    }

}
