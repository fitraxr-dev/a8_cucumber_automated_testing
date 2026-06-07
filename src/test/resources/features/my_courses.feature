Feature: Kursus Saya Pelajar

    Scenario: Memvalidasi requirement halaman "Kursus Saya" menampilkan course dalam progress 0%
        Given User telah terautentikasi
        And User telah mendaftar course dan course belum ada progress sama sekali
        When User mengklik tombol Kursus Saya
        Then User diarahkan ke halaman "Kursus Saya"
        And Halaman Kursus Saya tab Dalam Progress menampilkan daftar kursus yang sedang dalam progress termasuk 0%
