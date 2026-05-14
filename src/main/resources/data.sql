-- Insert Authors
INSERT INTO authors (full_name, biography) VALUES ('J.K. Rowling','British author best known for the Harry Potter fantasy series.');
INSERT INTO authors (full_name, biography) VALUES ('George Orwell', 'English novelist famous for 1984 and Animal Farm.');
INSERT INTO authors (full_name, biography) VALUES ('F. Scott Fitzgerald', 'American writer known for his novel The Great Gatsby.');
INSERT INTO authors (full_name, biography) VALUES ('J.R.R. Tolkien', 'English writer and author of The Hobbit and The Lord of the Rings.');


-- Insert Categories
INSERT INTO categories (name) VALUES ('Fantasy');
INSERT INTO categories (name) VALUES ('Dystopian');
INSERT INTO categories (name) VALUES ('Classic Literature');
INSERT INTO categories (name) VALUES ('Adventure');

-- Insert Books
-- (Assuming IDs start from 1 for authors and categories)
INSERT INTO books (title, isbn, stock_quantity, author_id, category_id)
VALUES ('Harry Potter and the Philosophers Stone', '978-0-7475-3269-9', 120, 1, 1);

INSERT INTO books (title, isbn, stock_quantity, author_id, category_id)
VALUES ('1984', '978-0-452-28423-4', 85, 2, 2);

INSERT INTO books (title, isbn, stock_quantity, author_id, category_id)
VALUES ('The Great Gatsby', '978-0-7432-7356-5', 45, 3, 3);

INSERT INTO books (title, isbn, stock_quantity, author_id, category_id)
VALUES ('The Hobbit', '978-0-261-10221-7', 60, 4, 1);