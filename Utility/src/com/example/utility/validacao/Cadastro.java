package com.example.utility.validacao;


import com.example.utility.R;
import com.example.utility.negocio.RelacaoVeiculos;
import com.example.utility.negocio.Veiculo;
import com.example.utility.repositorio.RepositorioVeiculoScript;

import com.example.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Cadastro extends Activity implements OnClickListener{
	
	RepositorioVeiculoScript repositorio;
	
	private EditText novoLogin;
	private EditText novaSenha;
	private Button cadastrar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro);
		
		novoLogin = (EditText) findViewById(R.id.editTextNovoUsuario);
		novaSenha = (EditText) findViewById(R.id.editTextNovaSenha);
		cadastrar = (Button) findViewById(R.id.buttonNovoCadastro);
		cadastrar.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		//
		String newLogin = novoLogin.getText().toString();
		String newSenha = novaSenha.getText().toString();
		
		Veiculo veiculo = new Veiculo();
		veiculo.nome = "Celta";
		veiculo.login = newLogin;
		veiculo.senha = newSenha;
		veiculo.placa = "KKK-0909";
		veiculo.ano = "2015";
		//Aqui abaixo não havia
		veiculo.latitude = "123";
		veiculo.longitude = "123";
		
		repositorio = new RepositorioVeiculoScript(this);
		repositorio.salvar(veiculo);
		
		startActivity(new Intent(this, RelacaoVeiculos.class));
		
		
		
	}

	

	
}
