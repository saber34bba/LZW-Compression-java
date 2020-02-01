package lzw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Bidi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeSet;

public class Lzw {

    Node nd;

    private String currunt, nextstring;
    private boolean concat_exist = false;
    //static LinkedHashMap<String, Integer> output;
    //   static ArrayList<String, Integer> output;

    static LinkedHashMap<String, Integer> Dico;
    String concat;

    public static void main(String[] args) throws IOException {
        Lzw lzw = new Lzw();
        // output = new LinkedHashMap<>();
        Dico = new LinkedHashMap<>();
        lzw.readFile();
        // lzw.printall();
    }

    private void readFile() throws FileNotFoundException, IOException {
        String st;
        nd = new Node();
        File file = new File("C:\\test2.txt");
        int newline=0;
        BufferedReader br = new BufferedReader(new FileReader(file));

        while ((st = br.readLine()) != null) {
            newline=0;
            
            for (int i = 0; i < st.length(); i++) {
                                    newline=1;
                if (!concat_exist) {
                    currunt = String.valueOf(st.charAt(i));
                }
                if (i != st.length() - 1) {
                    nextstring = String.valueOf(st.charAt(i + newline));
                    concat = currunt + nextstring;
                    if(newline==0)
                        currunt=concat;
                    if (!Dico.containsKey(concat)) {
                        Dico.put(concat, Dico.size() + 256);
                        if (concat_exist) {
                                      
                            add_to_list(nd, currunt, Dico.get(currunt));

                        } else {

                            add_to_list(nd, currunt, (int) currunt.charAt(0));

                        }

                        concat_exist = false;

                    } else {
                        currunt = concat;
                        concat_exist = true;
                    }
                    newline=1;

                }/* else {

                    if (!concat_exist) {
                        add_to_list(nd, currunt, (int) currunt.charAt(0));
                    } else {
                         concat_exist = false;
                        add_to_list(nd, currunt, (int) Dico.get(currunt));
                    }

                }*/

            }
            

        }
         if (!concat_exist /*&& currunt!=null*/) {
                        add_to_list(nd, currunt, (int) currunt.charAt(0));
                    } else {
                         concat_exist = false;
                        add_to_list(nd, currunt, (int) Dico.get(currunt));
                    }
         
        print_all_dic();

    }

    private void print_all_dic() {
        recursive(nd.next);
    }
    String s;
    int asci;

    void recursive(Node nd) {
        if (nd != null) {
            // s=nd.value;
            //asci=nd.asci;
            System.out.println(nd.value + " " + nd.asci);
            recursive(nd.next);
       
        }
    }
    public void add_to_list(Node node, String code, int asci) {
        if (node.next != null) {
            add_to_list(node.next, code, asci);
        } else {
            node.next = new Node(code, asci);
        }

    }

    static class Node {

        Node next;
        String value;
        int asci;

        public Node(String value, int asci) {
            this.value = value;
            this.asci = asci;
        }

        public Node() {

        }

    }

}
