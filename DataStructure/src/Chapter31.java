import java.util.*;

public class Chapter31 {
	public static void main(String[] args){
		YanghuiTriangle(5);
	}
	
	public static void YanghuiTriangle(int n){
		Queue<Integer> queue = new LinkedList<>();
		
		int s=0, k=0, t=0;
		
		queue.add(1);
		queue.add(1);
		
		for(int i=1;i<=n;i++){
			System.out.println();
			queue.add(0);
			for(int j=1;j<=i+2;j++){
				t=queue.poll();
				queue.add(s+t);
				s=t; //最后一次循环中s被设为0，开启下一次循环。
				if (j!=i+2){
					System.out.print(s+" ");
				}
			}			
		}
		
	}
	
	
	
}
