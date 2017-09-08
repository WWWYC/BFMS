package cn.bf.util;

public class Demo6 {
	public static Demo6 t1 = new Demo6();
	public static Demo6 t2 = new Demo6();
	
	{
		System.out.println("我是构造快");
	}
	
	static {
		System.out.println("我是静态块");
	}
	public static void main(String[] args) {
	        Demo6 t = new Demo6();
	        
        }
}
