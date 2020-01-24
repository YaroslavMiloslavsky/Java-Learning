public class Driver {

	public static void main(String[] args) {
		
		int[] array = {4,5,6,7,4};
		int[][] twoDArr = {{2,3,4},
						   {4,3,2},
						   {3,4,5}};	
		
		for(int i=0;i<3;i++)
			for(int j=0; j<3;j++)
				System.out.println(twoDArr[i][j]);
	}
	
//	public static <E> void printArr(E[] arr) {
//		for(int i=0 ; i<arr.length ; i++)
//			System.out.println(arr[i]);
//	}
}


