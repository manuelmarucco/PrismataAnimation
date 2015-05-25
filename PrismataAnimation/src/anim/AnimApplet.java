package anim;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class AnimApplet extends Applet implements Runnable {

	private URL base;
	private Image image,orbImage;
	private Graphics second;
	private List<Orb> orbs = new ArrayList<Orb>();
	//private List<double> pendulumPeriodList = new ArrayList<double>();
	
    @Override
    public void init() {
    
    // Background Setups
    	
        setSize(800, 480);
        setBackground(Color.BLACK);
        setFocusable(true);
        Frame frame = (Frame) this.getParent().getParent();
        frame.setTitle("Prismata Animation");
        try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}

	// Image Setups
 	
        orbImage = getImage(base, "images/orb.png");
        
    // List of Orbs Setups
		/*int Ti;
        for(double aux: pendulumPeriodList){
        	Ti = (int)Math.round(aux);
        	orbs.add(new Orb(Ti));
        }*/
        for (int i=0; i<10; i++){
        	orbs.add(new Orb(45+1*i));
    	}
    }

    @Override
    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }


    @Override
    public void run() {
        while (true) {
        	for (Orb orb: orbs){
        		orb.update();
        	}
            repaint(); // calls paint method
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
	public void paint(Graphics g) {
    	int count = 0;
    	for (Orb orb: orbs){
    		g.drawImage(orbImage, 160+count, orb.getY()+250, this);
    		count += 30;
    	}
    	
	}
    
    @Override
   	public void update(Graphics g) {
    	
    // Codigo que no se entiende
    	
       	if (image == null) {
   			image = createImage(this.getWidth(), this.getHeight());
   			second = image.getGraphics();
   		}

   		second.setColor(getBackground());
   		second.fillRect(0, 0, getWidth(), getHeight());
   		second.setColor(getForeground());
   		paint(second);

   		g.drawImage(image, 0, 0, this);

   		
   	}
    
} 