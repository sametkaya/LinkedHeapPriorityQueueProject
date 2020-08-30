/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkedHeapPriorityQueueProject;

public class LinkedHeapPriorityQueue {

    Node LastNode = null;
    Node Root = null;

//It is used to insert the specified element into this queue and return true upon success.
    public boolean add(Node item) //O(log n)heapfy'dan kaynaklı
    {
        if (this.Root == null) {
            this.Root = item;
            this.LastNode = this.Root;
            return true;
        }

        boolean isAdded = false;
        Node temp = this.Root;
        while (!isAdded) {
            if (temp.right == null) {
                temp.level++;
                temp.right = item;
                item.parent = temp;
                //temp.left.next = temp.right;
                isAdded = true;
                LastNode.next = temp.right;
                LastNode.next.prev = LastNode;
                LastNode = LastNode.next;

            } else if (temp.left == null) {
                temp.level++;
                temp.left = item;
                item.parent = temp;
                //temp.next = temp.left;
                isAdded = true;
                LastNode.next = temp.left;
                LastNode.next.prev = LastNode;
                LastNode = LastNode.next;
            } else {
                temp.level++;
                if (temp.right.level < 2) {
                    temp = temp.right;
                } else if (temp.right.level > temp.left.level) {

                    temp = temp.left;

                } else {
                    temp = temp.right;

                }
            }

        }

        this.heapify();
        return true;
    }

    private void heapify() // O(log n) sıralama için sadece cgild ve parant arası dolaşım yapılıyor
    {
        if (this.LastNode.left != null && this.LastNode.left.data > this.LastNode.data) {
            this.SwapNodes(this.LastNode.left, this.LastNode);
        }
        if (this.LastNode.right != null && this.LastNode.right.data > this.LastNode.data) {
            this.SwapNodes(this.LastNode.right, this.LastNode);
        }

        Node tmp = this.LastNode;
        while (tmp.parent != null) {
            if (tmp.data < tmp.parent.data) {
                this.SwapNodes(tmp, tmp.parent);
            }
            tmp = tmp.parent;
        }
    }
    
     public void heapifyTop(Node tmp)
     {
         if(tmp ==null)
             return;
         
       if(tmp.left!= null && tmp.data>tmp.left.data)
       {
           this.SwapNodes(tmp.left, tmp);
           heapifyTop(tmp.left);
       }
       
        if(tmp.right!= null && tmp.data>tmp.right.data)
       {
           this.SwapNodes(tmp.right, tmp);
           heapifyTop(tmp.right);
       }
         
     }
//[5][6][12][8][7][14][19]

    public void printAll() {
        System.out.println("");
        Node tmp = this.Root;
        while (tmp != null) {
            System.out.print("[" + tmp.data + "]");
            tmp = tmp.next;
        }

    }

    public void removeMin() {
        SwapNodes(this.Root, this.LastNode);

        if (LastNode.parent.left != null && LastNode.parent.left == LastNode) {
            LastNode.parent.level--;
            LastNode.parent.left = null;
        } else if (LastNode.parent.right != null && LastNode.parent.right != LastNode) {
            LastNode.parent.level--;
            LastNode.parent.right = null;
        }
        LastNode = LastNode.prev;
        LastNode.next=null;

        heapifyTop(this.Root);
      
    }
    
    public Node peek() {
        
        return this.Root;
    }
    
    

    private void SwapNodes(Node node1, Node node2) {
        Node temp = new Node(node1);
        node1.CopyData(node2);
        node2.CopyData(temp);

    }
//	It is used to insert the specified element into this queue.
//    public boolean offer(E item) {
//    }
//It is used to retrieves and removes the head of this queue.
//    public E remove() {
//    }
//It is used to retrieves and removes the head of this queue.
//    public E poll() {
//    }
//It is used to retrieves and removes the head of this queue, or returns null if this queue is empty.
//    public E element() {
//    }
//It is used to retrieves, but does not remove, the head of this queue.
//    public E peek() {
//    }
//    public void add(Node2 newnode) {
//        if (this.LastNode == null) {
//            this.LastNode = newnode;
//            return;
//        }
//        boolean inserted = false;
//        Node2 temp = this.LastNode;
//        while (!inserted) {
//            if (newnode.data < temp.data) {
//                this.SwapNodes(newnode, temp);
//
//            }
//        }
//
//    }
//
//    public void PrintAll() {
//        Node2 temp = LastNode;
//        do {
//
//            System.out.print("[" + temp.data + "],");
//            temp = temp.left;
//        } while (temp != null);
//    }
//
//    public void Heapify() {
//        if (this.LastNode == null) {
//            return;
//        }
//        boolean isHeapify = false;
//        do {
//            isHeapify = false;
//            Node2 temp = LastNode;
//            do {
//                if (temp.data < temp.left.data) {
//                    this.SwapNodes(temp, temp.left);
//                    isHeapify = true;
//                }
//                temp = temp.left;
//
//            } while (temp.left != null);
//
//        } while (isHeapify);
//
//    }

    public static void main(String[] args) {
        LinkedHeapPriorityQueue list = new LinkedHeapPriorityQueue();
        Node n1 = new Node(5, 1);
        list.add(n1);
        //list.PrintAll();
        Node n2 = new Node(6, 1);
        list.add(n2);
        Node n3 = new Node(12, 1);
        list.add(n3);
        Node n4 = new Node(8, 1);
        list.add(n4);
        Node n5 = new Node(7, 1);
        list.add(n5);
        Node n6 = new Node(14, 1);
        list.add(n6);
        Node n7 = new Node(19, 1);
        list.add(n7);
        list.printAll();
        list.removeMin();
        list.printAll();
        
        Node n8 = new Node(3, 1);
        list.add(n8);
        list.printAll();
        
    }

}

class Node {

    int data;
    int priority;
    int level;
    Node next;
    Node prev;
    Node parent;
    Node right;
    Node left;

    public Node(int data, int priority) {
        this.data = data;
        this.priority = priority;
        this.level = 0;
        this.next = null;
        this.prev = null;
        this.parent = null;
        this.right = null;
        this.left = null;
    }

    public Node(int data, int priority, int index) {
        this.data = data;
        this.priority = priority;
        this.level = index;
        this.next = null;
        this.prev = null;
        this.right = null;
        this.left = null;
    }

    public Node(Node cnode) {
        this.data = cnode.data;
        this.priority = cnode.priority;
        this.level = cnode.level;
        this.next = cnode.next;
        this.right = cnode.right;
        this.left = cnode.left;

    }

    public void CopyOver(Node cnode) {
        this.data = cnode.data;
        this.priority = cnode.priority;
        this.level = cnode.level;
        this.next = cnode.next;
        this.prev = cnode.prev;
        this.left = cnode.left;
        this.right = cnode.right;
    }

    public void CopyData(Node cnode) {
        this.data = cnode.data;
        this.priority = cnode.priority;
        //this.level = cnode.level;

    }
}
