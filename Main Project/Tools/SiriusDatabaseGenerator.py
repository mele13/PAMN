import os
import json
import sqlite3
import random
from datetime import datetime, timedelta
from InsertData import *

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
    "photo_news" TEXT NOT NULL,
    "good_news" INTEGER NOT NULL,
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
    "photo_user" TEXT NOT NULL,
    UNIQUE("email"),
    PRIMARY KEY("id")
);
'''

CREATE_LIKED_ANIMAL_TABLE = '''
CREATE TABLE IF NOT EXISTS LikedAnimal (
    "id" INTEGER NOT NULL,
    "user_id" INTEGER NOT NULL,
    "animal_id" INTEGER NOT NULL,
    PRIMARY KEY("id")
);
'''

animal_photo_paths = [
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

user_photo_paths = [
    'res/drawable/user_image1.jpg',
    'res/drawable/user_image2.jpg',
    'res/drawable/user_image3.jpg',
    'res/drawable/user_image4.jpg',
    'res/drawable/user_image5.jpg',
    'res/drawable/user_image6.jpg',
    'res/drawable/user_image7.jpg',
    'res/drawable/user_image8.jpg',
    'res/drawable/user_image9.jpg',
    'res/drawable/user_image10.jpg',
    'res/drawable/user_default_image.jpg',
]

type_animal_enum = ['CAT', 'DOG', 'BIRD']

animals_not_shuffled = getAnimalsData()
animals = list(animals_not_shuffled.values())
random.shuffle(animals)
news = getNewsData()

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
            'photo_animal': animal_photo_paths[i % len(animal_photo_paths)]
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
            'until_date': None,
            'photo_news': 'res/drawable/new_image.jpg',
            'good_news': random.choice([0, 1]),
        }
        for i in range(1, num_news + 1)
    ]

    insert_records(cursor, 'News', news_data)

def create_users(cursor, num_users):
    user_data = [
        {
            'username': 'sele',
            'email': 'sele@example.com',
            'password': 'sele',
            'role': 'admin',
            'photo_user': 'res/drawable/user_default_image.jpg',
        },
        {
            'username': 'mele',
            'email': 'mele@example.com',
            'password': 'mele',
            'role': 'admin',
            'photo_user': 'res/drawable/user_default_image.jpg',
        }
    ]

    insert_records(cursor, 'User', user_data)

def create_liked_animals_table(cursor):
    execute_query(cursor, CREATE_LIKED_ANIMAL_TABLE)
    print("Created table LikedAnimal and inserted 0 records in database")

def create_animals_from_data(cursor, num_animals):
    animal_data = [
        {
            'name': animal['name'],
            'birth_date': animal['birthDate'],
            'sex': animal['sex'],
            'waiting_adoption': animal['waiting_adoption'],
            'foster_care': animal['foster_care'],
            'short_info': animal['short_info'],
            'long_info': animal['long_info'],
            'breed': animal['breed'],
            'type_animal': animal['type_animal'],
            'entry_date': animal['entry_date'],
            'photo_animal': animal['photo_animal'],
        }
        # for name, animal in animals.items()
        for animal in animals
    ][:num_animals]

    insert_records(cursor, 'Animal', animal_data)

def create_news_from_data(cursor, num_news):
    news_data = [
        {
            'title': entry['title'],
            'short_info': entry['short_info'],
            'long_info': entry['long_info'],
            'published_date': (datetime.now() - timedelta(days=random.randint(0, 365 * 2))).strftime('%Y-%m-%d'),
            'created_at': (datetime.now() - timedelta(days=random.randint(0, 30))).strftime('%Y-%m-%d'),
            'until_date': (datetime.now() - timedelta(days=random.randint(30, 30 + 6 * 30))).strftime('%Y-%m-%d'),
            'photo_news': entry['photo_news'],
            'good_news': entry['good_news'],
        }
        for title, entry in news.items()
    ][:num_news]

    insert_records(cursor, 'News', news_data)

def main():
    # Connect to the database and create 'Sirius.db' file if it doesn't exist
    conn = sqlite3.connect(db_path)
    cursor = conn.cursor()

    # Create tables and insert random data
    # create_table_and_insert_data(cursor, 'Animals', CREATE_ANIMALS_TABLE, 12, create_animals)
    # create_table_and_insert_data(cursor, 'News', CREATE_NEWS_TABLE, 5, create_news)
    # create_table_and_insert_data(cursor, 'User', CREATE_USER_TABLE, 2, create_users)

    # Create tables and insert specific data
    create_table_and_insert_data(cursor, 'Animals', CREATE_ANIMALS_TABLE, len(animals), create_animals_from_data)
    create_table_and_insert_data(cursor, 'News', CREATE_NEWS_TABLE, len(news), create_news_from_data)
    create_table_and_insert_data(cursor, 'User', CREATE_USER_TABLE, 2, create_users)
    create_liked_animals_table(cursor)

    # Save changes and close the connection
    conn.commit()
    conn.close()

if __name__ == "__main__":
    main()
