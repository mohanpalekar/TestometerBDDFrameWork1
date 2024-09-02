package Utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.input.ReversedLinesFileReader;

import Interfaces.HealeniumRepositoryInterface;

public class HealeniumRepository implements HealeniumRepositoryInterface{

	@Override
	public long [] startHealeniumServer(File [] file, String command) throws IOException, InterruptedException, InvalidPortException, FailedToStartHealeniumServer {

		long [] serverData = new long[2];

		int retry = 0;

		Logs.getLog().getLogger().info("{HealeniumRepository} trying to start healenium server");

		runCommand(command);

		serverData = getServerData(file[1]);

		if(serverData[0] != -1) {
			return serverData;
		}else {
			while(retry != 5) {
				Files.deleteIfExists(file[1].getAbsoluteFile().toPath());
				Logs.getLog().getLogger().info("{HealeniumRepository} Re-trying to start healenium server (count) "+retry);
				Logs.getLog().getLogger().info("{HealeniumRepository} does Healenium log file exist ? "+file[1].exists());
				updatePort(file[0], getRandomNumber());
				runCommand(command);
				serverData = getServerData(file[1]);
				if(serverData[0] != -1) {
					return serverData;
				}else {
					retry++;
					continue;
				}
			}
			if(retry == 5) {
				throw new FailedToStartHealeniumServer("{HealeniumRepository} FailedToStartHealeniumServer");
			}
		}
		return serverData;

	}


	@Override
	public void stopHealeniumServer(File logs, String command) throws IOException, InterruptedException {

		Logs.getLog().getLogger().info("{HealeniumRepository} stopping healenium server ...");

		runCommand(command);
		
		Files.deleteIfExists(logs.getAbsoluteFile().toPath());
		
		Logs.getLog().getLogger().info("{HealeniumRepository} does Healenium log file exist ? "+logs.exists());

	}


	private long[] getServerData(File file) throws InvalidPortException, IOException {

		long [] serverData = new long[2];

		long PID = -1;

		long portNumber = -1;

		if(file.exists()) {

			try (ReversedLinesFileReader fr = ReversedLinesFileReader.builder()
					.setPath(file.getAbsolutePath())
					.setBufferSize(4096)
					.setCharset(StandardCharsets.UTF_8)
					.get()) {

				String ch;

				while((ch = fr.readLine()) != null) {

					if(ch.contains("Tomcat started on port")) {
						
						Logs.getLog().getLogger().info("{HealeniumRepository}  : --> "+ch);

						Logs.getLog().getLogger().info("{HealeniumRepository} isServerRunnng ? : Yes");

						String processID = ch.strip().substring(30, (ch.length()-108));
						PID = Integer.parseInt(processID.strip());
						serverData[0] = PID;

						Logs.getLog().getLogger().info("{HealeniumRepository} PID is "+PID);

						switch(processID.strip().length()) {

						case 3:
							portNumber = Integer.parseInt(ch.strip().substring(108, 112));
							Logs.getLog().getLogger().info("{HealeniumRepository} portNumber is "+portNumber);
							break;

						case 4:
							portNumber = Integer.parseInt(ch.strip().substring(109, 113));
							Logs.getLog().getLogger().info("{HealeniumRepository} portNumber is "+portNumber);
							break;

						case 5:
							portNumber = Integer.parseInt(ch.strip().substring(110, 114));
							Logs.getLog().getLogger().info("{HealeniumRepository} portNumber is "+portNumber);
							break;

						default: throw new InvalidPortException("ERROR --> {HealeniumRepository} port not found");

						}

						Logs.getLog().getLogger().info("{HealeniumRepository} Tomcat running on port "+portNumber);
						serverData[1] = portNumber;
						break;

					}
				}
			}
		}else {
			throw new FileNotFoundException("ERROR --> File Not Found "+file.getAbsolutePath());
		}
		return serverData;
	}

	private long getRandomNumber() {
		while(true) {
			Random random = new Random();
			long number = random.nextInt((9999 - 100) + 1) + 10;
			if(number <1000) {
				continue;
			}
			else return number;
		}
	}


	private void updatePort(File f1, long portNumber) throws IOException {

		List<String> lines = new ArrayList<String>();
		String line = null;

		try(FileReader fr = new FileReader(f1)){

			try(BufferedReader br = new BufferedReader(fr)){

				while ((line = br.readLine()) != null) {
					if (line.contains("HLM_SERVER_PORT=")) {
						String portNumberInFile = line.strip().substring(16, 20).strip();
						Logs.getLog().getLogger().info("{HealeniumRepository} portNumberInFile : "+portNumberInFile);
						line = line.replace("HLM_SERVER_PORT="+portNumberInFile, "HLM_SERVER_PORT="+portNumber);

						Logs.getLog().getLogger().info("{HealeniumRepository} updated port to "+portNumber);
					}
					lines.add(line);
				}
			}

			try(FileWriter fw = new FileWriter(f1)){
				try(BufferedWriter pw = new BufferedWriter(fw)){

					for(String s : lines) {
						pw.write(s);
						pw.newLine();
					}
				}
			}
		}
	}


	private void runCommand(String command) throws IOException, InterruptedException {

		Logs.getLog().getLogger().info("{HealeniumRepository} command is : "+command);

		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c" +command);

		builder.redirectErrorStream(true);

		java.lang.Process p = builder.start();

		try(BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()))){

			Logs.getLog().getLogger().info("{HealeniumRepository} response from command prompt is : "+r.readLine());

		}

		p.waitFor();

		Thread.sleep(10000);

	}


}
