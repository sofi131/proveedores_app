package com.ceica.Modelos;

import com.ceica.bbdd.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class Proveedor {
    private int id;
    private String cif;
    private String nombre;
    private String direccion;
    private String localidad;

    //vacío
    public Proveedor() {

    }

    //2 valores
    public Proveedor(java.lang.String cif, java.lang.String nombre) {
        this.cif = cif;
        this.nombre = nombre;
    }

    public static boolean insertar(Proveedor proveedor) {
        Connection conn=Conexion.conectar();
        String sql="insert into proveedores (nombre,dirección,localidad,provincia,cif)" +
                " values (?,?,?,?,?)";
        try {
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,proveedor.getNombre());
            pst.setString(2, proveedor.getDireccion());
            pst.setString(3,proveedor.getLocalidad());
            pst.setString(4,proveedor.getProvincia());
            pst.setString(5,proveedor.getCif());
            if(pst.executeUpdate()<0){
                return false;
            }else{
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //getter
    public int getId() {
        return id;
    }

    public String getCif() {
        return cif;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    //setter
    public void setId(int id) {
        this.id = id;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    private String provincia;

    //------------------------------Conexión a proveedores-------------------------------
    public static List<Proveedor> getProveedores()  {
        List<Proveedor> proveedorList=new ArrayList<>();
        Connection conn= Conexion.conectar();
        String sql="select * from proveedores";
        try {
            Statement stm=conn.createStatement();
            ResultSet respuesta = stm.executeQuery(sql);
            while (respuesta.next()){
                Proveedor proveedor=new Proveedor();
                proveedor.setId(respuesta.getInt("idProveedor"));
                proveedor.setCif(respuesta.getString("cif"));
                proveedor.setNombre(respuesta.getString("nombre"));
                proveedor.setDireccion(respuesta.getString("dirección"));
                proveedor.setLocalidad(respuesta.getString("localidad"));
                proveedor.setProvincia(respuesta.getString("provincia"));
                proveedorList.add(proveedor);
            }

        } catch (SQLException e) {
            //throw new RuntimeException(e);

            return proveedorList;
        }
        try {
            conn.close();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
        }
        return proveedorList;
    }
//------------------------------------editarNombreProveedor--------------------
//    public boolean editarNombreProveedor(String cif, String nombre){
//
//    };

    //método toString cadena de txt con toda la info
    @Override
    public String toString() {
        return "Proveedor{" +
                "id=" + id +
                ", cif='" + cif + '\'' +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' + "\n" +
                '}';
    }
}
