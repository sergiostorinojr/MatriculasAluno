package com.example.matriculasaluno;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

public class FormularioHelper {

	private EditText editNome;
	private EditText editSite;
	private EditText editTelefone;
	private EditText editEndereco;
	private RatingBar ratingBar;
	private ImageView img;
	private Aluno alunos;

	public FormularioHelper(Formulario formulario) {
		editNome = (EditText) formulario.findViewById(R.id.edtNome);
		editSite = (EditText) formulario.findViewById(R.id.edtSite);
		editTelefone = (EditText) formulario.findViewById(R.id.edtTelefone);
		editEndereco = (EditText) formulario.findViewById(R.id.edtEndereco);
		ratingBar = (RatingBar) formulario.findViewById(R.id.rbNota);
		img = (ImageView) formulario.findViewById(R.id.foto);
		alunos = new Aluno();
	}

	public Aluno getAlunoFormulario() {
		Aluno aluno = new Aluno();

		aluno.setNome(editNome.getText().toString());
		aluno.setSite(editSite.getText().toString());
		aluno.setTelefone(editTelefone.getText().toString());
		aluno.setEndereco(editEndereco.getText().toString());
		aluno.setNota(Double.valueOf(ratingBar.getRating()));
		aluno.setFoto(alunos.getFoto());

		return aluno;

	}

	public void setAlunoFormulario(Aluno alunoAlteracao) {
		alunos = alunoAlteracao;
		editNome.setText(alunoAlteracao.getNome());
		editSite.setText(alunoAlteracao.getSite());
		editEndereco.setText(alunoAlteracao.getEndereco());
		editTelefone.setText(alunoAlteracao.getTelefone());
		ratingBar.setRating(alunoAlteracao.getNota().floatValue());
		
		if(alunos.getFoto() != null){
			carregaImagem(alunoAlteracao.getFoto());
		}
		
	}


	public ImageView getFoto() {
		return img;
	}

	public void carregaImagem(String caminhoArquivo) {
		alunos.setFoto(caminhoArquivo);
		
		Bitmap imagem = BitmapFactory.decodeFile(caminhoArquivo);
		Bitmap createScaledBitmap = Bitmap.createScaledBitmap(imagem, 100, 100, true);
		
		
		img.setImageBitmap(createScaledBitmap);
		
	}
	

}
