package com.example.matriculasaluno.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.matriculasaluno.Aluno;
import com.example.matriculasaluno.R;

public class ListaAlunosAdapter extends BaseAdapter {

	private ArrayList<Aluno> alunos = new ArrayList<Aluno>();
	private LayoutInflater inflater;
	private Context context;

	public ListaAlunosAdapter(List<Aluno> alunos, Context context) {
		this.alunos.addAll(alunos);
		this.context = context;
	}

	@Override
	public int getCount() {
		return alunos.size();
	}

	@Override
	public Object getItem(int position) {
		return alunos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return alunos.get(position).getId();
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		
		Aluno aluno  = alunos.get(position);
		
		View linha;
		
		if(view == null){
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			linha = inflater.inflate(R.layout.linha_listagem, null);
			
		}
		else{
			linha = view;
		}
		
		TextView nome = (TextView) linha.findViewById(R.id.listnome);
		nome.setText(aluno.getNome());
		
		ImageView foto = (ImageView) linha.findViewById(R.id.listfoto);
		if(aluno.getFoto() != null){
			
		Bitmap fotoAluno = BitmapFactory.decodeFile(aluno.getFoto());
		Bitmap fotoReduzida = Bitmap.createScaledBitmap(fotoAluno, 100, 100, true);
		foto.setImageBitmap(fotoReduzida);
		
		} else {
			
			Drawable semFoto = context.getResources().getDrawable(R.drawable.semfoto);
			foto.setImageDrawable(semFoto);
		}
		
		
		
		return linha;
	}

}
