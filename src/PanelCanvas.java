import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public class PanelCanvas extends JPanel {
	
	private BufferedImage image;
	
	public PanelCanvas(BufferedImage image) {
		this.setBounds(0, 0, 256, 256);
		/*try {
			this.image = ImageIO.read(new File("img/test.png"));
		} catch(IOException e) {
			System.out.println("file could not be loaded");
			System.exit(1);
		}
		
		image.setRGB(0, 0, 0xffff8000);
		image.setRGB(0, 1, 0xffff8000);*/
		this.image = image;
		/*this.image = new BufferedImage(256, 256, BufferedImage.TYPE_INT_ARGB);
		
		for(int y = 0; y < 256; y++) {
			for(int x = 0; x < 256; x++) {
				this.image.setRGB(x, y, 0xff000000 | (x << 16) | (y << 8));
			}
		}*/

		//this.paint(this.getGraphics());
	}

	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		//g2d.setPaint(Color.white);
		//g2d.drawRect(0, 0, 100, 100);
		g.drawImage(this.image, 0, 0, null);
	}
}