/* Copyright (c) 2014 "Naftoreiclag" https://github.com/Naftoreiclag
 *
 * Distributed under the BSD 2-Clause License (http://opensource.org/licenses/BSD-2-Clause)
 * See accompanying file LICENSE
 */

package naftoreiclag.gibberish;

import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.WaveData;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.openal.AL10.*;

public class Main
{
	private static float mod = 1.5f;
	private static Map<String, Integer> sounds = new HashMap<String, Integer>();
	public static String loadDir = "banana";

	public static void main(String[] args) throws FileNotFoundException
	{
		
		try
		{
			Display.setDisplayMode(new DisplayMode(100, 100));
			Display.create();
			AL.create();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Display.destroy();
			AL.destroy();
			System.exit(0);
		}
		
		boolean iDidIt = false;
		
		loadBanana();
		while(!Display.isCloseRequested() && !iDidIt)
		{
			if(!iDidIt)
			{
				String s1 = "";
				try
				{
					s1 = test();
				} catch (IOException e1)
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.exit(0);
				}
				System.out.println(s1);


				sayItBanana(s1);
			}
			iDidIt = true;
			
			Display.update();
			Display.sync(60);
		}
		unloadBanana();
		
		AL.destroy();
		Display.destroy();
		System.exit(0);
	}
	
	protected static String test() throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("example.txt"));
		StringBuilder outPut = new StringBuilder();
		String line;
		while((line = br.readLine()) != null)
		{
			outPut.append(line + " ");
		}
		br.close();
		return outPut.toString();
	}
	
	private static void sayItBanana(String s1)
	{
		s1 = s1.toLowerCase();
		
		
		String s = TranslationDict.translate(s1);
		s = s.toLowerCase();
		System.out.println("ITIS:" + s + "]");
		String chunk = "";
		for(char c : s.toCharArray())
		{
			if(c == ' ' || c == '.')
			{
				playSound(chunk);
				System.out.println("[" + chunk + "]");
				chunk = "";
				try
				{
					Thread.sleep((int) (200.0f / mod));
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				continue;
			}
			else
			{
				chunk += c;
			}
		}
	}
	
	private static void sayItCarrot(String s1)
	{
		s1 = s1.toLowerCase();
		
		for(char c : s1.toCharArray())
		{
			System.out.println(c);
			playSound(c + "");
			try
			{
				Thread.sleep((int) (350.0f / mod));
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static void loadBanana()
	{
		
		try
		{
			TranslationDict.load();
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.exit(1);
		}
		
		loadDir = "banana";

		loadSound("aa");
		loadSound("ae");
		loadSound("ah");
		loadSound("ao");
		loadSound("aw");
		loadSound("ay");
		loadSound("b");
		loadSound("ch");
		loadSound("d");
		loadSound("dh");
		loadSound("eh");
		loadSound("er");
		loadSound("ey");
		loadSound("f");
		loadSound("g");
		loadSound("hh");
		loadSound("ih");
		loadSound("iy");
		loadSound("jh");
		loadSound("k");
		loadSound("l");
		loadSound("m");
		loadSound("n");
		loadSound("ng");
		loadSound("ow");
		loadSound("oy");
		loadSound("p");
		loadSound("r");
		loadSound("s");
		loadSound("sh");
		loadSound("t");
		loadSound("th");
		loadSound("uh");
		loadSound("uw");
		loadSound("v");
		loadSound("w");
		loadSound("y");
		loadSound("z");
		loadSound("zh");
	}
	
	private static void unloadBanana()
	{
		unloadSound("aa");
		unloadSound("ae");
		unloadSound("ah");
		unloadSound("ao");
		unloadSound("aw");
		unloadSound("ay");
		unloadSound("b");
		unloadSound("ch");
		unloadSound("d");
		unloadSound("dh");
		unloadSound("eh");
		unloadSound("er");
		unloadSound("ey");
		unloadSound("f");
		unloadSound("g");
		unloadSound("hh");
		unloadSound("ih");
		unloadSound("iy");
		unloadSound("jh");
		unloadSound("k");
		unloadSound("l");
		unloadSound("m");
		unloadSound("n");
		unloadSound("ng");
		unloadSound("ow");
		unloadSound("oy");
		unloadSound("p");
		unloadSound("r");
		unloadSound("s");
		unloadSound("sh");
		unloadSound("t");
		unloadSound("th");
		unloadSound("uh");
		unloadSound("uw");
		unloadSound("v");
		unloadSound("w");
		unloadSound("y");
		unloadSound("z");
		unloadSound("zh");
	}
	
	private static void loadCarrot()
	{
		loadDir = "carrot";
		
		loadSound("a");
		loadSound("b");
		loadSound("c");
		loadSound("d");
		loadSound("e");
		loadSound("f");
		loadSound("g");
		loadSound("h");
		loadSound("i");
		loadSound("j");
		loadSound("k");
		loadSound("l");
		loadSound("m");
		loadSound("n");
		loadSound("o");
		loadSound("p");
		loadSound("q");
		loadSound("r");
		loadSound("s");
		loadSound("t");
		loadSound("u");
		loadSound("v");
		loadSound("w");
		loadSound("x");
		loadSound("y");
		loadSound("z");
	}
	
	private static void unloadCarrot()
	{
		unloadSound("a");
		unloadSound("b");
		unloadSound("c");
		unloadSound("d");
		unloadSound("e");
		unloadSound("f");
		unloadSound("g");
		unloadSound("h");
		unloadSound("i");
		unloadSound("j");
		unloadSound("k");
		unloadSound("l");
		unloadSound("m");
		unloadSound("n");
		unloadSound("o");
		unloadSound("p");
		unloadSound("q");
		unloadSound("r");
		unloadSound("s");
		unloadSound("t");
		unloadSound("u");
		unloadSound("v");
		unloadSound("w");
		unloadSound("x");
		unloadSound("y");
		unloadSound("z");
	}
	
	private static void loadSound(String name)
	{
		int buffer = alGenBuffers();

		WaveData data;
		try
		{
			data = WaveData.create(new BufferedInputStream(new FileInputStream("resources/gibberish/" + loadDir + "/" + name + ".wav")));
		}
		catch (FileNotFoundException e)
		{
			return;
		}
		alBufferData(buffer, data.format, data.data, data.samplerate);
		data.dispose();

		int source = alGenSources();
		alSourcei(source, AL_BUFFER, buffer);
		alSourcef(source, AL_PITCH, mod);
		
		sounds.put(name, source);
	}
	
	private static boolean playSound(String name)
	{
		Integer i = sounds.get(name);
		
		if(i != null)
		{
			alSourcePlay(i);
			return true;
		}
		return false;
	}
	
	private static void unloadSound(String name)
	{
		Integer i = sounds.get(name);
		
		if(i != null)
		{
			alDeleteBuffers(i);
		}
	}
}
