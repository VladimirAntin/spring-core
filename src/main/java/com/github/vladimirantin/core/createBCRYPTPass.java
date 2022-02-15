package com.github.vladimirantin.core;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Scanner;

public class createBCRYPTPass {

    // https://bcrypt-generator.com/
    public static void main(String[] args){
        System.out.print("enter password: ");
        Scanner in = new Scanner(System.in);
        String arg = in.nextLine();

        System.out.format("\n%s: '%-4s'",arg,passwordEncoder(arg));
    }

    public static String passwordEncoder(String enc){
        return new BCryptPasswordEncoder().encode(enc);
    }
}
