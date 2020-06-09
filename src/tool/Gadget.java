package tool;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Gadget{
	//【模块】将字符串转化为数字
	public static int toNum(String x){
		try{
			x=x.replace(" ","");
		}catch(NullPointerException e1){
			return -1;
		}
		try{
			return Integer.valueOf(x).intValue();                       //将字符串转化为为int格式
		}
		catch(NumberFormatException e2){
			return -1;
		}
	}
	
	//【模块】制作定长空字符串
	public static String makeStr(int length){
		String answer=new String("");
		for(long i=0;i<length;){
			if((length-i)>=128){                                      //当剩余128个字节以上需要填写时
				answer=answer+"                                                                                                                                ";
				i=i+128;
			}else if((length-i)>=64){                                 //当剩余64个字节以上需要填写时
				answer=answer+"                                                                ";
				i=i+64;
			}else if((length-i)>=32){                                 //当剩余32个字节以上需要填写时
				answer=answer+"                                ";
				i=i+32;
			}else if((length-i)>=16){                                 //当剩余16个字节以上需要填写时
				answer=answer+"                ";
				i=i+16;
			}else if((length-i)>=8){                                  //当剩余8个字节以上需要填写时
				answer=answer+"        ";
				i=i+8;
			}else if((length-i)>=4){                                  //当剩余4个字节以上需要填写时
				answer=answer+"    ";
				i=i+4;
			}else if((length-i)>=2){                                  //当剩余2个字节以上需要填写时
				answer=answer+"  ";
				i=i+2;
			}else{                                                    //当剩余1个字节需要填写时
				answer=answer+" ";
				i++;
			}
		}
		return answer;
	}
	
	//【模块】读取当前系统日期
	public static String readData(String format){
		SimpleDateFormat time=new SimpleDateFormat(format);
		return time.format(new Date());
	}
	
	//【模块】读取当前系统日期("yyyy-MM-dd HH:mm:ss.SSS")
	public static String readData(){
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return time.format(new Date());
	}
	
	//【模块】打开文件
	public static boolean openFile(String path){
		try{
			Desktop.getDesktop().open(new File(path));
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	//【模块】将数字转化为两位数
	public static String toDoubleDigit(int x){
		if(x<=0){
			return "00";
		}else if(x<10){
			return "0"+x;
		}else if(x<100){
			return ""+x;
		}else{
			return String.valueOf(x).substring(0,2);
		}
	}
}
