# QuestHero

## Proje Açıklaması

QuestHero, günlük görevlerinizin oyunlaştırılmış bir planlayıcı sistemidir. Kullanıcılar gerçek hayattaki görevlerini "quest" (görev) olarak tanımlayıp tamamladıkça puan kazanır, seviye atlar ve başarımlar elde eder.

## İçindekiler

* [Özellikler](#özellikler)
* [Gereksinimler](#gereksinimler)
* [Kurulum](#kurulum)
* [Kullanım](#kullanım)
* [Teknolojiler ve Kütüphaneler](#teknolojiler-ve-kütüphaneler)
* [Veritabanı Şeması](#veritabanı-şeması)
* [Tasarım Deseni](#tasarım-deseni)
* [Proje Yapısı](#proje-yapısı)
* [Katkıda Bulunanlar](#katkıda-bulunanlar)

## Özellikler

* Görev ekleme, silme, tamamlama
* Puan ve seviye sistemi
* Kaçırılan görevlerin takibi
* Oyunlaştırma paneli (GamePanel)
* Arka planda zamanlama kontrolü

## Gereksinimler

* Java 17 veya üzeri
* SQLite (dosya tabanlı)
* Internet bağlantısı gerekmiyor (tamamen lokal çalışır)

## Kurulum

1. Depoyu klonlayın:

   bash
   git clone https://github.com/1rems/OOP.git 
   
   bash
2. git push origin main

3.Projeyi  tercih ettiğiniz IDE’de açın.
4. Uygulamayı çalıştırın:


## Kullanım

1. Uygulama açıldığında kullanıcı adı ve ID girerek oturum açın.
2. Görev alanından başlık, süre, puan ve tarih bilgilerini girip "Görev Ekle" butonuna tıklayın.
3. Listede görünen göreve çift tıklayarak tamamlayın veya "Görevi Sil" ile kaldırın. Kullanıcı bilgilerini güncelle butonuna basarak ilerlemenizi kaydedin
4. "Seviye" butonuyla oyun panelini açıp ilerlemenizi görün. Başarımlar butonuna basarak başarılarınızı görün.

## Teknolojiler ve Kütüphaneler

* *JavaFX*: Grafik arayüz
* *JDBC*: Veritabanı bağlantısı
* *SQLite*: Lokal veritabanı (QuestHero.db)
* *ScheduledExecutorService*: Zamanlayıcı

## Veritabanı Şeması

-- users tablosu\ CREATE TABLE users (
  userID INTEGER PRIMARY KEY AUTOINCREMENT,
  username TEXT NOT NULL,
  point INTEGER DEFAULT 0,
  level INTEGER DEFAULT 1
);
-- tasks tablosu
CREATE TABLE tasks (
  taskID INTEGER PRIMARY KEY AUTOINCREMENT,
  title TEXT NOT NULL,
  duration INTEGER,
  date TEXT,
  point INTEGER,
  isCompleted BOOLEAN DEFAULT 0,
  createdTime TEXT,
  userID INTEGER,
  FOREIGN KEY(userID) REFERENCES users(userID)
);


## Tasarım Deseni

* **Singleton Pattern**: `DataBaseConnection` sınıfı tekil bağlantı sağlar.
* **MVC**: Model (`User`, `Task`, `Achievements`), View (JavaFX sahneleri), Controller (`Main` sınıfı ve event handler’lar).

## Proje Yapısı


Proje/
├─ src/
│  ├─ Proje/
│  │  ├─ Main.java
│  │  ├─ User.java
│  │  ├─ Task.java
│  │  ├─ Achievements.java
│  │  ├─ Reward.java
│  │  └─ DataBaseConnection.java
│  └─ styles.css
├─ QuestHero.db
└─ README.md
```

## Katkıda Bulunanlar

* Ezgi Nur Temür
* İrem Su Tepedelen 
* Ayşegül Pınar Yılmaz
