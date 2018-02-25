/*
 * 创建游戏，并实现消除
 */
package link;

import javax.swing.*;
import javax.swing.event.*;

import redraw.TestProgress;
import redraw.myJPanel;

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.lang.Thread.State;
import java.net.MalformedURLException;
import java.sql.Time;
import java.util.Stack;

import javax.imageio.*;
import javax.print.DocFlavor.URL;

public class link extends JFrame implements ActionListener{
	private Stack<JButton> stack = new Stack<JButton>();
	private JFrame mainFrame;	//主面板
	private Container thisContainer;	//主面板的容器
	private JPanel leftPanel,rightTPanel,rightCPanel,rightBPanel,primary,buttonPanel; //小模块
	private JLabel title; //标签
	//消除图片的按钮
	private JButton buttons[][]={
			{new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton()},
			{new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton()},
			{new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton()},
			{new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton()},
			{new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton()},
			{new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton()},
			{new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton()},
			{new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton()},
			{new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton()},
			{new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton(),new JButton()}
	};
	private JButton reset,newly,start,back_1,back_2,next,reStart;//开始按钮，重置按钮，再来一局
	private static boolean pause=true; //判断是否暂停,游戏是否结束
	private JLabel scorebar;
	private myJPanel scorebarBg;
	private JProgressBar progress; // 进度条
	private int x=-1,y=-1,x0=-1,y0=-1,again,totol=320; //进度条的进度和字的初始设置
	private String frist,second;
	private Timer timer;  //定时器系统自带
	private JLabel bg,end,score,diead,levelText;
	private JTextArea text;
	private int levelnum;
	private int progressValue =0;
	private String flag=null;
	private int hard = 0;
	
