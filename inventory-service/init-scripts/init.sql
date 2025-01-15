DO $$
BEGIN
   IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'inventory_db') THEN
      PERFORM dblink_exec('dbname=postgres', 'CREATE DATABASE inventory_db');
   END IF;
END
$$;
