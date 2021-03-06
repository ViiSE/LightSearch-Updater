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
<img src="https://user-images.githubusercontent.com/43209824/80060806-d389a280-8572-11ea-914a-d13237b49952.png">
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
то показывает диалоговое окно с предложением обновить приложение. Если пользователь соглашается, то файл обновления скачивается по адресу значения поля "url" файла update.json, и устанавливается.

Доступ к последней версии и документации [LightSearch Android](https://github.com/ViiSE/LightSearch-Android)
---------------------------------------------------------------------------------------------
По точке /app доступна возможность скачать последний релиз [LightSearch Android](https://github.com/ViiSE/LightSearch-Android) и документацию к нему в формате PDF.

<p align="center">
<img src="https://user-images.githubusercontent.com/43209824/80067482-3d10ad80-8581-11ea-93eb-743878adca64.jpg"
     width="240" height="480">
</p>

Доступ к документации [LightSearch Android](https://github.com/ViiSE/LightSearch-Android) через браузер
-------------------------------------------------------------------------------------------------------
По точке /man можно получить доступ к документации в формате PDF через браузер (или web-движок), если устройством не поддерживается нативное отображение файлов этого формата (для работы требуется поддержка JavaScript).

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
- [JUnit 5](https://junit.org/junit5/)
- [PDF.js](https://mozilla.github.io/pdf.js/)
