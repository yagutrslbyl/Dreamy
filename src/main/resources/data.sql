-- Insert Authors
INSERT INTO authors (name) VALUES ('J.K. Rowling');
INSERT INTO authors (name) VALUES ('George Orwell');
INSERT INTO authors (name) VALUES ('F. Scott Fitzgerald');
INSERT INTO authors (name) VALUES ('J.R.R. Tolkien');

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