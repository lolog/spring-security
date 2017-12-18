一.主要流程
   1. 创建一个继承AbstractSecurityInterceptor的Filter
   2. 扩展Filter注入FilterInvocationSecurityMetadataSource,AccessDecisionManager和UserDetailsService
   3. 扩展FilterInvocationSecurityMetadataSource,AccessDecisionManager和UserDetailsService实现类

二.实现
  1. 自定义Filter - FilterSecurityInterceptorCustomizing
  2. 扩展FilterInvocationSecurityMetadataSource - InvocationSecurityMetadataSourceCustomizing
  3. 扩展AccessDecisionManager - AccessDecisionManagerCustomizing
  4. 扩展UserDetailsService - UserDetailServiceCustomizing