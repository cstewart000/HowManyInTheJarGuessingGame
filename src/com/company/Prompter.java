package com.company;

import java.util.Scanner;

/**
 * Created by helloworld on 8/07/2016.
 */
public class Prompter {

    private static Scanner in = new Scanner(System.in);


    public static void message(String message) {
        System.out.println(message);
    }

    public static String messageStringResponse(String prompt) {

        message(prompt);
        return in.nextLine();

    }

    public static int messageNumberResponse(String prompt) {

            String stringNumIn = messageStringResponse(prompt);
            int number;

            try {
                number = Integer.parseInt(stringNumIn);
            }catch(Exception e){
                message("Invalid entry, you must enter a number");
                number = messageNumberResponse(prompt);
            }
            return number;
    }

}

