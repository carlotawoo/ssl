# ssl
springboot配置http以及https


适用于springboot2.X
配置文件中添加ssl的配置
增加config的配置

生成免费的ssl文件命令：
keytool -genkeypair -alias hellowood -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore hellowood.p12 -validity 3650

方法二：
可参考：

https://www.cnblogs.com/liuyangjava/p/13488751.html


添加shiro  以及部分Lamdba表达式