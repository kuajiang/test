package srm579;

import java.util.HashSet;
import java.util.Set;

public class UndoHistory {
    public int minPresses(String[] lines){
        int count = 0;
        StringBuffer buffer = new StringBuffer();
        Set<String> undos = new HashSet<String>();
        undos.add("");
        int bufferIndex = 0;

        for (int i=0;i<lines.length;i++){
            String line = lines[i];
            if(i>0){
                if(line.startsWith(buffer.toString())){
                    bufferIndex = buffer.length();
                }else{
                    for (int j=line.length();j>=0;j--){
                        if(undos.contains(line.substring(0,j))){
                            buffer.delete(0,buffer.length());
                            buffer.append(line.substring(0,j));
                            bufferIndex = j;
                            count+=2;
                            break;
                        }
                    }
                }
            }
            for(int j=bufferIndex;j<line.length();j++){
                buffer.append(line.charAt(j));
                undos.add(buffer.toString());
                count +=1;
            }

            count+=1;
            System.out.println(count);
        }

        return count;
    }

    public static void main(String[] args){
        UndoHistory uh = new UndoHistory();
        uh.minPresses(new String[]{"pyramid", "sphinx", "sphere", "python", "serpent"});
        uh.minPresses(new String[]{"ba","a","a","b","ba"});
    }
}
