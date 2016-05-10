package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;


public class ScoreBoard 
{
	File file = new File("Songs");
	FileInputStream fis = null;
    BufferedInputStream bis = null;
	DataInputStream dis = null;
	//private int[] rankings = {1,2,3,4,5,6,7,8,9,10,11};
	private ArrayList<Song> songlist = new ArrayList<Song>();
	private String[][] board; 
	private boolean full = false;
	public ScoreBoard()
	{
		fillSongList();
		board = new String[songlist.size()][12];
		for(int i=0;i<songlist.size();i++)
		{
			board[i][0] = songlist.get(i).getTitle();
			for(int j=1;j<=11;j++)
			{
			 try{
				 fis = new FileInputStream(file);
				 // Here BufferedInputStream is added for fast reading.
				 bis = new BufferedInputStream(fis);
				 dis = new DataInputStream(bis);
				 // dis.available() returns 0 if the file does not have more lines.
				 if(full == false)
						 while (dis.available() != 0 && dis.readLine()!=""+i+")"){}
				 full = true;
				 board[i][j] = dis.readLine();
				 // dispose all the resources after using them.
				 fis.close();
				 bis.close();
				 dis.close();
			 } catch (FileNotFoundException e) {
			      e.printStackTrace();
			    } catch (IOException e) {
			      e.printStackTrace();
			    }
			}
			full = false;
		}
	}

	public String getScore(int j,int i)
	{
		return board[j][i];
	}
	
	public String getTitle(int i)
	{
		return board[i][0];
	}
	
	public void addScore(String score, String title)
	{
		int b=0,j=0,k=0;
		String temp;
        BufferedWriter bufferedwriter = null;
        PrintWriter printwriter = null;
        while(songlist.get(b).getTitle()!= title){b++;}
        if(b>songlist.size())
        	return;
        board[b][11] = score;
		for(k=11;k>0;k--)
		{
			for(int l=k-1;l>0;l--)
			{
				if(board[b][k].compareTo(board[b][l])>0)
				{
					temp = board[b][k];
					board[b][k]=board[b][l];
					board[b][l]=temp;
				}
		   }
		}
		for(k=0;k<songlist.size();k++)
		{
			try{
			bufferedwriter = new BufferedWriter(new FileWriter("C:\\Documents and Settings\\Administrator\\Desktop\\piano\\Data\\Songs\\Score.txt"));
	        printwriter = new PrintWriter(bufferedwriter);
	        printwriter.println((k+1)+")");
			for(j=0;j<12;j++)
			{
			          printwriter.println(board[k][j]);		     
			}
			}
			catch(IOException ex)
			{
			     ex.printStackTrace();
			}
			finally
			{
			    printwriter.close();}
		}
	}
	
	public void fillSongList()
	{
		songlist.add(new Song("Legend of Zelda","Benjamin",
				"loz.mid","loz.wav"));
		//songlist.add(new Song("Moonlight Sonata","Beethoven",
			//	"MoonlightSonataRightHand.mid","MoonlightSonata.mp3"));
		songlist.add(new Song("Thanksgiving","George Winston",
				"ThanksgivingR.mid","Thanksgiving.mp3"));
		songlist.add(new Song("Super Mario Bros. Theme","",
				"SMB.mid","SMB.mp3"));
	}
}
