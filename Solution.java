package com.javarush.task.task18.task1827;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Прайсы
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        System.setIn(new ByteArrayInputStream("C:\\Users\\afilt\\Desktop\\1.txt".getBytes()));//для теста, потом удалить

        if (args.length == 0) return;

        List<String> idList = new ArrayList<>();
        String fileName = new BufferedReader(new InputStreamReader(System.in)).readLine();

        if (args[0].equals("-c")) {

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {

                while (reader.ready()) {
                    String currentLine = reader.readLine();
                    StringBuilder builder = new StringBuilder();
                    for (int i = 0; i < 8; i++) {
                        char currentSymbol = currentLine.charAt(i);
                        if (Character.isDigit(currentSymbol)) {
                            builder.append(currentSymbol);
                        }
                        idList.add(builder.toString());
                    }
                }

                int maxID = idList.stream()
                        .map(Integer::parseInt)
                        .max(Integer::compare).orElse(0);

                String id = makeParameter(++maxID + "", 8);
                String productName = makeParameter(args[1], 30);
                String price = makeParameter(args[2], 8);
                String quantity = makeParameter(args[3], 4);
                String result = id + productName + price + quantity;

                writer.write('\n');
                writer.write(result);
            }
        }
    }

    private static String makeParameter(String parameter, int length) {
        parameter = parameter.trim();
        if (parameter.length() < length) {
            StringBuilder builder = new StringBuilder(parameter);
            int spaceCount = length - parameter.length();
            for (int i = 0; i < spaceCount; i++) {
                builder.append(" ");
            }
            parameter = builder.toString();
        } else if (parameter.length() > length) {
            parameter = parameter.substring(0, length);
        }
        return parameter;
    }
}