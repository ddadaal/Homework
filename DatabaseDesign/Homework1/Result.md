# 1. 查询销售量最高的产品的前两名（pname，volume）
select P.pname, S.v as volume from Production P, (
  select pid, sum(volume) as v from Deal
  group by pid
  order by v DESC
  limit 2
) as S
where P.pid = S.sid;
# 2. 查询2017年生产的产品的总销量（pname，volume）
select P.pname, S.v as volume from Production P, (
  select D.pid, sum(D.volume) as v from Deal D
  group by D.pid
  where D.pid in (
    select pid from Production P
    where year(pdate) = 2017
  )
) as S
where S.pid = P.pid;
# 3. 查询产品编号为2且销售量超过100的销售人员的姓名及所在的公司（sname，aname）
select S.sname, A.aname from Agent A, Sales S
where A.aid = S.aid and S.sid in (
  select D.sid from Deal D
  group by D.sid, D.pid
  having D.pid = "2" and sum(D.volume)>100
);
# 4. 查询所有代理商所有产品的销量（aname，pname，volume）
select A.aname, P.pname, S1.v as volume as volume from Agent A, Production P, Sales S, (
  select sid, pid, sum(volume) as v from Deal
  group by sid, pid
) as S1
where P.pid = S.pid and S.sid = S1.sid and A.aid = S.aid;

# 5. 查询每个产品有多少个销售人员在销售（pname，scount（计数））
select P.pname, S.count as scount from Production P, (
  select pid, count(sid) as count from Deal
  group by pid
) as S
where S.pid = p.pid;

# 6. 查询名称包含BBB的代理商中的所有销售人员（aname，sname）
select A.aname, S.sname from Agent A, Sales S
where A.aid = S.sid and A.aname like "%BBB%";

# 7. 查询总销量最差的产品（pname，volume）
select P.pname, S.v as volume from Production P, (
  select pid, sum(volume) as v from Deal
  group by pid
  order by v
  limit 1
) as S
where P.pid = S.sid;

# 8. 查询每种产品销售总量最高的销售人员（pname，sname，volumn）
select P.pname, S.sname, S1.v as volume from Production P, Sales S, (
  select pid, sid, sum(volume) as v from Deal
  group by pid, sid
  order by v DESC
) as S1
where P.pid = S1.pid and S.sid = S1.sid and not exists (
  select * from Sales S2, (
    select pid, sid, sum(volume) as v from Deal
    group by pid, sid
    order by v DESC
  ) as S3
  where S2.sid <> S.sid and S2.sid = S3.sid and S3.v > S1.v
);