package edu.sv.proyecto.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;
import java.util.ArrayList;

import edu.sv.proyecto.BD.DbHelper;
import edu.sv.proyecto.Entidades.Promociones;

public class DbPromociones extends DbHelper {

    Context context;

    public DbPromociones(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarPromocion(String NombrePromocion,String DescripcionPromocion,Double PrecioPromo){
        long  id= 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("NombrePromocion", NombrePromocion);
            values.put("DescripcionPromocion", DescripcionPromocion);
            values.put("PrecioPromo", PrecioPromo);

            id = db.insert(TABLE_PROMOCIONES, null, values);
        }catch (Exception ex){
            ex.toString();
        }
        return  id;
    }

    public ArrayList<Promociones> mostrarPromociones(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Promociones> listaPromociones = new ArrayList<>();
        Promociones promociones = null;
        Cursor cursorPromociones = null;

        cursorPromociones = db.rawQuery(" SELECT * FROM " + TABLE_PROMOCIONES, null);

        if(cursorPromociones.moveToFirst()) {
            do {
                promociones = new Promociones();
                promociones.setId(cursorPromociones.getInt(0));
                promociones.setNombrePromocion(cursorPromociones.getString(1));
                promociones.setDescripcionPromocion(cursorPromociones.getString(2));
                promociones.setPrecio(cursorPromociones.getDouble(3));
                listaPromociones.add(promociones);
            }while (cursorPromociones.moveToNext());
        }

        cursorPromociones.close();
        return listaPromociones;
    }

    public Promociones verPromocion(int id){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Promociones promociones = null;
        Cursor cursorPromociones = null;

        cursorPromociones = db.rawQuery(" SELECT * FROM " + TABLE_PROMOCIONES + " WHERE id = " + id + " LIMIT 1", null);

        if(cursorPromociones.moveToFirst()) {
            promociones = new Promociones();
            promociones.setId(cursorPromociones.getInt(0));
            promociones.setNombrePromocion(cursorPromociones.getString(1));
            promociones.setDescripcionPromocion(cursorPromociones.getString(2));
            promociones.setPrecio(cursorPromociones.getDouble(3));
        }
        cursorPromociones.close();

        return promociones;
    }

    public boolean EditarPromocion(int id, String NombrePromocion,String DescripcionPromocion,Double PrecioPromo){

        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_PROMOCIONES + " SET NombrePromocion = '"+ NombrePromocion +"',  DescripcionPromocion = '"+ DescripcionPromocion +"', " +
                    "PrecioPromo = '"+ PrecioPromo +"' WHERE id = '"+id+"' ");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return  correcto;
    }

    public boolean eliminarPromocion(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_PROMOCIONES + " WHERE id = '" + id + "'");
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