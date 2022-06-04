Мой компьютер работает под управлением Windows 8.1 и я был вынужден использовать Docker Toolbox. Способа запустить Docker Compose на такой основе я не нашел, поэтому для выполнения “задачи № 1 – Скоро deadline” делал следующее.

## Шаги
1. В Docker Toolbox скачал образ для работы с mysql (версии 8) с помощью команды:  
	`$ docker image pull mysql:8`  
1. Создал из скачанного образа контейнер и запустил его с помощью команды:  
	`$ docker container run -d -e MYSQL_RANDOM_ROOT_PASSWORD=yes -e MYSQL_DATABASE=db -e MYSQL_USER=user -e MYSQL_PASSWORD=pass -p 3306:3306 mysql:8`  
	![скриншот](https://github.com/MartynAndrey/auto-8-1/blob/master/img/21.JPG?raw=true)  
1. Запустил файл для создания структуры БД по схеме с помощью команды:  
	`docker exec -i 3a61 mysql -uuser -ppass db < schema.sql`  
	Здесь:  
	1. 3a61 - первые 4 знака из id запущенного контейнера (см. предыдущую картинку)  
	1. db - название созданной БД  
	1. schema.sql - адрес файла со схемой БД  
	![скриншот](https://github.com/MartynAndrey/auto-8-1/blob/master/img/22.JPG?raw=true)  
Т.о. согласно схеме, указанной в файле schema.sql, в БД были созданы необходимые таблицы. В этом можно убедиться, применив DBeaver:  
	![скриншот](https://github.com/MartynAndrey/auto-8-1/blob/master/img/23.JPG?raw=true)
