package view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import logic.Simulation;
import model.Server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameSimulator extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField minSosire;
	private JTextField maxSosire;
	private JTextField minServire;
	private JTextField maxServire;
	private JTextField minSimulare;
	private JTextField maxSimulare;
	private JTextField nrServereIN;
	private JLabel sosire;
	private JLabel servire;
	private JLabel simulare;
	private JLabel nrServere;
	private JButton btnStart;
	private JButton[] servere;
	private JButton[][] taskuri;
	private JScrollPane scrollPane;
	private static JTextArea log;
	private JPanel panelShow;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public FrameSimulator() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(729, 498);
		setForeground(new Color(204, 255, 255));
		setBackground(new Color(153, 255, 204));
		setTitle("Simulare Cozi");
		getContentPane().setBackground(new Color(255, 255, 204));
		getContentPane().setLayout(null);

		sosire = new JLabel("Interval sosire dintre clienti:");
		sosire.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sosire.setBounds(502, 11, 180, 14);
		getContentPane().add(sosire);

		minSosire = new JTextField();
		minSosire.setBounds(502, 44, 52, 20);
		getContentPane().add(minSosire);
		minSosire.setColumns(10);

		maxSosire = new JTextField();
		maxSosire.setBounds(619, 44, 52, 20);
		getContentPane().add(maxSosire);
		maxSosire.setColumns(10);

		servire = new JLabel("Interval timp de servire:");
		servire.setFont(new Font("Tahoma", Font.PLAIN, 12));
		servire.setBounds(502, 75, 146, 14);
		getContentPane().add(servire);

		minServire = new JTextField();
		minServire.setBounds(502, 100, 52, 20);
		getContentPane().add(minServire);
		minServire.setColumns(10);

		maxServire = new JTextField();
		maxServire.setBounds(619, 100, 52, 20);
		getContentPane().add(maxServire);
		maxServire.setColumns(10);

		simulare = new JLabel("Interval de simulare:");
		simulare.setFont(new Font("Tahoma", Font.PLAIN, 12));
		simulare.setBounds(502, 131, 125, 14);
		getContentPane().add(simulare);

		minSimulare = new JTextField();
		minSimulare.setBounds(502, 156, 52, 20);
		getContentPane().add(minSimulare);
		minSimulare.setColumns(10);

		maxSimulare = new JTextField();
		maxSimulare.setBounds(619, 156, 52, 20);
		getContentPane().add(maxSimulare);
		maxSimulare.setColumns(10);

		nrServere = new JLabel("Numarul de case de marcat:");
		nrServere.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nrServere.setBounds(502, 187, 180, 14);
		getContentPane().add(nrServere);

		nrServereIN = new JTextField();
		nrServereIN.setBounds(537, 212, 52, 20);
		getContentPane().add(nrServereIN);
		nrServereIN.setColumns(10);

		btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				simulate();
			}
		});
		btnStart.setBackground(new Color(204, 0, 0));
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnStart.setBounds(540, 339, 131, 59);
		getContentPane().add(btnStart);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 289, 457, 170);
		getContentPane().add(scrollPane);

		log = new JTextArea();
		scrollPane.setViewportView(log);

		JScrollPane scrollShow = new JScrollPane();
		scrollShow.setBounds(0, 0, 457, 278);
		getContentPane().add(scrollShow);

		panelShow = new JPanel();
		scrollShow.setViewportView(panelShow);
		panelShow.setLayout(null);

		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(400, 400, 750, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void simulate() {

		int nrServers = Integer.parseInt(nrServereIN.getText());
		int minArrival = Integer.parseInt(minSosire.getText());
		int maxArrival = Integer.parseInt(maxSosire.getText());
		int minProcessing = Integer.parseInt(minServire.getText());
		int maxProcessing = Integer.parseInt(maxServire.getText());
		int minSimulation = Integer.parseInt(minSimulare.getText());
		int maxSimulation = Integer.parseInt(maxSimulare.getText());
		/////////////// luam datele de intrare din interfata

		if (minArrival > maxArrival || minProcessing > maxProcessing || minSimulation > maxSimulation || nrServers <= 0)
			JOptionPane.showMessageDialog(null, "Date introduse incorecte");
		else {
			log.setText("\nInterval de sosire intre clienti: " + minArrival + " - " + maxArrival
					+ " minute\nInterval de servire pentru clienti: " + minProcessing + " - " + maxProcessing
					+ "\nLimitele de timp in care se face simularea: " + minSimulation + " - " + maxSimulation
					+ "\nNumarul caselor deschise: " + nrServers + "\n\n");
			//////////// daca datele introduse sunt corecte, incepe thread-ul
			Thread simulation = new Thread(new Simulation(nrServers, minArrival, maxArrival, minProcessing,
					maxProcessing, minSimulation, maxSimulation, this));

			simulation.start();
		}

	}

	public void writeLog(String str) {
		log.setText(log.getText() + str);
	}

	public void createPanel(int nrServers, int nrTasks) {
		taskuri = new JButton[nrServers][nrTasks];
		servere = new JButton[nrServers];

		for (int i = 0; i < nrServers; i++) {
			servere[i] = new JButton();
			servere[i].setBackground(Color.blue);
			servere[i].setBounds(i * 100, 0, 30, 30);
			panelShow.add(servere[i]);

			for (int j = 0; j < nrTasks; j++) {
				taskuri[i][j] = new JButton();
				taskuri[i][j].setBackground(Color.cyan);
				taskuri[i][j].setBounds(i * 100, (j * 50), 30, 30);
				panelShow.add(taskuri[i][j]);

			}

		}

	}

	public void afisare(ArrayList<Server> servers, int time) {

		for (int i = 0; i < servers.size(); i++) {
				
			if (!servers.get(i).getTasks().isEmpty()) {
				
				for (int j = 0; j < servers.get(i).getTasks().size(); j++) {

					taskuri[i][j].setBounds(j * 100, (i * 50), 30, 30);
					taskuri[i][j].setVisible(true);

				}
			}
		}
	}
}
