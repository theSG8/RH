package gui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ViewConcreteReportGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ViewConcreteReportGUI frame = new ViewConcreteReportGUI("", "", "");
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
	public ViewConcreteReportGUI(String title, String desc, String ob) {
		setBounds(100, 100, 484, 561);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("title");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTitle.setBounds(44, 30, 116, 14);
		contentPane.add(lblTitle);

		lblTitle.setText(title);

		JLabel lblDesc = new JLabel("desc");
		lblDesc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDesc.setVerticalAlignment(SwingConstants.TOP);
		lblDesc.setHorizontalAlignment(SwingConstants.LEFT);
		lblDesc.setBounds(44, 140, 320, 89);
		contentPane.add(lblDesc);

		lblDesc.setText(desc);

		JLabel lblId = new JLabel("id");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblId.setBounds(32, 470, 259, 22);
		contentPane.add(lblId);

		lblId.setText(ob);
	}
}
