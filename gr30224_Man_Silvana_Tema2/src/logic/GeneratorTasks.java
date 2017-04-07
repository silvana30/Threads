package logic;

import java.util.ArrayList;
import java.util.Random;

import model.Task;

public class GeneratorTasks {

	public static ArrayList<Task> generateRandomTasks(int minArrival, int maxArrival, int minProcessing,
			int maxProcessing, int minSimulation, int maxSimulation) {

		ArrayList<Task> tasks = new ArrayList<Task>();
		int arrivalTime = 0;
		int processingTime;

		int IDclient = 0;

		Random r = new Random();

		while (arrivalTime < maxSimulation) {
			if (IDclient == 0)
				arrivalTime = r.nextInt(maxArrival - minArrival + 1) + minArrival + minSimulation;
			else

				arrivalTime = r.nextInt(maxArrival - minArrival + 1) + minArrival
						+ tasks.get(IDclient - 1).getArrivalTime();
			processingTime = r.nextInt(maxProcessing - minProcessing + 1) + minProcessing;
			tasks.add(new Task(arrivalTime, processingTime, IDclient));
			IDclient++;

		}
		return tasks;

	}

}
