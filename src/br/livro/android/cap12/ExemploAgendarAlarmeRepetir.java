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
public class ExemploAgendarAlarmeRepetir extends Activity {
	private static final String CATEGORIA = "livro";

	// Tempo para iniciar o alarme na primeira vez
	private static final int segundos = 5;

	// Repetir a cada 10 segundos
	private static final int tempoRepetir = 10 * 1000;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		TextView text = new TextView(this);
		text.setText("Alarme agendado para daqui a " + segundos + " segundos.\nE repetir a cada 10.");
		setContentView(text);

		// 5 segundos
		agendar(segundos);
	}

	// Agenda o alarme para executar daqui a X segundos
	private void agendar(int segundos) {
		// Intent para disparar o BroadcastReceiver 'ReceberAlarme'
		Intent it = new Intent("EXECUTAR_ALARME");
		PendingIntent p = PendingIntent.getBroadcast(ExemploAgendarAlarmeRepetir.this, 0, it, 0);

		// Para executar o alarme depois de 5 segundos a partir de agora
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		c.add(Calendar.SECOND, segundos);

		// Agenda o alarme
		AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
		long time = c.getTimeInMillis();
		// Repetir a cada 10 segundos
		alarme.setRepeating(AlarmManager.RTC_WAKEUP, time, tempoRepetir, p);

		Log.i(CATEGORIA, "Alarme agendado para daqui a " + segundos + " segundos. Repetir a cada " + tempoRepetir);
	}
	@Override
	// Cancela o alarme se sair da tela, ou remova este método se é necessário manter o alarme vivo
	protected void onDestroy() {
		super.onDestroy();
		Log.i(CATEGORIA, "onDestroy() - alarme cancelado.");
		Intent it = new Intent("EXECUTAR_ALARME");
		PendingIntent p = PendingIntent.getBroadcast(ExemploAgendarAlarmeRepetir.this, 0, it, 0);

		//Cancela o alarme
		AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
		am.cancel(p);
	}
}