package com.example.utility.repositorio;

import android.content.Context;
import com.example.*;


public class RepositorioVeiculoScript extends RepositorioVeiculo{
	
	private static final String SCRIPT_DATABASE_DELETE = "DROP TABLE IF EXISTS veiculo";

	private static final String[] SCRIPT_DATABASE_CREATE = new String[] {
		"create table veiculo (_id integer primary key autoincrement,"
		+ "nome text not null, login text not null, senha text not null, placa text not null, ano text not null, latitude text not null, longitude text not null);",
		
		
		"insert into veiculo(nome, login, senha, placa, ano, latitude, longitude) values('Celta', 'val', '1234', 'KKK-9898', '2014', '+5432', '-8976');",
		"insert into veiculo(nome, login, senha, placa, ano, latitude, longitude) values('Celta', 'roberto', '1235', 'KKK-9899', '2014', '+5432', '-8976');",
		"insert into veiculo(nome, login, senha, placa, ano, latitude, longitude) values('Celta', 'carlos', '1236', 'KKK-9890', '2014', '+5432', '-8976');",
		
	};
	
	private SQLiteHelper dbHelper;
	
	public RepositorioVeiculoScript(Context context) {
     
	
	dbHelper = new SQLiteHelper(context, NOME_BANCO, VERSAO_BANCO, SCRIPT_DATABASE_CREATE, SCRIPT_DATABASE_DELETE);
		
	db = dbHelper.getWritableDatabase();
		
	}
	
	
	
	@Override
	public void fechar() {
		super.fechar();
		if (dbHelper != null) {
			dbHelper.close();
		}
	}


}
