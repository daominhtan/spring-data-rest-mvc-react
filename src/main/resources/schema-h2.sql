create TABLE "people" (
    "id" bigint auto_increment,
    "user_id" bigint,
    "date_of_birth" date not null,
    "name" varchar(255)
    "created_at" timestamp,
    "updated_at" timestamp,
    "version" bigint,
    PRIMARY KEY ("id")
);

create TABLE "possessions" (
    "id" bigint auto_increment,
    "owner_id" bigint not null,
    "description" text,
    "completed_at" timestamp,
    "created_at" timestamp,
    "updated_at" timestamp,
    "version" bigint,
    PRIMARY KEY ("id"),
    FOREIGN KEY ("owner_id") REFERENCES "people" ("id")
);
