package com.scorpion.nextcode.Common;

import com.scorpion.nextcode.Model.Response.DatosLogin;
import com.scorpion.nextcode.Model.Response.DatosPlanes;

import java.util.ArrayList;
import java.util.List;

public class Global {
   public static DatosLogin miPerfil= new DatosLogin();
   //Todo todos los planes
   public static List<DatosPlanes> planes= new ArrayList<>();



   //Todo mis planes
   public static List<DatosPlanes> Misplanes= new ArrayList<>();
}
