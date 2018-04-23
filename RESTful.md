# RESTful API 规范 v1.0

本规范适用于艾博克`财税平台`SOA系统。需要访问SOA系统的第二方、第三方App需要在系统中注册、登录之后会返回一个Access-Token和过期时间
App以后的每次访问都需要在请求头中携带Access-Token，才能访问本系统API；Access-Token会在过期时间后过期，App需要在过期之前刷新Access-Token。

用户使用本系统API同样需要用户登录之后返回的User-Token，User-Token可以作为用户唯一身份标识。User-Token也会有过期时间，如果用户在
过期时间之内不调用接口，User-Token也会过期；如果在过期时间之内调用了接口，系统会自动刷新User-Token，直到下一个过期时间周期。

### 完整URI

	http://api.abc12366.com:9600/images
	http://api.abc12366.com:9400/cms
	http://api.abc12366.com:9300/sns
	http://api.abc12366.com:9200/message
	http://api.abc12366.com:9100/uc

### URI规范
* 不要用大写
* 单词间使用下划线'_'
* 不使用动词（如select、query、update），资源要使用名词或名词复数形式（如user、rooms、tickets）
* 层级 `>=` 三层，则使用'?'带参数
	+ ~~users/1/address/2/citys~~ (不好)
	+ /citys?users=1&address=2; (好)

---

### 版本控制
在请求头中加入：
> Accept Header：Version: 1

---

### Method
* GET：查询资源
* POST：创建资源
* PUT：全量更新资源（提供改变后的完整资源）
* DELETE：删除资源

#### 例子
* GET /collection：返回资源对象的列表（数组）
* GET /collection/resource：返回单个资源对象
* POST /collection：返回新生成的资源对象
* PUT /collection/resource：返回完整的资源对象
* DELETE /collection/resource：返回一个空文档

---

### 兼容
很多客户只支持GET/POST请求，一般有两种方式模拟PUT等请求

添加_method参数
```javascript
	/users/1?_method=put&name=111
```
添加X-HTTP-Method-Override请求头 (我们使用这种方式)
```javascript
	X-HTTP-Method-Override: PUT
```

---

### 参数处理

#### GET参数
非id的参数使用'?'方式传输
```javascript
	/users/1?state=closed
```
#### POST、PATCH、PUT、DELETE
* 非id的参数使用body传输，并且应该encode

#### 过滤
* ?type=1&state=closed
* ?page=2&size=100：指定第几页，以及每页的记录数。

#### 分页
* ?page=2&size=100
* page：指定第几页，为0表示所以记录
* size：每页的记录数，为0表示所以记录

---

### 状态码

* 1xx范围是为低级别HTTP的东西保留的，而且，您很可能会在没有手动发送其中一个状态代码的情况下完成整个职业生涯。
* 2xx范围保留给成功的消息，其中所有都按计划进行。尽最大努力确保您的服务器尽可能多地向消费者发送这些消息。
* 3xx范围保留用于流量重定向。大多数API不会很多地使用这些请求（SEO用户不太频繁），然而，较新的超媒体风格的API将会更多地利用这些请求。
* 4xx范围被保留用于响应消费者所做的错误，例如他们提供不好的数据或要求不存在的东西。这些请求应该是等号的，而不是改变服务器的状态。
* 当服务器发生错误时，5xx范围被保留作为响应。通常情况下，即使在开发者手中，这些错误也被低级功能抛出，以确保消费者获得某种响应。消费者可以知道当收到5xx响应时服务器的状态，因此这些应该是可以避免的。

* 200 OK - [GET]：服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）。
* 201 CREATED - [POST/PUT/PATCH]：用户新建或修改数据成功。
* 202 Accepted - [*]：表示一个请求已经进入后台排队（异步任务）
* 204 NO CONTENT - [DELETE]：用户删除数据成功。
* 400 INVALID REQUEST - [POST/PUT/PATCH]：用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的。
* 401 Unauthorized - [*]：表示用户没有权限（令牌、用户名、密码错误）。
* 403 Forbidden - [*] 表示用户得到授权（与401错误相对），但是访问是被禁止的。
* 404 NOT FOUND - [*]：用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的。
* 406 Not Acceptable - [GET]：用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）。
* 410 Gone -[GET]：用户请求的资源被永久删除，且不会再得到的。
* 422 Unprocesable entity - [POST/PUT/PATCH] 当创建一个对象时，发生一个验证错误。
* 500 INTERNAL SERVER ERROR - [*]：服务器发生错误，用户将无法判断发出的请求是否成功。

以上是常见的状态码，完整的状态码列表在这[状态码](http://www.restapitutorial.com/httpstatuscodes.html)

---

### 正常处理
请求头的状态码是200，表示网络正常。正常业务请求体的返回：
```json
{
	code: 2000,
    message: "操作成功"
}
```

### 错误处理
请求头的状态码也是200，表示网络正常。常规错误时的错误处理：
```json
{
	code: 4000,
    message: "请求头Access-Token不正确"
}
```

校验错误时的错误处理：
```json
{
	code: 4001,
	field: username,
	message: "用户名不能为空，有效长度在8-16位"
}
```

### HTTP Request例子
```
POST /animal HTTP/1.1
Host: api.example.org
Accept: application/json
Content-Type: application/json
Version: 1
Content-Length: 24
 
{
  "name": "Gir",
  "animal_type": 12
}
```

### HTTP Response例子
```
HTTP/1.1 200 OK
Date: Wed, 18 Dec 2013 06:08:22 GMT
Content-Type: application/json
Access-Control-Max-Age: 1728000
Cache-Control: no-cache
 
{
  "id": 12,
  "created": 1386363036,
  "modified": 1386363036,
  "name": "Gir",
  "animal_type": 12
}
```

---

### 例子
* GET /zoos：列出所有动物园
* POST /zoos：新建一个动物园
* GET /zoos/ID：获取某个指定动物园的信息
* PUT /zoos/ID：更新某个指定动物园的信息（提供该动物园的全部信息）
* PATCH /zoos/ID：更新某个指定动物园的信息（提供该动物园的部分信息）
* DELETE /zoos/ID：删除某个动物园
* GET /zoos/ID/animals：列出某个指定动物园的所有动物
* DELETE /zoos/ID/animals/ID：删除某个指定动物园的指定动物
* GET /zoos/ID/animals: 获取某一动物园的动物列表
