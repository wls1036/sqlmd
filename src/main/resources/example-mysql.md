# mx_user
```text
用户表
```

列名|类型|备注|默认值
---|---|---|---
*id|varchar(100)|主键|-
name|varchar(100)|姓名|-
email|varchar(100)|邮箱|-


### idx_name
- name
- email

# mx_order
```text
订单表
```

列名|类型|备注|默认值
---|---|---|---
*id|varchar(100)|主键|-
orderNo|varchar(100)|订单编号|-
address|varchar(100)|订单地址|-


### idx_orderNo
- orderNo
