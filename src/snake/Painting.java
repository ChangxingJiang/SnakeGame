package snake;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import tool.Gadget;

public class Painting extends JPanel{
	private static final long serialVersionUID = 1L;
	
	/**********��ͼ�ο�**********
	[�����Ӧ��]
	00:�յ�
	01:1-wall(ǽ)
	03:3-topHead(������ͷ)
	04:4-rightHead(������ͷ)
	05:5-downHead(������ͷ)
	06:6-leftHead(������ͷ)
	07:7-body(����)
	08:8-topTail(������β)
	09:9-rightTail(������β)
	10:10-downTail(������β)
	11:11-leftTail(������β)
	12:12-food(ʳ��)
	13+Ϊ��������
	font_0:����0
	font_1:����1
	font_2:����2
	font_3:����3
	font_4:����4
	font_5:����5
	font_6:����6
	font_7:����7
	font_8:����8
	font_9:����9
	cube/000-159:������ͼƬ
	[�����Ӧ��]
	��ͼ���Ͻ�����:(125,58)
	ʱ�����Ͻ�����:(207,10)
	�÷����Ͻ�����:(1095,10)
	�ٶ���ʾ����:(1101,150)
	��������������:(1120,426)
	����ҳ�ɼ����Ͻ�����:(655,320)
	������:14*22
	������:6
	****************************/
	
	/**********�������**********/
	//�������ͼ������
	Logger logger;
	//�����������岥�ű�����
	private int cube_num=0;
	private boolean cube_wait=false;
	
	/**********������**********/
	public Painting(Logger logger){
		this.logger=logger;
	}
	
