package 反射;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerScahduce {
	public static void main(String [] args) {
		//创建一个定时器
		Timer timer = new Timer();
		//获取当前时间对象
		Calendar now = Calendar.getInstance();
		//使当前时间对象增加一秒
		now.set(Calendar.SECOND,now.get(Calendar.SECOND) + 1);
		//设置定时任务的启动时间
		Date runDate = now.getTime();
		//设置定时任务
		MyTask task2 = new MyTask();
		//每三秒运行指定的任务
		timer.scheduleAtFixedRate(task2,runDate,3000);
	}
}


class Worker {
	public static void sayHappyBirthday() {
		System.out.println("hello timer!");
	}
}

class MyTask extends TimerTask {

	@Override
	public void run() {
		try {
			//可以把类名和方法名做成配置文件或存到数据库
			Method m = Class.forName("Worker").getClass().getMethod("sayHappyBirthday");
			//调用静态方法不需要传递对象
			m.invoke(null);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}