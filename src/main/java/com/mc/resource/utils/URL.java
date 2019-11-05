package com.mc.resource.utils;

import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (Exception e) {
			return "";
		}
	}

	public static List<Integer> decodeIntList(String s) {
//		String[] vet = s.split(",");
//		List<Integer> list = new ArrayList<Integer>();
//		for (int i = 0; i < vet.length; i++) {
//			list.add(Integer.parseInt(vet[i]));
//		}
//		return list;
		return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
	}
}
