package classifier;

import java.io.*;
import java.util.*;
import java.math.*;

public class Classifier {

    
    private char[] b;
    private int i,
            i_end,
            j, k;
    private static final int INC = 50;

    public Classifier() {
        b = new char[INC];
        i = 0;
        i_end = 0;
    }

    public void add(char ch) {
        if (i == b.length) {
            char[] new_b = new char[i + INC];
            for (int c = 0; c < i; c++) {
                new_b[c] = b[c];
            }
            b = new_b;
        }
        b[i++] = ch;
    }

    public void add(char[] w, int wLen) {
        if (i + wLen >= b.length) {
            char[] new_b = new char[i + wLen + INC];
            for (int c = 0; c < i; c++) {
                new_b[c] = b[c];
            }
            b = new_b;
        }
        for (int c = 0; c < wLen; c++) {
            b[i++] = w[c];
        }
    }

    public String toString() {
        return new String(b, 0, i_end);
    }

    public int getResultLength() {
        return i_end;
    }

    public char[] getResultBuffer() {
        return b;
    }

    private final boolean cons(int i) {
        switch (b[i]) {
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                return false;
            case 'y':
                return (i == 0) ? true : !cons(i - 1);
            default:
                return true;
        }
    }

    private final int m() {
        int n = 0;
        int i = 0;
        while (true) {
            if (i > j) {
                return n;
            }
            if (!cons(i)) {
                break;
            }
            i++;
        }
        i++;
        while (true) {
            while (true) {
                if (i > j) {
                    return n;
                }
                if (cons(i)) {
                    break;
                }
                i++;
            }
            i++;
            n++;
            while (true) {
                if (i > j) {
                    return n;
                }
                if (!cons(i)) {
                    break;
                }
                i++;
            }
            i++;
        }
    }

    private final boolean vowelinstem() {
        int i;
        for (i = 0; i <= j; i++) {
            if (!cons(i)) {
                return true;
            }
        }
        return false;
    }

    private final boolean doublec(int j) {
        if (j < 1) {
            return false;
        }
        if (b[j] != b[j - 1]) {
            return false;
        }
        return cons(j);
    }

    private final boolean cvc(int i) {
        if (i < 2 || !cons(i) || cons(i - 1) || !cons(i - 2)) {
            return false;
        }
        {
            int ch = b[i];
            if (ch == 'w' || ch == 'x' || ch == 'y') {
                return false;
            }
        }
        return true;
    }

    private final boolean ends(String s) {
        int l = s.length();
        int o = k - l + 1;
        if (o < 0) {
            return false;
        }
        for (int i = 0; i < l; i++) {
            if (b[o + i] != s.charAt(i)) {
                return false;
            }
        }
        j = k - l;
        return true;
    }

    private final void setto(String s) {
        int l = s.length();
        int o = j + 1;
        for (int i = 0; i < l; i++) {
            b[o + i] = s.charAt(i);
        }
        k = j + l;
    }

    private final void r(String s) {
        if (m() > 0) {
            setto(s);
        }
    }

    private final void step1() {
        if (b[k] == 's') {
            if (ends("sses")) {
                k -= 2;
            } else if (ends("ies")) {
                setto("i");
            } else if (b[k - 1] != 's') {
                k--;
            }
        }
        if (ends("eed")) {
            if (m() > 0) {
                k--;
            }
        } else if ((ends("ed") || ends("ing")) && vowelinstem()) {
            k = j;
            if (ends("at")) {
                setto("ate");
            } else if (ends("bl")) {
                setto("ble");
            } else if (ends("iz")) {
                setto("ize");
            } else if (doublec(k)) {
                k--;
                {
                    int ch = b[k];
                    if (ch == 'l' || ch == 's' || ch == 'z') {
                        k++;
                    }
                }
            } else if (m() == 1 && cvc(k)) {
                setto("e");
            }
        }
    }

    private final void step2() {
        if (ends("y") && vowelinstem()) {
            b[k] = 'i';
        }
    }

