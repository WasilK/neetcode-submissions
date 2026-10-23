class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int st = 0, en = numbers.length - 1;
        while(st != en){
            if(numbers[st] + numbers[en] == target){
                return new int[] {st+1, en+1};
            }else if(numbers[st] + numbers[en] > target){
                en--;
            }else{
                st++;
            }
        }
        return new int[] {};
    }
}
