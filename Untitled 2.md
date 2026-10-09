# 谓词逻辑补充作业：答案与解析

> 约定：论域默认非空；真值用 $1$ 表示真、$0$ 表示假。$\forall$ 的展开是合取，$\exists$ 的展开是析取。改名、Skolem 形等结果通常不唯一，下列写法只要满足等价（或可满足性等价）要求即可。第 $8$、$10$ 题中 $A \Rightarrow B$ 表示在任意解释下 $A$ 为真时 $B$ 必为真。

---

## 第 1 题 有限论域中消去量词

设论域 $D=\{0,1,2\}$。把下列公式用不含量词的公式表示出来。

在有限论域上：

$$(\forall x)F(x) \iff F(0) \land F(1) \land F(2), \qquad (\exists x)F(x) \iff F(0) \lor F(1) \lor F(2).$$

### 1) $(\forall x)P(x) \land (\exists x)Q(x)$

**答案：**

$$[P(0) \land P(1) \land P(2)] \land [Q(0) \lor Q(1) \lor Q(2)].$$

**解析：** 左边全称量词对 $0,1,2$ 逐个取合取，右边存在量词对 $0,1,2$ 逐个取析取，中间的 $\land$ 保留。

### 2) $(\forall x)[P(x) \to Q(x)]$

**答案：**

$$[P(0) \to Q(0)] \land [P(1) \to Q(1)] \land [P(2) \to Q(2)].$$

**解析：** 先把 $P(x) \to Q(x)$ 看作整体 $F(x)$，再按全称量词展开为三个实例的合取。注意蕴涵号在每个实例内部，不能把 $\to$ 提到合取外面。

### 3) $(\forall x)[\sim P(x)] \lor (\exists x)Q(x)$

**答案：**

$$[\sim P(0) \land \sim P(1) \land \sim P(2)] \lor [Q(0) \lor Q(1) \lor Q(2)].$$

**解析：** $(\forall x)[\sim P(x)]$ 展开为 $\sim P(0) \land \sim P(1) \land \sim P(2)$；$(\exists x)Q(x)$ 展开为析取。易错点：$\sim$ 只作用在紧跟的 $P(x)$ 上，展开后每个实例各带一个 $\sim$。

---

## 第 2 题 约束变元、自由变元与辖域

> 判读原则：量词的辖域是它后面紧跟的那个公式（题目用括号或方括号标出的部分）；在辖域之内、被该量词约束的出现是约束出现，在一切量词辖域之外的出现是自由出现。同一个字母可以在不同出现处一处约束、一处自由。

### 1) $(\forall x)P(x) \to Q(x)$

- **辖域：** $(\forall x)$ 的辖域是 $P(x)$，到右括号为止，不包括 $\to Q(x)$。
- **约束出现：** $P(x)$ 中的 $x$。
- **自由出现：** $Q(x)$ 中的 $x$。
- 结论：字母 $x$ 既有约束出现，又有自由出现。

### 2) $(\forall x)[P(x) \land Q(x)] \to ((\forall x)P(x) \land Q(x))$

- **辖域：** 左边 $(\forall x)$ 的辖域是 $[P(x) \land Q(x)]$；右边 $(\forall x)$ 的辖域只是 $P(x)$。
- **约束出现：** 左边 $P(x)$、$Q(x)$ 中的 $x$（受左边量词约束）；右边 $P(x)$ 中的 $x$（受右边量词约束）。
- **自由出现：** 最右边 $Q(x)$ 中的 $x$，因为它既不在左边量词的辖域内，也不在右边量词的辖域（右边辖域只有 $P(x)$）内。
- 结论：$x$ 既有约束出现，又有自由出现。

### 3) $(\exists x)(\exists y)[P(x,y) \land Q(a)] \lor (\forall z)R(x,z)$

- **辖域：** $(\exists x)$ 的辖域是 $(\exists y)[P(x,y) \land Q(a)]$；$(\exists y)$ 的辖域是 $[P(x,y) \land Q(a)]$；$(\forall z)$ 的辖域是 $R(x,z)$。
- **约束出现：** $P(x,y)$ 中的 $x$（受 $\exists x$ 约束）、$y$（受 $\exists y$ 约束）；$R(x,z)$ 中的 $z$（受 $\forall z$ 约束）。
- **自由出现：** $R(x,z)$ 中的 $x$。因为 $\lor$ 右边的部分已经在 $(\exists x)$ 的辖域之外了。
- **注意：** $Q(a)$ 中的 $a$ 是个体常元，不是变元，谈不上约束或自由；它虽然写在 $(\exists y)$ 的辖域里，但 $a$ 不受任何量词约束。

---

## 第 3 题 变元代换（改名）

要求：改名之后，任何变元不能既是约束变元又是自由变元。办法是把约束变元改成不与自由变元重名的新字母，自由变元原样保留。结果不唯一。

