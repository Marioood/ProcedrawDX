import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
	
	public Window() {
		Container container = this.getContentPane();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(800, 600);
		//container.setBackground(new Color(0xff8000));
		container.setBackground(Color.black);
		this.setVisible(true);
	}
}