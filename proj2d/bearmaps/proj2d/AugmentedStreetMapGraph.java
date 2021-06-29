package bearmaps.proj2d;

import bearmaps.proj2ab.Point;
import bearmaps.proj2ab.WeirdPointSet;
import bearmaps.proj2c.streetmap.StreetMapGraph;
import bearmaps.proj2c.streetmap.Node;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {

    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        // You might find it helpful to uncomment the line below:
         List<Node> nodes = this.getNodes();
         Point p;
         points = new ArrayList<>();
         PointToNode = new HashMap<>();
         tries = new Trie();
         names = new HashMap<>();
         NameToNodes = new HashMap<>();
         String name;

         for (Node n : nodes) {
             if (neighbors(n.id()).size() != 0) {
                 p = new Point(n.lon(), n.lat());
                 points.add(p);
                 PointToNode.put(p, n);
             }
             if (n.name() != null) {
                 name = cleanString(n.name());
                 tries.insert(name);
                 names.put(name, n.name());

                 if (!NameToNodes.containsKey(name)) {
                     NameToNodes.put(name, new LinkedList<>());
                 }
                 NameToNodes.get(name).add(n);
             }
         }

         kdtree = new WeirdPointSet(points);
    }

    private List<Point> points;
    private HashMap<Point, Node> PointToNode;
    private WeirdPointSet kdtree;
    private Trie tries;
    private HashMap<String, String> names;
    private HashMap<String, List<Node>> NameToNodes;

    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        Point p = kdtree.nearest(lon, lat);
        return PointToNode.get(p).id();
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        List<String> x = tries.KeysWithPrefix(cleanString(prefix));
        return x;
    }

    public class Trie {
        private final Map<Character, Trie> nextNodes = new HashMap<>();
        private boolean isWordEnd = false;

        public Trie() {
        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            Trie current = this;
            Trie next;
            for (char c : word.toCharArray()) {
                next = current.nextNodes.getOrDefault(c, new Trie());
                current.nextNodes.put(c, next);
                current = next;
            }
            current.isWordEnd = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            Trie current = this;
            for (char c: word.toCharArray()) {
                if (!current.nextNodes.containsKey(c)) {
                    return false;
                }
                current = current.nextNodes.get(c);
            }
            return current.isWordEnd;
        }

        /**
         * Returns words in the trie that starts with the given prefix.
         */
        public List<String> KeysWithPrefix(String prefix) {
            List<String> x = new LinkedList<>();

            Trie current = this;
            for (char c: prefix.toCharArray()) {
                if (!current.nextNodes.containsKey(c)) {
                    return x;
                }
                current = current.nextNodes.get(c);
            }

            for (char c : current.nextNodes.keySet()) {
                colHelp(prefix+c, x, current.nextNodes.get(c));
            }

            return x;
        }
        public void colHelp(String s, List<String> x, Trie n) {
            if (n.isWordEnd) {
                x.add(names.get(s));
            }
            for (char c : n.nextNodes.keySet()) {
                colHelp(s+c, x, n.nextNodes.get(c));
            }
        }
    }


    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        List<Map<String, Object>> x = new LinkedList<>();

        for (Node n : NameToNodes.get(cleanString(locationName))) {
            Map<String, Object> results = new HashMap<>();
            results.put("lat", n.lat());
            results.put("lon", n.lon());
            results.put("name", n.name());
            results.put("id", n.id());
            x.add(results);
        }

        return x;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}