    private final void step3() {
        if (k == 0) {
            return;
        }
        switch (b[k - 1]) {
            case 'a':
                if (ends("ational")) {
                    r("ate");
                    break;
                }
                if (ends("tional")) {
                    r("tion");
                    break;
                }
                break;
            case 'c':
                if (ends("enci")) {
                    r("ence");
                    break;
                }
                if (ends("anci")) {
                    r("ance");
                    break;
                }
                break;
            case 'e':
                if (ends("izer")) {
                    r("ize");
                    break;
                }
                break;
            case 'l':
                if (ends("bli")) {
                    r("ble");
                    break;
                }
                if (ends("alli")) {
                    r("al");
                    break;
                }
                if (ends("entli")) {
                    r("ent");
                    break;
                }
                if (ends("eli")) {
                    r("e");
                    break;
                }
                if (ends("ousli")) {
                    r("ous");
                    break;
                }
                break;
            case 'o':
                if (ends("ization")) {
                    r("ize");
                    break;
                }
                if (ends("ation")) {
                    r("ate");
                    break;
                }
                if (ends("ator")) {
                    r("ate");
                    break;
                }
                break;
            case 's':
                if (ends("alism")) {
                    r("al");
                    break;
                }
                if (ends("iveness")) {
                    r("ive");
                    break;
                }
                if (ends("fulness")) {
                    r("ful");
                    break;
                }
                if (ends("ousness")) {
                    r("ous");
                    break;
                }
                break;
            case 't':
                if (ends("aliti")) {
                    r("al");
                    break;
                }
                if (ends("iviti")) {
                    r("ive");
                    break;
                }
                if (ends("biliti")) {
                    r("ble");
                    break;
                }
                break;
            case 'g':
                if (ends("logi")) {
                    r("log");
                    break;
                }
        }
    }

    private final void step4() {
        switch (b[k]) {
            case 'e':
                if (ends("icate")) {
                    r("ic");
                    break;
                }
                if (ends("ative")) {
                    r("");
                    break;
                }
                if (ends("alize")) {
                    r("al");
                    break;
                }
                break;
            case 'i':
                if (ends("iciti")) {
                    r("ic");
                    break;
                }
                break;
            case 'l':
                if (ends("ical")) {
                    r("ic");
                    break;
                }
                if (ends("ful")) {
                    r("");
                    break;
                }
                break;
            case 's':
                if (ends("ness")) {
                    r("");
                    break;
                }
                break;
        }
    }

    private final void step5() {
        if (k == 0) {
            return;
        }
        switch (b[k - 1]) {
            case 'a':
                if (ends("al")) {
                    break;
                }
                return;
            case 'c':
                if (ends("ance")) {
                    break;
                }
                if (ends("ence")) {
                    break;
                }
                return;
            case 'e':
                if (ends("er")) {
                    break;
                }
                return;
            case 'i':
                if (ends("ic")) {
                    break;
                }
                return;
            case 'l':
                if (ends("able")) {
                    break;
                }
                if (ends("ible")) {
                    break;
                }
                return;
            case 'n':
                if (ends("ant")) {
                    break;
                }
                if (ends("ement")) {
                    break;
                }
                if (ends("ment")) {
                    break;
                }

                if (ends("ent")) {
                    break;
                }
                return;
            case 'o':
                if (ends("ion") && j >= 0 && (b[j] == 's' || b[j] == 't')) {
                    break;
                }

                if (ends("ou")) {
                    break;
                }
                return;

            case 's':
                if (ends("ism")) {
                    break;
                }
                return;
            case 't':
                if (ends("ate")) {
                    break;
                }
                if (ends("iti")) {
                    break;
                }
                return;
            case 'u':
                if (ends("ous")) {
                    break;
                }
                return;
            case 'v':
                if (ends("ive")) {
                    break;
                }
                return;
            case 'z':
                if (ends("ize")) {
                    break;
                }
                return;
            default:
                return;
        }
        if (m() > 1) {
            k = j;
        }
    }

    private final void step6() {
        j = k;
        if (b[k] == 'e') {
            int a = m();
            if (a > 1 || a == 1 && !cvc(k - 1)) {
                k--;
            }
        }
        if (b[k] == 'l' && doublec(k) && m() > 1) {
            k--;
        }
    }

    private final void step7() {
        if (ends("y")) {
            b[k] = 'i';
        }
    }

    public void stem() {
        k = i - 1;
        if (k > 1) {
            step1();
            step2();
            step3();
            step4();
            step5();
            step6();
            step7();
        }
        i_end = k + 1;
        i = 0;
    }

    public static ArrayList<String> wordsList = new ArrayList<>();
    public static ArrayList<String> tempList = new ArrayList<>();
    public static ArrayList<String> wList = new ArrayList<>();


