package src;

import java.util.ArrayList;

public class MidiParseTest {

	public static void main(String[] args) {
		
//<<<<<<< .mine
		MidiParser mp = new MidiParser("Data/Songs/MikeTysonR.mid");
		ArrayList<Key> theNotes = mp.parse(0);		
//=======
		//MidiParser mp = new MidiParser("Data/Songs/ThanksgivingGod.mid");
		//ArrayList<Key> theNotes = mp.parse(0);		
//>>>>>>> .r15
		
		for (Key n : theNotes) {
			System.out.println("Start: "+n.getStart()+" Duration: "+ n.getDuration()+ " Tone: "+n.getTone());
		}
		
	}
	
}
