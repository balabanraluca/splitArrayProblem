import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of elements in the array: ");
        int n = input.nextInt();
        int[] A = new int[n];
        System.out.println("Enter the elements of the array, separated by spaces: ");
        for (int i = 0; i < n; i++) {
            A[i] = input.nextInt();
        }
        System.out.println(SplitArraySameAverage.splitArraySameAverage(A)); // output: true sau false
    }
}

