package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	URL[] soundURL = new URL[30];
	
	public Sound() {
		soundURL[0] = getClass().getResource("/sound/wind_in_tree.wav");
		soundURL[1] = getClass().getResource("/sound/open_smth.wav");
		soundURL[2] = getClass().getResource("/sound/end_game.wav");
		soundURL[3] = getClass().getResource("/sound/already18.wav");
		soundURL[4] = getClass().getResource("/sound/pick_key.wav");
		soundURL[5] = getClass().getResource("/sound/pick_smth.wav");


	}
	
	
	public void setFile(int i){
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]); 
			clip = AudioSystem.getClip();
			clip.open(ais);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void play(){
		clip.start();
	}
	
	
	public void loop(){
		clip.loop(0);
	}
	
	
	public void stop(){
		clip.stop();
	}
	
}
