# Newsflow Agent Notları

Bu dosya, projede çalışacak geliştirici veya agent için kısa ve net çalışma rehberidir.

## Proje Özeti

Newsflow; RSS kaynaklarından haberleri çeken, PostgreSQL'e kaydeden ve React arayüzünde listeleyen bir haber akışı uygulamasıdır.

Ana parçalar:

- Backend: Spring Boot
- Frontend: React + Vite
- Veritabanı: PostgreSQL
- Lokal ortam: Docker Compose

## Klasörler

```text
backend/JavaNewsRss   Spring Boot backend
frontend              React frontend
screens               README görselleri
Readme.md             Proje dokümantasyonu
agent.md              Agent çalışma notları
```

## Lokal Çalıştırma

Backend klasöründen çalıştır:

```powershell
cd C:\Users\DenizSullu\Desktop\newsflow\backend\JavaNewsRss
.\mvnw.cmd -DskipTests package
docker compose up --build -d
```

Adresler:

- Frontend: http://localhost:3000
- Backend: http://localhost:8080
- Swagger: http://localhost:8080/swagger-ui/index.html
- pgAdmin: http://localhost:5050
- PostgreSQL: localhost:5432

pgAdmin:

- Email: `admin@admin.com`
- Şifre: `admin`

PostgreSQL:

- Database: `newspaper`
- User: `postgres`
- Password: `12345`
- Docker içi host: `postgres`
- Host makineden bağlantı: `localhost`

## Önemli Backend Dosyaları

```text
backend/JavaNewsRss/src/main/java/com/example/JavaNewsRss/Model/News.java
backend/JavaNewsRss/src/main/java/com/example/JavaNewsRss/Repository/NewsRepository.java
backend/JavaNewsRss/src/main/java/com/example/JavaNewsRss/Business/
backend/JavaNewsRss/src/main/java/com/example/JavaNewsRss/Controller/NewsController.java
backend/JavaNewsRss/src/main/java/com/example/JavaNewsRss/Scheduling/
```

Backend akışı:

1. Scheduler RSS kaynaklarını tetikler.
2. RSS itemları parse edilir.
3. Haberler `News` entitysine dönüştürülür.
4. `link` üzerinden duplicate kontrolü yapılır.
5. Yeni haberler PostgreSQL'e kaydedilir.
6. Frontend haberleri pageable endpoint üzerinden çeker.

## RSS Kaynakları

```text
BBC Türkçe: https://www.bbc.com/turkce/index.xml
NTV:        https://www.ntv.com.tr/gundem.rss
Sözcü:      https://www.sozcu.com.tr/feeds-haberler
```

RSS tarafında değişiklik yaparken her kaynak bağımsız ele alınmalıdır. Bir kaynak hata verirse diğer kaynakların çalışması durmamalıdır.

## API Endpointleri

```http
GET /api/news/getAllNews
GET /api/news/getNewsPageable?page=0&size=12
GET /api/news/getNewsPageable?page=0&size=12&publisher=BBC
GET /api/news/findByUUID/{uuid}
GET /api/news/findByTitle/{title}
```

Frontend listeleme için `getNewsPageable` kullanılmalıdır. `getAllNews` ana listeleme için kullanılmamalıdır.

## Frontend Notları

Önemli dosyalar:

```text
frontend/src/Components/News/News.jsx
frontend/src/Components/NewsDetail/NewsDetail.jsx
frontend/src/Components/Navi/Navi.jsx
frontend/src/services/newsService.js
frontend/src/index.css
frontend/vite.config.js
```

API istekleri component içinde dağınık yazılmamalı, `newsService.js` üzerinden yönetilmelidir.

## Dikkat Edilecek Kurallar

- `docker-compose.yml` ana compose dosyasıdır.
- Backend image çalışmadan önce jar üretilmelidir.
- Haber duplicate kontrolünde `link` alanı korunmalıdır.
- Sayfalama responseunda `id` ve `imageUrl` alanları kalmalıdır.
- Frontend ana haber listesinde pageable API kullanılmalıdır.
- RSS kaynakları tek hata yüzünden birbirini durdurmamalıdır.
- Backend değişikliklerinden sonra build ve Docker testi yapılmalıdır.
- Gereksiz refactor yapılmamalıdır.

## Kontrol Komutları

Backend build:

```powershell
cd C:\Users\DenizSullu\Desktop\newsflow\backend\JavaNewsRss
.\mvnw.cmd -DskipTests package
```

Docker başlatma:

```powershell
docker compose up --build -d
```

Log kontrolü:

```powershell
docker compose logs backend
```

Container kontrolü:

```powershell
docker compose ps
```

API kontrolü:

```powershell
Invoke-RestMethod "http://localhost:8080/api/news/getNewsPageable?page=0&size=5"
```

## Geliştirme Öncelikleri

Backend tarafında öncelik sırası:

1. Test altyapısı
2. RSS parserların daha sağlam hale getirilmesi
3. Veritabanı migration yapısı
4. Daha net REST endpoint tasarımı
5. Logging ve healthcheck iyileştirmeleri
6. CI/CD pipeline

Frontend tarafında öncelik sırası:

1. Componentleri küçük parçalara ayırmak
2. API state yönetimini düzenlemek
3. Loading ve error state kontrollerini güçlendirmek
4. Mobil görünümü korumak

## Değişiklik Sonrası Minimum Kontrol

Bir değişiklikten sonra en az şunlar kontrol edilmelidir:

- Backend build alıyor mu?
- Docker servisleri ayağa kalkıyor mu?
- Swagger açılıyor mu?
- Pageable haber endpointi cevap veriyor mu?
- Frontend ana sayfada haberler görünüyor mu?
- Haber detay sayfası açılıyor mu?
- BBC, NTV ve Sözcü kaynakları ayrı ayrı çalışabiliyor mu?
