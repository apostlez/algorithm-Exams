package codejam2018_1st;

import java.util.*;

public class Problem_4_editorial {
    public static void main(String[] args) {
        Marathon marathon = new Marathon();
        System.out.println(marathon.solve());
    }
}

class Marathon {
    private int n = 0;
    private int m = 0;
    private int k = 0;

    Vector<Node> nodes = null;

    public Marathon() {
        Scanner scanner = new Scanner(System.in);

        n = scanner.nextInt();
        m = scanner.nextInt();
        k = scanner.nextInt();

        nodes = new Vector<>();

        for (int i = 0; i < n; i++) {
            nodes.add(new Node(i));
        }

        for (int i = 0; i < m; i++) {
            int from = scanner.nextInt() - 1;
            int to = scanner.nextInt() - 1;
            int c = scanner.nextInt();
            int t = scanner.nextInt();

            Edge edge = new Edge(from, to, c, t);
            nodes.get(from).addEdge(edge);
            nodes.get(to).addEdge(edge);
        }
    }

    public int solve() {
        int min = 1;
        int max = 32623;

        while (min + 1 < max) {
            int mid = (min + max) / 2;

            if (hasEnoughMoney(mid)) {
                min = mid;
            } else {
                max = mid;
            }
        }

        return min;
    }

    public boolean hasEnoughMoney(int person) {
        for (Node node : nodes) {
            node.init();
        }

        Queue<Node> queue = new PriorityQueue<>();
        queue.offer(nodes.get(0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            node.setVisited(true);

            for (Edge edge : node.getEdges()) {
                Node nextNode = nodes.get(edge.getAdjustNodeIndex(node.getIndex()));

                if (!nextNode.isVisited()) {
                    long price = 0;
                    if (edge.getT() < person) {
                        price = (person - edge.getT());
                        price *= (person - edge.getT());
                        price *= edge.getC();
                    }

                    if (nextNode.getPrice() > node.getPrice() + price) {
                        nextNode.setPrice(node.getPrice() + price);
                    }

                    if (nextNode.getPrice() > k) {
                        continue;
                    }

                    queue.remove(nextNode);
                    queue.offer(nextNode);
                }
            }
        }

        return nodes.get(n - 1).getPrice() <= k;
    }
}

class Node implements Comparable<Node> {
    private int index = 0;
    private long price = 0;
    private boolean visited = false;
    private List<Edge> edges = null;

    public Node(int index) {
        this.index = index;
        edges = new ArrayList<>();
    }

    public int getIndex() {
        return index;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getPrice() {
        return price;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited() {
        return visited;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void init() {
        if (index == 0) {
            price = 0;
        } else {
            price = 2000000000;
        }

        visited = false;
    }

    @Override
    public int compareTo(Node node) {
        if (price > node.price) {
            return 1;
        } else if (price < node.price) {
            return -1;
        } else {
            return 0;
        }
    }
}

class Edge {
    private int from = 0;
    private int to = 0;
    private int c = 0;
    private int t = 0;

    public Edge(int from, int to, int c, int t) {
        this.from = Math.min(from, to);
        this.to = Math.max(from, to);
        this.c = c;
        this.t = t;
    }

    public int getC() {
        return c;
    }

    public int getT() {
        return t;
    }

    public int getAdjustNodeIndex(int index) {
        return from == index ? to : from;
    }
}
