import java.util.*;

public class Radix{
  public static void radixsort(int[]data){
    int size = data.length;
    int m = getMax(data); //get maxnum for digits
    LinkedList[] buckets = new LinkedList[20];
    for (int c = 0; c < 20; c++){
      buckets[c] = new LinkedList();
    }
    for (int digit = 1; m/digit > 0; digit *= 10){
      for (int i = 0; i < data.length; i++){
        if (data[i] < 0){
          buckets[((data[i]/digit) % 10 * -1)].add(data[i]); //goes in bucket 0-9
        }
        else{
          buckets[data[i]/digit % 10 + 10].add(data[i]); //bucket 10-19  last
        }
      }
      //transfer bucket stuff into data
      int combine = 0;
      LinkedList<Integer> linked = new LinkedList<Integer>();
      while(combine < 20){
        linked.addAll(buckets[combine]);
        buckets[combine].clear();
        combine++;
      }
      for(int j = 0; j < size; j++){
        data[j] = linked.remove();
      }
    }
  }


  public static int getMax(int[] data){
    int maxNum = data[0];              //takes first thing in data
      for (int i = 1; i < data.length; i++){     //compares with other things
        if (data[i] > maxNum)
        maxNum = data[i];              //change maxNum
      }
    return maxNum;                     //returns biggest
  }
}
