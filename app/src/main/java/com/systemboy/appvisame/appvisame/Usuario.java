package com.systemboy.appvisame.appvisame;

/**
 * Created by Daniel on 02/04/2015.
 */
public class Usuario {
    private String titulo;
    private String estado;
    private String fecha;
    private String urgencia;

   public Usuario(String titulo, String estado, String fecha, String urgencia){

    this.titulo = titulo;
    this.estado = estado;
    this.fecha = fecha;
    this.urgencia = urgencia;

    }

    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo){
        this.titulo = titulo;
    }
    public String getEstado(){
        return  estado;
    }
    public void setEstado(String estado){
        this.estado = estado;
    }
    public String getFecha(){
        return fecha;
    }
    public void setFecha(String fecha){
        this.fecha = fecha;
    }
    public String getUrgencia(){
        return urgencia;
    }
}
