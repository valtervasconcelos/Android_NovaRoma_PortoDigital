package com.utility.negocio;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.*;
import com.example.utility.R;
import com.utility.dao.RepositorioVeiculoScript;
import com.utility.dao.VeiculoListAdapter;
import com.utility.modelo.Veiculo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class RelacaoVeiculos extends ListActivity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Não usa esse abaixo
		//setContentView(R.layout.activity_relacao_veiculos);
		
        RepositorioVeiculoScript repositorio = new RepositorioVeiculoScript(this);
		
		List<Veiculo> lista = repositorio.listarVeiculo();
		
		setListAdapter(new VeiculoListAdapter(this, lista));
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
				
        Veiculo veiculo = (Veiculo) this.getListAdapter().getItem(position);
		
		Toast.makeText(this, "Você selecionou o veiculo: " + veiculo._id, Toast.LENGTH_SHORT).show();
		
		startActivity(new Intent(this, GerenciarVeiculos.class));
	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.relacao_veiculos, menu);
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
		// TODO Auto-generated method stub
		
	}
}
