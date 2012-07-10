

-- Change script: #1: 1_create_base_tables.sql

DROP TABLE inventory;


DELETE FROM changelog  WHERE change_number = 1 AND delta_set = 'DDL';
COMMIT;
--------------- Fragment ends: #1: 1_create_base_tables.sql ---------------

