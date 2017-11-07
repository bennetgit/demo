####安全表达式：计算结果
####authentication：用户认证对象
####denyAll：结果始终为false
####hasAnyRole(list of roles)：如果用户被授权指定的任意权限，结果为true
####hasRole(role)：如果用户被授予了指定的权限，结果 为true
####hasIpAddress(IP Adress)：用户地址
####isAnonymous()：是否为匿名用户
####isAuthenticated()：不是匿名用户
####isFullyAuthenticated：不是匿名也不是remember-me认证
####isRemberMe()：remember-me认证
####permitAll：始终true
####principal：用户主要信息对象