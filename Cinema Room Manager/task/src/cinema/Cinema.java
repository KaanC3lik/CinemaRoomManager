package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    static Scanner scanner = new Scanner(System.in);
    static int numberOfRow;
    static int numberOfColumn;
    static int totalSeat;
    static int numberOfSeatsTaken = 0;
    static int currentIncome = 0;
    static String[][] array;

    public static void arrangeSeats() {

        for (int i = 0; i <= numberOfRow; i++) {
            for (int j = 0; j <= numberOfColumn; j++) {
                if (i != 0 && j < 1) {
                    array[i][j] = "" + i;
                } else if (i == 0 && j >0 ) {
                    array[i][j] = "" + j;
                } else {
                    array[i][j] = "S";
                }
                if (j == 0 && i == 0) {
                    array[i][j] = " ";
                }
            }
        }
    }
    public static int menu(){
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static void ShowSeats() {
        System.out.println("Cinema:");

        for (int i = 0; i <= numberOfRow; i++) {
            for (int j = 0; j <= numberOfColumn; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void buyTicket() {

        int ticket;
        int whichRow;
        int whichColumn;
        while (true) {
            System.out.println("Enter a row number:");
            whichRow = scanner.nextInt();

            System.out.println("Enter a seat number in that row:");
            whichColumn = scanner.nextInt();
            if (numberOfRow < whichRow || numberOfColumn < whichColumn) {
                System.out.println("Wrong input");
                continue;
            }

            boolean seatTaken = array[whichRow][whichColumn].equals("B");

            if (!seatTaken ) {
                if (totalSeat <= 60) {
                    ticket = 10;
                    System.out.println("Ticket price: $" + ticket);
                }else {
                    if (whichRow <= ((numberOfRow / 2))) {
                        ticket = 10;
                        System.out.println("Ticket price: $" + ticket);
                    } else {
                        ticket = 8;
                        System.out.println("Ticket price: $" + ticket);
                    }
                }
                array[whichRow][whichColumn] = "B";
                System.out.println();
                numberOfSeatsTaken++;
                currentIncome += ticket;
                break;
            }else if (seatTaken){
                System.out.println("That ticket has already been purchased!");
            }
        }
    }

    public static void statistics() {
        float numberOfSeatsPurchased = numberOfSeatsTaken;
        float sumOfSeats = totalSeat;
        float percantage = (numberOfSeatsPurchased / sumOfSeats) * 100;


        System.out.println("Number of purchased tickets: " + numberOfSeatsTaken);

        System.out.print("Percentage: ");
        System.out.printf("%.2f", percantage);
        System.out.println("%");

        System.out.println("Current income: $" + currentIncome);

        System.out.print("Total income: $");
        System.out.print(findTotalIncome());
        System.out.println();
    }

    public static int findTotalIncome() {
        int ticket;
        int totalIncome;
        if (totalSeat <= 60) {
            ticket = 10;
            totalIncome = ticket * totalSeat;
            return totalIncome;
        }else {
            if (numberOfRow % 2 == 1) {
                totalIncome = (((numberOfRow / 2) + 1) * numberOfColumn * 8) + (numberOfRow / 2) * numberOfColumn * 10;
                return totalIncome;
            }else {
                totalIncome = ((numberOfRow / 2) * 8) * numberOfColumn + (numberOfRow / 2) * numberOfColumn * 10;
                return totalIncome;
            }
        }
    }

    public static void main(String[] args) {
        // Write your code here

        System.out.println("Enter the number of rows:");
        numberOfRow = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        numberOfColumn = scanner.nextInt();
        System.out.println();

        totalSeat = numberOfRow * numberOfColumn;

        array = new String[numberOfRow + 1][numberOfColumn + 1];

        arrangeSeats();

        int input;
        boolean exit = false;

        while (!exit) {
            input = menu();
            if (input == 1) {
                ShowSeats();
            } else if (input == 2) {
                buyTicket();
            } else if (input == 3) {
                statistics();
            } else if (input == 0) {
                exit = true;
            }
        }

        /*
         */

    }
}
