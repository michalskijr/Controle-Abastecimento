package com.example.mjuni.trabalho.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by mjunior on 03/04/2016.
 */
public abstract class DAO<T> extends SQLiteOpenHelper {

    private static final String NAME = "BANCO_TRABALHO";
    private static final int VERSION = 1;

    private Class<T> tipo;

    public DAO(Context context, Class<T> tipo) {
        super(context, NAME, null, VERSION);
        this.tipo = tipo;
    }

    public void salvar(T objeto) throws IllegalAccessException {
        ContentValues cv = new ContentValues();
        for (Field field : FieldUtils.getAllFieldsList(getTipo())) {
            if(!field.isAnnotationPresent(Id.class) &&
                    !field.getName().startsWith("shadow$"))
                cv.put(field.getName(), String.valueOf(FieldUtils.readField(
                        field, objeto, true)));
        }

        getWritableDatabase().insert(tipo.getSimpleName(), null, cv);
    }

    public void alterar(T objeto) throws IllegalAccessException {
        ContentValues contentValues = new ContentValues();
        for (Field field : FieldUtils.getAllFieldsList(getTipo())) {
            if (!field.isAnnotationPresent(Id.class) &&
                    !field.getName().startsWith("shadow$"))
                contentValues.put(field.getName(), String.valueOf(
                        FieldUtils.readField(field, objeto, true)));
        }

        StringBuilder clausulaWhere = new StringBuilder();
        String[] valorWhere = new String[]{};
        for (Field field : FieldUtils.getAllFieldsList(getTipo())) {
            if (field.isAnnotationPresent(Id.class)) {
                clausulaWhere.append(field.getName()).append("=?");
                valorWhere = new String[]{String.valueOf(FieldUtils.readField(
                        field, objeto, true))};
            }
        }

        getWritableDatabase().update(getTipo().getSimpleName(), contentValues,
                clausulaWhere.toString(), valorWhere);
    }

    public void deletar(T objeto) throws IllegalAccessException{
        StringBuilder clausulaWhere = new StringBuilder();
        String[] valorWhere = new String[]{};
        for (Field field : FieldUtils.getAllFieldsList(getTipo())) {
            if (field.isAnnotationPresent(Id.class)) {
                clausulaWhere.append(field.getName()).append("=?");
                valorWhere = new String[]{String.valueOf(FieldUtils.readField(
                        field, objeto, true))};
            }
        }

        getWritableDatabase().delete(getTipo().getSimpleName(),
                clausulaWhere.toString(), valorWhere);
    }

    public abstract List<T> listar();

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_MARCA = "CREATE TABLE marca (" +
                "cod INTEGER PRIMARY KEY, nome TEXT)";
        db.execSQL(SQL_MARCA);

        String SQL_INSERT_MARCA_0 = "INSERT INTO marca VALUES (0, 'VOLKSWAGEN');";
        String SQL_INSERT_MARCA_1 = "INSERT INTO marca VALUES (1, 'FORD');";
        String SQL_INSERT_MARCA_2 = "INSERT INTO marca VALUES (2, 'GM');";
        String SQL_INSERT_MARCA_3 = " INSERT INTO marca VALUES (3, 'HONDA');";

        db.execSQL(SQL_INSERT_MARCA_0);
        db.execSQL(SQL_INSERT_MARCA_1);
        db.execSQL(SQL_INSERT_MARCA_2);
        db.execSQL(SQL_INSERT_MARCA_3);

        String SQL_VEICULO = "CREATE TABLE veiculo (" +
                "cod INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "cod_marca INTEGER NOT NULL, " +
                "modelo TEXT, " +
                "placa TEXT NOT NULL, km_inicial REAL, " +
                "cap_tanque REAL, descricao TEXT, " +
                "FOREIGN KEY (cod_marca) REFERENCES marca(cod))";
        db.execSQL(SQL_VEICULO);

        String SQL_ABASTECIMENTO = "CREATE TABLE abastecimento (" +
                "cod INTEGER PRIMARY KEY AUTOINCREMENT, data TEXT, litros NUMERIC," +
                "vl_total NUMERIC, km_atual NUMERIC, tp_combustivel INTEGER, posto TEXT, " +
                "cod_veiculo INTEGER NOT NULL, " +
                "FOREIGN KEY (cod_veiculo) REFERENCES veiculo(cod))";
        db.execSQL(SQL_ABASTECIMENTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Class<T> getTipo() {
        return this.tipo;
    }
}