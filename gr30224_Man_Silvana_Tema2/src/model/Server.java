package model;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server {

	private int IDserver;
	
	private ArrayList<Task> tasks;
	private int waitingPeriod;

	private int servingPeriod;
	private int nrTaskTotal;
	

	public Server(int iDserver) {
		super();
		this.IDserver = iDserver;
		tasks = new ArrayList<Task>();
		waitingPeriod = 0;
		servingPeriod = 0;
		nrTaskTotal = 0;
		
	}

	public void addTask(Task newTask) {

		//adaug un nou task in server si incrementez numarul de taskuri
		
		tasks.add(newTask);
		nrTaskTotal++;
		
		

	}

	public synchronized void removeTask() {
		//sterg un task din lista dupa metoda FIFO
		
		tasks.remove(0);
	}

	public int getID() {
		return this.IDserver;
	}

	public synchronized Task getTask(int t) {
		return tasks.get(t);
	}

	public double getAverageWaitingTime() {
		//calculeaza timpul mediu de asteptare pt fiecare coada
		
		return (double) waitingPeriod / nrTaskTotal;
		
	}

	public double getAverageServingTime() {
		//calculeaza timpul mediu de servire pt fiecare coada
		
		return (double) servingPeriod / nrTaskTotal;
	}
	
	public void incWaitingTime(int waiting) {
		//incrementeaza timpul de asteptare
		
		waitingPeriod += waiting;
	}
	public void incServingTime(int serving) {
		//incrementeaza timpul de servire
		
		servingPeriod += serving;
	}
	
	
	public int getSize(){
		return tasks.size();
	}
	public int getWaitingPeriod() {

		for (Task t : tasks)
			waitingPeriod += t.getProcessingTime();
		return waitingPeriod;
	}
	

	public String raportServer() {

		return "\nCoada: " + IDserver + "\nTimp asteptare mediu: " + String.format("%.2f", getAverageWaitingTime()) + "\nTimp servire mediu: "
				+ String.format("%.2f",getAverageServingTime()) + "\nNumar total clienti: " + nrTaskTotal+ "\n\n";
	}

	public boolean isEmpty(){
		return tasks.isEmpty();
	} 
	
	
	public Task getFirst() {
		//primul element din coada
		
		return tasks.get(0);
	}
	
	public synchronized ArrayList<Task> getTasks() {
		return tasks;
	}
}
