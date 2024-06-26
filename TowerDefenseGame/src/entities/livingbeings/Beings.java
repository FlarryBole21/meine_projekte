package entities.livingbeings;

import entities.bases.Bases;
import game.GamePanel;

public enum Beings {
	
	FRIENDLY_NORMAL_LIZARD(new NormalLizard(Bases.FRIENDLY_CAVE.getBase().getRect().getWidth()-70, 570, 100, 100, 10, 250, true)),
	ENEMY_NORMAL_LIZARD(new NormalLizard(GamePanel.SCREENSIZE.width-Bases.ENEMY_CAVE.getBase().getRect().getWidth()-30,
			570, 100, 100, 10, 250, false)),
	FRIENDLY_INTERMEDIATE_LIZARD(new IntermediateLizard(Bases.FRIENDLY_CAVE.getBase().getRect().getWidth()-70, 570, 100, 100, 20, 500, true)),
	ENEMY_INTERMEDIATE_LIZARD(new IntermediateLizard(GamePanel.SCREENSIZE.width-Bases.ENEMY_CAVE.getBase().getRect().getWidth()-30,
			570, 100, 100, 20, 500, false)),
	FRIENDLY_ADVANCED_LIZARD(new AdvancedLizard(Bases.FRIENDLY_CAVE.getBase().getRect().getWidth()-70, 570, 100, 100, 40, 1000, true)),
	ENEMY_ADVANCED_LIZARD(new AdvancedLizard(GamePanel.SCREENSIZE.width-Bases.ENEMY_CAVE.getBase().getRect().getWidth()-30,
			570, 100, 100, 40, 1000, false)),
	FRIENDLY_NORMAL_SPIDER(new NormalSpider(Bases.FRIENDLY_CAVE.getBase().getRect().getWidth()-70, 590, 100, 100, 80, 2000, true)),
	ENEMY_NORMAL_SPIDER(new NormalSpider(GamePanel.SCREENSIZE.width-Bases.ENEMY_CAVE.getBase().getRect().getWidth()-30,
	        590, 100, 100, 80, 2000, false)),
	FRIENDLY_NORMAL_BEAR(new NormalBear(Bases.FRIENDLY_CAVE.getBase().getRect().getWidth()-70, 570, 100, 100, 160, 4000, true)),
	ENEMY_NORMAL_BEAR(new NormalBear(GamePanel.SCREENSIZE.width-Bases.ENEMY_CAVE.getBase().getRect().getWidth()-30,
	        570, 100, 100, 160, 4000, false));

	
	
	private LivingBeing being;
	private int xPos;
	private int yPos;
	private int width;
	private int heigth;
	private int attack;
	private double health;
	private boolean friendly;
	private int waitingDistance;
	
	Beings(LivingBeing being){
		this.being=being;
		this.xPos=being.getRect().getX();
		this.yPos=being.getRect().getY();
		this.width=being.getRect().getWidth();
		this.heigth=being.getRect().getHeight();
		this.attack=being.getAttack();
		this.health=being.getHealth();
		this.friendly=being.isFriendly();
		this.waitingDistance=being.getWaitingDistance();

	}
	
	public int getAttack() {
		return attack;
	}

	public double getHealth() {
		return health;
	}


	public boolean isFriendly() {
		return friendly;
	}


	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public int getWidth() {
		return width;
	}


	public int getHeigth() {
		return heigth;
	}


	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}



	public LivingBeing getBeing() {
		return being;
	}

	public void setBeing(LivingBeing being) {
		this.being = being;
	}

	public int getWaitingDistance() {
		return waitingDistance;
	}


}
