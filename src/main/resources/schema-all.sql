CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `root`@`localhost` 
    SQL SECURITY DEFINER
VIEW `v_report` AS
    SELECT 
        CAST(CONCAT(YEAR(`o`.`operation_date`),
                    CONCAT('-',
                            CONCAT(MONTH(`o`.`operation_date`), '-1')))
            AS DATE) AS `report_month`,
        `o`.`operation_category` AS `report_category`,
        SUM(`o`.`amount`) AS `amount`
    FROM
        `operation` `o`
    GROUP BY `report_month` , `o`.`operation_category`;