package edu.sv.proyecto.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import edu.sv.proyecto.BD.DbHelper;
import edu.sv.proyecto.Entidades.Usuarios;

public class DbUsuarios extends DbHelper {

    Context context;

    public DbUsuarios(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarUsuarios(String Nombre,String Apellido,String Celular,
                                 String Usuario,String Email,String Password){
        long  id= 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("Nombre", Nombre);
            values.put("Apellido", Apellido);
            values.put("Celular", Celular);
            values.put("Usuario", Usuario);
            values.put("Email", Email);
            values.put("Password", Password);

             id = db.insert(TABLE_USUARIOS, null, values);
        }catch (Exception ex){
            ex.toString();
        }
        return  id;
    }

    public ArrayList<Usuarios> mostrarUsuarios(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
        Usuarios usuarios = null;
        Cursor cursorUsuarios = null;

        cursorUsuarios = db.rawQuery(" SELECT * FROM " + TABLE_USUARIOS, null);

        if(cursorUsuarios.moveToFirst()) {
            do {
                usuarios = new Usuarios();
                usuarios.setId(cursorUsuarios.getInt(0));
                usuarios.setNombre(cursorUsuarios.getString(1));
                usuarios.setApellido(cursorUsuarios.getString(2));
                usuarios.setCelular(cursorUsuarios.getString(3));
                usuarios.setUser(cursorUsuarios.getString(4));
                usuarios.setEmail(cursorUsuarios.getString(5));
                usuarios.setPassword(cursorUsuarios.getString(6));
                listaUsuarios.add(usuarios);
            }while (cursorUsuarios.moveToNext());
        }

        cursorUsuarios.close();
        return listaUsuarios;
    }

    public Usuarios verUsuario(int id){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Usuarios usuarios = null;
        Cursor cursorUsuarios = null;

        cursorUsuarios = db.rawQuery(" SELECT * FROM " + TABLE_USUARIOS + " WHERE id = " + id + " LIMIT 1", null);

        if(cursorUsuarios.moveToFirst()) {
            usuarios = new Usuarios();
            usuarios.setId(cursorUsuarios.getInt(0));
            usuarios.setNombre(cursorUsuarios.getString(1));
            usuarios.setApellido(cursorUsuarios.getString(2));
            usuarios.setCelular(cursorUsuarios.getString(3));
            usuarios.setUser(cursorUsuarios.getString(4));
            usuarios.setEmail(cursorUsuarios.getString(5));
            usuarios.setPassword(cursorUsuarios.getString(6));
        }
        cursorUsuarios.close();

        return usuarios;
    }

    public boolean EditarUsuarios(int id, String Nombre,String Apellido,String Celular,
                                 String Usuario,String Email,String Password){

        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_USUARIOS + " SET Nombre = '"+ Nombre +"',  Apellido = '"+ Apellido +"', Celular = '"+ Celular +"', Usuario = '"+ Usuario +"', Email = '"+ Email +"', Password = '"+ Password +"' WHERE id = '"+id+"' ");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return  correcto;
    }

    public boolean eliminarUsuario(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_USUARIOS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;
    }
}
