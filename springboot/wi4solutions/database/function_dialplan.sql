DELIMITER $$
CREATE DEFINER=`root`@`localhost` FUNCTION `getDialData`(number VARCHAR(100)) RETURNS varchar(255) CHARSET latin1
BEGIN
DECLARE dial VARCHAR(200) DEFAULT "";
SELECT CONCAT(preceding,SUBSTRING(number,p.digit_cut + 1),'@', g.host) INTO dial FROM dial_plan p INNER JOIN gateway g ON g.id = p.gateway_id 
WHERE SUBSTRING(number,1,LENGTH(prefix)) = p.prefix ORDER BY LENGTH(p.prefix) DESC LIMIT 1 ;	

RETURN dial;
	
END$$
DELIMITER ;
