package 코딩테스트필수알고리즘.quiz;

import java.io.*;
import java.util.*;

// MST
// Prim alg.
public class N1197_최소스패닝트리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[V+1];
        List<Node>[] graph = new ArrayList[V+1];
        for (int i = 0; i < V+1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            graph[A].add(new Node(B, W));
            graph[B].add(new Node(A, W));
        }

        int ans = 0;
        Queue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(o1.weight, o2.weight);
        });
        pq.add(new Node(1, 0));
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            int node = now.node;
            int weight = now.weight;

            if (!visited[node]) {
                visited[node] = true;
                ans += weight;

                for (Node next : graph[node]) {
                    if (!visited[next.node]) {
                        pq.add(next);
                    }
                }
            }
        }

        bw.write(String.valueOf(ans));
        bw.newLine();
        bw.flush();
        bw.close();
    }
}

class Node {
    int node;
    int weight;

    Node(int node, int weight) {
        this.node = node;
        this.weight = weight;
    }
}