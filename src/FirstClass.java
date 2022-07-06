/** https://github.com/G-HanVV/Test.git */

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstClass {
    public static void main(String[] args) {
        int t = 1;
        Scanner in = new Scanner(System.in);
        for (;t == 1;){
            String str;
            String mainCheckInt = "";
            String mainCheckRome = "";
            String operator = "";
            System.out.print("Выражение: ");
            str = in.nextLine();
            Pattern patternMainInt = Pattern.compile("[0-9]{1,2}[+/*-][0-9]{1,3}");
            Matcher matcherMainInt = patternMainInt.matcher(str);
            Pattern patternMainRome = Pattern.compile("[IVXLDCM]{1,4}[+/*-][IVXLDCM]{1,4}");
            Matcher matcherMainRome = patternMainRome.matcher(str);
            Pattern patternOperator = Pattern.compile("[+/*-]");
            Matcher matcherOperator = patternOperator.matcher(str);
            while (matcherMainInt.find()) {
                mainCheckInt = str.substring(matcherMainInt.start(), matcherMainInt.end());
            }
            while (matcherMainRome.find()) {
                mainCheckRome = str.substring(matcherMainRome.start(), matcherMainRome.end());
            }
            while (matcherOperator.find()) {
                operator = str.substring(matcherOperator.start(), matcherOperator.end());
            }
            if (str == mainCheckInt) {
                int[] nums = getInt(str);
                if (nums[0] > 0 && nums[1] > 0 && nums[0] <= 10 && nums[1] <= 10) {
                    switch (operator) {
                        case "+":
                            System.out.println(nums[0] + nums[1]);
                            break;
                        case "-":
                            System.out.println(nums[0] - nums[1]);
                            break;
                        case "*":
                            System.out.println(nums[0] * nums[1]);
                            break;
                        case "/":
                            System.out.println(nums[0] / nums[1]);
                            break;
                    }
                } else {
                    try {
                        int p = Integer.parseInt("i");
                    } catch (Exception exception) {
                        System.out.println("throws Exception //т.к. ну мы же договаривались от 1 до 10");
                        t = 0;
                    }
                }
            } else if (str == mainCheckRome) {
                int[] nums = getIntFromRome(str);
                if (nums[0] != 0 && nums[1] != 0) {
                    switch (operator) {
                        case "+":
                            System.out.println(getRomeNum(nums[0] + nums[1]));
                            break;
                        case "-":
                            if (nums[0] > nums[1]) {
                                System.out.println(getRomeNum(nums[0] - nums[1]));
                            } else {
                                System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел");
                                t = 0;
                            }
                            break;
                        case "*":
                            System.out.println(getRomeNum(nums[0] * nums[1]));
                            break;
                        case "/":
                            System.out.println(getRomeNum(nums[0] / nums[1]));
                            break;
                    }
                } else {
                    System.out.println("throws Exception //т.к. некорректное значение операндов");
                    t = 0;
                }
            } else {
                Pattern patternEx = Pattern.compile("[IVXLDCM0-9]{1,4}[+/*-][IVXLDCM0-9]{1,4}");
                Matcher matcherEx = patternEx.matcher(str);
                String checkExeption = "";
                while (matcherEx.find()) {
                    checkExeption = str.substring(matcherEx.start(), matcherEx.end());
                }
                if (checkExeption == str) {
                    System.out.println("throws Exception //т.к. используются одновременно разные системы счисления");
                    t = 0;
                } else if (operator == "") {
                    System.out.println("throws Exception //т.к. строка не является математической операцией");
                    t = 0;
                } else {
                    System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                    t = 0;
                }
            }
            if (t == 1) {
                try {
                    System.out.print("1 - еще раз, 0 - закончить: ");
                    t = Integer.parseInt(in.nextLine());
                } catch (Exception exception) {
                    System.out.println("Не хочешь по хорошему? Заканчиваем.");
                    t = 0;
                }
            }
        }
        in.close();
    }
    public static int[] getInt(String desc) {
        int count = 0;
        int[] allMatches = new int[2];
        Matcher m = Pattern.compile("[0-9]{1,2}").matcher(desc);
        while (m.find()) {
            allMatches[count] = Integer.parseInt(m.group());
            count++;
        }
        return allMatches;
    }

    public static int[] getIntFromRome(String desc) {
        int count = 0;
        String[] allMatches = new String[2];
        int[] allMatchNums = new int[2];
        Matcher m = Pattern.compile("[IXVLDCM]{1,4}").matcher(desc);
        while (m.find()) {
            allMatches[count] = m.group();
            count++;
        }
        for (int i = 0; i < 2; i++) {
            switch (allMatches[i]) {
                case "I":
                    allMatchNums[i] = 1;
                    break;
                case "II":
                    allMatchNums[i] = 2;
                    break;
                case "III":
                    allMatchNums[i] = 3;
                    break;
                case "IV":
                    allMatchNums[i] = 4;
                    break;
                case "V":
                    allMatchNums[i] = 5;
                    break;
                case "VI":
                    allMatchNums[i] = 6;
                    break;
                case "VII":
                    allMatchNums[i] = 7;
                    break;
                case "VIII":
                    allMatchNums[i] = 8;
                    break;
                case "IX":
                    allMatchNums[i] = 9;
                    break;
                case "X":
                    allMatchNums[i] = 10;
                    break;
                default:
                    allMatchNums[i] = 0;
                    break;
            }
        }
        return allMatchNums;
    }

    public static String getRomeNum(int num){
        String romeBase = "";
        String romeNumFinal = "";
        String str = Integer.toString(num);
        for (int i = 0; i < str.length(); i++){
            switch (str.substring(i, i+1)){
                case "1":
                    romeBase = "I";
                    break;
                case "2":
                    romeBase = "II";
                    break;
                case "3":
                    romeBase = "III";
                    break;
                case "4":
                    romeBase = "IV";
                    break;
                case "5":
                    romeBase = "V";
                    break;
                case "6":
                    romeBase = "VI";
                    break;
                case "7":
                    romeBase = "VII";
                    break;
                case "8":
                    romeBase = "VIII";
                    break;
                case "9":
                    romeBase = "IX";
                    break;
                case "0":
                    romeBase = "";
                    break;
            }
            switch (str.length() - i){
                case 3:
                    Matcher matcherI3 = Pattern.compile("I").matcher(romeBase);
                    romeBase = matcherI3.replaceAll("C");
                    break;
                case 2:
                    Matcher matcherX2 = Pattern.compile("X").matcher(romeBase);
                    romeBase = matcherX2.replaceAll("C");
                    Matcher matcherV2 = Pattern.compile("V").matcher(romeBase);
                    romeBase = matcherV2.replaceAll("L");
                    Matcher matcherI2 = Pattern.compile("I").matcher(romeBase);
                    romeBase = matcherI2.replaceAll("X");
                    break;
            }
            romeNumFinal = romeNumFinal + romeBase;
        }
        return romeNumFinal;
    }
}