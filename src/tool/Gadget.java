package tool;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Gadget{
	//��ģ�顿���ַ���ת��Ϊ����
	public static int toNum(String x){
		try{
			x=x.replace(" ","");
		}catch(NullPointerException e1){
			return -1;
		}
		try{
			return Integer.valueOf(x).intValue();                       //���ַ���ת��ΪΪint��ʽ
		}
		catch(NumberFormatException e2){
			return -1;
		}
	}
	
	//��ģ�顿�����������ַ���
	public static String makeStr(int length){
		String answer=new String("");
		for(long i=0;i<length;){
			if((length-i)>=128){                                      //��ʣ��128���ֽ�������Ҫ��дʱ
				answer=answer+"                                                                                                                                ";
				i=i+128;
			}else if((length-i)>=64){                                 //��ʣ��64���ֽ�������Ҫ��дʱ
				answer=answer+"                                                                ";
				i=i+64;
			}else if((length-i)>=32){                                 //��ʣ��32���ֽ�������Ҫ��дʱ
				answer=answer+"                                ";
				i=i+32;
			}else if((length-i)>=16){                                 //��ʣ��16���ֽ�������Ҫ��дʱ
				answer=answer+"                ";
				i=i+16;
			}else if((length-i)>=8){                                  //��ʣ��8���ֽ�������Ҫ��дʱ
				answer=answer+"        ";
				i=i+8;
			}else if((length-i)>=4){                                  //��ʣ��4���ֽ�������Ҫ��дʱ
				answer=answer+"    ";
				i=i+4;
			}else if((length-i)>=2){                                  //��ʣ��2���ֽ�������Ҫ��дʱ
				answer=answer+"  ";
				i=i+2;
			}else{                                                    //��ʣ��1���ֽ���Ҫ��дʱ
				answer=answer+" ";
				i++;
			}
		}
		return answer;
	}
	
	//��ģ�顿��ȡ��ǰϵͳ����
	public static String readData(String format){
		SimpleDateFormat time=new SimpleDateFormat(format);
		return time.format(new Date());
	}
	
	//��ģ�顿��ȡ��ǰϵͳ����("yyyy-MM-dd HH:mm:ss.SSS")
	public static String readData(){
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return time.format(new Date());
	}
	
	//��ģ�顿���ļ�
	public static boolean openFile(String path){
		try{
			Desktop.getDesktop().open(new File(path));
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	//��ģ�顿������ת��Ϊ��λ��
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
