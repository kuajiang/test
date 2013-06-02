package test;

public class Pareto{
    public int optima(String[] policy){
        if(policy==null || policy.length==0) return 0;

        int count = 0;
        int row = policy.length;
        int column = policy[0].split(" ").length;

        int flags[] = new int[row];
        for(int i=0;i<row;i++){
            flags[i] = 1;
        }

        int[][] data = new int[row][column];
        for(int i=0;i<row;i++){
            String[] feelings = policy[i].split(" ");
            for(int j=0;j<feelings.length;j++){
                if("awful".equals(feelings[j])) data[i][j]=0;
                else if("bad".equals(feelings[j])) data[i][j]=1;
                else if("fair".equals(feelings[j])) data[i][j]=2;
                else if("fairly-happy".equals(feelings[j])) data[i][j]=3;
                else if("happy".equals(feelings[j])) data[i][j]=4;
                else if("ecstatic".equals(feelings[j])) data[i][j]=5;
            }
            if(i>0){
                for(int j=0;j<i;j++){
                    int flag = compare(data[j],data[i]);
                    if(flag==1) flags[i]=0;
                    if(flag==-1) flags[j]=0;
                }
            }
        }
        for(int i=0;i<row;i++){
            count += flags[i];
        }
        return count;
    }

    private int compare(int[] a,int[] b){
        if(a==null || b==null || a.length!=b.length) return 0;
        int flag = 0;
        for(int i=0;i<a.length;i++){
            int newFlag = a[i]==b[i]?0:(a[i]>b[i]?1:-1);
            if(newFlag!=0){
                if(newFlag!=flag && flag!=0) return 0;
                else flag = newFlag;
            }
        }
        return flag;
    }

    public static void main(String[] args){
        Pareto pareto = new Pareto();
        System.out.println(pareto.optima(new String[]{"bad bad fairly-happy awful", "bad bad bad awful", "ecstatic awful ecstatic ecstatic"}));
    }
}