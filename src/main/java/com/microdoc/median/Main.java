package com.microdoc.median;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please, specify full path to file.");
        } else {

            try {
                System.out.println("Median is: " + getMedian(new File(args[0])));
            } catch (FileNotFoundException e) {
                System.out.println("Can not read the file or file has bad format!");
            }
        }
    }

    private static int getMedian(File file) throws FileNotFoundException {
        if (file.length() <= getTotalMemoryInMb() * 0.75) {
            return getMedian(loadNumbers(file));
        }

        return getMedianForBigFile(file);
    }

    private static long getTotalMemoryInMb() {
        return Runtime.getRuntime().maxMemory();
    }

    private static ArrayList<Integer> loadNumbers(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (scanner.hasNextInt()) {
            arrayList.add(scanner.nextInt());
        }

        return arrayList;
    }

    private static int getMedian(ArrayList<Integer> arrayList) {
        arrayList.sort(Integer::compareTo);
        if (arrayList.size() % 2 == 0) {
            return (arrayList.get(arrayList.size() / 2) + arrayList.get(arrayList.size() / 2 - 1)) / 2;
        } else {
            return arrayList.get(arrayList.size() / 2);
        }
    }

    private static int getMedianForBigFile(File file) throws FileNotFoundException {
        int median = 0;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextInt()) {
            int inputNumber = scanner.nextInt();
            if (inputNumber > median) {
                median++;
            } else if (inputNumber < median) {
                median--;
            }
        }
        return median;
    }
}
