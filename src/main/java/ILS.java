package Coins;


public class ILS extends Coin {

    private final  static  double value = 0.28;


   @Override
   public double getValue() {

       return value;
   }


   @Override
public double calculate(double amount){
       return  amount * getValue();
   }
}

}
