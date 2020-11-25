/** 
 * COMP 2503 Fall 2019 Assignment 2
 * 
 * Last updated by @author Maryam Elahi
 * @date Fall 2019
 * 
 * Singly Linked List Class, The generic type extends the Comparable interface
*/

import java.util.Comparator;
public class SLL<T extends Comparable<T>>
{
   Node<T> head, tail; 
   int size;

   public SLL()
   {
      head = null;
      tail = null;
      size = 0;
   }

   public int size()
   {
      return size;
   }

   public Node<T> get(int index) 
   {
       Node<T> curr = head;
       int i = 0;
       while (i != index && curr != null) 
       {
           curr = curr.getNext();
           i++;
       }
       return curr; 
   }
   
   
   public boolean contains(T object)
   {

      if (find(object) == null) 
      {
         return false;
      }
      else 
      {
         return true;
      }

   }

   public Node<T> find(T object) 
   {
       Node<T> curr = head;
       while (curr != null && !object.equals(curr.getData()))
       {
           curr = curr.getNext();
       }
       return curr;
   }

   public void add(T data, Comparator<T> comparatorObject)
   {
      addInOrder(new Node<T>(data), comparatorObject);
   }
   
   
   /**
    * Use the given comparator object to decide where is the right location to insert
    * the new node, to keep the list sorted based on the comparator's specified order. 
    */
   public void addInOrder(Node<T> newNode, Comparator<T> comparatorObject) 
   {
       if (head == null) 
       {
           addHead(newNode);
       } 
       else 
       {
           if (comparatorObject.compare(newNode.getData(), head.getData()) <= 0) 
              // less than first item, insert here. 
              addHead(newNode);
           else 
           {
              Node<T> curr = head;
              while (curr.getNext() != null 
            		  && comparatorObject.compare(newNode.getData(), curr.getNext().getData()) > 0) 
              {
                 // while greater than the curr item, go to the next node. 
                 curr = curr.getNext();
              }
              if (curr.getNext() == null) 
                 // did not find a place, greater than all in the list, add at the end 
              {
                  addTail(newNode);
              }
              else 
              {
            	  // found the right location for insert somewhere in the middle
                  newNode.setNext(curr.getNext());
                  curr.setNext(newNode);
                  size++;
              }
           }
       }
   }
   
   public void add(T data) 
   {
      addInOrder(new Node<T>(data));
   }

   /**
    * Use the natural order, i.e., the Comparable method (compareTo) 
    * to decide where is the right location to insert the new node 
    * to keep the list sorted.
    */
   public void addInOrder(Node<T> newNode) 
   {
       if (head == null) 
       {
           addHead(newNode);
       } 
       else 
       {
           if (newNode.getData().compareTo(head.getData()) <= 0) 
              // less than first item, insert here. 
              addHead(newNode);
           else 
           {
              Node<T> curr = head;
              while (curr.getNext() != null && newNode.getData().compareTo(curr.getNext().getData()) > 0) 
              {
                 curr = curr.getNext();
              }
              if (curr.getNext() == null) 
                 // did not find a place 
              {
                  addTail(newNode);
              }
              else 
              {
                  newNode.setNext(curr.getNext());
                  curr.setNext(newNode);
                  size++;
              }
              
           }
       }
   }
   
   public void addHead(Node<T> newNode) 
   {
       if (head == null) // empty list 
       {
           head = newNode; tail = newNode;
       }
       else 
       {
           newNode.setNext(head);
           head = newNode;
       }
       size++;
   }
   
   public void addHead(T data) {
	   addHead(new Node<T> (data));
   }
   
   public T deleteHead() {
	   Node<T> n = delHead();
	      if ( n == null) 
	         return null;
	      else 
	         return n.getData(); 
   }
   
   private Node<T> delHead() 
   {
      Node<T> n = null;
      if ( head != null) 
      {
         n = head;
         if ( head == tail) 
            tail = head.getNext();
         head = head.getNext();
         size--;
      }
      return n;
   }
   
   public void addTail( Node<T> newNode) 
   {
       if (tail == null) // list is empty
       {
           head = newNode; 
           tail = newNode;
       }
       else 
       {
          tail.setNext(newNode);
          tail = newNode;
       }
       size++;
   }
   
   public void addTail(T data) {
	   addTail(new Node<T> (data));
   } 
   
   public void printList()
   {
       Node<T> curr = head;
       while (curr != null) 
       {
          // Visit the node. In this case, print it out. 
           System.out.println(curr.toString());
           curr = curr.getNext();
       }
   }
   
   public void printListNodeIndicator()
   {
       System.out.println();
       System.out.print("head->");
       Node<T> curr = head;
       while (curr != null) 
       {
          // Visit the node. In this case, print it out. 
           System.out.print("[" + curr.toString() + "]->");
           curr = curr.getNext();
       }
       System.out.println("null");
   }
   
   public void emptyList()
   {
       head = null;
       tail = null;
   }
   
}


 class Node<T extends Comparable<T>>
{
   
    private T data;
    private Node<T> next;
    
    /**
     * Constructor for objects of class Node
     */
    public Node(T d)
    {
       data = d;
       next = null;
    }

    public T getData() { return data; }
    public void setData(T o) { data = o; }
    
    public Node<T> getNext() { return next; }
    public void setNext(Node<T> n) { next = n; }
    public String toString() 
    {
        return getData().toString();
    }
    
}
