package search;

import java.util.ArrayList;

import java.util.List;
import java.util.Collections;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 */
public class QueueBasedBreadthFirstSearcher<T> extends Searcher<T> {
	
	private final List<T> states;
	private final List<T> predecessors;
	int a='a';
	double b=0;
	
	
	public QueueBasedBreadthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
		states=new ArrayList<T>();
		predecessors=new ArrayList<T>();
		System.out.println(new String("Shubham").hashCode());
		System.out.println(new String("Shubhm").hashCode());
		System.out.println(new String("Shubham").equals(new String("Shubham")));
		System.out.println((a));

Stack<T> stack=new Stack<T>();

	}
	

	@Override
	public List<T> findSolution() {
		// TODO
	System.out.println(new String("Shubham").hashCode());
		if(solution!=null)
			return solution;
		final T initialState=searchProblem.getInitialState();
		states.add(initialState);
		predecessors.add(initialState);
		T current=queueHelper(initialState);
		final List<T> path=new ArrayList<T>();
		if(current!=null){
			path.add(current);
			while(!current.equals(searchProblem.getInitialState())){
				T predecessor=predecessors.get(states.indexOf(current));
				path.add(predecessor);
				current=predecessor;
			}
			Collections.reverse(path);
			
		}
		if(path.size()>0){
			if(!isValidSolution(path)){
				throw new RuntimeException("searcher should never find an invalid solution!!");
			}
				
			}
		
		return path;
		
		//return null;
	}
	
	private T queueHelper(T data){
		if(searchProblem.isGoal(data))
			return data;
		Queue<T> queue=new LinkedList<T>();
		visited.add(data);
		queue.add(data);
		while(!queue.isEmpty()){
			T e=queue.remove();
			List<T> list=searchProblem.getSuccessors(e);
			for(int i=0;i<list.size();i++){
				if(!visited.contains(list.get(i))){
				visited.add(list.get(i));
				queue.add(list.get(i));
				if(!states.contains(list.get(i))){
				states.add(list.get(i));
				predecessors.add(list.get(i));
				}
				predecessors.set(states.indexOf(list.get(i)), e);
				if(searchProblem.isGoal(list.get(i)))
					return list.get(i);
				}
			}
		}	
		for(int z=0;z<visited.size();z++)
		{
			visited.remove(z);
		}
		
	return null;	
	}
	
}
