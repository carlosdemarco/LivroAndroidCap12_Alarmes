package br.livro.android.cap12;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * Um Alarm é utilizado para agendar a execução de determinada tarefa, como abrir uma Activity ou BroadcastReceiver
 * 
 * @author rlecheta
 * 
 */
public class Menu extends ListActivity {
	private static final String[] ops = new String[] { "Agendar Alarme", "Agendar Alarme Repetir"};

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, ops));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
			case 0:
				startActivity(new Intent(this,ExemploAgendarAlarme.class));
				break;
			case 1:
				startActivity(new Intent(this,ExemploAgendarAlarmeRepetir.class));
				break;
		}
	}
}