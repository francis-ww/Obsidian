# 1.0 DB, DBMS, DBS

## Basic Concepts

### Data
- 描述事物的符号记录

### Database (DB)
- 相关信息的集合，以文件形式存储
- 集成化相关数据的集合 (integrated collection of related data)
- 起源于规范化"表(table)"的处理（结构化数据）
  - e.g. 成绩单
- Database 就是相互之间有关联的 table 的集合
  - e.g. 登记表和成绩单

### Database Management System (DBMS)
- 访问数据的程序，方便高效
- **数据库系统的核心**，管理大体量的信息
- 工作示意图：
  - ![[DBMS_working_mechanism.jpg]]

#### 应用实例

**a. 12306 网站**
- 多用户并发访问，分布式数据库
- 负荷非常高，采用内存数据库技术（不限于），提高访问效率

**b. 校内管理系统**
- 教务系统、科研管理系统

**c. Big data (大数据)**
- 百科定义：数据量非常大，**超出传统数据库软件工具抓取、存储、管理和分析能力的数据集合**
- 互联网环境下的海量数据，包括文本、音频、视频等非结构化数据

> 💡 **注意**：结构简单和小规模的数据不一定需要 DBMS

### Database System (DBS)
- **组成**：DB + DBMS + User/Administrator

---

# 1.2 Purpose of DBS

以大学数据库为例：

**Data consists of:**
- Students（学生）
- Instructors（教师）
- Classes（课程）
- Grades（成绩）

**Application programs:**
- 各类应用程序

---

# 1.3 View of Data (数据的三层抽象)

| 层级                 | 英文名                                  | 中文名            | 描述                 | 类比         |
| ------------------ | ------------------------------------ | -------------- | ------------------ | ---------- |
| **External Level** | View Level / External Schema         | **视图层 / 外模式**  | 用户所看到的数据呈现形式（局部逻辑） | 瞎子摸到大象（局部） |
| **Logical Level**  | Conceptual Level / Conceptual Schema | **概念层 / 概念模式** | 全面的数据和关系（全局逻辑）     | 百科全书中的大象   |
| **Physical Level** | Internal Level / Internal Schema     | **物理层 / 内模式**  | 描述数据实际如何存储在文件中     | 森林中的大象     |
* Instances and Schems
     Instance - the actual content of the database at a particular point in time
     实例：按模式组织的一个具体的数据
* 
---

# 📌 今日学习要点总结

1. **区分三个核心概念**：
   - **DB** = 数据本身（相关表的集合）
   - **DBMS** = 管理软件（数据库系统的核心）
   - **DBS** = 完整系统（DB + DBMS + 用户）

2. **理解数据抽象的三层模型**：
   - 外部层（用户视角）→ 逻辑层（全局设计）→ 物理层（存储实现）
   - 上层屏蔽下层细节，实现数据独立性

3. **DBMS 的应用场景**：
   - 高并发、大数据量、多用户共享时需要 DBMS
   - 小规模、结构简单的数据可能不需要

---

*🤖 Edited by Robster | 基于原始笔记优化：统一格式、补充定义、添加对比表格和学习总结


