package org.example;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static List <String>wordlist = new ArrayList<>();

    private static int count = 0;
    public static void main(String[] args) {

        try {
            String url = "https://random-word-api.vercel.app/api?word";

            String filepath = "D:\\Java\\random.txt";
            File file = new File(filepath);

            boolean newFile = file.createNewFile();


            writeWordsToFile(url, filepath);
            addWordToList(filepath);

            startTyping();
        } catch (Exception ex) {
            ex.printStackTrace();

        }

    }

    public static void writeWordsToFile(String url, String filepath) throws Exception {

        try (FileWriter fw = new FileWriter(filepath,true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            URL wordUrl = new URL(url);
            for (int i = 0; i <15 ; i++) {

                Scanner scanner = new Scanner(wordUrl.openStream());
                String response = scanner.useDelimiter("\\A").next();


                int start = response.indexOf("[\"") + 2;
                int end = response.indexOf("\"]");

                String word = response.substring(start, end);

                bw.write(word);

                bw.newLine();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void addWordToList(String filePath)throws  Exception{
        FileReader fr = new FileReader(filePath);
        BufferedReader br =new BufferedReader(fr);
        wordlist =br.lines().toList();
        System.out.println(wordlist);

    }

    public  static void  startTyping(){

        boolean run = true;

        while (run) {
            Scanner sc = new Scanner(System.in);
            String s = getRandomWord();
            System.out.println(s);
            String client = sc.next();
            if(s.equalsIgnoreCase(client)){
                count++;
                startTyping();
            }else {
                System.out.println(count);
             return;
            }
        }
    }

    private static String  getRandomWord(){
        Random random =new Random();
        int randomNumber = random.nextInt(wordlist.size());
        return  wordlist.get(randomNumber);

    }

}