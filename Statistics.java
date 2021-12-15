
/*
 * This program reads the three files, then calculates and displays the mean, median,
 * and mode of the list of integers within those files.
 *
 * By Zefeng Wang
 * Created on December 7, 2021
 * */

// import modules
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

// class Statistics
class Statistics { 
  public static void main(String[] args) {
    List<String> lines = Collections.emptyList();
    try {

      // Reads each line of the file and stores it into an array
      lines = Files.readAllLines(Paths.get("/home/ubuntu/environment/files/Unit1-06/set2.txt"),
                                 StandardCharsets.UTF_8);
      Iterator<String> itr = lines.iterator();
      List<Integer> list = new ArrayList<Integer>();
      for (int i = 0; itr.hasNext(); i++) {
        list.add(Integer.parseInt(itr.next()));
      }
      Integer[] array = Arrays.copyOf(list.toArray(), list.size(), Integer[].class);
      
      // Calls the functions and outputs the result
      double mean = calcMean(array);
      System.out.println("The mean is " + mean);
      double median = calcMedian(array);
      System.out.println("The median is " + median);
      int mode = calcMode(array);
      System.out.println("The mode is " + mode);

    } catch (Exception e) {
      System.out.println("This file contains an invalid value.");
    }
  }

   

  // Calculate the mean of all values in the array
  private static double calcMean(Integer[] arr) {
    double sum = 0.0;
    for (Integer num : arr) {
      sum += num;
    }
    return sum / arr.length;
  }

  // Calculate the median of all values in the array
  private static double calcMedian(Integer[] arr) {

    // Gets the index at the middle of the array and sorts the array
    double middleIndex = (arr.length - 1) / 2.0;
    Arrays.sort(arr);

    // Check to see if the index is an integer
    if (middleIndex % 1 == 0) {
      return arr[(int) middleIndex];
    } else {
      return ((double) arr[(int) Math.floor(middleIndex)] 
                + arr[(int) Math.ceil(middleIndex)]) / 2.0;
    }
  }
  
  // Calculate the mode of all values in the array
  private static int calcMode(Integer[] arr) {
    int maxValue = 0;
    int maxCount = 0;

    for (int i = 0; i < arr.length; i++) {
      int count = 0;
      for (int j = 0; j < arr.length; j++) {
        if (arr[i] == arr[j]) {
          count++;
        }
      }
      if (count > maxCount) {
        maxCount = count;
        maxValue = arr[i];
      }
    }
    return maxValue;
  }  
}


