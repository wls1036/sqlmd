# mx_user1
```text
用户表
```

列名|类型|备注|默认值
---|---|---|---
*id|varchar2(100)|主键|-
name|varchar2(100)|姓名|-
*email|varchar2(100)|邮箱|-


### idx_name
- name
- email

# mx_order1
```text
订单表
```

列名|类型|备注|默认值
---|---|---|---
*id|varchar2(100)|主键|-
orderNo|varchar2(100)|订单编号|-
address|varchar2(100)|订单地址|-


### idx_x
- orderNo
- address