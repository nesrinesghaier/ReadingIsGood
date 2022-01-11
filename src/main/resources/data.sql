INSERT INTO customers(EMAIL, ADDRESS, FIRST_NAME, LAST_NAME, PASSWORD)
VALUES ('test@test.com', 'test1', 'Nesrine', 'Sghaier',
        '$2a$10$1VoHL/mKfPD4W.P40.GT7OU2CmJ7bcITjR3kGuobTBVQ0NoDPtboi');
INSERT INTO customers(EMAIL, ADDRESS, FIRST_NAME, LAST_NAME, PASSWORD)
VALUES ('test11@test.com', 'test', 'Nesrine', 'Sghaier',
        '$2a$12$bU1Na4bq2SnVzsrvuhKLd.LAO44eQguNmDU0nLjdhUtnC7giHhyxi');
INSERT INTO customers(EMAIL, ADDRESS, FIRST_NAME, LAST_NAME, PASSWORD)
VALUES ('test12@test.com', 'test', 'Nesrine', 'Sghaier',
        '$2a$12$FamgpgGZJi3J4TlTZmFRVu4.GsCLhXrybIi0iSO0Wj7uy807U4ODa');

INSERT INTO books(author, publisher, stock, title, type,price, version)
VALUES ('author1', 'publisher1', 20, 'title1', 'Drama',50,0);
INSERT INTO books(author, publisher, stock, title, type,price, version)
VALUES ('author2', 'publisher2', 10, 'title2', 'Thriller',10,0);
INSERT INTO books(author, publisher, stock, title, type,price, version)
VALUES ('author3', 'publisher3', 5, 'title3', 'Mix',15,0);
INSERT INTO books(author, publisher, stock, title, type,price, version)
VALUES ('integ3', 'integ3', 1, 'integ', 'Mix',20,0);
INSERT INTO orders(order_date_time, status, EMAIL)
VALUES ('2020-12-22 19:10:25-07', 'COMPLETED', 'test11@test.com');
INSERT INTO orders(order_date_time, status, EMAIL)
VALUES ('2021-01-01 13:10:25-07', 'COMPLETED', 'test11@test.com');
INSERT INTO orders(order_date_time, status, EMAIL)
VALUES ('2021-02-01 15:10:25-07', 'COMPLETED', 'test@test.com');
INSERT INTO orders(order_date_time, status, EMAIL)
VALUES ('2021-02-01 10:10:25-07', 'COMPLETED', 'test11@test.com');
INSERT INTO orders(order_date_time, status, EMAIL)
VALUES ('2021-02-025 16:10:25-07', 'PENDING', 'test11@test.com');
INSERT INTO order_details(quantity, book_id, order_id)
VALUES (2, 1, 1);
INSERT INTO order_details(quantity, book_id, order_id)
VALUES (5, 2, 2);
INSERT INTO order_details(quantity, book_id, order_id)
VALUES ( 5, 3, 3);
INSERT INTO order_details(quantity, book_id, order_id)
VALUES (2, 1, 1);
INSERT INTO order_details(quantity, book_id, order_id)
VALUES (10, 1, 1);
INSERT INTO order_details(quantity, book_id, order_id)
VALUES (1, 1, 4);
INSERT INTO order_details(quantity, book_id, order_id)
VALUES (1, 2, 5);