package com.example.utility.repositorio;

import java.util.List;

import com.example.*;
import com.example.utility.R;
import com.example.utility.negocio.Veiculo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class VeiculoListAdapter extends BaseAdapter{

	private List <Veiculo> lista;
	private LayoutInflater inflater;
	
	public VeiculoListAdapter(Context context, List<Veiculo>lista){
		this.lista = lista;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		
		return lista.size();
	}

	@Override
	public Object getItem(int position) {
	
		return lista.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Veiculo c = lista.get(position);
		
		View view = inflater.inflate(R.layout.activity_relacao_veiculos, null);
							
		TextView nome = (TextView) view.findViewById(R.id.nome);
		nome.setText(c.nome);
		
		TextView placa = (TextView) view.findViewById(R.id.placa);
		placa.setText(c.placa);
		
		TextView ano = (TextView) view.findViewById(R.id.ano);
	    ano.setText (c.ano);
	    
	    TextView latitude = (TextView) view.findViewById(R.id.latitude);
	    latitude.setText (c.latitude);
	    
	    TextView longitude = (TextView) view.findViewById(R.id.longitude);
	    longitude.setText (c.longitude);
		
		return view;
	}

}