### 1) $(\forall x)(\exists y)[P(x,y) \to Q(y,z)] \lor (\forall z)R(x,y,z)$

**先清点：** 左半部分中 $x,y$ 被约束，$z$ 自由（在 $Q(y,z)$ 中）；右半部分中 $z$ 被 $(\forall z)$ 约束，$x,y$ 自由（在 $R(x,y,z)$ 中）。所以自由变元集合是 $\{x,y,z\}$，约束变元必须全部换成新字母。

**答案（一种改法）：**

$$(\forall u)(\exists v)[P(u,v) \to Q(v,z)] \lor (\forall w)R(x,y,w).$$

**检查：** 约束变元为 $u,v,w$，自由变元为 $z$（左）和 $x,y$（右），两边不再重名。

### 2) $((\forall x)[P(x) \to R(x)] \lor Q(x)) \land ((\exists x)R(x) \to (\exists z)S(x,z))$

**先清点：** 左边 $(\forall x)$ 辖域内的 $x$ 是约束的，$Q(x)$ 中的 $x$ 是自由的；右边 $(\exists x)$ 辖域内的 $x$ 是约束的，$S(x,z)$ 中的 $x$ 是自由的，$z$ 只被约束。自由变元只有 $x$。

**答案（一种改法）：**

$$((\forall u)[P(u) \to R(u)] \lor Q(x)) \land ((\exists v)R(v) \to (\exists z)S(x,z)).$$

**检查：** 约束变元为 $u,v,z$，自由变元只有 $x$，不再重名。这里 $z$ 本来就不与自由变元冲突，可以保留；当然把 $z$ 一并改成 $w$ 也对。

> 易错点：改名只能改约束出现（量词本身和它辖域内的同名出现），自由出现一个都不能动，否则公式的意思就变了。

---

## 第 4 题 在给定解释下求真值

### 1) $A=(\forall x)[P(x) \lor Q(x)] \land R(a)$

解释：$D=\{1,2,3\}$，$P(x)$：$x^{2}+x=2$；$Q(x)$：$x$ 是素数；$R(x)$：$x<3$；$a=1$。

先列表（$1$ 不是素数）：

| $x$ | $P(x)$：$x^{2}+x=2$ | $Q(x)$：$x$ 是素数 | $P(x) \lor Q(x)$ |
|---|---|---|---|
| $1$ | $1+1=2$，真 $1$ | 假 $0$ | $1$ |
| $2$ | $4+2=6 \neq 2$，假 $0$ | 真 $1$ | $1$ |
| $3$ | $9+3=12 \neq 2$，假 $0$ | 真 $1$ | $1$ |

**答案：** $(\forall x)[P(x) \lor Q(x)]$ 三个实例全为真，故为 $1$；$R(a)=R(1)$ 即 $1<3$，为 $1$。所以 $A = 1 \land 1 = 1$，即 **$A$ 为真**。

### 2) 四个公式在 $D=\{2,3\}$ 下的真值

解释：$a=3$，$b=2$，$f(2)=3$，$f(3)=2$，$P(2,2)=0$，$P(2,3)=0$，$P(3,2)=1$，$P(3,3)=1$。

**(i) $A=P(a,f(b)) \land P(b,f(a))$**

$f(b)=f(2)=3$，所以 $P(a,f(b))=P(3,3)=1$；$f(a)=f(3)=2$，所以 $P(b,f(a))=P(2,2)=0$。

**答案：** $A = 1 \land 0 = 0$，为假。

**(ii) $B=(\forall x)(\exists y)P(y,x)$**

逐个 $x$ 检查是否存在 $y$ 使 $P(y,x)$ 为真：$x=2$ 时取 $y=3$，$P(3,2)=1$，成立；$x=3$ 时取 $y=3$，$P(3,3)=1$，成立。两个 $x$ 都成立。

**答案：** $B=1$，为真。

**(iii) $C=(\exists y)(\forall x)P(y,x)$**

逐个 $y$ 检查是否对一切 $x$ 都有 $P(y,x)$ 为真：$y=2$ 时 $P(2,2)=0$，不成立；$y=3$ 时 $P(3,2)=1$ 且 $P(3,3)=1$，成立。存在这样的 $y$。

**答案：** $C=1$，为真。

**(iv) $E=(\forall x)(\forall y)[P(x,y) \to P(f(x),f(y))]$**

全称公式只要找到一个反例即为假。取 $x=3$，$y=2$：$P(3,2)=1$，而 $f(3)=2$，$f(2)=3$，$P(f(3),f(2))=P(2,3)=0$，于是 $1 \to 0 = 0$。

**答案：** $E=0$，为假。

