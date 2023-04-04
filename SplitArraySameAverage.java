import java.util.ArrayList;
import java.util.List;
//the program is importing the ArrayList and List classes from the java.util package.
public class SplitArraySameAverage {
    public static boolean splitArraySameAverage(int[] A) {
         // This line of code defines a public static method called splitArraySameAverage
        // that takes an integer array A as an argument and returns a boolean value.
        int n = A.length;
         // These lines of code initialize the integer variable n to the length of the array A
        // and compute the sum of all the elements in A and store it in the integer variable totalSum.
        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum += A[i];
        }
        //This line of code computes the average of the elements in A and stores it in the double variable avg.
        double avg = (double) totalSum/n;

        // Stage 1
        //An array of lists called "sumLists" is created.
        //Each list in "sumLists" will store all the possible sums that can be formed from the elements of the array "A", having a certain number of elements.
        //Each element in "A" is iterated through, and a new sum is added to each list in "sumLists" consisting of the sum of the previous elements in the list and the current element from "A".
        //By the end of the stage, all possible sums of subarrays that have at most "n/2" elements will be stored in "sumLists".
        List<Integer>[] sumLists = new ArrayList[n / 2 + 1];
        for (int i = 0; i <= n / 2; i++) {
            sumLists[i] = new ArrayList<>();
        }
        sumLists[0].add(0);
        for (int i = 0; i < n; i++) {
            for (int j = n / 2; j >= 1; j--) {
                for (int k = 0; k < sumLists[j - 1].size(); k++) {
                    sumLists[j].add(sumLists[j - 1].get(k) + A[i]);
                }
            }
        }

        // Stage 2 si 3
        //For each number of elements "i" from 1 to "n/2", check if it's possible to form a subarray with "i" elements that has the same average as the entire array.
        //To check this, calculate the sum of all possible subarrays with "i" elements from the "sumLists" array.
        //If the sum of a subarray divided by "i" equals the overall average of the array (within a certain precision), it means that subarray has the same average
        // as the entire array, so the indices of the elements in this subarray are used to create two new arrays "B" and "C".
        //"B" contains the elements with the indices that are set in "j" (represented as a bit mask), while "C" contains the elements with the indices that are not set in "j".
        //The average of "B" is equal to the sum of the subarray divided by "i", while the average of "C" is equal to the difference between the overall average and the average of "B".
        //Finally, return true if there exists a subarray that has the same average as the entire array, and false otherwise.
        for (int i = 1; i <= n / 2; i++) {
            if (totalSum * i % n == 0) {
                for (int j = 0; j < sumLists[i].size(); j++) {
                    double sum = (double) sumLists[i].get(j);
                    double subAvg = sum / i;
                    if (Math.abs(subAvg - avg) < 1e-5) {
                        System.out.print("Sum " + " : "+ sumLists[i].get(j) + "\n");
                        List<Integer> B = new ArrayList<>();
                        List<Integer> C = new ArrayList<>();
                        for (int k = 0; k < n; k++) {
                            if ((j >> k & 1) == 0) {
                                C.add(A[k]);
                            } else {
                                B.add(A[k]);
                            }
                        }
                        System.out.println("Subarray B " + " is "+ B + " : "+ subAvg);
                        System.out.println("Subarray C " + " is " +C + " : "+ (totalSum - sum) / (n - i));

                        return true;
                    }
                }
            }
        }
        return false;
    }
}