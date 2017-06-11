/**
 * 
 */
package roadgraph;

import java.util.LinkedList;
import java.util.List;

import geography.GeographicPoint;

/**
 * @author UCSD Intermediate Programming MOOC team
 *
 * A directed edge in a map graph from Node start to Node end
 */
class MapEdge 
{
	/** The name of the road */
	private String roadName;
	
	/** The type of the road */
	private String roadType;
	
	/** The two end points of the edge */
	private MapNode start;
	private MapNode end;
	
	/** The length of the road segment, in km */
	private double length;
	
	/** The duration of a trip on the road segment, to be determined by the roadType and length */
	private double duration;
	
	/** speed over different road segments */
	/** conversion of km to miles */
	private static final double kmToMiles = 1.60934; 
	
	
	/** constants to represent speed over various road types */
	static final double PRIMARY_ROAD_SPEED = 70 * kmToMiles;
	static final double SECONDARY_ROAD_SPEED = 50 * kmToMiles;
	static final double TERTIARY_ROAD_SPEED = 40 * kmToMiles;
	static final double RESIDENTIAL_ROAD_SPEED = 25 * kmToMiles;
	static final double LIVING_STREET_ROAD_SPEED = 15 * kmToMiles;
	
	
	
	
	static final double DEFAULT_LENGTH = 0.01;
	
	
	
	/** Create a new MapEdge object
	 * 
	 * @param roadName
	 * @param n1  The point at one end of the segment
	 * @param n2  The point at the other end of the segment
	 * 
	 */
	MapEdge(String roadName, MapNode n1, MapNode n2) 
	{
		this(roadName, "", n1, n2, DEFAULT_LENGTH);
	}
	
	/** 
	 * Create a new MapEdge object
	 * @param roadName  The name of the road
	 * @param roadType  The type of the road
	 * @param n1 The point at one end of the segment
	 * @param n2 The point at the other end of the segment
	 */
	MapEdge(String roadName, String roadType, MapNode n1, MapNode n2) 
	{
		this(roadName, roadType, n1, n2, DEFAULT_LENGTH);
	}
	
	/** 
	 * Create a new MapEdge object
	 * @param roadName  The name of the road
	 * @param roadType  The type of the road
	 * @param n1 The point at one end of the segment
	 * @param n2 The point at the other end of the segment
	 * @param length The length of the road segment
	 */	
	MapEdge(String roadName, String roadType,
			MapNode n1, MapNode n2, double length) 
	{
		this.roadName = roadName;
		start = n1;
		end = n2;
		this.roadType = roadType;
		this.length = length;
		setDuration();
	}
	
	/**
	 * return the MapNode for the end point
	 * @return the MapNode for the end point
	 */
	MapNode getEndNode() {
	   return end;
	}
	
	/**
	 * Return the location of the start point
	 * @return The location of the start point as a GeographicPoint
	 */
	GeographicPoint getStartPoint()
	{
		return start.getLocation();
	}
	
	/**
	 * Return the location of the end point
	 * @return The location of the end point as a GeographicPoint
	 */
	GeographicPoint getEndPoint()
	{
		return end.getLocation();
	}
	
	/**
	 * Return the length of this road segment
	 * @return the length of the road segment
	 */
	double getLength()
	{
		return length;
	}
	
	/**
	 * Get the road's name
	 * @return the name of the road that this edge is on
	 */
	public String getRoadName()
	{
		return roadName;
	}
	
	public String getRoadType() {
		return roadType;
	}
	
	public void setDuration() {
		switch (roadType) {
			case "primary": duration = length/PRIMARY_ROAD_SPEED;
			break;
			case "secondary": duration = length/SECONDARY_ROAD_SPEED;
			break;
			case "teriary": duration = length/TERTIARY_ROAD_SPEED;
			break;
			case "residential": duration = length/RESIDENTIAL_ROAD_SPEED;
			break;
			case "living_street": duration = length/LIVING_STREET_ROAD_SPEED;
			break;
			default: duration = length/TERTIARY_ROAD_SPEED;
		}
	}
	
	public double getDuration() {
		return duration;
	}

	/**
	 * Given one of the nodes involved in this edge, get the other one
	 * @param node The node on one side of this edge
	 * @return the other node involved in this edge
	 */
	MapNode getOtherNode(MapNode node)
	{
		if (node.equals(start)) 
			return end;
		else if (node.equals(end))
			return start;
		throw new IllegalArgumentException("Looking for " +
			"a point that is not in the edge");
	}
	
	/**
	 * Return a String representation for this edge.
	 */
	@Override
	public String toString()
	{
		String toReturn = "[EDGE between ";
		toReturn += "\n\t" + start.getLocation();
		toReturn += "\n\t" + end.getLocation();
		toReturn += "\nRoad name: " + roadName + " Road type: " + roadType +
				" Segment length: " + String.format("%.3g", length) + "km" + "\t" +
				"Duration: " + duration;
		
		return toReturn;
	}

}