> 提醒：$B$ 与 $C$ 在这个解释下恰好同为真，但 $(\forall x)(\exists y)$ 与 $(\exists y)(\forall x)$ 一般**不等值**：后者要求存在一个统一的 $y$ 对所有 $x$ 都成立，要求更强。本题的 $P$ 恰好在 $y=3$ 那一行全为 $1$，所以 $C$ 也成立。

---

## 第 5 题 论域为整数集时判定公式类型

论域为整数集 $\mathbb{Z}$，符号取通常算术含义。四个公式都是没有自由变元的语句，所以先逐一判定它们在 $\mathbb{Z}$ 中的真值；分类说明见本节末尾的注。

### (1) $(\forall x)[x>-10 \land x^{2} \geq 0]$

**答案：在 $\mathbb{Z}$ 中为假。**

**解析：** 取反例 $x=-11$：$-11>-10$ 为假，故合取为假，全称语句为假。注意 $x^{2} \geq 0$ 这一半对一切整数都成立，出问题的是前一半。

### (2) $(\exists x)[2^{x}>8 \land x^{2}-6x+5 \leq 0]$

**答案：在 $\mathbb{Z}$ 中为真。**

**解析：** $x^{2}-6x+5=(x-1)(x-5) \leq 0$ 给出 $1 \leq x \leq 5$；$2^{x}>8=2^{3}$ 给出 $x>3$。取见证 $x=4$（或 $x=5$）：$2^{4}=16>8$ 且 $16-24+5=-3 \leq 0$，两半同时成立，存在语句为真。

### (3) $(\forall x)(\exists y)[x+y=1024]$

**答案：在 $\mathbb{Z}$ 中为真。**

**解析：** 对任意整数 $x$，取 $y=1024-x$，仍是整数，且 $x+y=1024$。关键点：$y$ 可以依赖 $x$ 来选，这正是 $(\forall x)(\exists y)$ 的含义。

### (4) $(\exists y)(\forall x)[xy<10 \lor x+y \geq 2]$

**答案：在 $\mathbb{Z}$ 中为真。**

**解析：** 取见证 $y=0$（$y$ 必须先选定、且对一切 $x$ 都管用）。此时 $xy=0<10$ 对一切 $x$ 成立，析取的左半恒真，右半 $x+y \geq 2$ 是否成立都无所谓，故对一切 $x$ 析取为真。易错点：这题看起来像是要兼顾很大的正 $x$ 和负 $x$，其实 $y=0$ 一举把乘积项清零了。

> **关于“永真式 / 矛盾式 / 可满足公式”的分类注。** 上面四式都是闭语句，在固定的 $\mathbb{Z}$ 解释下各有确定的真值：(2)(3)(4) 为真，(1) 为假。若按“在 $\mathbb{Z}$ 这个解释下”来分类：(2)(3)(4) 在 $\mathbb{Z}$ 上为真，(1) 在 $\mathbb{Z}$ 上为假（取 $x=-11$ 即被推翻）。但若按数理逻辑的严格定义——永真式须对**一切**解释和论域都为真、矛盾式须对一切解释都为假——则四式都不是逻辑永真式，也不是逻辑矛盾式：它们的真假依赖整数的算术性质（例如把论域换成 $\{0,1\}$，第 (1) 式反而为真；换成自然数集，第 (3) 式在 $x>1024$ 时就取不到合适的 $y$ 了）。所以严格地说，四式都是**可满足式**（至少在 $\mathbb{Z}$ 或其他某个解释下可以为真），其中 (1) 在本题给定的 $\mathbb{Z}$ 解释下为假、其余三式在 $\mathbb{Z}$ 下为真。考试若只要求在 $\mathbb{Z}$ 下判真假，按前面的真值表作答即可。

---

## 第 6 题 证明等值式

用到的规律：蕴涵等值式 $A \to B \iff \sim A \lor B$；量词否定律 $\sim(\forall x)A \iff (\exists x)\sim A$，$\sim(\exists x)A \iff (\forall x)\sim A$；量词辖域扩张律（当公式 $B$ 中不含变元 $x$ 的自由出现时，量词可以越过 $\land$、$\lor$ 把 $B$ 吸收进辖域或移出辖域）；$\forall$ 对 $\land$、$\exists$ 对 $\lor$ 的分配律。以下默认论域非空。

### 1) $(\forall x)(\forall y)[P(x) \to Q(y)] \iff (\exists x)P(x) \to (\forall y)Q(y)$

**证明：**

$$(\forall x)(\forall y)[P(x) \to Q(y)] \iff (\forall x)(\forall y)[\sim P(x) \lor Q(y)]$$

对内层先固定 $x$：$\sim P(x)$ 中不含 $y$，由辖域扩张律 $(\forall y)[\sim P(x) \lor Q(y)] \iff \sim P(x) \lor (\forall y)Q(y)$，得

$$\iff (\forall x)[\sim P(x) \lor (\forall y)Q(y)].$$

