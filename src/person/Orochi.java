package person;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import ui.GameFrame;
import util.ImageUtil;
import util.Mp3Player;

public class Orochi extends BaseEnemy {

	private Random random=new Random();
	private int speed=5;
	
	public Orochi(int x,GameFrame ui,ZhangLiao zl,int HP,int boss){
		super(ui,zl,HP,boss);
		this.x=x;
		this.y=random.nextInt(143)+315;
		
		this.stand_leftImgs=new Image[27];
		this.stand_rightImgs=new Image[27];
		for(int i=0;i<stand_leftImgs.length;i++){
			this.stand_leftImgs[i]=ImageUtil.getImage("/imgs/Orochi/站立_左 ("+(i+1)+").png");
			this.stand_rightImgs[i]=ImageUtil.getImage("/imgs/Orochi/站立_右 ("+(i+1)+").png");
		}
		
		this.walk_leftImgs=new Image[5];
		this.walk_rightImgs=new Image[5];
		for(int i=0;i<walk_leftImgs.length;i++){
			this.walk_leftImgs[i]=ImageUtil.getImage("/imgs/Orochi/行走_左 ("+(i+1)+").png");
			this.walk_rightImgs[i]=ImageUtil.getImage("/imgs/Orochi/行走_右 ("+(i+1)+").png");
		}
		
		this.down_leftImgs=new Image[10];
		this.down_rightImgs=new Image[10];
		
		this.attack_leftImgs=new Image[11];
		this.attack_rightImgs=new Image[11];
		for(int i=0;i<attack_leftImgs.length;i++){
			this.attack_leftImgs[i]=ImageUtil.getImage("/imgs/Orochi/攻击_左 ("+(i+1)+").png");
			this.attack_rightImgs[i]=ImageUtil.getImage("/imgs/Orochi/攻击_右 ("+(i+1)+").png");
		}
		
		this.attack1_leftImgs=new Image[9];
		this.attack1_rightImgs=new Image[9];
		for(int i=0;i<attack1_leftImgs.length;i++){
			this.attack1_leftImgs[i]=ImageUtil.getImage("/imgs/Orochi/攻击1_左 ("+(i+1)+").png");
			this.attack1_rightImgs[i]=ImageUtil.getImage("/imgs/Orochi/攻击1_右 ("+(i+1)+").png");
		}
		
		this.attack2_leftImgs=new Image[62];
		this.attack2_rightImgs=new Image[62];
		for(int i=0;i<attack2_leftImgs.length;i++){
			this.attack2_leftImgs[i]=ImageUtil.getImage("/imgs/Orochi/攻击2_左 ("+(i+1)+").png");
			this.attack2_rightImgs[i]=ImageUtil.getImage("/imgs/Orochi/攻击2_右 ("+(i+1)+").png");
		}
		
		this.hit_leftImgs=new Image[3];
		this.hit_rightImgs=new Image[3];
		for(int i=0;i<hit_leftImgs.length;i++){
			this.hit_leftImgs[i]=ImageUtil.getImage("/imgs/Orochi/挨打_左 ("+(i+1)+").png");
			this.hit_rightImgs[i]=ImageUtil.getImage("/imgs/Orochi/挨打_右 ("+(i+1)+").png");
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
		super.setName("大  蛇");
	} 
	
	

	
	private void attackMusic() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/Orochi/脆弱的生命.mp3").play();
 			}
 		}.start();
 		
 	}
	
	private void attack1Music() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/Orochi/我与盖亚同在.mp3").play();
 			}
 		}.start();
 		
 	}
 	
	private void attack2Music() {
 		new Thread() {
 			public void run() {
 					new Mp3Player("/music/Orochi/阳光.mp3").play();
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
			
			super.stand(x-32, y-123, 80, 50, x-40, y-25);
		else
			super.stand(x-32, y-123, 80, 50, x-40, y-25);
	}
	
	private void attack(){
		if(!super.isAttack())stateflag=super.isRight();
		if(!isMusic) {
			attackMusic();
			isMusic=true;
		}
		if(stateflag)
			super.attack(x-110, y-175, 80, 50,x-40, y-25, 150, 50,x-60, y-25, new int[]{5,6},10);
		else
			super.attack(x-100, y-175, 80, 50, x-40, y-25, 150, 50,x-90, y-25, new int[]{5,6},10);
		if(super.getImgIndex()==attack_leftImgs.length-1){
			super.setState(0);
			super.setAttack(false);
			isMusic=false;
		}
	}

	private void attack1(){
		if(!super.isAttack1()) {
			stateflag=super.isRight();
		}
		if(!isMusic&&super.getImgIndex()==0) {
			attack1Music();
			isMusic=true;
		}
		if(stateflag)
			super.attack1(x-38, y-195, 0, 0,x-60, y-25, 50, 50, x+170, y-25, new int[]{4,5},10);
		else
			super.attack1(x-255, y-195, 0, 0, x-60, y-25, 50, 50, x-220, y-25, new int[]{4,5},10);
		
		
		if(super.getImgIndex()==attack1_leftImgs.length-1){
			super.setState(0);
			isMusic=false;
			super.setAttack1(false);
		}
	}
	
	private boolean flag1=false;
	private void attack2(){
		if(!super.isAttack2())stateflag=super.isRight();
		if(!isMusic) {
			isMusic=true;
			attackAnimation();
			flag1=false;
		}
		if(super.getImgIndex()==22&&!flag1) {
			attack2Music();
			flag1=true;
		}
		if(stateflag)
			super.attack2(x-100, y-200, 0, 0,x-60, y-25, 300, 220, x-100, y-200, new int[]{27,28},30);
		else
			super.attack2(x-200, y-200, 0, 0, x-60, y-25, 300, 220, x-200, y-200, new int[]{27,28},30);
			

		if(super.getImgIndex()==attack2_leftImgs.length-1){
			super.setState(0);
			super.setAttack2(false);
			isMusic=false;
		}
	}
	
	private void hit(){
		if(!super.isHit())stateflag=super.isRight();
		if(stateflag)
			super.hit(x-55,y-148);
		else
			super.hit(x-80,y-148);
		if(HP<=0)super.setIslive(false);
		if(super.getImgIndex()==hit_leftImgs.length-1){
			super.setState(0);
			super.setIshit(false);
			super.getZl().markadd();
			isMusic=false;
			if(++hitIndex==3){
				super.setState(4);
				super.setImgIndex(0);
				hitIndex=0;
			}
		}
	}
	private void down(){
		if(!super.isHitDown())stateflag=super.isRight();
		if(stateflag)
			super.down(x-200,y-175);
		else
			super.down(x-100,y-175);
		if(HP<=0&&super.getImgIndex()==7)super.setIslive(false);
		if(super.getImgIndex()==down_leftImgs.length-1){
			super.setState(0);
			super.setIshit(false);
			super.setHitDown(false);
			if(stateflag){
				x-=78;
				if(x<super.getUi().getBgx()+180)x=180;
			}
			else{
				x+=78;
				if(x>super.getUi().getBgx()+1907-200)x=super.getUi().getBgx()+1907-200;
			}
		}
	}
	
	private void walk(){
		if(super.isRight())
			super.walk(x-32, y-123, 80, 50, x-40, y-25);
		else
			super.walk(x-32, y-123, 80, 50, x-40, y-25);
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
