#多线程编程实验代码说明(文件源码均为GBK)
---
##1.文件目录说明
- bin	class文件存放
- file	文件系统文件存放处
- icon	应用图标
- lib	第三方库文件
- src	源码.java
##2.数据库配置
###1.user表
>id编号(int)
>
>username用户名(char)
>
>password密码(char)
>
>role角色(char)
>
###2.doc表
>id编号(int)
>
>number文件编号(char)
>
>owner拥有者(char)
>
>timestamp时间(timestamp)
>
>description描述(char)
>
>filename文件名(char)
>
###3.log表
>id编号(int)
>maker事件发出者(char)
>made事件承受者(char)
>event事件描述(char)
>time时间(timestamp)
##3程序操作说明
---
>主函数&nbsp;**FileFrame.java**&nbsp;&nbsp;&nbsp;**Server.java**
>
>管理员kate 密码123
>
>操作员jack密码123
>
>浏览员rose密码123
