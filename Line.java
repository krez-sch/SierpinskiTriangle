package sierpinski;

public class Line {
	
	private Point p1, p2;
	private double m, b;
	
	Line(Point point1, Point point2)
	{
		p1 = point1;
		p2 = point2;
		
		p1.x = round(p1.x);
		p1.y = round(p1.y);
		p2.x = round(p2.x);
		p2.y = round(p2.y);
		
		m = (p2.y - p1.y) / (p2.x - p1.x);
		b = p1.y - (m * p1.x);
	}
	
	/**
	 * We round the numbers to account for decimal precision errors.
	 * @param number
	 * @return
	 */
	private double round(double number)
	{
		int precision = 6;
		double p = Math.pow(10, precision);
		return Math.round(number*p)/p;
	}
	
	private boolean xGreaterMin(Line l, double x)
	{
		return (x >= Math.min(this.p1.x, this.p2.x)) && (x >= Math.min(l.p1.x, l.p2.x));
	}
	
	private boolean xLessMax(Line l, double x)
	{
		return (x <= Math.max(this.p1.x, this.p2.x)) && (x <= Math.max(l.p1.x, l.p2.x));
	}
	
	private boolean yGreaterMin(Line l, double y)
	{
		return (y >= Math.min(this.p1.y, this.p2.y)) && (y >= Math.min(l.p1.y, l.p2.y));
	}
	
	private boolean yLessMax(Line l, double y)
	{
		return (y <= Math.max(this.p1.y, this.p2.y)) && (y <= Math.max(l.p1.y, l.p2.y));
	}
	
	/**
	 * Return the
	 * @param l
	 * 	   - The intersecting line segment.
	 * @return
	 *     The midpoint between the lines if one exists. Returns null otherwise.
	 */
	public Point getIntersection(Line l)
	{
		Point intersection = null;
		double x = (l.b - this.b) / (this.m - l.m);
		double y = this.m * ((l.b - this.b) / (this.m - l.m)) + this.b;
		
		if (!Double.isNaN(x) && !Double.isNaN(y))
		{
			x = round(x);
			y = round(y);
			
			if (xGreaterMin(l, x) && xLessMax(l, x) && yGreaterMin(l, y) && yLessMax(l, y))
			{
				intersection = new Point(x, y);
			}
		}
		
		return intersection;
	}
	
	public boolean instersects(Line l)
	{
		return getIntersection(l) != null;
	}
	
	public boolean instersects(Point p1, Point p2)
	{
		return getIntersection(new Line(p1, p2)) != null;
	}
	
	public String toString()
	{
		return "(" + p1 + ")->(" + p2 + ")";
	}

}
