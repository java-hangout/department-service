# department-service
http://localhost:8082/api/departments/create
{
"departmentsName": "IT_InfoSec",
"description": "Handles Security Issues",
"managerId": "",
"userIds":[]
}
{
"id": "dept_003",
"departmentsName": "IT_InfoSec",
"description": "Handles Security Issues",
"managerId": "",
"userIds": []
}

http://localhost:8082/api/departments/delete/dept_004

http://localhost:8082/api/departments/dept_002
{
"id": "dept_002",
"departmentsName": "IT_Architect",
"description": "Handles Architecture and Solution",
"managerId": "",
"userIds": []
}

http://localhost:8082/api/departments/all
[
{
"id": "dept_001",
"departmentsName": "IT_SD",
"description": "Handles Service Tickets",
"managerId": "",
"userIds": []
},
{
"id": "dept_002",
"departmentsName": "IT_Architect",
"description": "Handles Architecture and Solution",
"managerId": "",
"userIds": []
},
{
"id": "dept_003",
"departmentsName": "IT_InfoSec",
"description": "Handles Security Issues",
"managerId": "",
"userIds": []
}
]

http://localhost:8082/api/departments/update/dept_003
{
"departmentsName": "IT_InfoSec",
"description": "Handles Security Issues and vulnarabilities",
"managerId": "",
"userIds":[]
}
{
"id": "dept_003",
"departmentsName": "IT_InfoSec",
"description": "Handles Security Issues and vulnarabilities",
"managerId": "",
"userIds": []
}