再看外层：$(\forall y)Q(y)$ 中不含 $x$，再由辖域扩张律 $(\forall x)[\sim P(x) \lor C] \iff (\forall x)\sim P(x) \lor C$（这里 $C=(\forall y)Q(y)$），得

$$\iff (\forall x)\sim P(x) \lor (\forall y)Q(y) \iff \sim(\exists x)P(x) \lor (\forall y)Q(y)$$

$$\iff (\exists x)P(x) \to (\forall y)Q(y).$$

证毕。直观读法：左边说“任取一对 $(x,y)$，只要 $P(x)$ 成立就得有 $Q(y)$”，这等价于“只要有某个 $x$ 使 $P(x)$ 成立，那么每个 $y$ 都得满足 $Q(y)$”。

### 2) $(\exists x)(\exists y)[P(x) \to Q(y)] \iff (\forall x)P(x) \to (\exists y)Q(y)$

**证明：**

$$(\exists x)(\exists y)[P(x) \to Q(y)] \iff (\exists x)(\exists y)[\sim P(x) \lor Q(y)]$$

固定 $x$ 看内层：$\sim P(x)$ 中不含 $y$，由辖域扩张律 $(\exists y)[\sim P(x) \lor Q(y)] \iff \sim P(x) \lor (\exists y)Q(y)$，得

$$\iff (\exists x)[\sim P(x) \lor (\exists y)Q(y)].$$

再看外层：$(\exists y)Q(y)$ 中不含 $x$，由辖域扩张律 $\iff (\exists x)\sim P(x) \lor (\exists y)Q(y)$，再由量词否定律 $(\exists x)\sim P(x) \iff \sim(\forall x)P(x)$，得

$$\iff \sim(\forall x)P(x) \lor (\exists y)Q(y) \iff (\forall x)P(x) \to (\exists y)Q(y).$$

证毕。注意此式用了论域非空：左边取到见证对之后，才能保证另一侧的量词也有对象可取。

### 3) $\sim(\exists y)(\forall x)P(x,y) \iff (\forall y)(\exists x)[\sim P(x,y)]$

**证明：** 量词否定律从外往里用两次：

$$\sim(\exists y)(\forall x)P(x,y) \iff (\forall y)\sim(\forall x)P(x,y) \iff (\forall y)(\exists x)\sim P(x,y).$$

证毕。读法：左边说“不存在一个 $y$ 能让所有 $x$ 都满足 $P$”，右边说“对每个 $y$，都至少有一个 $x$ 让 $P$ 不成立”，说的正是同一件事。

---

## 第 7 题 前束范式与 Skolem 范式

方法：先用蕴涵等值式消去 $\to$，把 $\sim$ 推到原子公式前，再把量词逐个提到最前面（前提是先改名，避免变元冲突）。得到前束范式后求 Skolem 范式：按前束形中从左到右的顺序消去存在量词——某个 $\exists$ 变量前面有几个全称量词，它就换成以那几个全称变量为参数的 Skolem 函数；前面没有全称量词时换成 Skolem 常量。注意：前束范式与原式**等值**；Skolem 范式与原式一般**不等值**，只保持可满足性等价，这正是消解法所需要的。

### 1) $\sim((\forall x)P(x) \to (\exists y)P(y))$

**化前束形：**

$$\sim((\forall x)P(x) \to (\exists y)P(y)) \iff (\forall x)P(x) \land \sim(\exists y)P(y) \iff (\forall x)P(x) \land (\forall y)\sim P(y)$$

$$\iff (\forall x)(\forall y)[P(x) \land \sim P(y)].$$

**前束范式：** $(\forall x)(\forall y)[P(x) \land \sim P(y)]$。

**Skolem 范式：** 前束形中已无存在量词，所以 Skolem 范式与前束范式相同：$(\forall x)(\forall y)[P(x) \land \sim P(y)]$。

### 2) $\sim((\forall x)P(x) \to (\exists y)(\forall z)Q(y,z))$

**化前束形：**

$$\sim((\forall x)P(x) \to (\exists y)(\forall z)Q(y,z)) \iff (\forall x)P(x) \land \sim(\exists y)(\forall z)Q(y,z)$$

$$\iff (\forall x)P(x) \land (\forall y)(\exists z)\sim Q(y,z) \iff (\forall x)(\forall y)(\exists z)[P(x) \land \sim Q(y,z)].$$

**前束范式：** $(\forall x)(\forall y)(\exists z)[P(x) \land \sim Q(y,z)]$。

**Skolem 范式：** $\exists z$ 前面有 $\forall x$、$\forall y$ 两个全称量词，把 $z$ 换成 Skolem 函数 $f(x,y)$：

$$(\forall x)(\forall y)[P(x) \land \sim Q(y, f(x,y))].$$

### 3) $(\forall x)(\forall y)[(\exists z)P(x,y,z) \land ((\exists u)Q(x,u) \land (\exists v)Q(y,v))]$

