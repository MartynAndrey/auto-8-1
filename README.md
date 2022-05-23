Мой компьютер работает под управлением Windows 8.1 и я был вынужден использовать Docker Toolbox. В качестве SQL Client'а применял DBeaver. Способа запустить Docker Compose на такой основе я не нашел, поэтому для выполнения “задачи № 1 – Скоро deadline” делал следующее.

## Шаги
1. В Docker Toolbox скачал образ для работы с mysql (версии 8) с помощью команды:  
	`$ docker image pull mysql:8`
1. Создал из скачанного образа контейнер и запустил его с помощью команды:  
	`$ docker container run -d -e MYSQL_RANDOM_ROOT_PASSWORD=yes -e MYSQL_DATABASE=db -e MYSQL_USER=user -e MYSQL_PASSWORD=pass -p 3306:3306 mysql:8`  
	![скриншот](https://github.com/MartynAndrey/auto-8-1/blob/master/1.JPG?raw=true)
1. Создал в DBeaver новое соединение с MySQL с соответствующими настройками:
	1. Сервер = 192.168.99.100
	1. Порт = 3306
	1. Пользователь = user
	1. Пароль = pass  
	![скриншот](https://github.com/MartynAndrey/auto-8-1/blob/master/2.JPG?raw=true)
4. Подключился к БД  
	![скриншот](https://github.com/MartynAndrey/auto-8-1/blob/master/3.JPG?raw=true)
5. Открыл SQL консоль для созданного соединения  
	![скриншот](https://github.com/MartynAndrey/auto-8-1/blob/master/4.JPG?raw=true)
6. Вставил в консоль содержимое файла schema.sql и нажал кнопку “Выполнить SQL скрипт”  
	![скриншот](https://github.com/MartynAndrey/auto-8-1/blob/master/5.JPG?raw=true)
7. В БД были созданы необходимые таблицы, о чем свидетельствовало их появление в левой панели окна DBeaver  
	![скриншот](https://github.com/MartynAndrey/auto-8-1/blob/master/6.JPG?raw=true)