    public boolean test(File f)throws FileNotFoundException, IOException{
         HashMap<String, Double> hmh = null,hms=null;
   
        Iterator iv;
        double spam,ham;
       BigDecimal probv=new BigDecimal("1.00");
       probv.setScale(200);
       BigDecimal probs= new BigDecimal("1.00");
        probs.setScale(200);
       BigDecimal probh= new BigDecimal("1.00");
        probh.setScale(200);
        spam=ham=0;
       // probv = probs = probh = 1;
        try {
          ObjectInputStream oish = new ObjectInputStream(new FileInputStream("A://algo project//laststep//processing files//hamtest.txt"));
            hmh = (HashMap<String, Double>) oish.readObject();
            oish.close();
            ObjectInputStream oiss = new ObjectInputStream(new FileInputStream("A://algo project//laststep//processing files//spamtest.txt"));
            hms = (HashMap<String, Double>) oiss.readObject();
            oiss.close();
        } catch (Exception eex) {
        }
       

        /*------------------------------ VBS-----------------------------------------------------*/
        String str1 = "";

        String str2 = "";
        HashSet<String> stemp;
        try (Scanner inFile2 = new Scanner(new File("A://algo project//laststep//processing files//dictionary.txt")).useDelimiter(" \\s*")) {
            stemp = new HashSet<>();
            while (inFile2.hasNext()) {

                str1 = inFile2.next();
                stemp.add(str1);
            }
        }

        String[] stopWordsofwordnet = stemp.toArray(new String[0]);

        String token1 = "";
        //String str11 = "";
        HashSet<String> temps = null;

        try {
            String vv="";
            BufferedReader reader;
            BufferedWriter writer;
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
           
            
            temps = new HashSet<>();
            while ((token1 = reader.readLine()) != null) {
                for (String ret : token1.split(" ")) {

                    if (!stemp.contains(ret)) {
                        temps.add(ret);
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
        }

        String[] tempsArray = temps.toArray(new String[0]);
        wordsList.addAll(Arrays.asList(tempsArray));

        /* for(String str: wList)
         System.out.println(str);*/

        /*for (int i = 0; i < wList.size(); i++) {
         for (String stopWordsofwordnet1 : stopWordsofwordnet) {
         if (stopWordsofwordnet1.contains(wList.get(i))) {
         wList.remove(i);
         }
         }
         }*/

        /*for(String s : wList)
         System.out.println(s);*/
        try {
            File file = new File("A://algo project//laststep//processing files//auxillary.txt");
            try (FileWriter fileWriter = new FileWriter(file)) {
                for (String str : wordsList) {
                    fileWriter.write(str);
                    fileWriter.write(" ");
                    fileWriter.flush();
                }
            }
        } catch (IOException e) {
        }
        char[] w = new char[501];
        String p = " ";
       // Classifier s = new Classifier();

        try {

            FileInputStream in = new FileInputStream("A://algo project//laststep//processing files//auxillary.txt");

            try {
                while (true) {
                    int ch = in.read();
                    if ((char) ch != ' ') {
                        if (Character.isLetter((char) ch)) {
                            int j = 0;
                            while (true) {
                                ch = Character.toLowerCase((char) ch);
                                w[j] = (char) ch;
                                if (j < 500) {
                                    j++;
                                }
                                ch = in.read();
                                if (!Character.isLetter((char) ch)) {
                                    for (int c = 0; c < j; c++) {
                                        add(w[c]);
                                    }

                                    stem();
                                    {
                                        String u;

                                        u = toString();
                                        if (u.length() != 1) {
                                            if (!tempList.contains(u)) {
                                                tempList.add(u);
                                            }
                                        }

                                    }

                                    break;
                                }
                            }
                        }

                        if (ch < 0) {
                            break;
                        }
                    }
                }
                
            } catch (IOException e) {
                System.out.println("error reading file");

            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");

        }

        for (String stopWordsofwordnet1 : stopWordsofwordnet) {
            for (int i = 0; i < tempList.size(); i++) {
                if (stopWordsofwordnet1.contains(tempList.get(i))) {
                    tempList.remove(i);
                }
            }
        }
        for (String str : tempList) {
                if(hmh.containsKey(str))
                    ham = hmh.get(str);
                else
                    ham = 0.001428571;
                if(hms.containsKey(str))
                    spam = hms.get(str);
                else
                    spam = 0.0017;
                
                BigDecimal ssv = new BigDecimal(spam);
                ssv.setScale(200);
                BigDecimal hhv = new BigDecimal(ham);
                hhv.setScale(200);
                probs =probs.multiply(ssv);
               // BigDecimal hprobs=probs.setScale (200);     
                probh =probh.multiply(hhv);
                //BigDecimal hprobh= hprobh.setScale (200);   
        }
        System.out.println(probs);
        System.out.println(probh);
probv = probs.divide(probh,200,RoundingMode.CEILING);
//BigDecimal hprobv= hprobv.setScale (200);  
System.out.println(probv);
     if(probv.compareTo(BigDecimal.ONE) >= 0){
            System.out.println("SPAM");
            return true;
       }
        else{
            System.out.println("HAM");
            return false;
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        new Classifier();
    }

}