**化前束形：** 三个存在量词 $z,u,v$ 的辖域互不重叠、字母也不冲突，可以依次提到 $\forall x$、$\forall y$ 之后：

$$(\forall x)(\forall y)(\exists z)(\exists u)(\exists v)[P(x,y,z) \land Q(x,u) \land Q(y,v)].$$

**前束范式：** $(\forall x)(\forall y)(\exists z)(\exists u)(\exists v)[P(x,y,z) \land Q(x,u) \land Q(y,v)]$。

**Skolem 范式：** $z,u,v$ 前面都已有 $\forall x$、$\forall y$，分别换成 Skolem 函数 $f(x,y)$、$g(x,y)$、$h(x,y)$：

$$(\forall x)(\forall y)[P(x, y, f(x,y)) \land Q(x, g(x,y)) \land Q(y, h(x,y))].$$

说明：按前束形顺序机械地做，$u$ 也以 $(x,y)$ 为参数；从语义上 $Q(x,u)$ 中的 $u$ 其实只需依赖 $x$，写成 $g(x)$ 也对。Skolem 函数的选法不唯一，但参数必须把该存在量词之前出现的全称变量都包含进去（可以多，不可少）。

### 4) $(\forall x)[\sim P(x,0) \to ((\exists y)P(y,f(x)) \land (\forall z)Q(x,z))]$

**化前束形：** 先消蕴涵：$\sim P(x,0) \to A \iff P(x,0) \lor A$。在 $(\forall x)$ 内部，$P(x,0)$ 不含 $y,z$，可把 $(\exists y)$ 与 $(\forall z)$ 依次提出到析取之外：

$$(\forall x)(\exists y)(\forall z)[P(x,0) \lor (P(y,f(x)) \land Q(x,z))].$$

**前束范式：** $(\forall x)(\exists y)(\forall z)[P(x,0) \lor (P(y,f(x)) \land Q(x,z))]$。

**Skolem 范式：** $\exists y$ 前面只有 $\forall x$，把 $y$ 换成一元 Skolem 函数 $g(x)$；再把母式化成合取范式（对 $\lor$ 分配）：$P(x,0) \lor (P \land Q)$ 等值于 $[P(x,0) \lor P] \land [P(x,0) \lor Q]$。得

$$(\forall x)(\forall z)[(P(x,0) \lor P(g(x), f(x))) \land (P(x,0) \lor Q(x,z))].$$

若老师只要求“消去存在量词”而不要求母式为合取范式，写成 $(\forall x)(\forall z)[P(x,0) \lor (P(g(x),f(x)) \land Q(x,z))]$ 也可以，但做消解时通常要继续分配成上面两个子句。

---

## 第 8 题 证明蕴含式

以下均在任意解释下论证：设解释任意给定，前提为真，推出结论为真。前提、结论中出现的自由变元按同一赋值理解（第 (3) 小题的结论 $P(x)$ 中的 $x$ 即在该赋值下取定的那个个体）。

### (1) $(\exists x)(\exists y)[P(x) \land Q(y)] \Rightarrow (\exists x)P(x)$

**证明：** 前提为真，意为存在个体 $c,d$ 使 $P(c) \land Q(d)$ 为真，于是 $P(c)$ 为真。已经找到使 $P$ 成立的个体 $c$，故 $(\exists x)P(x)$ 为真。证毕。

### (2) $\sim((\exists x)P(x) \land Q(a)) \Rightarrow (\exists x)P(x) \to \sim Q(a)$

**证明：** 记 $A=(\exists x)P(x)$，$B=Q(a)$。由命题逻辑等值式：$\sim(A \land B) \iff \sim A \lor \sim B \iff A \to \sim B$。所以前提与结论实际**等值**，蕴含当然成立（这小题本质是德摩根律加蕴涵等值式）。证毕。

### (3) $(\forall x)[\sim P(x) \to Q(x)] \land (\forall x)[\sim Q(x)] \Rightarrow P(x)$

**证明：** 设 $x$ 在赋值下取个体 $d$。由第二个前提对 $d$ 用全称消去，得 $\sim Q(d)$；由第一个前提对 $d$ 用全称消去，得 $\sim P(d) \to Q(d)$。若 $\sim P(d)$ 成立，则由假言推理得 $Q(d)$，与 $\sim Q(d)$ 矛盾。故 $\sim P(d)$ 不成立，即 $P(d)$ 成立。这正是结论 $P(x)$ 在该赋值下的含义。证毕。

用规律说话：$\sim P(d) \to Q(d)$ 与 $\sim Q(d)$ 由拒取式（或假言三段论的逆否形式）直接得 $\sim\sim P(d)$，即 $P(d)$。

### (4) $(\forall x)[P(x) \lor Q(x)] \land (\forall x)[\sim P(x)] \Rightarrow (\forall x)Q(x)$

