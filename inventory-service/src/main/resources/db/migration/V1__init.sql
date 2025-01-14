CREATE TABLE "inventories" (
    "id" BIGSERIAL,
    "inventory_id" UUID NOT NULL,
    "sku_code" VARCHAR(255) NOT NULL,
    "quantity" INTEGER NOT NULL,
    CONSTRAINT "inventory_pkey" PRIMARY KEY("id")
);