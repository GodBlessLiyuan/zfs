## 砖助接口文档 -- v.10 ##

nohup  mvn spring-boot:run &

## 接口说明 ##

### 1. 设备注册接口： ###

* url: https://**server_addr**/helper/v1/dev
* method: POST
* 请求消息示例：
    
    ```
    {
        "android":26,
        "utdid":"utdid",
        "imei":["imei1","imei2"],
        "androidid":"androidid",
        "uuid":"xxxx",
        "version":1
    }
    ```
    参数说明：
    ```
    android: android系统版本号
    utdid: 阿里设备唯一标识
    imei: imei和meid
    androidid: androidid
    uuid: 随机数
    version： app端版本号
    ```

### 2. 请求验证码接口： ###

* url: https://**server_addr**/helper/v1/sms
* method: POST
* 请求消息示例：

    ```
    {
        "did":"deviceid",
        "phone":"18810562717",
        "version":1
    }
    ```
    参数说明：
    ```
    did: 设备唯一标识
    phone: 手机号变种id
    version: 应用版本号versionCode
    ```
    响应消息及错误码：
    ```
    {
        "status":100
    }
    ```
    正确返回：
    ```
    {
        "status":100
    }
    ```
    电话号码格式错误或长度错误
    ```
    {
        "status":101
    }
    ```
    验证码未过期，请稍后重试，用户发送重复请求
    ```
    {
        "status":102
    }
    ```
    非法设备请求，重试请求设备注册接口
    ```
    {
        "status":103
    }
    ```
    app版本错误，无效版本
    ```
   {
        "status":104
    }
    ```
  
### 3. 注册登录接口 ###

* url: https://**server_addr**/helper/v1/register
* method: POST
* 请求消息示例：

    ```
    {
        "did":"deviceid",
        "sms":"123456",
        "version":1
    }
    ```
    参数说明：
    ```
    did: 设备唯一标识
    sms: 短信验证码
    version: 应用版本号versionCode
    ```
    响应消息及错误码：
    ```
    {
        "status":100
    }
    ```
    正确返回：
    ```
    {
        "status":100
    }
    ```
    电话号码格式错误或长度错误
    ```
    {
        "status":101
    }
    ```
    验证码未过期，请稍后重试，用户发送重复请求
    ```
    {
        "status":102
    }
    ```
    非法设备请求，重试请求设备注册接口
    ```
    {
        "status":103
    }
    ```
    app版本错误，无效版本
    ```
   {
        "status":104
    }
    ```


