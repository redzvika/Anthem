# Anthem

#### Phase 1

**local deployment<BR>** 


1. start docker-compose up<BR> 
  this will a postgres db <BR>
2. use intellij run the project in dev mode<BR>
3. use curl command from fillDbViaCurl.txt to update the db with data from assignment xls file<BR>
4. every 5 minutes the app will try to process the new  "incoming action" submitted to via (POST) http://localhost:8080/useractions <BR>
5. every day at 00:01 the app will process pre handled information related to rules [1,4] it will fill update the reports table, **for the previous day**<BR>
6. you can view the reports at (GET) http://localhost:8080/reports


