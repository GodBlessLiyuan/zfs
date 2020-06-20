# v1.0.2

1. 智能助手和多开分身VIP商品互通的需求
助手端和多开端的t_vipcommodity:
    增加字段（商品属性）：comm_attr tinyint DEFAULT 1,
新增加了购买赠送表：t_buy_gift
## 更新模块 ##

1. dkfspay
    改动了微信通知接口：v1.0/wxpaynotify，具体为WxPayServiceImpl类下的wxPayNotify()方法。业务逻辑为查询商品属性，
调用多开分身的web接口。 
    改动了支付通知接口：v1.0/alipaynotify，具体为AlipayServiceImpl类下的updateInfo()方法。业务逻辑为查询商品属性，
调用多开分身的web接口。 
2. dkfsweb
    添加了接口v1.0/buy_zj_douOrder，查询或者新增数据到了用户表t_user、销售渠道表t_soft_channel，新增数据到购买赠送表t_buy_gift,修改了用户会员表
 t_user_vip,
    修改了商品列表的后台,接口为vipcommodity/insert;
    
3. dkfsserver
    改动了商品列表接口：v1.0/getcommodity，判断商品类别是否通用
    改动了订单接口：v1.0/order里面的内容
    修改了接口：v1.0/config：具体为ConfigServiceImpl类的queryConfigInfo()方法,下载链接存在数据库中：INSERT into `t_key_value` (key_name,value) VALUES (20,'http://39.97.253.38/dkfsserver/v1.0/clearfans');
    修改了接口：v1.0/checkava，修改了表t_avatar，增加了字段os_version int DEFAULT 0,该字段os_version在表t_avatar中必须有值：0或者10，也可以参见：v1.0md的api:28号. 分身更新

   