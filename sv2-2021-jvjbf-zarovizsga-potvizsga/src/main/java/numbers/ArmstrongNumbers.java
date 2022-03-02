package numbers;

public class ArmstrongNumbers {
    public boolean isArmstrongNumber(int number){
        if(number<0){
            throw new IllegalArgumentException("Number can't be negative: "+number);
        }
        Integer numObj = number;
        int sum=0;
        for (int i = 0;i<numObj.toString().length();++i){
            sum+=Math.pow(Integer.parseInt(numObj.toString().substring(i,i+1)),numObj.toString().length());
        }
        if (sum==number){
            return true;
        }else {
            return false;
        }
    }
}
