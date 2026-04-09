package org.example;

import java.util.Arrays;

class Solution {
    public static boolean hasDuplicate(int[] nums) {
        boolean result = false;
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j<nums.length;j++){
                // result = nums[i]==nums[j]?true:false;
                if(nums[i] == nums[j]){
                    result = true;
                }
            }
        }
        return result;
    }
}