**证明：** 任取个体 $d$。由前提得 $P(d) \lor Q(d)$ 与 $\sim P(d)$（两次全称消去）。由析取三段论（$P \lor Q$ 加 $\sim P$ 推 $Q$）得 $Q(d)$。$d$ 是任意取的，故 $(\forall x)Q(x)$ 成立。证毕。

### (5) $(\exists x)[P(x) \to \sim Q(x)] \land (\forall x)P(x) \Rightarrow \sim(\forall x)Q(x)$

**证明：** 由第一个前提，存在个体 $c$ 使 $P(c) \to \sim Q(c)$ 为真。由第二个前提对 $c$ 用全称消去，得 $P(c)$ 为真。假言推理得 $\sim Q(c)$ 为真，于是 $(\exists x)\sim Q(x)$ 为真。再由量词否定律，$(\exists x)\sim Q(x) \iff \sim(\forall x)Q(x)$，结论成立。证毕。

---

## 第 9 题 演绎证明

使用的推理规则：全称消去（US）、全称引入（UG）、存在消去（ES，取见证个体，并要求该个体是新出现的、不在已有公式中自由出现）、存在引入（EG）、假言推理（MP）、附加前提证明法（CP，即证明蕴涵时先把前件设为附加前提）、间接证明法（反证法）。编号后注明依据。

### (1) $(\exists x)[P(x) \to Q(x)] \Rightarrow (\forall x)P(x) \to (\exists x)Q(x)$

**演绎证明：**

- ① $(\exists x)[P(x) \to Q(x)]$ —— 前提
- ② $(\forall x)P(x)$ —— 附加前提（CP，最后要证的是蕴涵式）
- ③ $P(c) \to Q(c)$ —— ES，由 ①，$c$ 为新个体（见证）
- ④ $P(c)$ —— US，由 ②
- ⑤ $Q(c)$ —— MP，由 ③④
- ⑥ $(\exists x)Q(x)$ —— EG，由 ⑤
- ⑦ $(\forall x)P(x) \to (\exists x)Q(x)$ —— CP，由 ②—⑥

合法性说明：$c$ 不在前提 ① 和附加前提 ② 中自由出现，最后结论 ⑥ 中也不含 $c$，所以 ES 的引进和消去都合法。

### (2) $(\forall x)P(x) \to (\forall x)Q(x) \Rightarrow (\exists x)[P(x) \to Q(x)]$

用间接证明法：假设结论不成立，推出矛盾。用到论域非空（总能取到任意个体 $u,v$）。

**演绎证明：**

- ① $(\forall x)P(x) \to (\forall x)Q(x)$ —— 前提
- ② $\sim(\exists x)[P(x) \to Q(x)]$ —— 假设（间接证明）
- ③ $(\forall x)\sim[P(x) \to Q(x)]$ —— 量词否定律，由 ② 置换
- ④ $(\forall x)[P(x) \land \sim Q(x)]$ —— 由 ③ 置换，用 $\sim(A \to B) \iff A \land \sim B$
- ⑤ $P(u) \land \sim Q(u)$ —— US，由 ④，$u$ 为任意个体
- ⑥ $P(u)$ —— 化简（$\land$ 消去），由 ⑤
- ⑦ $(\forall x)P(x)$ —— UG，由 ⑥（$u$ 任意，且不在假设、前提中自由出现）
- ⑧ $(\forall x)Q(x)$ —— MP，由 ①⑦
- ⑨ $P(v) \land \sim Q(v)$ —— US，由 ④，另取任意个体 $v$
- ⑩ $\sim Q(v)$ —— 化简，由 ⑨
- ⑪ $Q(v)$ —— US，由 ⑧
- ⑫ $Q(v) \land \sim Q(v)$ —— 矛盾，由 ⑩⑪

假设 ② 导致矛盾，故 $(\exists x)[P(x) \to Q(x)]$ 成立。证毕。

### (3) $(\forall x)[P(x) \to Q(x)],\ (\forall x)[R(x) \to \sim Q(x)] \Rightarrow (\forall x)[R(x) \to \sim P(x)]$

**演绎证明：** 取任意个体 $u$，在其上做链式推理，最后 UG 收尾；证 $\sim P(u)$ 时内层再用一次间接证明。

- ① $(\forall x)[P(x) \to Q(x)]$ —— 前提
- ② $(\forall x)[R(x) \to \sim Q(x)]$ —— 前提
- ③ $P(u) \to Q(u)$ —— US，由 ①，$u$ 任意
- ④ $R(u) \to \sim Q(u)$ —— US，由 ②
- ⑤ $R(u)$ —— 附加前提（CP，证内层蕴涵）
- ⑥ $\sim Q(u)$ —— MP，由 ④⑤
- ⑦ $P(u)$ —— 假设（间接证明 $\sim P(u)$）
- ⑧ $Q(u)$ —— MP，由 ③⑦
- ⑨ 矛盾 —— 由 ⑥⑧，故假设 ⑦ 不成立，得 $\sim P(u)$
- ⑩ $R(u) \to \sim P(u)$ —— CP，由 ⑤—⑨
- ⑪ $(\forall x)[R(x) \to \sim P(x)]$ —— UG，由 ⑩（$u$ 任意）

