`sqlmd`is a tool for generating table create sql

Here is an example

````text
# mx_user(table_name)
```text(table_comment)
用户表
```
(column info and * for primary key)
name|type|remark|defaultvalue
---|---|---|---
*id|varchar(100)|主键|-
name|varchar(100)|姓名|-
email|varchar(100)|邮箱|-

(index information)
### idx_name
- name
- email
````

ctrl+c to copy markdown to clipboard and execute the command:

```bash
java -jar sqlmd.jar [oracle|mysql]
```

the result will output in the console and paste into clipboard. just ctrl+v.