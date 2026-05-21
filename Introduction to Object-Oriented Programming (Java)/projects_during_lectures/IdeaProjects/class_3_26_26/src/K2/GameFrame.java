package K2;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
    public final int GAME_WIDTH=800;
    public final int GAME_HEIGHT = 600;
    public final int TANK_WIDTH = 20;
    public final int TANK_HEIGHT = 20;

    public int x = 100;
    public int y = 300;

    enum Direction {L,U,R,D};//方向的枚举
    private Direction dir = Direction.D;

    public GameFrame() {
        this.setLocation(200,200);        //设置窗体显示在屏幕的位置
        this.setSize(GAME_WIDTH,GAME_HEIGHT);     //设置窗体的大小
        this.setResizable(false);         //设置窗体大小不可调
        this.setTitle("坦克大战");           //设置窗体标题
        //this.setBackground(Color.GRAY);  //设置窗体背景颜色
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        Thread thread;                           //声明线程变量
        thread = new Thread(new PaintThread());  //创建游戏线程

        thread.start();
    }


    public void paint(Graphics g) {
        //super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        g.setColor(Color.red);
        //画正方形坦克架
        g.drawRect(x-TANK_WIDTH/2, y-TANK_HEIGHT/2, TANK_WIDTH, TANK_HEIGHT);
        //画圆形炮塔，比坦克架小2个像素
        g.drawOval(x-TANK_WIDTH/2+2, y-TANK_HEIGHT/2+2, TANK_WIDTH-4, TANK_HEIGHT-4);
        switch(dir){
            case L:
                g.drawRect(x-28, y-1, 28, 2);  //画长方形炮管，宽2，长28
                drawTrackLR(g);                //调用绘制履带的函数
                break;
            case R:
                g.drawRect(x, y-1, 28, 2);     //画长方形炮管，宽2，长28
                drawTrackLR(g);                //调用绘制履带的函数
                break;
            case U:
                g.drawRect(x-1, y-28, 2, 28);  //画长方形炮管，宽2，长28
                drawTrackUD(g);                //调用绘制履带的函数
                break;
            case D:
                g.drawRect(x-1, y, 2, 28);     //画长方形炮管，宽2，长28
                drawTrackUD(g);                //调用绘制履带的函数
                break;
        }

        y -= 3;
    }
    public void drawTrackLR(Graphics g){
        g.drawRect(x-12, y-14, 24, 4); //画上边长方形履带，宽4，长24
        g.drawRect(x-12, y+10, 24, 4);//画下边长方形履带，宽4，长24
    }
    public void drawTrackUD(Graphics g){
        g.drawRect(x-14, y-12, 4, 24);//画左边长方形履带，宽4，长24
        g.drawRect(x+10, y-12, 4, 24);    //画右边长方形履带，宽4，长24
    }
    private class PaintThread implements Runnable
    {
        public void run(){
            while(true){
                repaint();      //此方法会导致窗口重绘操作
                try{
                    Thread.sleep(500);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}