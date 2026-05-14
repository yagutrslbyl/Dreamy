-- INSERT AUTHORS
INSERT IGNORE INTO authors (id, full_name, biography)
VALUES
    (1, 'J.K. Rowling', 'British author best known for the Harry Potter fantasy series.'),
    (2, 'George Orwell', 'English novelist famous for 1984 and Animal Farm.'),
    (3, 'F. Scott Fitzgerald', 'American writer known for his novel The Great Gatsby.'),
    (4, 'J.R.R. Tolkien', 'English writer and author of The Hobbit and The Lord of the Rings.');

-- INSERT CATEGORIES
INSERT IGNORE INTO categories (id, name)
VALUES
    (1, 'Fantasy'),
    (2, 'Dystopian'),
    (3, 'Classic Literature'),
    (4, 'Adventure');

-- INSERT BOOKS
INSERT IGNORE INTO books (id, title, isbn, stock_quantity, author_id, category_id)
VALUES
    (1, 'Harry Potter and the Philosophers Stone', '978-0-7475-3269-9', 120, 1, 1),
    (2, '1984', '978-0-452-28423-4', 85, 2, 2),
    (3, 'The Great Gatsby', '978-0-7432-7356-5', 45, 3, 3),
    (4, 'The Hobbit', '978-0-261-10221-7', 60, 4, 1);