	//初始化，程序的真正入口
	public void startGame(){
		mainFrame = menu.getMainFrame();
		
		thisContainer=mainFrame.getContentPane(); //获取容器
		thisContainer.setLayout(null);
		
		 bg = new JLabel();
		 bg.setBounds(-4, 0, 975, 732);
		 common.Image("img/button/bg.png",975,706,bg);
		
		//左边黑色部分
		leftPanel=new JPanel();
		leftPanel.setOpaque(false);
		leftPanel.setLayout(null);
		leftPanel.setBounds(5, 5, 400, 732); //设置窗口大小
		
		title=new JLabel();
		title.setBounds(10, 30, 400, 200);
		common.Image("img/button/title.png",300, 200,title);
		
		start=new JButton();
		start.setContentAreaFilled(false);  //设置按钮透明
		common.Image("img/button/button5.png",150, 70,start);
		start.setBounds(30, 300, 150, 70);
		start.addActionListener(this);
		start.setBorderPainted(false); //设置边框，鼠标移动上去不显示
		
		reset=new JButton();
		reset.setContentAreaFilled(false);  //设置按钮透明
		common.Image("img/button/button3.png",150, 70,reset);
		reset.setBounds(80, 400, 150, 70);
		reset.addActionListener(this);
		reset.setBorderPainted(false);
		
		newly=new JButton();
		newly.setContentAreaFilled(false);  //设置按钮透明
		common.Image("img/button/button2.png",150, 70,newly);
		newly.setBounds(120, 500, 150, 70);
		newly.addActionListener(this);
		newly.setBorderPainted(false);
		
		//左下角的返回按钮
		back_1=new JButton();
		back_1.setContentAreaFilled(false);  //设置按钮透明
		common.Image("img/level/back.png",60, 60,back_1);
		back_1.setBounds(16,630, 60, 60);
		back_1.addActionListener(this);
		back_1.setBorderPainted(false);
		
		leftPanel.add(back_1);
		leftPanel.add(title);
		leftPanel.add(start);
		leftPanel.add(reset);
		leftPanel.add(newly);
		
		//背景面板
		rightTPanel=new JPanel();
		rightTPanel.setOpaque(false);
		rightTPanel.setBounds(318, 70, 620, 620);
		
		//放连连看的图片按钮的面板
		buttonPanel=new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(null);
		buttonPanel.setBounds(316, 50, 620, 620);
		
		levelnum = common.getLevelnum();
		switch(levelnum)
		{
			case 1:progressValue=1000;hard = 6*levelnum; break;
			case 2:progressValue=900;hard = 6*levelnum;break;
			case 3:progressValue=800;hard = 6*levelnum;break;
			case 4:progressValue=700;hard = 6*levelnum;break;
			case 5:progressValue=600;hard = 6*levelnum;break;
			case 6:progressValue=500;hard = 6*levelnum;break;
		}
		
		progress = new JProgressBar();			// 实例化进度条
		progress.setMinimum(0);
		progress.setMaximum(progressValue);
        progress.setStringPainted(true);      			// 描绘文字    
        progress.setBorderPainted(false);				// 进度条不绘制其边框
        progress.setForeground(new Color(242,70,70));	// 设置进度条的前景色
        progress.setBackground(Color.white); 			// 设置背景色
        progress.setBounds(375, 638, 500, 30);
        timer = new Timer(100, this);
        
        scorebar = new JLabel();
        scorebar.setBounds(490, 40, 180, 40);
		scorebar.setFont(new Font("宋体",Font.BOLD, 16));//设置字体
		
		scorebarBg =new myJPanel();
		scorebarBg.setImg("img/ui/grade.png");
		scorebarBg.setOpaque(false);
		scorebarBg.setLayout(null);
		scorebarBg.setBounds(390, 40, 200, 60);//分数图片位置

		//通关标题
		levelText = new JLabel();
		levelText.setBounds(570,130, 410, 100);
		levelText.setFont(new java.awt.Font("font/font.ttf",1,40));
		levelText.setForeground(Color.WHITE);
		levelText.setVisible(false);			
		
		//游戏字
		text = new JTextArea();
		text.setOpaque(false);  //设置按钮透明
		text.setEditable(false);
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setFont(new java.awt.Font("font/font.ttf",1,40));
		text.setVisible(false);
		
		//游戏通关
		end = new JLabel();
		end.setBounds(430,150, 410, 350);
		common.Image("img/button/pass.png",410, 350,end);
		end.setVisible(false);
			
		//游戏失败
		diead = new JLabel();
		diead.setBounds(430,150, 410, 350);
		common.Image("img/button/nopass.png",410, 350,diead);
		diead.setVisible(false);
		
		//下一关
		next=new JButton();
		next.setContentAreaFilled(false);  //设置按钮透明
		common.Image("img/level/next.png",80,80,next);
		next.setBounds(700, 450, 80,80);
		next.addActionListener(this);
		next.setBorderPainted(false);
		next.setVisible(false);
		
		reStart=new JButton();
		reStart.setContentAreaFilled(false);  //设置按钮透明
		common.Image("img/level/next.png",80,80,reStart);
		reStart.setBounds(700, 450, 80,80);
		reStart.addActionListener(this);
		reStart.setBorderPainted(false);
		reStart.setVisible(false);
		
		//游戏通关和结束后的返回按钮
		back_2=new JButton();
		back_2.setContentAreaFilled(false);  //设置按钮透明
		common.Image("img/level/back.png",80,80,back_2);
		back_2.setBounds(480,450,80,80);
		back_2.addActionListener(this);
		back_2.setBorderPainted(false);
		back_2.setVisible(false);

		//生成图片
		make();		

		thisContainer.add(progress);        
        thisContainer.add(text);
        thisContainer.add(next);
        thisContainer.add(levelText);
        thisContainer.add(reStart);
		thisContainer.add(back_2);
        thisContainer.add(end);;
        thisContainer.add(diead);
        thisContainer.add(buttonPanel);;
		thisContainer.add(leftPanel);
		thisContainer.add(rightTPanel);
		thisContainer.add(scorebar);
		thisContainer.add(scorebarBg);
		thisContainer.add(bg);
		
		mainFrame.setVisible(true);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//开始、暂停按钮
		if (e.getSource() == start){
			resumeGame();
		}
		
		//进度条
	    if (e.getSource() == timer) {
	    	int value = progress.getValue();
	    	if (value > 0){
	    		progress.setValue(--value);
	    		progress.setString(value--+"/"+progressValue);
	    		if(isEnd()){
	    			common.setGameOver(true);
	    			back_2.setVisible(true);
    				end.setVisible(true);
    				next.setVisible(true);

	    			levelText.setText("Level "+ levelnum +"！");
	    			levelText.setVisible(true);
    				
	    			if(levelnum == 6){
	    				text.setBounds(490, 230, 330, 250);
		    			text.setText("恭喜你已全部通关，环保小怪可以去保护环境咯！");
		    			text.setVisible(true);
	    			}else{
	    				text.setBounds(550, 300, 280, 80);
		    			text.setText("恭喜通关！");
		    			text.setVisible(true);
		    			flag = "ok";
	    			}

	    			pause = true;
	    			resumeGame();
	    		}
	    	}
	    	else {
	    		timer.stop();
	    		
	    		if(!isEnd()){
	    			common.setGameOver(true);
	    			diead.setVisible(true);
	    			back_2.setVisible(true);
	    			reStart.setVisible(true);

	    			levelText.setText("Level "+ levelnum +"！");
	    			levelText.setVisible(true);

    				text.setBounds(550, 300, 280, 80);
	    			text.setText("游戏失败！");
	    			text.setVisible(true);
	    			progress.setValue(progressValue);
	    			make();
	    		}
	    		
				pause = true;
				resumeGame();
	    		
	    		for(int i=1;i<buttons[0].length-1;i++){
		    		for(int j=1;j<buttons.length-1;j++){
		    			buttons[i][j].setEnabled(false);
		    		}
		    	}
	    	}
	    }
	    //返回到关卡
	    if(e.getSource() == back_1 || e.getSource() == back_2){
			thisContainer.removeAll();
			thisContainer.repaint();
			common.setGameOver(false);
			if(levelnum == 6){
				common.setLevelnum(6);
			}else{
				if(flag == "ok"){
					common.setLevelnum(levelnum+1);//关卡数加1，进行下一关解锁。
					flag="null";
				}else{
					common.setLevelnum(levelnum);
				}
			}
			new level().level();
		}
	    
		if(e.getSource() == next){
			//进行下一关
			System.out.println("=============next:被点击=================");
			if(levelnum == 6){
				thisContainer.removeAll();
				thisContainer.repaint();
				common.setLevelnum(6);
    			common.setGameOver(false);
				new level().level();
				flag="null";
			}else{
				common.setLevelnum(levelnum+1); //关卡数加1，进行下一关解锁。
				flag="null";
    			common.setGameOver(true);
    			pause = true;
    			back_2.setVisible(false);
    			next.setVisible(false);
    			end.setVisible(false);
    			text.setVisible(false);
    			levelText.setVisible(false);
    			resumeGame();
    			make();
			}
		}
		
	    //重置按钮
	    if(e.getSource() == reset){
	    	int v = progress.getValue();
	    	int s =Integer.parseInt(scorebar.getText());
	    	if(s>1){
		    	//记录
		    	int k=-1,h=0;
		    	for(int i=1;i<buttons[0].length-1;i++){
		    		for(int j=1;j<buttons.length-1;j++)
		    		{
		    			String ll=buttons[i][j].getName();
		    			if(ll != "0"){
		    				++h;
		    			}
		    		}
		    	}
		    	int res[] = new int[h];
		    	for(int i=1;i<buttons[0].length-1;i++){
		    		for(int j=1;j<buttons.length-1;j++)
		    		{
		    			String ll=buttons[i][j].getName();
		    			if(ll != "0"){
		    				res[++k]=Integer.parseInt(ll);
		    				buttons[i][j].setName(-1+"");
		    			}
		    		}
		    	}
		    	//放
		    	while(k>=0){
					int i=(int)(Math.random()*(buttons[0].length-2))+1;
					int j=(int)(Math.random()*(buttons[0].length-2))+1;
					while(buttons[i][j].getName() != "-1"){
						i=(int)(Math.random()*(buttons[0].length-2))+1;
						j=(int)(Math.random()*(buttons[0].length-2))+1;
					}
					common.Image("img/monster/image"+res[k]+".png",62,62,buttons[i][j]);
	    			buttons[i][j].setName(""+res[k]);
	    			buttons[i][j].setBounds(i*62,j*62,62,62);
	    			buttons[i][j].setBackground(Color.white);
	    			buttons[i][j].setBorderPainted(false);
	    			buttonPanel.add(buttons[i][j]);
		    		k--;
		    	}
		    	progress.setValue(v-10);
		    	progress.setString(v-10+"/"+1000);
		    	scorebar.setText(s-10+"");
	    	}else{
	    		JOptionPane.showMessageDialog(thisContainer, "你没有达到交换的条件！");
	    	}
	    }
	    //重新开始，再来一局
	    if(e.getSource() == newly ||e.getSource() == reStart){
			pause=true;
			resumeGame();
			common.setGameOver(false);
			make();
	    }
	    
	    //是否消除图片 并判断是否得分
	    for(int i=0;i<buttons[0].length;i++){
    		for(int j=0;j<buttons.length;j++)
    		{
    			if(e.getSource()==buttons[i][j]){
    				if(x==-1&&y==-1){
    					frist=buttons[i][j].getName();
        				System.out.println(buttons[i][j].getName());
    					x=i;
    					y=j;
    				}else{
    					second=buttons[i][j].getName();
        				System.out.println(buttons[i][j].getName());
    					x0=i;
    					y0=j;
    				}

					if(stack.size()<2){
						System.out.println("stack.size():"+stack.size());
						try {
							music.clikEffect();
						} catch (MalformedURLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if(!stack.isEmpty() && stack.lastElement().equals((JButton)e.getSource())){
							
	        				//System.out.println("清除所有buttons[i][j].getName():"+buttons[i][j].getName());
							x=-1;
							y=-1;
							stack.lastElement().setBorderPainted(false);
							stack.pop();
						}						
						else{
							
	        				//System.out.println("添加buttons[i][j].getName()"+buttons[i][j].getName());
							stack.push((JButton)e.getSource());
							stack.lastElement().setBorderPainted(true);
						}
					}
					if(stack.size()==2)	{
						if(check(x,y,x0,y0,frist,second)){
							buttons[x][y].setName(0+"");
							buttons[x0][y0].setName(0+"");
							x=-1;y=-1;x0=-1;y0=-1;
							stack.elementAt(0).setVisible(false);
							stack.elementAt(1).setVisible(false);
							stack.clear();
							try {
								music.clearEffect();
							} catch (MalformedURLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
	        				System.out.println("清除buttons");
							int score = Integer.parseInt(scorebar.getText());
							if(score==1)
								score+=9;
							else
								score+=10;
							if(score<totol+1){
								scorebar.setText(score+"");
		    				}
						}
						else{
							x0=-1;
							y0=-1;
							stack.lastElement().setBorderPainted(false);
							stack.pop();
						}
					}
				}
    		}
    	}
	}

	private void make(){
		levelnum = common.getLevelnum();
		switch(levelnum)
		{
			case 1:progressValue=1000;hard = 6*levelnum;break;
			case 2:progressValue=900;hard = 6*levelnum;break;
			case 3:progressValue=800;hard = 6*levelnum;break;
			case 4:progressValue=700;hard = 6*levelnum;break;
			case 5:progressValue=600;hard = 6*levelnum;break;
			case 6:progressValue=500;hard = 6*levelnum;break;
		}
		progress.setValue(progressValue);
		progress.setString(progressValue+"/"+progressValue);
		scorebar.setText(0+"");
		
		for(int i=0;i<buttons[0].length;i++){
			for(int j=0;j<buttons.length;j++)
			{
				buttons[i][j].setName(0+"");
			}
		}
		
		int len=((buttons[0].length-2)*(buttons.length-2))/2;
		for(int m=0;m<len;m++){
			int rand = (int)(Math.random()*hard)+1; // 1-25
			for(int k=0;k<2;k++){
				int i=(int)(Math.random()*(buttons[0].length-2))+1;
				int j=(int)(Math.random()*(buttons[0].length-2))+1;
				while(buttons[i][j].getName() != "0"){
					i=(int)(Math.random()*(buttons[0].length-2))+1;
					j=(int)(Math.random()*(buttons[0].length-2))+1;
				}
				common.Image("img/monster/image"+rand+".png",62,62,buttons[i][j]);
				buttons[i][j].setName(""+rand);
				buttons[i][j].setBounds(i*62,j*62,62,62);
				buttons[i][j].setVisible(true);
				buttons[i][j].setContentAreaFilled(false);
				buttons[i][j].setBorderPainted(false);
				
				System.out.println(common.isGameOver());
				if(!common.isGameOver())
				{
					buttons[i][j].addActionListener(this);     //监听按钮
				}
				buttons[i][j].setEnabled(false);
				buttonPanel.add(buttons[i][j]);
			}
		}
	}
	
	void resumeGame()
	{
		if(pause){
			//暂停之后
			common.Image("img/button/button5.png",150, 70,start);
			timer.stop();
			for(int i=1;i<buttons[0].length-1;i++){
	    		for(int j=1;j<buttons.length-1;j++){
	    			buttons[i][j].setEnabled(false); //不可点击
	    		}
	    	}
			pause=false;
		}else{
			//开始游戏
			common.Image("img/button/button1.png",150, 70,start);			
			timer.start();
			for(int i=1;i<buttons[0].length-1;i++){
	    		for(int j=1;j<buttons.length-1;j++){
	    			buttons[i][j].setEnabled(true); //可以点击
	    		}
	    	}
			System.out.println("pause:"+pause);
			pause=true;
		}
	}
		
	//游戏是否结束
	private boolean isEnd(){
		boolean flag4=false;
		int k=0;
		for(int i=1;i<buttons[0].length-1;i++){
    		for(int j=1;j<buttons.length-1;j++)
    		{
    			if(buttons[i][j].getName()=="0")
    				k++;
    		}
		}
		//总共64个方格，全部消完代表结束
		if(k==64){   
			flag4=true;
		}
		return flag4;
		
	}
	
	//判断是否有通路
	private boolean line(int x,int y,int x0,int y0){
		// TODO Auto-generated method stub
		boolean flag2=false;
		int a=Math.abs(y-y0)==0?Math.abs(x-x0):Math.abs(y-y0);
		if(a==1){
			//page.drawLine(x, y, x0, y0);
			flag2=true;
		}else{
			if(y==y0){ //x轴
				int b=x<x0?x:x0;
				int c=x>x0?x:x0;
				for(int i=b+1;i<c;i++){
					if(buttons[i][y].getName()=="0"){
						flag2=true;
					}
					else{
						flag2=false;
						break;
					}
				}
			}else{//y轴
				int b=y<y0?y:y0;
				int c=y>y0?y:y0;
				for(int i=b+1;i<c;i++){
					if(buttons[x][i].getName()=="0"){
						flag2=true;
					}
					else{
						flag2=false;
						break;
					}
				}
			}
		}
		return flag2;
	}
	
	private boolean one(int x,int y,int x0,int y0){
		boolean flag1=false;
		int a[]={x,y0};
		int b[]={x0,y};
		
		//判读a(x,y0)点是否有元素
		if(buttons[x][y0].getName()=="0"){
			if(line(x,y0,x,y) && line(x,y0,x0,y0)){
				flag1=true;
			}
		}
		//判读b点是否有元素
		if(buttons[x0][y].getName()=="0"){
			if(line(x0,y,x,y) && line(x0,y,x0,y0)){
				flag1=true;
			}
		}
		return flag1;
	}
	
	private boolean two(int x,int y,int x0,int y0) {
		// TODO Auto-generated method stub
		boolean flag3=false;
		
		//1.垂直方向
		for(int i=0;i<buttons[0].length;i++){
			if(buttons[x][i].getName()=="0" && buttons[x0][i].getName()=="0"){
				if(line(x,i,x0,i)){
					if(line(x,i,x,y) && line(x0,i,x0,y0)){
						flag3=true;
						break;
					}
				}
			}
		}		
		//2.水平方向
		for(int i=0;i<buttons[0].length;i++){
			if(buttons[i][y].getName()=="0" && buttons[i][y0].getName()=="0"){
				if(line(i,y,i,y0)){
					if(line(i,y,x,y) && line(i,y0,x0,y0)){
						flag3=true;
						break;
					}
				}
			}
		}
		return flag3;
	}
	//检查判断能不能消除
	private boolean check(int x,int y,int x0,int y0,String frist,String second){
		boolean flag=false;
		int n1=Integer.parseInt(frist);
		int n2=Integer.parseInt(second);
		if(n1==n2){
			if(x==x0||y==y0){
				flag=line(x, y, x0, y0);
			}
			if(one(x, y, x0, y0)){
				flag=true;				
			}
			if(two(x,y,x0,y0)){
				flag=true;
			}
		}else{			
			flag=false;
		}		
		return flag;
	}
}