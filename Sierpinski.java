package sierpinski;

import java.util.*;

public class Sierpinski
{
	/**
	 * Tracks the points of the triangle.
	 */
	private ArrayList<Point> points;
	
	/**
	 * Tracks the slopes used to calculate the points of the triangle.
	 */
	private int[] slopes; //ArrayList<Integer> slopes = new ArrayList<Integer>();
	
	/**
	 * Save the sin() and cos() values of the slope to quickly calculate the next point.
	 */
	private final double[] slopeX, slopeY;
	
	/**
	 * The level the triangle will be drawn to.
	 * The total lines will equal 3^n
	 */
	private int lv;
	
	Sierpinski(int level)
	{
		this(level, 1.0);
	}

	Sierpinski(int level, double length)
	{
	    System.out.println("Drawing Sierpinski triangle at level " + level + "...");
	    
	    lv = level;
		double lengthCos = length / 2.0;
		double lengthSin = lengthCos * Math.sqrt(3);
		
		// Get the slopes of the Sierpinski triangle
		drawSlopes(level);
		
		slopeX = new double[]{length, lengthCos, -lengthCos, -length, -lengthCos, lengthCos, length};
		slopeY = new double[]{0, lengthSin, lengthSin, 0, -lengthSin, -lengthSin};
		points = new ArrayList<Point>(slopes.length + 1);
		
		// Start with the origin point
		points.add(new Point());
		double newX = 0, newY = 0;
		
		// Add all points in order using the given slopes
		for (int i = 0; i < slopes.length; i++)
		{
			newX += slopeX[getBoundedSlope(i)];
			newY += slopeY[getBoundedSlope(i)];
			points.add(new Point(newX, newY));
		}
		
		System.out.println("Done.");
	}
	
	/**
	 * Returns the bounded slope at the given index.
	 * @return slope (bounded between 0-5)
	 */
	private int getBoundedSlope(int index)
	{
		int slope = slopes[index] % 6;
		if (slope < 0)
		{
			slope += 6;
		}
		return slope;
	}

	/**
	 * Draw the Sierpinski triangle iteratively.
	 * @return The slopes of the full Sierpinski triangle.
	 */
	private void drawSlopes(int level)
	{
		int tempArr[], index, arrMax = 1, offsetIndex = 0;
		int offsets[] = new int[] {1, 0, -1, -1, 0, 1};
		
		slopes = new int[(int) Math.pow(3, level)];
		slopes[0] = 0;
		
		for (int l = 0; l < level; l++)
		{
			arrMax *= 3;
			tempArr = new int[arrMax * 3];
			index   = 0;
			
			for (int i = 0; i < arrMax; i++)
			{
				if (offsetIndex >= 6) offsetIndex -= 6;

				tempArr[index++] = slopes[i] + offsets[offsetIndex++];
				tempArr[index++] = slopes[i] + offsets[offsetIndex++];
				tempArr[index++] = slopes[i] + offsets[offsetIndex++];
			}
			
			slopes = tempArr;
		}
	}

	// Deprecated, iteration performed better at higher N levels.
	/**
	 * Draw the Sierpinski triangle recursively.
	 * @return The slopes of the full Sierpinski triangle.
	 */
	/*
	private void drawLines(int level)
	{
		slopes = drawLines(level, 0, true);
	}
	 */
	
	/**
	 * Draw the Sierpinski triangle recursively.
	 * @return The slopes of the partial Sierpinski triangle.
	 */
	/*
	private ArrayList<Integer> drawLines(int level, int slope, boolean expandLineOut)
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		int offset;
		if (level-- <= 0)
		{
			list.add(slope);
		}
		else
		{
			offset = expandLineOut ? 1 : -1;
			list.addAll(drawLines(level, slope + offset, !expandLineOut));
			list.addAll(drawLines(level, slope, expandLineOut));
			list.addAll(drawLines(level, slope - offset, !expandLineOut));
		}
		return list;
	}
	 */

	/**
	 * Returns the list of points in the Sierpinski triangle.
	@return List of points.
	*/
	public ArrayList<Point> getPoints()
	{
		return points;
	}
	
	public String toString()
	{
		return "Level: " + lv + " / Lines: " + slopes.length;
	}
}