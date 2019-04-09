import java.util.*;

public class Radix{
  public static void radixsort(int[]data){
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
        combine++;
      }
      //int k = linked.size();
      for(int j = 0; j < linked.size(); j++){
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
  
  public static String str(int[] data){
    String str="";
    for(int i = 0; i < data.length; i++){
      str+=data[i]+ " ";
    }
    return str;
  }
  
  public static void main(String[]args){
    int[] cat = new int[]{6,5,2,12};
    radixsort(cat);
    System.out.println(str(cat));

/*
  System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
  int[]MAX_LIST = {1000000000,500,10};
  for(int MAX : MAX_LIST){
    for(int size = 31250; size < 2000001; size*=2){
      long qtime=0;
      long btime=0;
      //average of 5 sorts.
      for(int trial = 0 ; trial <=5; trial++){
        int []data1 = new int[size];
        int []data2 = new int[size];
        for(int i = 0; i < data1.length; i++){
          data1[i] = (int)(Math.random()*MAX);
          data2[i] = data1[i];
        }
        long t1,t2;
        t1 = System.currentTimeMillis();
        radixsort(data2);
        t2 = System.currentTimeMillis();
        qtime += t2 - t1;
        t1 = System.currentTimeMillis();
        Arrays.sort(data1);
        t2 = System.currentTimeMillis();
        btime+= t2 - t1;
        if(!Arrays.equals(data1,data2)){
          System.out.println("FAIL TO SORT!");
          System.exit(0);
        }
      }
      System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
    }
    System.out.println();
  }  */


}




}
