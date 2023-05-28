package edu.sv.proyecto.BD;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import edu.sv.proyecto.Entidades.Entradas;

public class DbEntradas extends DbHelper {

    Context context;

    public DbEntradas(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarEntrada(String NombreEntrada,String DescripcionEntrada,Double PrecioEntrada){
        long  id= 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("NombreEntrada", NombreEntrada);
            values.put("DescripcionEntrada", DescripcionEntrada);
            values.put("PrecioEntrada", PrecioEntrada);

            id = db.insert(TABLE_ROPA, null, values);
        }catch (Exception ex){
            ex.toString();
        }
        return  id;
    }

    public ArrayList<Entradas> mostrarEntradas(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Entradas> listaEntradas = new ArrayList<>();
        Entradas entradas = null;
        Cursor cursorEntradas = null;

        cursorEntradas = db.rawQuery(" SELECT * FROM " + TABLE_ROPA, null);

        if(cursorEntradas.moveToFirst()) {
            do {
                entradas = new Entradas();
                entradas.setId(cursorEntradas.getInt(0));
                entradas.setNombreEntrada(cursorEntradas.getString(1));
                entradas.setDescripcionEntrada(cursorEntradas.getString(2));
                entradas.setPrecioEntrada(cursorEntradas.getDouble(3));
                listaEntradas.add(entradas);
            }while (cursorEntradas.moveToNext());
        }

        cursorEntradas.close();
        return listaEntradas;
    }

    public Entradas verEntradas(int id){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Entradas entradas = null;
        Cursor cursorEntradas = null;

        cursorEntradas = db.rawQuery(" SELECT * FROM " + TABLE_ROPA + " WHERE id = " + id + " LIMIT 1", null);

        if(cursorEntradas.moveToFirst()) {
            entradas = new Entradas();
            entradas.setId(cursorEntradas.getInt(0));
            entradas.setNombreEntrada(cursorEntradas.getString(1));
            entradas.setDescripcionEntrada(cursorEntradas.getString(2));
            entradas.setPrecioEntrada(cursorEntradas.getDouble(3));
        }
        cursorEntradas.close();

        return entradas;
    }

    public boolean EditarEntrada(int id, String NombreEntrada,String DescripcionEntrada,Double PrecioEntrada){

        boolean correcto = false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_ROPA + " SET NombreEntrada = '"+ NombreEntrada +"',  DescripcionEntrada = '"+ DescripcionEntrada +"', " +
                    "PrecioEntrada = '"+ PrecioEntrada +"' WHERE id = '"+id+"' ");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally {
            db.close();
        }
        return  correcto;
    }

    public boolean eliminarEntrada(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_ROPA + " WHERE id = '" + id + "'");
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
