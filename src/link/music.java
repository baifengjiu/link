package link;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.swing.JFrame;

public class music {
	private static File f;
	private static URL url;
	private static AudioClip ac = null;
	private static boolean isPlay;//背景音乐标志，类型为boolean
	
	public static boolean isPlay() {
		return isPlay;
	}

	public static void setPlay(boolean isPlay) {
		music.isPlay = isPlay;
	}

	public static void music() throws MalformedURLException{
		// TODO Auto-generated method stub		  
		//解析地址
		f = new File("img/music/bgm.wav");
		url = f.toURI().toURL();
		ac = Applet.newAudioClip(url);
		if(isPlay){
			ac.loop();
			 System.out.println(isPlay);
			 isPlay = false;
		}else{
			ac.stop(); 
			 System.out.println(isPlay);
			 isPlay =true;
		}
	}
	
	public static void clikEffect() throws MalformedURLException{
		f = new File("img/music/clikad.wav");
		url = f.toURI().toURL();
		ac = Applet.newAudioClip(url);
		ac.play(); 
	}
	public static void clearEffect() throws MalformedURLException{
		f = new File("img/music/clearad.wav");
		url = f.toURI().toURL();
		ac = Applet.newAudioClip(url);
		ac.play(); 
	}
}