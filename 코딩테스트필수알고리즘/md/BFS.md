- 그래프 탐색 : 어떤 것들이 연속해서 이어질 때, 모두 확인하는 방법
- Graph : Vertex(어떤 것) + Edge(이어지는 것)

<br>

### 그래프 탐색 종류
- BFS : Breadth-first search (너비 우선 탐색) - 자기 자식 우선 탐색
- DFS : Depth-first search (깊이 우선 탐색) - 자식의 자식 우선 탐색

<br>

### 아이디어
1. 시작점에 연결된 Vertex 찾기
2. 찾은 Vertex 를 Queue 에 저장
3. Queue 의 가장 먼저 것 뽑아서 반복

<br>

### 시간 복잡도
- 시간 복잡도란? : 알고리즘이 얼마나 오래 걸리는지
- BFS : O(V+E)

<br>

### 자료 구조
- 검색할 그래프
- 방문 여부 확인 (재방문 금지)
- Queue : BFS 실행

<br>

---

#### 1926 그림
```text
1. 아이디어
- 2중 for문 → 값 1 && 방문 X → BFS
- BFS 돌면서 그림 개수 +1, 최대값을 갱신

2. 시간 복잡도
- BFS : O(V+E)
- V : 500 * 500 // V = n * m
- E : 4 * 500 * 500 // 하나의 V 에 연결될 수 있는 것 최소 2개 ~ 최대 4개 → E = V * 4
- V+E : 5 * 250000 = 100만 < 2억 →→ 가능!

3. 자료 구조
- 그래프 전체 지도 : int[][]
- 방문 : bool[][]
- Queue(BFS)
```
```text
from collections import deque

import sys
input = sys.stdin.readline

n,m = map(int, input().split())
map = [list(map(int, input().split())) for _ in range(n)]
chk = [[False] * m for _ in range(n)]

dy = [0,1,0,-1]
dx = [1,0,-1,0]

def bfs(y, x):
    rs = 1
    q = deque()
    q.append((y, x))
    while q:
        ey, ex = q.popleft()
        for k in range(4):
            ny = ey + dy[k]
            nx = ex + dx[k]
            if 0<=ny<n and 0<=nx<m:
                if map[ny][nx] == 1 and chk[ny][nx] == False:
                    rs += 1
                    chk[ny][nx] = True
                    q.append((ny,nx))
    return rs

cnt = 0
maxv = 0
for j in range(n):
    for i in range(m):
        if map[j][i] == 1 and chk[j][i] == False:
            chk[j][i] = True
            cnt += 1 # 전체 그림 개수 +1
            maxv = max(maxv, bfs(j,i)) # BFS > 그림 크기를 구해주고 최대값 갱신

print(cnt)
print(maxv)
```