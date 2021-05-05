package sierpinski;

import java.io.*;
import java.util.ArrayList;

public class Main
{
	public static void main(String[] args)
	{
		BufferedReader fileReader;
		PrintWriter fileWriter;
		
		try
		{
			fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("input.txt"))));
			String input;
			ArrayList<String> lines = new ArrayList<String>();
			
			while ((input = fileReader.readLine()) != null) {
				input = input.strip();
				if (!input.isEmpty())
				{
					lines.add(input);
				}
			}
			
			fileReader.close();
			
			int level = Integer.parseInt(lines.get(0));
			Sierpinski triangle = new Sierpinski(level);
			ArrayList<Double> numbers = new ArrayList<Double>();
			ArrayList<Point> points = triangle.getPoints();
			Point p1, p2;
			Line inputLine;
			boolean found;

			fileWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File("output.txt"))));
			System.out.println("Checking " + (lines.size() - 1) + " input lines for intersections...");
			
			for (int indexP = 1; indexP < lines.size(); indexP++)
			{
				numbers.clear();
				for (String s : lines.get(indexP).split(" "))
				{
					numbers.add(Double.parseDouble(s));
				}
				
				p1 = new Point(numbers.get(0), numbers.get(1));
				p2 = new Point(numbers.get(2), numbers.get(3));
				inputLine = new Line(p1, p2);
				found = false;
				
				for (int indexS = 1; indexS < points.size(); indexS++)
				{
					if (inputLine.instersects(points.get(indexS - 1), points.get(indexS)))
					{
						found = true;
						break;
					}
				}
				
				fileWriter.println(found ? "1" : "0");
			}
			
			fileWriter.close();
			System.out.println("Done.");
		}
		catch(Exception e)
		{
			System.out.println("Error: " + e);
		}
	}
}
