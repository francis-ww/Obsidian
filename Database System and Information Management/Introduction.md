1.0 DB, DBMS, DBS

Basic Concepts
     Data
         描述事物的符号记录
     Database(DB)
         相关信息的集合，以文件形式存储，集成(integrated collection of related data)
         起源于规范化“表(table)”的处理（结构化数据）e.g. 成绩单
         Database就是相互之间有关联的table的集合 e.g. 登记表和成绩单
     Database Management System(DBMS)
         访问数据的程序，方便高效，数据库系统的核心，管理大体量的信息
         ![[image.jpg]]
         E.g. 
             a. 12306网站
                 多用户并发访问，分布式数据库
                 负荷非常高，采用内存数据库技术（不限于），提高访问效率
             b. 校内管理系统：教务、科研管理系统
             c. Big data
                 百科：数据量非常大，无法用常规数据
                 互联网环境下的海量数据，文本、音频、视频等
         结构简单和小规模的数据不一定需要DBMS
     Database System(DBS)
         DB+DBMS+User/Administrator

1.2 Purpose of DBS (e.g. university database)
     Data consists of
         Students
         Instructors
         Classes
         Grades
     Application programs

1.3 View of Data
     External Level(View Level)
         用户所看到的数据呈现形式（局部逻辑）
         瞎子摸到的大象（局部）
     Logical Level(Conceptual Level)
         全面的数据和关系（全局逻辑）
     Physical level(Internal Level)
         Describes how data(e.g. customer records) are actually stored in files 
         Physical/Internal Schema （物理模式，内模式）