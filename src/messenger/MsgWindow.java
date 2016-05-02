package messenger;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Set;

public class MsgWindow extends JFrame implements KeyListener{
	private static final String PROMPT = ">> ";

	  private MsgUser myUser;
	  private JComboBox buddiesList;
	  private JTextArea textArea;
	  
	  public MsgWindow (MsgUser u, Set buddies)
	  {
	    super(u.toString());

	    addWindowListener(new WindowAdapter()
	      { public void windowClosing(WindowEvent e) { myUser.quit(); }});

	    myUser = u;

	    Object buddiesArray[] = buddies.toArray();
	    buddiesList = new JComboBox(buddiesArray);

	    JPanel talkTo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	    talkTo.add(new JLabel("Talk to:", JLabel.RIGHT));
	    talkTo.add(buddiesList);

	    textArea = new JTextArea(10, 20);
	    textArea.setFont(new Font("Serif", Font.PLAIN, 20));
	    textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(true);
	    textArea.append(PROMPT);
	    textArea.addKeyListener(this);
	    JScrollPane areaScrollPane = new JScrollPane(textArea);
	    areaScrollPane.setVerticalScrollBarPolicy(
	        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

	    Container c = getContentPane();
	    c.add(talkTo, BorderLayout.NORTH);
	    c.add(areaScrollPane, BorderLayout.CENTER);

	    int x = (int)(Math.random()* 500);
	    int y = (int)(Math.random()* 300);
	    setBounds(x, y, 300, 300);
	    show();
	  }

	  public void addBuddy(MsgUser u)
	  {
	    buddiesList.addItem(u);
	  }

	  public void removeBuddy(MsgUser u)
	  {
	    buddiesList.removeItem(u);
	  }

	  public void showMessage(String text)
	  {
	    textArea.append(text);
	    textArea.append("\n" + PROMPT);
	  }

	  private void sendMessage(String text)
	  {
	    MsgUser u = (MsgUser)buddiesList.getSelectedItem();
	    u.receiveMessage("[" + myUser.toString() + "] " + text);
	    textArea.append(PROMPT);
	  }

	  /**
	   *  Implement KeyListener interface:
	   */
	  public void keyReleased (KeyEvent e)
	  {
	    String msg;
	    int code = e.getKeyCode();

	    switch(code)
	    {
	      case KeyEvent.VK_ENTER:
	        msg= textArea.getText();
	        int tail = msg.lastIndexOf(PROMPT) + PROMPT.length();
	        msg = msg.substring(tail).trim();
	        if (msg.length() > 0)
	          sendMessage(msg);
	        break;
	    }
	  }

	  public void keyPressed (KeyEvent e) {}
	  public void keyTyped (KeyEvent e) {}
}
