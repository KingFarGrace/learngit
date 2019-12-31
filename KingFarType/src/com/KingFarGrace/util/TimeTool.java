package com.KingFarGrace.util;

import javax.swing.JLabel;
import java.util.Calendar;

public class TimeTool {
	private static boolean startFlag = false;
	private static boolean endFlag = true;
	private static boolean continueFlag = true;
	private static boolean pauseFlag = false;
	private static int pauseHour = 0;
	private static int pauseMinute = 0;
	private static int pauseSecond = 0;
	private static int continueHour = 0;
	private static int continueMinute = 0;
	private static int continueSecond = 0;
	private static int[] pauseTime = new int[3];
	private static int startHour = 0;
	private static int startMinute = 0;
	private static int startSecond = 0;
	private static int endHour = 0;
	private static int endMinute = 0;
	private static int endSecond = 0;
	
	public static void addClock(JLabel l) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					Calendar c = Calendar.getInstance();
					
					l.setText(	"当前时间为：" + c.get(Calendar.HOUR_OF_DAY) + ":" + 
									c.get(Calendar.MINUTE) + ":" + 
										c.get(Calendar.SECOND)	);
					try {
						Thread.sleep(1000);
					} catch(InterruptedException ie) {
						ie.printStackTrace();
					}
				}//end_while
			}//end_run
		}).start();;//end_Thread
	}
	
	public static void addTimer() {
		if(startFlag == false && endFlag == true) {
			startFlag = true;
			endFlag = false;
			getTime(startHour, startMinute, startSecond);
		}
	}
	
	public static void pauseTimer() {
		if(continueFlag == true && pauseFlag == false) {
			continueFlag = false;
			pauseFlag = true;
			getTime(pauseHour, pauseMinute, pauseSecond);
		}
	}
	
	public static void continueTimer() {
		if(continueFlag == false && pauseFlag == true) {
			continueFlag = true;
			pauseFlag = false;
			getTime(continueHour, continueMinute, continueSecond);
			pauseTime[0] += pauseHour - continueHour;
			pauseTime[1] += pauseMinute - continueMinute;
			pauseTime[2] += pauseSecond - continueSecond;
		}
	}
	
	public static void delTimer() {
		flushEndTime();
		if(startFlag == true && endFlag == false) {
			startFlag = false;
			endFlag = true;
			System.out.println("OK");
			getTime(endHour, endMinute, endSecond);
		}
		flushStartTime();
	}
	
	public static int[] getIntervalTime() {
		int[] time = new int[3];
		time[0] = ((endHour - startHour) - pauseTime[0] + 24) % 24;
		time[1] = ((endMinute - startMinute) - pauseTime[1] + 60) % 60;
		time[2] = ((endSecond - startSecond) - pauseTime[2] + 60) % 60;
		flushPauseTime();
		return time;
	}
	
	private static void getTime(int hour, int minute, int second) {
		Calendar c = Calendar.getInstance();
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
		second = c.get(Calendar.SECOND);
		System.out.println(hour + ":" + minute + ":" + second);
	}
	
	private static void flushStartTime() {
		startHour = 0;
		startMinute = 0;
		startSecond = 0;
	}
	
	private static void flushEndTime() {
		endHour = 0;
		endMinute = 0;
		endSecond = 0;
	}
	
	private static void flushPauseTime() {
		for(int i =0; i < 3; i++) {
			pauseTime[i] = 0;
		}
	}
}
