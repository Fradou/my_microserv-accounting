CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `v_operation_total` AS
    SELECT 
        CAST(CONCAT(YEAR(`o`.`operation_date`),
                    CONCAT('-',
                            CONCAT(MONTH(`o`.`operation_date`), '-1')))
            AS DATE) AS `operation_month`,
        `o`.`operation_category` AS `operation_category`,
        SUM(`o`.`amount`) AS `amount`
    FROM
        `operation` `o`
    GROUP BY `operation_month` , `o`.`operation_category`