package com.example.utility.repositorio;

import java.util.ArrayList;
import java.util.List;

import com.example.*;
import com.example.utility.negocio.Veiculo;
import com.example.utility.negocio.Veiculo.Veiculos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class RepositorioVeiculo {
	
	private static final String CATEGORIA_TESTE = "teste";

	protected static final String NOME_BANCO = "teste_banco"; 

	protected static final String NOME_TABELA = "veiculo"; 

	protected static final int VERSAO_BANCO = 1;

	protected SQLiteDatabase db;
	
	public RepositorioVeiculo(){
		
	}

	public RepositorioVeiculo(Context context) {
		db = context.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE,null);

	}

	public long salvar(Veiculo veiculo){
		long resultado = 0;

		if (veiculo._id != 0) {
			resultado = atualizar(veiculo);
		} else {
			resultado = inserir(veiculo);
		}
		return resultado;
	}

	public long inserir(Veiculo veiculo) {
		
		ContentValues values = new ContentValues();

		values.put(Veiculos.NOME, veiculo.nome);
		values.put(Veiculos.LOGIN, veiculo.login);
		values.put(Veiculos.SENHA, veiculo.senha);
		values.put(Veiculos.PLACA, veiculo.placa);
		values.put(Veiculos.ANO, veiculo.ano);
		values.put(Veiculos.LATITUDE, veiculo.latitude);
		values.put(Veiculos.LONGITUDE, veiculo.longitude);
		
		

		long id = inserir(values);

		return id;
	}

	public long inserir(ContentValues valores) {
		return db.insert(NOME_TABELA, "", valores);
	}

	public int atualizar(Veiculo veiculo) {
		ContentValues values = new ContentValues();

		values.put(Veiculos.NOME, veiculo.nome);
		values.put(Veiculos.LOGIN, veiculo.login);
		values.put(Veiculos.SENHA, veiculo.senha);
		values.put(Veiculos.PLACA, veiculo.placa);
		values.put(Veiculos.ANO, veiculo.ano);
		values.put(Veiculos.LATITUDE, veiculo.latitude);
		values.put(Veiculos.LONGITUDE, veiculo.longitude);

		String _id = String.valueOf(veiculo._id);
		String where = Veiculos._ID + "=?";
		String[] whereArgs = new String[] { _id };

		int count = atualizar(values, where, whereArgs);

		return count;
	}

	public int atualizar(ContentValues valores, String where, String[] whereArgs) {

		int count = db.update(NOME_TABELA, valores, where, whereArgs);
		Log.i(CATEGORIA_TESTE, "Atualizou" + count + "registros");
		return count;
	}
	
	public int deletar(long id) {
		String where = Veiculos._ID + "=?";
		String _id = String.valueOf(id);
		String[] whereArgs = new String[] { _id } ;
		
		int count = deletar(where, whereArgs);
		
		return count;
	}

	public int deletar(String where, String[] whereArgs) {
		int count = db.delete(NOME_TABELA, where, whereArgs);
		Log.i(CATEGORIA_TESTE, "removidos" + count + "registros");
		return count;
	}

	public Cursor getCursor() {
		try {
			return db.query(NOME_TABELA, Veiculos.COLUNAS, null, null, null, null, null, null);
		} catch (SQLException ex) {
			Log.e(CATEGORIA_TESTE, "Erro ao buscar o medicamento: " + ex.toString());
			return null;
		}

	}

	public List<Veiculo> listarVeiculo() {

		Cursor c = getCursor();
		List<Veiculo> veiculos = new ArrayList<Veiculo>();

		if (c.moveToFirst()) {
			int idxId = c.getColumnIndex(Veiculos._ID);
			int idxNome = c.getColumnIndex(Veiculos.NOME);
			int idxPlaca = c.getColumnIndex(Veiculos.PLACA);
			int idxAno = c.getColumnIndex(Veiculos.ANO);
			int idxLatitude = c.getColumnIndex(Veiculos.LATITUDE);
			int idxLongitude = c.getColumnIndex(Veiculos.LONGITUDE);

			do {
				Veiculo veiculo = new Veiculo();
				veiculos.add(veiculo);

				veiculo._id = c.getLong(idxId);
				veiculo.nome = c.getString(idxNome);
				veiculo.placa = c.getString(idxPlaca);
				veiculo.ano = c.getString(idxAno);
				veiculo.latitude = c.getString(idxLatitude);
				veiculo.longitude = c.getString(idxLongitude);
			} while (c.moveToNext());

		}
		return veiculos;

	}

	public Veiculo buscarVeiculoPorNome(String nome) {  
		Veiculo veiculo = null;
		try {
			Cursor c = db.query(NOME_TABELA, Veiculos.COLUNAS,
					Veiculos.NOME + "='", null, null, null, null, null);
			
			if (c.moveToNext()) {
				veiculo = new Veiculo();
				veiculo._id = c.getLong(0);
				veiculo.nome = c.getString(1);
				veiculo.placa = c.getString(2);
				veiculo.ano = c.getString(3);
				veiculo.latitude = c.getString(4);
				veiculo.longitude = c.getString(5);
			}
		} catch (Exception ex) {
			Log.e(CATEGORIA_TESTE,
					"Erro ao buscar o veiculo pelo nome: " + ex.toString());
		}
		return veiculo;

	}

	public boolean isUsuarioValido(String login, String senha) {
		 boolean retorno = false;
		try {
			//WHERE login = ? AND senha = ?
			String where = Veiculos.LOGIN + "=?";
			where += " AND ";
			where += Veiculos.SENHA + "=?";
			
			String[] whereArgs = new String[] {login, senha};
			
			Cursor c = db.query(NOME_TABELA, Veiculos.COLUNAS, where, whereArgs, null, null, null, null);
			
			if (c.moveToFirst()) {
				retorno = true;
			}
		} catch (Exception ex) {
			Log.e(CATEGORIA_TESTE, "Erro ao buscar o veiculo pelo nome: " + ex.toString());
		}
		return retorno;
		
	}
	
	public void fechar() {
		if (db != null) {
			db.close();
		}
	}

}
