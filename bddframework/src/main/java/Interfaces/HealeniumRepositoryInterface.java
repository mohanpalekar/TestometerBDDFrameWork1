package Interfaces;

import java.io.File;
import java.io.IOException;

import Utilities.FailedToStartHealeniumServer;
import Utilities.InvalidPortException;


public interface HealeniumRepositoryInterface {
	
	long [] startHealeniumServer(File [] file, String command) throws IOException, InterruptedException, FailedToStartHealeniumServer, InvalidPortException;
	
	void stopHealeniumServer(File logFile, String command) throws IOException, InterruptedException;

}
