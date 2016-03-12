package engine.component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import engine.utils.Vector3f;

public class ComponentLoader {

	public static ComponentData loadComponentData(String filePath, String extension){
		ComponentData data = null;
		File file = new File(filePath + extension);
		System.out.println(file.getAbsolutePath());
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return data;
		}

		try {
			String componentKey = in.readLine();
			data = new ComponentData(componentKey);
			String line;
			while(true){
				line = in.readLine();
				if(line == null){
					break;
				}
				String[] splitLine = line.split(" ");

				if(splitLine.length == 4 && splitLine[0].equals("v")){
					System.out.println("Loading vector2 is not yet supported!");
					continue;
				}

				if(splitLine.length == 5 && splitLine[0].equals("v")){
					float x = Float.parseFloat(splitLine[2]);
					float y = Float.parseFloat(splitLine[3]);
					float z = Float.parseFloat(splitLine[4]);
					data.addVector3f(splitLine[1], x, y, z);
				}

				if(splitLine.length == 6 && splitLine[0].equals("v")){
					System.out.println("Loading vector4 is not yet supported!");
					continue;
				}

				if(splitLine.length == 3){

					if(splitLine[0].equals("f")){
						float f = Float.parseFloat(splitLine[2]);
						data.addFloat(splitLine[1], f);
					}

					if(splitLine[0].equals("i")){
						int i = Integer.parseInt(splitLine[2]);
						data.addInteger(splitLine[1], i);
					}

					if(splitLine[0].equals("s")){
						data.addString(splitLine[1], splitLine[2]);
					}

				}
				else{
					continue;
				}
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

	public static void writeComponentData(String filePath, ComponentData data){

		if(data.isEmpty())
			return;

		File file = new File(filePath + ".csf");
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(file));
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		try{
			out.write(data.componentKey);
			out.newLine();
			List<ComponentValue> valueList = data.getValues();
			for(ComponentValue value : valueList){
				String s = value.valueKey;
				
				if(value.valueType == ComponentValue.COMPONENT_TYPE_INT){
					int i = value.getInteger();
					out.write("i " + s + " " + Integer.toString(i));
				}
				
				if(value.valueType == ComponentValue.COMPONENT_TYPE_FLOAT){
					float f = value.getFloat();
					out.write("f " + s + " " + Float.toString(f));
				}
				
				if(value.valueType == ComponentValue.COMPONENT_TYPE_STRING){
					out.write("s " + s + " " + value.getString());
				}
				
				if(value.valueType == ComponentValue.COMPONENT_TYPE_VECTOR3){
					Vector3f vector = value.getVector3f();
					out.write("v " + s + " " + vector.x + " " + vector.y + " " +vector.z);
				}
				/*Object o = data.getValue(s);

				if(o instanceof Integer){
					int i = (int)o;
					out.write("i " + s + " " + Integer.toString(i));
				}
				else if(o instanceof Float){
					float f = (float)o;
					out.write("f " + s + " " + Float.toString(f));
				}

				if(o instanceof String){
					String s1 = (String)o;
					out.write("s " + s + " " + s1);
				}
				
				if(o instanceof Vector3f){
					Vector3f vector = (Vector3f)o;
					out.write("v " + s + " " + vector.x + " " + vector.y + " " +vector.z);
				}*/
				
				out.newLine();
			}
			out.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
