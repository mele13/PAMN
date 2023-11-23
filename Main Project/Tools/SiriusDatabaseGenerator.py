import os
import sqlite3
import random
from datetime import datetime, timedelta

current_dir = os.path.dirname(os.path.abspath(__file__))
db_path = os.path.join(current_dir, 'Sirius.db')

CREATE_ANIMALS_TABLE = '''
CREATE TABLE IF NOT EXISTS Animal (
    "id" INTEGER NOT NULL,
    "name" TEXT NOT NULL,
    "birth_date" TEXT NOT NULL,
    "sex" TEXT NOT NULL,
    "waiting_adoption" INTEGER NOT NULL,
    "foster_care" INTEGER NOT NULL,
    "short_info" TEXT NOT NULL,
    "long_info" TEXT NOT NULL,
    "breed" TEXT NOT NULL,
    "type_animal" TEXT NOT NULL,
    "entry_date" TEXT NOT NULL,
    "photo_animal" TEXT NOT NULL,
    PRIMARY KEY("id")
);
'''

CREATE_NEWS_TABLE = '''
CREATE TABLE IF NOT EXISTS News (
    "id" INTEGER NOT NULL,
    "title" TEXT NOT NULL,
    "short_info" TEXT NOT NULL,
    "long_info" TEXT NOT NULL,
    "published_date" TEXT NOT NULL,
    "created_at" TEXT NOT NULL,
    "until_date" TEXT,
    UNIQUE("title","published_date"),
    PRIMARY KEY("id")
);
'''

CREATE_USER_TABLE = '''
CREATE TABLE IF NOT EXISTS User (
    "id" INTEGER NOT NULL,
    "username" TEXT NOT NULL,
    "email" TEXT NOT NULL,
    "password" TEXT NOT NULL,
    "role" TEXT NOT NULL,
    PRIMARY KEY("id")
);
'''

photo_paths = [
    'res/drawable/bernese1.jpg',
    'res/drawable/bird1.jpg',
    'res/drawable/budgerigar1.jpg',
    'res/drawable/calico1.jpg',
    'res/drawable/cat1.jpg',
    'res/drawable/dog1.jpg',
    'res/drawable/goldenretriever1.jpg',
    'res/drawable/hollandlop1.jpg',
    'res/drawable/minirex1.jpg'
    'res/drawable/parrot1.jpg',
    'res/drawable/persian1.jpg',
    'res/drawable/rabbit1.jpg',
]

type_animal_enum = ['CAT', 'DOG', 'BIRD']

def execute_query(cursor, query, data=None):
    if data:
        cursor.executemany(query, data)
    else:
        cursor.execute(query)

def insert_records(cursor, table, data):
    placeholders = ', '.join(['?' for _ in data[0]])
    columns = ', '.join(data[0].keys())
    query = f"INSERT INTO {table} ({columns}) VALUES ({placeholders})"
    execute_query(cursor, query, [tuple(record.values()) for record in data])

def create_table_and_insert_data(cursor, table, create_query, num_records, record_data_func):
    cursor.execute(create_query)
    print(f"Created table {table} and inserted {num_records} records in database")
    record_data_func(cursor, num_records)

def create_animals(cursor, num_animals):
    animal_data = [
        {
            'name': f'Animal {i}',
            'birth_date': (datetime.now() - timedelta(days=random.randint(1, 365*10))).strftime('%Y-%m-%d'),
            'sex': random.choice(['M', 'F']),
            'waiting_adoption': random.choice([0, 1]),
            'foster_care': random.choice([0, 1]),
            'short_info': f'Short Info {i}',
            'long_info': f'Long Info {i}',
            'breed': f'Breed {i}',
            'type_animal': random.choice(type_animal_enum),
            'entry_date': datetime.now().strftime('%Y-%m-%d'),
            'photo_animal': photo_paths[i % len(photo_paths)]
        }
        for i in range(1, num_animals + 1)
    ]

    insert_records(cursor, 'Animal', animal_data)

def create_news(cursor, num_news):
    news_data = [
        {
            'title': f'Titulo {i}',
            'short_info': f'Info corta {i}',
            'long_info': f'Info larga {i}',
            'published_date': datetime.now().strftime('%Y-%m-%d'),
            'created_at': datetime.now().strftime('%Y-%m-%d'),
            'until_date': None
        }
        for i in range(1, num_news + 1)
    ]

    insert_records(cursor, 'News', news_data)

def create_users(cursor, num_users):
    user_data = [
        {
            'username': 'sele',
            'email': 'sele@example.com',
            'password': 'adminpassword1',
            'role': 'admin'
        },
        {
            'username': 'mele',
            'email': 'mele@example.com',
            'password': 'adminpassword2',
            'role': 'admin'
        }
    ]

    insert_records(cursor, 'User', user_data)

def main():
    # Connect to the database and create 'Sirius.db' file if it doesn't exist
    conn = sqlite3.connect(db_path)
    cursor = conn.cursor()

    # Create tables and insert data
    create_table_and_insert_data(cursor, 'Animals', CREATE_ANIMALS_TABLE, 12, create_animals)
    create_table_and_insert_data(cursor, 'News', CREATE_NEWS_TABLE, 5, create_news)
    create_table_and_insert_data(cursor, 'User', CREATE_USER_TABLE, 2, create_users)

    # Save changes and close the connection
    conn.commit()
    conn.close()

if __name__ == "__main__":
    main()
