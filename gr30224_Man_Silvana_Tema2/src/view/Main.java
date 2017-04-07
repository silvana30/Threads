package view;

import java.awt.EventQueue;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameSimulator window = new FrameSimulator();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		/*
		 * FrameSimulator window = null; Simulation gen = new
		 * Simulation(3,2,60,1,200,1,201,window); Thread t = new Thread(gen);
		 * t.start();
		 */
	}
}
