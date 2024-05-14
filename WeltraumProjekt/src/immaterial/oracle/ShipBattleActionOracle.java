package immaterial.oracle;

import immaterial.Game;
import space.buildable.ship.BattleShip;
import space.buildable.ship.Ship;
import space.inventory.item.weapon.CloseInWeaponSystem;
import space.inventory.item.weapon.NormalMissile;
import space.inventory.item.weapon.NovaSeekerMissile;
import space.inventory.item.weapon.StarshockMissile;

public class ShipBattleActionOracle extends BattleActionOracle{


	private static final long serialVersionUID = 1L;
	
	private double esacpeChance;
	private NormalMissile normalMissile = new NormalMissile();
	private CloseInWeaponSystem closeInWeaponSystem = new CloseInWeaponSystem();
	private NovaSeekerMissile novaSeekerMissile = new NovaSeekerMissile();
	private StarshockMissile starsShockMissile = new StarshockMissile();
	
	public ShipBattleActionOracle() {
		super.setType("Orakel der Schiffskampfaktionen");
		this.esacpeChance = 0.3;
	}
	

	//Hauptschleife die die Kämofe im Raumschiff steuert
	//Charaketr kann den Feind angreifen , versuchen zu fliehen, oder nichts tun
	//Wer anfängt wird ausgelost
	//Sobald die Lebenspunkte von einer der beiden Parteien auf null fallen, endet der Kampf
	//Beim Spieler danna auch das Spiel, da er auch im Raumschiff sitzt
	@Override
	public void run() {
		String input;
		double randomChance;
		boolean firstRound = true;
		
		BattleShip enemyShip = createRandomEnemyShip();
		while(true) {

			if(enemyShip.getHealth() <= 0) {
				break;
			}
			if(getGame().getPlayer().getCurrentShip().getHealth() <= 0) {
				break;
			}
			
			if(firstRound) {
				battleInformationAll(enemyShip);
				Game.INPUTORACLE.printBreakLine();
				System.out.println("Losen wer anfängt.. ");
				battleTime();
				randomChance = Math.random();
				if(randomChance < 0.5) {
					System.out.println("Feindliches Schiff darf anfangen!");
					System.out.println("Lade nächsten Zug... ");
					battleTime();
					System.out.println("Gegner ist am Zug!");
					enemyTurn(enemyShip);
				}else {
					System.out.println("Du darfst anfangen!");
				}
				battleTime();
				Game.INPUTORACLE.printBreakLine();
				firstRound=false;
			}
			System.out.println("Lade Zug des Spielers... ");
			battleTime();
			System.out.println("Spieler ist am Zug!");
			System.out.println("Was möchtest du jetzt machen?");
			System.out.println("1 -> Fliehen");
			System.out.println("2 -> Angreifen");
			System.out.println("Iregndwas anderes -> Nichts");
			input = Game.INPUTORACLE.inputEmptyCheck(getScanner());
			
			if(input.equals("1")) {
				randomChance = Math.random();
				if(randomChance <= esacpeChance) {
					System.out.println("Du bist erfolgreich geflohen!");
					break;
					
				}else {
					System.err.println("Fliehen fehl geschlagen!");
				}
				
				Game.INPUTORACLE.printBreakLine();
				
			}else if (input.equals("2")) {
				 enemyShip =playerTurn(enemyShip);
			}
			
			
			Game.INPUTORACLE.printBreakLine();
			if(enemyShip.getHealth() > 0) {
				enemyTurn(enemyShip);
			}
			
		}
		
	}
	
	//Zeige Statisken über die Schiffe an
	private void battleInformationAll(BattleShip enemyShip) {
		System.out.println("Lade Informationen... ");
		battleTime();
		Game.INPUTORACLE.printBreakLine();
		System.out.println("Schiff des Spielers <Info>");
		Game.INPUTORACLE.printBreakLine();
		battleInformation((BattleShip) getGame().getPlayer().getCurrentShip());
		Game.INPUTORACLE.printBreakLine();
		System.out.println("Schiff des Gegners <Info>");
		Game.INPUTORACLE.printBreakLine();
		battleInformation(enemyShip);
		
	}
	
	
	private void battleInformation(BattleShip ship) {
		for(String information: ship.getInformation()) {
			System.out.println(information);
		}
		
	}
	
	
	//Zug des Spielers
	//Er fügt seinem Gegner Schaden zu in Höhe der Stärke seines Raumschiffs (eventuell auch mit Waffen)
	//Eventuell auch mit kritischen Schaden
	private BattleShip playerTurn(BattleShip battleShip) {
		int criticalHit = (int) (Math.random() * (6));
		Game.INPUTORACLE.printBreakLine();
		BattleShip enemyShip = battleShip;
		BattleShip playerShip = (BattleShip) getGame().getPlayer().getCurrentShip();
		System.out.println("Spieler greift an!");
		int playerShipStrength = playerShip.getStrength();
		if(criticalHit >= 4) {
			int criticalHitNumber = (int) (playerShipStrength/3);
			criticalHitNumber+=playerShipStrength;
			System.out.println("Spieler fügt zusätzlichen kritischen Schaden zu " + criticalHitNumber);
			enemyShip=enemyShipTakesDamage(enemyShip,enemyShip.getHealth(), (playerShipStrength+criticalHitNumber));
		}else {
			enemyShip=enemyShipTakesDamage(enemyShip,enemyShip.getHealth(), playerShipStrength);
		}
		battleTime();
		System.out.println("Zug des Spielers beendet!");
		Game.INPUTORACLE.printBreakLine();
		return enemyShip;
	}
	
