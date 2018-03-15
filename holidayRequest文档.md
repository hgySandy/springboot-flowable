# 1.提交请假申请

**Curl**

```
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' 'http://localhost:1111/holiday?employee=Grace&nrOfHolidays=5&description=go%20to%20American'
```
**Request URL**
```
http://localhost:1111/holiday?employee=Grace&nrOfHolidays=5&description=go%20to%20American
```


Table 1. 提交请假申请 - URL 参数

|参数|必填|值|描述|
|----|----|----|----|
|employee|是|String|请假申请人姓名。|
|nrOfHolidays|是|Integer|请假天数。|
|description|是|String|请假描述（原因、理由）。|


Table 2. 提交请假申请 - 响应码
| 响应码|描述|
|------|------|
|200|代表提交请假申请成功。|
|400|代表提交请假申请失败。|
**成功响应体：**
```
{
  "code": 200,
  "message": "SUCCESS",
  "data": {
    "nrOfHolidays": 5,
    "description": "go to American",
    "employee": "Grace"
  }
}
```
* employee: 代表请假申请人姓名。

* description: 请假描述（原因、理由）。

* nrOfHolidays: 请假天数。

# 2.查看请假申请列表

**Curl**

```
curl -X GET --header 'Accept: application/json' 'http://localhost:1111/holiday'
```
**Request URL**
```
http://localhost:1111/holiday
```


Table 1. 查看请假申请列表 - URL 参数

|参数|必填|值|描述|
|----|----|----|----|
|无|无|无|无|



Table 2. 查看请假申请列表 - 响应码
| 响应码|描述|
|------|------|
|200|代表查看请假申请列表成功。|
|400|代表查看请假申请列表失败。|
**成功响应体：**
```
{
  "code": 200,
  "message": "SUCCESS",
  "data": [
    {
      "taskId": "57519",
      "taskName": "Approve or reject request"
    },
    {
      "taskId": "57532",
      "taskName": "Approve or reject request"
    },
    {
      "taskId": "57545",
      "taskName": "Approve or reject request"
    },
    {
      "taskId": "7545",
      "taskName": "Approve or reject request"
    }
  ]
}
```
* taskId: 任务id。

* taskName: 任务名称。

# 3.查看请假申请详情

**Curl**

```
curl -X GET --header 'Accept: application/json' 'http://localhost:1111/holiday/42572'
```
**Request URL**
```
http://localhost:1111/holiday/42572
```


Table 1. 查看请假申请详情 - URL 参数

|参数|必填|值|描述|
|----|----|----|----|
|taskId|是|integer|任务id|



Table 2. 查看请假申请详情 - 响应码
| 响应码|描述|
|------|------|
|200|代表查看请假申请详情成功。|
|400|代表查看请假申请详情失败。|
**成功响应体：**
```
{
  "code": 200,
  "message": "SUCCESS",
  "data": {
    "taskId": "42572",
    "approved": false,
    "employee": "Lily",
    "nrOfHolidays": 5,
    "description": "go to American"
  }
}
```
* taskId: 任务id。
* approved: 是否同意true/false。
* employee: 员工姓名。
* nrOfHolidays: 请假天数。
* description: 请假描述。


# 4.审批请假申请

**Curl**

```
curl -X PUT --header 'Content-Type: application/json' --header 'Accept: application/json' 'http://localhost:1111/holiday/42519?taskId=42519&approved=true'

```
**Request URL**
```
http://localhost:1111/holiday/42519?taskId=42519&approved=true
```


Table 1. 审批请假申请 - URL 参数

|参数|必填|值|描述|
|----|----|----|----|
|taskId|是|String|任务id|
|approved|是|boolean|通过或拒绝申请true/false|


Table 2. 审批请假申请 - 响应码
| 响应码|描述|
|------|------|
|200|代表审批请假申请成功。|
|400|代表审批请假申请失败。|
**成功响应体：**
```
{
  "code": 200,
  "message": "SUCCESS",
  "data": {
    "approved": true,
    "taskId": "42519"
  }
}
```
* taskId: 任务id。
* approved: 是否同意true/false。


