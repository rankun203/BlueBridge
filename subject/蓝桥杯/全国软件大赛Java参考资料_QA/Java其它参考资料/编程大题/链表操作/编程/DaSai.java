import java.io.*;
class Node {//结点类
		private char data; //结点数据
	    private Node next; //下一个结点
	    
		public Node(){
			next = null;
		}
		public Node(char data,Node next){
			this.data = data;
			this.next = next;
		}
		public void setData (char data){   
 			this.data = data;
		}
		public char getData(){  
 			return data;
		}
		public void setNext(Node next){
			this.next = next;
		}		
		public Node getNext(){
			return next;
		}
  }

class LinkList {//链表类
	private Node head;     //头结点
	private int length;    //结点个数
	
	public LinkList(){     
	    length = 0;
		head = null;
	}
	
	public LinkList(String str) {  //通过字符串类建立链表
    	for(int i = 0; i < str.length(); i ++){
		    head = addNode(str.charAt(i));
		}
		length = str.length();
	}	
		
	public LinkList(char[] c){ //通过字符数组建立链表
		for(int i = 0; i < c.length; i ++){
		    head = addNode(c[i]);
		}
		length = c.length;
	}   

    public LinkList(int n) {//用[A,Z]范围内的随机字母构造n个结点的链表  
        if(n > 0){
        	int k;
            for(int i = 0; i < n ;i ++){
                k = (int) (Math.random() * 26 + 65);
				head = addNode((char)k);
            }
            length = n;
        }
        else
        	System.out.println("数据非法！");
    }
    
	public Node last(){//得到最后一个节点
		Node lastnode = head;
		while(lastnode.getNext() != null){
			lastnode = lastnode.getNext();
		}
		return lastnode;
	}
	
	public Node addNode(char c) { //向当前链表的尾部增加一个结点
		if(head == null){
			head = new Node();
			head.setData(c);
		}
		else{
			Node node = new Node();
			node.setData(c);
			last().setNext(node);
		}
		length ++; 
		return head;
	}
	
	public Node addNode(char c,int index) { //向链表的第index个结点之后增加一个结点
		if(head == null){
			head = new Node();
			head.setData(c);
		}
		else{
			Node node = new Node();
			node.setData(c);
			Node temp=head;
			for(int i = 0;i < index;i ++){
				temp = temp.getNext(); 
			}
			node.setNext(temp.getNext()); 
			temp.setNext(node); 
		}
		length ++;             
		return head;
	}
	
	public Node deleteNode(char c)throws Exception{ //删除指定数据的结点
		if(head == null){           
			throw new Exception("null node");
		}		
	    char ch = head.getData();
	    if(ch == c) {
	    	head = head.getNext();
	    	return head;
	    }	    
	    Node linkNode = head.getNext();       //获取下一个结点对象
	    Node tempNode = head;                 //存储上一个结点对象
	    while(linkNode != null) {
	    	ch = linkNode.getData();
	    	if(ch == c){
	    	    tempNode = linkNode.getNext(); 
	    	    return head;
	    	}
	    	tempNode = linkNode;	    	
			linkNode = linkNode.getNext();
		}
		length--;                             
		return head;
	}
	
	public void deleteNode(int index)throws Exception{ //删除链表指定位置的结点
		if(head == null){           
			throw new Exception("null node");
		}
		if(index<0 || index >length){
			System.out.print("位置不存在!");
		} 
		Node temp = head;
		if(index == 0)head = head.getNext();
		else{
			for(int i = 0; i < index-1; i ++){
				temp = temp.getNext();
			}	
			temp.setNext(temp.getNext().getNext());
		}		
		length --;
	} 

	public int length() {      //返回链表中结点的个数
	    return this.length;
	}
	
	public char charAt(int index){       //获取链表中指定索引位置的字符数据
	    int ilen = length;
	    if(index < 0 || index >= ilen){
	    throw new StringIndexOutOfBoundsException("索引超出边界："+index);
		}
		Node linkNode = head;
		for(int i = 0; i < index; i ++){
			linkNode = linkNode.getNext();
		}
	    return linkNode.getData();
	}
	
	public boolean isEmpty(){
		if(length() == 0)
			return true;
		else
			return false;
	}
}

public class DaSai{
    public static void main(String[] args) {
    	LinkList linkOne = new LinkList(6);    //建立链表对象
    	LinkList linkTwo=new LinkList(6);    
		
		System.out.println("随机生成的字母链表1是： ");
		for(int i = 0; i < linkOne.length(); i ++){
			System.out.print(linkOne.charAt(i));
		}
		
		System.out.println("\n\n随机生成的字母链表2是： ");
		for(int i = 0; i < linkTwo.length(); i ++){
			System.out.print(linkTwo.charAt(i));
		}
				
		System.out.println("\n\n链表1的补集是： ");
		complement(linkOne);

		System.out.println("\n\n链表1和链表2的交集是： ");
		intersection(linkOne,linkTwo);
		
		System.out.println("\n\n链表1和链表2的并集是： ");
		union(linkOne,linkTwo);
		
		System.out.println("\n\n链表1和链表2的并集去冗余后的集是： ");
		delRedundant(linkOne);		
	}
	
	
	static void complement(LinkList listOne){//求链表的补集
		System.out.println("等待考生完成！");
	}	
		
	static void intersection(LinkList listOne,LinkList listTwo){//求链表的交集
		System.out.println("等待考生完成！");
	}
		
	static void union(LinkList listOne,LinkList listTwo){//求链表的并集
		System.out.println("等待考生完成！");
	}
	
	static void  delRedundant(LinkList listOne){//去链表的冗余
		System.out.println("等待考生完成！");
	}
}
