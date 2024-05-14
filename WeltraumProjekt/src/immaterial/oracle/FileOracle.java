package immaterial.oracle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Scanner;

import immaterial.Game;

public class FileOracle extends Oracle {

	private static final long serialVersionUID = 1L;
	//Pfade wo die Daten gespeichert werden
	private final static String PLANETCREATEPATH = "data/planetCreate";
	private final static String SUNCREATEPATH = "data/sunCreate";
	private final static String MOONCREATEPATH = "data/moonCreate";
	private final static File[] FILES = { new File("./" + PLANETCREATEPATH + ".txt"),
			new File("./" + SUNCREATEPATH + ".txt"), new File("./" + MOONCREATEPATH + ".txt")

	};

	//Hier sind die Informationen für die Planten, Sonnen und Monde festgelegt
	//Diese Daten basieren auf keinen ECHTEN Daten und sind frei erfunden
	//Die Daten werden in Files nieder geschrieben -> Später ausgelesen
	//Basierend daruf werden dann die Sonnensysteme erstellt -> Zufälige Erstellung
	private final static String PLANETDATA = "Terra 1.0 1.0 1.0 0 3\n" + "Draconis 0.8 0.6 0.7 1 1\n"
			+ "Gaia 1.2 1.2 1.1 2 2\n" + "Aetheria 0.9 0.5 0.6 1 8\n" + "Novaria 1.1 1.3 1.2 2 8\n"
			+ "Vulcan 0.7 0.4 0.5 1 10\n" + "Eden 1.3 1.5 1.3 0 6\n" + "Oasis 1.0 0.9 0.9 0 2\n"
			+ "Inferno 0.6 0.3 0.4 1 10\n" + "Aurora 1.2 1.1 1.1 0 9\n" + "Celestia 0.8 0.7 0.7 0 1\n"
			+ "Serenity 1.0 0.8 0.8 1 6\n" + "Agora 0.9 0.6 0.6 1 3\n" + "Zephyr 1.1 1.0 1.0 1 8\n"
			+ "Infernum 0.7 0.5 0.5 2 10\n" + "Seraphim 1.3 1.2 1.1 2 5\n" + "Phoenix 0.8 0.6 0.6 1 10\n"
			+ "Elysium 1.2 1.1 1.1 0 2\n" + "Nemesis 0.9 0.7 0.7 2 8\n" + "Halcyon 1.1 0.9 0.9 0 1\n"
			+ "Helios 0.7 0.4 0.4 1 3\n" + "Arcadia 1.2 1.0 1.0 1 9\n" + "Abyss 0.8 0.5 0.5 1 6\n"
			+ "Zenith 1.1 0.9 0.9 2 0\n" + "Aeolus 1.2 1.1 1.0 0 0\n" + "Hades 0.8 0.6 0.6 2 5\n"
			+ "Utopia 1.1 1.0 1.0 1 7\n" + "Olympus 0.7 0.4 0.4 1 5\n" + "Bambeya 1.2 0.6 0.3 1 7\n"
			+ "Zestia 0.8 0.7 0.5 0 0\n" + "Homeria 1.3 0.7 0.6 0 0\n";

	private final static String SUNDATA = "Sol 1.0 1.0 5778 1.0 4\n" + "Sirius 2.0 1.5 9940 23.6 1\n"
			+ "Proxima 0.12 0.15 3042 0.0017 3\n" + "Alpha 1.5 2.0 10370 80.4 0\n"
			+ "Betelgeuse 20.0 1.0 3130 120000 5\n" + "Vega 2.1 2.7 9602 40.1 2\n" + "Pollux 1.9 8.8 4736 36.0 4\n"
			+ "Arcturus 1.1 25.4 4290 170.0 2\n" + "Deneb 19.0 203.0 8520 196000 1\n"
			+ "Regulus 3.5 3.8 12100 288.0 5\n" + "Antares 15.0 680.0 3590 64000 0\n"
			+ "Rigel 23.0 78.9 12100 120000 3\n" + "Aldebaran 1.1 44.2 3910 400.0 1\n"
			+ "Spica 10.0 7.0 22400 2140.0 0\n" + "Bellatrix 6.0 4.0 22000 800.0 0\n"
			+ "Polaris 5.5 45.0 5800 430.0 0\n" + "Fomalhaut 1.8 1.6 8600 17.9 0\n"
			+ "Achernar 6.2 11.0 15000 850.0 0\n" + "Alnilam 40.0 20.0 27900 375000 0\n"
			+ "Alkaid 2.0 3.0 14000 45.0 1\n" + "Denebola 2.8 2.2 11000 16.0 1\n" + "Mirfak 5.0 5.0 6200 500.0 1\n"
			+ "Wezen 40.0 25.0 18000 140000 1\n" + "Alphard 7.5 2.0 24000 400.0 1\n" + "Alcyone 4.0 4.0 8000 250.0 0\n"
			+ "Mirach 3.0 5.0 9000 220.0 0\n" + "Hamal 2.5 2.0 6700 40.0 0\n" + "Menkar 5.0 7.0 5200 550.0 0\n"
			+ "Mirzam 15.0 10.0 18700 14000 0\n";

