 INSERT INTO address
 (city, country)
 VALUES
 ( 'Voronezh', 'Russia');


 INSERT INTO person
 (email, phone, photo, about,
 status_code, first_name, last_name, birth_day, messages_permission,
 last_online_time, is_online, is_blocked, is_deleted, created_on,
 updated_on, password, address)
 VALUES
 ( 'test@gmail.com', '79039939393', 'somelink.ru',  'I love to read books',
 'NONE',  'Viktor',  'Popov', '1995-11-11', 'ALL',
 '2022-11-09', 'true', 'false','false', '2022-11-09',
 '2022-11-04', 'password124', (SELECT id FROM address WHERE city ='Voronezh'));



