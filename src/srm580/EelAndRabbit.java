package srm580;

public class EelAndRabbit {
    public int getmax(int[] l, int[] t){
        int count = 0;

        for (int i=0;i<l.length;i++){
            int t1 = t[i];
            for(int j=0;j<l.length;j++){
                int t2 = t[j];
                int newCount = 0;
                for(int k=0;k<l.length;k++){
                    if((t1-t[k])*(t1-t[k]-l[k])<=0||(t2-t[k])*(t2-t[k]-l[k])<=0){
                        newCount++;
                    }
                }
                count = Math.max(count,newCount);
            }
        }
        return count;
    }

    public static void main(String[] args){
        System.out.println(new EelAndRabbit().getmax(
                new int[]{2, 4, 3, 2, 2, 1, 10},
                new int[]{2, 6, 3, 7, 0, 2, 0}
        ));
        System.out.println(new EelAndRabbit().getmax(
                new int[]{1, 1, 1},
                new int[]{2, 0, 4}
        ));
        System.out.println(new EelAndRabbit().getmax(
            new int[]{8, 2, 1, 10, 8, 6, 3, 1, 2, 5},
            new int[]{17, 27, 26, 11, 1, 27, 23, 12, 11, 13}
        ));
    }
}
