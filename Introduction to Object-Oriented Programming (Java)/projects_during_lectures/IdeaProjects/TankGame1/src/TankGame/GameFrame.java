package TankGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class GameFrame extends JFrame implements KeyListener{
    public final int GAME_WIDTH=800;
    public final int GAME_HEIGHT = 600;

    private PlayerTank tank;
    private NPCTank npcTank;
    private ArrayList<NPCTank> npclist = new ArrayList<NPCTank>();
    private ArrayList<Bullet> blist = new ArrayList<Bullet>();

    public GameFrame() {
        this.setLocation(200,200);        //设置窗体显示在屏幕的位置
        this.setSize(GAME_WIDTH,GAME_HEIGHT);     //设置窗体的大小
        this.setResizable(false);         //设置窗体大小不可调
        this.setTitle("坦克大战");           //设置窗体标题
        //this.setBackground(Color.GRAY);  //设置窗体背景颜色
        this.addKeyListener(this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        tank = new PlayerTank(GAME_WIDTH/2,GAME_HEIGHT,Direction.UP);
        npcTank = new NPCTank(GAME_WIDTH/2,0,Direction.DOWN);

        this.setVisible(true);

        Thread thread;                           //声明线程变量
        thread = new Thread(new PaintThread());  //创建游戏线程
        thread.start();
    }

    private Image offScreenImage = null;
    private Graphics gOffScreen=null;

    public void paint(Graphics g) {

        if(offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
            gOffScreen = offScreenImage.getGraphics();
        }
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        tank.draw(gOffScreen);
        for(int i=0;i<blist.size();i++) {
            blist.get(i).draw(gOffScreen);
        }
        npcTank.draw(gOffScreen);

        //将第二缓存中的内容一次性全部绘制到屏幕上
        g.drawImage(offScreenImage, 0, 0, null);
    }

    private class PaintThread implements Runnable
    {
        public void run(){
            while(true){

                tank.move();
                for(int i=0;i<blist.size();i++) {
                    blist.get(i).move();
                }
                npcTank.move();

                repaint();      //此方法会导致窗口重绘操作
                try{
                    Thread.sleep(100);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }


    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        int key;
        key = e.getKeyCode();
        Direction dir= null;
        switch(key) {
            case KeyEvent.VK_LEFT:     dir = Direction.LEFT;    break;
            case KeyEvent.VK_UP:     dir = Direction.UP;        break;
            case KeyEvent.VK_RIGHT:    dir = Direction.RIGHT;    break;
            case KeyEvent.VK_DOWN:     dir = Direction.DOWN;    break;
            case KeyEvent.VK_SPACE:
                Bullet bullet = tank.fire();
                if(bullet != null)
                    blist.add(bullet);
                break;
        }
        if(dir != null)
            tank.setDir(dir);
    }


    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}