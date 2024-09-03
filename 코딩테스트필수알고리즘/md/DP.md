## DP : Dynamic Programming

- 이전의 값을 재활용하는 알고리즘
- 예시 : 1~10 숫자 중, 각각 이전 값들을 합한 값 구하기
- 이전의 값을 활용해서 시간 복잡도 줄일 수 있음

<br>

### Tip
- 어떻게 할지 모르겠을 때에는, 하나씩 그려보면서 규칙 찾기
- 점화식을 명확하게 세우고 코드 짜기

<br>

---

#### 11726 2xn 타일링
```text
### 아이디어
- A1 : 1, A2 : 2, A3 : 1 + 2
- An = A(n-1) + A(n-2)
- for 문으로 3부터 N까지 돌면서
- 이전 값과 그 이전 값을 더해서 저장 (이 때 10007로 나눈 나머지 값)

### 시간 복잡도
- for : N > O(N)

### 자료 구조
- 방법의 수 배열(An) : int[]
    - 최대값 : 10006 > INT 가능
```
```text
import sys
input = sys.stdin.readline

n = int(input())
rs = [0,1,2]

for i in range(3, n+1):
    rs.append((rs[i-1] + rs[i-2])%10007)

print(rs[n])
```