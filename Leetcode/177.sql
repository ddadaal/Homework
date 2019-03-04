select LE.Salary from (
    select * from Employee
    order by Salary desc
    limit n
) LE
order by LE.Salary
limit 1