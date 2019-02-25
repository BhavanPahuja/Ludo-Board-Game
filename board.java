import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class board {


	// arrays that hold the board layout
	String[][] square = new String[15][15];
	String[][] border = new String[16][15];

	Token[][][] tokenRecord = new Token[15][15][4];

	// coordinates for starting positions
	int[][] greenHomePos = { { 2, 2 }, { 2, 3 }, { 3, 2 }, { 3, 3 } };
	int[][] yellowHomePos = { { 2, 11 }, { 2, 12 }, { 3, 11 }, { 3, 12 } };
	int[][] redHomePos = { { 11, 2 }, { 11, 3 }, { 12, 2 }, { 12, 3 } };
	int[][] blueHomePos = { { 11, 11 }, { 11, 12 }, { 12, 11 }, { 12, 12 } };

	Map<String, int[][]> positionMap;

	// x and y coordinates of each color's path
	int[][] greenPath = { { 6, 1 }, { 6, 2 }, { 6, 3 }, { 6, 4 }, { 6, 5 }, 
			{ 5, 6 }, { 4, 6 }, { 3, 6 }, { 2, 6 }, { 1, 6 }, { 0, 6 }, 
			{ 0, 7 }, { 0, 8 }, { 2, 8 }, { 3, 8 }, { 4, 8 }, { 5, 8 }, 
			{ 6, 9 }, { 6, 10 }, { 6, 11 }, { 6, 12 }, { 6, 13 }, { 6, 14 }, 
			{ 7, 14 }, { 8, 14 }, { 8, 12 }, { 8, 11 }, { 8, 10 }, { 8, 9 }, 
			{ 9, 8 }, { 10, 8 }, { 11, 8 }, { 12, 8 }, { 13, 8 }, { 14, 8 }, 
			{ 14, 7}, { 14, 6 }, { 12, 6 }, { 11, 6 }, { 10, 6 }, { 9, 6 }, 
			{ 8, 5 }, { 8, 4 }, { 8, 3 }, { 8, 2 }, { 8, 1 }, { 8, 0 }, 
			{ 7, 0 }, { 7, 1 }, { 7, 2 }, { 7, 3 }, { 7, 4 }, { 7, 5 }, { 7, 6 } };

	int[][] yellowPath = { { 1, 8 }, { 2, 8 }, { 3, 8 }, { 4, 8 }, { 5, 8 }, 
			{ 6, 9 }, { 6, 10 }, { 6, 11 }, { 6, 12 }, { 6, 13 }, { 6, 14 }, 
			{ 7, 14 }, { 8, 14 }, { 8, 12 }, { 8, 11 }, { 8, 10 }, { 8, 9 }, 
			{ 9, 8 }, { 10, 8 }, { 11, 8 }, { 12, 8 }, { 13, 8 }, { 14, 8 }, 
			{ 14, 7}, { 14, 6 }, { 12, 6 }, { 11, 6 }, { 10, 6 }, { 9, 6 }, 
			{ 8, 5 }, { 8, 4 }, { 8, 3 }, { 8, 2 }, { 8, 1 }, { 8, 0 }, 
			{ 7, 0 }, { 6, 0 }, { 6, 2 }, { 6, 3 }, { 6, 4 }, { 6, 5 }, 
			{ 5, 6 }, { 4, 6 }, { 3, 6 }, { 2, 6 }, { 1, 6 }, { 0, 6 }, 
			{ 0, 7 }, { 1, 7 }, { 2, 7 }, { 3, 7 }, { 4, 7 }, { 5, 7 }, { 6, 7 } };

	int[][] redPath = { { 13, 6 }, { 12, 6 }, { 11, 6 }, { 10, 6 }, { 9, 6 }, 
			{ 8, 5 }, { 8, 4 }, { 8, 3 }, { 8, 2 }, { 8, 1 }, { 8, 0 }, 
			{ 7, 0 }, { 6, 0 }, { 6, 2 }, { 6, 3 }, { 6, 4 }, { 6, 5 }, 
			{ 5, 6 }, { 4, 6 }, { 3, 6 }, { 2, 6 }, { 1, 6 }, { 0, 6 }, 
			{ 0, 7 }, { 0, 8 }, { 2, 8 }, { 3, 8 }, { 4, 8 }, { 5, 8 }, 
			{ 6, 9 }, { 6, 10 }, { 6, 11 }, { 6, 12 }, { 6, 13 }, { 6, 14 }, 
			{ 7, 14 }, { 8, 14 }, { 8, 12 }, { 8, 11 }, { 8, 10 }, { 8, 9 }, 
			{ 9, 8 }, { 10, 8 }, { 11, 8 }, { 12, 8 }, { 13, 8 }, { 14, 8 }, 
			{ 14, 7}, { 13, 7}, { 12, 7}, { 11, 7}, { 10, 7}, { 9, 7}, { 8, 7} };

	int[][] bluePath = { { 8, 13 }, { 8, 12 }, { 8, 11 }, { 8, 10 }, { 8, 9 }, 
			{ 9, 8 }, { 10, 8 }, { 11, 8 }, { 12, 8 }, { 13, 8 }, { 14, 8 }, 
			{ 14, 7}, { 14, 6 }, { 12, 6 }, { 11, 6 }, { 10, 6 }, { 9, 6 }, 
			{ 8, 5 }, { 8, 4 }, { 8, 3 }, { 8, 2 }, { 8, 1 }, { 8, 0 }, 
			{ 7, 0 }, { 6, 0 }, { 6, 2 }, { 6, 3 }, { 6, 4 }, { 6, 5 }, 
			{ 5, 6 }, { 4, 6 }, { 3, 6 }, { 2, 6 }, { 1, 6 }, { 0, 6 }, 
			{ 0, 7 }, { 0, 8 }, { 2, 8 }, { 3, 8 }, { 4, 8 }, { 5, 8 }, 
			{ 6, 9 }, { 6, 10 }, { 6, 11 }, { 6, 12 }, { 6, 13 }, { 6, 14 }, 
			{ 7, 14 }, { 7, 13 }, { 7, 12 }, { 7, 11 }, { 7, 10 }, { 7, 9 }, { 7, 8 } };

	Map<String, int[][]> pathMap;

	board() 
	{
		positionMap = new HashMap<String, int[][]>();
		positionMap.put("Green", greenHomePos);
		positionMap.put("Yellow", yellowHomePos);
		positionMap.put("Red", redHomePos);
		positionMap.put("Blue", blueHomePos);

		//Initializing the path map

		pathMap = new HashMap<String, int[][]>();
		pathMap.put("Green", greenPath);
		pathMap.put("Yellow", yellowPath);
		pathMap.put("Red", redPath);
		pathMap.put("Blue", bluePath);


		//Initializing the token record.
		for(int i=0; i<tokenRecord.length; i++) 
		{
			tokenRecord[i] = new Token[15][4];
			for(int j=0; j<tokenRecord[i].length; j++) 
			{
				tokenRecord[i][j] = new Token[4];
				for(int k=0; k<tokenRecord[i][j].length; k++)
					tokenRecord[i][j][k] = null;
			}
		}

		System.out.println(tokenRecord[3][3]);
		System.out.println(tokenRecord[3][3][0]);

		for(int i=0; i<=14; i++)
		{
			square[2][i] =  "|    |";
			square[3][i] =  "|    |";
			square[11][i] = "|    |";
			square[12][i] = "|    |";
			if(i<=9 && i>=5)
				square[13][i] = "|    |";
			if(i<=4 && i>=1)
			{
				square[14][i] = "      ";
				square[9][i] = "      ";
				square[0][i] = "      ";
				square[5][i] = "      ";
			}
			if(i<=6 && i>=8)
			{
				square[0][i] = "|    |";
				square[9][i] = "|    |";
				square[5][i] = "|    |";
				square[14][i] = "|    |";

			}
			if(i<=5 && i>=9)
			{
				square[1][i] = "|    |";
				square[4][i] = "|    |";
				square[10][i] = "|    |";
			}
			if(i>=10 && i<=12)
			{
				square[0][i] = "      ";
			}
			if(i>=11 && i<=12)
			{
				square[1][i] = "      ";
				square[4][i] = "      ";

			}
			if(i>=0 && i<=5)
			{
				square[7][i] = "|    |";
				square[6][i] = "|    |";
				square[8][i] = "|    |";
			}
			if(i==5)
			{
				square[5][i] = "     |";
				square[0][i] = "     |";
				square[9][i] = "     |";
				square[14][i] = "     |";

			}
			if(i>=9 && i<=14)
			{
				square[6][i] = "|    |";
				square[7][i] = "|    |";
				square[8][i] = "|    |";
				square[9][i] = "      ";
			}
			if(i==0)
			{
				square[1][i] = "|    |";
				square[4][i] = "|    |";
				square[5][i] = "|     ";
				square[9][i] = "|     ";
				square[10][i] = "|     ";
				square[13][i] = "|    |";
				square[1][i] = "|     ";
				square[4][i] = "|     ";
				square[13][i] = "|     ";
			}
			if(i>=2 && i<=3)
			{
				square[1][i] = "      ";
				square[4][i] = "      ";
				square[10][i] = "      ";
				square[13][i] = "      ";
			}
			if(i>=10 && i<=13)
			{
				square[5][i] = "      ";
				square[9][i] = "      ";
				square[14][i] = "      ";				
			}
			if(i==1 && i==4 && i==10 && i==13)
			{
				square[i][13] = "     |";
				square[i][14] = "|    |";
			}

		}

		square[0][0] = "|Green";
		square[0][9] = "|     ";
		square[0][13] = "     Y";
		square[0][14] = "ellow|";
		square[1][4] = "     |";
		square[1][10] = "|     ";
		square[4][4] = "     |";
		square[4][10] = "|     ";
		square[5][9] = "|     ";
		square[5][14] = "     |";
		square[6][6] = "|     ";
		square[6][7] = "      ";
		square[6][8] = "     |";
		square[7][6] = "|     ";
		square[7][7] = "      ";
		square[7][8] = "     |";
		square[8][6] = "|     ";
		square[8][7] = "      ";
		square[8][8] = "     |";
		square[9][0] = "|     ";
		square[9][9] = "|     ";
		square[9][14] = "     |";
		square[10][0] = "|    |";
		square[10][4] = "     |";
		square[10][10] = "|     ";
		square[10][11] = "      ";
		square[10][12] = "      ";
		square[13][4] = "     |";
		square[13][10] = "|     ";
		square[13][11] = "      ";
		square[13][12] = "      ";
		square[14][0] = "|Red  ";
		square[14][9] = "|     ";
		square[14][14] = " Blue|";


		for(int i=0; i<=15; i++)
		{
			for(int j=0; j<=14; j++)
			{
				border[i][j] = "------";
			}
		}


		for(int i=0; i<=14; i++)
		{
			border[0][i] = "------";
			border[9][i] = "------";
			border[15][i] = "------";
			if(i>=1 && i<=4)
			{
				border[1][i] = "------";
			}
			if(i>=10 && i<=13)
			{
				border[14][i] = "------";
			}
		}
	}

	void printBoard() 
	{
		for(int i=0; i<16; i++) 
		{
			for(int j=0; j<15; j++)
				System.out.print(border[i][j]);
			System.out.println();
			if(i==15)
				break;
			for(int j=0; j<15; j++) 
				System.out.print(renderBlock(i, j));
			System.out.println();
		}
	}

	// Method to render the block with its current contents	
	private String renderBlock(int yCoord, int xCoord) 
	{
		String defaultBlock = square[yCoord][xCoord];
		Token[] tokenArray = tokenRecord[yCoord][xCoord];
		String contents = "";
		for(int i=0; i<tokenArray.length; i++) 
		{
			Token token = tokenArray[i];
			if(token==null)
				break;
			if(contents.length()==0) 
			{
				String color = token.getColor();
				contents += color.substring(0, 1) + token.getTokenNumber();
			}
			else contents += token.getTokenNumber();
		}

		String renderedBlock = defaultBlock.substring(0, 1) + contents + defaultBlock.substring(contents.length()+1);
		return renderedBlock;	
	}


	void initializeTokens(User user) 
	{
		String color = user.getColor();
		int[][] coordinates = positionMap.get(color);

		for(int i=0; i<4; i++) {

			int yCoord = coordinates[i][0];
			int xCoord = coordinates[i][1];

			setTokenCoordinates(user.getToken(i), yCoord, xCoord);

		}

	}

	boolean takeTokenOut(Token token) 
	{
		if(token.isTakenOut())
			return false;

		boolean successfullyMoved = moveToken(token, 6);
		if(successfullyMoved) 
		{
			token.setTakenOut(true);
			return true;
		}
		return false;
	}


	boolean moveToken(Token token, int howManySquares) 
	{
		if(!token.isTakenOut() && howManySquares!=6)
			return false;

		String color = token.getColor();
		int[][] path = pathMap.get(color);
		int currentY = token.getY();
		int currentX = token.getX();
		int currentBlock = 0;

		for(int i=0; i<path.length; i++) 
		{
			if(path[i][0]==currentY && path[i][1]==currentX) 
			{
				currentBlock = i;
				break;
			}
		}

		int endPosition = currentBlock + howManySquares;
		if(endPosition>=path.length)
			return false; // cannot finish without exact number

		for(int i=currentBlock+1; i<=endPosition; i++)
			if(isBlocked(token, path[i][0], path[i][1]))
				return false; // blocked by enemy

		if(containsOneEnemyToken(token, path[endPosition][0], path[endPosition][1]))
			consumeEnemy(path[endPosition][0], path[endPosition][1]);

		if(!token.isTakenOut()) {

			setTokenCoordinates(token, path[endPosition-1][0], path[endPosition-1][1]);
			token.setTakenOut(true);

		} else 
			setTokenCoordinates(token, path[endPosition][0], path[endPosition][1]);

		if(endPosition+1==path.length)
			token.setCompleted(true);

		return true;

	}


	boolean movesArePossible(User user, int numberRolled) {

		if(numberRolled==6) 
		{
			for(int i=0; i<4; i++) 
			{
				Token token = user.getToken(i);
				if(canTakeTokenOut(token))
					return true;
			}
		}

		for(int i=0; i<4; i++)
		{			
			Token token = user.getToken(i);
			if(canMoveToken(token, numberRolled))
				return true;
		}
		return false;
	}

	private boolean canTakeTokenOut(Token token) 
	{
		if(token.isTakenOut())
			return false;

		boolean canMove = canMoveToken(token, 6);
		if(canMove)
			return true;
		return false;
	}

	private boolean canMoveToken(Token token, int howManySquares) 
	{
		if(!token.isTakenOut() && howManySquares!=6)
			return false;
		String color = token.getColor();
		int[][] path = pathMap.get(color);
		int currentY = token.getY();
		int currentX = token.getX();
		int currentBlock = 0;
		
		for(int i=0; i<path.length; i++) 
		{
			if(path[i][0]==currentY && path[i][1]==currentX) 
			{
				currentBlock = i;
				break;
			}
		}

		int endPosition = currentBlock + howManySquares;
		if(endPosition>=path.length)
			return false; // cannot finish without exact number

		for(int i=currentBlock+1; i<=endPosition; i++)
			if(isBlocked(token, path[i][0], path[i][1]))
				return false; // blocked by enemy

		return true;
	}

	//Enemy token by sent back to the starting position
	private void consumeEnemy(int yCoord, int xCoord) 
	{
		Token token = tokenRecord[yCoord][xCoord][0];
		String color = token.getColor();
		int[][] homeCoordinates = positionMap.get(color);

		for(int i=0; i<4; i++) 
		{
			int homeY = homeCoordinates[i][0];
			int homeX = homeCoordinates[i][1];

			if(tokenRecord[homeY][homeX][0]==null) 
			{
				token.setTakenOut(false);
				setTokenCoordinates(token, homeY, homeX);
				break;
			}
		}
	}

	//Method checks if the block contains an enemy token that can be knocked out
	
	boolean containsOneEnemyToken(Token token, int yCoord, int xCoord) 
	{
		List<Token> tokenList = new ArrayList<Token>();

		for(int i=0; i<4; i++) 
		{
			Token currentToken = tokenRecord[yCoord][xCoord][i];

			if(currentToken!=null && currentToken.getColor().equals(token.getColor()))
				return false;
			
			else if(currentToken!=null)
				tokenList.add(currentToken);
			
			else break;
		}

		if(tokenList.size()==1)
			return true;
		else return false;
	}

	// Method checks if the block is blocked (2 or more tokens)
	boolean isBlocked(Token token, int yCoord, int xCoord) 
	{
		List<Token>tokenList = new ArrayList<Token>();

		for(int i=0; i<4; i++) 
		{
			Token currentToken = tokenRecord[yCoord][xCoord][i];

			if(currentToken!=null && currentToken.getColor().equals(token.getColor()))
				return false;
			else if(currentToken!=null)
				tokenList.add(currentToken);
			else break;
		}
		if(tokenList.size()>1)
			return true;
		else return false;
	}


	void setTokenCoordinates(Token token, int yCoord, int xCoord) 
	{
		boolean notInitialized = token.getX()==0 && token.getY()==0;
		if(notInitialized)
		{
			tokenRecord[yCoord][xCoord][0] = token;
			token.setY(yCoord);
			token.setX(xCoord);
		} 
		else 
		{
			int currentY = token.getY();
			int currentX = token.getX();
			
			for(int i=0; i<tokenRecord[currentY][currentX].length; i++) 
			{
				if(tokenRecord[currentY][currentX][i]==token) 
				{
					tokenRecord[currentY][currentX][i] = null;
					rearrangeBlock(currentY, currentX);
					break;
				}
			}

			tokenRecord[yCoord][xCoord][3] = token;
			token.setY(yCoord);
			token.setX(xCoord);
			rearrangeBlock(yCoord, xCoord);
		}
	}

	// Rearrange coordinates to keep the tokens in an ascending order.
	private void rearrangeBlock(int yCoord, int xCoord) 
	{
		List<Token> tokenList = new ArrayList<Token>();
		for(int i=0; i<4; i++) 
		{
			Token token = tokenRecord[yCoord][xCoord][i];
			if(token!=null) 
			{
				tokenList.add(token);
				tokenRecord[yCoord][xCoord][i] = null;
			}
		}
		if(tokenList.size()!=0) 
		{
			int numberOfTokens = tokenList.size();
			
			for(int i=0; i<numberOfTokens; i++) 
			{
				int lowestTokenNumber = 5;
				int lowestTokenIndex = 5;

				for(int j=i; j<numberOfTokens; j++) 
				{
					int nextTokenNumber = tokenList.get(j).getTokenNumber();
					boolean isLower = nextTokenNumber < lowestTokenNumber;

					if(isLower) 
					{
						lowestTokenNumber = nextTokenNumber;
						lowestTokenIndex = j;
					}
					lowestTokenNumber = isLower ? nextTokenNumber : lowestTokenNumber;
				}
				tokenRecord[yCoord][xCoord][i] = tokenList.get(lowestTokenIndex);
			}
		}
	}
}
