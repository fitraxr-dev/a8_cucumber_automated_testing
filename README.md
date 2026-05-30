# Cucumber Automated Testing

Panduan singkat untuk konfigurasi dan menjalankan WebDriver pada project ini.

## Prerequisites

- Java 17 atau lebih baru
- Maven
- Browser yang akan dipakai untuk test

## Konfigurasi WebDriver

Konfigurasi WebDriver dibaca dari file:

- `src/test/resources/web-driver.properties`

Contoh isi file:

```properties
browser=firefox
# browserBinary=/usr/bin/zen-browser
baseUrl=https://polban-space.cloudias79.com/jtk-learn/
headless=false
implicitWait=10
```

### Arti properti

- `browser`: browser yang dipakai. Nilai yang didukung saat ini adalah `firefox` dan `chrome`.
- `browserBinary`: path eksplisit ke binary browser. Kosongkan atau komentari jika ingin memakai browser default sistem.
- `baseUrl`: URL dasar aplikasi yang akan dibuka saat test berjalan.
- `headless`: `true` jika ingin menjalankan browser tanpa UI, `false` jika ingin melihat browser terbuka.
- `implicitWait`: waktu tunggu implisit dalam detik.

## Lokasi implementasi

- Konfigurasi driver: `src/test/java/com/a7/config/WebDriverConfig.java`
- Smoke test WebDriver: `src/test/java/com/a7/smoke/WebDriverSmokeTest.java`
- Runner Cucumber: `src/test/java/com/a7/runner/RunCucumberTest.java`
- Feature login: `src/test/resources/features/login.feature`

## Cara menjalankan smoke test

Smoke test dipakai untuk mengecek apakah WebDriver bisa start, membuka halaman, dan menutup browser dengan benar.

```bash
mvn test -Dtest=WebDriverSmokeTest
```

## Cara menjalankan Cucumber test

Jika ingin menjalankan skenario Cucumber, jalankan runner berikut:

```bash
mvn test -Dtest=RunCucumberTest
```

Jika runner dibatasi ke `login.feature`, maka hanya feature tersebut yang dijalankan.

## Jika browser tidak jalan

Periksa hal berikut:

1. Nilai `browser` di `web-driver.properties`.
2. Path `browserBinary` jika Anda mengisi properti itu.
3. Browser yang terpasang di sistem.
4. Error dari Selenium Manager atau `geckodriver` / `chromedriver`.

## Catatan

Kalau Anda ingin memakai browser selain Firefox atau Chrome, biasanya perlu penyesuaian di `WebDriverConfig` dan dependensi driver yang sesuai.
