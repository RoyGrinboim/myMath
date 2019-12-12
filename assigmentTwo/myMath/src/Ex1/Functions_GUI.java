package Ex1;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import com.google.gson.Gson;

public class Functions_GUI implements functions{

	public static Color[] Colors = {Color.blue, Color.cyan,
			Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK};

	private LinkedList <function> funcs = new LinkedList<function>();
	
	@Override
	public boolean add(function arg0) {
		funcs.add(arg0);
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends function> arg0) {
		funcs.addAll(arg0);
		return true;
	}

	@Override
	public void clear() {
		funcs.clear();
		
	}

	@Override
	public boolean contains(Object arg0) {
		if(funcs.contains(arg0))
			return true;
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		if(funcs.containsAll(arg0))
			return true;
		return false;
	}

	@Override
	public boolean isEmpty() {
		if(funcs.isEmpty())
			return true;
		return false;
	}

	public LinkedList<function> getFuncs(){
		return this.funcs;
	}
	@Override
	public Iterator<function> iterator() {
			Iterator <function> itFunc = new Iterator<function>() {
				
				private int indx = 0;
				
				@Override
				public boolean hasNext() {
					if(getFuncs().size() > indx+1)
						return true;
					return false;
				}

				@Override
				public function next() {
					function f = getFuncs().get(indx);
					indx++;
					return f;
				}
			};
			return itFunc;
		}

	@Override
	public boolean remove(Object arg0) {
		return this.funcs.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return this.funcs.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		return this.funcs.retainAll(arg0);
	}

	@Override
	public int size() {
		return this.funcs.size();
	}

	@Override
	public Object[] toArray() {
		return this.funcs.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		return this.funcs.toArray(arg0);
	}

	@Override
	public void initFromFile(String fileName) throws IOException {
		File file = new File(fileName); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String st; 
		while ((st = br.readLine()) != null) 
		{
			ComplexFunction cf = new ComplexFunction(st);
			add(cf);
		}
			
		
	}

	@Override
	public void saveToFile(String fileName) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
	    for (int i = 0; i < funcs.size(); i++) {
	    	writer.write(funcs.get(i).toString());	
		}    
	    writer.close();
		
	}

	@Override
	public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawFunctions(String json_file) {
		function[] fun = new Gson().fromJson(json_file, function[].class);
		for(int i=0; i < fun.length; i++)
		{
			funcs.add(fun[i]);
		}
		
	}

}
