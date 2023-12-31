def getAnimalsData():
    animals = {
        'thor': {
            'name': 'Thor',
            'birthDate': '2013-10-01',
            'sex': 'M',
            'waiting_adoption': 1,
            'foster_care': 1,
            'short_info': 'Chocolate doggo',
            'long_info': 'Thor, our superhero pup, was rescued from an urban area where he roamed the streets. While some neighbours lovingly fed him, most treated him poorly. This cuddly and intelligent little guy is a true alpha male, enjoying cozy spots and indulging in delicious treats.\nAt almost 11 years old, Thor defies his age, running, playing and outsmarting like a one-yer-old pup. He is a well-mannered dog, having learned the ropes in his foster home. Thor has a clear preference for females, both human and animal, acting as their devoted protector and bodyguard!',
            'breed': 'Mixed',
            'type_animal': 'DOG',
            'entry_date': '2015-12-05',
            'photo_animal': 'res/drawable/thor1.jpg, res/drawable/thor2.jpg, res/drawable/thor3.jpg, res/drawable/thor4.jpg, res/drawable/thor5.jpg, res/drawable/thor6.jpg, res/drawable/thor_hela.jpg, res/drawable/thor_hermes1.jpg, res/drawable/thor_hermes2.jpg',
        },
        'hermes': {
            'name': 'Hermes',
            'birthDate': '2019-05-23',
            'sex': 'M',
            'waiting_adoption': 1,
            'foster_care': 1,
            'short_info': 'Black wolfie',
            'long_info': 'Rescued from a bleak situation where he was tied to a tree, surviving on bread and milk from a dirty bucket, Hermes was saved alongside his puppy offspring and his female companion.\nInitially timid, especially around men, Hermes is overcoming his fears gradually, particularly when showered with affection. He is the most loyal dog you will never find, capturing hearts with his intense gaze.\nHermes is a protective companion, perfect for strolling down the streat with you.',
            'breed': 'Mixed',
            'type_animal': 'DOG',
            'entry_date': '2020-12-16',
            'photo_animal': 'res/drawable/hermes1.jpg, res/drawable/hermes2.jpg, res/drawable/hermes3.jpg, res/drawable/hermes4.jpg, res/drawable/hermes5.jpg, res/drawable/hermes6.jpg, res/drawable/hermes_hela1.jpg, res/drawable/thor_hermes1.jpg, res/drawable/hermes_hela2.jpg, res/drawable/thor_hermes2.jpg',
        },
        'hela': {
            'name': 'Hela',
            'birthDate': '2022-11-02',
            'sex': 'M',
            'waiting_adoption': 1,
            'foster_care': 1,
            'short_info': 'Black panther',
            'long_info': 'This little bundle of joy, rescued alongside his mother Coco (also up for adoption), is the sole survivor of three siblings. Hela, the playful cat, revolves around playing, sleeping and eating. He manages his own meals, is calm and highly independent.\nHela is obsessed with wires, hanging objects and platforms, finding endless joy in these playful pursuits.\nWhile he enjoys affection in his own way, be cautious - Hela can quickly become uncomfortable, showcasing his quirky personality. He needs a loving, playful and patient adopter.',
            'breed': 'Common European',
            'type_animal': 'CAT',
            'entry_date': '2023-08-03',
            'photo_animal': 'res/drawable/hela1.jpg, res/drawable/hela2.jpg, res/drawable/hela3.jpg, res/drawable/hela4.jpg, res/drawable/hela5.jpg, res/drawable/hela6.jpg, res/drawable/hela7.jpg, res/drawable/hela8.jpg, res/drawable/hela9.jpg, res/drawable/hela10.jpg, res/drawable/hermes_hela.jpg, res/drawable/coco_hela.jpg',
        },
        'coco': {
            'name': 'Coco',
            'birthDate': '2021-11-23',
            'sex': 'F',
            'waiting_adoption': 1,
            'foster_care': 1,
            'short_info': 'Potato couch',
            'long_info': 'Coco arrived at an urban area pregnant and starving at just 9 months old. Compassionate neighbours came to her aid, providing food, caring for he and her babies, and nursing her back to health. After giving birth, Coco underwent a minor surgery to remove a small tumor. With the love and care she received, she started trusting people more.\nDespite a tough past, where only one of her three puppies, Hela, survived (also up for adoption), Coco now lives harmoniously with other two dogs and Hela in a foster home.\nThis affectionate kitty, a true potato couch, loves lounging on people and purring. Coco will never harm you; she is incredibly calm, even when marking her territory, doing so with the utmost gentleness.',
            'breed': 'Common European',
            'type_animal': 'CAT',
            'entry_date': '2023-07-26',
            'photo_animal': 'res/drawable/coco1.jpg, res/drawable/coco2.jpg, res/drawable/coco3.jpg, res/drawable/coco4.jpg, res/drawable/coco5.jpg, res/drawable/coco6.jpg, res/drawable/coco7.jpg, res/drawable/coco_hela.jpg',
        },
        'zipi': {
            'name': 'Zipiel',
            'birthDate': '2013-02-02',
            'sex': 'F',
            'waiting_adoption': 1,
            'foster_care': 1,
            'short_info': 'Wise doggo',
            'long_info': 'She was found 10 years ago in a box near a park. She is not very sociable with other dogs but very affectionate with humans. She loves company and follows people everywhere asking for cuddles.\nShe is very afraid of loud sounds like firecrackers or thunder and tends to hide when she hears them. She is a chihuahua and yorkshire mix.',
            'breed': 'Mixed',
            'type_animal': 'DOG',
            'entry_date': '2013-03-16',
            'photo_animal': 'res/drawable/zipi1.jpg, res/drawable/zipi2.jpg, res/drawable/zipi3.jpg, res/drawable/zipi4.jpg',
        },
        'rocco': {
            'name': 'Rocco',
            'birthDate': '2022-08-18',
            'sex': 'M',
            'waiting_adoption': 1,
            'foster_care': 0,
            'short_info': 'Cuddly shep',
            'long_info': 'He is the best life companion, super cute and super cuddly with everyone, including cats!\nHe does not know how to drink water and sometimes he lies down in strange positions.',
            'breed': 'German Shepperd',
            'type_animal': 'DOG',
            'entry_date': '2022-10-23',
            'photo_animal': 'res/drawable/rocco1.jpg, res/drawable/rocco2.jpg, res/drawable/rocco3.jpg, res/drawable/rocco4.jpg, res/drawable/rocco5.jpg, res/drawable/rocco6.jpg, res/drawable/rocco7.jpg, res/drawable/roco_mia.jpg',
        },
        'luna': {
            'name': 'Luna',
            'birthDate': '2010-01-01',
            'sex': 'F',
            'waiting_adoption': 1,
            'foster_care': 0,
            'short_info': 'The old lady',
            'long_info': 'She loves to be pampered. Sometimes eating bothers her and she needs special care for her scalp.',
            'breed': 'Yorkshire',
            'type_animal': 'DOG',
            'entry_date': '2013-06-12',
            'photo_animal': 'res/drawable/luna1.jpg, res/drawable/luna2.jpg, res/drawable/luna3.jpg, res/drawable/luna4.jpg, res/drawable/luna5.jpg, res/drawable/luna6.jpg, res/drawable/pese_luna_mia.jpg',
        },
        'karen': {
            'name': 'Karen',
            'birthDate': '2022-04-23',
            'sex': 'F',
            'waiting_adoption': 1,
            'foster_care': 0,
            'short_info': 'Whisker joy',
            'long_info': 'Super cuddly and adventurous cat. I love to wallow senselessly when petted.',
            'breed': 'Common European',
            'type_animal': 'CAT',
            'entry_date': '2023-06-09',
            'photo_animal': 'res/drawable/karen1.jpg, res/drawable/karen2.jpg, res/drawable/karen3.jpg, res/drawable/karen4.jpg, res/drawable/karen5.jpg, res/drawable/karen6.jpg, res/drawable/karen7.jpg, res/drawable/karen8.jpg, res/drawable/karen9.jpg, res/drawable/abuela_karen1.jpg, res/drawable/abuela_karen2.jpg',
        },
        'abuela': {
            'name': 'Abuela',
            'birthDate': '2020-04-23',
            'sex': 'F',
            'waiting_adoption': 1,
            'foster_care': 0,
            'short_info': 'Sweed grandpaw',
            'long_info': 'She loves company and is super cuddly. She gets along well with everyone. Everyone loves her.',
            'breed': 'Common European',
            'type_animal': 'CAT',
            'entry_date': '2020-12-26',
            'photo_animal': 'res/drawable/abuela1.jpg, res/drawable/abuela2.jpg, res/drawable/abuela3.jpg, res/drawable/abuela4.jpg, res/drawable/abuela5.jpg, res/drawable/abuela6.jpg, res/drawable/abuela7.jpg, res/drawable/abuela8.jpg, res/drawable/abuela9.jpg, res/drawable/abuela10.jpg, res/drawable/abuela_karen1.jpg, res/drawable/abuela_karen2.jpg, res/drawable/abuela_trico.jpg',
        },
        'trico': {
            'name': 'Trico',
            'birthDate': '2022-09-23',
            'sex': 'F',
            'waiting_adoption': 1,
            'foster_care': 0,
            'short_info': 'Cuddle explorer',
            'long_info': 'She loves company and is super cuddly. She likes exploring her surroundings and people\'s houses. She is super cute.',
            'breed': 'Common European',
            'type_animal': 'CAT',
            'entry_date': '2023-03-15',
            'photo_animal': 'res/drawable/trico1.jpg, res/drawable/trico2.jpg, res/drawable/trico3.jpg, res/drawable/trico4.jpg, res/drawable/trico5.jpg, res/drawable/trico6.jpg, res/drawable/abuela_trico.jpg',
        },
        'pese': {
            'name': 'Pese',
            'birthDate': '2017-06-28',
            'sex': 'M',
            'waiting_adoption': 1,
            'foster_care': 1,
            'short_info': 'Sunny pup',
            'long_info': 'He is super sociable, he does not get along very well with male dogs older than him. He is funny-photo magnet.',
            'breed': 'Mixed',
            'type_animal': 'DOG',
            'entry_date': '2017-08-29',
            'photo_animal': 'res/drawable/pese1.jpg, res/drawable/pese2.jpg, res/drawable/pese3.jpg, res/drawable/pese4.jpg, res/drawable/pese5.jpg, res/drawable/pese6.jpg, res/drawable/pese_luna_mia.jpg',
        },
        'mia': {
            'name': 'Mia',
            'birthDate': '2022-02-13',
            'sex': 'F',
            'waiting_adoption': 1,
            'foster_care': 0,
            'short_info': 'Silly doggie',
            'long_info': 'She loves sleeping with someone. The poor thing is a little crazy, she has a lot of temperament and enjoys playing on her own.',
            'breed': 'Majorero dog',
            'type_animal': 'DOG',
            'entry_date': '2022-04-06',
            'photo_animal': 'res/drawable/pese_luna_mia.jpg, res/drawable/miaa1.jpg, res/drawable/miaa2.jpg, res/drawable/miaa3.jpg, res/drawable/roco_mia.jpg',

        },
        'patera': {
            'name': 'Patera',
            'birthDate': '2023-06-14',
            'sex': 'M',
            'waiting_adoption': 1,
            'foster_care': 0,
            'short_info': 'Scrounge master',
            'long_info': 'Meet Patera, the charming cat who has overcome a challenging start in life. Patera was discovered in a pile of garbage, hidden behind ceramics, at just one and a half months old. Despite his dramatic beginning, Patera has blossomed into a playful, intelligent and affectionate feline.\nHis time in foster care has been filled with love and attention, making him well-prepared to explore a home of his own.\nPatera is a curious soul, unafraid to investigate every nook and caranny.\nHe has had interactions with dogs twice as big his size without any issues, showcasing his adaptability. His curiosity knows no bounds, and he is ready to bring joy and laughter to a new home.',
            'breed': 'Common European',
            'type_animal': 'CAT',
            'entry_date': '2023-08-31',
            'photo_animal': 'res/drawable/patera1.jpg, res/drawable/patera2.jpg, res/drawable/patera3.jpg, res/drawable/patera4.jpg',
        },
        'dorito': {
            'name': 'Dorito',
            'birthDate': '2021-06-05',
            'sex': 'M',
            'waiting_adoption': 1,
            'foster_care': 0,
            'short_info': 'Affy tabby',
            'long_info': 'Meet Dorito, the charming orange tabby cat who is as sweet as a bag of chips! This kitty has a distinctive orange coat reminiscent of the famous cat Garfield.\nDorito is a bundle of joy, always seeking your attention. He loves to roll around on the floor while you shower him with affection. He is not one to pick fights; in fact, he is submissive and adores belly and head rubs.\nDespite his affectionate nature, he values his independence and enjoys exploring the great outdoors. Dorito is a goofy fellow who is not afraid of anything; he is truly fearless!\nThis laid-back feline gets along well with dogs who respect his space.',
            'breed': 'Common European',
            'type_animal': 'CAT',
            'entry_date': '2022-09-15',
            'photo_animal': 'res/drawable/dorito1.jpg, res/drawable/dorito2.jpg, res/drawable/dorito3.jpg, res/drawable/dorito4.jpg, res/drawable/dorito5.jpg, res/drawable/dorito6.jpg',
        },
        'odin': {
            'name': 'Odin',
            'birthDate': '2020-06-05',
            'sex': 'M',
            'waiting_adoption': 1,
            'foster_care': 1,
            'short_info': 'Sleepy savior',
            'long_info': 'Found on the street, he was rescued by another shelter and neutered. He is very affectionate with everyone and likes to sleep on people\'s feet.\nRecommended adoption with his best friend Loki (also up for adoption).',
            'breed': 'Common European',
            'type_animal': 'CAT',
            'entry_date': '2021-01-06',
            'photo_animal': 'res/drawable/odin1.jpg, res/drawable/odin2.jpg, res/drawable/odin_loki1.jpg, res/drawable/odin_loki2.jpg',
        },
        'loki': {
            'name': 'Loki',
            'birthDate': '2021-10-05',
            'sex': 'M',
            'waiting_adoption': 1,
            'foster_care': 1,
            'short_info': 'Purrful pilgrim',
            'long_info': 'Rescued from the street, he was found by a loving family who could not keep him. Loki is a very good and calm cat, who loves to play with balls. At first, he usually avoids people but when he gains confidence, he is the most affectionate cat in the world.\nRecommended adoption with his best friend Odin (also up for adoption).',
            'breed': 'Common European',
            'type_animal': 'CAT',
            'entry_date': '2021-12-28',
            'photo_animal': 'res/drawable/loki1.jpg, res/drawable/loki2.jpg, res/drawable/loki3.jpg, res/drawable/odin_loki1.jpg, res/drawable/odin_loki2.jpg',
        },
        'miranda': {
            'name': 'Miranda',
            'birthDate': '2020-01-17',
            'sex': 'F',
            'waiting_adoption': 1,
            'foster_care': 1,
            'short_info': 'Chirp maestro',
            'long_info': 'Found in a suburban neighborhood, Miranda is a social and affectionate agapornis. He has a penchant for approaching people, seeking attention and creating a symphony of chirps and trills.\nMiranda delights in perching on shoulders, heads or elbows, establishing a close bond with his human companions. If ignored, she has a charming way of demanding attention through melodious trilling.\nAdditionally, Miranda is a playful spirit, thoroughly enjoying interactive sessions with rubber balls.',
            'breed': 'Agapornis',
            'type_animal': 'BIRD',
            'entry_date': '2020-05-19',
            'photo_animal': 'res/drawable/miranda1.jpg, res/drawable/miranda2.jpg, res/drawable/miranda3.jpg',
        },
        'coco2': {
            'name': 'Coco',
            'birthDate': '2020-10-15',
            'sex': 'F',
            'waiting_adoption': 1,
            'foster_care': 1,
            'short_info': 'Rice Dream',
            'long_info': 'Coco is a charming male canine with a heart full of affection and a hint of timidity.\nHe is a bit shy, but once you get to know him, you will find a playful companion who adores devouring bowls of rice or slurping up macaroni. Coco is the epitome of joy, always ready for a game of fetch or a delightful nap.',
            'breed': 'Mixed',
            'type_animal': 'DOG',
            'entry_date': '2020-12-19',
            'photo_animal': 'res/drawable/coco_paula1.jpg, res/drawable/coco_paula2.jpg, res/drawable/coco_paula3.jpg',
        },
        'blanquita': {
            'name': 'Blanquita',
            'birthDate': '2005-02-03',
            'sex': 'F',
            'waiting_adoption': 1,
            'foster_care': 1,
            'short_info': 'Melody Whisk',
            'long_info': 'Meet Blanquita, the charming 200gr feline songstress who serenades the world with her melodic meows at the delightful hour of 4 AM. This adorable senior lady brings a symphony of joy to your mornings, ensuring you wake up with a smile on your face.',
            'breed': 'Mixed',
            'type_animal': 'CAT',
            'entry_date': '208-06-02',
            'photo_animal': 'res/drawable/blanquita1.jpg, res/drawable/blanquita2.jpg, res/drawable/blanquita3.jpg',
        },
    }
    return animals

