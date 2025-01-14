CREATE TABLE "orders" (
    "id" BIGSERIAL,
    "order_id" UUID NOT NULL,
    "order_number" VARCHAR(255) NOT NULL,
    "sku_code" VARCHAR(255) NOT NULL,
    "price" NUMERIC(38, 2) NOT NULL,
    "quantity" INTEGER NOT NULL,
    "created_date" TIMESTAMP(6) NOT NULL,
    CONSTRAINT "orders_pkey" PRIMARY KEY("id")
);