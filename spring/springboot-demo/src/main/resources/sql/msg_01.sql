DELETE FROM resource_bundle WHERE KEY='test.01';
INSERT INTO resource_bundle (id, key, value, locale, created_on, updated_on) VALUES (nextval('seq_resource_bundle'),'test.01','test','en',now(),now());
INSERT INTO resource_bundle (id, key, value, locale, created_on, updated_on) VALUES (nextval('seq_resource_bundle'),'test.01','测试','zh',now(),now());