def getNewsData():
    news = {
        'neutral1': {
            'title': 'Cat nannie',
            'short_info': 'Short info',
            'long_info': 'Long info',
            'photo_news': 'res/drawable/news_baby_and_cat.jpg',
            'good_news': 0,
        },
        'neutral2': {
            'title': 'Horse ride raffle',
            'short_info': 'Short info',
            'long_info': 'Long info',
            'photo_news': 'res/drawable/news_building_horse_trust.jpg',
            'good_news': 0,
        },
        'neutral3': {
            'title': 'Canicross event on 16/12',
            'short_info': 'Short info',
            'long_info': 'Long info',
            'photo_news': 'res/drawable/news_canicross_race.jpg',
            'good_news': 0,
        },
        'neutral4': {
            'title': 'Walking your dog is healthy',
            'short_info': 'Short info',
            'long_info': 'Long info',
            'photo_news': 'res/drawable/news_dog_health.jpg',
            'good_news': 0,
        },
        'neutral5': {
            'title': 'Raising money for Durango',
            'short_info': 'Short info',
            'long_info': 'Long info',
            'photo_news': 'res/drawable/news_charity_event_money_raising_durango.jpg',
            'good_news': 0,
        },
        'neutral6': {
            'title': 'Leo\'s 24th birthday',
            'short_info': 'Short info',
            'long_info': 'Long info',
            'photo_news': 'res/drawable/news_leo_birthday.jpg',
            'good_news': 0,
        },
        'neutral7': {
            'title': 'New litter found',
            'short_info': 'Short info',
            'long_info': 'Long info',
            'photo_news': 'res/drawable/news_found_abandoned_puppies.jpg',
            'good_news': 0,
        },
        'neutral8': {
            'title': 'Bobi breaks a world record',
            'short_info': 'Short info',
            'long_info': 'Long info',
            'photo_news': 'res/drawable/news_bobi_oldest_dog_world_record.jpg',
            'good_news': 0,
        },
        'good1': {
            'title': 'Daisy found!',
            'short_info': 'Short info',
            'long_info': 'Long info',
            'photo_news': 'res/drawable/news_found_daisy.jpg',
            'good_news': 1,
        },
        'good2': {
            'title': 'Leia found!',
            'short_info': 'Short info',
            'long_info': 'Long info',
            'photo_news': 'res/drawable/news_found_leia.jpg',
            'good_news': 1,
        },
        'good2': {
            'title': 'Pipo found!',
            'short_info': 'Short info',
            'long_info': 'Long info',
            'photo_news': 'res/drawable/news_found_pipo.jpg',
            'good_news': 1,
        },
        'good3': {
            'title': 'Tomasa adopted!',
            'short_info': 'Short info',
            'long_info': 'Long info',
            'photo_news': 'res/drawable/news_adopted_tomasa.jpg',
            'good_news': 1,
        },
        'good4': {
            'title': 'Darcy recovered!',
            'short_info': 'Short info',
            'long_info': 'Long info',
            'photo_news': 'res/drawable/news_recovered_darcy.jpg',
            'good_news': 1,
        },
        'good5': {
            'title': 'Zarko adopted!',
            'short_info': 'Short info',
            'long_info': 'Long info',
            'photo_news': 'res/drawable/news_adopted_zarko.jpg',
            'good_news': 1,
        },
        'lost1': {
            'title': 'Irvine lost near Siam Park',
            'short_info': 'Short info',
            'long_info': 'Long info',
            'photo_news': 'res/drawable/lost_irvine.jpg',
            'good_news': 0,
        },
        'lost2': {
            'title': 'Pumba lost in Icod',
            'short_info': 'Short info',
            'long_info': 'Long info',
            'photo_news': 'res/drawable/lost_pumba.jpg',
            'good_news': 0,
        },
        'lost3': {
            'title': 'Rocky lost in Radazul',
            'short_info': 'Short info',
            'long_info': 'Long info',
            'photo_news': 'res/drawable/lost_rocky.jpg',
            'good_news': 0,
        },
        'lost4': {
            'title': 'Donna lost in Garachico',
            'short_info': 'Short info',
            'long_info': 'Long info',
            'photo_news': 'res/drawable/lost_donna.jpg',
            'good_news': 0,
        },
    }
    return news
