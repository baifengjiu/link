/*
 * ѡ��ʵ��
 */
package link;

import java.awt.Color;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import redraw.myJPanel;

public class level extends Frame implements ActionListener {
	private JFrame mainFrame;
	private Container thisContainer;	//����������
	private JLabel set_view;
	private JButton back;
	private JButton[] levelName={new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton()};
	private myJPanel bgPanel;
	private static boolean isPlay = true;//�������ֱ�־������Ϊboolean
	private int level;
	private final int increment = 140;	//����,�ؿ���x���������
	
	public void level()
	{
		//����壬���
		mainFrame=menu.getMainFrame();
		thisContainer=mainFrame.getContentPane(); //��ȡ����
		thisContainer.setLayout(null);

		level = common.getLevelnum();	//����ͨ����գ����н�����
		
		//����ͼƬ
		bgPanel=new myJPanel();
		bgPanel.setLayout(null);
		bgPanel.setBounds(0, 0, 975, 732);
		bgPanel.setImg("img/level/gbg.png");
		
		//С����
		set_view = new JLabel();
		set_view.setBounds(40, 4, 900, 720);
		common.Image("img/level/level_02.png",900,720,set_view);
		
		//���ذ�ť
		back = new JButton();
		back.setBounds(715, 70, 50,50);
		common.Image("img/level/back.png",50,50,back);
		back.setContentAreaFilled(false);
		back.addActionListener(this);
		back.setBorderPainted(false);
		
		//�ؿ���ť
		for(int i = 0; i < 6; ++i)
		{
			if(i < level){
				System.out.println("img/level/"+(i+1)+".png");
				System.out.println(levelName[i]);
				common.Image("img/level/"+(i+1)+".png", 125, 125, levelName[i]);
				levelName[i].addActionListener(this);
			}
			else{
				common.Image("img/level/gg.png",125, 125,levelName[i]);
			}
			
			if(i < levelName.length/2)
			{
				levelName[i].setBounds(280+i*increment, 180, 125, 125);
			}
			else
			{
				levelName[i].setBounds(280+ ((i-3) * increment), 350, 125, 125);
			}
			levelName[i].setBorderPainted(false);
			levelName[i].setContentAreaFilled(false);
			thisContainer.add(levelName[i]);
		}

		thisContainer.add(back);
		thisContainer.add(set_view);
		thisContainer.add(bgPanel);
		
		mainFrame.setVisible(true);	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//���ذ�ť
		if(e.getSource() == back){
			thisContainer.removeAll();
			thisContainer.repaint();
			try {
				new menu().menu();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		//�ؿ���ť
		for(int i = 0; i < 6; ++i)
		{
			if(e.getSource() == levelName[i])
			{
				thisContainer.removeAll();
				thisContainer.repaint();
				common.setLevelnum(i+1);
				new link().startGame();
			}
		}
		
		
	}
}