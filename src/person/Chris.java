package person;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import ui.GameFrame;
import util.ImageUtil;
import util.Mp3Player;

public class Chris extends BaseEnemy {

	private Random random=new Random();
	private int speed=4 ;
	
	public Chris(int x,GameFrame ui,ZhangLiao zl,int HP,int boss){
		super(ui,zl,HP,boss);
		this.x=x;
		this.y=random.nextInt(143)+315;
		
		this.stand_leftImgs=new Image[16];
		this.stand_rightImgs=new Image[16];
		for(int i=0;i<stand_leftImgs.length;i++){
			this.stand_leftImgs[i]=ImageUtil.getImage("/imgs/Chris/站立_左 ("+(i+1)+").png");
			this.stand_rightImgs[i]=ImageUtil.getImage("/imgs/Chris/站立_右 ("+(i+1)+").png");
		}
		
		this.walk_leftImgs=new Image[6];
		this.walk_rightImgs=new Image[6];
		for(int i=0;i<walk_leftImgs.length;i++){
			this.walk_leftImgs[i]=ImageUtil.getImage("/imgs/Chris/行走_左 ("+(i+1)+").png");
			this.walk_rightImgs[i]=ImageUtil.getImage("/imgs/Chris/行走_右 ("+(i+1)+").png");
		}
		
		this.down_leftImgs=new Image[16];
		this.down_rightImgs=new Image[16];
		for(int i=0;i<down_leftImgs.length;i++){
			this.down_leftImgs[i]=ImageUtil.getImage("/imgs/Chris/倒地_左 ("+(i+1)+").png");
			this.down_rightImgs[i]=ImageUtil.getImage("/imgs/Chris/倒地_右 ("+(i+1)+").png");
		}
		
		this.attack_leftImgs=new Image[99];
		this.attack_rightImgs=new Image[99];
		for(int i=0;i<attack_leftImgs.length;i++){
			this.attack_leftImgs[i]=ImageUtil.getImage("/imgs/Chris/克里斯_左 ("+(i+1)+").png");
			this.attack_rightImgs[i]=ImageUtil.getImage("/imgs/Chris/克里斯_右 ("+(i+1)+").png");
		}
		
		this.attack1_leftImgs=new Image[42];
		this.attack1_rightImgs=new Image[42];
		for(int i=0;i<attack1_leftImgs.length;i++){
			this.attack1_leftImgs[i]=ImageUtil.getImage("/imgs/Chris/攻击1_左 ("+(i+1)+").png");
			this.attack1_rightImgs[i]=ImageUtil.getImage("/imgs/Chris/攻击1_右 ("+(i+1)+").png");
		}
		
		this.attack2_leftImgs=new Image[66];
		this.attack2_rightImgs=new Image[66];
		for(int i=0;i<attack2_leftImgs.length;i++){
			this.attack2_leftImgs[i]=ImageUtil.getImage("/imgs/Chris/攻击2_左 ("+(i+1)+").png");
			this.attack2_rightImgs[i]=ImageUtil.getImage("/imgs/Chris/攻击2_右 ("+(i+1)+").png");
		}
		
		this.hit_leftImgs=new Image[5];
		this.hit_rightImgs=new Image[5];
		for(int i=0;i<hit_leftImgs.length;i++){
			this.hit_leftImgs[i]=ImageUtil.getImage("/imgs/Chris/挨打_左 ("+(i+1)+").png");
			this.hit_rightImgs[i]=ImageUtil.getImage("/imgs/Chris/挨打_右 ("+(i+1)+").png");
		}
		
		Image[][]imgs={stand_leftImgs,stand_rightImgs,
				   walk_leftImgs,walk_rightImgs,
				   down_leftImgs,down_rightImgs,
				   attack_leftImgs,attack_rightImgs,
				   attack1_leftImgs,attack1_rightImgs,
				   attack2_leftImgs,attack2_rightImgs,
				   hit_leftImgs,hit_rightImgs};
		stand();
		super.setImgs(imgs);	
		super.setName("克里斯");
	} 
	
