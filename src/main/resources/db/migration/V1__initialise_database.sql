--DROP SCHEMA IF EXISTS mcve CASCADE;

--CREATE SCHEMA mcve;

--SET SEARCH_PATH to mcve;


CREATE TYPE my_type AS (
  var_a text,
  var_b int
);

CREATE OR REPLACE FUNCTION f_test_func(IN words text[], IN my_types my_type[], IN my_data my_type)
RETURNS int LANGUAGE sql 
AS $$

	SELECT 1;

$$
