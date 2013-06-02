package srm579;

import java.util.Arrays;
import java.util.Scanner;

public class TravellingPurchasingMan {
    public final int MAX = 1000000;
    public int maxStores(int n, String[] interestingStores, String[] roads){
        int m = interestingStores.length;
        int[] opens = new int[m];
        int[] closes = new int[m];
        int[] durations = new int[m];
        int[][] d = new int[n][n];
        int[][] t = new int[1<<m][m];
        int count = 0;

        for (int i=0;i<m;i++){
            Scanner scanner = new Scanner(interestingStores[i]);
            opens[i] = scanner.nextInt();
            closes[i] = scanner.nextInt();
            durations[i] = scanner.nextInt();
        }
        for(int[] dRow:d){
            Arrays.fill(dRow,MAX);
        }
        for (int i=0;i<n;i++){
            d[i][i] = 0;
        }
        for (int i=0;i<roads.length;i++){
            Scanner scanner = new Scanner(roads[i]);
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            int distance = scanner.nextInt();
            d[start][end] = d[end][start] = distance;
        }
        for (int i=0;i<n;i++)
            for (int j=0;j<n;j++)
                for (int k=0;k<n;k++){
                    d[k][j] = d[j][k] = Math.min(d[j][k],d[j][i]+d[i][k]);
                }

        for(int[] tRow:t){
            Arrays.fill(tRow,MAX);
        }
        for (int i=0;i<m;i++){
            if(d[n-1][i]<=closes[i]){
                t[1<<i][i] = Math.max(opens[i],d[n-1][i])+durations[i];
            }
        }
        for(int i=0;i<1<<m;i++){
            for (int j=0;j<m;j++){
                if(t[i][j]<MAX){
                    count = Math.max(count,Integer.bitCount(i));
                    for(int k=0;k<m;k++){
                        if((i>>k&1)==0){
                            if(closes[k]>=t[i][j]+d[j][k]){
                                t[1<<k|i][k] = Math.min(t[1<<k|i][k],Math.max(opens[k],t[i][j]+d[j][k])+durations[k]);
                            }
                        }
                    }
                }
            }
        }

        return count;
    }

    public static void main(String[] args){
        System.out.println(new TravellingPurchasingMan().maxStores(5,
        new String[]{"0 1000 17"},
        new String[]{"2 3 400", "4 1 500", "4 3 300", "1 0 700", "0 2 400"}
        ));
    }
}
