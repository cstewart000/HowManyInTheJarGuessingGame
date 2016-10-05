package com.company;

import java.util.ArrayList;

public class Main {


    private static ArrayList<Session> sessions = new ArrayList<>();
    private static boolean playAgain = true;
    private static int bestScore=0;
    private static Session bestSession = null;

    public static void main(String[] args) {


        // Playing loop
        while(playAgain) {
            runSession();
            playAgain();
        }

        endSessionAndReportGames();

    }

    private static void playAgain() {

        String playResponse = Prompter.messageStringResponse("\nPlay again y/n?: \t ");

        if (playResponse.equalsIgnoreCase("y")) {
            playAgain = true;

        } else if (playResponse.equalsIgnoreCase("n")) {
            playAgain = false;

        } else {
            Prompter.message("\nInvalid Response.\t ");
            playAgain();
        }
    }

    private static void endSessionAndReportGames() {
        for(Session session : sessions){

            if(session.getScore()<bestScore || bestScore ==0){
                bestScore = session.getScore();
                bestSession = session;
            }
        }

        Prompter.message(String.format("\n%s had the best score with only %d guesses for the number of %s in the jar : \t ",
                bestSession.getPlayerName(),
                bestSession.getScore(),
                bestSession.getGame().getItemName()));
    }

    private static void runSession() {

         // Setup text
        Prompter.message(" SETUP\n================\n\t");


        // Player I/O
        String playerName = Prompter.messageStringResponse("Player name?:\t ");
        Session session = new Session(playerName);

        // Jar item I/O
        String jarContentsString = Prompter.messageStringResponse("Name of items in the jar? \t ");

        // Jar Capacity I/O
        int maxNumItems  = Prompter.messageNumberResponse(String.format("Maximum number of %s that will fit in the jar?\t "
                , jarContentsString));

        // Initialise the game
        Game game = new Game(jarContentsString, maxNumItems);

        // Prompt for input
        System.out.println("\n TIME TO PLAY!\n================\n");

        while (game.isComplete() != true) {

            try {
                int guessNum = Prompter.messageNumberResponse(String.format("\nHow many %s do you think are in the jar?: \t "
                        , game.getItemName()));
                String response = game.guess(guessNum);

                Prompter.message(response);
            }catch (Exception e){

                System.out.printf("That was not a proper guess! Don't be a dork!\n");
            }

        }

        // Store the game and session into the record
        session.setGame(game);
        session.setScore(game.getNumberOfGuesses());
        sessions.add(session);
    }


}
