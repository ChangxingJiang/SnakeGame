package snake;

import java.util.ArrayList;
import java.util.Random;

import tool.Gadget;
import tool.Point;

public class Logger{

	/**********游戏数据**********
	[编码对应表]
	00:空地
	01:1-wall(墙)
	03:3-topHead(向上蛇头)
	04:4-rightHead(向右蛇头)
	05:5-downHead(向下蛇头)
	06:6-leftHead(向左蛇头)
	07:7-body(蛇身)
	08:8-topTail(向上蛇尾)
	09:9-rightTail(向右蛇尾)
	10:10-downTail(向下蛇尾)
	11:11-leftTail(向左蛇尾)
	12:12-food(食物)
	13+为特殊内容
	[坐标对应表]
	游戏界面:1280*720
	地图尺寸:960*600
	地图块数:32*20
	地图左上角坐标:125*58
	****************************/
	
	/**********定义变量**********/
	//【界面变量】
	//[界面变量]0为开始界面,1为游戏界面,2为结束界面
	private int page=0;
	
	//【地图变量】当前地图变量
	private int mapNum=1;
	private int[][] map=new int[20][32];
	
	//【蛇变量】
	//[蛇身体位置]
	private ArrayList<Point> snake=new ArrayList<Point>();
	//[蛇运动方向]1上，2右，3下，4左
	private int direction=0;
	//[蛇目标方向]
	private int aimDirection=0;
	//[未增加蛇长度]
	private int snakeEat=0;
	
	//【局面变量】
	//[当前是否存活]
	private boolean alive=true;
	//[当前空地总数]
	private int empty=647;
	//[当前食物总数]
	private int foodCount=0;
	
	//【时间变量】
	//[当前游戏时间]
	private int time=0;
	//[当前蛇基础运动速度]1为100ms,2为90ms,3为8ms,4为70ms,5为60ms,6为50ms,7为40ms
	private int speedBase=1;
	//[当前蛇额外增益移动速度]-2为-20ms,1为10ms,2为20ms,3为30ms
	private int speedTemp=0;
	//[当前等待次数]
	private int wait=0;
	
	//【得分变量】
	//[当前得分]
	private int mark=0;
	
	/**********应用模块**********/
	//【构造器】创建一个空地图
	public Logger(){
		//[绘制空地图]
		for(int i=0;i<20;i++){
			for(int j=0;j<32;j++){
				map[i][j]=0;
			}
		}
		//[初始化地图形状]
		Map mapLoading=new Map();
		if(mapNum==1){
			ArrayList<Point> mapWall=mapLoading.getMapA();
			for(int i=0;i<mapWall.size();i++){
				map[mapWall.get(i).getX()-1][mapWall.get(i).getY()-1]=1;
			}
		}else if(mapNum==2){
			ArrayList<Point> mapWall=mapLoading.getMapB();
			for(int i=0;i<mapWall.size();i++){
				map[mapWall.get(i).getX()-1][mapWall.get(i).getY()-1]=1;
			}
		}else if(mapNum==3){
			ArrayList<Point> mapWall=mapLoading.getMapC();
			for(int i=0;i<mapWall.size();i++){
				map[mapWall.get(i).getX()-1][mapWall.get(i).getY()-1]=1;
			}
		}
		//[初始化蛇身体位置]
		snake.add(new Point(13,15));
		map[13][15]=6;
		snake.add(new Point(13,16));
		map[13][16]=7;
		snake.add(new Point(13,17));
		map[13][17]=11;
		//[初始化蛇运动方向]
		direction=4;
		aimDirection=4;
	}
	
	/**********操作命令**********/
	//【操作】命令蛇转向
	public boolean order(int direction){
		//检验是否为游戏界面
		if(page==0||page==2){
			return false;
		}
		//防止蛇直接掉头
		if(this.direction==1&&direction==3){
			return false;
		}else if(this.direction==2&&direction==4){
			return false;
		}else if(this.direction==3&&direction==1){
			return false;
		}else if(this.direction==4&&direction==2){
			return false;
		}
		//执行修改方向命令
		else{
			this.aimDirection=direction;
			return true;
		}
	}
	
	/**********执行命令**********/
	//【时间消息】(返回是否重画)
	public boolean execute(){
		//[检验是否为游戏界面]
		if(page==0||page==2){
			return false;
		}
		//[增加游戏时间变量]
		time++;
		//[当蛇不需要移动时]
		if(wait<(15-getSpeed())){
			//增加已等待时间
			wait=wait+1;
			//计算是否添加食物
			if(decideFood()){
				return true;
			}else{
				return false;
			}
		}
		//[当蛇需要移动时]
		else{
			//清空已等待时间
			wait=0;
			//若存在临时移速则降低
			if(speedTemp>0){
				speedTemp--;
			}
			//处理蛇向前事件
			if(snakeEvent()){
				//蛇向前移动
				snakeMove();
			}
			else{
				alive=false;
				page=2;
			}
			//计算是否添加食物
			decideFood();
			return true;
		}
	}
	