证毕。实质就是：$R \to \sim Q$ 与 $P \to Q$（逆否即 $\sim Q \to \sim P$）串联，得 $R \to \sim P$。

### (4) $(\forall x)[P(x) \to (Q(y) \land R(x))],\ (\exists x)P(x) \Rightarrow Q(y) \land (\exists x)[P(x) \land R(x)]$

注意 $y$ 在前提、结论中都是自由变元，表示同一个给定个体，不要对它用量词规则。

**演绎证明：**

- ① $(\forall x)[P(x) \to (Q(y) \land R(x))]$ —— 前提
- ② $(\exists x)P(x)$ —— 前提
- ③ $P(c)$ —— ES，由 ②，$c$ 为新个体（见证）
- ④ $P(c) \to (Q(y) \land R(c))$ —— US，由 ①（$x$ 取 $c$）
- ⑤ $Q(y) \land R(c)$ —— MP，由 ③④
- ⑥ $Q(y)$ —— 化简，由 ⑤
- ⑦ $R(c)$ —— 化简，由 ⑤
- ⑧ $P(c) \land R(c)$ —— 合取引入，由 ③⑦
- ⑨ $(\exists x)[P(x) \land R(x)]$ —— EG，由 ⑧
- ⑩ $Q(y) \land (\exists x)[P(x) \land R(x)]$ —— 合取引入，由 ⑥⑨

证毕。$c$ 不出现在最终结论中，ES 合法。

---

## 第 10 题 Skolem 范式与消解法证明

消解法证 $A \Rightarrow B$ 的固定套路：构造 $A \land \sim B$，化成 Skolem 范式并写成子句集，再反复用消解规则（带最一般合一替换）推出空子句 $\Box$。推出空子句说明 $A \land \sim B$ 不可满足，即 $A \Rightarrow B$ 成立。下面子句中不同子句的变元可各自改名，变元默认受全称量词约束。

### (1) $(\forall x)[P(x) \to Q(x)] \Rightarrow (\forall x)[(\exists y)[P(y) \land R(x,y)] \to (\exists y)[Q(y) \land R(x,y)]]$

**第一步：否定结论并与前提合取。**

前提记为 $A$，结论记为 $B$。否定 $B$：

$$\sim B \iff (\exists x)[(\exists y)[P(y) \land R(x,y)] \land (\forall y)[\sim Q(y) \lor \sim R(x,y)]].$$

为避免变元撞名，把 $A$ 中的变元改名为 $u$，把 $\sim B$ 中两个内层变元分别记为 $v$（存在见证）和 $w$（全称变元）。把存在量词提到最前（它们与 $A$ 中的变元互不干扰），得前束形并 Skolem 化：$x$ 的见证记常量 $a$，$v$ 的见证记常量 $b$。

**第二步：子句集。**

- $S1$：$\{\sim P(u),\ Q(u)\}$ —— 来自前提 $A$，即 $\sim P(u) \lor Q(u)$
- $S2$：$\{P(b)\}$ —— 来自 $\sim B$ 中的 $P(v)$
- $S3$：$\{R(a,b)\}$ —— 来自 $\sim B$ 中的 $R(x,v)$
- $S4$：$\{\sim Q(w),\ \sim R(a,w)\}$ —— 来自 $\sim B$ 中的 $\sim Q(w) \lor \sim R(x,w)$，其中 $x$ 已 Skolem 化为 $a$

**第三步：消解。**

- $S5$：$\{Q(b)\}$ —— $S1$ 与 $S2$ 消解，合一替换 $u=b$（$\sim P(b)$ 与 $P(b)$ 互补消去）
- $S6$：$\{\sim R(a,b)\}$ —— $S4$ 与 $S5$ 消解，合一替换 $w=b$（$\sim Q(b)$ 与 $Q(b)$ 互补消去）
- $S7$：$\Box$ —— $S3$ 与 $S6$ 消解（$R(a,b)$ 与 $\sim R(a,b)$ 互补）

推出空子句，故 $A \land \sim B$ 不可满足，原蕴含式成立。证毕。

### (2) $(\exists x)P(x) \to (\forall x)[(P(x) \lor Q(x)) \to R(x)],\ (\exists x)P(x),\ (\exists x)Q(x) \Rightarrow (\exists x)(\exists y)[R(x) \land R(y)]$

