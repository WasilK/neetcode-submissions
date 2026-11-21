class Solution {
    public boolean lemonadeChange(int[] bills) {
        int five = 0;
        int ten = 0;
        for(int bill : bills){
            if(bill == 5){
                five++;
            }else if(bill == 10){
                if(five == 0){
                    return false;
                }
                ten++;
                five--;
            }else{
                if(five < 3 && ten < 1){
                    return false;
                }
                if(ten > 0){
                    if(five < 1){
                        return false;
                    }
                    ten--;
                    five--;
                }else{
                    if(five < 3){
                        return false;
                    }
                    five -= 3;
                }
            }
        }
        return true;
    }
}