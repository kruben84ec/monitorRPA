package org.util;

public class Conexion {
    private Conexion(){}

    private static class Holder{
        private static final Conexion INSTANCE = new Conexion();
    }

    public static Conexion getInstance(){
        return Holder.INSTANCE;
    }
}
