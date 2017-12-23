package TestGeneticAlgorithm;

class Position {
	private int x, y;

	public Position(int x, int y) {
		if(!setX(x) || !setY(y)) {
			setX((int) (Logic.spielfeldbreite / 2));
			setY(0);
		}
	}

	public int getX() {
		return x;
	}

	public boolean setX(int x) {
		this.x = x;
		return true;
	}

	public int getY() {
		return y;
	}

	public boolean setY(int y) {
		this.y = y;
		return true;
		
	}
	
	
	@Override
	public boolean equals(Object p) {
		Position position = null;
		
		try {
			position = (Position) p;
		}catch(Exception e){
			return false;
		}
		
		if(this.getX() == position.getX() && this.getY() == position.getY()) {
			return true;
		}
		
		
		return false;
		
	}

}