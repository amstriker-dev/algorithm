package com.mm.am.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Sorting {
	private static int[] arrNumber;
	private static String[] arrDistinct;
	private static int[] arr;
	
	 public static void main(String[] args)
	    {
		 Scanner in = new Scanner(System.in);
		 System.out.print("Enter the number whose factorial is to be found: ");
		    int  N = in.nextInt();

		    arr = new int[N];

		    for (int i=0; i < N; i++){
		      arr[i] = in.nextInt();
		    }

		    if (IsSorted(arr)){
		      System.out.println("yes");
		      return;
		    }

		    if(CheckSingleSwap(arr))
		    return;

		    if(CheckSingleReverse(arr))
		    return;

		    System.out.println("no");
	}

	 private static boolean CheckSingleSwap(int[] arr)
	    {
	        //int count = 0;
	        int[] arrCopy = Arrays.copyOf(arr, arr.length);
	        Arrays.sort(arrCopy);
	       // List<Integer> indexes = new ArrayList<Integer>();
	        // 
	        int firstSwap = -1, secondSwap= -1;

	        for(int i = 0; i < arr.length; i++)
	        {
	            if(arr[i] != arrCopy[i])
	            {
//	                count++;
//	                indexes.add(i+1);
	            	 if (firstSwap == -1)
	                     firstSwap = i;
	                 else if (secondSwap == -1 && arr[i] == arrCopy[firstSwap] && arr[firstSwap] == arrCopy[i])
	                     secondSwap = i;
	                 else
	                     return false;
	            }
	        }
	        
	        if (firstSwap != -1 && secondSwap != -1) 
	        	{
	                System.out.println("yes");
	                System.out.println("swap " + (firstSwap + 1) + " " + (secondSwap + 1));
	                return true;
	            }
	            System.out.println("array is already sorted!");
	            return false; // or whatever you decide to do; maybe even an exception or enumerated type


	    }
	        
     

	private static boolean CheckSingleReverse(int[] A)
    {
		int[] B = Arrays.copyOf(A, A.length);
	    Arrays.sort(B);
	 // find region
	    int L = A.length;
	    int diffStart = -1, diffEnd = -1; boolean mid = false, found = false;
	    for (int i = 0; i < L; i++)
	    {
	        if (A[i] != B[i])
	        {
	            if (found)
	            {
	                if (i - diffEnd == 2 && !mid)
	                {
	                    mid = true;
	                    found = false;
	                    diffEnd = -1;
	                }
	                else
	                    return false;
	            }
	            else if (diffStart == -1)
	                diffStart = i;
	        }
	        else 
	        if (diffStart != -1 && diffEnd == -1)
	        {
	            found = true;
	            diffEnd = i - 1;
	        }
	    }
	    if (diffEnd == -1)
	    {
	        if (A[L - 1] != B[L - 1])
	            diffEnd = L - 1;
	        else if (!found)
	        {
	            System.out.println("array is already sorted!");
	            return false;
	        }
	    }

	    // find out if it's reversed
	    int count = (diffEnd - diffStart + 1) / 2;
	    for (int i = 0; i < count; i++)
	    {
	        int oneEnd = diffStart + i, otherEnd = diffEnd - i;
	        if (!(A[oneEnd] == B[otherEnd] && A[otherEnd] == B[oneEnd]))
	            return false;
	    }
	    System.out.println("yes");
	    System.out.println("reverse " + (diffStart + 1) + " " + (diffEnd + 1));
	    return true;   
    }
//        int length = arr.length;
//        int limit = length - 2;
//        int current = 1;
//
//        List<Integer> indexes = new ArrayList<Integer>();
//
//        while (current < limit)
//        {
//            for (int i = 0; i < length; i++)
//            {
//                int temp = current + i;
//                for (int j = i; j <= temp && temp < length; j++)
//                {
//                    indexes.add(j);
//                }
//
//                if (IsSorted(ReverseArrayPart(arr, indexes)))
//                {
//                    System.out.println("yes");
//                    System.out.println("reverse " + (indexes.get(0) + 1) + " " + (indexes.get(indexes.size() - 1) + 1));
//
//                    return true;
//                }
//                indexes.clear();
//            }
//
//            current++;
//        }
//
//        return false;
    
	
	private static int[] ReverseArrayPart(int[] arr, List<Integer> indexes) {
		int[] result = new int[arr.length];
		int[] arrayPart = new int[indexes.size()];
		int j = 0;

		for (int i = 0; i < arr.length; i++) {
			if (indexes.contains(i)) {
				arrayPart[j] = arr[i];
				j++;
			}
			result[i] = arr[i];
		}

		for (int i = 0; i < arrayPart.length / 2; i++) {
			int temp = arrayPart[i];
			arrayPart[i] = arrayPart[arrayPart.length - i - 1];
			arrayPart[arrayPart.length - i - 1] = temp;
		}

		j = 0;
		for (int i = 0; i < result.length; i++) {
			if (indexes.contains(i)) {
				result[i] = arrayPart[j];
				j++;
			}
		}

		return result;
	}

	 private static boolean IsSorted(int[] arr)
	    {
	        int length = arr.length;

	        for (int i = 0; i < length - 1; i++)
	        {
	            if (arr[i] > arr[i + 1])
	            {
	                return false;
	            }
	        }

	        return true;
	    }
}
