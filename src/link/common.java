package link;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class common {
	public static void Image(String Isrc,int x,int y,JButton a){
		ImageIcon titleimage = new ImageIcon(Isrc);
		Image Image = titleimage.getImage();
		Image Ismall = Image.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		ImageIcon  Ititle= new ImageIcon(Ismall);
		a.setIcon(Ititle);
	}
	//ͼƬ
	public static void Image(String Isrc,int x,int y,JLabel a){
		ImageIcon titleimage = new ImageIcon(Isrc);
		Image Image = titleimage.getImage();
		Image Ismall = Image.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		ImageIcon  Ititle= new ImageIcon(Ismall);
		a.setIcon(Ititle);
	}
	
	private static int levelnum;
	public static int getLevelnum() {
		return levelnum;
	}
	public static void setLevelnum(int levelnum) {
		common.levelnum = levelnum;
	}
	
	private static boolean isGameOver=false;
	public static boolean isGameOver() {
		return isGameOver;
	}
	public static void setGameOver(boolean isGameOver) {
		common.isGameOver = isGameOver;
	}
	
}
