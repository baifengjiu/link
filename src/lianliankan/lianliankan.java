package lianliankan;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.lang.Thread.State;
import java.sql.Time;
import java.util.Stack;

import javax.imageio.*;

public class lianliankan extends JFrame implements ActionListener{
	private Stack<JButton> stack = new Stack<JButton>();
	JFrame mainFrame;
	Container thisContainer;
	JPanel leftPanel,rightTPanel,rightCPanel,rightBPanel,primary,buttonPanel;
	JLabel title,label;
	JButton buttons[][]={
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
	JButton reset,newly,start;
	static boolean pause=true;
	private JProgressBar progress,scorebar; // 进度条
	int x=-1,y=-1,x0=-1,y0=-1,again,totol=320;
	String frist,second;
	Timer timer;
  	
	public void init(){
		mainFrame=new JFrame("连连看");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);//固定窗口大小
		mainFrame.setSize(753, 627);
		
		thisContainer=mainFrame.getContentPane();
		thisContainer.setBackground(new Color(250,246,180));
		thisContainer.setLayout(null);
		
		leftPanel=new JPanel();
		leftPanel.setBackground(new Color(250,246,180));
		leftPanel.setBounds(5, 5, 220, 572);
		leftPanel.setLayout(null);
		
		title=new JLabel();
		Image("img/title.png",180, 100,title);
		title.setBounds(10, 60, 200, 200);
		
		start=new JButton();
		Image("img/button4.png",100, 30,start);
		start.setBounds(10, 340, 100, 30);
		start.addActionListener(this);
		start.setBorderPainted(false);
		
		reset=new JButton();
		Image("img/button3.png",100, 30,reset);
		reset.setBounds(80, 390, 100, 30);
		reset.addActionListener(this);
		reset.setBorderPainted(false);
		
		newly=new JButton();
		Image("img/button2.png",100, 30,newly);
		newly.setBounds(120, 440, 100, 30);
		newly.addActionListener(this);
		newly.setBorderPainted(false);
		
		leftPanel.add(title);
		leftPanel.add(start);
		leftPanel.add(reset);
		leftPanel.add(newly);
		
		//背景面板
		rightTPanel=new JPanel();
		rightTPanel.setBackground(Color.white);
		rightTPanel.setBounds(230, 5, 500, 500);
		
		//放按钮的面板
		buttonPanel=new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(null);
		buttonPanel.setBounds(168, -57, 623, 623);
		
		for(int i=0;i<buttons[0].length;i++){
    		for(int j=0;j<buttons.length;j++)
    		{
    			buttons[i][j].setName(0+"");
    		}
		}
		
		int len=((buttons[0].length-2)*(buttons.length-2))/2;
		for(int m=0;m<len;m++){
			int rand = (int)(Math.random()*25)+1;
			for(int k=0;k<2;k++){
				int i=(int)(Math.random()*(buttons[0].length-2))+1;
				int j=(int)(Math.random()*(buttons[0].length-2))+1;
				while(buttons[i][j].getName() != "0"){
					i=(int)(Math.random()*(buttons[0].length-2))+1;
					j=(int)(Math.random()*(buttons[0].length-2))+1;
				}
				Image("img/image"+rand+".png",62,62,buttons[i][j]);
    			buttons[i][j].setName(""+rand);
    			buttons[i][j].setBounds(i*62,j*62,62,62);
    			buttons[i][j].setBackground(Color.white);
    			buttons[i][j].setBorderPainted(false);
    			buttons[i][j].addActionListener(this);     //监听按钮
    			buttons[i][j].setEnabled(false);
    			buttonPanel.add(buttons[i][j]);
			}
		}
				
		progress = new JProgressBar(0,1000);			// 实例化进度条
        progress.setStringPainted(true);      			// 描绘文字    
        progress.setBorderPainted(false);				// 进度条不绘制其边框
        progress.setForeground(new Color(242,70,70));	// 设置进度条的前景色
        progress.setBackground(Color.white); 			// 设置背景色
        progress.setBounds(230, 520, 500, 20);
        progress.setValue(1000);
        progress.setString(1000+"/"+1000);
        timer = new Timer(100, this);
        
        scorebar = new JProgressBar(1,totol);
        scorebar.setStringPainted(true);
        scorebar.setBorderPainted(false);
        scorebar.setForeground(new Color(159,194,56));
        scorebar.setBackground(Color.white);
        scorebar.setBounds(230, 555, 500, 20);
        scorebar.setString(0+"/"+totol);
        scorebar.setValue(0);
		
        thisContainer.add(buttonPanel);
		thisContainer.add(progress);
		thisContainer.add(leftPanel);
		thisContainer.add(rightTPanel);
		thisContainer.add(scorebar);
		
		mainFrame.setVisible(true);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == start){
			if(pause){
				pause=false;
				Image("img/button1.png",100, 30,start);
				timer.start();
				for(int i=1;i<buttons[0].length-1;i++){
		    		for(int j=1;j<buttons.length-1;j++){
		    			buttons[i][j].setEnabled(true);
		    		}
		    	}
			}else{
				pause=true;
				Image("img/button5.png",100, 30,start);
				timer.stop();
				for(int i=1;i<buttons[0].length-1;i++){
		    		for(int j=1;j<buttons.length-1;j++){
		    			buttons[i][j].setEnabled(false);
		    		}
		    	}
			}
		}
	    if (e.getSource() == timer) {
	    	int value = progress.getValue();
	    	if (value > 0){
	    		progress.setValue(--value);
	    		progress.setString(value--+"/"+1000);
	    		if(isEnd()){
	    			int s=scorebar.getValue();
	    			again=JOptionPane.showConfirmDialog(thisContainer, "是否再来一局?", "再来一局?",JOptionPane.YES_NO_OPTION);
	    			if(again==JOptionPane.YES_OPTION){
	    				totol+=totol;
	    				scorebar.setMaximum(totol);
	    				scorebar.setValue(s);
	    				again();
	    			}
	    		}
	    	}
	    	else {
	    		timer.stop();
	    		if(!isEnd()){
	    			JOptionPane.showMessageDialog(thisContainer, "时间到，游戏结束！");
	    			progress.setValue(1000);
	    		}
	    		for(int i=1;i<buttons[0].length-1;i++){
		    		for(int j=1;j<buttons.length-1;j++){
		    			buttons[i][j].setEnabled(false);
		    		}
		    	}
	    	}
	    }
	    if(e.getSource() == reset){
	    	int v = progress.getValue();
	    	int s =scorebar.getValue();
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
					Image("img/image"+res[k]+".png",62,62,buttons[i][j]);
	    			buttons[i][j].setName(""+res[k]);
	    			buttons[i][j].setBounds(i*62,j*62,62,62);
	    			buttons[i][j].setBackground(Color.white);
	    			buttons[i][j].setBorderPainted(false);
	    			buttonPanel.add(buttons[i][j]);
		    		k--;
		    	}
		    	progress.setValue(v-10);
		    	progress.setString(v-10+"/"+1000);
		    	scorebar.setValue(s-10);
				scorebar.setString(s-10+"/"+totol);
	    	}else{
	    		JOptionPane.showMessageDialog(thisContainer, "你没有达到交换的条件！");
	    	}
	    }
	    if(e.getSource() == newly){
	    	totol=320;
			scorebar.setMaximum(totol);
			scorebar.setValue(0);
			scorebar.setString(0+"/"+1000);
	    	again();
	    }
	    for(int i=0;i<buttons[0].length;i++){
    		for(int j=0;j<buttons.length;j++)
    		{
    			if(e.getSource()==buttons[i][j]){
    				if(x==-1&&y==-1){
    					frist=buttons[i][j].getName();
    					x=i;
    					y=j;
    				}else{
    					second=buttons[i][j].getName();
    					x0=i;
    					y0=j;
    				}
    				
					if(stack.size()<2){
						if(!stack.isEmpty() && stack.lastElement().equals((JButton)e.getSource())){
							x=-1;
							y=-1;
							stack.lastElement().setBorderPainted(false);
							stack.pop();
						}						
						else{
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
							stack.pop();
							stack.pop();
							
							int score = scorebar.getValue();
							if(score==1)
								score+=9;
							else
								score+=10;
							if(score<totol+1){					
								scorebar.setValue(score);
								scorebar.setString(score+"/"+totol);
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
	
	private void Image(String Isrc,int x,int y,JButton a){
		ImageIcon titleimage = new ImageIcon(Isrc);
		Image Image = titleimage.getImage();
		Image Ismall = Image.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		ImageIcon  Ititle= new ImageIcon(Ismall);
		a.setIcon(Ititle);
	}
	private void Image(String Isrc,int x,int y,JLabel a){
		ImageIcon titleimage = new ImageIcon(Isrc);
		Image Image = titleimage.getImage();
		Image Ismall = Image.getScaledInstance(x, y, Image.SCALE_SMOOTH);
		ImageIcon  Ititle= new ImageIcon(Ismall);
		a.setIcon(Ititle);
	}
	boolean isEnd(){
		boolean flag4=false;
		int k=0;
		for(int i=1;i<buttons[0].length-1;i++){
    		for(int j=1;j<buttons.length-1;j++)
    		{
    			if(buttons[i][j].getName()=="0")
    				k++;
    		}
		}
		if(k==64){
			flag4=true;
		}
		return flag4;
		
	}
	void again(){
		progress.setValue(1000);
    	for(int i=0;i<buttons[0].length;i++){
    		for(int j=0;j<buttons.length;j++)
    		{
    			buttons[i][j].setName(0+"");
    		}
		}			
		int len=((buttons[0].length-2)*(buttons.length-2))/2;
		for(int m=0;m<len;m++){
			int rand = (int)(Math.random()*25)+1;
			for(int k=0;k<2;k++){
				int i=(int)(Math.random()*(buttons[0].length-2))+1;
				int j=(int)(Math.random()*(buttons[0].length-2))+1;
				while(buttons[i][j].getName() != "0"){
					i=(int)(Math.random()*(buttons[0].length-2))+1;
					j=(int)(Math.random()*(buttons[0].length-2))+1;
				}
				buttons[i][j].setVisible(true);
				Image("img/image"+rand+".png",62,62,buttons[i][j]);
    			buttons[i][j].setName(""+rand);
    			buttons[i][j].setBounds(i*62,j*62,62,62);
    			buttons[i][j].setBackground(Color.white);
    			buttons[i][j].setBorderPainted(false);
    			buttonPanel.add(buttons[i][j]);
			}
		}
	}
	private boolean line(int x,int y,int x0,int y0){
		// TODO Auto-generated method stub
		boolean flag2=false;
		int a=Math.abs(y-y0)==0?Math.abs(x-x0):Math.abs(y-y0);
		if(a==1){
			//page.drawLine(x, y, x0, y0);
			flag2=true;
		}else{
			if(y==y0){//x轴
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
	
	public boolean check(int x,int y,int x0,int y0,String frist,String second){
		boolean flag=false;
		int n1=Integer.parseInt(frist);
		int n2=Integer.parseInt(second);
		if(n1==n2){
			if(x==x0||y==y0){
				flag=line(x,y,x0, y0);
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

	public static void main(String[] args){
		new lianliankan().init();
		
	}
}