package redraw;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

//设置主页背景图片的JPnel类  
public class myJPanel extends JPanel {  
	private String img;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		ImageIcon image=new ImageIcon(img);
		image.setImage(image.getImage().getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_FAST)); //璋冩暣鍥惧儚鐨勫垎杈ㄧ巼浠ラ�傚簲瀹瑰櫒
		image.paintIcon(this, g,0, 0);
	}
}
