[TOC]

# 1. Data Structure

- 二维数组的行数和列数：

  - 行数：`arr.length()`；
  - 第 i 行的列数：`arr[i].length()`；

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



# 2. I/O

- 字符的大小写转换：
  - 大写转换为小写，加32；
  - 小写转换为大写，减32；
  - 注意：`char`加上`int`，则自动转换为`int`类型；

```java
if ((character > 'A') && (character < 'Z')) // 基本类型可直接比较
```

- 字符串的大小写转换：

```java
// *** method ***
.toLowerCase()
.toUpperCase()    
```



# 3. Java grammar

- `@SuppressWarnings("unchecked")`：使编译器对代码中的某些警告保持静默；