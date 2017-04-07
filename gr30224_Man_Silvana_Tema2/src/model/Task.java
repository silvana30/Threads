package model;

public class Task {

	private int arrivalTime;
	private int processingTime;

	private int ID;

	public Task(int arrivalTime, int processingTime, int ID) {

		this.arrivalTime = arrivalTime;
		this.processingTime = processingTime;
		this.ID = ID;

	}

	public Task() {
		super();
	}

	public int getProcessingTime() {
		// TODO Auto-generated method stub
		return processingTime;
	}

	public int getArrivalTime() {
		// TODO Auto-generated method stub
		return arrivalTime;
	}

	public int getID() {
		return ID;
	}

	public void setArrivalTime(int arrive) {
		arrivalTime = arrive;
	}

	public void setProcessingTime(int process) {
		processingTime = process;
	}

	public void setID(int id) {
		ID = id;
	}
	public void decProcessing() {
		this.processingTime--;
	}

	

	public String sosireToString(int idServer) {
		return "Clientul " + ID + " a ajuns la casa " + idServer + " la timpul " + arrivalTime + "\nTimpul lui de procesare este "+processingTime+"\n";
	}

	public String iesireToString(int idServer) {
		return "Clientul " + ID + " pleaca de la casa " + idServer + "\n";
	}
}
