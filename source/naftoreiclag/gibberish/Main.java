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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.openal.AL10.*;

public class Main
{
	private static Map<String, Integer> sounds = new HashMap<String, Integer>();

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
		
		boolean iDidIt = false;
		
		while(!Display.isCloseRequested())
		{
			if(!iDidIt)
			{
				String s = "naftoreiclag";
				for(char c : s.toCharArray())
				{
					playSound(c + "");
					try
					{
						Thread.sleep(150);
					} catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			iDidIt = true;
			
			Display.update();
			Display.sync(60);
		}
		
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
		
		AL.destroy();
		Display.destroy();
		System.exit(0);
	}
	
	private static void loadSound(String name)
	{
		int buffer = alGenBuffers();

		WaveData data;
		try
		{
			data = WaveData.create(new BufferedInputStream(new FileInputStream("resources/gibberish/" + name + ".wav")));
		}
		catch (FileNotFoundException e)
		{
			return;
		}
		alBufferData(buffer, data.format, data.data, data.samplerate);
		data.dispose();

		int source = alGenSources();
		alSourcei(source, AL_BUFFER, buffer);
		
		sounds.put(name, source);
	}
	
	private static void playSound(String name)
	{
		Integer i = sounds.get(name);
		
		if(i != null)
		{
			alSourcePlay(i);
		}
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
