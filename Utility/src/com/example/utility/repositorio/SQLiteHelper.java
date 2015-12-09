package com.example.utility.repositorio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.*;

public class SQLiteHelper extends SQLiteOpenHelper{
	
private static final String CATEGORIA_TESTE = "teste";
	
	private String[] scriptsSQLCreate;
	private String scriptsSQLDelete;

	public SQLiteHelper(
			Context context,
			String nomeBanco,
			int versaoBanco,
			String[]scriptsSQLCreate,
			String scriptsSQLDelete){
		super(context, nomeBanco, null, versaoBanco);
		this.scriptsSQLCreate = scriptsSQLCreate;
		this.scriptsSQLDelete = scriptsSQLDelete;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(CATEGORIA_TESTE, "Criando banco com sql");
		int qtdeScripts = scriptsSQLCreate.length;
		for(int i = 0; i< qtdeScripts; i++){
			String sql = scriptsSQLCreate[i];
			Log.i(CATEGORIA_TESTE, sql);
			
			db.execSQL(sql);
		}
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(CATEGORIA_TESTE, "Atualizando a vers�o " + oldVersion + " para " + newVersion);
		Log.i(CATEGORIA_TESTE, scriptsSQLDelete);
		db.execSQL(scriptsSQLDelete);
		onCreate(db);
	}

}
