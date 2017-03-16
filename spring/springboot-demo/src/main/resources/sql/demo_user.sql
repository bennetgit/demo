create table IF NOT EXISTS demo_user(
   	id bigint not null,
   	name varchar(255) not null,
   	age integer not null,
	constraint demo_user_pk primary key(id)
   )WITH (
    	OIDS=FALSE
    );

   DO  $$
   BEGIN
      CREATE SEQUENCE seq_demo_user
       INCREMENT 50
       START 1
       MINVALUE 1
       MAXVALUE 9223372036854775807
       CACHE 1;
     EXCEPTION
     WHEN duplicate_table THEN
       RAISE NOTICE 'sequence [seq_demo_user] already exists' ;
   END ;
   $$;