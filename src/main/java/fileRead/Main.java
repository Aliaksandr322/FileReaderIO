package fileRead;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File file = new File("test.txt");
        String text = "text.txt";
        File textFile = new File(text);
        File numFile = new File("num_file.txt");

        if (!file.exists() || !textFile.exists() || !numFile.exists()){
            try {
                file.createNewFile();
                textFile.createNewFile();
                numFile.createNewFile();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (Writer writer = new BufferedWriter(new FileWriter(file, false))){
            String data = readFile(text);
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        upDateFile(file.getPath(), "Hello world!!", 5);
        System.out.println(readFile(String.valueOf(file)));
        numFile("num_file.txt" , 10);
        sortFile("num_file.txt");
        repeatingWord("text.txt","Java");

    }

    public static void numFile(String path, int num) {
        int array[] = new int[num];
        String strArray[] = new String[10];
        for (int i = 0; i < array.length; i++){
            array[i] = (int)(Math.random()*10 + 1);
            strArray[i] = String.valueOf(array[i]);
        }
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(strArray));
        try (Writer writer = new BufferedWriter(new FileWriter(path))){

            for (int i =0; i < array.length ; i++){
                writer.write(strArray[i] + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sortFile(String path){
        try (BufferedReader reader = new BufferedReader(new FileReader(path))){
            String line;
            List<Integer> stringList= new ArrayList<>();

            while ((line = reader.readLine()) != null){
                stringList.add(Integer.parseInt(line));
            }
            Collections.sort(stringList);
            System.out.println(stringList);
            Writer writer = new BufferedWriter(new FileWriter(path));
            for(Integer str : stringList){
                writer.write(str + "\n");
            }
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readFile(String filePath){
        StringBuilder sb = new StringBuilder();
        try (Reader reader = new BufferedReader(new FileReader(filePath))){
            int characterCounter;
            while ((characterCounter = reader.read()) != -1){
                sb.append((char) characterCounter);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    public static void upDateFile(String path, String textForUpdate, int num){
        File file = new File(path);
        try (Writer writer = new BufferedWriter(new FileWriter(file, true))){
            for (int i = 0; i < num ;i++){
                writer.write(textForUpdate + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void repeatingWord(String path , String search){
        search = search.toLowerCase();
        String resultFormat ="\"%s\" : %d";
        String text = readFile(path).toLowerCase();
        int counter = 0;

        if (text.contains(search)){
            int index;

            while ((index = text.indexOf(search)) != -1){
                counter++;
                text =text.substring(index + search.length());
            }
        }
        System.out.println(String.format(resultFormat,search,counter));
    }

    public static void reversText(String path){

    }



}
