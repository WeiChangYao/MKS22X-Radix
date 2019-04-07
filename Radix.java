public class Radix{
  public static void radixsort(int[]data){
    int m = getMax(data); //get maxnum for digits
    @SuppressWarnings("unchecked")
    MyLinkedList[] buckets = new MyLinkedList[20];
    for (int digit = 1; m/digit > 0; digit *= 10){
      for (int i = 0; i < data.length; i++){
        if (data[i] < 0){
          buckets[data[i]/digit % 10 * -1].add( data[i] ); //goes in bucket 0-9
        }
        else{
          buckets[data[i]/digit % 10 + 10].add( data[i] ); //bucket 10-19
        }
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
  
///////////////////////NODE STUFF///////////////////////
class Node{
  private Integer data;
  private Node next,prev;
  public Node(Integer num, Node nextNum, Node prevNum){
    data = num;
    next = nextNum;
    prev = prevNum;
  }
  public Node next(){
    return next;
  }
  public Node prev(){
    return prev;
  }
  public void setNext(Node other){
    next = other;
  }
  public void setPrev(Node other){
    prev = other;
  }
  public Integer getData(){
    return data;
  }
  public Integer setData(Integer i){
    data = i;
    return data;            //DOES THIS RETURN OLD DATA!?!?!?!?
                            //BUMP^^^^
  }
  public String toString(){
    String str = "";
    return str += data;
  }
}
//////////////////MY LINKED LIST////////////////////
class MyLinkedList{
  private int size;
  public Node start,end;
  private int length;

  MyLinkedList(){
    size = 0;                       //makes list with size 0
    start = new Node(null,null,null);//starting node with nothing
    end = new Node(null,null,null);
  }

  public int size(){
    return size;                   //returns size
   }

  public boolean add(Integer value){
    Node current = start;            //start with start
    Node newNode = new Node(value, null, null); //makes a new node with value
    while (current.next() != null){  //dont stop until you reach the end
      current = current.next();    //current turns into next node
    }
    current.setNext(newNode);      //makes current node point to new node
    newNode.setPrev(current);      //makes new node point back
    size++;                        //add to size
    if(start.getData() == null){   //IF THERE'S NOTHING THERE!!!
      start = current;             //start node turns into the new node
      start.setPrev(null);         //new node has no previous node
    }
    end = newNode;                 //MAKES THE NEW NODE END
    return true;
  }



  public String toString(){
    String str = "[";             //starts with [
    Node current = start;         //start with first node
    while (current != null){      //dont stop until you reach the end
      if(current.getData() != null){  //as long as data is not nothing (because I made start null by default in the beginning)
        str += current.getData();     //add to string
        if(current.next() != null){   //add ", " as long as it's not the last node
          str+= ", ";
        }
      }
      current = current.next();    //move to next node

    }
    str += "]";                  //] to close it off
    return str;                  //return the computer's hard work
  }

  private Node getNthNode(int index){
    int i = 0;                   //start with 0
    Node current = start;        //start with start node
    while (current != null){     //as long as current isn't nothing
      if (i == index){           //stop when counter is equal to index
        return current;
      }
      current = current.next();  //move on and add to counter
      i++;

    }
    return current;              //return current node
  }

  public Integer get(int index){
    if(index < 0) throw new IndexOutOfBoundsException();
    if(index > size-1) throw new IndexOutOfBoundsException();
    return getNthNode(index).getData();   //return data of that node
  }

  public Integer set(int index, Integer value){
    if(index < 0) throw new IndexOutOfBoundsException();
    if(index > size-1) throw new IndexOutOfBoundsException();
    Integer a = getNthNode(index).getData();  //save the data
    getNthNode(index).setData(value);         //set node's value to new value
    return a;                                 //return saved data
  }

  public boolean contains(Integer value){
    Node current = start;
    while (current != null){
      if(value.equals(current.getData())){ //checks if data is value
        return true;                       //as long as the node isn't blank
      }
      current = current.next();
    }
    return false;
  }

  public int indexOf(Integer value){
    int count = 0;
    Node current = start;
    while (current != null){
      if(value.equals(current.getData())){
        return count;          //if value is equal to the node data
      }                        //return count
      count++;                 //else, move on and add to count
      current = current.next();
    }
    return -1;                 //-1 if it's not there
  }

  public void add(int index,Integer value){
    if(index < 0) throw new IndexOutOfBoundsException();
    if(index > size) throw new IndexOutOfBoundsException();
    if (index == 0){
      addFirst(value);  //add to first if index is 0
    } else{                     //I HAD TO ADD AN ELSE THERE OTHERWISE addFirst WOULDN'T WORK
    if (index == size){
      addLast(value);   //ADD TO FIRST IF ADDING TO LAST INDEX (CAPS SO I CAN REFER TO THIS)
    }

    else{
    size++;             //add to size
    Node newNode = new Node(value, null, null); //make new node with value
    newNode.setNext(getNthNode(index));         //set new node's next to node at index
	  getNthNode(index-1).setNext(newNode);       //set the thing before index node to new node
    }                                           //FORGOT TO SET PREV
  }
  }


  public void addFirst(Integer value){
    size++;                                //add to size
    Node newNode = new Node(value, start, null); //make new node that points to start
    if (start != null){                   //if start is real
      start.setPrev(newNode);             //make it point back
    }
    start = newNode;                      //new node is the new start
  }

  public void addLast(Integer value){
    size++;
    Node newNode = new Node(value, null, end); //newNode points to end
    if (end != null){
      end.setNext(newNode);                    //end points back at newNode
    }
    end = newNode;                             //newNode is new end
  }

  public Integer remove(int index){
    if(index < 0) throw new IndexOutOfBoundsException();
    if(index > size-1) throw new IndexOutOfBoundsException();
    if(index == size-1){           //size -1 is the last index
        return removeLast();
     }
    if(index == 0){
      return removeFirst();
    }
    else{
    Node newNode = getNthNode(index);//Literally replaced getNthNode with newNode (newNode = getNthNode(index)) didn't work >:[ [for the bottom half]
    Integer removeReturn = newNode.getData();//save data at index
    newNode.next().setPrev(newNode.prev());  //set node infront index point to node before index
    getNthNode(index-1).setNext(getNthNode(index+1));  //set node before index point to node infront index
    size--;                                            //minus size
    return removeReturn;
    }
  }



  public boolean remove(Integer value){
   if (contains(value) == true){        //sees if the value's there
      if(indexOf(value) == size-1){     //if value is last, removeLast
        removeLast();
        return true;
      }
     else{
      remove(indexOf(value));         //remove thing in value's index
      return true;
     }
    }
    else{return false;}
  }

  public Integer removeLast(){
    Node current = getNthNode(size-1); //current is last node
    Integer val = current.getData();   //save value
    current = current.prev();      //current is second to last node
    current.setNext(null);             //it doesn't point to anything
    end = current;                     //it's the new end node
    size--;                            //decrease size
    return val;
  }

  public Integer removeFirst(){
    Node current = start;
    Integer val = start.getData();    //save start data
    current = start.next();           //current is second node
    current.setPrev(null);            //it points back to nothing
    start = current;                  //it's the new start node
    size--;
    return val;
  }

  public String toBackString(){
    String str = "";
    Node current = end;         //start with first node
    while (current != null){      //dont stop until you reach the end
      if(current.getData() != null){  //as long as data is not nothing (because I made start null by default in the beginning)
        str += current.getData();     //add to string
      }
      current = current.prev();    //move to next node
    }
    return str;                  //return the computer's hard work
  }

  public void extend(MyLinkedList other){
    this.size = other.size + this.size;  //adds the sizes in this list
    other.size = 0;                       //other list is size 0
    this.end.setNext(other.start);        //points this end to the start of other
    other.start.setPrev(this.end);        //start of other points back
    this.end = other.end;                 //the other end becomes the new end node
    other.start = new Node(null,null,null);
    other.end = new Node(null,null,null); //erasing other list

  }
}
}
