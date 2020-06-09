package tool;

public class Point{

	///[定义变量]
	private int x=0;
	private int y=0;
	
	//[读取方法]
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	//[写入方法]
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
		this.y=y;
	}
	
	//[构造方法]
	public Point(){}
	
	public Point(int x,int y){
		this.x=x;
		this.y=y;
	}
	
	//[测试模块]
	public static void main(String[] args){}
}
