package com.example.matriculasaluno;

import java.util.List;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.matriculasaluno.adapter.ListaAlunosAdapter;
import com.example.matriculasaluno.dao.AlunoDAO;

@SuppressLint("NewApi")
public class ListaAlunos extends ActionBarActivity {

	private Aluno aluno;
	private ListView lista;
	private List<Aluno> alunos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listagem_aluno);

		carregaLista();

		lista.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int posicao, long id) {

				Aluno alunoClicado = (Aluno) adapter.getItemAtPosition(posicao);

				Intent irParaFormulario = new Intent(ListaAlunos.this,
						Formulario.class);
				irParaFormulario.putExtra("alunoSelecionado", alunoClicado);

				startActivity(irParaFormulario);

			}

		});

		lista.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int posicao, long id) {
				// TODO Auto-generated method stub

				aluno = (Aluno) adapter.getAdapter().getItem(posicao);

				Toast.makeText(ListaAlunos.this,
						"Cliente na posição " + aluno.getId() + "\n " + id,
						Toast.LENGTH_LONG).show();

				return false;
			}
		});

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		MenuItem ligar = menu.add("Ligar");
		ligar.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {

				Intent irTelaDiscagem = new Intent(Intent.ACTION_CALL);
				Uri discarPara = Uri.parse("tel:" + aluno.getTelefone());
				irTelaDiscagem.setData(discarPara);

				startActivity(irTelaDiscagem);

				return false;
			}
		});
		menu.add("Enviar SMS");
		MenuItem navegarSite = menu.add("Navegar no Site");
		navegarSite.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {

				Intent irParaOSite = new Intent(Intent.ACTION_VIEW);
				Uri localSite = Uri.parse("http://" + aluno.getSite());
				irParaOSite.setData(localSite);
				startActivity(irParaOSite);

				return false;
			}
		});
		MenuItem deletar = menu.add("Deletar");
		deletar.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				AlunoDAO dao = new AlunoDAO(ListaAlunos.this);
				dao.deletar(aluno);
				dao.close();

				carregaLista();
				return false;
			}
		});
		menu.add("Ver no mapa");
		menu.add("Enviar e-mail");

		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	protected void onResume() {
		super.onResume();

		carregaLista();
	}

	private void carregaLista() {
		AlunoDAO dao = new AlunoDAO(this);

		 alunos = dao.getLista();
		dao.close();

		lista = (ListView) findViewById(R.id.lista);

		ListaAlunosAdapter adapter = new ListaAlunosAdapter(alunos,
				ListaAlunos.this);

		lista.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_alunos, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		switch (id) {
		case R.id.novo:

			Intent intent = new Intent(this, Formulario.class);
			startActivity(intent);

			break;
			
		case R.id.enviar_alunos:

			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
