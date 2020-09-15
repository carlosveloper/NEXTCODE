package com.scorpion.nextcode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.scorpion.nextcode.Utils.Vista_tabs;
import com.scorpion.nextcode.View.Fragments.MisFacturas;
import com.scorpion.nextcode.View.Fragments.MisPlanes;
import com.scorpion.nextcode.View.Fragments.Planes;
import com.scorpion.nextcode.ViewModel.ViewModelLogin;
import com.scorpion.nextcode.ViewModel.ViewModelMain;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewModelMain viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(ViewModelMain.class);
        viewModel.peticionPlanes();
        IniciarTabs();
        seleccion_tabs();
        tabLayout.getTabAt(0).select();
    }


    private void IniciarTabs(){
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setCustomView(new Vista_tabs(this, R.drawable.plancolor, R.drawable.plan, "Planes")));
        tabLayout.addTab(tabLayout.newTab().setCustomView(new Vista_tabs(this, R.drawable.plancolor, R.drawable.plan, "Mis Planes")));
        tabLayout.addTab(tabLayout.newTab().setCustomView(new Vista_tabs(this, R.drawable.facturacolor, R.drawable.factura, "Mi Facturas")));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }


    private void seleccion_tabs(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                //  tabLayout.getTabAt(position).select();

                elegir(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //("seleccion ","antiguo");

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //  int position = tab.getPosition();
                //("seleccion ","nuevo");
                elegir(tab.getPosition());
            }
        });



    }

    private void elegir(int position){
        // if(position==0)
        clearFragmentBackStack();
        switch (position) {
            case 0:
                getSupportFragmentManager().beginTransaction()
                        //.replace(R.id.Contenedor_Fragments, new mercado()).commit();
                        .replace(R.id.Contenedor_Fragments, new Planes()).commit();
                break;

            case 1:
                //("posicion",""+position);

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Contenedor_Fragments, new MisPlanes()).commit();
                break;

            case 2:
                //("posicion",""+position);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Contenedor_Fragments, new MisFacturas()).commit();
                break;

        }

    }

    public   void cambiar_tab(int position){
        tabLayout.getTabAt(position).select();

    }



    public void clearFragmentBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        //("cuantos fragments",""+fm.getBackStackEntryCount());
        for (int i = 0; i < fm.getBackStackEntryCount() - 1; i++) {
            fm.popBackStack();
        }
    }





}