	//【子模块】处理蛇向前事件(返回是否可以向前)
	public boolean snakeEvent(){
		//[定义目标移动位置变量]
		int targetX=0;
		int targetY=0;
		//[计算目标移动位置]
		switch(aimDirection){
		//当蛇没有运动方向
		case 0:
			return false;
		//当蛇向上运动
		case 1:
			targetX=snake.get(0).getX()-1;
			targetY=snake.get(0).getY();
			break;
		//当蛇向右运动
		case 2:
			targetX=snake.get(0).getX();
			targetY=snake.get(0).getY()+1;
			break;
		//当蛇向下运动
		case 3:
			targetX=snake.get(0).getX()+1;
			targetY=snake.get(0).getY();
			break;
		//当蛇向左运动
		case 4:
			targetX=snake.get(0).getX();
			targetY=snake.get(0).getY()-1;
			break;
		//出现异常值
		default:
			return false;
		}
		//[判断蛇是否撞边墙]
		if(targetX<0||targetX>19||targetY<0||targetY>31){
			return false;
		}
		if(map[targetX][targetY]==1){
			return false;
		}
		//[判断蛇是否撞到自身]
		if(map[targetX][targetY]>=3&&map[targetX][targetY]<=11){
			return false;
		}
		//[判断蛇是否吃到食物]
		if(map[targetX][targetY]==12){
			eatFood();
		}
		return true;
	}
	
	//【子模块】蛇吃到食物
	private void eatFood(){
		//[增加蛇的长度]
		snakeEat++;
		//[修改地图统计数据]
		empty--;
		foodCount--;
		//[增加爆发性移动速度]
		if(speedBase<3){
			speedTemp=speedTemp+3;
			if(speedTemp>8){
				speedTemp=8;
			}
		}else if(speedBase>=3&&speedBase<5){
			speedTemp=speedTemp+2;
			if(speedTemp>6){
				speedTemp=6;
			}
		}else if(speedBase>=5){
			speedTemp=speedTemp+1;
		}
		//[增加分数]
		mark=mark+(snake.size()-2);
		//[计算基础速度]
		if(snake.size()==4||snake.size()==14||snake.size()==29||snake.size()==49||snake.size()==74||snake.size()==104){
			if(speedBase<7){
				speedBase++;
			}
		}
	}
	
	//【子模块】蛇向前移动(拥有保护不超出边墙功能)
	private void snakeMove(){
		switch(aimDirection){
		//当蛇没有运动方向
		case 0:
			alive=false;
			page=2;
			break;
		//当蛇向上运动
		case 1:
			if(snake.get(0).getX()==0){
				alive=false;
				page=2;
				break;
			}else{
				//设置蛇头
				Point head=snake.get(0);
				snake.add(0,new Point(head.getX()-1,head.getY()));
				map[head.getX()-1][head.getY()]=3;
				map[head.getX()][head.getY()]=7;
				//蛇尾移动
				tailMove();
				break;
			}
		//当蛇向右运动
		case 2:
			if(snake.get(0).getY()==31){
				alive=false;
				page=2;
				break;
			}else{
				//设置蛇头
				Point head=snake.get(0);
				snake.add(0,new Point(head.getX(),head.getY()+1));
				map[head.getX()][head.getY()+1]=4;
				map[head.getX()][head.getY()]=7;
				//蛇尾移动
				tailMove();
				break;
			}
		//当蛇向下运动
		case 3:
			if(snake.get(0).getX()==19){
				alive=false;
				page=2;
				break;
			}else{
				//设置蛇头
				Point head=snake.get(0);
				snake.add(0,new Point(head.getX()+1,head.getY()));
				map[head.getX()+1][head.getY()]=5;
				map[head.getX()][head.getY()]=7;
				//蛇尾移动
				tailMove();
				break;
			}
		//当蛇向左运动
		case 4:
			if(snake.get(0).getY()==0){
				alive=false;
				page=2;
				break;
			}else{
				//设置蛇头
				Point head=snake.get(0);
				snake.add(0,new Point(head.getX(),head.getY()-1));
				map[head.getX()][head.getY()-1]=6;
				map[head.getX()][head.getY()]=7;
				//蛇尾移动
				tailMove();
				break;
			}
		//出现异常值
		default:
			alive=false;
			page=2;
			break;
		}
		direction=aimDirection;
	}
	
	//【子模块】蛇尾向前移动
	private void tailMove(){
		//判断蛇是否需要增长
		if(snakeEat>=1){
			snakeEat--;
		}else{
			//清除老蛇尾
			Point fadeTail=snake.get(snake.size()-1);
			snake.remove(snake.size()-1);
			map[fadeTail.getX()][fadeTail.getY()]=0;
			//计算蛇尾方向
			int tailType=0;
			Point nearTail=snake.get(snake.size()-2);
			Point nowTail=snake.get(snake.size()-1);
			if(nearTail.getX()==nowTail.getX()-1){
				tailType=8;
			}else if(nearTail.getY()==nowTail.getY()+1){
				tailType=9;
			}else if(nearTail.getX()==nowTail.getX()+1){
				tailType=10;
			}else{
				tailType=11;
			}
			map[nowTail.getX()][nowTail.getY()]=tailType;
		}
	}
	
