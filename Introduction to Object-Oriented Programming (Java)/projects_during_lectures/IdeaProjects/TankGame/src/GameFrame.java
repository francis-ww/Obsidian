import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;

public class GameFrame extends JFrame implements KeyListener{
    public static final int GAME_WIDTH=800;
    public static final int GAME_HEIGHT = 600;

    private PlayerTank tank;
    private ArrayList<NPCTank> npcList = new ArrayList<NPCTank>();
    private ArrayList<Bullet> bnpclist = new ArrayList<Bullet>();
    private ArrayList<Bullet> bplylist = new ArrayList<Bullet>();
    private AnimationTE animation_nt;
    private AnimationTE animation_pt;
    private AnimationBE animation_b;
    private AnimationTC animation_tc;

    public GameFrame() {

        this.setLocation(200,200);        //设置窗体显示在屏幕的位置
        this.setSize(GAME_WIDTH,GAME_HEIGHT);     //设置窗体的大小
        this.setResizable(false);         //设置窗体大小不可调
        this.setTitle("坦克大战");           //设置窗体标题
        //this.setBackground(Color.GRAY);  //设置窗体背景颜色
        this.addKeyListener(this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        tank = new PlayerTank(GAME_WIDTH/2,GAME_HEIGHT,Direction.UP);
        for(int i=0;i<50;i++) {
            npcList.add(new NPCTank(150+ i%10 * 50,i/10 * 50,Direction.DOWN));
        }

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
        for(NPCTank npc:npcList) {
            npc.draw(gOffScreen);
        }

        for(int i=0;i<bplylist.size();i++) {
            bplylist.get(i).draw(gOffScreen);
        }
        for(int i=0;i<bnpclist.size();i++) {
            bnpclist.get(i).draw(gOffScreen);
        }

        if(animation_nt != null) {
            animation_nt.draw(gOffScreen);
            if(animation_nt.isFinish()) {
                animation_nt = null;
            }
        }
        if(animation_b != null) {
            animation_b.draw(gOffScreen);
            if(animation_b.isFinish()) {
                animation_b = null;
            }
        }
        if(animation_pt != null) {
            animation_pt.draw(gOffScreen);
            if(animation_pt.isFinish()) {
                animation_pt = null;
                animation_tc = new AnimationTC(GAME_WIDTH/2,GAME_HEIGHT-34);
                animation_tc.addListener(new IAFinish() {
                    @Override
                    public void finishPerformed() {
                        // TODO Auto-generated method stub
                        tank.setx(GAME_WIDTH / 2);
                        tank.sety(GAME_HEIGHT - 34);
                        tank.setDir(Direction.UP);
                    }
                });
            }
        }
        if(animation_tc != null) {
            animation_tc.draw(gOffScreen);
            if(animation_tc.isFinish()) {
                animation_tc = null;
                tank.setx(GAME_WIDTH/2);
                tank.sety(GAME_HEIGHT-34);
                tank.setDir(Direction.UP);
            }
        }

        //将第二缓存中的内容一次性全部绘制到屏幕上
        g.drawImage(offScreenImage, 0, 0, null);
    }

    private class PaintThread implements Runnable
    {
        public void run(){
            while(true){

                tank.move();
                for(NPCTank npc:npcList) {
                    npc.move();
                }

                Random rnd = new Random();
                int index = Math.abs(rnd.nextInt() % (npcList.size()*10));
                if(index < npcList.size()) {
                    Bullet b = npcList.get(index).fire();
                    if(b != null)
                        bnpclist.add(b);
                }


                for(int i=bplylist.size()-1;i>=0;i--) {
                    Bullet b = bplylist.get(i);
                    b.move();
                    if(b.getx() < 17 || b.getx() > GAME_WIDTH-17 ||
                            b.gety()<34  || b.gety() > GAME_HEIGHT-17) {
                        animation_b = b.explosive();
                        bplylist.remove(i);
                    }else {
                        for(int j=npcList.size()-1;j>=0;j--) {
                            if(b.isClollision(npcList.get(j))) {
                                animation_nt = npcList.get(j).explosive();
                                bplylist.remove(i);
                                npcList.remove(j);
                                break;
                            }
                        }
                    }
                }
                for(int i=0;i<bnpclist.size();i++) {
                    Bullet b = bnpclist.get(i);
                    b.move();
                    if(b.getx() < 17 || b.getx() > GAME_WIDTH-17 ||
                            b.gety()<34  || b.gety() > GAME_HEIGHT-17) {
                        animation_b = b.explosive();
                        bnpclist.remove(i);
                    }else {
                        if(b.isClollision(tank)) {
                            animation_pt = tank.explosive();
                            IAFinish l = () ->{
                                animation_tc = new AnimationTC(GAME_WIDTH/2,GAME_HEIGHT-34);
                                IAFinish listener = () ->{
                                    tank.setx(GAME_WIDTH / 2);
                                    tank.sety(GAME_HEIGHT - 34);
                                    tank.setDir(Direction.UP);
                                };
                                animation_tc.addListener(listener);
                            };
                            animation_pt.addListener(l);
                            tank.setx(-1000);
                        }
                    }
                }

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
            case KeyEvent.VK_LEFT: 	dir = Direction.LEFT;	break;
            case KeyEvent.VK_UP: 	dir = Direction.UP;		break;
            case KeyEvent.VK_RIGHT:	dir = Direction.RIGHT;	break;
            case KeyEvent.VK_DOWN: 	dir = Direction.DOWN;	break;
            case KeyEvent.VK_SPACE:
                Bullet bullet = tank.fire();
                if(bullet != null)
                    bplylist.add(bullet);
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