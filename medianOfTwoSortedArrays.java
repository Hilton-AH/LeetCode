/*

Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

difficulty: medium

time: O(log(m+n))
space: O(1)

*/



class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) { //parameter: two sorted arrays
        int m = nums1.length; //length of nums1
        int n = nums2.length; //length of nums2
        if (m > n) { // to ensure m<=n
            int[] temp = nums1; nums1 = nums2; nums2 = temp; //swap the two arrays
            int tmp = m; m = n; n = tmp; //swap the two lengths
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2; //initialize the two pointers
        while (iMin <= iMax) { //loop until the two pointers cross
            int i = (iMin + iMax) / 2; //get the mid point
            int j = halfLen - i; //get the other mid point
            if (i < iMax && nums2[j-1] > nums1[i]){ //if the left part is too small, increase the left pointer
                iMin = i + 1; // i is too small --> increase iMin
            }
            else if (i > iMin && nums1[i-1] > nums2[j]) { //if the right part is too small, decrease the right pointer
                iMax = i - 1; // i is too big --> decrease iMax
            }
            else { // i is perfect
                int maxLeft = 0; //initialize the maxLeft
                if (i == 0) { maxLeft = nums2[j-1]; } //if i is 0, maxLeft is nums2[j-1]
                else if (j == 0) { maxLeft = nums1[i-1]; } //if j==0, then maxLeft is nums1[i-1]
                else { maxLeft = Math.max(nums1[i-1], nums2[j-1]); } //get the maxLeft
                if ( (m + n) % 2 == 1 ) { return maxLeft; } //if the length is odd, return the maxLeft

                int minRight = 0; //initialize the minRight
                if (i == m) { minRight = nums2[j]; } //
                else if (j == n) { minRight = nums1[i]; } 
                else { minRight = Math.min(nums2[j], nums1[i]); } //get the minRight

                return (maxLeft + minRight) / 2.0; //return the median
            }
        }
        return 0.0; //should never reach here because of the while loop
    }
}



//test
public class medianOfTwoSortedArrays {
    public static void main(String[] args) { 
        Solution s = new Solution(); //create a Solution object
        int[] nums1 = {1, 2}; //create a nums1 array
        int[] nums2 = {3, 4}; //create a nums2 array
        System.out.println(s.findMedianSortedArrays(nums1, nums2)); //print the result
    }
}