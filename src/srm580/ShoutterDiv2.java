package srm580;

public class ShoutterDiv2 {
    public int count(int[] s, int[] t){
        int count = 0;
        if(s.length==1) return 0;
        for (int i=1;i<s.length;i++){
            for (int j=0;j<i;j++){
                if((s[j]<=t[i] && t[j]>=s[i])||(s[i]<=t[j] && t[i]>=s[j])) count++;
            }
        }
        return count;
    }

    public static void main(String[] args){
        ShoutterDiv2 sd = new ShoutterDiv2();
        System.out.println(sd.count(new int[]{9,26,8,35,3,58,91,24,10,26,22,18,15,12,15,27,15,60,76,19,12,16,37,35,25,4,22,47,65,3,2,23,26,33,7,11,34,74,67,32,15,45,20,53,60,25,74,13,44,51},
                new int[]{26,62,80,80,52,83,100,71,20,73,23,32,80,37,34,55,51,86,97,89,17,81,74,94,79,85,77,97,87,8,70,46,58,70,97,35,80,76,82,80,19,56,65,62,80,49,79,28,75,78}));
    }
}
