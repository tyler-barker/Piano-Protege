package src;

/**
 * MidiParser.java
 * @author Benjamin Eckel
 * 
 * Usage:
 * 		Create and instance and call parse() to get the ArrayList of notes.
 * 
 * Updates: 
 * 		Currently, trackNumber has no significance and tickToMS has not been 
 * 		completely verified as accurate. It does yeild coherent results though.
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.ShortMessage;

public class MidiParser {
	
	private static DumpReceiver sm_receiver;
	private File midiFile;
	private float tempo;      // monitors global tempo changes
	private float resolution; // ticks per beat
	
	/**
	 * Constructor. Give is a midi file to parse and a track number.
	 * @param filePath
	 * @param _trackNumber
	 */
	public MidiParser(String filePath) {
		sm_receiver = new DumpReceiver(System.out, false);
		midiFile = new File(filePath);
		tempo = 0f;
	}
	
	/**
	 * Use this function to parse the file and get the ArrayList
	 * of notes. 
	 * 
	 * @param targetChannel the channel (hand) you want to parse.
	 * @return ArrayList<Note> the notes that were parsed.
	 */
	public ArrayList<Key> parse(int targetChannel) {
		Sequence sequence = null;
		try {
			sequence = MidiSystem.getSequence(midiFile);
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (sequence == null) {
			out("Cannot retrieve Sequence.");
		} else {
			
			ArrayList<Key> finishedNotes = new ArrayList<Key>();
			ArrayList<Key> openNotes = new ArrayList<Key>();
		
			resolution = sequence.getResolution();
			out("Resolution: " + resolution + " ticks per beat");
			
			Track track = sequence.getTracks()[0];
			for (int nEvent = 0; nEvent < track.size(); nEvent++) {
				MidiEvent event = track.get(nEvent);
				String strMessage = sm_receiver.messageToString(event.getMessage());
				if (strMessage.startsWith("Set Tempo")) {
					tempo = Float.valueOf(strMessage.substring(strMessage.indexOf(":") + 1));
					out("Tempo set at: "+String.valueOf(tempo)); // for debugging
				} else if ((strMessage.startsWith("note On:") || strMessage.startsWith("note Off:")) && (((ShortMessage)event.getMessage()).getChannel() == targetChannel)) {
					long tick = event.getTick();
					String noteName = strMessage.substring(strMessage.indexOf(":")+1, strMessage.lastIndexOf(":"));
					int velocity = Integer.valueOf(strMessage.substring(strMessage.lastIndexOf(":") + 1));
					if (strMessage.startsWith("note On:") && velocity > 0) {
						openNotes.add(new Key(noteName, tickToMS(tick), 0)); // give it a duration of 0 for now
					} else if (velocity == 0 || strMessage.startsWith("note Off:")) { // close the note and put it in finishedNotes						
						for (int i = 0; i < openNotes.size(); i++) {
							Key n = openNotes.get(i);
							if (n.getTone().equals(noteName)) {
								finishedNotes.add(new Key(n.getTone(), n.getStart(), (int)(tickToMS(tick) - n.getStart())));
								openNotes.remove(i);
							}
						}
					} else {
						out("Why is velocity negative?");
					}
				}
			}
			return finishedNotes;
		}
		return new ArrayList<Key>(); 
	}
	
	/**
	 * Convert from midi tick position to millisecond time stamp.
	 *  
	 * @param tick Midi tick position
	 * @return long millisecond tim stamp.
	 */
	private long tickToMS(long tick) {
		//float msPerBeat = (tempo * 1000) / 60; 
		float msPerBeat = 60000/tempo;
		float msPerTick = msPerBeat / (float) resolution; 
		return (long) (tick * msPerTick);
	}
	
	/**
	 * Convenience function for debugging.	
	 * 
	 * @param strMessage
	 */
	private static void out(String strMessage) {
		System.out.println(strMessage);
	}
	
	/**
	 * Another convenience function to print out a MidiEvent in
	 * human readable format.
	 * 
	 * @param event MidiEvent to be printed.  
	 */
	public static void output(MidiEvent event) {
		MidiMessage message = event.getMessage();
		long lTicks = event.getTick();
		sm_receiver.send(message, lTicks);
	}
}

