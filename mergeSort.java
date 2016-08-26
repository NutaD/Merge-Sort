package sort;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class mergeSort {

    public static int[] ReadArray(String file) // read an initial file
    {
        int [] numArray;
        try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(new FileInputStream(file))))
        {
            String[] arr= reader.lines().toArray(size->new String[size]); //create array of String from numbers in file
            numArray= new int [arr.length];
            for (int i=0;i<arr.length;i++)   //convert array of String to array of Integer
            {
                numArray[i]=Integer.valueOf(arr[i]);
            }
        } 
        catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return numArray;
    }
    
    public static int[] SplitMerge(int [] Array) { // recursive realization of Merge Sort algorithm
        if (Array.length <= 1) 
        {
            return Array;
        }
        int len=Array.length/2; //devide array on 2 parts
        int[] left=Arrays.copyOfRange(Array, 0, len); //create left part
        int[] right=Arrays.copyOfRange(Array,len,Array.length); //create right part
        SplitMerge(left);
        SplitMerge(right);
        MergeSort(left, right, Array); //merge and sort both parts 
        return Array;
    }
    
    private static void MergeSort(int[] left, int[] right, int [] Array) {
        int i1=0, i2 = 0; //counter of indexes' for left and right parts of devided Array
        int j = 0; //counter for positions in array
        while (i1 < left.length && i2 < right.length) {
            if (left[i1] < right[i2]) 
            {
                Array[j] = left[i1];
                i1++;                
            } else 
            {
                Array[j] = right[i2];
                i2++;
            }
            j++;
        }
        // copy sorted parts to array
        System.arraycopy(left, i1, Array, j, left.length - i1);
        System.arraycopy(right, i2, Array, j, right.length - i2);
    }
   
    public static void main(String args[]) throws Exception
    {
        int Array[];
        Array=ReadArray("C:\\MergeSort.txt");
        if (Array!=null)
        {
            SplitMerge(Array);
            for (int i=0;i<Array.length;i++)
            {
                System.out.println(Integer.toString(Array[i])); // print results
            }
        }
    }
}
