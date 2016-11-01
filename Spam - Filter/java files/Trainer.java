package newclass;

import java.io.*;
import java.util.*;

public class NewClass {

    static double counter;
    static String sv;
    private char[] b;
    private int i,
            i_end,
            j, k;
    private static final int INC = 50;

    public NewClass() {
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

    public static void main(String[] args) throws FileNotFoundException, IOException {
        HashMap<String, Double> hmv = null;
        HashSet<String> hsv = new HashSet<String>();
        Iterator iv;
        double pv, kv;

        try {
            DataInputStream countRead = new DataInputStream(new BufferedInputStream(new FileInputStream("C:\\Users\\Aatish Bansal\\Documents\\NetBeansProjects\\NewClass\\src\\newclass\\Counter.txt")));
            counter = countRead.readDouble();
            countRead.close();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:\\Users\\Aatish Bansal\\Documents\\NetBeansProjects\\NewClass\\src\\newclass\\test.txt"));
            hmv = (HashMap<String, Double>) ois.readObject();
            ois.close();
        } catch (Exception eex) {
        }
        counter++;

        System.out.println(counter);

        /*------------------------------ VBS-----------------------------------------------------*/
        String str1 = "";

        String str2 = "";
        HashSet<String> stemp;
        try (Scanner inFile2 = new Scanner(new File("C:\\Users\\Aatish Bansal\\Documents\\NetBeansProjects\\NewClass\\src\\newclass\\dictionary.txt")).useDelimiter(" \\s*")) {
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

            BufferedReader reader;
            reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\Aatish Bansal\\Documents\\NetBeansProjects\\NewClass\\src\\newclass\\input.txt")));

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
            File file = new File("C:\\Users\\Aatish Bansal\\Documents\\NetBeansProjects\\NewClass\\src\\newclass\\auxillary.txt");
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
        NewClass s = new NewClass();

        try {
            FileInputStream in = new FileInputStream("C:\\Users\\Aatish Bansal\\Documents\\NetBeansProjects\\NewClass\\src\\newclass\\auxillary.txt");

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
                                        s.add(w[c]);
                                    }

                                    s.stem();
                                    {
                                        String u;

                                        u = s.toString();
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
            sv = str;
            if (hmv.containsKey(sv)) {
                kv = (double) hmv.get(sv);
                pv = ((kv * (counter - 1)) + 1) / counter;
                hmv.remove(sv);
            } else {
                pv = 1 / counter;
            }
            hmv.put(sv, pv);
            hsv.add(sv);

        }

        for (Map.Entry mv : hmv.entrySet()) {
            String qv = (String) mv.getKey();
            if (hsv.contains(qv)) {
                continue;
            }
            kv = (double) mv.getValue();
            pv = (kv * (counter - 1)) / counter;
            hmv.remove(mv);
            hmv.put(qv, pv);
        }

        try {
            DataOutputStream countWrite = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("C:\\Users\\Aatish Bansal\\Documents\\NetBeansProjects\\NewClass\\src\\newclass\\Counter.txt")));
            countWrite.writeDouble(counter);
            countWrite.close();
        } catch (Exception eex) {
        }

        Set setv = hmv.entrySet();
        HashMap<String, Double> hm2 = new HashMap<String, Double>();
        try {
            FileOutputStream outv = new FileOutputStream("C:\\Users\\Aatish Bansal\\Documents\\NetBeansProjects\\NewClass\\src\\newclass\\test.txt");
            ObjectOutputStream ooutv = new ObjectOutputStream(outv);
            ooutv.writeObject(hmv);

            ooutv.close();
            FileInputStream fv = new FileInputStream("C:\\Users\\Aatish Bansal\\Documents\\NetBeansProjects\\NewClass\\src\\newclass\\test.txt");
            ObjectInputStream finv = new ObjectInputStream(fv);
            hm2 = (HashMap<String, Double>) finv.readObject();
            finv.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        setv = hm2.entrySet();
        iv = setv.iterator();
        while (iv.hasNext()) {
            Map.Entry mev = (Map.Entry) iv.next();
            System.out.print(mev.getKey() + ": ");
            System.out.println(mev.getValue());
        }
    }

}
