package com.koreaIT.BAM.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Util {
	public static String getDateStr() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return now.format(formatter);
	}
}


//Scanner sc = new Scanner(System.in);
//
//
//System.out.print("가로 길이를 입력하세요: ");
//int width = sc.nextInt();
//System.out.print("세로 길이를 입력하세요: ");
//int height = sc.nextInt();
//
//for (int i = 0; i < height; i++) {  
//    for (int j = 0; j < width; j++) {  
//        System.out.print("* "); 
//    }
//    System.out.println(); 
//}
//
//
//
//