	//Das Gleiche wie oben, aber für den Gegner
	private void enemyTurn(BattleShip battleShip) {
		int criticalHit = (int) (Math.random() * (6));
		Game.INPUTORACLE.printBreakLine();
		System.out.println("Feindliches Schiff greift an!");
		battleTime();
		int playerShipHealth = getGame().getPlayer().getCurrentShip().getHealth();
		if(criticalHit >= 4) {
			int criticalHitNumber = (int) (battleShip.getStrength()/3);
			criticalHitNumber+=battleShip.getStrength();
			System.out.println("Gegner fügt zusätzlichen kritischen Schaden zu " + criticalHitNumber);
			playerShipTakesDamage(playerShipHealth, (battleShip.getStrength()+ criticalHitNumber));
		}else {
			playerShipTakesDamage(playerShipHealth, battleShip.getStrength());
		}
		battleTime();
		System.out.println("Zug des Gegners beendet!");
		Game.INPUTORACLE.printBreakLine();
		
	}
	
	
	//Spieler nimmt Schaden vom Gegner
	private void playerShipTakesDamage(int playerShipHealth, int battleShipStrength) {
		getGame().getPlayer().getCurrentShip()
		.setHealth(playerShipHealth - battleShipStrength);
		System.out.println("Schiff hat " + battleShipStrength + " Schaden erhalten");
		playerShipHealth = getGame().getPlayer().getCurrentShip().getHealth();
		
		if(playerShipHealth < 0) {
			playerShipHealth =0;
		}
		System.out.println("Das Schiff des Spielers hat nur noch " + playerShipHealth + " Lebenspunkte");
		
		Game.INPUTORACLE.printBreakLine();
	}
	
	
	//Gegner nimmt Schaden vom Spieler
	private BattleShip enemyShipTakesDamage(BattleShip battleShip,
			int battleShipHealth, int playerShipStrength) {
		
		BattleShip enemyShip = battleShip;
		enemyShip.setHealth(battleShipHealth - playerShipStrength);
		System.out.println("Feindliches Schiff hat " + playerShipStrength + " Schaden erhalten");
		if(enemyShip.getHealth() <0) {
			enemyShip.setHealth(0);
		}
		System.out.println("Das Schiff des Gegners hat nur noch " + enemyShip.getHealth() + " Lebenspunkte");
		Game.INPUTORACLE.printBreakLine();
		
		return enemyShip;
	}
	
	//Die Schiffe, die der Spieler begegnen kann, haben nicht immer alle die selben Werte
	//Einige werden mit Waffen ausgerüstet, bevor sie dem Spieler entgegentreten
	private BattleShip createRandomEnemyShip() {
		int randomShipNumber = (int) (Math.random() * (12));
		int randomShipNumber2 = (int) (Math.random() * (6));
		BattleShip enemyShip = new BattleShip();
		
		if(randomShipNumber == 0) {
			enemyShip.addWeapon(normalMissile);
		}else if(randomShipNumber == 1) {
			enemyShip.addWeapon(closeInWeaponSystem);
		}else if (randomShipNumber == 2) {
			enemyShip.addWeapon(novaSeekerMissile);
		}else if(randomShipNumber == 3) {
			enemyShip.addWeapon(starsShockMissile);
		}else if(randomShipNumber == 4 && randomShipNumber2 > 3) {
			enemyShip.addWeapon(novaSeekerMissile);
			enemyShip.addWeapon(normalMissile);
		}else if(randomShipNumber == 5 && randomShipNumber2 > 3) {
			enemyShip.addWeapon(normalMissile);
			enemyShip.addWeapon(closeInWeaponSystem);
		}else if(randomShipNumber == 6 && randomShipNumber2 > 4) {
			enemyShip.addWeapon(closeInWeaponSystem);
			enemyShip.addWeapon(starsShockMissile);
		}else if(randomShipNumber == 5 && randomShipNumber2 > 4) {
			enemyShip.addWeapon(novaSeekerMissile);
			enemyShip.addWeapon(starsShockMissile);
		}
		
		return enemyShip;
		
		
	}
	
	
	//Pause
	private void battleTime() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}

}
