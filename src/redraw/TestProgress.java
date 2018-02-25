package redraw;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JProgressBar;

public class TestProgress extends JProgressBar{
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		ImageIcon image=new ImageIcon("img/ui/time2-01.png"); //鑾峰彇鍥惧儚
		image.setImage(image.getImage().getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_FAST)); //璋冩暣鍥惧儚鐨勫垎杈ㄧ巼浠ラ�傚簲瀹瑰櫒
		image.paintIcon(this, g,0, 0);
	}
}

