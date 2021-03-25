[![Codacy Badge](https://api.codacy.com/project/badge/Grade/69d104afa8bc431a8d661d1f1a670690)](https://www.codacy.com/app/miroleg/SmartRestaurant?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=miroleg/SmartRestaurant;utm_campaign=Badge_Grade)

# SmartRestaurant
Restaurant Orders Management




## Развитие проекта (branch main)  В разработке

<a name="description"></a>
**Формулировка задачи:**


Разработать и реализовать приложение REST API с использованием Hibernate/Spring/SpringMVC (или Spring-Boot) без фронтэнда.

Задача:  Необходимо написать серверную часть управления рестораном. БЕЗ FrontEnd!

1.       Ресторан (это и есть ТЗ) дает меню со списком блюд Клиенту (клиента писать не надо!) по его запросу. Все запросы RESTfull.

2.       Клиент делает запрос Ресторану со списком еды, которую он выбрал (механизм выбора находится на стороне клиента, поэтому его писать не надо!)

3.       Ресторан отдает Клиенту совокупное время, которое необходимо для приготовления (время, как и стоимость – часть свойств блюда).

Использовать Spring/Spring Boot, Hibernate, логирование. Все заявки сторяться в базу. Реализацию взаимодействия с базой писать не надо, сделать только интерфейс-заглушку. Тесты Junit сделать только как «заглушка» - без реализации. Проект должен собираться на Maven. Выложить в открытый репозиторий на GitHub, дать ссылку.




## Уже было реализовано в ветке Vote_for_best_restaurant
в проекте  [https://github.com/miroleg/SmartRestaurant](https://github.com/miroleg/SmartRestaurant)


### Содержание ###


[Формулировка задачи](#description)  
[Описание решения](#descriptionsol)  
&nbsp;&nbsp;&nbsp;[1. Код и демо проекта](#codedemo)  
&nbsp;&nbsp;&nbsp;[2. Предназначение, алгоритмы решения и использование приложения](#purpose)  
&nbsp;&nbsp;&nbsp;[3. Используемые инструменты и технологии](#algo)  
&nbsp;&nbsp;&nbsp;[4.Описание REST API](#descrest)  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Роли](#roles)  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Пользователи](#users)    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Рестораны](#restaurants)    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Блюда](#dishes)  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Меню](#menu)  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[Голоса и голосование](#votes)    
&nbsp;&nbsp;&nbsp;[5. Обработка ошибок](#errors)  
&nbsp;&nbsp;&nbsp;[6. Примеры работы с curl](#curlexample)




<a name="description"></a>
**Формулировка задачи:**

Разработайте систему голосования для выбора ресторана для обеда.

В приложении должны быть 2 типа пользователей: администратор и обычный пользователь
Администратор может ввести ресторан и вводить обеденное меню дня (обычно 2-5 пунктов, только название блюда и цена)
Меню меняется каждый день (администраторы делают обновления)

Каждый ресторан предлагает новое меню каждый день.  

---

<a name="descriptionsol"></a>
### Описание решения
<a name="codedemo"></a>
&#x1F539;  **1. Код и демо проекта**

Код проекта находится здесь:  [https://github.com/miroleg/SmartRestaurant](https://github.com/miroleg/SmartRestaurant)

ветка в проекте:  Vote_for_best_restaurant


<a name="purpose"></a>
&#x1F539;  **2. Предназначение, алгоритмы решения и использование приложения**

Приложение предназначено для организации голосования зарегистрированных в сервисе пользователей за обеденное меню ресторанов.
Меню ресторанов публикует пользователь,  имеющий роль администратора ADMIN. Администратор также имеет возможность управлять всеми сущностями приложения.
Предполагаемое использование сервиса - для написания клиентских приложений,
в том числе Web-приложений.
Приложения могут взаимодействовать с сервисом по HTTP.
Формат взаимодействия описан ниже.

<a name="algo"></a>
&#x1F539;  **3. Используемые инструменты и технологии**

Spring Boot, Spring Security, Spring Rest, Spring Data Binding and Validation,
Spring Data JPA, Spring Cashing, Hibernate, H2 Database, Spring Boot Test, JUnit, AssertJ

<a name="descrest"></a>
&#x1F539;  **4.Описание REST API**

Сущности:

    User - предназначена для хранения пользователей системы
    Role - для хранения ролей пользователей, связана с сущностью User отношением Many-To-Many
    Dish - справочник блюд, связана отношением Many-To-One с сущностью Restaurant.
    Restaurant - содержит рестораны
    Menu - для внесения сведений о ежедневном меню ресторанов.
    MenuItem - содержит ссылку на блюдо из Dish, стоимость и ссылку на запись ежедневного меню в Menu
    Vote - содержит сведения о голосовании пользователей.


**REST API:**

    - host - в демо-приложении localhost  
    - port - в демо-приложении 8080  
    - smartrestaurant - имя сервиса
    - host:port/smartrestaurant/v - URL к REST API
    - host:port/smartrestaurant/v/public/register - URL регистрации нового пользователя
    - host:port/smartrestaurant/v/public/menu?date={date} - URL для получения сведения о меню
    - host:port/smartrestaurant/v/public/votes/votingresults?date={date} - URL для получения результатов голосования
    - host:port/smartrestaurant/v/profile/vote/{id} - URL для голосования
    - host:port/smartrestaurant/v/login - URL для входа пользователя в систему
    - host:port/smartrestaurant/v/admin - ресурсы для администрирования системы
    - host:port/smartrestaurant/v/admin/h2 - консоль демо-базы данных

Форматы результатов запросов даны в виде примеров работы с curl.
Следут помнить, что запросы **curl** должны содержать имя и пароль, например

**_curl http:<i></i>//localhost:8080/smartrestaurant/v/admin/restaurants/1/menu/2018-12-28/menuitems -u alex:qwerty2_**

В демо-приложении существуют следующие пользователи:

| Пользователь  | Пароль | Роли |  
| ------------- | ------------- | ------------- |
| mick | qwerty1 | USER |
| alex | qwerty2 | USER, ADMIN |
| joel | qwerty3 | USER |
| bill | qwerty4 | USER |


Работа с программой выглядит следующим образом:
- Администратор системы вводит данные об обеденном меню ресторанов.   
  При необходимости вносит сведения о ресторане, блюде и строке меню, содержащей блюдо и стоимость.
- Пользователь изучает текущее меню по адресу /public/menu?date={date}
- Зарегистрированный пользователь голосует за меню конкретного ресторана по адресу /profile/vote/{restaurant_id}
- Пользователь может самостоятельно зарегистрироваться по адресу /register



&#x1F534;  **Ресурсы, находящиеся по пути** **_http:<i></i>//host:port/smartrestaurant/v/admin/_**, **доступны только для пользователей с ролью ADMIN**

<a name="roles"></a>
&#x1F538;  **Роли**  
&#x1F53C;  **Список функций REST API приложения для ресурса** **_http:<i></i>//host:port/vsmartrestaurant/v/admin/roles:_**

| Ресурс  | POST | GET | PUT | DELETE |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| **/roles**  | Создать роль (данные в RequestBody). Возвращает созданную роль и путь к ресурсу  | Получить все роли | - | Удалить все роли |
| **/roles/{id}** |	- |	Получить данные роли с указанным id | Обновить роль с указанным id (данные в RequestBody). Возвращает обновленную роль и путь к ресурсу | Удалить роль  с указанным id|
| **/roles/search?name={value}**, где value = имя роли |	- |	Получить роль по ее имени | - | -|

[Примеры работы с curl](#rolesexample)

<a name="users"></a>
&#x1F538;  **Пользователи**

&#x1F53C;  **Список функций REST API приложения для ресурса** **_http:<i></i>//host:port/smartrestaurant/v/admin/users:_**

| Ресурс  | POST | GET | PUT | DELETE |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| **/users**  | Создать пользователя (данные в RequestBody). Пользователь создается с ролью USER. Возвращает созданного пользователя и путь к ресурсу  | Получить всех пользователей | - | Удалить всех пользователей |
| **/users/{id}** |	- |	Получить данные пользователя с указанным id | Обновить пользователя с указанным id (данные в RequestBody). Возвращает обновленного пользователя и путь к ресурсу | Удалить пользователя  с указанным id|
| **/users/sort?order={value}**, где value = name или email |	- |	Получить список всех пользователей, отсортированных в соответствии со значением параметра  | - | -|
| **/users/filter?name={value}**, где value = искомая строка |	- |	Получить список всех пользователей, поле name которых содержит значение параметра. Используется %LIKE% | - | -|
| **/users/page?offset={offset}&size={size}**, где offset - номер страницы, size - количество элементов на странице |	- |	Возвращает список пользователей постранично в соттветствие с параметрами запроса | - | -|
| **/{id}/roles** | - |	Получить роли пользователя с указанным id | - | - |
| **/{id}/roles/{roleId}** | Добавить пользователю с указанным id роль с указанным roleId. Возвращает список ролей пользователя. | - | - | Удалить у пользователя с указанным id роль с указанным roleId. |
| **/{id}?enabled={value}** | Установить у пользователя свойсвто enabled | - | - | - | - |

[Примеры работы с curl](#usersexample)

<a name="restaurants"></a>
&#x1F538;  **Рестораны**

&#x1F53C;  **Список функций REST API приложения для ресурса** **_http:<i></i>//host:port/smartrestaurant/v/admin/restaurants:_**

| Ресурс  | POST | GET | PUT | DELETE |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| **/restaurants**  | Создать ресторан (данные в RequestBody). Возвращает созданный ресторан путь к ресурсу  | Получить все рестораны| - | Удалить все рестораны |
| **/restaurants/{id}** |	- |	Получить данные о  ресторане с указанным id | Обновить ресторан с указанным id (данные в RequestBody). Возвращает обновленный ресторан и путь к ресурсу | Удалить ресторан с указанным id|
| **/restaurants/{id}/dishes** | Создать новое блюдо для этого ресторана (данные в BodyRequest) |	Получить данные о  блюдах ресторана с указанным id | - | Удалить все блюда для этого ресторана|
| **/restaurants/{id}/dishes/{dishId}** |	- |	Получить данные о  блюде  указанным dishId ресторана с указанным id | Обновить сведения о блюде с указанным dishId для указанного ресторана | Удалить блюдо с dishId для ресторана id |
| **/restaurants/{id}/menu/menuitems?date={date}** |	Создать новый пункт меню указанного ресторана на указанную дату. В RequestBody должен быть объект  {"id":0,"date":"2018-12-28","restaurant_id":1,"dish_id":4,"price": 23.56}   |	Получить меню указанного ресторана на указанную дату | - | Удалить меню ресторана на указанную дату |
| **/restaurants/{id}/menu/menuitems/{menuitemsId}?date={date}** | Получить сведения о пункте меню |	- | - | Удалить пункт меню с указанным menuItemsId  |

[Примеры работы с curl](#restaurantsexample)

<a name="dishes"></a>
&#x1F538;  **Блюда**

Управление списком блюд производится в контексте конкретного ресторана способами, описанным выше.

<a name="menu"></a>
&#x1F538;  **Меню**

Управление меню производится в контексте конкретного ресторана способами, описанным выше.  
Кроме того, пользователь для голосования может получить меню на указанную дату, содержащее список ресторанов,
список блюд со стоимостью.

**Список функций REST API приложения для ресурса** **_http:<i></i>//host:port/smartrestaurant/v/public:_**


| Ресурс  | POST | GET | PUT | DELETE |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| **/public/menu/?date={date}**, где date-требуемая дата в формате yyyy-mm-dd  | - | Получить меню на дату | - | - |

[Пример работы с curl](#menuserexample)


<a name="votes"></a>
&#x1F538;  **Голоса и голосование**


&#x1F53C;  **Список функций REST API приложения для ресурса** **_http:<i></i>//host:port/smartrestaurant/v/admin/votes:_**

| Ресурс  | POST | GET | PUT | DELETE |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| **/votes**  | - | Получить всю историю голосований | Удалить всю историю голосований |
| **/votes?date={date}**, где date - дата в формат yyyy-mm-dd |	- |	Получить данные о голосовании на указанную дату| - | Удалить историю голосования на указанную дату |


[Пример работы с curl:](#votesadminexample)


Для зарегистрированных пользователей:  
&#x1F53C;  **Список функций REST API приложения для ресурса** **_http:<i></i>//host:port/smartrestaurant/v/profile/:_**

| Ресурс  | POST | GET | PUT | DELETE |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| **/profile/vote?restaurant={restaurantId}**   | Проголосовать за ресторан с указанным restaurantId | - | - |
| **/profile/update_password?userId={id}&oldPassword={oldPassword}&newPassword={newPassword}**, где id - id пользовател   | - | - | - |

Напрмер, смена пароля текущего пользователя:

    curl -X POST 'http://localhost:8080/smartrestaurant/v/profile/update_password?userId=2&oldPassword=qwerty2&newPassword=qwerty22' -u alex:qwerty2

Для пользователей без аккаунта:

&#x1F53C;  **Список функций REST API приложения для ресурса** **_http:<i></i>//host:port/smartrestaurant/v/public/:_**

| Ресурс  | POST | GET | PUT | DELETE |
| ------------- | ------------- | ------------- | ------------- | ------------- |
| **/public/menu?date={date}**   | - | Получить меню ресторанов на указаннную дату | - |
| **/public/votes/votingresults?date={date}**   | - | Получить результаты голосования на указаннную дату | - |
| **/public/register**   | Создать нового пользователя | - | - |


Пользователь может зарегистрировать себя в сервисе командой:

    $ curl --header 'Content-Type: application/json' -X POST --data '{"id":0,"name":"diana", "email":"diana@gmail.com","password":"password", "enabled":true}' http://localhost:8080/smartrestaurant/v/public/register

Возвращается данные созданного пользователя:

    {"id" : 5,"name" : "diana","email" : "diana@gmail.com","password" : "$2a$10$mEU7zurPrMaCFnczpK1Y3ugevic1X87.aYd9jRf9uK2Q01JfnJdhS","enabled" : true}

Пользователь, получив предварительно данные для выбора ресторана по адресу

    http://host:port/smartrestaurant/v/public/menu/{date}

[Пример запроса и ответа](#menuserexample)

может проголосовать послав запрос вида

    curl -X POST http://localhost:8080/smartrestaurant/v/profile/vote?restaurant=1

Результаты голосования доступны по адресу

    http://host:port/smartrestaurant/v/public/votes/votingresults?date={date}

Например, получить результаты голосования на 28.12.2018

     curl http://localhost:8080/smartrestaurant/v/public/votes/votingresults?date=2018-12-28

Получим

```json
[{"restaurantName":"Frenchette","votes":3},{"restaurantName":"Xian Famous Foods","votes":1}]
```



<a name="errors"></a>
&#x1F539;  **5. Обработка ошибок**

Сервис в случае появления ошибки генерирует ошибку и возвращает клиенту в виде JSON объекта.
Формат объекта ошибки:

    {  
        HttpStatus status;  
        String message;  
        List<String> errors;  
    }


Например, в случае попытки создать нового пользователя с именем, совпадающим с уже имеющемся в системе, мы получим ошибку  нарушения целостности данных:  
*{"status":"CONFLICT","message":"could not execute statement; SQL [n/a]; constraint [\"USERS_UNIQUE_NAME_IDX ON PUBLIC.USERS(NAME) VALUES ('diana', 5)\"; SQL statement:\n/* insert org.javatraining.voteforlunch.model.User */ insert into users (id, email, enabled, name, password, registered) values (null, ?, ?, ?, ?, ?) [23505-197]]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement","errors":["Found integrity violation"]}*

        {"status":"CONFLICT","message":"could not execute statement; SQL [n/a]; constraint [\"USERS_UNIQUE_NAME_IDX ON PUBLIC.USERS(NAME) VALUES ('diana', 5)\"; SQL statement:\n/* insert org.javatraining.voteforlunch.model.User */ insert into users (id, email, enabled, name, password, registered) values (null, ?, ?, ?, ?, ?) [23505-197]]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement","errors":["Found integrity violation"]}

Поддерживаются следующие коды операции, возвращаемые сервисом:

    200 - OK
    201 - CREATED
    204 - NO_CONTENT
    301 - FOUND
    400 - BAD_REQUEST
    401 - UNAUTHORIZED
    402 - FORBIDDEN
    404 - NOT_FOUND
    405 - METHOD_NOT_ALLOWED
    409 - CONFLICT
    415 - UNSUPPORTED_MEDIA_TYPE
    500 - INTERNAL_SERVER_ERROR


<a name="curlexample"></a>
### Приложение. Примеры работы с curl

<a name="rolesexample"></a>
**Примеры работы с curl для ресурса** **_http:<i></i>//host:port/smartrestaurant/v/admin/roles:_**

**Получить список ролей:**

     curl http://localhost:8080/smartrestaurant/v/admin/roles 

**Возвращает:**

    [{"id":1,"name":"USER"},{"id":2,"name":"ADMIN"}]  

**Получить роль по id:**

    curl http://localhost:8080/smartrestaurant/v/admin/roles/2

**Возвращает:**

    {"id":2,"name":"ADMIN"}  

**Создать новую роль:**

    curl --header "Content-Type: application/json" --request POST --data '{"id":0,"name":"SUPERADMIN"}' http://localhost:8080/smartrestaurant/v/admin/roles  

**Возвращает:**

    {"id":3,"name":"SUPERADMIN"}  

**Обновить роль:**

    curl --header 'Content-Type: application/json' -X PUT --data '{"id":1,"name":"SPECIAL_USER"}' http://localhost:8080/smartrestaurant/v/admin/roles/1

**Возвращает:**

    {"id":1,"name":"SPECIAL_USER"}  

**Удалить роль с указанным id:**

    curl -X DELETE http://localhost:8080/smartrestaurant/v/admin/roles/3  

**Найти роль с указанным именем:**

    curl http://localhost:8080/smartrestaurant/v/admin/roles/search?name=ADMIN  

**Возвращает:**

    {"id":2,"name":"ADMIN"}  

--------------------------------------------------
<a name="usersexample"></a>
**Примеры работы с curl для ресурса** **_http:<i></i>//host:port/smartrestaurant/v/admin/users:_**

**Получить список пользователей:**

    curl http://localhost:8080/smartrestaurant/v/admin/users/

**Возвращает:**

    [{"id":1,"name":"mick","email":"mick@gmail.com","password":"$2a$10$QCLWZmfLbLD1jXRP/IjX3e9L7XcNmDZdRYbV6icrds.JN2Wy4Fmeq","enabled":true},{"id":2,"name":"alex","email":"alex@gmail.com","password":"$2a$10$qcZdYIAdxliLejgWwcOZt.t5HOpUJ0X6ctHYBSqYwSIKbYLjGzVzC","enabled":true},{"id":3,"name":"joel","email":"joel@gmail.com","password":"$2a$10$y7NUV.il0WoZ4Bm4sSnDGe1MdlznkrELAZ6Z.eDO47/KGJZkIqGvy","enabled":true},{"id":4,"name":"bill","email":"bill@gmail.com","password":"$2a$10$III4LWdEygJq0yUcQzDOYOihtxPHzTlzx1/syBMwsDhoA0B34HUuK","enabled":true}]  

**Получить список пользователей, отсортированный по имени:**

    curl http://localhost:8080/smartrestaurant/v/admin/users/sort?order=name

**Возвращает:**

    [{"id":2,"name":"alex","email":"alex@gmail.com","password":"$2a$10$qcZdYIAdxliLejgWwcOZt.t5HOpUJ0X6ctHYBSqYwSIKbYLjGzVzC","enabled":true},{"id":4,"name":"bill","email":"bill@gmail.com","password":"$2a$10$III4LWdEygJq0yUcQzDOYOihtxPHzTlzx1/syBMwsDhoA0B34HUuK","enabled":true},{"id":3,"name":"joel","email":"joel@gmail.com","password":"$2a$10$y7NUV.il0WoZ4Bm4sSnDGe1MdlznkrELAZ6Z.eDO47/KGJZkIqGvy","enabled":true},{"id":1,"name":"mick","email":"mick@gmail.com","password":"$2a$10$QCLWZmfLbLD1jXRP/IjX3e9L7XcNmDZdRYbV6icrds.JN2Wy4Fmeq","enabled":true}]    

**Получить список пользователей постранично, страница 1 по 2 элемента:**

    curl 'http://localhost:8080/smartrestaurant/v/admin/users/page?page=1&offset=1&size=2'  

**Возвращает:**

    [{"id":3,"name":"joel","email":"joel@gmail.com","password":"$2a$10$y7NUV.il0WoZ4Bm4sSnDGe1MdlznkrELAZ6Z.eDO47/KGJZkIqGvy","enabled":true},{"id":4,"name":"bill","email":"bill@gmail.com","password":"$2a$10$III4LWdEygJq0yUcQzDOYOihtxPHzTlzx1/syBMwsDhoA0B34HUuK","enabled":true}]  

**Получить список пользователей с именем, содержащим строку 'al':**

    curl 'http://localhost:8080/smartrestaurant/v/admin/users/filter?name=al'  

**Возвращает:**

    [{"id":2,"name":"alex","email":"alex@gmail.com","password":"$2a$10$qcZdYIAdxliLejgWwcOZt.t5HOpUJ0X6ctHYBSqYwSIKbYLjGzVzC","enabled":true}]  

**Получить сведения о пользователе с id = 2:**

    curl http://localhost:8080/smartrestaurant/v/admin/users/2

**Возвращает:**

    {"id":2,"name":"alex","email":"alex@gmail.com","password":"$2a$10$qcZdYIAdxliLejgWwcOZt.t5HOpUJ0X6ctHYBSqYwSIKbYLjGzVzC","enabled":true}   

**Создать нового пользователя:**

    curl --header "Content-Type: application/json" --request POST --data '{"id":0,"name":"diana", "email":"diana@gmail.com","password":"password", "enabled":true}' http://localhost:8080/smartrestaurant/v/admin/users  

**Возвращает:**

    {"id":5,"name":"diana","email":"diana@gmail.com","password":"password","enabled":true}  

**Обновить данные пользователя:**

    curl --header "Content-Type: application/json" --request PUT --data '{"id":5,"name":"diana", "email":"diana@gmail.com","password":"password", "enabled":false}' http://localhost:8080/smartrestaurant/v/admin/users/5  

**Возвращает:**

    {"id":5,"name":"diana","email":"diana@gmail.com","password":"password","enabled":false}  

**Удалить всех пользователей:**

    curl -X DELETE http://localhost:8080/smartrestaurant/v/admin/users/  

**Удалить пользователя с id = 2:**

    curl -X DELETE http://localhost:8080/smartrestaurant/v/admin/users/2  

**Получить сведения о ролях пользователя:**

    curl http://localhost:8080/smartrestaurant/v/admin/users/3/roles

**Возвращает:**

    [{"id":1,"name":"USER"},{"id":2,"name":"ADMIN"}]  

**Добавить пользователю  с id = 1 роль с id = 2:**

    curl --request POST http://localhost:8080/smartrestaurant/v/admin/users/1/roles/2  

**Возвращает:**

    [{"id":1,"name":"USER"},{"id":2,"name":"ADMIN"}]   

**Удалить у пользователя с id = 1 роль с id = 2:**

    curl -X DELETE http://localhost:8080/smartrestaurant/v/admin/users/1/roles/2   

**Установить свойство enabled у пользователя с указанным id:**

      curl -X POST http://localhost:8080/smartrestaurant/v/admin/users/2?enabled=false  


**Возвращает:**

    {"id":2,"name":"alex","email":"alex@gmail.com","password":"$2a$10$qcZdYIAdxliLejgWwcOZt.t5HOpUJ0X6ctHYBSqYwSIKbYLjGzVzC","enabled":false}



-------------------------------------------------
<a name="restaurantsexample"></a>
**Примеры работы с curl для ресурса** **_http:<i></i>//host:port/smartrestaurant/v/admin/restaurants:_**

**Получить список ресторанов:**

    curl http://localhost:8080/smartrestaurant/v/admin/restaurants

**Возвращает:**

    [{"id":1,"name":"Frenchette","description":"The everyday French bistro is fresh ....","contact":"241 W Broadway New York, NY 10013","site":"https://www.frenchettenyc.com/","email":"reservations@frenchettenyc.com","phones":"(212) 334-3883"},
     {"id":2,"name":"Xi'an Famous Foods","description":"Xi’an Famous Foods, which started as a stand ....","contact":"45 Bayard St New York, NY 10013","site":"https://www.xianfoods.com/","email":"info@xianfoods.com","phones":"(212) 786-2068"},
     {"id":3,"name":"Le Coucou","description":"Restaurateur Stephen Starr and chef Daniel Rose ....","contact":"138 Lafayette St New York, NY","site":"https://lecoucou.com/","email":"lecoucou.info@starr-restaurants.com","phones":"(212) 271-4252"},
     {"id":4,"name":"Wildair","description":"At Wildair, Jeremiah Stone and Fabian von Hauske — the chef-restaurateurs behind ....","contact":"142 Orchard St New York, NY","site":"http://wildair.nyc/","email":"info@wildair.nyc","phones":"(646) 964-5624"},
     {"id":5,"name":"Uncle Boons","description":"This Nolita lounge is still turning out some of ....","contact":"7 Spring St New York, NY","site":"http://www.uncleboons.com/","email":"info@uncleboons.com","phones":"(646) 370-6650"}]  

**Получить сведения о ресторане с id = 2:**

    curl http://localhost:8080/smartrestaurant/v/admin/restaurants/2    

**Возвращает:**

    {"id":2,"name":"Xi'an Famous Foods","description":"Xi’an Famous Foods, which started as a stand in Flushing, now has more than a dozen locations across NYC. But despite its chain status, the family-run restaurant has maintained quality, gaining cult following status for spicy, tacky hand-ripped noodles. The cumin lamb is particularly popular, but the cold-skin noodles come in close second, often selling out on busy days. Though prices range from location to location, a satisfying meal can be had for under $15 at this counter-service restaurant.","contact":"45 Bayard St New York, NY 10013","site":"https://www.xianfoods.com/","email":"info@xianfoods.com","phones":"(212) 786-2068"}    

**Создать новый ресторан:**

    curl --header "Content-Type: application/json" --request POST --data '{"id":0,"name":"Prince Street Pizza", "description":"Tiny Soho slice shop Prince Street has been slinging its bouncy, thick pizza since 2012, and it has quickly become one of the city’s favorite pizza options.","contact":"27 Prince St A New York, NY 10012","site":"https://princestreetpizzanyc.com", "email":"info@princestreetpizzanyc.com","phones":"212-966-4101"}' http://localhost:8080/smartrestaurant/v/admin/restaurants

**Возвращает:**

    {"id":6,"name":"Prince Street Pizza","description":"Tiny Soho slice shop Prince Street has been slinging its bouncy, thick pizza since 2012, and it has quickly become one of the city’s favorite pizza options.","contact":"27 Prince St A New York, NY 10012","site":"https://princestreetpizzanyc.com","email":"info@princestreetpizzanyc.com","phones":"212-966-4101"}  

**Обновить сведения о ресторане с id = 5**

     $ curl http://localhost:8080/smartrestaurant/v/admin/restaurants/5   

**Возвращает:**

     {"id":5,"name":"Uncle Boons","description":"This Nolita lounge is still turning out some of the city’s most captivating Thai fare, courtesy of Per Se alums Ann Redding and Matt Danzer. Look for dishes like green curry snails, wood-fired yellowtail collar, a spicy lamb laab, or a savory crab fried rice. The space is an eclectic way to start a night out; order an overflowing beer slushie to get in the mood.","contact":"7 Spring St New York, NY","site":"http://www.uncleboons.com/","email":"info@uncleboons.com","phones":"(646) 370-6650"}
     
     {"id":5,"name":"Uncle Boons","description":"This Nolita lounge is still turning out some of the city’s most captivating Thai fare, courtesy of Per Se alums Ann Redding and Matt Danzer. Look for dishes like green curry snails, wood-fired yellowtail collar, a spicy lamb laab, or a savory crab fried rice. The space is an eclectic way to start a night out; order an overflowing beer slushie to get in the mood.","contact":"7 Spring St New York, NY","site":"http://www.uncleboons.com/","email":"info@uncleboons.com","phones":"(646) 370-6650"}
     
     curl --header "Content-Type: application/json" -X PUT --data '{"id":5,"name":"Prince Street Pizza UPDATED", "description":"Tiny Soho slice UPDATED","contact":"27 Prince St A New York, NY 10012","site":"https://princestreetpizzanyc.com", "email":"info@princestreetpizzanyc.com","phones":"212-966-4101"}' http://localhost:8080/voteforlunch/api/v1/admin/restaurants/5

**Возвращает:**

    {"id":5,"name":"Prince Street Pizza UPDATED","description":"Tiny Soho slice UPDATED","contact":"27 Prince St A New York, NY 10012","site":"https://princestreetpizzanyc.com","email":"info@princestreetpizzanyc.com","phones":"212-966-4101"}

**Удалить ресторан с указанным id:**

    curl -X DELETE http://localhost:8080/smartrestaurant/v/admin/restaurants/2  

**Удалить все рестораны:**

    curl -X DELETE http://localhost:8080/smartrestaurant/v/admin/restaurants/

**Получить список блюд ресторана с указанным id = 2:**

    curl http://localhost:8080/smartrestaurant/v/admin/restaurants/2/dishes/ 

**Возвращает:**

    [{"id":6,"name":"Lamb Pao-Mo Soup","description":"Our housemade unleavened bread boiled in lamb broth with sliced lamb meat and scallions, topped with cilantro."},{"id":7,"name":"Spicy Cumin Lamb Hand-Ripped Noodles","description":"Our biangbiang wide, hand-ripped noodles mixed with sauteed spicy cumin lamb."},{"id":8,"name":"Spicy & Tingly Beef Hand-Ripped Noodles","description":"Our biangbiang wide, hand-ripped noodles mixed with chunks of lean beef, with a spicy and tingly (because of Sichuan peppercorns) sauce."},{"id":9,"name":"Chang'An Spicy Tofu","description":"Housemade soft tofu drizzled with soy sauce, vinegar, chili oil, and fresh cilantro."},{"id":10,"name":"Pork \"Zha Jiang\" Hand-Ripped Noodles","description":"Our wide hand-ripped biangbiang noodles mixed with a savory, and slightly-sweet ground pork meat sauce, tossed with cucumbers, scallions, celery, and chives."},{"id":11,"name":"Stewed Pork Burger","description":"Pork belly meat, stewed and diced with its own juices, then packed into a warm and crispy flatbread-like bun."},{"id":12,"name":"Stir-Fried Liang Pi \"Cold-Skin Noodles\"","description":"Chewy wheat flour noodles, quickly stir-fried with bean sprouts, cucumbers, cilantro, and cubes of spongy gluten, in all our proprietary sauces, which includes soy sauce, black vinegar, chili oil (unless requested not spicy)."},{"id":13,"name":"Stewed Oxtail Hand-Ripped Noodles in Soup","description":"Our biangbiang wide, hand-ripped noodles, topped with sliced stewed oxtails which were stewed in soy sauce and spices, in a beef broth."}]

**Получить сведения о блюде с указанным id для ресторана с указанным id:**

    curl http://localhost:8080/smartrestaurant/v/admin/restaurants/3/dishes/14

**Возвращает:**

    {"id":14,"name":"Huitres, Granite aux Algues","description":"oysters, seaweed ice"}


**Создать новое блюдо для ресторана с указанным id:**

    curl --header "Content-Type: application/json" --request POST --data '{"id":0,"name":"New Dish", "description":"New Description"}' http://localhost:8080/smartrestaurant/v/admin/restaurants/1/dishes/

**Возвращает:**

    {"id":51,"name":"New Dish","description":"New Description"}

**Удалить блюдо с указанным id для ресторана с указанным id:**

    curl -X DELETE http://localhost:8080/smartrestaurant/v/admin/restaurants/1/dishes/2

**Удалить весь список блюд для ресторана с указанным id:**

    curl -X DELETE http://localhost:8080/smartrestaurant/v/admin/restaurants/1/dishes/

**Обновить сведения о блюде с указанным id для указанного ресторана:**

    curl --header "Content-Type: application/json" --request PUT --data '{"id":3,"name":""Salmon UPDATED", "description":"UPDATED Description"}' http://localhost:8080/smartrestaurant/v/admin/restaurants/1/dishes/3

**Возвращает:**

    {"id":3,"name":"Salmon UPDATED","description":"UPDATED Description"}


**Получить сведения о меню ресторана на указанную дату:**

     curl http://localhost:8080/smartrestaurant/v/admin/restaurants/1/menu/menuitems?date=2018-12-28 -u alex:qwerty2

**Возвращает:**

    [{"id":1,"datei":"2018-12-28","restaurant_id":1,"dish_id":1,"price":10.00},{"id":2,"datei":"2018-12-28","restaurant_id":1,"dish_id":2,"price":11.00},{"id":3,"datei":"2018-12-28","restaurant_id":1,"dish_id":3,"price":12.00}]

**Удалить меню указанного ресторана на указанную дату:**

     curl -X DELETE http://localhost:8080/smartrestaurant/v/admin/restaurants/1/menu/menuitems?date=2018-12-28 -u alex:qwerty2

**Создать пункт меню меню указанного ресторана на указанную дату с указанным блюдом с указанной стоимостью:**

      curl --header "Content-Type: application/json" --request POST --data '{"id":0,"date":"2018-12-28","restaurant_id":1,"dish_id":4,"price": 23.56}' http://localhost:8080/smartrestaurant/v/admin/restaurants/1/menu/menuitems?date=2018-12-28 -u alex:qwerty2

**Возвращает:**

    {"id":28,"date":"2018-12-28","restaurant_id":1,"dish_id":4,"price":23.56}

---------------------------------------------
<a name="menuserexample"></a>
**Примеры работы c curl для ресурса** **_http:<i></i>//host:port/smartrestaurant/v/public/menu:_**

**Получить меню на указанную дату:**

     curl  http://localhost:8080/smartrestaurant/v/public/menu?date=2018-12-28

**Возвращает:**
```json
    [
        {
            "date": "2018-12-28",
            "restaurant": {
                "id": 3,
                "name": "Le Coucou",
                "description": "Restaurateur Stephen Starr and chef Daniel Rose take cues from traditional French restaurants, transforming their place into one of the most exciting upscale restaurants in гserFromUserTo York. The dining room offers perfect light in a room adorned in stately yet stylish decor. The menu is obvious in its luxuries: Lobster, foie gras, and oysters all make appearances. Also look for dishes like the caviar course and the halibut beurre blanc. For dessert, do not miss the omelette Norvegienne, essentially a baked Alaska.",
                "contact": "138 Lafayette St New York, NY",
                "site": "https://lecoucou.com/",
                "email": "lecoucou.info@starr-restaurants.com",
                "phones": "(212) 271-4252"
            },
            "dishes": [
                {
                    "id": 14,
                    "name": "Huîtres, Granité aux Algues",
                    "description": "oysters, seaweed ice",
                    "price": 22
                },
                {
                    "id": 15,
                    "name": "Velouté de Pommes de Terre",
                    "description": "crème fraîche, caviar",
                    "price": 23
                },
                {
                    "id": 16,
                    "name": "Quenelle de brochet “Route de Reims”",
                    "description": "champagne beurre blanc, caviar",
                    "price": 24
                }
            ]
        },
        {
            "date": "2018-12-28",
            "restaurant": {
                "id": 2,
                "name": "Xian Famous Foods",
                "description": "Xian Famous Foods, which started as a stand in Flushing, now has more than a dozen locations across NYC. But despite its chain status, the family-run restaurant has maintained quality, gaining cult following status for spicy, tacky hand-ripped noodles. The cumin lamb is particularly popular, but the cold-skin noodles come in close second, often selling out on busy days. Though prices range from location to location, a satisfying meal can be had for under $15 at this counter-service restaurant.",
                "contact": "45 Bayard St New York, NY 10013",
                "site": "https://www.xianfoods.com/",
                "email": "info@xianfoods.com",
                "phones": "(212) 786-2068"
            },
            "dishes": [
                {
                    "id": 6,
                    "name": "Lamb Pao-Mo Soup",
                    "description": "Our housemade unleavened bread boiled in lamb broth with sliced lamb meat and scallions, topped with cilantro.",
                    "price": 11.11
                },
                {
                    "id": 7,
                    "name": "Spicy Cumin Lamb Hand-Ripped Noodles",
                    "description": "Our biangbiang wide, hand-ripped noodles mixed with sauteed spicy cumin lamb.",
                    "price": 12.99
                },
                {
                    "id": 8,
                    "name": "Spicy & Tingly Beef Hand-Ripped Noodles",
                    "description": "Our biangbiang wide, hand-ripped noodles mixed with chunks of lean beef, with a spicy and tingly (because of Sichuan peppercorns) sauce.",
                    "price": 13.44
                }
            ]
        },
        {
            "date": "2018-12-28",
            "restaurant": {
                "id": 1,
                "name": "Frenchette",
                "description": "The everyday French bistro is fresh again at Frenchette, a lively restaurant in Tribeca from the chefs behind mainstays like Balthazar. Riad Nasr and Lee Hanson offer a constantly changing menu with simple yet compelling options like rotisserie lobster, duck frites, and charred carrots. A natural wine list culled by Jorge Riera means that both by-the-glass and bottle lists are worth exploring, too. But a warm room and even warmer service makes Frenchette a modern destination, where downtown dining feels alive. Reservations, or dining in the bar area, are highly recommended.",
                "contact": "241 W Broadway New York, NY 10013",
                "site": "https://www.frenchettenyc.com/",
                "email": "reservations@frenchettenyc.com",
                "phones": "(212) 334-3883"
            },
            "dishes": [
                {
                    "id": 1,
                    "name": "Buffalo Chicken Salad",
                    "description": "The Count: 1,130 calories, 74 grams fat, 3,290 milligrams sodium.\"Salad\" is stretching it! Fried meat, oily sauce, and cheese push the calories in this meal through the roof at one popular restaurant. It has about as many as a whole pint of chocolate chip cookie dough ice cream. The salad also packs nearly 25% more fat.",
                    "price": 10
                },
                {
                    "id": 2,
                    "name": "Fried Rice with Vegetables",
                    "description": "The Count:  910 calories, 16 grams fat, 1,360 milligrams sodiumGetting Chinese takeout? Dont assume the veggie options are the healthiest. Vegetarian fried rice can pack an unhealthy wallop. Instead, go for steamed dishes with lots of veggies and brown rice if it’s on the menu. Keep the rice to a half-cup -- that\"s about half the size of a baseball. Always ask for sauce on the side.",
                    "price": 11
                },
                {
                    "id": 3,
                    "name": "Salmon on bed",
                    "description": "Roasted beet and 5 0z. salmon on bed of kale and spinach greens with balsamic vinegar drizzle",
                    "price": 12
                }
            ]
        }
    ]
```    

<a name="votesadminexample"></a>
**Примеры работы с curl для ресурса** **_http:<i></i>//host:port/smartrestaurant/v/admin/votes:_**

**Получить всю историю голосований:**

     curl http://localhost:8080/smartrestaurant/v/admin/votes 

**Возвращает:**

     [{"id":1,"datev":"2018-12-28T00:00:00","user_id":1,"restaurant_id":1},{"id":2,"datev":"2018-12-28T00:00:00","user_id":2,"restaurant_id":1},{"id":3,"datev":"2018-12-28T00:00:00","user_id":3,"restaurant_id":2},{"id":4,"datev":"2018-12-28T00:00:00","user_id":3,"restaurant_id":1},{"id":5,"datev":"2018-12-29T00:00:00","user_id":1,"restaurant_id":2},{"id":6,"datev":"2018-12-29T00:00:00","user_id":2,"restaurant_id":1},{"id":7,"datev":"2018-12-29T00:00:00","user_id":3,"restaurant_id":2}]

**Получить историю голосований на дату:**

     curl http://localhost:8080/smartrestaurant/v/admin/votes/?date=2018-12-28 -u alex:qwerty2


**Возвращает:**

     [{"id":1,"datev":"2018-12-28T00:00:00","user_id":1,"restaurant_id":1},{"id":2,"datev":"2018-12-28T00:00:00","user_id":2,"restaurant_id":1},{"id":3,"datev":"2018-12-28T00:00:00","user_id":3,"restaurant_id":2},{"id":4,"datev":"2018-12-28T00:00:00","user_id":3,"restaurant_id":1}]

**Удалить всю историю голосований:**

      curl -X DELETE http://localhost:8080/smartrestaurant/v/admin/votes

**Удалить историю голосований на указанную дату:**

      curl -X DELETE http://localhost:8080/smartrestaurant/v/admin/votes?date=2018-12-28

