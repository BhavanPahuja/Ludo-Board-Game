package Ludo;
import Ludo.Token;
import Ludo.board;

import java.util.ArrayList;
import java.util.List;

import Ludo.Dice;
import Ludo.Moves;

public class TokenMethods 
{
	static void initializeTokens(User user) 
	{
		String color = user.getColor();
		int[][] coordinates = board.positionMap.get(color);

		for(int i=0; i<4; i++) {

			int yCoord = coordinates[i][0];
			int xCoord = coordinates[i][1];

			setTokenCoordinates(user.getToken(i), yCoord, xCoord);
		}
	}
		
		static boolean moveToken(Token token, int howManySquares) 
		{
			if(!token.isTakenOut() && howManySquares!=6)
				return false;

			String color = token.getColor();
			int[][] path = board.pathMap.get(color);
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

			if(Moves.containsOneEnemyToken(token, path[endPosition][0], path[endPosition][1]))
				Moves.consumeEnemy(path[endPosition][0], path[endPosition][1]);

			if(!token.isTakenOut()) {

				setTokenCoordinates(token, path[endPosition-1][0], path[endPosition-1][1]);
				token.setTakenOut(true);

			} else 
				setTokenCoordinates(token, path[endPosition][0], path[endPosition][1]);

			if(endPosition+1==path.length)
				token.setCompleted(true);

			return true;
		}
		
		public static void setTokenCoordinates(Token token, int yCoord, int xCoord) 
		{
			boolean notInitialized = token.getX()==0 && token.getY()==0;
			if(notInitialized)
			{
				board.tokenRecord[yCoord][xCoord][0] = token;
				token.setY(yCoord);
				token.setX(xCoord);
			} 
			else 
			{
				int currentY = token.getY();
				int currentX = token.getX();
				
				for(int i=0; i<board.tokenRecord[currentY][currentX].length; i++) 
				{
					if(board.tokenRecord[currentY][currentX][i]==token) 
					{
						board.tokenRecord[currentY][currentX][i] = null;
						board.rearrangeBlock(currentY, currentX);
						break;
					}
				}

				board.tokenRecord[yCoord][xCoord][3] = token;
				token.setY(yCoord);
				token.setX(xCoord);
				board.rearrangeBlock(yCoord, xCoord);
			}
		}
		
		static boolean takeTokenOut(Token token) 
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
		
		static boolean isBlocked(Token token, int yCoord, int xCoord) 
		{
			List<Token>tokenList = new ArrayList<Token>();

			for(int i=0; i<4; i++) 
			{
				Token currentToken = board.tokenRecord[yCoord][xCoord][i];

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
}
