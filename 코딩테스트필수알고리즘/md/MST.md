## MST : Minimum Spanning Tree

- Spanning Tree : 모든 노드가 연결된 트리
- MST : 최소의 비용으로 모든 노드가 연결된 트리
- MST 푸는 방법 : Kruskal or Prim
- Kruskal : 전체 간선 중 작은 것부터 연결
- ✅ Prim : 현재 연결된 트리에 이어진 간선 중 가장 작은 것을 추가

<br>

- heap
  - 최대값, 최소값을 빠르게 계산하기 위한 자료 구조
  - 이진 트리 구조
  - 처음에 저장할 때부터 최대값 or 최소값 결정하도록

<br>

### 핵심 코드
```text
heap = [[0,1]]

while heap:
    w, next_node = heapq.heappop(heap)
    if chk[next_node] == False:
        chk[next_node] = True
        rs += w
        for next_edge in edge[next_node]:
            if chk[next_edge[1]] == False:
                heapq.heappush(heap, next_edge)
```

<br>

### Tip
- 최소 스패닝 트리 코드는 그냥 외우기 ✅⭐⭐
- 중요한 건 해당 문제가 MST 문제인지 알아내는 능력
  - 모든 노드가 연결되도록 한다거나
  - 이미 연결된 노드를 최소의 비용으로 줄이기

<br>

---

#### 1197 최소 스패닝 트리
```text
### 아이디어
- 최소 스패닝 트리 기본 문제 (외우기)
- 간선을 인접 리스트 형태로 저장
- 시작점부터 힙에 넣기
- 힙이 빌 때까지,
    - 해당 노드 방문 안한 곳일 경우
    - 방문 체크, 비용 추가, 연결된 간선 새롭게 추가
    
### 시간 복잡도
- Edge 리스트에 저장 : O(E)
- Heap 안 모든 Edge 에 연결된 간선 확인 : O(E + E)
- 모든 간선 힙에 삽입 : O(ElogE)
- O(E + 2E + ElogE) = O(ElogE)

### 자료 구조
- Edge 저장 리스트 (int, int) [] // 무게, 다음 노드
    - 무게 최대 : 1e^6 > INT 가능
    - 정점 번호 최대 : 1e^4 > INT 가능
- 정점 방문 : bool[]
- MST 비용 : int
```
```text
import sys
import heapq
input = sys.stdin.readline

V,E = map(int, input().split())
edge = [[] for _ in range(V+1)]
chk = [False] * (V+1)
rs = 0
for i in range(E):
    a,b,c = map(int, input().split())
    edge[a].append([c,b])
    edge[b].append([c, a])

heap = [[0,1]]

while heap:
    w, each_node = heapq.heappop(heap)
    if chk[each_node] == False:
        chk[each_node] = True
        rs += w
        for next_edge in edge[each_node]:
            if chk[next_edge[1]] == False:
                heapq.heappush(heap, next_edge)

print(rs)
```