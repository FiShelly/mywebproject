后台入口：127.0.0.1:3000/index
客户端入口： 127.0.0.1:3000/client

增加新闻不关闭模态框是为了方便在此添加，
因为真正使用来说，不可能只添加一条新闻，肯定会添加多条
那么如果关闭模态框后，用户需要反复点击模态框，这样一来就增加用户操作量

login.html 登录页面
nb_index.html 后台首页
new_index.html H5新闻显示页

如果用老师是用macbook的可能由于文件夹权限问题导致进行新闻的增加和修改操作
由于我已经部署到云服务器上，因此老师由于macbook无法正常使用的时候可以访问
http://115.28.174.18:3000/index

shell脚本在Shell文件夹内。
startWebApp.sh  启动新闻系统
logWebApp.sh   输出日志（不知道为什么，当启动这个shell的时候会导致应用访问失败，望老师解答。）