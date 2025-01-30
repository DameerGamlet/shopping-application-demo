DO $$
BEGIN
   IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'inventory_db') THEN
      PERFORM dblink_exec('dbname=postgres', 'CREATE DATABASE inventory_db');
   END IF;
   IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'order_db') THEN
      PERFORM dblink_exec('dbname=postgres', 'CREATE DATABASE order_db');
   END IF;
END
$$;