三个前提分别记为 $A1$、$A2$、$A3$，结论记为 $B$。

**第一步：各部件化子句。**

$A1$：先消蕴涵并改名（各量词变元互不重用）：

$$A1 \iff (\forall x_{1})\sim P(x_{1}) \lor (\forall x_{2})[( \sim P(x_{2}) \lor R(x_{2})) \land (\sim Q(x_{2}) \lor R(x_{2}))]$$

把 $\lor$ 对 $\land$ 分配，得两个全称子句（$u,v$ 为子句变元，可独立改名）：

- $C1$：$\{\sim P(u),\ \sim P(v),\ R(v)\}$ —— 由 $(\forall x_{1})\sim P(x_{1}) \lor (\forall x_{2})[\sim P(x_{2}) \lor R(x_{2})]$ 展开
- $C2$：$\{\sim P(u),\ \sim Q(v),\ R(v)\}$ —— 由 $(\forall x_{1})\sim P(x_{1}) \lor (\forall x_{2})[\sim Q(x_{2}) \lor R(x_{2})]$ 展开

$A2$、$A3$ Skolem 化（存在见证取常量）：

- $C3$：$\{P(a)\}$ —— 由 $A2=(\exists x)P(x)$，见证记 $a$
- $C4$：$\{Q(b)\}$ —— 由 $A3=(\exists x)Q(x)$，见证记 $b$

否定结论 $B$：

$$\sim B \iff (\forall x)(\forall y)[\sim R(x) \lor \sim R(y)], \qquad C5：\{\sim R(s),\ \sim R(t)\}.$$

**子句集：** $\{C1, C2, C3, C4, C5\}$，全部变元受全称约束、常量 $a,b$ 为 Skolem 常量。

**第二步：消解。**

先由 $P$ 的见证推出 $R(a)$：

- $C6$：$\{\sim P(v),\ R(v)\}$ —— $C1$ 与 $C3$ 消解，合一 $u=a$，消去 $\sim P(a)$
- $C7$：$\{R(a)\}$ —— $C6$ 与 $C3$ 再消解，合一 $v=a$，消去 $\sim P(a)$

再由 $P,Q$ 的见证推出 $R(b)$：

- $C8$：$\{\sim Q(v),\ R(v)\}$ —— $C2$ 与 $C3$ 消解，合一 $u=a$，消去 $\sim P(a)$
- $C9$：$\{R(b)\}$ —— $C8$ 与 $C4$ 消解，合一 $v=b$，消去 $\sim Q(b)$

最后与否定结论消解：

- $C10$：$\{\sim R(t)\}$ —— $C5$ 与 $C7$ 消解，合一 $s=a$
- $C11$：$\Box$ —— $C10$ 与 $C9$ 消解，合一 $t=b$

推出空子句，故前提合取与 $\sim B$ 不可满足，原蕴含式成立。证毕。

**备注：** 本题结论 $(\exists x)(\exists y)[R(x) \land R(y)]$ 并不要求 $x \neq y$，事实上只用 $R(a)$ 与自身（在 $C5$ 中取 $s=t=a$）也能推出空子句，前提 $A3$ 用不上。上面保留 $R(b)$ 的推法是为了把给定条件都用上，并顺带说明：$R$ 的见证不止一个。若题目本意要求两个不同个体，应在结论中另加 $x \neq y$ 之类的条件。

---

## 易错点小结

- **辖域看紧跟的公式。** $(\forall x)P(x) \to Q(x)$ 中量词只管 $P(x)$；辖域之外的同名 $x$ 是自由出现（第 2 题）。
- **改名只改约束出现。** 自由变元一个不能动；新字母不能与任何自由变元重名（第 3 题）。
- **有限论域展开别漏项。** $\forall$ 是合取、$\exists$ 是析取；蕴涵号留在每个实例内部（第 1 题）。
- **$1$ 不是素数。** 第 4 题 (1) 的 $Q(1)$ 为假，靠 $P(1)$ 为真才救回合取项。
- **$(\forall x)(\exists y)$ 与 $(\exists y)(\forall x)$ 不等值。** 前者的 $y$ 可随 $x$ 变，后者的 $y$ 必须统一管用；第 4 题 $B,C$ 同真只是这个解释下的巧合，第 5 题 (3)(4) 正好一题考“随 $x$ 变的 $y$”、一题考“先选定的 $y$”。
- **Skolem 函数的参数看它前面有几个全称量词。** 参数只能多、不能少；Skolem 范式只保持可满足性，不与原式等值（第 7、10 题）。
- **消解法必须先否定结论。** 证 $A \Rightarrow B$ 时，子句集来自 $A \land \sim B$；推出空子句 $\Box$ 才算证毕（第 10 题）。
- **存在消去的见证不能漏进结论。** ES 引进的 $c$ 不得在前提中出现，也不得出现在最终结论中（第 9 题）。
