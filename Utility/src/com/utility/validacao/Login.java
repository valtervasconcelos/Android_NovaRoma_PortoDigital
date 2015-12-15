package com.utility.validacao;


import com.example.utility.R;
import com.example.*;
import com.utility.dao.RepositorioVeiculoScript;
import com.utility.negocio.GerenciarVeiculos;
import com.utility.negocio.RelacaoVeiculos;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends Activity implements OnClickListener {
	
    EditText inputLogin;
    EditText inputSenha;
    Button entrar;
    Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_login);
        
        inputLogin = (EditText) findViewById(R.id.editTextUsuario);
        inputSenha = (EditText) findViewById(R.id.editTextSenha);
        entrar = (Button) findViewById(R.id.buttonEntrar);
        cadastrar = (Button) findViewById(R.id.buttonCadastrar);
        
        entrar.setOnClickListener(this);
        
        cadastrar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				startActivity(new Intent(Login.this, Cadastro.class));
				
			}
		});		
        
              
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
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
    
    public void onClick(View v) {
    	
		String login = inputLogin.getText().toString();
		String senha = inputSenha.getText().toString();
		
//		RepositorioVeiculo repositorio = new RepositorioVeiculo(this);
		
		RepositorioVeiculoScript repositorio = new RepositorioVeiculoScript(this);
		
		boolean usuarioExiste = repositorio.isUsuarioValido(login, senha);
		if (usuarioExiste) {
			Intent it = new Intent(getApplicationContext(), RelacaoVeiculos.class );
			startActivity(it);
		} else {
			Toast.makeText(this, "Login ou Senha inválido", Toast.LENGTH_LONG).show();
			inputLogin.setText("");
			inputSenha.setText("");
		}
		
	}

	

}
