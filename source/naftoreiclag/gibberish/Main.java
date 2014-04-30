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
	private static float mod = 3.0f;
	private static Map<String, Integer> sounds = new HashMap<String, Integer>();

	public static void main(String[] args) throws FileNotFoundException
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
		
		boolean iDidIt = false;
		
		while(!Display.isCloseRequested() && !iDidIt)
		{
			if(!iDidIt)
			{
				//String s = "S AA R IY";
				//String s = "AY . AE M . S AA R IY . D EY V . . AY . AE M . AH F R EY D . AY . K AE N T . D UW . DH AE T .";
				//String s = "AY  AE M  S AA R IY  D EY V  AY  AE M  AH F R EY D  AY  K AE N T  D UW  DH AE T ";
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
			iDidIt = true;
			
			Display.update();
			Display.sync(60);
		}
		
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
	
	private static void loadSound(String name)
	{
		int buffer = alGenBuffers();

		WaveData data;
		try
		{
			data = WaveData.create(new BufferedInputStream(new FileInputStream("resources/gibberish/banana/" + name + ".wav")));
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
