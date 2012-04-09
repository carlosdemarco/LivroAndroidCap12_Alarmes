package br.livro.android.cap12;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Exemplo de como utilizar um AlarmManager para agendar um BroadcastReceiver
 * para executar daqui a X segundos
 * 
 * @author ricardo
 * 
 */
public class ExemploAgendarAlarme extends Activity {
	private static final String CATEGORIA = "livro";
	private static final int segundos = 5;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		TextView text = new TextView(this);
		text.setText("Alarme agendado para daqui a " + segundos + " segundos.");
		setContentView(text);

		// 5 segundos
		agendar(segundos);
	}

	// Agenda o alarme para executar daqui a X segundos
	private void agendar(int segundos) {
		// Intent para disparar o broadcast
		Intent it = new Intent("EXECUTAR_ALARME");
		PendingIntent p = PendingIntent.getBroadcast(ExemploAgendarAlarme.this, 0, it, 0);
	
		// Para executar o alarme depois de x segundos a partir de agora
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		c.add(Calendar.SECOND, segundos);
	
		// Agenda o alarme
		AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
		long time = c.getTimeInMillis();
		alarme.set(AlarmManager.RTC_WAKEUP, time, p);
	
		Log.i(CATEGORIA, "Alarme agendado para daqui a " + segundos + " segundos.");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i(CATEGORIA, "onDestroy() - alarme cancelado.");
		Intent it = new Intent("EXECUTAR_ALARME");
		PendingIntent p = PendingIntent.getBroadcast(ExemploAgendarAlarme.this, 0, it, 0);

		//Cancela o alarme
		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		am.cancel(p);
	}
}