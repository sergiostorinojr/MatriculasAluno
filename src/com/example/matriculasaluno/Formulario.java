package com.example.matriculasaluno;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.example.matriculasaluno.dao.AlunoDAO;

public class Formulario extends Activity {

	private FormularioHelper helper;
	private String caminhoArquivo;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.formulario);
		
		Intent intent = getIntent();
		final Aluno alunoAlteracao = (Aluno) intent.getSerializableExtra("alunoSelecionado");
		
	

		helper = new FormularioHelper(this);

		Button bt = (Button) findViewById(R.id.botao);
		
		if(alunoAlteracao != null){
			bt.setText("Alterar");
			helper.setAlunoFormulario(alunoAlteracao);
		}
		

		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Aluno aluno = helper.getAlunoFormulario();

				AlunoDAO dao = new AlunoDAO(Formulario.this);
				
				if(alunoAlteracao == null){
					dao.salva(aluno);
					
				} else {
					aluno.setId(alunoAlteracao.getId());
					dao.alterar(aluno);
				}
				
				dao.close();
				
				finish();// volta para activity anterior

			}
		});
		
		//Clique na foto disparar camara Android
		ImageView foto = helper.getFoto();
	
		foto.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//faz chamada da camara android
				Intent irCamaraAndroid = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				caminhoArquivo = Environment.getExternalStorageDirectory().toString()+
						"/"+System.currentTimeMillis()+".png";
				
				File arquivo = new File(caminhoArquivo);
				
				Uri localImagem = Uri.fromFile(arquivo);
				
				irCamaraAndroid.putExtra(MediaStore.EXTRA_OUTPUT, localImagem);
				startActivityForResult(irCamaraAndroid, 123);
				
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == 123){
			if(resultCode == Activity.RESULT_OK){
				helper.carregaImagem(caminhoArquivo);
				
			} else {
				caminhoArquivo = null;
			}
		}
	}
}
