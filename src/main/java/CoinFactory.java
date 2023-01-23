package Coins;

import Coins.Coins;

public class  CoinFactory {



    public static Coin getCoin(Coins coinType)throws Exception
    {
        switch (coinType)
        {
            case USD:
                return new USD();

            case ILS:
                return new ILS();

            default:
                throw new Exception("invalid coin type");

        }
    }
}




















