-- Insert Authors
INSERT INTO authors (full_name, biography, created_at)
VALUES
    ('J.K. Rowling', 'British author best known for the Harry Potter fantasy series.', NOW()),
    ('George Orwell', 'English novelist famous for 1984 and Animal Farm.', NOW()),
    ('F. Scott Fitzgerald', 'American writer known for his novel The Great Gatsby.', NOW()),
    ('J.R.R. Tolkien', 'English writer and author of The Hobbit and The Lord of the Rings.', NOW());

-- Insert Categories
INSERT INTO categories (name, created_at)
VALUES
    ('Fantasy', NOW()),
    ('Dystopian', NOW()),
    ('Classic Literature', NOW()),
    ('Adventure', NOW());

-- Insert Books
INSERT INTO books (title, isbn, stock_quantity, author_id, category_id, created_at)
VALUES
    ('Harry Potter and the Philosophers Stone', '978-0-7475-3269-9', 120, 1, 1, NOW()),
    ('1984', '978-0-452-28423-4', 85, 2, 2, NOW()),
    ('The Great Gatsby', '978-0-7432-7356-5', 45, 3, 3, NOW()),
    ('The Hobbit', '978-0-261-10221-7', 60, 4, 1, NOW());