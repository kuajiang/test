package srm580;

public class ShoutterDiv1 {
    public int count(String[] s1000, String[] s100, String[] s10, String[] s1,
                     String[] t1000, String[] t100, String[] t10, String[] t1) {
        int count = 0;

        int length = 0;
        for (String s : s1000) {
            length += s.length();
        }

        int[][] matrix = new int[length][length];
        int[] s = new int[length];
        int[] t = new int[length];

        int index = 0;
        for (int i = 0; i < s1000.length; i++) {
            for (int j = 0; j < s1000[i].length(); j++) {
                s[index] = 1000 * (s1000[i].charAt(j) - '0') + 100 * (s100[i].charAt(j) - '0')
                        + 10 * (s10[i].charAt(j) - '0') + (s1[i].charAt(j) - '0');
                t[index] = 1000 * (t1000[i].charAt(j) - '0') + 100 * (t100[i].charAt(j) - '0')
                        + 10 * (t10[i].charAt(j) - '0') + (t1[i].charAt(j) - '0');
                index++;
            }
        }

        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (s[i] > s[j] || (s[i] == s[j] && t[i] > t[j])) {
                    int tmp = s[i];
                    s[i] = s[j];
                    s[j] = tmp;
                    tmp = t[i];
                    t[i] = t[j];
                    t[j] = tmp;
                }
            }
        }
        for (int i = 0; i < length; i++) {
            System.out.println("" + i + "\t[" + s[i] + ",\t" + t[i] + "]");
        }

        int[] firstEnter = new int[length];
        int[] lastLeave = new int[length];
        firstEnter[0] = -1;
        for (int i=1;i<length;i++){
            boolean firstEnterSetted = false;
            for (int j=0;j<i;j++){
                if(t[j]>=s[i]){
                    if(!firstEnterSetted){
                        firstEnter[i] = j;
                        firstEnterSetted = true;
                    }
                    if(t[j]>t[lastLeave[i]]){
                        lastLeave[i] = j;
                    }
                }
            }
            if(!firstEnterSetted) return -1;
        }
        for (int i = 2; i < length; i++) {
            System.out.println("check:"+i);
            int reposter = firstEnter[i];
            while (reposter>-1){
                if(matrix[reposter][i]==0){
                    matrix[reposter][i] = 1;
                    count++;
                    System.out.println("new:" + reposter + "\trepost\t" + i);
                }
                reposter = firstEnter[reposter];
            }
            reposter = lastLeave[i];
            for(int j=0;j<i;j++){
                if(t[j]<s[i] && matrix[reposter][j]==0){
                    count++;
                    matrix[reposter][j] = 1;
                    System.out.println("old:" + reposter + "\trepost\t" + j);
                }
            }
        }


        return count;
    }

    public static void main(String[] args) {
        ShoutterDiv1 shoutterDiv1 = new ShoutterDiv1();
//        shoutterDiv1.test0();
//        shoutterDiv1.test1();
//        shoutterDiv1.test2();
//        shoutterDiv1.test3();
        shoutterDiv1.test4();
    }

    public void test0() {
        System.out.println(this.count(
                new String[]{"22", "2"},
                new String[]{"00", "0"},
                new String[]{"11", "1"},
                new String[]{"21", "4"},
                new String[]{"22", "2"},
                new String[]{"00", "0"},
                new String[]{"11", "1"},
                new String[]{"43", "6"}
        ));
    }

    public void test1() {
        System.out.println(this.count(
                new String[]{"00"},
                new String[]
                        {"00"},
                new String[]
                        {"00"},
                new String[]
                        {"13"},
                new String[]
                        {"00"},
                new String[]
                        {"00"},
                new String[]
                        {"00"},
                new String[]
                        {"24"}
        ));
    }

    public void test2() {
        System.out.println(this.count(
                new String[]{"0000"},
                new String[]
                        {"0000"},
                new String[]
                        {"0000"},
                new String[]
                        {"1234"},
                new String[]
                        {"0000"},
                new String[]
                        {"0000"},
                new String[]
                        {"0000"},
                new String[]
                        {"2345"}
        ));
    }

    public void test3() {
        System.out.println(this.count(
                new String[]{"0000000000"},
                new String[]
                        {"0000000000"},
                new String[]
                        {"0000000000"},
                new String[]
                        {"7626463146"},
                new String[]
                        {"0000000000"},
                new String[]
                        {"0000000000"},
                new String[]
                        {"0000000000"},
                new String[]
                        {"9927686479"}
        ));
    }

    public void test4() {
        System.out.println(this.count(
                new String[]{"00000000000000000000000000000000000000000000000000"},
                                new String[]
                {"00000000000000000000000000000000000000000000000000"},
                                new String[]
                {"50353624751857130208544645495168271486083954769538"},
                                new String[]
                {"85748487990028258641117783760944852941545064635928"},
                                new String[]
                {"00000000000000000000000000000000000000000000000000"},
                                new String[]
                {"00000000000000000000000000000000000000000000000000"},
                                new String[]
                {"61465744851859252308555855596388482696094965779649"},
                                new String[]
                {"37620749792666153778227385275518278477865684777411"}
        ));
    }
}
