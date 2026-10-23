class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int lp = 0, rp = people.length - 1;
        int boats = 0;
        while(lp <= rp){
            int weight = people[lp] + people[rp];
            if(weight <= limit){
                lp++;
                rp--;
            }else{
                rp--;
            }
            boats++;
        }
        return boats;
    }
}