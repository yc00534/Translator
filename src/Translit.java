import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Translit extends JFrame implements ActionListener, KeyListener
{
	private JButton		button;
	private JTextArea	textFeld;
	private String		kopieren	= "Kopiere den Inhalt";
	Tabelle				tabelleList;

	public static int	start		= 0;

	public static void main(String[] args)
	{
		new Translit();
	}

	public Translit()
	{
		JFrame frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 500);
		frame.setVisible(true);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setVisible(true);
		frame.add(panel);

		button = new JButton("Start");
		button.setVisible(true);
		button.addActionListener(this);

		panel.add(button);

		textFeld = new JTextArea(" ");
		textFeld.setEditable(false);
		textFeld.setVisible(true);
		textFeld.addKeyListener(this);

		panel.add(button, BorderLayout.NORTH);
		panel.add(textFeld, BorderLayout.CENTER);
		frame.addKeyListener(this);
		tabelleList = new Tabelle();
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("Start"))
		{
			textFeld.setText("");
			button.setText(kopieren);
		}

		if (e.getActionCommand().equals(kopieren))
		{
			Toolkit.getDefaultToolkit().getSystemClipboard()
					.setContents(new StringSelection(textFeld.getText()), null);
		}

	}

	public void keyTyped(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_UNDEFINED)
		{
			System.out.println("Kein Unicode-Character gedr\u00FCckt!");
		}
	}

	public void keyPressed(KeyEvent e)
	{

	}

	public void keyReleased(KeyEvent e)
	{	
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			textFeld.append(" ");
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			textFeld.append("\n\r");
		} else if (e.getKeyCode() == 8)
		{
			String allText = textFeld.getText();
			textFeld.setText(allText.substring(0,allText.length()-1));
		} else
		{

			String key = String.valueOf(e.getKeyChar());

			String tempString = String.valueOf(tabelleList.list.get(key));

			String allText = textFeld.getText();

			textFeld.setText(allText + tempString);
		}

	}

}
