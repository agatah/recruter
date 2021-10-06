## To import files:
POST: /api/v1/import

required: path to file in request body

consumes: text/plain


## To generate reports:

#### All projects data:
GET: /api/v1/export/projects

produces: JSON


#### All active employees:

GET: /api/v1/export/employees

produces: JSON


#### Active employees without project:

GET: /api/v1/export/employees/without-project

produces: JSON

#### All active employees salaries:
GET: /api/v1/export/salaries

produces: text/csv

#### Active employees salaries per project:
GET: /api/v1/export/salaries/{projectName}

produces: text/csv