	/**********上帝命令**********/
	//【操作】判断是否添加食物
	private boolean decideFood(){
		//[食物数量=0]100%添加食物
		if(foodCount==0){
			addFood();
			return true;
		}
		//[食物数量>0]按10的次方数添加食物
		else{
			//计算比例数值
			int pro=1;
			for(int i=0;i<foodCount;i++){
				pro=10*pro;
			}
			//计算随机数
			Random r=new Random(); 
			int random=r.nextInt((int)pro);
			if(random==0){
				addFood();
				return true;
			}else{
				return false;
			}
		}
	}
	
	//【操作】添加食物
	private void addFood(){
		Random r=new Random(); 
		int place=r.nextInt(getEmpty());
		int countPlace=0;
		for(int i=0;i<20;i++){
			for(int j=0;j<32;j++){
				if(map[i][j]==0){
					if(countPlace==place){
						//将地图该位置替换为食物
						map[i][j]=12;
						//修改地图统计数据
						empty--;
						foodCount++;
						return;
					}
					countPlace++;
				}
			}
		}
	}
	
	//【重置游戏环境数据】
	public void clear(){
		//[绘制空地图]
		for(int i=0;i<20;i++){
			for(int j=0;j<32;j++){
				map[i][j]=0;
			}
		}
		//[初始化地图形状]
		Map mapLoading=new Map();
		if(mapNum==1){
			ArrayList<Point> mapWall=mapLoading.getMapA();
			for(int i=0;i<mapWall.size();i++){
				map[mapWall.get(i).getX()-1][mapWall.get(i).getY()-1]=1;
			}
		}else if(mapNum==2){
			ArrayList<Point> mapWall=mapLoading.getMapB();
			for(int i=0;i<mapWall.size();i++){
				map[mapWall.get(i).getX()-1][mapWall.get(i).getY()-1]=1;
			}
		}else if(mapNum==3){
			ArrayList<Point> mapWall=mapLoading.getMapC();
			for(int i=0;i<mapWall.size();i++){
				map[mapWall.get(i).getX()-1][mapWall.get(i).getY()-1]=1;
			}
		}
		//[清空蛇身体数据]
		snake=new ArrayList<Point>();
		//[初始化蛇身体数据]
		snake.add(new Point(13,15));
		map[13][15]=6;
		snake.add(new Point(13,16));
		map[13][16]=7;
		snake.add(new Point(13,17));
		map[13][17]=11;
		//[初始化蛇运动方向]
		direction=4;
		aimDirection=4;
		//[初始化蛇长度]
		snakeEat=0;
		//[初始化局内变量]
		alive=true;
		empty=647;
		foodCount=0;
		//[初始化游戏时间]
		time=0;
		//[初始化蛇运动速度]
		speedBase=1;
		speedTemp=0;
		wait=0;
		//[重置得分数据]
		mark=0;
	}
	
	/**********数据命令**********/
	//【读取界面数据】
	//[界面]读取界面变量
	public int getPage(){
		return page;
	}
	//[界面]修改界面变量
	public void setPage(int page){
		this.page=page;
	}
	
	//【读取局面数据】
	//[局面]获得当前是否存活
	public boolean isAlive(){
		return alive;
	}
	//[局面]获取当前空地数
	public int getEmpty(){
		return empty;
	}
	
	//【读取时间数据】
	//[时间]获取游戏时间(单位:秒)
	public int getTime(){
		return time/100;
	}
	//[时间]获取游戏时间(单位:毫秒)
	public int getTimeMs(){
		return time*10;
	}
	//[时间]获取当前基础速度
	public int getSpeedBase(){
		return speedBase;
	}
	//[时间]获取当前临时速度
	public int getSpeedTemp(){
		return speedTemp;
	}
	//[时间]获取当前速度
	public int getSpeed(){
		return speedTemp+speedBase;
	}

	//【读取地图数据】
	//[地图]获取点的内容
	public int getPoint(int x,int y){
		return map[x][y];
	}
	//[地图]获取地图
	public int[][] getMap(){
		return map;
	}
	//[地图]获取当前地图编号
	public int getMapNum(){
		return mapNum;
	}
	//[地图]设置当前地图编号
	public void setMapNum(int mapNum){
		this.mapNum=mapNum;
	}
	
	//【读取得分数据】
	public int getMark(){
		return mark;
	}
	
	/**********测试模块**********/
	//【测试启动程序】
	public static void main(String[] args){
		Logger envir=new Logger();
		envir.show();
		System.out.println("");
		envir.snakeMove();
		envir.show();
		System.out.println("");
		envir.order(1);
		envir.snakeMove();
		envir.show();
		System.out.println("");
		envir.snakeMove();
		envir.show();
	}
	
	//【测试输出】
	public void show(){
		for(int i=0;i<20;i++){
			for(int j=0;j<32;j++){
				System.out.print(Gadget.toDoubleDigit(getPoint(i,j))+" ");
			}
			System.out.println("");
		}
	}
}
