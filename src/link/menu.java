/*
 * 主菜单实现
 */

package link;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import redraw.myJPanel; 

public class menu extends Frame implements ActionListener {
	private JFrame Frame = null;
	private static JFrame mainFrame =null;
	
	public static JFrame getMainFrame() {
		return mainFrame;
	}
	public static void setMainFrame(JFrame mainFrame) {
		menu.mainFrame = mainFrame;
	}
	   
	private Container thisContainer;	//主面板的容器
	private boolean visible = false;//说明标志，类型为bollean
	private myJPanel bgPanel;
	private JButton bgm, set,start;
	private JLabel set_view, bgImage;
	
	@SuppressWarnings("deprecation") 
	void menu() throws MalformedURLException
	{
		if(menu.getMainFrame() == null)
		{
			Frame=new JFrame("呆萌小怪连连看"); //名字
			Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭的按钮
			Frame.setResizable(false);   //固定窗口大小，不能设置最大化最小化
			Frame.setSize(975, 732);
			this.setMainFrame(Frame);
		}else
		{
			Frame=menu.getMainFrame();
		}
	   thisContainer=Frame.getContentPane(); //获取容器
	   thisContainer.setBackground(new Color(250,246,180)); //设置容器背景颜色
	   thisContainer.setLayout(null);
	   
	   //主界面背景
	   bgPanel=new myJPanel();
	   bgPanel.setImg("img/ui/menu_bg.png");
	   bgPanel.setBounds(0, 0, 975, 732);
	   bgPanel.setLayout(null);	   
	   
	   //背景音乐按钮
	   bgm=new JButton();
	   bgm.setContentAreaFilled(false);  //设置按钮透明
	   common.Image("img/ui/ui_01.png",50,50,bgm);
	   bgm.setBounds(30, 620, 50, 50);
	   bgm.addActionListener(this);
	   bgm.setBorderPainted(false);
	   
	   //说明按钮
	   set = new JButton();
	   set.setContentAreaFilled(false);  //设置按钮透明
	   set.setBounds(885, 620, 50, 50);
	   common.Image ("img/ui/ui_04.png",50,50,set);
	   set.addActionListener(this);
	   set.setBorderPainted(false);

	   //操作说明
	   set_view = new JLabel();
	   set_view.setBounds(210, 90, 530, 500);
	   common.Image ("img/ui/ui_05.png", 530, 500,set_view);
	   set_view.setVisible(false);
	   
	   //logo显示
	   start = new JButton();
	   start.setContentAreaFilled(false);  //设置按钮透明
	   start.setBounds(265, 203, 400, 280);
	   common.Image ("img/ui/startplay.png",400,280,start);
	   start.addActionListener(this);
	   start.setBorderPainted(false);
	   
	   thisContainer.add(bgm);
	   thisContainer.add(set);
	   thisContainer.add(set_view);
	   thisContainer.add(start);
	   thisContainer.add(bgPanel);
		
	   Frame.setVisible(true);	
	}
	
	//事件
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//音乐开关
		//问题：1.音乐暂停过慢，不及时。在music.java里进行修改
		if(e.getSource() == bgm)
		{
			try {
				if(music.isPlay())
				{
					common.Image("img/ui/ui_01.png",50,50,bgm);
					music.music();
					music.setPlay(false);
				}
				else
				{
					common.Image("img/ui/ui_02.png",50,50,bgm);
					music.music();
					music.setPlay(true);
				}
			
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		//说明显示
		if(e.getSource() == set){
			if(visible)
			{
				set_view.setVisible(false);
				visible = false;
			}
			else {
				set_view.setVisible(true);
				visible = true;
			}
		}
		
		//开始游戏
		if(e.getSource() == start){
			thisContainer.removeAll();
			thisContainer.repaint();
			common.setLevelnum(1);
			new level().level();
		}
		
	}
}

