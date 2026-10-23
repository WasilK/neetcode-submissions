class Solution {
    public boolean makesquare(int[] matchsticks) {
        int sum = Arrays.stream(matchsticks).sum();
        if(sum % 4 != 0) return false;
        int length = sum / 4;
        int sides[] = new int[4];
        Arrays.sort(matchsticks);
        reverse(matchsticks);
        return backtrack(matchsticks, sides, 0, length);
    }

    boolean backtrack(int arr[], int sides[], int idx, int length){
        if(idx == arr.length){
            return true;
        }
        for(int i = 0; i < 4; i++){
            if(sides[i] + arr[idx] <= length){
                sides[i] += arr[idx];
                if(backtrack(arr, sides, idx + 1, length)) return true;
                sides[i] -= arr[idx];
            }
            if(sides[i] == 0) break;
        }
        return false;
    }

    void reverse(int arr[]){
        int l = 0, r = arr.length - 1;

        while(l < r){
            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            l++;
            r--;
        }
    }
}