Feature: My Course

    Background:
        Given User telah login dengan kredesial yang valid
        And User berada di halaman Dashboard

    Scenario: Course Dalam Progress
        When User klik tombol kursus saya
        Then User diarahkan ke halaman kursus saya tab Dalam Progress
        And Halaman menampilkan daftar kursus yang sedang berlangsung

    @author-amr
    Scenario: Validasi tab 'Selesai' menampilkan course dengan progress 100% dan status 'Completed' (TC2)
        When User klik tombol kursus saya
        And User klik tab "Selesai"
        Then User diarahkan ke halaman "Kursus Saya" tab "Selesai"
        And Tab "Selesai" aktif dan menampilkan daftar course dengan progress = "100%"

    @author-micho
    Scenario: Validasi tab 'Dalam Progress' menampilkan course dengan progress 0% (TC3)
        When User klik tombol kursus saya
        Then User diarahkan ke halaman "Kursus Saya" tab "Belum Selesai"
        And Tab "Belum Selesai" aktif dan menampilkan daftar course dengan progress = "0%"