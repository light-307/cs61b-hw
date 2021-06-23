package bearmaps.proj2c;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

public class AStarSolver <Vertex> implements ShortestPathsSolver<Vertex> {

    private DoubleMapPQ<Vertex> PQ;
    private HashMap<Vertex,Double> distTo;
    private HashMap<Vertex,Vertex> edgeTo;

    private SolverOutcome outcome_result;
    private int num_dequeue;
    private double total_time;

    private AStarGraph<Vertex> input;
    private Vertex start;
    private Vertex end;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();

        PQ = new DoubleMapPQ<Vertex>();
        distTo = new HashMap<Vertex,Double>();
        edgeTo = new HashMap<Vertex,Vertex>();

        num_dequeue = 0;
        total_time = 0;
        Vertex v;

        this.input = input;
        this.start = start;
        this.end = end;


        PQ.add(start, input.estimatedDistanceToGoal(start, end));
        distTo.put(start, 0.0);
        edgeTo.put(start, null);

        while (PQ.size() > 0) {
            if (!PQ.getSmallest().equals(end)) {
                v = PQ.removeSmallest();
                num_dequeue++;

                for (WeightedEdge<Vertex> e : input.neighbors(v)) {
                    relax(e);
                }

                if (sw.elapsedTime() > timeout) {
                    outcome_result = SolverOutcome.TIMEOUT;
                    total_time = timeout;
                    return;
                }
            }
            else {
                break;
            }
        }

        if (distTo.containsKey(end)) {
            outcome_result = SolverOutcome.SOLVED;
        }
        else {
            outcome_result = SolverOutcome.UNSOLVABLE;
        }
        total_time = sw.elapsedTime();
    }
    private void relax(WeightedEdge<Vertex> e) {
        Vertex p = e.from();
        Vertex q = e.to();
        double w = e.weight();

        if (!distTo.containsKey(q)) {
            distTo.put(q, Double.POSITIVE_INFINITY);
            edgeTo.put(q, null);
        }

        if (distTo.get(p) + w < distTo.get(q)) {
            distTo.replace(q, distTo.get(p) + w);
            edgeTo.replace(q, p);

            if (PQ.contains(q)) {
                PQ.changePriority(q, distTo.get(q) + input.estimatedDistanceToGoal(q, end));
            }
            else {
                PQ.add(q, distTo.get(q) + input.estimatedDistanceToGoal(q, end));
            }
        }
    }

    @Override
    public SolverOutcome outcome() {
        return outcome_result;
    }

    @Override
    public List<Vertex> solution() {
        if (outcome_result == SolverOutcome.SOLVED) {
            LinkedList<Vertex> result = new LinkedList<Vertex>();

            Vertex tmp = end;
            while (tmp != null) {
                result.addFirst(tmp);
                tmp = edgeTo.get(tmp);
            }
            return result;
        }
        return null;
    }

    @Override
    public double solutionWeight() {
        if (outcome_result == SolverOutcome.SOLVED)
            return distTo.get(end);
        return 0;
    }

    @Override
    public int numStatesExplored() {
        return num_dequeue;
    }

    @Override
    public double explorationTime() {
        return total_time;
    }

}
