USE `wi4solutions`;

DROP FUNCTION IF EXISTS `getDialData`;
DELIMITER $$
CREATE DEFINER=`wi4solutions`@`localhost` FUNCTION `getDialData`(`number` VARCHAR(100)) RETURNS varchar(100) CHARSET latin1
BEGIN
DECLARE dial VARCHAR(200) DEFAULT "";
SELECT CONCAT(preceding,SUBSTRING(number,p.digit_cut + 1),'@', g.name) INTO dial FROM dial_plan p INNER JOIN sip_peer g ON g.id = p.gateway_id 
WHERE SUBSTRING(number,1,LENGTH(prefix)) = p.prefix ORDER BY LENGTH(p.prefix) DESC LIMIT 1 ;	

RETURN dial;
	
END $$

DELIMITER ;

DROP PROCEDURE IF EXISTS `get_call_report`;

DELIMITER $$
CREATE  PROCEDURE `get_call_report`(IN `from_date` DATE, `to_date` DATE)
BEGIN
DECLARE FAILED_CALLS BIGINT UNSIGNED DEFAULT 0;
DECLARE CONNECTED_CALLS BIGINT UNSIGNED DEFAULT 0;
DECLARE TOTAL_CALLS BIGINT DEFAULT 0; 
DECLARE TOTAL_DURATION BIGINT DEFAULT 0; 
DECLARE ASR BIGINT DEFAULT 0; 
DECLARE ACD BIGINT DEFAULT 0; 

SELECT COUNT(id), SUM(duration) INTO CONNECTED_CALLS, TOTAL_DURATION FROM wi4solutions.call_detail_record WHERE disposition = 'ANSWERED' 
AND duration > 0
AND DATE(calldate) BETWEEN from_date AND to_date;
SELECT COUNT(id) INTO TOTAL_CALLS FROM wi4solutions.call_detail_record WHERE DATE(calldate) BETWEEN from_date AND to_date;

SET ASR = (CONNECTED_CALLS / TOTAL_CALLS) * 100;

SET ACD = TOTAL_DURATION / CONNECTED_CALLS ;

SELECT CONNECTED_CALLS, TOTAL_CALLS, TOTAL_DURATION, ACD, ASR;
END $$

DELIMITER ;

DROP PROCEDURE IF EXISTS `get_call_report_2`;

DELIMITER $$

CREATE DEFINER=`wi4solutions`@`localhost` PROCEDURE `get_call_report_2`(IN `from_date` DATE, `to_date` DATE, OUT `FAILED_CALLS` BIGINT, OUT `CONNECTED_CALLS` BIGINT, OUT `TOTAL_CALLS` BIGINT, OUT `TOTAL_DURATION` BIGINT, OUT `ASR` DOUBLE, OUT `ACD` BIGINT)
BEGIN

SELECT COUNT(id), SUM(duration) INTO CONNECTED_CALLS, TOTAL_DURATION FROM wi4solutions.call_detail_record WHERE disposition = 'ANSWERED' 
AND duration > 0
AND DATE(calldate) BETWEEN from_date AND to_date;
SELECT COUNT(id) INTO TOTAL_CALLS FROM wi4solutions.call_detail_record WHERE DATE(calldate) BETWEEN from_date AND to_date;
SELECT CONNECTED_CALLS / TOTAL_CALLS * 100 INTO ASR;
SELECT TOTAL_DURATION / CONNECTED_CALLS INTO ACD;

END $$
