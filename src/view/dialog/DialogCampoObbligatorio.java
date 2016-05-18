package view.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.interfaces.ObserverInterface;

import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class DialogCampoObbligatorio extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3815948239620787730L;
	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane = new JPanel();
	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");
	private JLabel lblCampoObb = new JLabel("Non hai inserito i campi Obbligatori, i campi obbligatori sono quelli contrassegnati con il simbolo *");
	
	/**
	 * Create the dialog.
	 */
	public DialogCampoObbligatorio(final ObserverInterface o) {
		setTitle("Inserisci i campi obbligatori");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DialogCampoObbligatorio.class.getResource("/javax/swing/plaf/metal/icons/Error.gif")));
		this.setBounds(100, 100, 674, 169);
		this.getContentPane().setLayout(new BorderLayout());
		this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(new BorderLayout(0, 0));
		lblCampoObb.setIcon(new ImageIcon(DialogCampoObbligatorio.class.getResource("/javax/swing/plaf/metal/icons/Warn.gif")));
		this.contentPanel.add(lblCampoObb, BorderLayout.CENTER);
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		{		
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{		
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						dispose();
						
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{	
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						dispose();
						
					}
				});;
				buttonPane.add(cancelButton);
			}
		}
	}

}