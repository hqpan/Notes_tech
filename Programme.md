[TOC]

# 1. Data Structure

- 二维数组的行数和列数：

  - 行数：`arr.length`；
  - 第 i 行的列数：`arr[i].length`；

- 动态数组：

  - 声明与创建：`ArrayList<Integer> ans = new ArrayList<>()`；
  - 向二维动态数组添加元素：

  ```java
  List<List<Integer>> ans = new ArrayList<>();
  List<Integer> newRow = new ArrayList<>();
  row.add(x);
  row.add(y);			// 生成行；
  ans.add(row);		// 将某一行添加到二维动态数组中；
  ```

  

- 堆栈；

```java
Stack<char> stack = new Stack<>();
// *** method ***
.push()
.pop()
.empty()
.peek()  // Looks at the object at the top of this stack without removing it from the stack.   
.search(Object o) // 返回从栈顶至当前元素的索引，从1开始计数；    
```

- 队列：

```java
Queue<String> queue = new LinkedList<>();
// method
.add()
.poll()	 // Retrieves and removes the head of this queue, or returns null if this queue is empty.  
.peek()  
.size()  // 该方法来自 LinkedList；
```

- 最小优先队列：
  
```java
PriorityQueue<Integer> queue = new PriorityQueue<>();
.add()
.poll()	 
.peek()  
.size()
```

- 词汇;
  
  - poll, vt. vi. & n. 投票，民意调查；
  - peek, vi. & n. 窥视；

# 2. 字符串

- 字符的大小写转换：
  - 大写转换为小写，加32；
  - 小写转换为大写，减32；
  - 记忆策略：先大后小；
  - 注意：`char`加上`int`，则自动转换为`int`类型，可使用显式类型转换将`int`转换为`char`；

```java
if ((character > 'A') && (character < 'Z')) // 基本类型可直接比较
```

- 字符串的大小写转换：

```java
// *** method ***
.toLowerCase()
.toUpperCase()    
```

- 创建格式化字符串;

```java
return String.format("%d-%d, %.2f", vertex1, vertex2, weight);
```



# 3. Java grammar

- `@SuppressWarnings("unchecked")`：使编译器对代码中的某些警告保持静默；
- 基本数据类型：
  - `int max = Integer.MAX_VALUE`；
  - `double max = Double.POSITIVE_INFINITY`；