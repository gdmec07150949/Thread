package com.example.administrator.myapplication;


import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView tv1;
    private int seconds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1= (TextView) findViewById(R.id.tv1);
        Date theLastDay =new Date(117,5,23);
        Date today=new Date();
        seconds = (int) ((theLastDay.getTime()-today.getTime())/1000);
    }
    public void anr(View v){
        for(int i=0;i<100000;i++){
            BitmapFactory.decodeResource(getResources(),R.drawable.android);
        }
    }
    public void threadclass(View v){
        class ThreadSamlie extends Thread{
            Random rm;
            public ThreadSamlie(String tname){
                super(tname);
                rm=new Random();
            }
            public void run(){
                for(int i=0;i<10;i++){
                    System.out.println(i+" "+getName());
                    try {
                        sleep(rm.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(getName()+"完成");
            }
        }
        ThreadSamlie thread1 =new ThreadSamlie("线程一");
        thread1.start();
        ThreadSamlie thread2 =new ThreadSamlie("线程二");
        thread2.start();
    }
    public void runnaleinterface(View v){
        class RunnableExample implements Runnable{
            Random rm;
            String name;
            public RunnableExample(String tname){
                this.name=tname;
                rm=new Random();
            }
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    System.out.println(i+" "+name);
                    try {
                        Thread.sleep(rm.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(name+"完成");
            }
        }
        Thread thread1 =new Thread("线程一");
        thread1.start();
        Thread thread2 =new Thread("线程二");
        thread2.start();
    }
    public void timertask(View v){
        class MyThread extends TimerTask{
            Random rm;
            String name;
            public MyThread(String tname){
                this.name=tname;
                rm =new Random();
            }
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    System.out.println(i+" "+name);
                    try {
                        Thread.sleep(rm.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(name+"完成");
            }
        }
        Timer timer1=new Timer();
        Timer timer2=new Timer();
        MyThread thread1 = new MyThread("线程一");
        MyThread thread2 = new MyThread("线程二");
        timer1.schedule(thread1,0);
        timer1.schedule(thread2,0);
    }
    public void showmsg(String msg){
        tv1.setText(msg);
    }
    public void asynctask(View v){
        class LearHard extends AsyncTask<Long,String,String>{
            private Context context;
            final int duration=10;
            int count =0;

            public LearHard(Activity context) {
                this.context = context;
            }
            @Override
            protected String doInBackground(Long... params) {
                long num =params[0].longValue();
                while (count<duration){
                    num--;
                    count++;
                    String status ="离毕业还有"+num+"秒，努力学习"+count+"秒。";
                    publishProgress(status);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return "这"+duration+"秒有收获，没虚度";
            }
            protected void onProgressUpdate(String... values){
                ((MainActivity)context).tv1.setText(values[0]);
                super.onProgressUpdate(values);
            }
            protected void onPostExecute(String s){
                showmsg(s);
                super.onPostExecute(s);
            }
        }
        LearHard learHard=new LearHard(this);
        learHard.execute((long)seconds);
    }
}
