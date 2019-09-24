package com.etranzact.core.utils;

import java.util.Random;

import static com.etranzact.core.constants.AppConstant.AccountGenerator.BIN;

/**
 * Created by johnadeshola on 9/21/19.
 */
public class AccountNumberGenerator {

    private static Random random = new Random(System.currentTimeMillis());

    public static String generate() {

        int randomNumberLength = 10 - (BIN.length() + 1);

        StringBuilder builder = new StringBuilder(BIN);
        for (int i = 0; i < randomNumberLength; i++) {
            int digit = random.nextInt(10);
            builder.append(digit);
        }

        // Do the Luhn algorithm to generate the check digit.
        int checkDigit = getCheckDigit(builder.toString());
        builder.append(checkDigit);

        return builder.toString();
    }

    private static int getCheckDigit(String number) {

        int sum = 0;
        for (int i = 0; i < number.length(); i++) {

            int digit = Integer.parseInt(number.substring(i, (i + 1)));

            if ((i % 2) == 0) {
                digit = digit * 2;
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10);
                }
            }
            sum += digit;
        }

        int mod = sum % 10;
        return ((mod == 0) ? 0 : 10 - mod);
    }

    public static void main(String[] args) {
        System.out.println(generate());
    }
}
