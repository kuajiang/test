package tco2013round1a;

public class HouseBuilding {
    public int getMinimum(String[] area){
        if(area==null || area.length==0) return 0;
        int height = area.length;
        int width = area[0].length();

        int count =Integer.MAX_VALUE;
        int sum[] = new int[9];
        for (int i=0;i<height;i++){
            for (int j=0;j<width;j++){
                int h = area[i].charAt(j)-'0';
                for (int k=1;k<=9;k++){
                    if(h<(k-1)) sum[k-1] += (k-1-h);
                    else if (h>k) sum[k-1] += (h-k);
                }
            }
        }

        for (int i=0;i<9;i++){
            count = Math.min(count,sum[i]);
        }


        return count;

    }

    public  static void main(String[] args){
        System.out.println(new HouseBuilding().getMinimum(
                new String[]
                        {"5781252",
                         "2471255",
                         "0000291",
                         "1212489"}
        ));
    }
}