	private final static String MOONDATA = "Luna 1.0 1.0 1.0 1 0\n" + "Phobos 0.8 0.6 0.7 2 1\n"
			+ "Ganymede 1.2 1.2 1.1 2 2\n" + "Deimos 0.7 0.4 0.5 1 0\n" + "Io 1.3 1.5 1.3 2 1\n"
			+ "Callisto 1.0 0.9 0.9 1 2\n" + "Enceladus 0.6 0.3 0.4 1 2\n" + "Triton 1.2 1.1 1.1 1 1\n"
			+ "Charon 0.8 0.7 0.7 1 0\n" + "Phoebe 1.0 0.8 0.8 1 2\n" + "Rhea 0.9 0.6 0.6 1 1\n"
			+ "Lapetus 1.1 1.0 1.0 2 1\n" + "Miranda 0.7 0.5 0.5 2 2\n" + "Titania 1.3 1.2 1.1 2 2\n"
			+ "Umbriel 0.8 0.6 0.6 1 0\n" + "Dione 1.1 1.0 1.0 2 1\n" + "Ariel 0.7 0.4 0.4 1 0\n"
			+ "Proteus 1.2 1.0 1.0 1 0\n" + "Nereid 0.8 0.5 0.5 1 2\n" + "Oberon 1.1 0.9 0.9 2 2\n"
			+ "Charon 1.2 1.1 1.0 2 1\n" + "Puck 0.8 0.6 0.6 1 0\n" + "Caliban 1.1 1.0 1.0 2 0\n"
			+ "Sycorax 0.7 0.4 0.4 1 1\n" + "Hyperion 1.0 1.0 1.0 1 2\n" + "Amalthea 0.8 0.6 0.7 1 1\n"
			+ "Mimas 1.2 1.2 1.1 2 1\n" + "Tethys 0.9 0.5 0.6 1 0\n" + "Thebe 1.1 1.3 1.2 1 0\n"
			+ "Rhea 0.7 0.4 0.5 2 1\n" + "Miranda 1.3 1.5 1.3 2 1\n" + "Callirrhoe 1.0 0.9 0.9 1 1\n"
			+ "Paaliaq 0.6 0.3 0.4 1 1\n" + "Ymir 1.2 1.1 1.1 2 2\n" + "Carme 0.8 0.7 0.7 1 1\n"
			+ "Ananke 1.0 0.8 0.8 1 1\n" + "Kalyke 0.9 0.6 0.6 1 2\n" + "Orthosie 1.1 1.0 1.0 2 0\n"
			+ "Herse 0.7 0.5 0.5 2 0\n" + "Aitne 1.3 1.2 1.1 2 2\n" + "Eurydome 0.8 0.6 0.6 1 1\n"
			+ "Autonoe 1.1 1.0 1.0 2 0\n" + "Thyone 0.7 0.4 0.4 1 0\n" + "Hermippe 1.2 1.1 1.0 1 1\n"
			+ "Aedea 0.8 0.5 0.5 1 0\n" + "Euanthe 1.1 0.9 0.9 2 2\n" + "Helike 0.7 0.4 0.4 1 2\n"
			+ "Orthosie 1.2 1.0 1.0 1 0\n" + "Hegemone 0.8 0.6 0.6 1 1\n" + "Philophrosyne 1.1 1.0 1.0 2 1\n"
			+ "Dia 0.7 0.4 0.4 1 1\n" + "Sponde 1.2 1.0 1.0 1 0\n" + "Kale 0.8 0.5 0.5 1 1\n"
			+ "Pasithee 1.1 0.9 0.9 2 2\n" + "Megaclite 0.7 0.4 0.4 1 2\n" + "Selene 1.0 1.0 1.0 1 1\n"
			+ "Atlas 0.8 0.6 0.7 1 1\n" + "Eos 1.2 1.2 1.1 2 2\n" + "Helios 0.7 0.4 0.5 2 2\n"
			+ "Artemis 1.3 1.5 1.3 1 0\n" + "Asteria 1.0 0.9 0.9 2 0\n" + "Cronus 0.6 0.3 0.4 1 1\n"
			+ "Phoebe 1.2 1.1 1.1 2 2\n" + "Diana 0.8 0.7 0.7 1 1\n" + "Apollo 1.0 0.8 0.8 1 0\n"
			+ "Aurome 0.9 0.6 0.6 1 0\n" + "Hyperion 1.1 1.0 1.0 2 1\n" + "Gama 0.7 0.5 0.5 2 2\n"
			+ "Athena 1.3 1.2 1.1 0 0\n" + "Demeter 0.8 0.6 0.6 1 1\n" + "Hera 1.1 1.0 1.0 2 0\n"
			+ "Eros 0.8 0.5 0.5 1 2\n" + "Hermes 1.2 1.0 1.0 1 0\n" + "Hestia 0.8 0.5 0.5 1 2\n"
			+ "Icarus 1.1 0.9 0.9 2 2\n" + "Iris 0.7 0.4 0.4 1 1\n" + "Janus 1.2 1.0 1.0 1 2\n"
			+ "Metis 0.8 0.6 0.6 1 1\n" + "Nemos 1.1 1.0 1.0 2 0\n" + "Pan 0.7 0.5 0.5 2 0\n"
			+ "Perseus 1.3 1.2 1.1 0 0\n" + "Tethys 1.1 1.0 1.0 2 2\n" + "Themis 0.7 0.4 0.4 1 0\n"
			+ "Zeus 1.2 1.1 1.0 1 0\n" + "Hephaestus 1.1 0.9 0.9 2 1\n" + "Nyx 0.7 0.4 0.4 1 1\n"
			+ "Achilles 1.2 1.0 1.0 1 0\n" + "Ariadne 0.8 0.5 0.5 1 1\n";

	
	public FileOracle() {
		super.setType("Orakel der Dateien");
	}  
	
