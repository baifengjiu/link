/*
 * ���˵�ʵ��
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
	   
	private Container thisContainer;	//����������
	private boolean visible = false;//˵����־������Ϊbollean
	private myJPanel bgPanel;
	private JButton bgm, set,start;
	private JLabel set_view, bgImage;
	
	@SuppressWarnings("deprecation") 
	void menu() throws MalformedURLException
	{
		if(menu.getMainFrame() == null)
		{
			Frame=new JFrame("����С��������"); //����
			Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //�رյİ�ť
			Frame.setResizable(false);   //�̶����ڴ�С���������������С��
			Frame.setSize(975, 732);
			this.setMainFrame(Frame);
		}else
		{
			Frame=menu.getMainFrame();
		}
	   thisContainer=Frame.getContentPane(); //��ȡ����
	   thisContainer.setBackground(new Color(250,246,180)); //��������������ɫ
	   thisContainer.setLayout(null);
	   
	   //�����汳��
	   bgPanel=new myJPanel();
	   bgPanel.setImg("img/ui/menu_bg.png");
	   bgPanel.setBounds(0, 0, 975, 732);
	   bgPanel.setLayout(null);	   
	   
	   //�������ְ�ť
	   bgm=new JButton();
	   bgm.setContentAreaFilled(false);  //���ð�ť͸��
	   common.Image("img/ui/ui_01.png",50,50,bgm);
	   bgm.setBounds(30, 620, 50, 50);
	   bgm.addActionListener(this);
	   bgm.setBorderPainted(false);
	   
	   //˵����ť
	   set = new JButton();
	   set.setContentAreaFilled(false);  //���ð�ť͸��
	   set.setBounds(885, 620, 50, 50);
	   common.Image ("img/ui/ui_04.png",50,50,set);
	   set.addActionListener(this);
	   set.setBorderPainted(false);

	   //����˵��
	   set_view = new JLabel();
	   set_view.setBounds(210, 90, 530, 500);
	   common.Image ("img/ui/ui_05.png", 530, 500,set_view);
	   set_view.setVisible(false);
	   
	   //logo��ʾ
	   start = new JButton();
	   start.setContentAreaFilled(false);  //���ð�ť͸��
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
	
	//�¼�
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//���ֿ���
		//���⣺1.������ͣ����������ʱ����music.java������޸�
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
		
		//˵����ʾ
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
		
		//��ʼ��Ϸ
		if(e.getSource() == start){
			thisContainer.removeAll();
			thisContainer.repaint();
			common.setLevelnum(1);
			new level().level();
		}
		
	}
}

