package src;

public class Song 
{
	public Song(String t, String a, String midi, String mp3)
	{
		title = t;
		artist = a;
		midiFile = midi;
		mp3File = mp3;
	}
	public String getTitle()
	{
		return title;
	}
	public String getArtist()
	{
		return artist;
	}
	public String getMidi()
	{
		return midiFile;
	}
	public String getMp3()
	{
		return mp3File;
	}
	private String title,artist,midiFile,mp3File;
}
