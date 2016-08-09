package puzzle;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import search.SearchProblem;
import search.Solver;
import search.Searcher;
/**
 * A class to represent an instance of the eight-puzzle.
 * 
 * The spaces in an 8-puzzle are indexed as follows:
 * 
 * 0 | 1 | 2 --+---+--- 3 | 4 | 5 --+---+--- 6 | 7 | 8
 * 
 * The puzzle contains the eight numbers 1-8, and an empty space. If we
 * represent the empty space as 0, then the puzzle is solved when the values in
 * the puzzle are as follows:
 * 
 * 1 | 2 | 3 --+---+--- 4 | 5 | 6 --+---+--- 7 | 8 | 0
 * 
 * That is, when the space at index 0 contains value 1, the space at index 1
 * contains value 2, and so on.
 * 
 * From any given state, you can swap the empty space with a space adjacent to
 * it (that is, above, below, left, or right of it, without wrapping around).
 * 
 * For example, if the empty space is at index 2, you may swap it with the value
 * at index 1 or 5, but not any other index.
 * 
 * Only half of all possible puzzle states are solvable! See:
 * https://en.wikipedia.org/wiki/15_puzzle for details.
 * 
 * 
 * @author liberato
 *
 */
public class EightPuzzle implements SearchProblem<List<Integer>> {
	/**
	 * Creates a new instance of the 8 puzzle with the given starting values.
	 * 
	 * The values are indexed as described above, and should contain exactly the
	 * nine integers from 0 to 8.
	 * 
	 * @param startingValues
	 *            the starting values, 0 -- 8
	 * @throws IllegalArgumentException
	 *             if startingValues is invalid
	 */
	List<Integer> list;

	public EightPuzzle(List<Integer> startingValues) {
		// TODO
	//	if(!isValidSolution(startingValues))
	//		th
		int[] array1 = new int[9];
		if (startingValues == null)
			throw new IllegalArgumentException();
		if (startingValues.size() != 9)
			throw new IllegalArgumentException();
		for (int i = 0; i < startingValues.size(); i++) {
			if(startingValues.get(i).intValue()<0||startingValues.get(i).intValue()>8)
				throw new IllegalArgumentException();
			array1[startingValues.get(i).intValue()]++;
		}
		for (int i = 0; i < array1.length; i++) {
			if (array1[i] != 1)
				throw new IllegalArgumentException();
		}
		list = startingValues;
	}

	@Override
	public List<Integer> getInitialState() {
		// TODO
		return list;
	}

	@Override
	public List<List<Integer>> getSuccessors(List<Integer> currentState) {
		// TODO

		List<List<Integer>> newList = new LinkedList<List<Integer>>();
		int location = -1;
		for (int i = 0; i < currentState.size(); i++) {
			if (currentState.get(i).intValue() == 0) {
				location = i;

			}
		}
		int right = location + 1;
		int left = location - 1;
		int below = location + 3;
		int above = location - 3;
		if (right <= 8 && right >= 0 && (right % 3) != 0) {

			List<Integer> copy = new LinkedList<Integer>();
			for (int i = 0; i < currentState.size(); i++)
				copy.add(i, currentState.get(i));

			Integer e = copy.get(right);
			copy.set(location, e);
			copy.set(right, new Integer(0));
			newList.add(copy);

		}
		if (left <= 8 && left >= 0 && left != 2 && left != 5) {

			List<Integer> copy2 = new LinkedList<Integer>();
			for (int i = 0; i < currentState.size(); i++)
				copy2.add(i, currentState.get(i));

			Integer e = copy2.get(left);
			copy2.set(location, e);
			copy2.set(left, new Integer(0));
			newList.add(copy2);

		}
		if (below <= 8 && below >= 0) {

			List<Integer> copy3 = new LinkedList<Integer>();
			for (int i = 0; i < currentState.size(); i++)
				copy3.add(i, currentState.get(i));

			Integer e = copy3.get(below);
			copy3.set(location, e);
			copy3.set(below, new Integer(0));
			newList.add(copy3);

		}
		if (above <= 8 && above >= 0) {

			List<Integer> copy4 = new LinkedList<Integer>();
			for (int i = 0; i < currentState.size(); i++)
				copy4.add(i, currentState.get(i));

			Integer e = copy4.get(above);
			copy4.set(location, e);
			copy4.set(above, new Integer(0));
			newList.add(copy4);
		}
		return newList;
	}

	@Override
	public boolean isGoal(List<Integer> state) {
		// TODO
		if(state==null)
			return false;
		if(state.size()!=9)
			return false;
		for (int i = 0; i <= 7; i++) {
			if (state.get(i).intValue() != (i + 1))
				return false;
		}
		if (state.get(8).intValue() != 0)
			return false;
		return true;
	}

	public static void main(String[] args) {
		EightPuzzle e = new EightPuzzle(Arrays.asList(new Integer[] { 1, 2, 3, 4, 0, 6, 7, 5, 8 }));

		List<List<Integer>> r = new Solver<List<Integer>>(e).solveWithBFS();
		for (List<Integer> l : r) {
			System.out.println(l);
		}
	}
}
