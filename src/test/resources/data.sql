DELETE FROM subscriber;

INSERT INTO subscriber (id,name, email) VALUES ('123e4567-e89b-12d3-a456-426614174001','John Doe', 'john.doe@example.com');
INSERT INTO subscriber (id,name, email) VALUES ('123e4567-e89b-12d3-a456-426614174002','Jane Doe', 'jane.doe@example.com');
INSERT INTO subscriber (id,name, email) VALUES ('123e4567-e89b-12d3-a456-426614174003','John2 Doe', 'john2.doe@example.com');
INSERT INTO subscriber (id,name, email) VALUES ('123e4567-e89b-12d3-a456-426614174004','Jane2 Doe', 'jane2.doe@example.com');
INSERT INTO subscriber (id,name, email) VALUES ('123e4567-e89b-12d3-a456-426614174005','John3 Doe', 'john3.doe@example.com');
INSERT INTO subscriber (id,name, email) VALUES ('123e4567-e89b-12d3-a456-426614174006','Jane3 Doe', 'jane3.doe@example.com');
INSERT INTO subscriber (id,name, email) VALUES ('123e4567-e89b-12d3-a456-426614174007','John4 Doe', 'john4.doe@example.com');
INSERT INTO subscriber (id,name, email) VALUES ('123e4567-e89b-12d3-a456-426614174008','Jane4 Doe', 'jane4.doe@example.com');
INSERT INTO subscriber (id,name, email) VALUES ('123e4567-e89b-12d3-a456-426614174009','John5 Doe', 'john5.doe@example.com');
INSERT INTO subscriber (id,name, email) VALUES ('123e4567-e89b-12d3-a456-426614174010','Jane5 Doe', 'jane5.doe@example.com');


INSERT INTO subscription_type (name) VALUES ('Tournament Tracker');
INSERT INTO subscription_type (name) VALUES ('Favorite Player');


INSERT INTO subscription (id,subscriber_id,subscription_type,configuration,creation_date,active)
VALUES ('123e4567-e89b-12d3-a455-426614174001','123e4567-e89b-12d3-a456-426614174001',1,null,CURRENT_DATE(),true);

INSERT INTO subscription (id,subscriber_id,subscription_type,configuration,creation_date,active)
VALUES ('123e4567-e89b-12d3-a455-426614174002','123e4567-e89b-12d3-a456-426614174001',2,null,CURRENT_DATE(),true);

INSERT INTO subscription (id,subscriber_id,subscription_type,configuration,creation_date,active)
VALUES ('123e4567-e89b-12d3-a455-426614174003','123e4567-e89b-12d3-a456-426614174002',1,null,CURRENT_DATE(),true);

INSERT INTO subscription (id,subscriber_id,subscription_type,configuration,creation_date,active)
VALUES ('123e4567-e89b-12d3-a455-426614174004','123e4567-e89b-12d3-a456-426614174003',1,null,CURRENT_DATE(),true);



