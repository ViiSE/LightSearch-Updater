<p align="center"> 
<img src="https://user-images.githubusercontent.com/43209824/64838878-905c6e00-d638-11e9-8026-e7b04d1af80f.png"
     width="300" height="300">
</p>

LightSearch Updater
===================

LightSearch Updater - Сервис обновления мобильного приложения LightSearch.

Имеет web-панель администратора, при помощи которой можно создавать новые версии релиза LightSearch Android, 
добавлять через drag-and-drop apk файлы, и изменять файл релиза, который считывается LightSearch Android 
для проверки выхода нового релиза, в редакторе с поддержкой синтаксиса JSON.

<p align="center"> 
<img src="https://user-images.githubusercontent.com/43209824/75731886-a53ed200-5d3c-11ea-9675-d2e0ef41c1ab.png">
</p>

Принцип работы
--------------
Статический ресурс (файл) информации релиза находится в точке /update/info/update.json. Этот файл имеет следующую структуру:

<pre>
{
  "latestVersion": "input_latest_version_here",
  "latestVersionCode": "input_latest_version_code_here",
  "url": "input_url_to_apk_here",
  "releaseNotes": [
    "- input",
    "- release",
    "- notes",
    "- here"
  ]
}
</pre>

- latestVersion - последняя версия мобильного приложения
- latestVersionCode - последняя версия кода мобильного приложения
- url - полный путь до APK
- releaseNotes - массив с информацией об нововведениях релиза для пользователей

При старте LightSearch Android обращается к точке /update/info/update.json, и если новый релиз доступен, 
то показывает диалоговое окно с предложением обновить приложение. Если пользователь соглашается, то открывается браузер с
адресом значения поля "url" файла update.json, обновление скачивается и устанавливается.

Ссылки
------
История проекта LightSearch доступна в [документах](https://github.com/ViiSE/LightSearch/tree/master/Documents/Project%20history)
и в [заметках разработки](https://github.com/ViiSE/LightSearch/blob/master/Dev%20notes). 

Используемые библиотеки и технологии
------------------------------------
- [Spring Framework](https://github.com/spring-projects/spring-framework)
- [Spring Boot](https://github.com/spring-projects/spring-boot)
- [Spring MVC](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html)
- [Spring Security](https://github.com/spring-projects/spring-security)
- [TestNG](https://testng.org/doc/)
- [Vaadin](https://github.com/vaadin/)