	private void attackMusic() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/Chris/克里斯阳光 .mp3").play();
 			}
 		}.start();
 		
 	}
	private void attackbgMusic() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/Chris/克里斯阳光bg.mp3").play();
 			}
 		}.start();
 		
 	}
	
	private void hitMusic() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/Chris/挨打.mp3").play();
 			}
 		}.start();
 		
 	}
	
	private void haiMusic() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/Chris/嗨.mp3").play();
 			}
 		}.start();
 		
 	}

	private void attack1Music() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/Chris/火焰.mp3").play();
 			}
 		}.start();
 		
 	}
 	
	private void attack2Music() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/Chris/去世.mp3").play();
 			}
 		}.start();
 		
 	}
 	
	private void attackAnimation() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/Chris/前摇.mp3").play();
 			}
 		}.start();
 		
 	}
	
	private void stand(){
		if(super.isRight())
			super.stand(x-30, y-103, 80, 40, x-40, y-20);
		else
			super.stand(x-30, y-103, 80, 40, x-40, y-20);
	}
	
	private boolean flag1=false;
	private void attack(){
		if(!super.isAttack())stateflag=super.isRight();
		if(!isMusic) {
			attackAnimation();
			isMusic=true;
			flag1=false;
		}
		if(super.getImgIndex()==45&&!flag1) {
			attackMusic();
			attackbgMusic();
			flag1=true;
		}
		if(stateflag)
			super.attack(x-110, y-200, 0, 0,x-50, y-20, 304, 224, x-110, y-200, new int[]{67},30);
		else
			super.attack(x-190, y-200, 0, 0, x-50, y-20, 304, 224, x-190, y-200, new int[]{67},30);
		if(super.getImgIndex()==attack_leftImgs.length-1){
			super.setState(0);
			super.setAttack(false);
			isMusic=false;
		}
	}

	private void attack1(){
		if(!super.isAttack1())stateflag=super.isRight();
		if(!isMusic&&super.getImgIndex()==10) {
			attack1Music();
			isMusic=true;
		}
		if(stateflag)
			super.attack1(x-45, y-105, 0, 0, x-50, y-20, 200, 40, x, y-20, new int[]{24,25},12);
		else
			super.attack1(x-240, y-105, 0, 0, x-50, y-20, 200, 40, x-200, y-20, new int[]{4,5},12);
		if(super.getImgIndex()==attack1_leftImgs.length-1){
			super.setState(0);
			if(stateflag)this.x=this.x+25;
			else this.x=this.x-25;
			super.setAttack1(false);
			isMusic=false;
		}
	}
	
	private boolean flag2=false;
	private void attack2(){
		if(!super.isAttack2())stateflag=super.isRight();
		if(!isMusic) {
			isMusic=true;
			haiMusic();
			flag2=false;
		}
		if(super.getImgIndex()==34&&!flag2) {
			attack2Music();
			flag2=true;
		}
		if(stateflag)
			super.attack2(x-83, y-210, 0, 0,x-60, y-25, 100, 50, x+50, y-25, new int[]{47,48},20);
		else
			super.attack2(x-167, y-210, 0, 0, x-60, y-25, 100, 50, x-150, y-25, new int[]{47,48},20);
		if(super.getImgIndex()==attack2_leftImgs.length-1){
			super.setState(0);
			if(stateflag)this.x=this.x+76;
			else this.x=this.x-76;
			super.setAttack2(false);
			isMusic=false;
			
		}
	}
	
	private void hit(){
		if(!super.isHit())stateflag=super.isRight();
		if(!isMusic) {
			isMusic=true;
			hitMusic();
		}
		if(stateflag)
			super.hit(x-55,y-103);
		else
			super.hit(x-50,y-103);
		if(HP<=0)super.setState(6);
		if(super.getImgIndex()==hit_leftImgs.length-1){
			super.setIshit(false);
			super.setState(0);
			super.setHit(false);
			isMusic=false;
			super.getZl().markadd();
			if(++hitIndex==3){
			super.setState(6);
			hitIndex=0;
			}
		}
		
	}
	private void down(){
		if(!super.isHitDown())stateflag=super.isRight();
		if(stateflag)
			super.down(x-180,y-145);
		else
			super.down(x-40,y-145);
		if(HP<=0&&super.getImgIndex()==7)super.setIslive(false);
		if(super.getImgIndex()==down_leftImgs.length-1){
			if(random.nextBoolean())super.setState(0);
			else super.setState(3);
			super.setHitDown(false);
			if(stateflag){
				x-=100;
				if(x<super.getUi().getBgx()+180)x=180;
			}
			else{
				x+=100;
				if(x>super.getUi().getBgx()+1907-200)x=super.getUi().getBgx()+1907-200;
			}
		}
	}
	
	private void walk(){
		if(super.isRight())
			super.walk(x-40, y-103, 80, 40, x-50, y-20);
		else
			super.walk(x-40, y-103, 80, 40, x-50, y-20);
		if(x<super.getUi().getBgx()+180)left=false;
		if(x>super.getUi().getBgx()+1907-200)right=false;
		if(y<=320)up=false;
		if(y>=super.getUi().getHeight()-10)down=false;
		if(left)x-=speed;
		if(right)x+=speed;
		if(up)y-=speed/2;
		if(down)y+=speed/2;
	}
	
	public void draw(Graphics g){
		//g.fillRect(x, y, 10, 10);
		super.draw(g);
		if(super.getZl().attackRect().intersects(getRect())){
			super.setState(5);
		}
		switch(super.getState()){
		case 0:
			stand();
			break;
		case 1:
			walk();
			break;
		case 2:
			attack();
			break;
		case 3:
			attack1();
			break;
		case 4:
			attack2();
			break;
		case 5:
			hit();
			break;
		case 6:
			down();
			break;
			
		}
		
	}
}
