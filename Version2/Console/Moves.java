package Ludo;
import Ludo.Token;
import Ludo.board;

import java.util.ArrayList;
import java.util.List;

import Ludo.Dice;
import Ludo.TokenMethods;

public class Moves 
{
	static boolean containsOneEnemyToken(Token token, int yCoord, int xCoord) 
	{
		List<Token> tokenList = new ArrayList<Token>();

		for(int i=0; i<4; i++) 
		{
			Token currentToken = board.tokenRecord[yCoord][xCoord][i];

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
	
	
	//Enemy token by sent back to the starting position
	static void consumeEnemy(int yCoord, int xCoord) 
	{
		Token token = board.tokenRecord[yCoord][xCoord][0];
		String color = token.getColor();
		int[][] homeCoordinates = board.positionMap.get(color);

		for(int i=0; i<4; i++) 
		{
			int homeY = homeCoordinates[i][0];
			int homeX = homeCoordinates[i][1];

			if(board.tokenRecord[homeY][homeX][0]==null) 
			{
				token.setTakenOut(false);
				TokenMethods.setTokenCoordinates(token, homeY, homeX);
				break;
			}
		}
	}
	
	
	private static boolean canMoveToken(Token token, int howManySquares) 
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
			if(TokenMethods.isBlocked(token, path[i][0], path[i][1]))
				return false; // blocked by enemy

		return true;
	}
	
	private static boolean canTakeTokenOut(Token token) 
	{
		if(token.isTakenOut())
			return false;

		boolean canMove = canMoveToken(token, 6);
		if(canMove)
			return true;
		return false;
	}
	
	static boolean movesArePossible(User user, int numberRolled) {

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
	

}
