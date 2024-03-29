package test;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class StartWindow extends javax.swing.JFrame {
	private JPanel jPanel1;
	private JLabel serverLabel;
	private JLabel teamLabel;
	private JTextField usernameInput;
	private JLabel usernameLabel;
	public JButton joinButton;
	public JList teamList;
	public JTextField serverInput;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				StartWindow inst = new StartWindow();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public StartWindow() {
		super();
		initGUI();
		String[] teams = {"RED TEAM", "BLUE TEAM"};
		teamList.setListData(teams);
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				jPanel1 = new JPanel();
				getContentPane().add(jPanel1, "Center");
				jPanel1.setLayout(null);
				jPanel1.setBounds(0, 0, 384, 262);
				{
					serverLabel = new JLabel();
					jPanel1.add(serverLabel);
					serverLabel.setText("Server:");
					serverLabel.setBounds(21, 31, 89, 16);
				}
				{
					serverInput = new JTextField();
					jPanel1.add(serverInput);
					serverInput.setBounds(128, 31, 245, 23);
				}
				{
					teamLabel = new JLabel();
					jPanel1.add(teamLabel);
					teamLabel.setText("Team: ");
					teamLabel.setBounds(21, 98, 89, 16);
				}
				{
					ListModel teamListModel = 
						new DefaultComboBoxModel(
								new String[] { "Item One", "Item Two" });
					teamList = new JList();
					jPanel1.add(teamList);
					teamList.setModel(teamListModel);
					teamList.setBounds(128, 101, 245, 91);
				}
				{
					joinButton = new JButton();
					jPanel1.add(joinButton);
					joinButton.setText("Join Game");
					joinButton.setBounds(128, 204, 131, 23);
				}
				{
					usernameLabel = new JLabel();
					jPanel1.add(usernameLabel);
					usernameLabel.setText("Username:");
					usernameLabel.setBounds(21, 64, 89, 16);
				}
				{
					usernameInput = new JTextField();
					jPanel1.add(usernameInput);
					usernameInput.setBounds(128, 67, 245, 23);
				}
			}
			pack();
			setSize(400, 300);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

}
