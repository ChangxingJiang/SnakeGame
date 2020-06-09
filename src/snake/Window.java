package snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Window extends JFrame implements ActionListener,MouseListener,MouseMotionListener,KeyListener{
	private static final long serialVersionUID=1L;
	
	/**********定义变量**********/
	//【计时器变量】
	private Timer timer;
	//【窗口变量】
	//[窗口名称]
	public String name="贪食蛇";
	//[屏幕分辨率]
	public int screenX=0;
	public int screenY=0;
	//[窗口分辨率]
	public int winX=1280;
	public int winY=720;
	//[窗口坐标]
	public int sitX=0;
	public int sitY=0;
	//【绘图变量】
	Painting painting;
	//【游戏变量】
	Logger logger;
	
	/**********程序启动模块**********/
	//【构造器】
	public Window(){
		//[激活计时器]
		Timer timer=new Timer(10,this);
		timer.start();
		//[读取屏幕分辨率]
		Dimension dim=getToolkit().getScreenSize();
		screenX=dim.width;
		screenY=dim.height;
		//[计算窗口坐标]
		sitX=(screenX-winX)/2;
		sitY=(screenY-winY)/2;
		//[窗口尺寸/位置设置]
		setTitle(name);                                               //定义窗口名称
		setSize(winX,winY);                                           //定义窗口尺寸
		setLocation(sitX,sitY);                                       //定义窗口左上角
		//[窗口功能设置]
		setUndecorated(true);                                         //定义窗口去除标题栏
		setExtendedState(JFrame.NORMAL);                              //定义窗口扩展状态为默认
		setResizable(false);                                          //设置窗口为不可随意变换大小
		//[设置消息接收]
		addMouseListener(this);                                       //添加鼠标消息(使用MouseListener接口)
		addMouseMotionListener(this);                                 //添加鼠标移动消息(使用MouseMotionListener接口)
		addKeyListener(this);                                         //添加键盘消息(使用addKeyListener接口)
		//[绘制一张新地图]
		logger=new Logger();
		//[初始化窗口图片绘制器]
		painting=new Painting(logger);                                //创建内容面板
		setContentPane(painting);                                     //应用内容面板
		painting.setLayout(null);                                     //面板不使用布局管理器
		//[窗口重画]
		setVisible(true);
	}
	
	/**********定时器响应模块**********/
	//【消息接收模块】每10毫秒接收一个消息
	public void actionPerformed(ActionEvent e){
		if(logger.getPage()==1&&logger.isAlive()==true){
			logger.execute();
		}
		this.repaint();
	}
	
	/**********消息响应模块**********/
	//【鼠标消息】鼠标按键在组件上按下并拖动
	public void mouseDragged(MouseEvent e){
		//[窗口拖动]
		//读取拖动过程中的当前鼠标位置坐标
		site2=e.getPoint();
		//调用窗口拖动处理方法
		move();
	}

	//【鼠标消息】鼠标光标移动到组件上但无按键按下
	public void mouseMoved(MouseEvent e){

	}

	//【鼠标消息】鼠标按键在组件上单击(按下并释放)时调用
	public void mouseClicked(MouseEvent e){
		//[点击鼠标左键]
		if(e.getButton()== MouseEvent.BUTTON1){
			//获取鼠标点击位置坐标
			int x=e.getX();
			int y=e.getY();
		}
		//[点击鼠标滑轮]
		if(e.getButton()== MouseEvent.BUTTON2){
			//获取滚轮点击位置坐标
			int x=e.getX();
			int y=e.getY();
		}
		//[点击鼠标右键]
		if(e.getButton()== MouseEvent.BUTTON3){
			//获取右键点击位置坐标
			int x=e.getX();
			int y=e.getY();
		}
	}

	//【鼠标消息】鼠标进入到组件上
	public void mousePressed(MouseEvent e){
		//[窗口拖动]
		//读取拖动过程前的鼠标位置坐标
		site1=e.getPoint();
	}

	//【鼠标消息】鼠标离开组件
	public void mouseReleased(MouseEvent e){
		//[窗口拖动]
		//读取拖动过程中的当前鼠标位置坐标
		site2=e.getPoint();
		//调用窗口拖动处理方法
		move();
	}

	//【鼠标消息】鼠标按键在组件上按下
	public void mouseEntered(MouseEvent e){

	}

	//【鼠标消息】鼠标按钮在组件上释放
	public void mouseExited(MouseEvent e){

	}
	
	//【键盘消息】键盘按下
	public void keyPressed(KeyEvent e){
		//接收到上方向键命令
		if(e.getKeyCode()==KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_W){
			if(logger.getPage()==1&&logger.isAlive()==true){
				logger.order(1);
			}
		}
		//接收到右方向键命令
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_D){
			if(logger.getPage()==1&&logger.isAlive()==true){
				logger.order(2);
			}
		}
		//接收到下方向键命令
		else if(e.getKeyCode()==KeyEvent.VK_DOWN||e.getKeyCode()==KeyEvent.VK_S){
			if(logger.getPage()==1&&logger.isAlive()==true){
				logger.order(3);
			}
		}
		//接收到左方向键命令
		else if(e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_A){
			if(logger.getPage()==1&&logger.isAlive()==true){
				logger.order(4);
			}
		}
		//接收到ESC键命令
		else if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
		//接收到回车命令
		else if(e.getKeyCode()==KeyEvent.VK_ENTER){
			//当位于开始页面或结束页面时
			if(logger.getPage()==0||logger.getPage()==2){
				logger.clear();
				logger.setPage(1);
				this.repaint();
			}
		}
		//接收到数字1键命令
		if(e.getKeyCode()==KeyEvent.VK_1){
			logger.setMapNum(1);
		}
		//接收到数字2键命令
		if(e.getKeyCode()==KeyEvent.VK_2){
			logger.setMapNum(2);
		}
		//接收到数字3键命令
		if(e.getKeyCode()==KeyEvent.VK_3){
			logger.setMapNum(3);
		}
	}

	//【键盘消息】键盘弹起
	public void keyReleased(KeyEvent e){
		
	}

	//【键盘消息】键盘完成一次按键
	public void keyTyped(KeyEvent e){
		
	}
	
	/**********页面模块**********/
	//【页面模块】窗口拖动
	//[定义功能变量]
	Point site1,site2; //定义鼠标点坐标

	//[功能主程序]
	public void move(){
		//读取鼠标拖动起点的坐标
		int startX=site1.x;
		int startY=site1.y;
		//读取鼠标拖动终点的坐标
		int endX=site2.x;
		int endY=site2.y;
		//判断拖动起点是否在标题栏
		if(startY>=0&&startY<=100){
			//当横坐标发生变化时，将窗口左上角坐标作出对应修正
			if(startX!=endX){
				sitX=sitX+(endX-startX);
			}
			//当纵坐标发生变化时，将窗口右上角坐标作出对应修正
			if(startY!=endY){
				sitY=sitY+(endY-startY);
			}
			//重新定义窗口位置
			setLocation(sitX,sitY);
			//重新显示窗口
			setVisible(true);
		}
	}
	
    /**********测试模块**********/
    //【测试程序启动】
	public static void main(String[] args){
		new Window();
	}
}
