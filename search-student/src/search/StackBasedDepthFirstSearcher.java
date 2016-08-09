package search;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Stack. This results in a
 * depth-first search.
 * 
 */
public class StackBasedDepthFirstSearcher<T> extends Searcher<T> {
	private final List<T> states;
	private final List<T> predecessors;
	//Stack<T> stack=new Stack<T>();
	
	public StackBasedDepthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
	states=new ArrayList<T>();
	predecessors=new ArrayList<T>();
	}

	@Override
	public List<T> findSolution() {
		// TODO
		if(solution!=null)
			return solution;
		final T initialState=searchProblem.getInitialState();
		states.add(initialState);
		predecessors.add(initialState);
		T current=stackHelper(initialState);
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
	}
	
	private T stackHelper(T data){
		if(searchProblem.isGoal(data))
			return data;
		Stack<T> stack=new Stack<T>();
		visited.add(data);
		stack.push(data);
		while(!stack.isEmpty()){
			T e=stack.peek();
			List<T> list=searchProblem.getSuccessors(stack.peek());
			boolean alreadyVisited=true;
			int j=-1;
			for(int i=0;i<list.size();i++){
				if(!visited.contains(list.get(i)))
				{
					j=i;
					alreadyVisited=false;
					break;
				}
			}
			if(alreadyVisited)
				stack.pop();
			else
			{
				if(j!=-1)
				stack.push(list.get(j));
				visited.add(list.get(j));
				states.add(list.get(j));
				predecessors.add(list.get(j));
				predecessors.set(states.indexOf(list.get(j)), e);
				if(searchProblem.isGoal(list.get(j)))
					return list.get(j);
			}
			
		}
		for(int z=0;z<visited.size();z++)
		{
			visited.remove(z);
		}
	return null;
	}
	
}
