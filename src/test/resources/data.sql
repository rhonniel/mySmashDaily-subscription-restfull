DELETE FROM subscriber;

INSERT INTO subscriber (name, email) VALUES ('John Doe', 'john.doe@example.com');
INSERT INTO subscriber (name, email) VALUES ('Jane Doe', 'jane.doe@example.com');
INSERT INTO subscriber (name, email) VALUES ('John2 Doe', 'john2.doe@example.com');
INSERT INTO subscriber (name, email) VALUES ('Jane2 Doe', 'jane2.doe@example.com');
INSERT INTO subscriber (name, email) VALUES ('John3 Doe', 'john3.doe@example.com');
INSERT INTO subscriber (name, email) VALUES ('Jane3 Doe', 'jane3.doe@example.com');
INSERT INTO subscriber (name, email) VALUES ('John4 Doe', 'john4.doe@example.com');
INSERT INTO subscriber (name, email) VALUES ('Jane4 Doe', 'jane4.doe@example.com');
INSERT INTO subscriber (name, email) VALUES ('John5 Doe', 'john5.doe@example.com');
INSERT INTO subscriber (name, email) VALUES ('Jane5 Doe', 'jane5.doe@example.com');


INSERT INTO subscription_type (name) VALUES ('Tournament Tracker');
INSERT INTO subscription_type (name) VALUES ('Favorite Player');


INSERT INTO subscription (subscriber_id,subscription_type,configuration,creation_date,active)
VALUES (1,1,null,CURRENT_DATE(),true);

INSERT INTO subscription (subscriber_id,subscription_type,configuration,creation_date,active)
VALUES (1,2,null,CURRENT_DATE(),true);

INSERT INTO subscription (subscriber_id,subscription_type,configuration,creation_date,active)
VALUES (2,1,null,CURRENT_DATE(),true);

INSERT INTO subscription (subscriber_id,subscription_type,configuration,creation_date,active)
VALUES (3,1,null,CURRENT_DATE(),true);



