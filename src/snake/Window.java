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
	
	/**********�������**********/
	//����ʱ��������
	private Timer timer;
	//�����ڱ�����
	//[��������]
	public String name="̰ʳ��";
	//[��Ļ�ֱ���]
	public int screenX=0;
	public int screenY=0;
	//[���ڷֱ���]
	public int winX=1280;
	public int winY=720;
	//[��������]
	public int sitX=0;
	public int sitY=0;
	//����ͼ������
	Painting painting;
	//����Ϸ������
	Logger logger;
	
	/**********��������ģ��**********/
	//����������
	public Window(){
		//[�����ʱ��]
		Timer timer=new Timer(10,this);
		timer.start();
		//[��ȡ��Ļ�ֱ���]
		Dimension dim=getToolkit().getScreenSize();
		screenX=dim.width;
		screenY=dim.height;
		//[���㴰������]
		sitX=(screenX-winX)/2;
		sitY=(screenY-winY)/2;
		//[���ڳߴ�/λ������]
		setTitle(name);                                               //���崰������
		setSize(winX,winY);                                           //���崰�ڳߴ�
		setLocation(sitX,sitY);                                       //���崰�����Ͻ�
		//[���ڹ�������]
		setUndecorated(true);                                         //���崰��ȥ��������
		setExtendedState(JFrame.NORMAL);                              //���崰����չ״̬ΪĬ��
		setResizable(false);                                          //���ô���Ϊ��������任��С
		//[������Ϣ����]
		addMouseListener(this);                                       //��������Ϣ(ʹ��MouseListener�ӿ�)
		addMouseMotionListener(this);                                 //�������ƶ���Ϣ(ʹ��MouseMotionListener�ӿ�)
		addKeyListener(this);                                         //��Ӽ�����Ϣ(ʹ��addKeyListener�ӿ�)
		//[����һ���µ�ͼ]
		logger=new Logger();
		//[��ʼ������ͼƬ������]
		painting=new Painting(logger);                                //�����������
		setContentPane(painting);                                     //Ӧ���������
		painting.setLayout(null);                                     //��岻ʹ�ò��ֹ�����
		//[�����ػ�]
		setVisible(true);
	}
	
	/**********��ʱ����Ӧģ��**********/
	//����Ϣ����ģ�顿ÿ10�������һ����Ϣ
	public void actionPerformed(ActionEvent e){
		if(logger.getPage()==1&&logger.isAlive()==true){
			logger.execute();
		}
		this.repaint();
	}
	
	/**********��Ϣ��Ӧģ��**********/
	//�������Ϣ����갴��������ϰ��²��϶�
	public void mouseDragged(MouseEvent e){
		//[�����϶�]
		//��ȡ�϶������еĵ�ǰ���λ������
		site2=e.getPoint();
		//���ô����϶�������
		move();
	}

	//�������Ϣ��������ƶ�������ϵ��ް�������
	public void mouseMoved(MouseEvent e){

	}

	//�������Ϣ����갴��������ϵ���(���²��ͷ�)ʱ����
	public void mouseClicked(MouseEvent e){
		//[���������]
		if(e.getButton()== MouseEvent.BUTTON1){
			//��ȡ�����λ������
			int x=e.getX();
			int y=e.getY();
		}
		//[�����껬��]
		if(e.getButton()== MouseEvent.BUTTON2){
			//��ȡ���ֵ��λ������
			int x=e.getX();
			int y=e.getY();
		}
		//[�������Ҽ�]
		if(e.getButton()== MouseEvent.BUTTON3){
			//��ȡ�Ҽ����λ������
			int x=e.getX();
			int y=e.getY();
		}
	}

	//�������Ϣ�������뵽�����
	public void mousePressed(MouseEvent e){
		//[�����϶�]
		//��ȡ�϶�����ǰ�����λ������
		site1=e.getPoint();
	}

	//�������Ϣ������뿪���
	public void mouseReleased(MouseEvent e){
		//[�����϶�]
		//��ȡ�϶������еĵ�ǰ���λ������
		site2=e.getPoint();
		//���ô����϶�������
		move();
	}

	//�������Ϣ����갴��������ϰ���
	public void mouseEntered(MouseEvent e){

	}

	//�������Ϣ����갴ť��������ͷ�
	public void mouseExited(MouseEvent e){

	}
	
	//��������Ϣ�����̰���
	public void keyPressed(KeyEvent e){
		//���յ��Ϸ��������
		if(e.getKeyCode()==KeyEvent.VK_UP||e.getKeyCode()==KeyEvent.VK_W){
			if(logger.getPage()==1&&logger.isAlive()==true){
				logger.order(1);
			}
		}
		//���յ��ҷ��������
		else if(e.getKeyCode()==KeyEvent.VK_RIGHT||e.getKeyCode()==KeyEvent.VK_D){
			if(logger.getPage()==1&&logger.isAlive()==true){
				logger.order(2);
			}
		}
		//���յ��·��������
		else if(e.getKeyCode()==KeyEvent.VK_DOWN||e.getKeyCode()==KeyEvent.VK_S){
			if(logger.getPage()==1&&logger.isAlive()==true){
				logger.order(3);
			}
		}
		//���յ����������
		else if(e.getKeyCode()==KeyEvent.VK_LEFT||e.getKeyCode()==KeyEvent.VK_A){
			if(logger.getPage()==1&&logger.isAlive()==true){
				logger.order(4);
			}
		}
		//���յ�ESC������
		else if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
		//���յ��س�����
		else if(e.getKeyCode()==KeyEvent.VK_ENTER){
			//��λ�ڿ�ʼҳ������ҳ��ʱ
			if(logger.getPage()==0||logger.getPage()==2){
				logger.clear();
				logger.setPage(1);
				this.repaint();
			}
		}
		//���յ�����1������
		if(e.getKeyCode()==KeyEvent.VK_1){
			logger.setMapNum(1);
		}
		//���յ�����2������
		if(e.getKeyCode()==KeyEvent.VK_2){
			logger.setMapNum(2);
		}
		//���յ�����3������
		if(e.getKeyCode()==KeyEvent.VK_3){
			logger.setMapNum(3);
		}
	}

	//��������Ϣ�����̵���
	public void keyReleased(KeyEvent e){
		
	}

	//��������Ϣ���������һ�ΰ���
	public void keyTyped(KeyEvent e){
		
	}
	
	/**********ҳ��ģ��**********/
	//��ҳ��ģ�顿�����϶�
	//[���幦�ܱ���]
	Point site1,site2; //������������

	//[����������]
	public void move(){
		//��ȡ����϶���������
		int startX=site1.x;
		int startY=site1.y;
		//��ȡ����϶��յ������
		int endX=site2.x;
		int endY=site2.y;
		//�ж��϶�����Ƿ��ڱ�����
		if(startY>=0&&startY<=100){
			//�������귢���仯ʱ�����������Ͻ�����������Ӧ����
			if(startX!=endX){
				sitX=sitX+(endX-startX);
			}
			//�������귢���仯ʱ�����������Ͻ�����������Ӧ����
			if(startY!=endY){
				sitY=sitY+(endY-startY);
			}
			//���¶��崰��λ��
			setLocation(sitX,sitY);
			//������ʾ����
			setVisible(true);
		}
	}
	
    /**********����ģ��**********/
    //�����Գ���������
	public static void main(String[] args){
		new Window();
	}
}
