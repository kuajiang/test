package test;

public class NENE {
    public String vulnerable(String[] cw, String[] ccw){
        byte[] flags = new byte[2048];
        for (int i=0;i<cw.length;i++){
            int start = str2int(cw[i]);
            int end = str2int(ccw[i]);
            System.out.println(""+start+"\t"+end);
            for(int j=start;;j++){
                if(j==2048) j=0;
                flags[j] = 1;
                if(j==end) break;
            }
        }
        for (int i=1;i<flags.length;i++){
            if(flags[i]==0) return int2str(i);
        }
        if(flags[0]==0) return "E";
        return "SAFE";
    }

    private String int2str(int value){
        if(value==512) return "N";
        if(value==1536) return "S";
        StringBuilder sb = new StringBuilder();
        int baseValue = value>>9;
        char base='E';
        char base2='N';
        switch (baseValue){
            case 0:
                base='E';
                base2='N';
                sb.append(base);
                break;
            case 1:
                base='N';
                base2='W';
                value -= 1024;
                sb.append(base2);
                break;
            case 2:
                base='W';
                base2='S';
                value -= 1024;
                sb.append(base);
                break;
            case 3:
                base='S';
                base2='E';
                value -= 2048;
                sb.append(base2);
                break;
            default:
                break;
        }
        for (int i=8;i>=0;i--){
            if(value==0) break;
            if(value>0){
                value -= (1<<i);
                sb.insert(0,base2);
            }else{
                value += (1<<i);
                sb.insert(0,base);
            }
        }

        return sb.toString();
    }

    private int str2int(String cw){
        int value = 0;
        char base = cw.charAt(cw.length()-1);
        if(base=='N') value = 512;
        else if(base =='W') value = 1024;
        else if(base =='S') value = 1536;

        for (int i=1;i<cw.length();i++){
            char code = cw.charAt(cw.length()-1-i);
            int direction = ((base=='E'&&code=='N')||(base=='W'&&code=='S')
                    ||(value<0 && base=='E'&&code=='E')
                    ||(value>512&&value<1024 && base=='W'&&code=='W'))?1:-1;
            value += direction*(512>>i);
//            System.out.println(""+value+"\t"+(512>>i));
        }
        if(value<0) value += 2048;
        return value;
    }


    public static void main(String[] args){
        System.out.println(new NENE().int2str(1025));
        System.out.println(new NENE().int2str(1023));
        System.out.println(new NENE().int2str(2047));
        System.out.println(new NENE().int2str(1));
        System.out.println(new NENE().str2int("WWWWWWWWSW"));
        System.out.println(new NENE().str2int("WWWWWWWWNW"));
        System.out.println(new NENE().str2int("EEEEEEEESE"));
        System.out.println(new NENE().str2int("EEEEEEEENE"));

        NENE n = new NENE();
        System.out.println(n.vulnerable(new String[]{"EEEENE","WNW","SSWWSW"},new String[]{"NNNNENEENE","WWNW","EEENE"}));
    }
}
