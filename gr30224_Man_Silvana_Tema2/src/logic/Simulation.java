package logic;

import java.util.ArrayList;

import model.*;
import view.*;

public class Simulation implements Runnable {
	private ArrayList<Task> tasks;
	private static ArrayList<Server> servers;

	private int nrServers;
	private int minArrival;
	private int maxArrival;
	private int minProcessing;
	private int maxProcessing;
	private int minSimulation;
	private int maxSimulation;
	private FrameSimulator frame;

	public Simulation(int nrServers, int minArrival, int maxArrival, int minProcessing, int maxProcessing,
			int minSimulation, int maxSimulation, FrameSimulator frame) {
		super();
		this.nrServers = nrServers;
		this.minArrival = minArrival;
		this.maxArrival = maxArrival;
		this.minProcessing = minProcessing;
		this.maxProcessing = maxProcessing;
		this.minSimulation = minSimulation;
		this.maxSimulation = maxSimulation;
		this.frame = frame;
	}

	public boolean isEmpty() {
		for (Server s : servers)
			if (!s.isEmpty())
				return false;
		return true;
	}

	public static Server strategyTime() {
		Server s;
		s = servers.get(0);

		for (int i = 1; i < servers.size(); i++) {
			if (s.getWaitingPeriod() > servers.get(i).getWaitingPeriod())
				s = servers.get(i);
		}
		return s;

	}

	public static Server strategyQueue() {
		Server s;
		s = servers.get(0);

		for (int i = 1; i < servers.size(); i++) {
			if (s.getSize() > servers.get(i).getSize()) {
				s = servers.get(i);
			}
		}
		return s;

	}

	public void run() {

		servers = new ArrayList<Server>();

		for (int i = 0; i < nrServers; i++) {
			////adauga servere
			servers.add(new Server(i));
		}

		this.tasks = GeneratorTasks.generateRandomTasks(minArrival, maxArrival, minProcessing, maxProcessing,
				minSimulation, maxSimulation);
		frame.createPanel(nrServers, tasks.size());
		//////genereza taskuri pt servere
		int time = minSimulation;

		while (true) {

			for (Server s : servers) {
				if (!s.isEmpty())
					s.getFirst().decProcessing();
			}
			frame.afisare(servers, time);

			for (Server s : servers) 
				if (!s.isEmpty()) {
					Task first = s.getFirst();
					if (first.getProcessingTime() == 0) {
						s.incWaitingTime(first.getProcessingTime());
						frame.writeLog(first.iesireToString(s.getID()));
						s.removeTask();
					}
					frame.afisare(servers, time);

				}

				while (!tasks.isEmpty() && tasks.get(0).getArrivalTime() == time) {

					double x = 0, y = 0;

					Server serv = strategyTime();
					serv.addTask(tasks.get(0));
					serv.incServingTime(tasks.get(0).getProcessingTime());
					frame.afisare(servers, time);
					frame.writeLog(tasks.get(0).sosireToString(serv.getID()));
					for (int i = 0; i < nrServers; i++) {
						x = servers.get(i).getAverageWaitingTime();
						y = servers.get(i).getAverageServingTime();
					}

					frame.writeLog("Timp de asteptare mediu: " + String.format("%.2f", x) + "--Timp de servire mediu: "
							+ String.format("%.2f", y) + "\n");

					tasks.remove(0);

				}

				if (tasks.isEmpty() && this.isEmpty()) {
					for (Server s1 : servers)
						frame.writeLog(s1.raportServer());

					break;
				}

				try {
					Thread.sleep(1000);
					time++;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

			
		}
	}
}
