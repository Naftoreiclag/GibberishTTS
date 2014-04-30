/* Copyright (c) 2014 "Naftoreiclag" https://github.com/Naftoreiclag
 *
 * Distributed under the BSD 2-Clause License (http://opensource.org/licenses/BSD-2-Clause)
 * See accompanying file LICENSE
 */

package naftoreiclag.gibberish;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TranslationDict
{
	public static Map<String, String> data = new HashMap<String, String>();
	
	public static void load() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("resources/gibberish/cmudict.0.7a"));
		String line;
		while((line = br.readLine()) != null)
		{
			if(line.startsWith(";;;"))
			{
				continue;
			}
			
			String[] info = line.split(" ");
			
			if(info.length < 3)
			{
				System.err.println(line);
				continue;
			}

			StringBuilder wordBuild = new StringBuilder();
			StringBuilder transBuild = new StringBuilder();
			
			boolean makingWord = true;
			for(int i = 0; i < info.length; ++ i)
			{
				if(info[i].equals(""))
				{
					wordBuild.deleteCharAt(wordBuild.length() - 1);
					makingWord = false;
					continue;
				}
				
				if(makingWord)
				{
					wordBuild.append(info[i]);
					wordBuild.append(" ");
				}
				else
				{
					transBuild.append(info[i]);
					transBuild.append(" ");
				}
			}
			transBuild.deleteCharAt(transBuild.length() - 1);
			
			String word = wordBuild.toString().toLowerCase();
			String trans = noNums(transBuild.toString().toLowerCase());
			
			data.put(word, trans);
			
			System.out.println(word + ":" + trans);
		}
		
		br.close();
	}
	
	public static String translate(String a)
	{
		StringBuilder builder = new StringBuilder();
		a += " ";
		char[] chars = a.toLowerCase().toCharArray();
		String currentBuild = "";
		for(char c : chars)
		{
			if(c == '.' || c == ' ')
			{
				if(data.containsKey(currentBuild))
				{
					builder.append(data.get(currentBuild));
				}
				builder.append(" . ");
				currentBuild = "";
				continue;
			}
			else
			{
				currentBuild += c;
			}
			/*
			if(data.containsKey(currentBuild))
			{
				builder.append(data.get(currentBuild));
				currentBuild = "";
				continue;
			}*/
		}
		
		return builder.toString();
	}
	
	public static String noNums(String a)
	{
		return a.replaceAll("0", "").replaceAll("1", "").replaceAll("2", "").replaceAll("3", "").replaceAll("4", "").replaceAll("5", "").replaceAll("6", "").replaceAll("7", "").replaceAll("8", "").replaceAll("9", "");
	}
}
