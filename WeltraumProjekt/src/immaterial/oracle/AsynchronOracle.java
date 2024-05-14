package immaterial.oracle;

import java.util.Timer;
import java.util.TimerTask;

public class AsynchronOracle extends Oracle{
	

	private static final long serialVersionUID = 1L;

	public AsynchronOracle() {
		super.setType("Orakel der Asynchronität");
	}
	
	//Thread wird pausiert
	public void threadSleep(int seconds) throws InterruptedException{
            Thread.sleep(seconds); 
	}

}
