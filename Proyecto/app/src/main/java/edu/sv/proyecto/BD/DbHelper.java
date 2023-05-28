package edu.sv.proyecto.BD;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NOMBRE = "Retailer.db";
    public static final String TABLE_USUARIOS = "t_usuarios";
    public static final String TABLE_PROMOCIONES = "t_promociones";
    public static final String TABLE_ROPA = "t_ropa";
    public static final String TABLE_DELIVERY = "t_delivery";


    public DbHelper(@Nullable Context context) {
        super(context,DATABASE_NOMBRE, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(" CREATE TABLE " + TABLE_USUARIOS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Nombre TEXT NOT NULL," +
                "Apellido TEXT NOT NULL," +
                "Celular TEXT NOT NULL," +
                "Usuario TEXT NOT NULL," +
                "Email TEXT," +
                "Password TEXT )");

        sqLiteDatabase.execSQL(" CREATE TABLE " + TABLE_PROMOCIONES + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NombrePromocion TEXT NOT NULL," +
                "DescripcionPromocion TEXT NOT NULL," +
                "PrecioPromo double(9,2) NOT NULL)");

        sqLiteDatabase.execSQL(" CREATE TABLE " + TABLE_ROPA + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NombreEntrada TEXT NOT NULL," +
                "DescripcionEntrada TEXT NOT NULL," +
                "PrecioEntrada double(9,2) NOT NULL)");

        sqLiteDatabase.execSQL(" CREATE TABLE " + TABLE_DELIVERY + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "FechaPedido TEXT NOT NULL," +
                "NombreCliente TEXT NOT NULL," +
                "DireccionCliente TEXT NOT NULL)");



    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE " + TABLE_USUARIOS + TABLE_PROMOCIONES +  TABLE_ROPA + TABLE_DELIVERY);
        onCreate(sqLiteDatabase);
    }



}
