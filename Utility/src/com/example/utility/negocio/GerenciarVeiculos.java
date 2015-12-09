package com.example.utility.negocio;

import com.example.utility.R;
import com.example.utility.R.id;
import com.example.utility.R.layout;
import com.example.utility.R.menu;
import com.example.utility.repositorio.RepositorioVeiculoScript;
import com.example.utility.repositorio.Veiculo;

import android.app.Activity;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class GerenciarVeiculos extends Activity implements OnClickListener {
	
	EditText modelo;
	EditText placa;
	EditText ano;
	EditText removerVeiculo;
	Button adicionar;
	Button remover;
	Button rastrear;
	Button emergencia;
	Button sair;
	Button contato;
	
	RepositorioVeiculoScript repositorio;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gerenciar_veiculos);
		
		modelo = (EditText) findViewById(R.id.editTextModelo);
		placa = (EditText) findViewById(R.id.editTextPlaca);
		ano = (EditText) findViewById(R.id.editTextAno);
		adicionar = (Button) findViewById(R.id.buttonAdicionar);
		
		adicionar.setOnClickListener(this);
		
//		contato = (Button)findViewById(R.id.buttonContatos);
//		contato.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//			//	startActivity(new Intent (GerenciarVeiculos.this, Contatos.class));
//				
//				startActivity(new Intent (GerenciarVeiculos.this, ContatoSimples.class));				
//			}
//		});
		
		sair = (Button) findViewById(R.id.buttonSair);
		sair.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Para sair da aplicação
				Intent intent = new Intent(Intent.ACTION_MAIN);
				intent.addCategory(Intent.CATEGORY_HOME);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
			}
		});
		
		rastrear = (Button) findViewById(R.id.buttonRastrear);
		rastrear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				startActivity(new Intent(GerenciarVeiculos.this, Mapa.class));
				
			}
		});
		
		emergencia = (Button) findViewById(R.id.emergencia);
		emergencia.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//representa o endereço que queremos abrir
				Uri uri = Uri.parse("tel:91987504");
				//Cria a Intent com o endereço
				Intent it = new Intent (Intent.ACTION_CALL,uri);
				//Envia a mensagem ao SO
				startActivity(it);
				
				
			}
		});
		
		
		remover = (Button) findViewById(R.id.buttonRemover);
		removerVeiculo = (EditText) findViewById(R.id.editTextRemover);	
		remover.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
				repositorio = new RepositorioVeiculoScript(GerenciarVeiculos.this);
				String stringRemover = removerVeiculo.getText().toString();
				int stringConvertida = new Integer (stringRemover).intValue();
				
				repositorio.deletar(stringConvertida);		
				
				
				startActivity(new Intent(GerenciarVeiculos.this, RelacaoVeiculos.class));
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gerenciar_veiculos, menu);
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
		
		Veiculo veiculo = new Veiculo();
		veiculo.nome = modelo.getText().toString();
		veiculo.placa = placa.getText().toString();
		veiculo.ano = ano.getText().toString();
		veiculo.login = "val";
		veiculo.senha = "123";
		veiculo.latitude = "123";
		veiculo.longitude = "123";
		
		repositorio = new RepositorioVeiculoScript(this);
		repositorio.salvar(veiculo);
		
		startActivity(new Intent(this, RelacaoVeiculos.class));
		
		
	}
}
