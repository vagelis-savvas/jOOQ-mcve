DROP SCHEMA IF EXISTS mcve CASCADE;

CREATE SCHEMA mcve;

CREATE TABLE mcve.test (
  id    INT NOT NULL,
  value INT,
  
  CONSTRAINT pk_test PRIMARY KEY (id) 
);

CREATE TYPE mcve.my_type AS (
  var_a text,
  var_b int
);

CREATE OR REPLACE FUNCTION mcve.f_test_func(IN words text[], IN my_types mcve.my_type[], IN my_data mcve.my_type)
RETURNS int LANGUAGE sql 
AS $$

	SELECT 1;

$$
