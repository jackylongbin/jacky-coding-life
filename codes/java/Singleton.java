/*
 * 文件名：Singleton.java
 * 版权：Copyright by www.jacky.com
 * 描述：
 * 修改人：Administrator
 * 修改时间：2018年1月31日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

/**
 * 饿单例模式
 * Singleton instance construct when class load,anxious initialization
 * 缺点不能延迟加载，资源效率不高
 */
/*public class Singleton {

	//element one,static reference
	public static Singleton instance = new Singleton();
	private Singleton()
	{
		//element two,private constructor
	}
	//element three,static function return instance
	public static Singleton getInstance()
	{
		return instance;
	}
}*/

/**
 * 懒单例模式
 * Singleton instance construct when first use
 * 实现延迟加载但是非线程安全
 */
/*public class Singleton{
	//element one,static reference
	private static Singleton instance = null;
	
	private Singleton()
	{
		//element two,private constructor
	}
	
	public static Singleton getInstance()
	{
		if(instance == null)
		{
			instance = new Singleton();
		}
		
		return instance;
	}
	
}*/

/**
 * 双重检测单例模式
 * 延迟加载，语义上线程安全，非绝对线程安全
 */
/*public class Singleton{
	//element one,static reference
	private static Singleton instance = null;
	
	private Singleton()
	{
		//element two,private constructor
	}
	
	public static Singleton getInstance()
	{
		if(instance == null)
		{
			synchronized (Singleton.class) {
				
				if(instance == null)
					instance = new Singleton();
			}
		}
		
		return instance;
	}
	
}*/

/**
 * 双重检测安全单例模式
 * 延迟加载，线程安全
 * jdk1.5以后
 */
/*public class Singleton{
	//element one,static reference
	private static volatile Singleton instance = null;
	
	private Singleton()
	{
		//element two,private constructor
	}
	
	public static Singleton getInstance()
	{
		if(instance == null)
		{
			synchronized (Singleton.class) {
				
				if(instance == null)
					instance = new Singleton();
			}
		}
		
		return instance;
	}
	
}*/


/**
 * 静态内部类单例模式
 * 延迟加载，线程安全，资源利用率高
 */
/*public class Singleton{
	//element one,static reference
	private Singleton()
	{
		//element two,private constructor
	}
	
	private static class SingletonHelp
	{
		public static final Singleton instance = new Singleton();
	}
	public static Singleton getInstance()
	{	
		return SingletonHelp.instance;
	}
}*/


/**
 * 最优雅的单例实现
 * 使用enum实现
 * 不用考虑序列化和反射的问题
 * EffectiveJava 推荐方法
 */
public enum Singleton {
    INSTANCE;
    private String name;
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}
