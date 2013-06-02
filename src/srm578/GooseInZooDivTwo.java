package srm578;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GooseInZooDivTwo {
    private  int[][] grid = new int[50][50];
    private  int width;
    private  int height;
    private String[] field;
    private int dist;


    private  void dfs(int x,int y){
        if(dist<0) return;
        grid[y][x] = 1;
        for (int i=-dist;i<=dist;i++){
            for(int j=Math.abs(i)-dist;j<=dist-Math.abs(i);j++){
                int newx = x+i;
                int newy = y+j;
                if(newx>=0&&newx<width&&newy>=0&&newy<height&&field[newy].charAt(newx)=='v'&&grid[newy][newx]==0){
                    dfs(newx,newy);
                }
            }
        }
    }

    public int count(String[] field,int dist){
        this.field = field;
        this.dist = dist;
        if (field == null || field.length == 0||dist<0) return 0;

        height = field.length;
        width = field[0].length();
        for (int[] row:grid){
            Arrays.fill(row,0);
        }
        int count = 0;
        for (int i=0;i<height;i++){
            for (int j=0;j<width;j++){
                if(field[i].charAt(j)!='v'||grid[i][j]==1) continue;
                count++;
                dfs(j,i);
            }
        }

        int mod = 1;
        for (int i=1;i<=count;i++){
            mod = (mod<<1)%1000000007;
        }
        mod = (mod-1+1000000007)%1000000007;
        return mod;
    }
}
