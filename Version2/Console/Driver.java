package Ludo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver
{
	private static String[] userColor = { "Green", "Yellow", "Red", "Blue" };
	private static List<User> userList = new ArrayList<User>();
	private static User user;
	private static board board;

	public static void main(String[] args) 
	{
		System.out.print("Enter the number of players (between 2 and 4): ");
		System.out.println();
		boolean inputCorrect = false;
		Scanner keyboard = new Scanner(System.in);
		int numberOfPlayers;
		numberOfPlayers = keyboard.nextInt();

		if(numberOfPlayers>1 && numberOfPlayers<5 && !inputCorrect) 
		{
			for(int i=0; i<numberOfPlayers; i++)
			{
				userList.add(new User(userColor[i]));
			}
			inputCorrect = true;
		} 
		else 
		{
			System.out.println("Error. PLease type a number between 2 and 4.");
		}

		System.out.println("The highest dice roll goes first. Type \"d\" to roll the dice.");

		boolean rolled = false;
		int counter = 0;
		while(!rolled) 
		{
			for(int i=0; i<userList.size(); i++)
			{
				User user = userList.get(counter);
				System.out.println(user + "'s turn.");
				String roll = "";
				roll = keyboard.next();
				if(roll.equals("d")) 
				{
					user.rollDice();
					System.out.println(user + " has rolled " + user.getNumberRolled());
				} 
				else 
				{
					System.out.println("Something went wrong. Type in \"d\".");
					continue;
				}
				if(++counter==userList.size())
					rolled = true;
			}
		}

		List<User> highestRollers = determineHighestRoller(userList);
		boolean moreThanOneHighestRollers = !(highestRollers.size()==1);

		while(moreThanOneHighestRollers)
		{
			boolean rollsCompleted = false;
			int rollCounter = 0;

			while(!rollsCompleted) 
			{
				User user = highestRollers.get(rollCounter);
				System.out.println(user + "'s turn.");
				String roll = "";
				roll = keyboard.next();
				if(roll.equals("d")) 
				{
					user.rollDice();
					System.out.println(user + " has rolled " + user.getNumberRolled());
				}
				else 
				{
					System.out.println("Something went wrong. Type in \"d\".");
					continue;
				}

				if(++rollCounter==highestRollers.size())
					rollsCompleted = true;
			}

			for(int i=0; i<highestRollers.size(); i++)
				System.out.println(highestRollers.get(i) + " has rolled " + highestRollers.get(i).getNumberRolled());

			highestRollers = determineHighestRoller(highestRollers);
			moreThanOneHighestRollers = !(highestRollers.size()==1);
		}

		user = highestRollers.get(0);
		System.out.println(user + " will get to go first.");

		board = new board();
		for(int i=0; i<userList.size(); i++)
			board.initializeTokens(userList.get(i));
		runGame();
	}

	private static void runGame() 
	{
		Scanner keyboard = null;
		boolean gameCompleted = false;
		game: while(!gameCompleted) 
		{
			board.printBoard();
			System.out.println(user + "'s turn. Type \"d\" to roll.");
			boolean rollComplete = false;
			while(!rollComplete) 
			{
				keyboard = new Scanner(System.in);
				String input = "";
				input = keyboard.next();
				if(input.equals("d")) 
				{
					user.rollDice();
					rollComplete = true;
				} 
				else 
				{
					System.out.println("Something went wrong. Type in \"d\".");
					continue;
				}
			}
			boolean movesArePossible = board.movesArePossible(user, user.getNumberRolled());
			if(!movesArePossible) 
			{
				System.out.println("Can't do anything.");
				setNextUser();
				continue;
			}

			System.out.println(user + " has rolled " + user.getNumberRolled() + 
					". You can do one of the following: \n" + 
					"\"To take a specific token out of the home circle type T followed by the token number (T 1 will move token 1 out of the home circle)\"\n" +
					"\"To move a specific token type M followed by the token number (M 1 will move token 1)"); 

			boolean turnComplete = false;
			while(!turnComplete) 
			{
				keyboard = new Scanner(System.in);
				String keyInput = "";
				boolean commandSuccessful = false;
				keyInput = keyboard.next();

				if(keyInput.equals("T")) 
				{
					if(!user.hasRolledSix()) 
					{
						System.out.println("Sorry, can't do that. Tokens can only be taken when a 6 is rolled.");
						continue;
					}

					int tokenNumber = 0;
					tokenNumber = keyboard.nextInt() - 1;

					if(tokenNumber<0 || tokenNumber>3) 
					{
						System.out.println("Wrong token number. Try again");
						continue;
					}

					Token token = user.getToken(tokenNumber);
					commandSuccessful = board.takeTokenOut(token);
				} 
				
				else if(keyInput.equals("M")) 
				{
					int tokenNumber = 0;
						tokenNumber = keyboard.nextInt() - 1;
						if(tokenNumber<0 || tokenNumber>3) 
						{
						System.out.println("Wrong token number. Try again");
						continue;
					}

					Token token = user.getToken(tokenNumber);
					int squareAmount = user.getNumberRolled();
					commandSuccessful = board.moveToken(token, squareAmount);
				}
				
				else 
				{
					System.out.println("Can't perform that action. Try again.");
					continue;
				}

				if(commandSuccessful) 
				{
					if(user.hasWon()) 
					{
						System.out.println("Congratulations " + user + ". You have won the game!!");
						break game;
					}
					if(user.hasRolledSix()) 
					{
						System.out.println(user + " has rolled a six, so they get to go again.");
						continue game;
					}
					setNextUser();
					turnComplete = true;
				} 
				else 
					System.out.println("Can't perform that action. Try again.");
			}
		}
		keyboard.close();
	}

	private static List<User> determineHighestRoller(List<User> usersWhoRolled) 
	{
		List<User> highRollers = new ArrayList<User>();
		User highestRoller = usersWhoRolled.get(0);
		boolean complete = false;
		int counter = 1;
		while(!complete) 
		{
			User nextUser = usersWhoRolled.get(counter++);
			int currentHighest = highestRoller.getNumberRolled();
			int nextUsersRoll = nextUser.getNumberRolled();

			if(currentHighest>nextUsersRoll)
			{
			} 
			
			else if(currentHighest<nextUsersRoll) 
			{
				highestRoller = nextUser;
				highRollers.clear();

			} 
			else if(currentHighest==nextUsersRoll) 
			{
				if(!highRollers.contains(highestRoller))
					highRollers.add(highestRoller);
				if(!highRollers.contains(nextUser))
					highRollers.add(nextUser);
			}
			if(counter==usersWhoRolled.size())
				complete = true;
		}
		if(highRollers.size()==0)
			highRollers.add(highestRoller);
		return highRollers;
	}

	private static void setNextUser() 
	{
		int nextIndex = userList.indexOf(user) + 1;

		if(nextIndex==userList.size())
			nextIndex = 0;
		user = userList.get(nextIndex);
	}
}