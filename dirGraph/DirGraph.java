package dirGraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
//Eric Wagner 116484695 ew046 0202

//I pledge on my honor that I have not given or received any unauthorized
//assistance on this assignment.

//this class implements a directional graph
public class DirGraph<V> {
	// keeps track of the neighbors of each vertex
	ArrayList<ArrayList<V>> neighbors = new ArrayList<ArrayList<V>>();
	// keeps track of each vertex
	public ArrayList<V> vertex = new ArrayList<V>();
	// keeps track of the weights of each edge
	ArrayList<ArrayList<Integer>> weights = new ArrayList<ArrayList<Integer>>();

	// method creates and adds a vertex to the graph with data vertexData
	public boolean createVertex(V vertexData) {
		if (vertexData == null) {
			throw new IllegalArgumentException();
		}
		if (vertex.contains(vertexData)) {
			return false;
		}
		vertex.add(vertexData);
		neighbors.add(new ArrayList<V>());
		weights.add(new ArrayList<Integer>());
		return true;
	}

	// method returns true or false if the given vertexData is a vertex in the
	// graph
	public boolean isVertex(V vertexData) {
		if (vertexData == null) {
			throw new IllegalArgumentException();
		}
		if (vertex.contains(vertexData)) {
			return true;
		}
		return false;
	}

	// returns the list of vertexes
	public Collection<V> getVertices() {
		return vertex;
	}

	// method deletes a vertex from the graph and all of the edges involving it
	public boolean deleteVertexWithEdges(V vertexData) {
		if (vertexData == null) {
			throw new IllegalArgumentException();
		}
		int count = 0;
		if (vertex.contains(vertexData) == false) {
			return false;
		}
		for (V v = vertex.get(count); count < vertex.size(); count++) {
			v = vertex.get(count);
			if (v.equals(vertexData)) {
				vertex.remove(count);

				neighbors.remove(count);
				weights.remove(count);
			}
		}
		int ocount = 0;
		for (ArrayList<V> v : neighbors) {
			if (v != null && v.contains(vertexData)) {
				for (int i = 0; i < v.size(); i++)
					if (v.get(i).equals(vertexData)) {
						v.remove(i);
					}

			}
		}
		ocount++;
		return true;

	}

	// creates an edge if possible from sourceVertex to destVertex with weight
	// weight. If the sourceVertex or destVertex are not already in the graph
	// than they are added
	public boolean createEdge(V sourceVertex, V destVertex, int weight) {
		if (sourceVertex == null || destVertex == null) {
			throw new IllegalArgumentException();
		}
		if (weight < 0) {
			return false;
		}
		if (vertex.contains(sourceVertex) == false) {
			this.createVertex(sourceVertex);
		}
		if (vertex.contains(destVertex) == false) {
			this.createVertex(destVertex);
		}
		int index = -1;
		for (int x = 0; x < vertex.size(); x++) {
			if (vertex.get(x).equals(sourceVertex)) {
				index = x;
			}
		}
		if (index == -1) {
			return false;
		}
		if (neighbors.get(index) == null) {
			neighbors.set(index, new ArrayList<V>());
		}
		if (neighbors.get(index).contains(destVertex)) {
			return false;
		}

		neighbors.get(index).add(destVertex);
		weights.get(index).add(weight);
		return true;
	}

	// method returns the weight of the edge from sourceVertex to destVertex if
	// it exists, otherwise returns -1
	public int getEdge(V sourceVertex, V destVertex) {
		if (sourceVertex == null || destVertex == null) {
			throw new IllegalArgumentException();
		}
		if (vertex.contains(sourceVertex) == false
				|| vertex.contains(destVertex) == false) {
			return -1;
		}

		int index = -1;
		for (int x = 0; x < vertex.size(); x++) {
			if (vertex.get(x).equals(sourceVertex)) {
				index = x;
			}
		}
		if (neighbors.get(index) != null) {
			if (neighbors.get(index).contains(destVertex)) {
				for (int dindex = 0; dindex < weights.get(index)
						.size(); dindex++) {
					if (neighbors.get(index).get(dindex).equals(destVertex)) {
						return weights.get(index).get(dindex);
					}

				}

			}
		}
		return -1;
	}

	// method changes the weight of the edge from sourceVertex to destVertex to
	// newWeight
	public boolean changeEdge(V sourceVertex, V destVertex, int newWeight) {
		if (sourceVertex == null || destVertex == null) {
			throw new IllegalArgumentException();
		}
		if (vertex.contains(sourceVertex) == false
				|| vertex.contains(destVertex) == false) {
			return false;
		}
		int index = -1, dindex = -1;
		for (int x = 0; x < vertex.size(); x++) {
			if (vertex.get(x).equals(sourceVertex)) {
				index = x;
			}
			if (vertex.get(x).equals(destVertex)) {
				dindex = x;
			}
		}

		if (newWeight < 0) {
			if (neighbors.get(index) == null
					|| neighbors.get(index).contains(destVertex) == false) {
				return false;
			} else {
				int di = 0;
				for (int i = 0; i < neighbors.get(index).size(); i++) {
					if (neighbors.get(i).equals(destVertex)) {
						di = i;
					}
				}
				neighbors.get(index).remove(di);
				return true;
			}
		} else {
			if (neighbors.get(index) == null
					|| neighbors.get(index).contains(destVertex) == false) {
				return false;
			} else {
				int di = -1;
				for (int i = 0; i < neighbors.get(index).size(); i++) {
					if (neighbors.get(index).get(i).equals((destVertex))) {
						di = i;
					}
				}
				weights.get(index).set(di, newWeight);
			}
		}
		return true;

	}

	// method returns all of the neighbors of vertex vertexData as an ArrayList
	public Collection<V> neighborsOfVertex(V vertexData) {
		if (vertexData == null) {
			throw new IllegalArgumentException();
		}
		if (vertex.contains(vertexData) == false) {
			return null;
		}
		for (int i = 0; i < vertex.size(); i++) {
			if (vertex.get(i).equals(vertexData)) {
				return neighbors.get(i);
			}
		}
		return null;
	}

	// method removes vertexes from the graph that are in verticesOfNewGraph and
	// returns them as a new graph. Edges between two points being separated are
	// removed, and one that are separated together remain present or are added
	// to the new graph appropriately
	public DirGraph<V> divideGraph(Collection<V> verticesOfNewGraph) {
		if (verticesOfNewGraph == null) {
			throw new IllegalArgumentException();
		}
		DirGraph<V> g = new DirGraph<V>();
		Iterator<V> it = verticesOfNewGraph.iterator();
		while (it.hasNext()) {
			V newv = it.next();
			if (vertex.contains(newv)) {
				g.createVertex(newv);
				int id = -1;
				for (int i = 0; i < vertex.size(); i++) {
					if (vertex.get(i).equals(newv)) {
						id = i;
					}
				}
				for (int i = 0; i < neighbors.get(id).size(); i++) {
					if (verticesOfNewGraph.contains(neighbors.get(id).get(i))) {

						g.createVertex(neighbors.get(id).get(i));
						g.createEdge(newv, neighbors.get(id).get(i),
								weights.get(id).get(i));
						neighbors.get(id).remove(i);
						weights.get(id).remove(i);
					} else {
						neighbors.get(id).remove(i);
						weights.get(id).remove(i);
					}
				}
				vertex.remove(id);
			}
		}
		return g;
	}

}
