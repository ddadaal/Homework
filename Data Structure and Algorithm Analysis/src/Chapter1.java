import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

public class Chapter1 {
	public static void main(String[] args){
		combination(new int[] {1,2,3,4,5}, 3,0,new Stack<Integer>());
		hanoi(3,'A','C','B');
	}
	
	public static int work1(int n){
		if (n<2){
			return n;
		}else{
			return n%2 + work1(n/2);
		}
	}
	
	public static void combination(int[] array, int r, int start, Stack<Integer> stack){
		int length = array.length;
		if (r==1){
			for(int i=start;i<length;i++){
				stack.forEach(x->System.out.print(x+" "));
				System.out.println(array[i]);
			}
		}else if (length-start==r){ 
			stack.forEach(x->System.out.print(x+" "));
			System.out.println(Arrays.stream(array).skip(start).mapToObj(x->x+"").collect(Collectors.joining(" ")));
		}else{
			stack.push(array[start]);
			combination(array, r-1, start+1, stack);
			stack.pop();
			combination(array, r, start+1, stack);
		}
	}
	
	public static void permute(String str){
		permute(str.toCharArray(), 0, str.length()-1);
	}
	
	public static void permute(char[] str, int low, int high){
		if (low==high){
			for(char s: str){
				System.out.print(s);
			}
			System.out.println();
		}else{
			for(int i=low;i<=high;i++){
				char temp = str[low];
				str[low]=str[i];
				str[i]=temp;
				
				permute(str, low+1, high);
				
				temp = str[low];
				str[low]=str[i];
				str[i]=temp;
			}
		}
		
	}
	
	public static int work31(int[] array,int ci){
		if (ci==array.length-1){
			return array[ci];
		}else{
			final int a = array[ci];
			final int b = work31(array, ci+1);
			return a>b ? a :b;
		}
	}
	
	public static double work32(int[] array, int ci){
		final double length = array.length;
		if (ci==array.length-1){
			return array[ci]/length;
		}else{
			return array[ci]/length + work32(array, ci+1);
		}
	}
	
//	public static int length(LinkedList[] array, int ci){
//		if (array[ci]==NULL){
//			return 0;
//		}else{
//			return 1+ length(array, ci+1);
//		}
//	}
	
	public static boolean palindromes(String word, int ci){
		final int mirror = word.length() - ci-1;
		if (mirror<=ci){
			return true;
		}else{
			return (word.charAt(ci) == word.charAt(mirror)) && palindromes(word,ci+1);
		}
	}
	
	public static boolean palindromes(String sentence, int low, int high){
		if (high<=low){
			return true;
		}else if (!Character.isLetterOrDigit(sentence.charAt(low))){
			return palindromes(sentence, low+1, high);
		}else if (!Character.isLetterOrDigit(sentence.charAt(high))){
			return palindromes(sentence, low,high-1);
		}else{
			return (sentence.charAt(low)==sentence.charAt(high)) && palindromes(sentence, low+1, high-1);
		}
	}
	
	public static void hanoi(int disk, char from, char to, char other){
		
		if (disk==1){ 
			System.out.println(String.format("Move disk %d from %c to %c", disk, from, to));
		}else{
			hanoi(disk-1, from, other, to);
			System.out.println(String.format("Move disk %d from %c to %c", disk, from, to));
			hanoi(disk-1, other, to, from);
		}
	}


	
}
