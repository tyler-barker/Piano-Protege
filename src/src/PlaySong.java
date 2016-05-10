package src;

import processing.core.PApplet;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;



public class PlaySong extends PApplet
{
	private AudioPlayer groove;
	private Minim minim;
	public void setup(){
	minim = new Minim(this);
	groove = minim.loadFile("loz.wav",2048);
	//while(millis()-startTime<5000){}
	groove.play(0);
	}
	public void draw(){}
}
