SELECT * FROM db.table WHERE
MONTH (nascimento) = MONTH (CURDATE())
AND DAY (nascimento) = DAY (CURDATE())
AND status = 'ATIVO';