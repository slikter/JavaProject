package Bank;

import Coins.*;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    final static String conversionResHistoryFileName = "results.txt";


    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to currency converter");
        Scanner scanner = new Scanner(System.in);
        int selectedConversion;
        Coin selectedCoin = null;
        List<String> conversionResHistory = new ArrayList<>();

        boolean exitMainLoop = true;
        do {

            System.out.println("Please choose an option (1/2):\n1. Dollars to Shekels\n2. Shekels to Dollars");
            Coins selectedCoinType = Coins.ILS;
            boolean exitCoinTypeLoop = true;
            do {

                exitCoinTypeLoop = true;
                selectedConversion = scanner.nextInt();
                switch (selectedConversion) {
                    case 1:
                        selectedCoinType = Coins.USD;
                        break;

                    case 2:
                        selectedCoinType = Coins.ILS;
                        break;

                    default:
                        System.out.println("Invalid Choice, please try again");
                        exitCoinTypeLoop = false;
                        break;

                }
            }while (!exitCoinTypeLoop);

            try
            {
                selectedCoin = CoinFactory.getCoin(selectedCoinType);
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
                return;
            }

            System.out.println("Please enter an amount to convert");
            double selectedAmount = scanner.nextDouble();
            double convertedAmount = selectedCoin.calculate(selectedAmount);
            String curConversionRes = selectedAmount + " in " + selectedCoinType + " coin is worth "
                    + convertedAmount + " in " + ((selectedCoinType== Coins.USD) ? Coins.ILS : Coins.USD)  + " coin";
            conversionResHistory.add(curConversionRes);
            System.out.println(curConversionRes);

            System.out.println("start over? (Y/N)");

            boolean exitStartOverChoiceLoop = true;
            do {

                String startOverChoice = scanner.next();
                exitMainLoop = true;
                exitStartOverChoiceLoop = true;
                if (startOverChoice.equalsIgnoreCase("Y"))
                    exitMainLoop = false;
                else if (!startOverChoice.equalsIgnoreCase("N")) {
                    System.out.println("Invalid Choice, please try again");
                    exitStartOverChoiceLoop = false;
                }
            }while (!exitStartOverChoiceLoop);

        }while(!exitMainLoop);

        System.out.println("Thanks for using our currency converter.\nconversion history:");
        String conversionResHistoryStr = "";
        for (String curConversionRes: conversionResHistory)
        {
            System.out.println(curConversionRes);
            conversionResHistoryStr += curConversionRes + "\n";

        }

        BufferedWriter outputWriter = null;
        try
        {
            outputWriter = new BufferedWriter(new FileWriter(conversionResHistoryFileName));
            outputWriter.write(conversionResHistoryStr);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return;
        }
        outputWriter.close();

        // open the conversion results history file in its default application in this computer
        
        try
        {
            //   Runtime.getRuntime().exec("cmd.exe /c Start " + conversionResHistoryFileName);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File(conversionResHistoryFileName));
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}

