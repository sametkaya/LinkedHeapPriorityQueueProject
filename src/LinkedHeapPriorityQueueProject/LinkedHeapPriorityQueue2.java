/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LinkedHeapPriorityQueueProject;





public class LinkedHeapPriorityQueue2 {

    Node2 LastNode = null;
    Node2 Root = null;

//It is used to insert the specified element into this queue and return true upon success.
    public boolean add(Node2 item) //O(log n)heapfy'dan kaynaklı
    {
        if (this.Root == null) {
            this.Root = item;
            this.LastNode = this.Root;
            return true;
        }
        boolean isAdded = false;
        Node2 temp = this.Root;
        while (!isAdded) {
            if (temp.left == null) {
                temp.left = item;
                item.parent = temp;
                temp.weight++;
                isAdded = true;
                LastNode = temp.left;
            } else if (temp.right == null) {
                temp.right = item;
                item.parent = temp;
                temp.weight++;
                isAdded = true;
                LastNode = temp.right;
            } else {
                if (temp.right.weight > temp.left.weight) {
                    
                    temp = temp.left;
                    temp.weight++;
                } else {
                    temp = temp.right;
                    temp.weight++;
                }
            }

        }

        /*
        if (this.LastNode.left == null) {
            this.LastNode.left = item;
            item.parent = LastNode;

        } else if (this.LastNode.right == null) {
            this.LastNode.right = item;
            item.parent = LastNode;

        } else {
            if (Math.abs(this.LastNode.right.data - item.data) < Math.abs(this.LastNode.left.data - item.data)) {
                this.LastNode.right.right = item;
                item.parent = this.LastNode.right;
                LastNode = item;
            } else {
                this.LastNode.left.left = item;
                item.parent = this.LastNode.left;
                LastNode = item;
            }
        }*/
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

        Node2 tmp = this.LastNode;
        while (tmp.parent != null) {
            if (tmp.data < tmp.parent.data) {
                this.SwapNodes(tmp, tmp.parent);
            }
            tmp = tmp.parent;
        }

    }
    public void removeMin()
    {
        
        
    }
    private void SwapNodes(Node2 node1, Node2 node2) {
        Node2 temp = new Node2(node1);
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
        LinkedHeapPriorityQueue2 list= new LinkedHeapPriorityQueue2();
        Node2 n1= new Node2(1, 1);
        list.add(n1);
        //list.PrintAll();
        Node2 n2= new Node2(2, 1);
        list.add(n2);
        Node2 n3= new Node2(7, 1);
        list.add(n3);
        Node2 n4= new Node2(3, 1);
        list.add(n4);
        Node2 n5= new Node2(5, 1);
        list.add(n5);
        //list.PrintAll();
    }
}
 class Node2 {

    int data;
    int priority;
    int weight;
    Node2 parent;
    Node2 right;
    Node2 left;

    public Node2(int data, int priority) {
        this.data = data;
        this.priority = priority;
        this.weight = 0;
        this.parent = null;
        this.right = null;
        this.left = null;
    }

    public Node2(int data, int priority, int index) {
        this.data = data;
        this.priority = priority;
        this.weight = index;
        this.parent = null;
        this.right = null;
        this.left = null;
    }

    public Node2(Node2 cnode) {
        this.data = cnode.data;
        this.priority = cnode.priority;
        this.weight = cnode.weight;
        this.parent = cnode.parent;
        this.right = cnode.right;
        this.left = cnode.left;

    }

    public void CopyOver(Node2 cnode) {
        this.data = cnode.data;
        this.priority = cnode.priority;
        this.weight = cnode.weight;
        this.parent = cnode.parent;
        this.left = cnode.left;
        this.right = cnode.right;
    }

    public void CopyData(Node2 cnode) {
        this.data = cnode.data;
        this.priority = cnode.priority;
        //this.weight = cnode.weight;

    }
}