package TopologicalSorting;

import java.util.*;

/*
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

For example,
Given the following words in dictionary,

[ t --> f, w --> e, r --> t, e --> r
"wrt",
"wrf",
"er",
"ett",
"rftt"
]
The correct order is: "wertf".
 */
public class AlienDictionary {
    public static char[] getAlphabet(String[] sortedWords) {
        if (sortedWords == null || sortedWords.length == 0) {
            return new char[0];
        }

        Set<Character> nodes = new HashSet<>();
        for (String w : sortedWords) {
            addChars(nodes, w);
        }

        // map from a node to all its neighbours = those it has edges to them
        // { a --> {b, c} } = edges are: a-->b and a-->c
        Map<Character, Set<Character>> outgoingEdges = new HashMap<>();
        // get a relation (=edge) from pairs of adjacent words
        for (int i = 1; i < sortedWords.length; i++) {
            char[] relation = getRelation(sortedWords[i - 1], sortedWords[i]);
            if (relation != null) {
                addEdge(outgoingEdges, relation[0], relation[1]);
            }
        }

        List<Character> sortedChars = topologicalSort(nodes, outgoingEdges);

        char[] alphabet = new char[sortedChars.size()];
        int i = 0;
        for (char c : sortedChars) {
            alphabet[i++] = c;
        }
        return alphabet;
    }

    // make sure all characters in s are in chars set
    private static void addChars(Set<Character> chars, String s) {
        for (char c : s.toCharArray()) {
            chars.add(c);
        }
    }

    // w1 comes before w2 in lexicographic order
    // extract a relation between chars and return it
    // {x, y} means that x comes before y. null means we could not get a relation
    private static char[] getRelation(String w1, String w2) {
        int len1 = w1.length();
        int len2 = w2.length();
        for (int i = 0, len = Math.min(len1, len2); i < len; i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                return new char[]{w1.charAt(i), w2.charAt(i)};
            }
        }
        // could not find a relation
        return null;
    }

    private static void addEdge(Map<Character, Set<Character>> outgoingEdges, char from, char to) {
        Set<Character> neighbours = outgoingEdges.get(from);
        if (neighbours == null) {
            neighbours = new HashSet<>();
            outgoingEdges.put(from, neighbours);
        }
        neighbours.add(to);
    }

    // for each unvisited node, run DFS starting from that node.
    // add nodes we're done with to the sortedNodes list
    private static List<Character> topologicalSort(Set<Character> nodes, Map<Character, Set<Character>> outgoingEdges) {
        LinkedList<Character> sortedNodes = new LinkedList<>();
        Set<Character> visited = new HashSet<>();
        for (Character node : nodes) {
            if (!visited.contains(node)) {
                dfs(sortedNodes, outgoingEdges, visited, node);
            }
        }
        return sortedNodes;
    }

    private static void dfs(LinkedList<Character> sortedNodes, Map<Character, Set<Character>> outgoingEdges,
                            Set<Character> visited, Character curNode) {

        visited.add(curNode);
        for (Character neighbour : outgoingEdges.getOrDefault(curNode, Collections.emptySet())) {
            if (!visited.contains(neighbour)) {
                dfs(sortedNodes, outgoingEdges, visited, neighbour);
            }
        }
        sortedNodes.addFirst(curNode); // instead of this, can use stack and add elements in it and then print the stack from top.
    }
}
