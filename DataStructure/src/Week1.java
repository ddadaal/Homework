import java.util.HashMap;
import java.util.Stack;

public class Week1 {
	public static void main(String[] args){
		System.out.println(work1(3));
		
		System.out.println(work31(new int[] {1,4,2,5,8,2,8,9,0,-1},0));
		System.out.println(work32(new int[] {1,4,2,5,8,2,8,9,0,-1},0));
		permute("abc");
		System.out.println(palindromes("asdfdsa",0));
		String sentence = "Madam, I'm Adam";
		System.out.println(palindromes(sentence.toLowerCase(), 0, sentence.length()-1));
		
		hanoi(3,0,2,1);
	}
	
	public static int work1(int n){
		if (n<2){
			return n;
		}else{
			return n%2 + work1(n/2);
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
	
	public static void hanoi(int disk, int from, int to, int other){
		
		if (disk==1){
			System.out.println(String.format("Move disk %d from %d to %d", disk, from, to));
		}else{
			hanoi(disk-1, from, other, to);
			System.out.println(String.format("Move disk %d from %d to %d", disk, from, to));
			hanoi(disk-1, other, to, from);
		}
	}

	
}
