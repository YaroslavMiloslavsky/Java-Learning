import java.security.SecureRandom;

public class LifeMatrix{
	private static final SecureRandom randomValues = new SecureRandom();
	private boolean[][] 	lifeMatrix;
	private final int 		matrixDim = 10;		// The dimension is set by the question
	
	/**
	 * This is the builder
	 * its purpose is solely generating a random boolean values matrix
	 */
	public LifeMatrix() {
		lifeMatrix = new boolean[matrixDim][matrixDim]; // new boolean matrix #default value - FALSE
		randomizeValues();
	}
	
	/**
	 * Randomises the boolean values inside the matrix
	 */
	private void randomizeValues() {
		for(int i=0 ; i<matrixDim ; i++)
			for(int j=0; j<matrixDim; j++)
				lifeMatrix[i][j] = randomValues.nextBoolean();
	}
	
	/**
	 * Gets the matrix set dimension
	 * @return The matrix dimension
	 */
	public int getMatrixDim() {
		return this.matrixDim;
	}
	
	/**
	 * Shows the life status of the chosen coordinate 
	 * @param x The first index of coordinate
	 * @param y The second index of coordinate
	 * @return True if the coordinate is set to True else False
	 */
	public boolean getCoordinateLifeValue(int x, int y) {
		return lifeMatrix[x][y];
	}
	
	/**
	 * Sets the life status of the chosen coordinate
	 * @param x The first index of coordinate
	 * @param y The second index of coordinate
	 * @param value The status that is being set
	 */
	public void setCoordinateLifeValue(int x, int y, boolean value) {
		lifeMatrix[x][y] = value;
	}
	
	/**
	 * Calculates the next values that are set by the question conditions
	 */
	public void nextGeneration() {
		int liveNeighbours;
		for(int x =0; x<matrixDim ; x++)
			for(int y=0 ; y<matrixDim ; y++) {
				liveNeighbours = 0; // the live neighbours around each coordinate
				// check if the neighbour set to True and if the indexes inside the matrix
				if(isLegalIndex(x+1,y) 	== true && lifeMatrix[x+1][y] == true)
					liveNeighbours++;
				if(isLegalIndex(x-1,y) 	== true && lifeMatrix[x-1][y] == true)
					liveNeighbours++;
				if(isLegalIndex(x,y+1) 	== true && lifeMatrix[x][y+1] == true)
					liveNeighbours++;
				if(isLegalIndex(x,y-1) 	== true && lifeMatrix[x][y-1] == true)
					liveNeighbours++;
				if(isLegalIndex(x+1,y+1) == true && lifeMatrix[x+1][y+1] == true)
					liveNeighbours++;
				if(isLegalIndex(x+1,y-1) == true && lifeMatrix[x+1][y-1] == true)
					liveNeighbours++;
				if(isLegalIndex(x-1,y+1) == true && lifeMatrix[x-1][y+1] == true)
					liveNeighbours++;
				if(isLegalIndex(x-1,y-1) == true && lifeMatrix[x-1][y-1] == true)
					liveNeighbours++;
				
				// Birth
				if(lifeMatrix[x][y] == false && liveNeighbours == 3)
					lifeMatrix[x][y] = true;
				
				// Death
				if(lifeMatrix[x][y] == true && (liveNeighbours ==0 || liveNeighbours ==1))
					lifeMatrix[x][y] = false;
				if(lifeMatrix[x][y] == true && liveNeighbours >3)
					lifeMatrix[x][y] = false;
				
				// Existence 
				if(lifeMatrix[x][y] == true && (liveNeighbours ==3 || liveNeighbours ==2))
					lifeMatrix[x][y] = true;
				
				// unexpected behaviour - shouldn't happen, this is just in case
				else
					lifeMatrix[x][y] = false;
			}
	}
	
	/**
	 * Checks if the coordinate is not out of boundaries
	 * @param i The first index
	 * @param j The second index
	 * @return True if the coordinate inside the matrix
	 */
	private boolean isLegalIndex(int i, int j) {
		if(i<0 || j<0) return false;
		if(i>9 || j>9) return false;
		return true;
	}

	
}
