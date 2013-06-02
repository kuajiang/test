package srm578;

public class DeerInZooDivTwo {
	public int[] getminmax(int N, int K){
		return new int[]{Math.max(0,N-K),N-(K>>1)-K%2};
	}
}
