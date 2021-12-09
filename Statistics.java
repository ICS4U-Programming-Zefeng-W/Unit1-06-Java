/*
 * This program reads the three files and calculates and displays the mean, median,
 * and mode of the list of integers within those files.
 *
 * By Zefeng Wang
 * Created on December 7, 2021
 * */

// import modules
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// class Statistics
class Statistics { 
  public static void main(String[] args) throws IOException {
    // Reads all of the files within the folder
    File dir = new File("/home/ubuntu/environment/files");
    File[] files = dir.listFiles();
    List<Integer> list = new ArrayList<Integer>();
    for (File f : files) {
      if (f.isFile()) {
        BufferedReader inputStream = null;
        try {
          // Reads each line of each file and appends it to the ArrayList
          inputStream = new BufferedReader(new FileReader(f));
          String line;
          while ((line = inputStream.readLine()) != null) {
            list.add(Integer.parseInt(line));
          }
        } finally {
          if (inputStream != null) {
            inputStream.close();
          }
        }
      }
    }
    // converts the ArrayList to an array containing Integer objects
    Integer[] array = Arrays.copyOf(list.toArray(), list.size(), Integer[].class);
    // Calls the functions and outputs the result
    double mean = calcMean(array);
    System.out.println("The mean is " + mean);
    double median = calcMedian(array);
    System.out.println("The median is " + median);
    int mode = calcMode(array);
    System.out.println("The mode is " + mode);
  }

  // Calculate the mean of all values in the array
  private static double calcMean(Integer[] arr) {
    double sum = 0;
    for (Integer num : arr) {
      sum += num;
    }
    return sum / arr.length;
  }

  // Sorts the array and calculate the median of all values in the array
  private static double calcMedian(Integer[] arr) {
    Arrays.sort(arr);
    double middleIndex = (arr.length-1) / 2;
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
