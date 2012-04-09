package br.livro.android.cap12;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * BroadcastReceiver para receber o alarme depois do tempo especificado
 * 
 * @author ricardo
 * 
 */
public class ReceberAlarme extends BroadcastReceiver {
	private static final String CATEGORIA = "livro";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i(CATEGORIA, "Alarme disparado!");

		// Podemos iniciar uma activity, serviço ou exibir uma notificação ao usuário aqui
		Toast.makeText(context, "Alarme disparado!", Toast.LENGTH_SHORT).show();
	}
}