delete from ebdb.ACCOUNTS_STATS
where log_date between str_to_date('2017-02-15 14:01:00','%Y-%m-%d %H:%i:%s') and str_to_date('2017-02-15 17:00:00','%Y-%m-%d %H:%i:%s') 