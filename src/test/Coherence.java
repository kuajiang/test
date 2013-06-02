package test;

public class Coherence {
    public int minBndry(int numRows, int numCols, int k){
        if(numRows<1||numCols<1||k>numCols*numRows || k==0) return 0;
        int width = Math.min(numCols,numRows);
        int height = Math.max(numCols,numRows);
        int count = 0;

        int areaHeight = 0;
        int area = areaHeight*Math.min(areaHeight,width);
        int x,y;
        for (int i=1;i<=k;i++){
            if(areaHeight<width){
                int value = i-area;
                x= value>areaHeight?value-areaHeight:areaHeight+1;
                y = value>areaHeight?areaHeight+1:value;
            }else{
                x = i%width;
                y = i/width+1;
            }
            if(x==1) count++;
            if(y==1) count++;
            if(x==width) count--;
            if(x==Math.min(width,areaHeight+1) && y==(areaHeight+1)){
                areaHeight++;
                area = areaHeight*areaHeight;
            }
            System.out.println(""+i+"("+x+","+y+")\t"+count);
        }

        return count;
    }
}
