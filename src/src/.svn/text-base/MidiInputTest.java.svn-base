package src;

import processing.core.*;
import rwmidi.*;

public class MidiInputTest extends PApplet 
{
	MidiInput input;
	MidiOutput output;

	public void setup() {
		size(200,200);
	  input = RWMidi.getInputDevices()[0].createInput(this);
	  output = RWMidi.getOutputDevices()[0].createOutput();
	}

	public void noteOnReceived(Note note) {
	  println("note on " + note.getPitch());
	}

	public void sysexReceived(rwmidi.SysexMessage msg) {
	  println("sysex " + msg);
	}
	public void mousePressed() {
		  int ret =    output.sendNoteOn(0, 3, 3);
		  ret = output.sendSysex(new byte[] {(byte)0xF0, 1, 2, 3, 4, (byte)0xF7});
		}

	public void draw()
	{
		
	}
}
