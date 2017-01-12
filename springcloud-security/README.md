注意：此工程不进行数据持久化，仅仅通过内存做处理

1、mvn clean install

2、start application

3、获取授权码

  1)浏览器访问   localhost:8081/oauth/authorize?client_id=123456&response_type=code&redirect_uri=http://www.baidu.com
  
  			   --->用户名 ： user  密码：user --->选择授权类型 ---> 获取临时授权码(code)
               
  2)通过第一步的code获取access_token 
  

    curl -X POST -H "Cache-Control: no-cache" -H "Content-Type: application/x-www-form-urlencoded" -d 'grant_type=authorization_code&code=ydIgwy&redirect_uri=http://www.baidu.com' "http://123456:123456@localhost:8081/oauth/token"

  3)通过获取到的access_token访问受保护的资源
  
  http://localhost:8081/hello?access_token=#{access_token}
