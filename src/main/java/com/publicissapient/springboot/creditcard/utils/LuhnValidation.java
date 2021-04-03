package com.publicissapient.springboot.creditcard.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LuhnValidation
{
    public static boolean Check(String ccNumber)
    {
        int sum = 0;
        boolean alternate = false;
        for (int i = ccNumber.length() - 1; i >= 0; i--)
        {
            int n = Integer.parseInt(ccNumber.substring(i, i + 1));
            if (alternate)
            {
                n *= 2;
                if (n > 9)
                {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        log.info("--luhn check");
        log.info(String.valueOf(sum%10));


        System.out.print("--luhn check");
        System.out.print(String.valueOf(sum%10));
        return (sum % 10 == 0);
    }
}