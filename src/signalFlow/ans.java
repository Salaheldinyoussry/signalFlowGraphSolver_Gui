package signalFlow;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Color;

public class ans extends JFrame {
   
	
	
	private JPanel contentPane;
	private JTextArea l;
	private JTextArea p;
	private JTextArea d;
	private JTextArea t;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ans frame = new ans("","","","");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ans(String paths , String loops , String deltas ,String transfer ) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 594, 752);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("paths\r\n");
		lblNewLabel.setForeground(Color.PINK);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel.setBounds(41, 10, 158, 50);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 70, 503, 128);
		contentPane.add(scrollPane);
		
		p = new JTextArea();
		p.setFont(new Font("Monospaced", Font.PLAIN, 19));
		p.setEditable(false);
		scrollPane.setViewportView(p);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(41, 266, 503, 128);
		contentPane.add(scrollPane_1);
		
		l = new JTextArea();
		l.setFont(new Font("Monospaced", Font.PLAIN, 19));
		l.setEditable(false);
		scrollPane_1.setViewportView(l);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(41, 461, 503, 88);
		contentPane.add(scrollPane_2);
		
		d = new JTextArea();
		d.setFont(new Font("Monospaced", Font.PLAIN, 19));
		d.setEditable(false);
		scrollPane_2.setViewportView(d);
		
		JLabel lblLoops = new JLabel("Loops\r\n");
		lblLoops.setForeground(Color.PINK);
		lblLoops.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblLoops.setBounds(41, 208, 158, 50);
		contentPane.add(lblLoops);
		
		JLabel lblDeltas = new JLabel("Deltas\r\n");
		lblDeltas.setForeground(Color.PINK);
		lblDeltas.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblDeltas.setBounds(41, 404, 158, 50);
		contentPane.add(lblDeltas);
		
		JLabel lblNewLabel_2_1 = new JLabel("Transfer function");
		lblNewLabel_2_1.setForeground(Color.PINK);
		lblNewLabel_2_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel_2_1.setBounds(41, 559, 158, 50);
		contentPane.add(lblNewLabel_2_1);
		
		
		JScrollPane scrollPane_2_1 = new JScrollPane();
		scrollPane_2_1.setBounds(41, 604, 503, 88);
		contentPane.add(scrollPane_2_1);
		
		t = new JTextArea();
		t.setFont(new Font("Monospaced", Font.PLAIN, 19));
		t.setEditable(false);
		scrollPane_2_1.setViewportView(t);
		p.setText(paths);
		l.setText(loops);
		d.setText(deltas);
		t.setText(transfer);

	}
}
