package anim;



public class Orb {
	
	private double x = 0;
	private int y = 0;
	private double N = 0; //periodo de oscilacion	
	private double A = 70; //Amplitud == cantidad de pixeles en los que se va a mover
	
	public Orb(){
	}
	
	public Orb(int NN){
		N = NN;
	}
	
	public void update() {
		
		if(x == N)	x = 0;
		x += 1;
		
		y = (int) Math.round( A*Math.sin(2*Math.PI/N*x) ); 
		
	}

	
	public int getY() {
		return y;
	}
	
	public void setN(double NN) {
		N = NN;
	}
	
}