	public String getPlanetCreatePath() {
		return PLANETCREATEPATH;
	}

	public String getSunCreatePath() {
		return SUNCREATEPATH;
	}

	public String getMoonCreatePath() {
		return MOONCREATEPATH;
	}

	//Testet ob Daten schon vorhanden sind
	//Wenn nicht, dann werden sie geschrieben
	public void testFiles() throws IOException {

		String[] textData = new String[FILES.length];
		textData[0] = PLANETDATA;
		textData[1] = SUNDATA;
		textData[2] = MOONDATA;

		for (int i = 0; i < FILES.length; i++) {
			if (!FILES[i].exists()) {
				createFileAndWrite(FILES[i], textData[i]);
			}
		}
	}

	//Schreibe eine File
	public void createFileAndWrite(File path, String text) throws IOException {
		path.createNewFile();
		FileWriter writer = new FileWriter(path);
		writer.write(text);
		writer.close();

	}

	//Lese eine File
	public LinkedList<String> readFile(String path) {
		LinkedList<String> rows = null;
		try {
			File datei = new File(path);
			Scanner scanner = new Scanner(datei);
			rows = new LinkedList<>();
			while (scanner.hasNextLine()) {
				rows.add(scanner.nextLine());
			}
			scanner.close();
			return rows;
		} catch (FileNotFoundException e) {
			System.out.println("Datei nicht gefunden: " + e.getMessage());
		}

		return rows;
	}
	
	//Speichert das Spiel
	//Speichern ist nach Erstellung der Welt, sowie im Haupt-& und LocalMenü möglich
	//Speichern wird über Serializable realisiert
	public void saveGame(Scanner scanner, Game game) {
		Runnable runnable = () -> System.out.println("Aktuellen Fortschritt speichern? "
	            + "1 -> Ja, Irgendwas anderes -> Nein");
	    boolean output = Game.ASKORACLE.trueFalseQuestion(scanner, runnable);
	    if(output) {
	    	try {
	            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("spielstand.ser"));
	            out.writeObject(game);
	            out.close();
	            System.out.println("Spielstand erfolgreich gespeichert.");
	        } catch (IOException e) {
	            System.err.println("Fehler beim Speichern des Spielstands: " + e.getMessage());
	        }
	    	
	    }
	}
	
	//Spiel wird versucht zu laden --> Prüft .ser Datei
	//Falls nicht vorhanden, fängt der Spieler von vorne an
	public Game loadGame() {
		Game loadedGame = null;
		try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("spielstand.ser"));
            loadedGame = (Game) in.readObject();
            in.close();
            System.out.println("Spielstand erfolgreich geladen:");
            return loadedGame;
         
        } catch (IOException | ClassNotFoundException e) {
            //System.err.println("Fehler beim Laden des Spielstands: " + e.getMessage());
        }
		
		return loadedGame;
	}

}
