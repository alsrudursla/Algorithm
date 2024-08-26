## DFS
- 그래프 탐색 종류 : BFS(형제 우선), DFS(자식 우선)
- Depth-first search
- 깊이 우선 탐색
- Stack, 재귀 함수

<br>

- 그래프 탐색은 BFS 로도 충분히 해결할 수 있다
- DFS 로 재귀 함수를 사용해보자
- 재귀 함수는 백트래킹에서 아주 중요하다

<br>

### 재귀 함수
- 자기 자신을 다시 호출하는 함수
- 주의할 점
  - 재귀 함수가 종료되는 시점 반드시 명시
  - 재귀 함수의 깊이가 너무 깊어지면 Stack Overflow
- DFS, 백트래킹에서 주로 사용

<br>

### 아이디어
1. 시작점에 연결된 Vertex 찾기
2. 연결된 Vertex 를 계속해서 찾음 (끝날 때까지)
3. 더이상 연결된 Vertex 가 없을 때까지

<br>

### 시간 복잡도
- O(V+E)

<br>

### 자료 구조
- 검색할 그래프 : 2차원 배열
- 방문 여부 확인 : 2차원 배열 (재방문 금지)

<br>

---

#### 2667 단지번호붙이기
```text
1. 아이디어
- 2중 for, 값 1 && 방문X => DFS
- DFS 를 통해 찾은 값을 저장 후 정렬해서 출력

2. 시간 복잡도
- DFS : O(V+E)
- V, E : N^2, 4N^2
- V+E : 5N^2 ~= N^2 ~= 625 >> 가능

3. 자료 구조
- 그래프 저장 : int[][]
- 방문 여부 : bool[][]
- 결과값 : int[]
```
```text
import sys
input = sys.stdin.readline

N = int(input())
map = [list(map(int, input().strip())) for _ in range(N)]
chk = [[False] * N for _ in range(N)]
result = []
each = 0
dy = [0,1,0,-1]
dx = [1,0,-1,0]

def dfs(y, x):
    global each
    each += 1 # 연속되는 크기
    for k in range(4):
        ny = y + dy[k]
        nx = x + dx[k]
        if 0<=ny<N and 0<=nx<N:
            if map[ny][nx] == 1 and chk[ny][nx] == False:
                chk[ny][nx] = True
                dfs(ny, nx) # 재귀

for j in range(N):
    for i in range(N):
        if map[j][i] == 1 and chk[j][i] == False:
            chk[j][i] = True # 방문 체크 표시
            each = 0
            dfs(j,i) # DFS 로 크기 구하기
            result.append(each) # 크기를 결과 리스트에 넣기

result.sort()
print(len(result))
for i in result:
    print(i)
```