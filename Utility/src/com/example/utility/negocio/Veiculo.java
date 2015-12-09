package com.example.utility.negocio;

import android.provider.BaseColumns;

public class Veiculo{
	
	public static final String AUTHORITY = "com.example.utility.repositorio";
	public long _id;
	public String nome;
	public String login; 
	public String senha; 
	public String placa;
	public String ano;
	public String latitude;
	public String longitude;
	
public static final class Veiculos implements BaseColumns{
		
		private Veiculos(){
			
		}
		
		public static final String _ID = "_id";
		public static final String NOME = "nome";
		public static final String LOGIN = "login";
		public static final String SENHA = "senha";
		public static final String PLACA = "placa";
		public static final String ANO = "ano";
		public static final String LATITUDE = "latitude";
		public static final String LONGITUDE = "longitude";
		
		
		public static final String [] COLUNAS = {_ID, NOME, LOGIN, SENHA, PLACA, ANO, LATITUDE, LONGITUDE};
		
		
	}

}
