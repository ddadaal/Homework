# 移动测试大作业

使用Intellij IDEA

`mvn package`打包成一个jar包。

# 坑记录

以下为应用在Android 6.0虚拟机上的情况

| 应用 | 坑 |
| -- | -- |
| Jianshi | 无法登录和注册，提示网络错误 |
| Odyssey | aapt分析出的启动activity转瞬而过，appium一直等待已经结束的activity启动直到超时；并且启动activity结束后是个权限弹窗，点击后进入另一个activity，appium仍然无法恢复 |
| Leafpic | 应用启动前权限弹窗，appium卡住；手动确认权限后可以正常进行脚本 | 
| Bihudaily | 点击Night Mode强退 | 
| SeeWeather | 多城市里添加城市会强退; 选择城市点击后界面没有变化 | 
| Jiandou | 没有数据；点击“电影榜单”会强退 |

# 进度 

| 应用 | 6.0进度 | 4.4进度 | 5.0进度 | 7.0进度 | 
| -- | -- | -- | -- | -- |
| bihudaily | 100% |  |  | |
| Bilibili |  |  | | |
| GeekNews |  |  | | |
| GuDong | 100%, 5min |  | | |
| ITHouse | 95%, 没有进行搜索 |  | | |
| Jiandou | 85%, 没点搜索，几个界面的tab没点  | | | |
| Jianshi | 登录不了 | | | |
| Leafpic  | 进不去 | | | |
| Odyssey | 进不去 | | | |
| SeeWeather | 在选城市界面疯狂崩溃，或者直接进不去 | | | |
