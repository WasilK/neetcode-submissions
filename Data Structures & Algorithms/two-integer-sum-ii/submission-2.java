class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int st = 0, en = numbers.length - 1;

        while(st != en){
            int sum = numbers[st] + numbers[en];
            if(sum == target){
                return new int[] {st + 1, en + 1};
            }else if(sum > target){
                en--;
            }else{
                st++;
            }
        }
        return new int[] {};
    }
}
