package com.example.matriculasaluno;

import java.io.Serializable;

import com.example.matriculasaluno.dao.*;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver implements Serializable{

	@Override
	public void onReceive(Context context, Intent intent) {
		//wifi tel sms bateria
		Object[] msg = (Object[]) intent.getExtras().get("pdus");
		
		byte[] primeira = (byte[]) msg[0];
		
		SmsMessage sms = SmsMessage.createFromPdu(primeira);
		String telefone = sms.getDisplayOriginatingAddress();
		
		AlunoDAO dao = new AlunoDAO(context);
		
		boolean ehAluno = dao.isAluno(telefone);
		dao.close();
		
		if(ehAluno){
			MediaPlayer player = MediaPlayer.create(context, R.raw.aluno);
			player.start();
			
			Toast.makeText(context,"Tocando musica...", Toast.LENGTH_LONG).show();
		}
		
		
	}

}
