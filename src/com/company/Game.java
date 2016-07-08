package com.company;

import com.sun.javafx.binding.StringFormatter;

import java.util.Random;

/**
 * Created by helloworld on 18/06/2016.
 */
public class Game {


    private int numberOfGuesses;
    private String itemName;
    private int itemsInJar;
    private int maxItemsInJar;
    private boolean complete;


    public Game(String itemName, int maxItemsInJar) {
        this.numberOfGuesses = 0;
        this.complete = false;
        this.itemName = itemName;
        this.maxItemsInJar = maxItemsInJar;
        setRandomItemsInJar();

    }

    // Set random

    private void setRandomItemsInJar() {
        Random random = new Random();

        // make sure 0 is not a valid random
        this.itemsInJar = random.nextInt(this.maxItemsInJar-1)+1;
    }

    // Handle guess

    public String guess(int guess) {

        String message ="";
        this.numberOfGuesses++;


        if (this.itemsInJar == guess) {
            message = String.format("Correct! \nYou guessed %d %s in %d tries!\n",
                    this.itemsInJar, this.itemName, this.numberOfGuesses);

            this.complete = true;

        } else if (guess > maxItemsInJar) {
            message = String.format("\nYou've guessed more than the jar can hold. "
                    +"Don't be silly! You have guessed %d times! \ntry again!\n",
                    this.numberOfGuesses);

        } else if (guess > this.itemsInJar) {
            message = String.format("Sorry! \nYou've guessed above the correct number. "
                    +"You have guessed %d times! \ntry again!\n",
                    this.numberOfGuesses);

        } else if (guess < this.itemsInJar) {
            message = String.format("Sorry! \nYou've guessed below the correct number. "
                            +"You have guessed %d times! \ntry again!\n",
                    this.numberOfGuesses);
        }

        return message;

    }

    // Setters and Getters

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public boolean isComplete() {
        return complete;
    }

    public int getNumberOfGuesses() {
        return numberOfGuesses;
    }
}