	/**********���Ʒ���**********/
	//����д��paintComponent����
	//[����]�����и�window���repaint()ʱ���Զ�����paintComponentģ��
	//[�ص�]����Ƶ�ͼƬ�Զ������Ȼ��Ƶ�ͼƬ
	public void paintComponent(Graphics g){
		//�����ƿ�ʼҳ�桿
		if(logger.getPage()==0){
			Image start=new ImageIcon("img\\start.png").getImage();
			g.drawImage(start,0,0,this);
		}
		//��������Ϸҳ��+����ҳ�汳��ͼ��
		else{
			//[����]���汳��ͼ(��1��)
			if(logger.isAlive()==true){
				Image back=new ImageIcon("img\\background.png").getImage();
				g.drawImage(back,0,0,this);
			}else{
				Image backBw=new ImageIcon("img\\backgroundBw.png").getImage();
				g.drawImage(backBw,0,0,this);
			}
			
			//[����]��ͼ��Ԫ��(��2��)
			//��ȡ���û�ͼԪ��
			Image wall;
			Image body;
			Image food;
			if(logger.isAlive()==true){
				wall=new ImageIcon("img\\1-wall.png").getImage();
				body=new ImageIcon("img\\7-body.png").getImage();
				food=new ImageIcon("img\\12-food.png").getImage();
			}else{
				wall=new ImageIcon("img\\1-wallBw.png").getImage();
				body=new ImageIcon("img\\7-bodyBw.png").getImage();
				food=new ImageIcon("img\\12-foodBw.png").getImage();
			}
			//��ȡ��ͼ����
			int[][] map=logger.getMap();
			//���Ƶ�ͼ�ϵ�Ԫ��
			for(int i=0;i<20;i++){
				for(int j=0;j<32;j++){
					//���㵱ǰ������
					int x=125+j*30;
					int y=58+i*30;
					//���Ƶ�ǰԭ��
					switch(map[i][j]){
					//��ǰλ��Ϊ�յ�
					case 0:
						break;
					//��ǰλ��Ϊǽ
					case 1:
						g.drawImage(wall,x,y,this);
						break;
					//��ǰλ��Ϊ������ͷ
					case 3:
						if(logger.isAlive()==true){
							Image topHead=new ImageIcon("img\\3-topHead.png").getImage();
							g.drawImage(topHead,x,y,this);
						}else{
							Image topHead=new ImageIcon("img\\3-topHeadBw.png").getImage();
							g.drawImage(topHead,x,y,this);
						}
						break;
					//��ǰλ��Ϊ������ͷ
					case 4:
						if(logger.isAlive()==true){
							Image rightHead=new ImageIcon("img\\4-rightHead.png").getImage();
							g.drawImage(rightHead,x,y,this);
						}else{
							Image rightHead=new ImageIcon("img\\4-rightHeadBw.png").getImage();
							g.drawImage(rightHead,x,y,this);
						}
						break;
					//��ǰλ��Ϊ������ͷ
					case 5:
						if(logger.isAlive()==true){
							Image downHead=new ImageIcon("img\\5-downHead.png").getImage();
							g.drawImage(downHead,x,y,this);
						}else{
							Image downHead=new ImageIcon("img\\5-downHeadBw.png").getImage();
							g.drawImage(downHead,x,y,this);
						}
						break;
					//��ǰλ��Ϊ������ͷ
					case 6:
						if(logger.isAlive()==true){
							Image leftHead=new ImageIcon("img\\6-leftHead.png").getImage();
							g.drawImage(leftHead,x,y,this);
						}else{
							Image leftHead=new ImageIcon("img\\6-leftHeadBw.png").getImage();
							g.drawImage(leftHead,x,y,this);
						}
						break;
					//��ǰλ��Ϊ����
					case 7:
						g.drawImage(body,x,y,this);
						break;
					//��ǰλ��Ϊ������β
					case 8:
						if(logger.isAlive()==true){
							Image topTail=new ImageIcon("img\\8-topTail.png").getImage();
							g.drawImage(topTail,x,y,this);
						}else{
							Image topTail=new ImageIcon("img\\8-topTailBw.png").getImage();
							g.drawImage(topTail,x,y,this);
						}
						break;
					//��ǰλ��Ϊ������β
					case 9:
						if(logger.isAlive()==true){
							Image rightTail=new ImageIcon("img\\9-rightTail.png").getImage();
							g.drawImage(rightTail,x,y,this);
						}else{
							Image rightTail=new ImageIcon("img\\9-rightTailBw.png").getImage();
							g.drawImage(rightTail,x,y,this);
						}
						break;
					//��ǰλ��Ϊ������β
					case 10:
						if(logger.isAlive()==true){
							Image downTail=new ImageIcon("img\\10-downTail.png").getImage();
							g.drawImage(downTail,x,y,this);
						}else{
							Image downTail=new ImageIcon("img\\10-downTailBw.png").getImage();
							g.drawImage(downTail,x,y,this);
						}
						break;
					//��ǰλ��Ϊ������β
					case 11:
						if(logger.isAlive()==true){
							Image leftTail=new ImageIcon("img\\11-leftTail.png").getImage();
							g.drawImage(leftTail,x,y,this);
						}else{
							Image leftTail=new ImageIcon("img\\11-leftTailBw.png").getImage();
							g.drawImage(leftTail,x,y,this);
						}
						break;
					//��ǰλ��Ϊʳ��
					case 12:
						g.drawImage(food,x,y,this);
						break;
					}
				}
			}
			
			//[����]��ʾ��Ϸʱ��(��3��)
			//��ȡ��ǰ��Ϸʱ��
			String time=""+logger.getTime();
			for(int i=0;i<time.length();i++){
				//���㵱ǰ������
				int x=207+20*i;
				int y=10;
				//���Ƶ�ǰ����
				int temp=Gadget.toNum(time.substring(i,i+1));
				switch(temp){
				case 0:
					if(logger.isAlive()==true){
						Image font_0=new ImageIcon("img\\font_0.png").getImage();
						g.drawImage(font_0,x,y,this);
					}else{
						Image font_0=new ImageIcon("img\\font_0Bw.png").getImage();
						g.drawImage(font_0,x,y,this);
					}
					break;
				case 1:
					if(logger.isAlive()==true){
						Image font_1=new ImageIcon("img\\font_1.png").getImage();
						g.drawImage(font_1,x,y,this);
					}else{
						Image font_1=new ImageIcon("img\\font_1Bw.png").getImage();
						g.drawImage(font_1,x,y,this);
					}
					break;
				case 2:
					if(logger.isAlive()==true){
						Image font_2=new ImageIcon("img\\font_2.png").getImage();
						g.drawImage(font_2,x,y,this);
					}else{
						Image font_2=new ImageIcon("img\\font_2Bw.png").getImage();
						g.drawImage(font_2,x,y,this);
					}
					break;
				case 3:
					if(logger.isAlive()==true){
						Image font_3=new ImageIcon("img\\font_3.png").getImage();
						g.drawImage(font_3,x,y,this);
					}else{
						Image font_3=new ImageIcon("img\\font_3Bw.png").getImage();
						g.drawImage(font_3,x,y,this);
					}
					break;
				case 4:
					if(logger.isAlive()==true){
						Image font_4=new ImageIcon("img\\font_4.png").getImage();
						g.drawImage(font_4,x,y,this);
					}else{
						Image font_4=new ImageIcon("img\\font_4Bw.png").getImage();
						g.drawImage(font_4,x,y,this);
					}
					break;
				case 5:
					if(logger.isAlive()==true){
						Image font_5=new ImageIcon("img\\font_5.png").getImage();
						g.drawImage(font_5,x,y,this);
					}else{
						Image font_5=new ImageIcon("img\\font_5Bw.png").getImage();
						g.drawImage(font_5,x,y,this);
					}
					break;
				case 6:
					if(logger.isAlive()==true){
						Image font_6=new ImageIcon("img\\font_6.png").getImage();
						g.drawImage(font_6,x,y,this);
					}else{
						Image font_6=new ImageIcon("img\\font_6Bw.png").getImage();
						g.drawImage(font_6,x,y,this);
					}
					break;
				case 7:
					if(logger.isAlive()==true){
						Image font_7=new ImageIcon("img\\font_7.png").getImage();
						g.drawImage(font_7,x,y,this);
					}else{
						Image font_7=new ImageIcon("img\\font_7Bw.png").getImage();
						g.drawImage(font_7,x,y,this);
					}
					break;
				case 8:
					if(logger.isAlive()==true){
						Image font_8=new ImageIcon("img\\font_8.png").getImage();
						g.drawImage(font_8,x,y,this);
					}else{
						Image font_8=new ImageIcon("img\\font_8Bw.png").getImage();
						g.drawImage(font_8,x,y,this);
					}
					break;
				case 9:
					if(logger.isAlive()==true){
						Image font_9=new ImageIcon("img\\font_9.png").getImage();
						g.drawImage(font_9,x,y,this);
					}else{
						Image font_9=new ImageIcon("img\\font_9.png").getImage();
						g.drawImage(font_9,x,y,this);
					}
					break;
				}
			}
			
			//[����]��ʾ��Ϸ�÷�(��3��)
			//��ȡ��ǰ��Ϸ�÷�
			String mark=""+logger.getMark();
			for(int i=0;i<mark.length();i++){
				//���㵱ǰ������
				int x=1095+20*i;
				int y=10;
				//���Ƶ�ǰ����
				int temp=Gadget.toNum(mark.substring(i,i+1));
				switch(temp){
				case 0:
					if(logger.isAlive()==true){
						Image font_0=new ImageIcon("img\\font_0.png").getImage();
						g.drawImage(font_0,x,y,this);
					}else{
						Image font_0=new ImageIcon("img\\font_0Bw.png").getImage();
						g.drawImage(font_0,x,y,this);
					}
					break;
				case 1:
					if(logger.isAlive()==true){
						Image font_1=new ImageIcon("img\\font_1.png").getImage();
						g.drawImage(font_1,x,y,this);
					}else{
						Image font_1=new ImageIcon("img\\font_1Bw.png").getImage();
						g.drawImage(font_1,x,y,this);
					}
					break;
				case 2:
					if(logger.isAlive()==true){
						Image font_2=new ImageIcon("img\\font_2.png").getImage();
						g.drawImage(font_2,x,y,this);
					}else{
						Image font_2=new ImageIcon("img\\font_2Bw.png").getImage();
						g.drawImage(font_2,x,y,this);
					}
					break;
				case 3:
					if(logger.isAlive()==true){
						Image font_3=new ImageIcon("img\\font_3.png").getImage();
						g.drawImage(font_3,x,y,this);
					}else{
						Image font_3=new ImageIcon("img\\font_3Bw.png").getImage();
						g.drawImage(font_3,x,y,this);
					}
					break;
				case 4:
					if(logger.isAlive()==true){
						Image font_4=new ImageIcon("img\\font_4.png").getImage();
						g.drawImage(font_4,x,y,this);
					}else{
						Image font_4=new ImageIcon("img\\font_4Bw.png").getImage();
						g.drawImage(font_4,x,y,this);
					}
					break;
				case 5:
					if(logger.isAlive()==true){
						Image font_5=new ImageIcon("img\\font_5.png").getImage();
						g.drawImage(font_5,x,y,this);
					}else{
						Image font_5=new ImageIcon("img\\font_5Bw.png").getImage();
						g.drawImage(font_5,x,y,this);
					}
					break;
				case 6:
					if(logger.isAlive()==true){
						Image font_6=new ImageIcon("img\\font_6.png").getImage();
						g.drawImage(font_6,x,y,this);
					}else{
						Image font_6=new ImageIcon("img\\font_6Bw.png").getImage();
						g.drawImage(font_6,x,y,this);
					}
					break;
				case 7:
					if(logger.isAlive()==true){
						Image font_7=new ImageIcon("img\\font_7.png").getImage();
						g.drawImage(font_7,x,y,this);
					}else{
						Image font_7=new ImageIcon("img\\font_7Bw.png").getImage();
						g.drawImage(font_7,x,y,this);
					}
					break;
				case 8:
					if(logger.isAlive()==true){
						Image font_8=new ImageIcon("img\\font_8.png").getImage();
						g.drawImage(font_8,x,y,this);
					}else{
						Image font_8=new ImageIcon("img\\font_8Bw.png").getImage();
						g.drawImage(font_8,x,y,this);
					}
					break;
				case 9:
					if(logger.isAlive()==true){
						Image font_9=new ImageIcon("img\\font_9.png").getImage();
						g.drawImage(font_9,x,y,this);
					}else{
						Image font_9=new ImageIcon("img\\font_9.png").getImage();
						g.drawImage(font_9,x,y,this);
					}
					break;
				}
			}
			
			//[����]��ʾ��Ϸ�÷�(��3��)
			//��ȡ��ǰ��Ϸʱ��
			int speed=logger.getSpeedBase();
			int x=1101;
			int y=150;
			switch(speed){
			case 1:
				if(logger.isAlive()==true){
					Image speed_1=new ImageIcon("img\\speed_1.png").getImage();
					g.drawImage(speed_1,x,y,this);
				}else{
					Image speed_1=new ImageIcon("img\\speed_1Bw.png").getImage();
					g.drawImage(speed_1,x,y,this);
				}
				break;
			case 2:
				if(logger.isAlive()==true){
					Image speed_2=new ImageIcon("img\\speed_2.png").getImage();
					g.drawImage(speed_2,x,y,this);
				}else{
					Image speed_2=new ImageIcon("img\\speed_2Bw.png").getImage();
					g.drawImage(speed_2,x,y,this);
				}
				break;
			case 3:
				if(logger.isAlive()==true){
					Image speed_3=new ImageIcon("img\\speed_3.png").getImage();
					g.drawImage(speed_3,x,y,this);
				}else{
					Image speed_3=new ImageIcon("img\\speed_3Bw.png").getImage();
					g.drawImage(speed_3,x,y,this);
				}
				break;
			case 4:
				if(logger.isAlive()==true){
					Image speed_4=new ImageIcon("img\\speed_4.png").getImage();
					g.drawImage(speed_4,x,y,this);
				}else{
					Image speed_4=new ImageIcon("img\\speed_4Bw.png").getImage();
					g.drawImage(speed_4,x,y,this);
				}
				break;
			case 5:
				if(logger.isAlive()==true){
					Image speed_5=new ImageIcon("img\\speed_5.png").getImage();
					g.drawImage(speed_5,x,y,this);
				}else{
					Image speed_5=new ImageIcon("img\\speed_5Bw.png").getImage();
					g.drawImage(speed_5,x,y,this);
				}
				break;
			case 6:
				if(logger.isAlive()==true){
					Image speed_6=new ImageIcon("img\\speed_6.png").getImage();
					g.drawImage(speed_6,x,y,this);
				}else{
					Image speed_6=new ImageIcon("img\\speed_6Bw.png").getImage();
					g.drawImage(speed_6,x,y,this);
				}
				break;
			case 7:
				if(logger.isAlive()==true){
					Image speed_7=new ImageIcon("img\\speed_7.png").getImage();
					g.drawImage(speed_7,x,y,this);
				}else{
					Image speed_7=new ImageIcon("img\\speed_7Bw.png").getImage();
					g.drawImage(speed_7,x,y,this);
				}
				break;
			}
			
			//[����]���渲��ͼ(��4��)
			Image cover=new ImageIcon("img\\cover.png").getImage();
			g.drawImage(cover,0,0,this);
			
			//[����]����ͼƬ��ʾ(��5��)
			//���㵱ǰ������
			if(logger.isAlive()==true){
				int cubeX=1096;
				int cubeY=426;
				Image cube=new ImageIcon("img\\cube\\"+cube_num+".png").getImage();
				g.drawImage(cube,cubeX,cubeY,this);
				if(cube_num>=159){
					if(cube_wait==true){
						cube_num=0;
						cube_wait=false;
					}else{
						cube_wait=true;
					}
				}else{
					if(cube_wait==true){
						cube_num++;
						cube_wait=false;
					}else{
						cube_wait=true;
					}
				}
			}
		}
		//�����ƽ���ҳ�桿
		if(logger.getPage()==2){
			//[��ʾ����ҳ�汳��ͼ]
			Image end=new ImageIcon("img\\end.png").getImage();
			g.drawImage(end,0,0,this);
			
			//[��ʾ��Ϸ���յ÷�]
			String mark=""+logger.getMark();
			for(int i=0;i<mark.length();i++){
				//���㵱ǰ������
				int x=657+27*i;
				int y=316;
				//���Ƶ�ǰ����
				int temp=Gadget.toNum(mark.substring(i,i+1));
				switch(temp){
				case 0:
					Image font_0=new ImageIcon("img\\fontS_0.png").getImage();
					g.drawImage(font_0,x,y,this);
					break;
				case 1:
					Image font_1=new ImageIcon("img\\fontS_1.png").getImage();
					g.drawImage(font_1,x,y,this);
					break;
				case 2:
					Image font_2=new ImageIcon("img\\fontS_2.png").getImage();
					g.drawImage(font_2,x,y,this);
					break;
				case 3:
					Image font_3=new ImageIcon("img\\fontS_3.png").getImage();
					g.drawImage(font_3,x,y,this);
					break;
				case 4:
					Image font_4=new ImageIcon("img\\fontS_4.png").getImage();
					g.drawImage(font_4,x,y,this);
					break;
				case 5:
					Image font_5=new ImageIcon("img\\fontS_5.png").getImage();
					g.drawImage(font_5,x,y,this);
					break;
				case 6:
					Image font_6=new ImageIcon("img\\fontS_6.png").getImage();
					g.drawImage(font_6,x,y,this);
					break;
				case 7:
					Image font_7=new ImageIcon("img\\fontS_7.png").getImage();
					g.drawImage(font_7,x,y,this);
					break;
				case 8:
					Image font_8=new ImageIcon("img\\fontS_8.png").getImage();
					g.drawImage(font_8,x,y,this);
					break;
				case 9:
					Image font_9=new ImageIcon("img\\fontS_9.png").getImage();
					g.drawImage(font_9,x,y,this);
					break;
				}
			}
		}
	}
}
