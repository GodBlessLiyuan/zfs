# v1.0.2

1. 智能助手和多开分身VIP商品互通的需求
助手端和多开端的t_vipcommodity:
    增加字段（商品属性）：comm_attr tinyint DEFAULT 1,
新增加了购买赠送表：t_buy_gift
新增了注册用户表：m_register_user
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
    修改了接口：userinfo/query，从表m_register_user查用户设备号（即注册时的用户设备号）
    增加了接口：get/post请求：history/createUserHistory；在RegisterUserHistoryController类文件下
3. dkfsserver
    新增了商品列表接口：v1.1/getcommodity，判断商品类别是否通用，修改istop字段值，使得商品在手机端排序显示。新版本多开app可以显示通用商品，
而旧版本app的商品列表的接口：v1.0/getcommodity，旧版本app没有通用商品的选项。原因概述为：历史开发技术遗留问题。
    改动了订单接口：v1.1/order,多加了在助手app购买商品赠送的订单显示；新版本多开app可以显示购买的通用商品和被助手app赠送的商品；
旧版本多开app的商品列表的接口:v1.0/order，不再显示被助手app赠送的商品订单，不再显示购买的通用商品订单。
    修改了接口：v1.0/config：具体为ConfigServiceImpl类的queryConfigInfo()方法,下载链接存在数据库中：INSERT into `t_key_value` (key_name,value) VALUES (20,'http://39.97.253.38/dkfsserver/v1.0/clearfans');
    修改了接口：v1.0/checkava，修改了表t_avatar，增加了字段os_version int DEFAULT 0,该字段os_version在表t_avatar中必须有值：0或者10，也可以参见：v1.0md的api:28号. 分身更新
    改动了用户登录接口：v1.0/register,因为只记录用户注册的设备，增加了表m_register_user，当用户不存在时，将用户和设备号写到这个m_register_user；
 当用户不存在，设备号也不存在，则记录日志；不影响原来的业务逻辑：将用户设备关系写到表用户设备表t_user_device
   