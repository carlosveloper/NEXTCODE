package com.scorpion.nextcode.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.scorpion.nextcode.Common.Validation;
import com.scorpion.nextcode.Model.Response.DatosPlanes;
import com.scorpion.nextcode.Model.Response.ResponsePlanes;
import com.scorpion.nextcode.R;
import com.scorpion.nextcode.View.Fragments.DetallePlan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class VistasPlanes extends RecyclerView.Adapter<VistasPlanes.MultiHolder> {


    List<DatosPlanes> lst_normal;
    List<DatosPlanes> lst_full;
    int tipo=1;
    FragmentManager fragmentManager;
    Context context;

    //Todo tipo 1= se presentara el boton agregar en detalle del plan
    //Todo tipo 2= no se presentara el boton agregar en detalle del plan


    public VistasPlanes(List<DatosPlanes> lst_normal, FragmentManager fragmentManager, Context context,int tipo) {
        this.tipo=tipo;
        this.context = context;
        this.lst_normal = lst_normal;
        this.fragmentManager = fragmentManager;
        lst_full = new ArrayList<>(lst_normal);

    }

    @NonNull
    @Override
    public MultiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_planes, parent, false);
        MultiHolder th = new MultiHolder(view);
        return th;
    }

    @Override
    public void onBindViewHolder(@NonNull MultiHolder holder, final int position) {
        holder.nombrePlan.setText(lst_normal.get(position).getNombre());
        holder.tipoPlan.setText(lst_normal.get(position).getTipo());
        holder.valorPlan.setText("$" + Validation.formatearDecimales(Double.parseDouble(""+lst_normal.get(position).getTotal()),2) );

        String foto = "https://image.freepik.com/vector-gratis/medalla-oro-ganador-cintas-rojas-aisladas_53562-5227.jpg";
        if (lst_normal.get(position).getId() == 1)
            foto = "data:image/jpeg;base64,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";
        if (lst_normal.get(position).getId() == 2)
            foto = "https://previews.123rf.com/images/mousemd/mousemd1502/mousemd150200010/36359103-medalla-de-plata-campe%C3%B3n-con-cinta-sobre-fondo-blanco.jpg";
        if (lst_normal.get(position).getId() == 3)
            foto = "https://previews.123rf.com/images/mousemd/mousemd1502/mousemd150200008/36359100-medalla-de-oro-de-campe%C3%B3n-con-cinta-sobre-fondo-blanco.jpg";


        Glide
                .with(holder.imagenPlan.getContext())
                .load(foto)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.imagenPlan);


        String finalFoto = foto;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //("click","Tienda");
                DetallePlan detalle = new DetallePlan();
                detalle.plan = lst_normal.get(position);
                detalle.imagen = finalFoto;
                detalle.tipo=tipo;
                FragmentTransaction fragmentTransaction;
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.Contenedor_Fragments, detalle).addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }

    ///
    @Override
    public int getItemCount() {
        return lst_normal.size();
    }

   /* @Override
    public Filter getFilter() {
        return puestos_filter;
    }*/

    public class MultiHolder extends RecyclerView.ViewHolder {
        // instancia la scosas que faltan aqui creas lo que hay en la vista
        CircleImageView imagenPlan;
        TextView nombrePlan, tipoPlan, valorPlan;

        public MultiHolder(@NonNull View itemView) {
            super(itemView);
            imagenPlan = itemView.findViewById(R.id.CIFotoPlan);
            nombrePlan = itemView.findViewById(R.id.TVNombrePLan);
            tipoPlan = itemView.findViewById(R.id.TVTipoPlan);
            valorPlan = itemView.findViewById(R.id.TVValorPlan);

            ///aqui instancias

        }
    }


    /*private Filter puestos_filter =new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ResponsePlanes> filtro=new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                //("lista","es nulo o es cero");

                filtro.addAll(lst_full);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ResponsePlanes item : lst_full) {

                    if(item.getData()=null){


                   String comparar= item.getMaxCategorias().toLowerCase().trim();

                    List<String> list = new ArrayList<String>(Arrays.asList(comparar.split(" , ")));
                    //("lista",Global.convertObjToString(list));


                    boolean encontre=false;
                    for(String s:list){

                        if(s.toLowerCase().contains(filterPattern)){
                            encontre=true;
                        }

                    }

                    if(encontre){
                        filtro.add(item);
                    }

                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filtro;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            lst_normal.clear();
            lst_normal.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }
    };
*/


    public void updateList(List<DatosPlanes> newlist) {
        lst_normal.clear();
        lst_normal.addAll(newlist);
        this.notifyDataSetChanged();
    }

}
